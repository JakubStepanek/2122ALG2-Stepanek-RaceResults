package utils;

import java.time.*;

import app.Nationality;

public class InputCheck {
    private static final int START_OF_RACING = 1949;

    public static String nameCheckFormat(String name) {
        if (!name.matches("^[A-Ž][a-ž]+$")) { // ukazka pouziti regularniho vyrazu na test validity
            throw new IllegalArgumentException("Nevalidní jméno závodníka!");
        }
        return name;
    }

    public static String surnameCheckFormat(String surname) {
        if (!surname.matches("^[A-Ž][a-ž]+$")) { // ukazka pouziti regularniho vyrazu na test validity
            throw new IllegalArgumentException("Nevalidní příjmení závodníka!");
        }
        return surname;
    }

    public static String checkChoiceFormat(String choice) {
        if (!choice.equalsIgnoreCase("ano") || !choice.equalsIgnoreCase("ne")) {
            throw new IllegalArgumentException("Neplatný vstup");
        }
        return choice;
    }

    public static boolean checkChoiceFormatBoolean(String choice) {
        return (choice.equalsIgnoreCase("ano") || choice.equalsIgnoreCase("ne"));
    }

    public static String checkNationality(String nationality) {
        // go through Nationality and check
        Nationality.values();
        return null;
    }

    public static String checkCircuitValues(String circuit) {
        
        // go through Circuit and check
        return null;
    }

    public static boolean inputIsNumber() {
        // TODO: check if input is number
        return false;
    }

    public static boolean inputIsString() {
        // TODO: check if input is string
        return false;
    }

    public static int checkSeasonYear(int year) {
        int currentYear = Year.from(LocalDate.now()).getValue();

        if (!(START_OF_RACING < year && year <= currentYear)) {
            throw new IllegalArgumentException("Rok nesedí: 1949 < zadaný rok ≤ " + currentYear);
        }
        return year;
    }

    public static void main(String[] args) {

    }
}
