package edu.utsa.cs3443.liftlog.model;

import java.util.ArrayList;

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
    

}
