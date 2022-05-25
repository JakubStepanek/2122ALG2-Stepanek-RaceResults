package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import app.Circuit;
import app.ComparatorRacerBySurname;
import app.Nationality;
import app.Race;
import app.Racer;
import utils.FileExplorer;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private final ArrayList<Racer> racerStats = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException, IOException {
        App app = new App();
        app.start();
    }

    private void start() {
        try {
            initialization();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String title = "Vítejte v prohlížení závodních výsledků";
        System.out.println(title);
        // = podle počtu písmen v nadpise :))
        System.out.println("=".repeat(title.length()));
        Race race = new Race();
        try {
            String choice;
            boolean end = false;
            while (!end) {
                showMainMenu();
                choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        showRaceMenu();
                        loadResults(race);
                        addPointsToRacerStats();
                        break;
                    case "2":
                        createNewRace();
                        break;
                    case "3":
                        if (race.getRacers().size() == 0) {
                            System.out.println("Nejdřív musíte načíst závod");
                            break;
                        }
                        System.out.print("Zadej příjmení závodníka: ");
                        String surname = sc.nextLine();
                        findRacer(race, surname);
                        break;
                    case "4":
                        showRacerDatabase();
                        break;
                    case "q":
                        System.out.println("Ukončuji...");
                        end = true;
                        break;
                    default:
                        System.out.println("neplatná volba");
                        break;
                }
            }

        } catch (IllegalArgumentException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void initialization() throws FileNotFoundException, IOException {
        String line;
        String[] parts;
        Racer r;
        String parentPath = System.getProperty("user.dir") + File.separator + "Data" + File.separator
                + "riders.csv";
        File dataDirectory = new File(parentPath);
        try (BufferedReader br = new BufferedReader(new FileReader(dataDirectory))) {
            // skip header
            br.readLine();
            while ((line = br.readLine()) != null) {
                parts = line.split(";");
                r = new Racer(parts[1], parts[0], Nationality.valueOf(parts[6]));
                r.setTeam(parts[2]);
                r.setBike(parts[3]);
                if (parts[4] != "") {
                    r.setPoints(Integer.parseInt(parts[4]));
                }
                r.setRacingNumber(parts[5]);
                racerStats.add(r);
            }
        }

    }

    public static void createNewRace() {
        // TODO: add new racer to riders.csv
        Race raceByUser = new Race();
        System.out.print("Zadejte sezónu, kdy se jel závod: ");
        raceByUser.setSeasonYear(sc.nextInt());
        System.out.print("Zadejte název okruhu: ");
        sc.nextLine();
        raceByUser.setCircuitName(Circuit.of(sc.nextLine()));
        String answer;
        do {// TODO: add racers to raceByUser
            System.out.println("Přejete si přidat závodníka?");
            answer = sc.nextLine().toLowerCase();
            if (answer.equals("a")) {
                raceByUser.addRacer(addRacerByUser());
            }
        } while (answer.equals("a"));

        System.out.println(raceByUser);
    }

    public static Racer addRacerByUser() {
        System.out.println("Zadejte jméno závodníka");
        String name = sc.nextLine();
        System.out.println("Zadejte příjmení závodníka");
        String surname = sc.nextLine();
        Nationality nationality = Nationality.valueOf(sc.nextLine());
        Racer r = new Racer(name, surname, nationality);
        return r;
    }

    public static void loadResults(Race race) throws FileNotFoundException {
        String userAnswer = sc.nextLine();
        try {
            File file = new File(
                    System.getProperty("user.dir") + File.separator + "Data" + File.separator
                            + userAnswer);
            race.loadStats(file);
            System.out.println(race);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (showRacerDetail(race)) {
            System.out.print("Zadej příjmení jezdce: ");
            String surname = sc.nextLine();
            System.out.println(
                    String.format("%-8s %-10s %-10s %-15s %-30s %-10s %-15s %-2s %s", "Umístění",
                            "Jméno",
                            "Příjmení", "Národnost", "Tým", "Motocykl", "Startovní číslo",
                            "Max. rychlost", "Čas"));
            findRacer(race, surname);
        }

    }

    // TODO: add points to racers in internal DB
    public void addPointsToRacerStats() {

    }

    public static void findRacer(Race race, String surname) {
        List<Racer> foundRacers = race.getRacer(surname);
        if (foundRacers.size() == 0) {
            System.err.println("Závodníka s hledaným příjmením nelze najít!");
        } else {
            for (Racer racer : foundRacers) {
                System.out.println(racer);
            }
        }
    }

    public static boolean showRacerDetail(Race race) {
        System.out.println("Chceš zobrazit detail jezdce? [a/n]");
        String answer = sc.nextLine().toLowerCase();
        return answer.equals("a");
    }

    public static void showMainMenu() {
        // clear console space
        System.out.println("\n".repeat(5));
        System.out.println("1 ...Zobrazit výsledky závodu");
        System.out.println("2 ...Zahájit nový závod");
        System.out.println("3 ...Upravit závodníka");
        System.out.println("4 ...Vypsat detail závodníků");
        System.out.println("q ...Konec");
        System.out.print("Vyberte jednu z možností: ");
    }

    public static void showRaceMenu() {
        String actualDir = System.getProperty("user.dir") + File.separator + "Data";
        File file = new File(actualDir);
        System.out.println(FileExplorer.showPathFile(file, 0));
        System.out.print("Vyberte soubor se závody: ");
    }

    public void showRacerDatabase() {
        ArrayList<Racer> copy = new ArrayList<>();
        for (Racer racer : this.racerStats) {
            copy.add(new Racer(racer));
        }
        Comparator<Racer> cbs = new ComparatorRacerBySurname();
        Collections.sort(copy, cbs);
        for (Racer racer : copy) {
            System.out.println(racer);
        }
    }

}
