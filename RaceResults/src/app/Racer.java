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
    private int pozition;
    private int points;
    private String bike;
    private String team;
    private String racingNumber;
    private double maxSpeed;

    public Racer(String name, String surname, Nationality nationality) {
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
    }

    public Racer(Racer racer) {
        this.name = racer.name;
        this.surname = racer.surname;
        this.nationality = racer.nationality;
        this.pozition = racer.pozition;
        this.points = racer.points;
        this.bike = racer.bike;
        this.team = racer.team;
        this.racingNumber = racer.racingNumber;
        this.maxSpeed = racer.maxSpeed;
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

    public Nationality getNationality() {
        return this.nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public int getPozition() {
        return this.pozition;
    }

    public void setPozition(int pozition) {
        this.pozition = pozition;
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

    public String getRacingNumber() {
        return this.racingNumber;
    }

    public void setRacingNumber(String racingNumber) {
        this.racingNumber = racingNumber;
    }

    public double getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return String.format("%10s %10s %15s %30s %10s Startovní číslo: %2s pozice: %2d %.1fmph", name, surname,
                this.nationality.getNationalityValue(),
                this.getTeam(), this.getBike(), this.getRacingNumber(), getPozition(), getMaxSpeed());
    }

    public static void main(String[] args) {
        Racer r = new Racer("Jakub", "Štěpánek", Nationality.CZ);
        System.out.println(r);
        // r.setRacingNumber("88");
        // System.out.println(r);

    }

    @Override
    public int compareTo(Racer o) {
        // TODO Auto-generated method stub
        return 0;
    }

}
