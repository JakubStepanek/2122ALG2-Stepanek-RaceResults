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
import java.util.InputMismatchException;
import java.util.Scanner;

import app.ComparatorRacerBySurname;
import app.Nationality;
import app.Race;
import app.Racer;
import utils.BinaryFileUtils;
import utils.FileExplorerUtils;
import utils.FileUtils;
import utils.J2HTML;
import utils.MenuUtils;
import utils.RaceUtils;
import utils.RacerUtils;

/**
 * @author JakubStepanek
 */
public class App {

    private static Scanner sc = new Scanner(System.in);
    // racerStats ...internal database updated on initialization
    private ArrayList<Racer> racerStats = new ArrayList<>();
    private BinaryFileUtils binaryFileUtils = new BinaryFileUtils();
    private FileUtils fileUtils = new FileUtils();

    public static void main(String[] args) throws FileNotFoundException, IOException {
        App app = new App();
        app.start();
    }

    /**
     * Method to start running application
     *
     * @throws IOException
     */
    private void start() throws IOException {
        File result;
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
        String dataPath = System.getProperty("user.dir") + File.separator + "data" + File.separator;

        while (!end) {
            try {
                MenuUtils.showMainMenu();
                // vstup ošetřen switchem => default
                choice = sc.nextLine();
                switch (choice) {
                    case "1" -> {
                        FileExplorerUtils.showRaceMenu();
                        String fileToLoad = sc.nextLine();
                        String path = dataPath + fileToLoad;
                        result = new File(path);
                        fileUtils.loadToRace(result, race);
                        System.out.println(race);
                    }
                    case "2" -> // create new race
                        RaceUtils.startNewRace();
                    case "3" -> {
                        if (RaceUtils.areRacersEmpty(race)) {
                            System.out.println("Nejdřív musíte načíst závod!");
                            break;
                        }
                        RacerUtils.changeRacerAppearance(race);
                    }
                    case "4" -> showRacerInterDatabase();
                    case "5" -> {
                        if (RaceUtils.areRacersEmpty(race)) {
                            System.out.println("Nejdřív musíte načíst závod!");
                            break;
                        } else {
                            fileUtils.saveRace(race, race.getCircuitName() + race.getSeasonYear());
                        }
                    }
                    case "6" -> {
                        result = new File(dataPath + "racersBinary.dat");
                        binaryFileUtils.save(result, race);
                    }
                    case "7" -> {
                        result = new File(dataPath + "racersBinary.dat");
                        System.out.println(binaryFileUtils.load(result, race));
                    }
                    case "8" -> fileUtils.save(J2HTML.saveHTML(race), new File(dataPath + "index.html"));
                    case "q" -> {
                        System.out.println("Ukončuji...");
                        end = true;
                    }
                    default -> System.out.println("neplatná volba");
                }

            } catch (IllegalArgumentException | FileNotFoundException | InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Function which loads racers from internal database
     *
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    private ArrayList<Racer> initialization() throws FileNotFoundException, IOException {
        String line;
        String[] parts;
        Racer r;
        String parentPath = System.getProperty("user.dir") + File.separator + "data" + File.separator
                + "riders.csv";
        File dataDirectory = new File(parentPath);
        try ( BufferedReader br = new BufferedReader(new FileReader(dataDirectory))) {
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

    /**
     * Method to show Racers from Internal database
     */
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
