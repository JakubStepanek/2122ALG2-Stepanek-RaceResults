package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import app.Race;
import utils.FileExplorer;

public class App {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        try (Scanner sc = new Scanner(System.in)) {
            String choice;
            boolean end = false;
            while (!end) {
                showMainMenu();
                choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        // raceMenu()
                        showRaceMenu();
                        String userAnswer = sc.nextLine();
                        Race race = new Race();
                        try {
                            File file = new File(
                                    System.getProperty("user.dir") + File.separator + "Data" + File.separator
                                            + userAnswer);
                            System.out.println(file);
                            race.loadStats(file);
                            System.out.println(race);
                        } catch (Exception e) {
                            throw new FileNotFoundException("Neplatný název souboru!");
                        }
                        System.out.println("Chceš zobrazit detail jezdce? [a/n]");
                        String answer = sc.nextLine().toLowerCase();
                        if (answer.equals("a")) {
                            System.out.print("Zadej jméno jezdce: ");
                            String surname = sc.nextLine();
                            System.out.println(race.showDetailOfRacer(surname));
                        }
                        break;
                    case "2":
                        break;
                    case "3":
                        // TODO:
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
        }
    }

    // public static void showRacerSelectionMenu() {
    // System.out.println("1 ...zobrazit detail závodníka");
    // System.out.println("q ...konec");
    // }

    public static void showMainMenu() {
        // clear console space
        System.out.println("\n".repeat(7));
        System.out.println("Vítejte v prohlížení závodních výsledků");
        // = podle počtu písmen v nadpise :))
        System.out.println("=".repeat(39));
        System.out.println("1 ...Zobrazit výsledky závodu");
        System.out.println("2 ...Zahájit nový závod");
        System.out.println("3 ...Prohlížet staré sezóny");
        System.out.println("q ...Konec");
        System.out.print("Vyberte jednu z možností: ");
    }

    public static void showRaceMenu() {
        String actualDir = System.getProperty("user.dir") + File.separator + "Data";
        File file = new File(actualDir);
        System.out.println(FileExplorer.showPathFile(file, 0));
        System.out.print("Vyberte soubor se závody: ");
    }

}
