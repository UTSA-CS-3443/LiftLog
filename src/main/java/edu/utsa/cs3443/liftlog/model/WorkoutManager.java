package edu.utsa.cs3443.liftlog.model;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * WorkoutManager is a collection of workouts
 * @author dgasp24
 * @author BroFreeman
 */
public class WorkoutManager {
    private ArrayList<Workout> workouts;
    private String fileName = "data/workouts.csv";

    public WorkoutManager() {
        workouts = new ArrayList<>();
    }

    /**
     * adds a workout to the collection of workouts
     * @param workout, workouts
     */
    public void addWorkout(Workout workout) {workouts.add(workout);}

    /**
     * returns the list of workouts
     * @return list, workouts
     */
    public ArrayList<Workout> getWorkouts() { return workouts; }

    private Workout findWorkout(String id){
        for (Workout workout : workouts) {
            if (id.equals(workout.getworkoutid())){
                return workout;
            }
        }
        return null;
    }
    
    public void loadWorkouts() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String firstLine = reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                Workout workout = convertLineToWorkout(line);
                Workout current = findWorkout(workout.getworkoutid());
                if (current != null){
                    current.addExercise(workout.getExercises().get(0));
                }else{
                    workouts.add(workout);
                }
            }
        }catch(FileNotFoundException e) {
            throw new IOException("Workout file not found");
        }catch(IOException e) {
            throw new IOException("Error reading Workout file");
        }
    }

    private Workout convertLineToWorkout(String line){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        String[] parts = line.split(",");
        String workoutID = parts[0];
        LocalDate date = LocalDate.parse(parts[1], dateFormat);
        String name = parts[2];
        String exercise = parts[3];
        int sets = Integer.parseInt(parts[4]);
        int reps = Integer.parseInt(parts[5]);
        double weight = Double.parseDouble(parts[6]);

        Workout workout = new Workout(workoutID, name, date);
        workout.addExercise(new Exercise(exercise, sets, reps, weight));
        return workout;
    }
    private String convertToLine(Workout workout, Exercise exercise) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String comma = ",";
        String line = workout.getworkoutid() + comma + workout.getDate().format(dateFormat) + comma +
                workout.getWorkoutName() + comma + exercise.getName() + comma +
                exercise.getSets() + comma + exercise.getReps() +
                comma + exercise.getWeight();
        return line;
    }

    public void saveWorkouts() {
        try {
            saveWorkoutsOrThrow();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveWorkoutsOrThrow() throws IOException {
        Path destination = Path.of(fileName).toAbsolutePath();
        Path temporaryFile = Files.createTempFile(
                destination.getParent(), "workouts-", ".tmp");

        try {
            try (BufferedWriter writer = Files.newBufferedWriter(temporaryFile)) {
                writer.write("workoutid,date,workoutName,exercise,sets,reps,weight");
                writer.newLine();

                for (Workout workout : workouts) {
                    for (Exercise exercise : workout.getExercises()) {
                        writer.write(convertToLine(workout, exercise));
                        writer.newLine();
                    }
                }
            }

            Files.move(temporaryFile, destination, StandardCopyOption.ATOMIC_MOVE,
                    StandardCopyOption.REPLACE_EXISTING);
        } finally {
            Files.deleteIfExists(temporaryFile);
        }
    }

    public void deleteWorkout(String id) throws IOException {
        Workout tempWorkout = null;
        for (Workout workout : workouts) {
            if (id.equals(workout.getworkoutid())){
                tempWorkout = workout;
            }
        }
        if (tempWorkout != null) {
            workouts.remove(tempWorkout);
            saveWorkouts();
        }
    }
}
