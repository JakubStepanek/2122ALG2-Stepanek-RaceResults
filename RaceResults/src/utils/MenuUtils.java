package utils;

/**
 * @author JakubStepanek
 */

public final class MenuUtils {
    /**
     * public constructor
     */
    public MenuUtils() {
    }

    /**
     * Method that shows Menu
     */
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

    /**
     * Method that shows Menu
     */
    public static void showNewRaceMenu() {
        System.out.println("-".repeat(30));
        System.out.println("1 ...přidat závodníka");
        System.out.println("2 ...upravit závodníka");
        System.out.println("3 ...smazat závodníka");
        System.out.println("q ...konec");
        System.out.print("Vyberte jednu z možností: ");
    }

    /**
     * Method that shows Menu
     */
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

}
