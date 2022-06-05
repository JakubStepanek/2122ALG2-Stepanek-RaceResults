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

import app.BinaryFile;
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

        // = podle počtu písmen v nadpise :))
        System.out.println("\n" + "=".repeat(title.length()));
        System.out.println(title);
        // = podle počtu písmen v nadpise :))
        System.out.println("=".repeat(title.length()));
        Race race = new Race();

        String choice;
        boolean end = false;
        while (!end) {
            try {
                showMainMenu();
                // vstup ošetřen switchem => default
                choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        FileExplorer.showRaceMenu();
                        loadResults(race);
                        break;
                    case "2":
                        // create new race
                        createNewRace();
                        break;
                    case "3":
                        if (RaceUtils.areRacersEmpty(race)) {
                            System.out.println("Nejdřív musíte načíst závod!");
                            break;
                        }
                        changeRacerArappearance(race);
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
                    case "6":
                        BinaryFile bf = new BinaryFile();
                        File result = new File("racersBinary.bat");
                        bf.save(result, race);
                        break;
                    case "7":
                        BinaryFile bf1 = new BinaryFile();
                        File result1 = new File("racersBinary.bat");
                        System.out.println(bf1.load(result1));
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

    private static void changeRacerArappearance(Race race) {
        boolean end = false;
        Racer foundRacer = RaceUtils.findRacer(race);
        while (!end) {
            showRacerChangeMenu();
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("Zadejte místo, kde se závodník umístil: ");
                    try {
                        foundRacer.setPosition(sc.nextInt());
                    } catch (NumberFormatException e) {
                        System.out.println(e.getMessage());
                    }
                    // clear buffer
                    sc.nextLine();
                    break;
                case "2":
                    System.out.print("Zadejte čas, kdy projel závodník cílem [XX:XX:XX]: ");
                    foundRacer.setRaceTime(sc.nextLine());
                    break;
                case "3":
                    System.out.print("Zadejte název týmu: ");
                    foundRacer.setTeam(sc.nextLine());
                    break;
                case "4":
                    System.out.print("Zadejte továrnu motocyklu: ");
                    foundRacer.setBike(sc.nextLine());
                    break;
                case "5":
                    System.out.print("Zadejte startovní číslo závodníka: ");
                    foundRacer.setRacingNumber(sc.nextInt());
                    // clear buffer
                    sc.nextLine();
                    break;
                case "6":
                    System.out.print("Zadejte maximální rychlost [xxx,x]: ");
                    foundRacer.setMaxSpeed(sc.nextDouble());
                    // clear buffer
                    sc.nextLine();
                    break;
                case "q":
                    end = true;
                    break;

                default:
                    System.out.println("Neplatná volba");
                    break;
            }
        }

    }

    public static void showRacerChangeMenu() {
        System.out.println("-".repeat(30));
        System.out.println("1 ...umístění");
        System.out.println("2 ...čas v cíli");
        System.out.println("3 ...tým");
        System.out.println("4 ...motocykl");
        System.out.println("5 ...startovní číslo");
        System.out.println("6 ...maximální rychlost");
        System.out.println("q ...konec");
        System.out.print("Vyberte, co si přejete změnit: ");

    }

    public static void createNewRace() {
        Race raceByUser = RaceUtils.createNewRace();

        boolean exitNewRaceMenu = false;
        while (!exitNewRaceMenu) {
            try {
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
                            changeRacerArappearance(raceByUser);
                        }
                        System.out.println("Nejdříve musíte přidat závodníka!");
                        break;
                    case "3":
                        // delete racer
                        if (!raceByUser.getRacers().isEmpty()) {
                            raceByUser.deleteRacer(RaceUtils.findRacer(raceByUser));
                            System.out.println("Závodník smazán.");
                            break;
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
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.print("Přejete si závod uložit? [ano/ne]: ");
        String save = sc.nextLine();
        if (save.equalsIgnoreCase("ano")) {
            try {
                RaceUtils.saveRace(raceByUser, raceByUser.getCircuitName() + raceByUser.getSeasonYear());

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

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
                r.setRacingNumber(Integer.parseInt(parts[5]));
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
            if (RaceUtils.showRacerDetail()) {
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
        System.out.println("3 ...upravit v načteném závodě závodníka");
        System.out.println("4 ...vypsat detail závodníků");
        System.out.println("5 ...uložit načtené závodníky");
        System.out.println("6 ...uložit načtené závodníky do binárního souboru");
        System.out.println("7 ...načíst závodníky z binárního souboru");

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
