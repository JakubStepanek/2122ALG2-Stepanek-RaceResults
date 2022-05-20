package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import app.Race;
import utils.FileExplorer;

public class Menu {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner sc = new Scanner(System.in);
        char choice;
        boolean end = false;
        while (end == false) {
            showMainMenu();
            switch ((choice = sc.next().charAt(0))) {
                case '1':
                    showRaceMenu();
                    String userAnswer = sc.next();
                    System.out.println(userAnswer);
                    Race race = new Race();
                    try {
                        File file = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator
                                + "Data" + File.separator + userAnswer);
                        System.out.println(file);
                        race.loadStats(file);
                        System.out.println(race);
                    } catch (Exception e) {
                        throw new FileNotFoundException("Neplatný název souboru!");
                    }

                    // TODO: executeMenu()

                    // read race from file
                    // show race result
                    break;
                case '2':
                    // add race
                    // add riders
                    // save to file
                    break;
                case '3':
                    // TODO:
                    break;
                case 'q':
                    System.out.println("Ukončuji...");
                    end = true;
                    break;
                default:
                    System.out.println("neplatná volba");
                    break;
            }
        }
    }

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
        String actualDir = System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "Data";
        File file = new File(actualDir);
        System.out.println(FileExplorer.showPathFile(file, 0));
        System.out.print("Vyberte soubor se závody: ");
        // read races from file
        // ask for selection
    }

}
