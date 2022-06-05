package app;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kubin
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

    public Racer(String name, String surname, Nationality nationality) {
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
    }

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setRaceTime(String raceTime) {
        this.raceTime = raceTime;
    }

    public String getRaceTime() {
        return this.raceTime;
    }

    public Nationality getNationality() {
        return this.nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public int getPosition() {
        if (this.position < 0) {
            return 0;
        } else {
            return this.position;
        }
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getBike() {
        return this.bike;
    }

    public void setBike(String bike) {
        this.bike = bike;
    }

    public String getTeam() {
        return this.team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getRacingNumber() {
        return this.racingNumber;
    }

    public void setRacingNumber(int racingNumber) {
        this.racingNumber = racingNumber;
    }

    public double getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getNationalityValue() {
        return this.nationality.getNationalityValue();
    }

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
        return this.getPoints() - o.getPoints();
    }

}
