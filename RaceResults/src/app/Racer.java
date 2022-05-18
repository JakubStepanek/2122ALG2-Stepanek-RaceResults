package app;

import java.rmi.StubNotFoundException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kubin
 */
public class Racer {
    private String name;
    private String surname;
    private Nationality nationality;
    private int pozition;
    private int points;

    public Racer(String name, String surname, Nationality nationality) {
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
    }

    public Racer(Racer racer) {
        this.name = racer.name;
        this.surname = racer.surname;
        this.nationality = racer.nationality;
    }

    public void setPozition(int pozition) {
        this.pozition = pozition;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return String.format("%10s %10s %20s", name, surname, this.nationality.getNationalityValue());
    }

    public static void main(String[] args) {
        Racer r = new Racer("Jakub", "Štěpánek", Nationality.CZ);
        System.out.println(r);
    }

}
