package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import app.Circuit;
import app.Nationality;
import app.Race;
import app.Racer;

/**
 * @author JakubStepanek
 */
public final class RaceUtils {
    private static Scanner sc = new Scanner(System.in);
    private static FileUtils fileUtils = new FileUtils();

    /**
     * private constructor
     */
    private RaceUtils() {

    }

    /**
     * Method to start new Race
     */
    public static void startNewRace() {
        Race raceByUser = new Race();
        try {
            raceByUser = RaceUtils.createNewRace();
        } catch (InputMismatchException e) {
            // clear buffer
            sc.nextLine();
            throw new InputMismatchException("Sezóna se zadává rokem");
        }
        boolean exitNewRaceMenu = false;
        while (!exitNewRaceMenu) {
            try {
                MenuUtils.showNewRaceMenu();
                // input checked by "default" in switch
                switch (sc.nextLine().toLowerCase()) {
                    case "1":
                        // add racer
                        raceByUser.addRacer(RaceUtils.addRacerByUser());
                        break;
                    case "2":
                        // edit racer
                        if (!raceByUser.getRacers().isEmpty()) {
                            RacerUtils.changeRacerAppearance(raceByUser);
                        } else {
                            System.out.println("Nejdříve musíte přidat závodníka!");
                        }
                        break;
                    case "3":
                        // delete racer
                        if (!raceByUser.getRacers().isEmpty()) {
                            raceByUser.deleteRacer(RaceUtils.findRacer(raceByUser));
                            System.out.println("Závodník smazán.");

                        } else {
                            System.out.println("Nejdříve musíte přidat závodníka!");
                        }

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
                fileUtils.saveRace(raceByUser, (raceByUser.getCircuitName() + raceByUser.getSeasonYear()));

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * Function checks if Racers in Race are empty
     *
     * @param race
     * @return
     */
    public static boolean areRacersEmpty(Race race) {
        return race.getRacers().isEmpty();
    }

    /**
     * Function creates new Race()
     * 
     * @return
     * @throws IllegalArgumentException
     */
    public static Race createNewRace() throws IllegalArgumentException {
        // TODO: add new racer to riders.csv
        Race raceByUser = new Race();
        System.out.print("Zadejte sezónu, kdy se jel závod: ");
        raceByUser.setSeasonYear(InputCheckUtils.checkSeasonYear(sc.nextInt()));
        // buffer clear
        sc.nextLine();
        System.out.print("Zadejte název okruhu: ");
        raceByUser.setCircuitName(Circuit.of(sc.nextLine()));
        return raceByUser;
    }

    /**
     * Function to add Racer by user in Race
     * 
     * @return
     */
    public static Racer addRacerByUser() {
        System.out.print("Zadejte jméno závodníka: ");
        String name = InputCheckUtils.nameCheckFormat(sc.nextLine());
        System.out.print("Zadejte příjmení závodníka: ");
        String surname = InputCheckUtils.surnameCheckFormat(sc.nextLine());
        System.out.print("Zadejte zkratku národnosti: ");
        Nationality nationality = Nationality.valueOf(sc.nextLine().toUpperCase());
        Racer r = new Racer(name, surname, nationality);
        System.out.println(r);
        return r;
    }

    /**
     * Function to find Racer in Race by surname
     * 
     * @param race
     * @param surname
     * @return
     */
    public static List<Racer> findRacers(Race race, String surname) {
        ArrayList<Racer> foundRacers = (ArrayList<Racer>) race.getRacer(surname);
        if (foundRacers.isEmpty()) {
            throw new IllegalArgumentException("Závodníka se zadaným příjmením nelze najít!");
        }
        // return all found racers
        return foundRacers;

    }

    /**
     * Function to find Racer in Race by Surname
     * 
     * @param race
     * @return
     */
    public static Racer findRacer(Race race) {
        System.out.print("Zadejte příjmení hledaného závodníka: ");
        ArrayList<Racer> foundRacers = (ArrayList<Racer>) race
                .getRacer(InputCheckUtils.surnameCheckFormat(sc.nextLine()));
        if (foundRacers.isEmpty()) {
            throw new IllegalArgumentException("Závodníka se zadaným příjmením nelze najít!");
        }
        // return first found racer
        return foundRacers.get(0);

    }

    /**
     * Funtion asks user if he wants to show detail of Racer
     * 
     * @return
     */
    public static boolean showRacerDetail() {
        System.out.println("Chceš zobrazit detail jezdce? [a/n]");
        return sc.nextLine().equalsIgnoreCase("a");
    }
}
