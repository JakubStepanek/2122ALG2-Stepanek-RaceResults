package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import javax.xml.crypto.Data;

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
    private Category category;
    private String circuitName;
    private String location;
    private ArrayList<Racer> racers = new ArrayList<>();
    private TimeTools raceDuration;
    private int topSpeed;
    private String teamName;
    private String bikeName;
    private TimeTools time;

    public Race() {

    }

    public Race(int seasonYear, String circuitName) {
        this.seasonYear = seasonYear;
        this.circuitName = circuitName;
    }

    public void addRacer(Racer racer) {
        this.racers.add(racer);
    }

    public Racer getRacer(Racer racer) {
        return new Racer(racers.get(findIndexOfRacer(racer)));
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    public void setSeasonYear(int seasonYear) {
        this.seasonYear = seasonYear;
    }

    public int getSeasonYear() {
        return this.seasonYear;
    }

    public ArrayList<Racer> getRacers() {
        ArrayList<Racer> copy = new ArrayList<>();
        for (Racer racer : this.racers) {
            copy.add(new Racer(racer));
        }
        return copy;
    }

    private int findIndexOfRacer(Racer wantedRacer) {
        int index = 0;
        for (Racer racer : racers) {
            if (!wantedRacer.equals(racer)) {
                index++;
            } else {
                break;
            }
        }
        return index;
    }

    private Racer firstRacer() {
        for (Racer racer : racers) {
            if (racer.getPozition() == 1) {
                return new Racer(racer);
            }
        }
        return null;
    }

    public void loadStats(File file) throws FileNotFoundException, IOException {
        String line;
        String[] parts;
        Racer r;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // skip header
            br.readLine();
            while ((line = br.readLine()) != null) {
                parts = line.split(";");
                r = new Racer(parts[6], parts[5], Nationality.valueOf(parts[12]));
                r.setTeam(parts[7]);
                r.setBike(parts[8]);
                r.setRacingNumber(parts[11]);
                if (parts[14].contains("+")) {
                    int temp = TimeTools.timeToSeconds(parts[14].substring(1, parts[14].length()));
                    int shortestTime = TimeTools.timeToSeconds(firstRacer().getRaceTime());
                    r.setRaceTime(TimeTools.secondsToTimeString(temp + shortestTime));
                } else {
                    r.setRaceTime(parts[14]);
                }
                if (parts[13].length() != 0) {
                    r.setMaxSpeed(Double.parseDouble(parts[13]));
                }
                r.setPozition(Integer.parseInt(parts[9]));
                addRacer(r);
            }
        }
    }

    public ArrayList<Racer> sortBySurname() {
        ArrayList copy = getRacers();
        Comparator cbs = new ComparatorRacerBySurname();
        Collections.sort(copy, cbs);
        return copy;
    }

    public Racer showDetailOfRacer(String surname) {
        for (Racer racer : racers) {
            if ((racer.getSurname()).equals(surname)) {
                return new Racer(racer);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Racer> racersToString = getRacers();
        sb.append("Výsledky: \n");
        sb.append("========== \n");
        sb.append(String.format("Sezóna %s, okruh %s%n", getSeasonYear(), getLocation()));
        for (Racer racer : racersToString) {
            sb.append(racer + "\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Race race = new Race(2021, "Brno");
        // String parentPath = System.getProperty("user.dir") + File.separator + "data"
        // + File.separator
        // + "raceResults2021.csv";
        // File dataDirectory = new File(parentPath);
        File dataDirectory = new File(
                "/Users/kubin/Documents/TUL/Semestr 2/Java/2122ALG2-RaceResults/RaceResults/src/Data/raceResults2020.csv");
        // DataControl.loadStats(dataDirectory, race);
        race.loadStats(dataDirectory);
        // race.loadStats(dataDirectory);
        // System.out.println(race);
        // TODO: better show of sortBySurname
        // System.out.println(race.sortBySurname());

        Scanner sc = new Scanner(System.in);
        String surname = sc.nextLine();
        System.out.println(race.showDetailOfRacer(surname));

    }
}

// DEBUG CODES
// Racer racer = new Racer("Jakub", "Štěpánek", Nationality.CZ);
// Racer racer1 = new Racer("Matěj", "SADA", Nationality.ES);
// Racer racer2 = new Racer("Pavel", "Vácha", Nationality.GB);
// Racer racer3 = new Racer("Fabian", "Klein", Nationality.DE);
// race.addRacer(racer);
// race.addRacer(racer1);
// race.addRacer(racer2);
// race.addRacer(racer3);
// System.out.println(race);
// System.out.println();
// System.out.println(race.getRacer(racer2));
