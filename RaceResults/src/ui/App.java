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
import utils.InputCheck;
import utils.RaceUtils;

public class App {
    private static Scanner sc = new Scanner(System.in);
    // racerStats ...internal database updated on initialization
    private ArrayList<Racer> racerStats = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException, IOException {

        App app = new App();
        app.start();
    }

    private void start() throws IOException {
        try {
            racerStats = initialization();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String title = "Vítejte v prohlížení závodních výsledků";
        System.out.println(title);
        // = podle počtu písmen v nadpise :))
        System.out.println("=".repeat(title.length()));
        Race race = new Race();

        String choice;
        boolean end = false;
        while (!end) {
            try {
                showMainMenu();
                // vstup ošetřen switchem -> default
                choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        FileExplorer.showRaceMenu();
                        loadResults(race);
                        break;

                    case "2":
                        // create new race
                        // Race raceByUser = new Race();

                        Race raceByUser = RaceUtils.createNewRace();

                        boolean exitNewRaceMenu = false;
                        while (!exitNewRaceMenu) {
                            showNewRaceMenu();
                            // input checked by "default" in switch
                            switch (sc.nextLine().toLowerCase()) {
                                case "1":
                                    // add racer
                                    raceByUser.addRacer(RaceUtils.addRacerByUser());
                                    break;
                                case "2":
                                    // edit racer
                                    if (!raceByUser.getRacers().isEmpty()) {
                                        showNewRaceEditRacerMenu();
                                    }
                                    System.out.println("Nejdříve musíte přidat závodníka!");
                                    break;
                                case "3":
                                    // delete racer
                                    if (!raceByUser.getRacers().isEmpty()) {
                                        System.out.print("Zadejte příjmení závodníka: ");
                                        String surname = sc.nextLine();
                                        raceByUser.deleteRacer(RaceUtils.findRacer(raceByUser, surname));
                                        System.out.println("Závodník smazán.");
                                    }
                                    System.out.println("Nejdříve musíte přidat závodníka!");

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
                        if (RaceUtils.areRacersEmpty(race)) {
                            System.out.println("Nejdřív musíte načíst závod!");
                            break;
                        }
                        System.out.print("Zadej příjmení závodníka: ");
                        String surname = sc.nextLine();
                        try {
                            System.out.println(RaceUtils.findRacers(race, surname));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "4":
                        showRacerInterDatabase();
                        break;
                    case "5":
                        if (RaceUtils.areRacersEmpty(race)) {
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

            } catch (IllegalArgumentException | FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void showNewRaceEditRacerMenu() {
        // TODO: edit values ...
        System.out.println("1 ...změnit tým závodníka");
        System.out.println("2 ...změnit motocykl závodníka");
        System.out.println("3 ...přidat body závodníkovy");
        System.out.println("4 ...");
        System.out.println("5 ...");
        System.out.println("6 ...");
    }

    public static void showNewRaceMenu() {
        System.out.println("-".repeat(30));
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
                // check if riders has set points
                if (!parts[4].equals("")) {
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
                // moc unavený na kontrolu...
                if (InputCheck.checkChoiceFormatBoolean(surname)) {
                    RaceUtils.findRacers(race, surname);
                    System.out.println(
                            String.format("%-8s %-10s %-10s %-15s %-30s %-10s %-15s %-2s %s", "Umístění",
                                    "Jméno",
                                    "Příjmení", "Národnost", "Tým", "Motocykl", "Startovní číslo",
                                    "Max. rychlost", "Čas"));
                }
                System.out.println("Závodník nebyl nalezen.");

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void showMainMenu() {
        // clear console space
        System.out.println("\n");
        System.out.println("-".repeat(30));
        System.out.println("1 ...zobrazit výsledky závodu");
        System.out.println("2 ...zahájit nový závod");
        System.out.println("3 ...upravit závodníka");
        System.out.println("4 ...vypsat detail závodníků");
        System.out.println("5 ...uložit načtené závodníky");
        System.out.println("q ...konec");
        System.out.print("Vyberte jednu z možností: ");
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
