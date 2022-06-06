package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import utils.InputCheckUtils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Jakub Štěpánek
 */
public class Race {
    private int seasonYear;
    private Circuit circuitName;
    private final ArrayList<Racer> racers = new ArrayList<>();
    private ArrayList<Racer> racersBackup = new ArrayList<>();

    /**
     * Public constructor
     */
    public Race() {
    }

    /**
     * Public constructor
     * 
     * @param seasonYear
     * @param circuitName
     */
    public Race(int seasonYear, Circuit circuitName) {
        this.seasonYear = seasonYear;
        this.circuitName = circuitName;
    }

    // getters
    /**
     * Function to get object Racer
     * 
     * @param racer
     * @return Racer
     */
    public Racer getRacer(Racer racer) {
        return new Racer(racers.get(findIndexOfRacer(racer)));
    }

    /**
     * Function to get Name of Circuit
     * 
     * @return String
     */

    public String getCircuitName() {
        return this.circuitName.getCircuitValue();
    }

    /**
     * Function to get Shortcut of Circuit
     * 
     * @return Circuit
     */

    public Circuit getCircuitShortcut() {
        return this.circuitName;
    }

    /**
     * Function to get Season year
     * 
     * @return int
     */
    public int getSeasonYear() {
        if (InputCheckUtils.checkSeasonYearBoolean(this.seasonYear)) {
            return this.seasonYear;
        }
        throw new IllegalArgumentException("Sezóna nebyla nastavena");
    }

    /**
     * Function to get all Racers
     * 
     * @return List
     */

    public List<Racer> getRacers() {
        ArrayList<Racer> copy = new ArrayList<>();
        for (Racer racer : this.racers) {
            copy.add(new Racer(racer));
        }
        return copy;
    }

    /**
     * Function to get count of Racers
     * 
     * @return int
     */

    public int getRacersSize() {
        return this.racers.size();
    }

    // setters
    /**
     * Method to add Racer to Race
     * 
     * @param racer
     */
    public void addRacer(Racer racer) {
        this.racers.add(racer);
    }

    /**
     * Method to assign Season year
     * 
     * @param seasonYear
     */
    public void setSeasonYear(int seasonYear) {
        this.seasonYear = seasonYear;
    }

    /**
     * Method to set name of Circuit
     * 
     * @param circuitName
     */
    public void setCircuitName(Circuit circuitName) {
        this.circuitName = circuitName;
    }

    /**
     * Function to get index of selected Racer, if there is no Racer like wanted
     * Racer, function returns -1
     * 
     * @param wantedRacer
     * @return int
     */
    private int findIndexOfRacer(Racer wantedRacer) {
        int index = 0;
        for (Racer racer : racers) {
            if (!wantedRacer.equals(racer)) {
                index++;
            } else {
                return index;
            }
        }
        return -1;
    }

    /**
     * Function checks is name of Circuit is set
     * 
     * @return boolean
     */
    public boolean isCircuitNameSet() {
        return (this.circuitName != null);

    }

    /**
     * Function to sort Racers by Surname
     * 
     * @return List<Racer>
     */

    public List<Racer> sortBySurname() {
        ArrayList<Racer> copy = (ArrayList<Racer>) getRacers();
        Comparator<Racer> cbs = new ComparatorRacerBySurname();
        Collections.sort(copy, cbs);
        return copy;
    }

    /**
     * Function to get Racers selected by Surname
     * 
     * @param surname
     * @return List
     */
    public List<Racer> getRacer(String surname) {
        return racers.stream()
                .filter(racers -> racers.getSurname().equalsIgnoreCase(surname))
                .collect(Collectors.toList());
    }

    /**
     * Method to delete Racer from Race
     * 
     * @param racer
     */
    public void deleteRacer(Racer racer) {
        ArrayList<Racer> copy = this.racers;
        backupRacers(this.racers);
        // nebezpečné?
        this.racers.clear();
        for (Racer racer2 : copy) {
            if (racer2.getSurname().compareTo(racer.getSurname()) != 0) {
                copy.add(racer);
            }
        }
        for (Racer racer2 : copy) {
            this.racers.add(racer2);
        }
    }

    /**
     * Private Method to backup Racers before deleting
     * 
     * @param racers
     */

    private void backupRacers(ArrayList<Racer> racers) {
        this.racersBackup = racers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Racer> racersToString = (ArrayList<Racer>) getRacers();
        sb.append("Výsledky:");
        sb.append(System.lineSeparator());
        sb.append("==========");
        sb.append(System.lineSeparator());
        sb.append(String.format("Sezóna %s, okruh %s [%s]\n", getSeasonYear(), getCircuitName(), getCircuitShortcut()));
        sb.append(String.format("%-8s %-10s %-10s %-15s %-30s %-10s %-15s %-2s %s", "Umístění",
                "Jméno",
                "Příjmení", "Národnost", "Tým", "Motocykl", "Startovní číslo",
                "Max. rychlost", "Čas"));
        sb.append(System.lineSeparator());
        for (Racer racer : racersToString) {
            sb.append(racer + "\n");
        }
        return sb.toString();
    }
}
