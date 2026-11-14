package edu.utsa.cs3443.liftlog.model;

/**
 * Exercise class will represent a single exercise in a workout
 * will be used as a collection in workout class
 * @author dgasp24
 * @author BroFreeman
 */
public class Exercise {
    private String name;
    private int sets;
    private int reps;
    private double weight;

    public Exercise(String name, int sets, int reps, double weight) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }

    /**
     * returns name for Exercise
     * @return String, Exercise name
     */
    public String getName() {return name;}

    /**
     * sets name for Exercise
     * @param name, Exercise name (String)
     */
    public void setName(String name) {this.name = name;}

    /**
     * returns number of sets for Exercise : (sets) x (reps)
     * @return int, Exercise sets
     */
    public int getSets() {return sets;}

    /**
     * sets the number of sets for the exercise : (sets) x (reps)
     * @param sets, Exercise sets (int)
     */
    public void setSets(int sets) {this.sets = sets;}

    /**
     * returns number of reps for the exercise : (sets) x (reps)
     * @return int, Exercise reps
     */
    public int getReps() {return reps;}

    /**
     * sets the number of reps for the exercise : (sets) x (reps)
     * @param reps, Exercise reps (int)
     */
    public void setReps(int reps) {this.reps = reps;}

    /**
     * returns the weight used for the exercise
     * @return double, Exercise weight
     */
    public double getWeight() {return weight;}

    /**
     * sets the amount of weight used for the exercise
     * @param weight, Exercise weight (double)
     */
    public void setWeight(double weight) {this.weight = weight;}

    //should probably have an method to add exercise
}
