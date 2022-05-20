package ui;

import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char choice;
        boolean end = false;
        while (end == false) {
            showMainMenu();
            switch ((choice = sc.next().charAt(0))) {
                case '1':
                    System.out.println("youdoyou");
                    break;
                case '2':
                    System.out.println("heh");
                    break;
                case '3':
                    System.out.println("prohlížej");
                    break;
                case 'q':
                    end = true;
                    break;
                default:
                    System.out.println("neplatná volba");
                    break;
            }
        }
    }

    public static void showMainMenu() {
        System.out.println("Vítejte v prohlížení závodních výsledků");
        System.out.println("1 -Zobrazit výsledky sezóny");
        System.out.println("2 -Zahájit novou sezónu");
        System.out.println("3 -Prohlížet staré sezóny");
        System.out.println("q -Konec");

        System.out.print("Vyberte jednu z možností:");

    }

}
