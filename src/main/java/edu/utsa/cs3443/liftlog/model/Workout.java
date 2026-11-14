package edu.utsa.cs3443.liftlog.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Workout class is a collection of exercises from Exercise class
 * @author dgasp24
 * @author BroFreeman
 */
public class Workout {
    private String workoutid;
    private String workoutName;
    private LocalDate date;
    private ArrayList<Exercise> exercises;

    public Workout(String workoutid, String workoutName, LocalDate date) {
        this.workoutid = workoutid;
        this.workoutName = workoutName;
        this.date = date;
        exercises = new ArrayList<>();
    }

    /**
     * returns the workout id
     * @return String, workoutid
     */
    public String getworkoutid() {return workoutid;}

    /**
     * sets teh workout id
     * @param workoutid, String
     */
    public void setworkoutid(String workoutid) {this.workoutid = workoutid;}

    /**
     * returns the workout name
     * @return String, workout name
     */
    public String getWorkoutName() {return workoutName;}

    /**
     * sets the workout name
     * @param workoutName, String
     */
    public void setWorkoutName(String workoutName) {this.workoutName = workoutName;}

    /**
     * returns the date the workout was set on
     * @return LocalDate, date
     */
    public LocalDate getDate() {return date;}

    /**
     * sets the date the workout was set on
     * @param date, LocalDate
     */
    public void setDate(LocalDate date) {this.date = date;}

    /**
     * returns list of Exercises (workout made up of exercises)
     * @return arraylist, exercises
     */
    public ArrayList<Exercise> getExercises() {return exercises;}

    /**
     * sets the list of exercises (workout made up of exercises)
     * @param exercises, arraylist
     */
    public void setExercises(ArrayList<Exercise> exercises) {this.exercises = exercises;}

    /**
     * adds a single exercise to the list
     * @param exercise, exercises
     * since workout is a list of exercises will be useful to have
     */
    public void addExercise(Exercise exercise) { exercises.add(exercise); }
}
