package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

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
    private Circuit circuitName;
    private String location;
    private final ArrayList<Racer> racers = new ArrayList<>();

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

    public void setLocation(String location) {
        this.location = location;
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
        if (isSeasonValid()) {
            return this.seasonYear;
        }
        throw new IllegalArgumentException("Sezóna nebyla nastavena");
    }

    private boolean isSeasonValid() {
        if (this.seasonYear <= 1949) {
            return false;
        }
        return true;
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
                return index;

            }
        }
        return -1;
    }

    private Racer firstRacer() {
        for (Racer racer : racers) {
            if (racer.getPosition() == 1) {
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
                if (!isSeasonValid()) {
                    setSeasonYear(Integer.parseInt(parts[0]));
                }
                if (!isCircuitNameSet()) {
                    setCircuitName(Circuit.valueOf(parts[3]));
                }
                r = new Racer(parts[6], parts[5], Nationality.valueOf(parts[12]));
                r.setTeam(parts[7]);
                r.setBike(parts[8]);
                r.setPoints(Integer.parseInt(parts[10]));
                r.setRacingNumber(parts[11]);
                r.setRaceTime(parts[14]);
                if (parts[13].length() != 0) {
                    r.setMaxSpeed(Double.parseDouble(parts[13]));
                }
                r.setPosition(Integer.parseInt(parts[9]));
                addRacer(r);
            }
        }
    }

    // TODO: TEST
    public void saveToFile(File results) throws IOException {
        int rank = 1;
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(results, true)))) {
            // header
            pw.println(String.format("%5s %10s %10s", "Pořadí", "Jméno", "Příjmení"));
            for (Racer racer : racers) {
                pw.print(String.format("%4d ", rank));
                pw.println(racer);
                rank++;
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    private boolean isCircuitNameSet() {
        return !(this.circuitName == null);

    }

    public void setCircuitName(Circuit circuitName) {
        this.circuitName = circuitName;
    }

    public ArrayList<Racer> sortBySurname() {
        ArrayList<Racer> copy = getRacers();
        Comparator<Racer> cbs = new ComparatorRacerBySurname();
        Collections.sort(copy, cbs);
        return copy;
    }

    public ArrayList<Racer> getRacer(String surname) {
        ArrayList<Racer> foundRacers = (ArrayList<Racer>) racers.stream()
                .filter(racers -> racers.getSurname().equalsIgnoreCase(surname))
                .collect(Collectors.toList());
        return foundRacers;
    }

    public void deleteRacer(Racer racer) {
        for (Racer racer2 : this.racers) {
            if (racer2.getSurname().compareTo(racer.getName()) == 0) {
                this.racers.remove(racer2);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Racer> racersToString = getRacers();
        sb.append("Výsledky:");
        sb.append(System.lineSeparator());
        sb.append("==========");
        sb.append(System.lineSeparator());
        sb.append(String.format("Sezóna %s, okruh %s [%s]%n", getSeasonYear(), getCircuitName(), getCircuitShortcut()));
        for (Racer racer : racersToString) {
            sb.append(racer + "\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Race race = new Race();
        // String parentPath = System.getProperty("user.dir") + File.separator + "data"
        // + File.separator
        // + "raceResults2021.csv";
        // File dataDirectory = new File(parentPath);
        File dataDirectory = new File(
                "/Users/kubin/Documents/TUL/Semestr 2/Java/2122ALG2-RaceResults/RaceResults/src/Data/raceResults2021OneRace.csv");
        // DataControl.loadStats(dataDirectory, race);
        race.loadStats(dataDirectory);
        // race.loadStats(dataDirectory);
        System.out.println(race);
        // TODO: better show of sortBySurname
        // System.out.println(race.sortBySurname());

        // Scanner sc = new Scanner(System.in);
        // String surname = sc.nextLine();
        // System.out.println(race.showDetailOfRacer(surname));

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
