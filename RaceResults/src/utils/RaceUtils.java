package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import app.Circuit;
import app.Nationality;
import app.Race;
import app.Racer;

public class RaceUtils {
    public static Scanner sc = new Scanner(System.in);

    public static void saveRace(Race race, String fileName) throws IOException {
        String result = System.getProperty("user.dir") + File.separator + "Data" + File.separator
                + fileName + ".csv";
        // new PrintWriter(new OutputStreamWriter () pouzit, kdyz chci kodovani
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(result, true)))) {
            pw.println(String.format("%-8s %-10s %-10s %-15s %-30s %-10s %-15s %-2s %s", "Umístění",
                    "Jméno",
                    "Příjmení", "Národnost", "Tým", "Motocykl", "Startovní číslo",
                    "Max. rychlost", "Čas"));
            for (Racer racer : race.getRacers()) {
                pw.println(racer);
            }
        }
    }

    // public void addPointsToInternalDatabase(Race race) {
    // ArrayList<Racer> loadedRacers = race.getRacers();
    // for (Racer racer : racerStats) {
    // for (int i = 0; i < loadedRacers.size(); i++) {
    // if (racer.getRacingNumber().equals(loadedRacers.get(i).getRacingNumber())) {
    // racer.setPoints(racer.getPoints() + loadedRacers.get(i).getPoints());
    // }
    // }
    // }
    // }
    public static boolean areRacersEmpty(Race race) {
        return race.getRacers().isEmpty();
    }

    public static Race createNewRace() throws IllegalArgumentException {
        // TODO: add new racer to riders.csv
        Race raceByUser = new Race();
        System.out.print("Zadejte sezónu, kdy se jel závod: ");
        raceByUser.setSeasonYear(InputCheck.checkSeasonYear(sc.nextInt()));
        // buffer clear
        sc.nextLine();
        System.out.print("Zadejte název okruhu: ");
        raceByUser.setCircuitName(Circuit.of(InputCheck.checkCircuitValues(sc.nextLine())));
        String answer;
        // do {// TODO: add racers to raceByUser InternalDB
        // System.out.print("Přejete si přidat závodníka? [ano/ne]: ");
        // answer = sc.nextLine().toLowerCase();
        // if (answer.equalsIgnoreCase("ano")) {
        // raceByUser.addRacer(addRacerByUser());
        // }
        // } while (answer.equalsIgnoreCase("ano"));

        return raceByUser;
    }

    public static Racer addRacerByUser() {
        System.out.print("Zadejte jméno závodníka: ");
        String name = InputCheck.nameCheckFormat(sc.nextLine());
        System.out.print("Zadejte příjmení závodníka: ");
        String surname = InputCheck.surnameCheckFormat(sc.nextLine());
        System.out.print("Zadejte zkratku národnosti: ");
        Nationality nationality = Nationality.valueOf(sc.nextLine().toUpperCase());
        Racer r = new Racer(name, surname, nationality);
        System.out.println(r);
        return r;
    }

    public static ArrayList<Racer> findRacers(Race race, String surname) {
        ArrayList<Racer> foundRacers = race.getRacer(surname);
        if (foundRacers.size() == 0) {
            throw new IllegalArgumentException("Závodníka se zadaným příjmením nelze najít!");
        } else {
            // for (Racer racer : foundRacers) {
            // System.out.println(racer);
            // }
            return foundRacers;
        }
    }

    public static Racer findRacer(Race race, String surname) {
        ArrayList<Racer> foundRacers = race.getRacer(surname);
        if (foundRacers.size() == 0) {
            throw new IllegalArgumentException("Závodníka se zadaným příjmením nelze najít!");
        } else {
            // for (Racer racer : foundRacers) {
            // System.out.println(racer);
            // }
            return foundRacers.get(0);
        }
    }

    public static boolean showRacerDetail(Race race) {
        System.out.println("Chceš zobrazit detail jezdce? [a/n]");
        String answer = sc.nextLine().toLowerCase();
        return answer.equals("a");
    }
}
