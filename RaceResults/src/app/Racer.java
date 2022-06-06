package app;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jakub Štěpánek
 */
public class Racer implements Comparable<Racer> {
    private String name;
    private String surname;
    private Nationality nationality;
    private int position;
    private int points;
    private String bike;
    private String team;
    private int racingNumber;
    private double maxSpeed;
    private String raceTime;

    /**
     * Public constructor
     * 
     * @param name
     * @param surname
     * @param nationality
     */
    public Racer(String name, String surname, Nationality nationality) {
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
    }

    /**
     * Public constructor
     * 
     * @param racer
     */

    public Racer(Racer racer) {
        this.name = racer.name;
        this.surname = racer.surname;
        this.nationality = racer.nationality;
        this.position = racer.position;
        this.points = racer.points;
        this.bike = racer.bike;
        this.team = racer.team;
        this.racingNumber = racer.racingNumber;
        this.maxSpeed = racer.maxSpeed;
        this.raceTime = racer.raceTime;
    }

    // getters
    /**
     * Function to get Name of Racer
     * 
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Function to get Surname of Racer
     * 
     * @return String
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * Function to get Time of Race for Racer
     * 
     * @return String
     */
    public String getRaceTime() {
        return this.raceTime;
    }

    /**
     * Function to get Nationality of Racer
     * 
     * @return Nationality
     */

    public Nationality getNationality() {
        return this.nationality;
    }

    /**
     * Function to get Position of Racer in Race
     * 
     * @return int
     */
    public int getPosition() {
        if (this.position < 0) {
            return 0;
        } else {
            return this.position;
        }
    }

    /**
     * Function to get Points of Racer by Race
     * 
     * @return int
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Function to get Bike name
     * 
     * @return String
     */
    public String getBike() {
        return this.bike;
    }

    /**
     * Function to get Team name
     * 
     * @return String
     */
    public String getTeam() {
        return this.team;
    }

    /**
     * Function to get Racing number of Racer
     * 
     * @return int
     */
    public int getRacingNumber() {
        return this.racingNumber;
    }

    /**
     * Function to get maximal speed of Racer by Race
     * 
     * @return double
     */
    public double getMaxSpeed() {
        return this.maxSpeed;
    }

    /**
     * Function to get Nationality value of Racer
     * 
     * @return String
     */
    public String getNationalityValue() {
        return this.nationality.getNationalityValue();
    }

    // setters
    /**
     * Method to set Name of Racer
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to set Surname of Racer
     * 
     * @param surname
     */

    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Method to set Race time of Racer
     * 
     * @param raceTime
     */
    public void setRaceTime(String raceTime) {
        this.raceTime = raceTime;
    }

    /**
     * Method to set Nationality of Racer
     * 
     * @param nationality
     */
    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    /**
     * Method to set Position of Racer in Race
     * 
     * @param position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Method to se Points of Racer by Race
     * 
     * @param points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Method to set Bike of Racer
     * 
     * @param bike
     */
    public void setBike(String bike) {
        this.bike = bike;
    }

    /**
     * Method to set Team of Racer
     * 
     * @param team
     */
    public void setTeam(String team) {
        this.team = team;
    }

    /**
     * Method to set Racing number of Racer
     * 
     * @param racingNumber
     */
    public void setRacingNumber(int racingNumber) {
        this.racingNumber = racingNumber;
    }

    /**
     * Method to set maximal speed of Racer by Race
     * 
     * @param maxSpeed
     */
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * Function to format output of Racers
     * 
     * @return String
     */
    public String toInternalDatabaseFormat() {
        // rider_name;rider_surname;team_name;bike_name;points;number;country
        return String.format("%-10s %-10s %-15s %-30s %-10s %-10s %s", getName(), getSurname(), getNationalityValue(),
                getTeam(), getBike(),
                getPoints(),
                getRacingNumber());
    }

    @Override
    public String toString() {
        // create two variables to now call getPosition twice a Racer to string
        int tempPositionNumber = getPosition();
        String tempPosition = (tempPositionNumber == 0 ? "DNF" : tempPositionNumber + ".");
        return String.format("%-8s %-10s %-10s %-15s %-30s %-10s %-15s %.1fmph %s %s bodů",
                tempPosition, getName(),
                getSurname(),
                getNationalityValue(),
                getTeam(), getBike(), getRacingNumber(), getMaxSpeed(), getRaceTime(), getPoints());
    }

    @Override
    public int compareTo(Racer o) {
        return Integer.compare(this.points, o.points);
    }

}
