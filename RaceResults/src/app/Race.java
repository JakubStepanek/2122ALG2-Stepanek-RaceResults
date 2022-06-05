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
 * @author kubin
 */
public class Race {
    private int seasonYear;
    private Circuit circuitName;
    private final ArrayList<Racer> racers = new ArrayList<>();
    private ArrayList<Racer> racersBackup = new ArrayList<>();

    public Race() {

    }

    public Race(int seasonYear, Circuit circuitName) {
        this.seasonYear = seasonYear;
        this.circuitName = circuitName;
    }

    public void addRacer(Racer racer) {
        this.racers.add(racer);
    }

    public Racer getRacer(Racer racer) {
        return new Racer(racers.get(findIndexOfRacer(racer)));
    }

    public String getCircuitName() {
        return this.circuitName.getCircuitValue();
    }

    public Circuit getCircuitShortcut() {
        return this.circuitName;
    }

    public void setSeasonYear(int seasonYear) {
        this.seasonYear = seasonYear;
    }

    public int getSeasonYear() {
        if (InputCheckUtils.checkSeasonYearBoolean(this.seasonYear)) {
            return this.seasonYear;
        }
        throw new IllegalArgumentException("Sezóna nebyla nastavena");
    }

    public List<Racer> getRacers() {
        ArrayList<Racer> copy = new ArrayList<>();
        for (Racer racer : this.racers) {
            copy.add(new Racer(racer));
        }
        return copy;
    }

    public int getRacersSize() {
        return this.racers.size();
    }

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

    public boolean isCircuitNameSet() {
        return (this.circuitName != null);

    }

    public void setCircuitName(Circuit circuitName) {
        this.circuitName = circuitName;
    }

    public List<Racer> sortBySurname() {
        ArrayList<Racer> copy = (ArrayList<Racer>) getRacers();
        Comparator<Racer> cbs = new ComparatorRacerBySurname();
        Collections.sort(copy, cbs);
        return copy;
    }

    public List<Racer> getRacer(String surname) {
        return racers.stream()
                .filter(racers -> racers.getSurname().equalsIgnoreCase(surname))
                .collect(Collectors.toList());
    }

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
