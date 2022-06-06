package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

import app.Race;
import app.Racer;

/**
 * @author JakubStepanek
 */
public final class RacerUtils {

    private static Scanner sc = new Scanner(System.in);

    /**
     * private constructor
     */
    private RacerUtils() {
    }

    /**
     * Method to change appearance of Racer
     *
     * @param race
     */
    public static void changeRacerAppearance(Race race) {
        boolean end = false;
        Racer foundRacer = RaceUtils.findRacer(race);
        while (!end) {
            MenuUtils.showRacerChangeMenu();
            String choice = sc.nextLine();
            try {
                switch (choice) {
                    case "1" -> {
                        System.out.print("Zadejte místo, kde se závodník umístil: ");
                        foundRacer.setPosition(sc.nextInt());
                        // clear buffer
                        sc.nextLine();
                    }
                    case "2" -> {
                        System.out.print("Zadejte čas, kdy projel závodník cílem [XX:XX:XX]: ");
                        foundRacer.setRaceTime(sc.nextLine());
                    }
                    case "3" -> {
                        System.out.print("Zadejte název týmu: ");
                        foundRacer.setTeam(sc.nextLine());
                    }
                    case "4" -> {
                        System.out.print("Zadejte továrnu motocyklu: ");
                        foundRacer.setBike(sc.nextLine());
                    }
                    case "5" -> {
                        System.out.print("Zadejte startovní číslo závodníka: ");
                        foundRacer.setRacingNumber(sc.nextInt());
                        // clear buffer
                        sc.nextLine();
                    }
                    case "6" -> {
                        System.out.print("Zadejte maximální rychlost [xxx,x]: ");
                        foundRacer.setMaxSpeed(sc.nextDouble());
                        // clear buffer
                        sc.nextLine();
                    }
                    case "q" ->
                        end = true;
                    default ->
                        System.out.println("Neplatná volba");
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
