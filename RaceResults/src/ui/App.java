package ui;
//use j2html

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import app.ComparatorRacerBySurname;
import app.Nationality;
import app.Race;
import app.Racer;
import utils.FileExplorer;
import utils.RaceUtils;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private final ArrayList<Racer> racerStats = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException, IOException {
        App app = new App();
        app.start();
    }

    private void start() throws IOException {
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
                        try {
                            loadResults(race);
                        } catch (NoSuchFieldError e) {
                            System.out.println(e.getLocalizedMessage());
                        }
                        break;
                    case "2":
                        Race raceByUser = RaceUtils.createNewRace();
                        boolean exitNewRaceMenu = false;
                        while (!exitNewRaceMenu) {
                            showNewRaceMenu();
                            switch (sc.nextLine().toLowerCase()) {
                                case "1":
                                    // add racer
                                    try {
                                        raceByUser.addRacer(RaceUtils.addRacerByUser());
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case "2":
                                    // edit racer
                                    showNewRaceEditRacerMenu();
                                    break;
                                case "3":
                                    // delete racer
                                    System.out.print("Zadejte příjmení závodníka: ");
                                    String surname = sc.nextLine();
                                    raceByUser.deleteRacer(RaceUtils.findRacer(raceByUser, surname));
                                    System.out.println("Závodník smazán.");
                                    break;
                                case "q":
                                    exitNewRaceMenu = true;
                                    break;
                                default:
                                    System.out.println("Neplatná volba! ");
                                    break;
                            }
                        }
                        System.out.print("Přejete si závod uložit? [ano/ne]: ");
                        String save = sc.nextLine();
                        if (save.equalsIgnoreCase("ano")) {
                            RaceUtils.saveRace(raceByUser, raceByUser.getCircuitName() + raceByUser.getSeasonYear());
                        }
                        break;
                    case "3":
                        // TODO: change values of RACER
                        if (RaceUtils.areRacersLoaded(race)) {
                            System.out.println("Nejdřív musíte načíst závod!");
                            break;
                        }
                        System.out.print("Zadej příjmení závodníka: ");
                        String surname = sc.nextLine();
                        RaceUtils.findRacers(race, surname);
                        break;
                    case "4":
                        showRacerInterDatabase();
                        break;
                    case "5":
                        if (RaceUtils.areRacersLoaded(race)) {
                            System.out.println("Nejdřív musíte načíst závod!");
                            break;
                        } else {
                            RaceUtils.saveRace(race, race.getCircuitName() + race.getSeasonYear());
                        }
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

    public static void showNewRaceEditRacerMenu() {
        // TODO: edit values ...
    }

    public static void showNewRaceMenu() {
        System.out.println("1 ...přidat závodníka");
        System.out.println("2 ...upravit závodníka");
        System.out.println("3 ...smazat závodníka");
        System.out.println("q ...konec");
        System.out.print("Vyberte jednu z možností: ");
    }

    private ArrayList<Racer> initialization() throws FileNotFoundException, IOException {
        String line;
        String[] parts;
        Racer r;
        String parentPath = System.getProperty("user.dir") + File.separator + "data" + File.separator
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
        return racerStats;

    }

    public static void loadResults(Race race) throws FileNotFoundException {
        String userAnswer = sc.nextLine();
        try {
            File file = new File(
                    System.getProperty("user.dir") + File.separator + "Data" + File.separator
                            + userAnswer);
            race.loadStats(file);
            System.out.println(race);
            if (RaceUtils.showRacerDetail(race)) {
                System.out.print("Zadej příjmení jezdce: ");
                String surname = sc.nextLine();
                System.out.println(
                        String.format("%-8s %-10s %-10s %-15s %-30s %-10s %-15s %-2s %s", "Umístění",
                                "Jméno",
                                "Příjmení", "Národnost", "Tým", "Motocykl", "Startovní číslo",
                                "Max. rychlost", "Čas"));
                RaceUtils.findRacers(race, surname);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void showMainMenu() {
        // clear console space
        System.out.println("\n".repeat(5));
        System.out.println("1 ...zobrazit výsledky závodu");
        System.out.println("2 ...zahájit nový závod");
        System.out.println("3 ...upravit závodníka");
        System.out.println("4 ...vypsat detail závodníků");
        System.out.println("5 ...uložit načtené závodníky");
        System.out.println("q ...konec");
        System.out.print("Vyberte jednu z možností: ");
    }

    public static void showRaceMenu() {
        String actualDir = System.getProperty("user.dir") + File.separator + "Data";
        File file = new File(actualDir);
        System.out.println(FileExplorer.showPathFile(file, 0));
        System.out.print("Vyberte soubor se závody: ");
    }

    public void showRacerInterDatabase() {
        ArrayList<Racer> copy = new ArrayList<>();
        for (Racer racer : this.racerStats) {
            copy.add(new Racer(racer));
        }
        Comparator<Racer> cbs = new ComparatorRacerBySurname();
        Collections.sort(copy, cbs);
        // rider_name;rider_surname;team_name;bike_name;points;number;country
        System.out.println(String.format("%-10s %-10s %-15s %-30s %-10s %-10s %s",
                " Jméno",
                "Příjmení", "Národnost", "Tým", "Motocykl", "Počet bodů", "Startovní číslo"));
        for (Racer racer : copy) {
            System.out.println(racer.toInternalDatabaseFormat());
        }
    }

}
