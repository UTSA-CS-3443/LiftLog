package edu.utsa.cs3443.liftlog.model;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.StringTokenizer;

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

    //loadWorkouts() still working on it
    /*public void loadWorkouts() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String firstLine = reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                Workout workout = convertLineToWorkout(line);
                workouts.add(workout);
            }
        }catch(FileNotFoundException e) {
            throw new IOException("Workout file not found");
        }catch(IOException e) {
            throw new IOException("Error reading Workout file");
        }
    }*/

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
        String comma = ",";
        String line = workout.getworkoutid() + comma + workout.getDate() + comma +
                workout.getWorkoutName() + comma + exercise.getName() + comma +
                exercise.getSets() + comma + exercise.getReps() +
                comma + exercise.getWeight();
        return line;
    }

    public void saveWorkouts() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))){
            writer.println("workoutid,date,workoutName,exercise,sets,reps,weight");
            for (Workout workout : workouts) {
                for (Exercise exercise : workout.getExercises()) {
                    writer.println(convertToLine(workout, exercise));
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
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
