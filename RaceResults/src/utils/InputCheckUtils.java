package utils;

import java.time.*;

/**
 * @author JakubStepanek
 */
public final class InputCheckUtils {
    private static final int START_OF_RACING = 1949;
    private static final int CURRENT_YEAR = Year.from(LocalDate.now()).getValue();

    /**
     * private constructor
     */
    private InputCheckUtils() {

    }

    /**
     * Function to check correct form of input name
     * 
     * @param name
     * @return
     */
    public static String nameCheckFormat(String name) {
        if (!name.matches("^[A-Ž][a-ž]+$")) { // ukazka pouziti regularniho vyrazu na test validity
            throw new IllegalArgumentException("Nevalidní jméno závodníka!");
        }
        return name;
    }

    /**
     * Function to check correct form of input surname
     * 
     * @param surname
     * @return
     */
    public static String surnameCheckFormat(String surname) {
        if (!surname.matches("^[A-Ž][a-ž]+$")) { // ukazka pouziti regularniho vyrazu na test validity
            throw new IllegalArgumentException("Nevalidní příjmení závodníka!");
        }
        return surname;
    }

    /**
     * Function checks if input choice equals "ano" if not throws Exception
     * 
     * @param choice
     * @return
     */
    public static String checkChoiceFormat(String choice) {
        if (!choice.equalsIgnoreCase("ano") || !choice.equalsIgnoreCase("ne")) {
            throw new IllegalArgumentException("Neplatný vstup");
        } else {
            return choice;
        }
    }

    /**
     * Function choice if format equals "ano" or "ne" then return boolean
     * 
     * @param choice
     * @return
     */
    public static boolean checkChoiceFormatBoolean(String choice) {
        boolean formatCheck = (!choice.equalsIgnoreCase("ano") || !choice.equalsIgnoreCase("ne"));
        if (formatCheck) {
            throw new IllegalArgumentException("Neplatný vstup");
        } else {
            return formatCheck;
        }
    }

    /**
     * Fuction checks correct date of Season
     * 
     * @param year
     * @return
     */
    public static int checkSeasonYear(int year) {
        if (!(START_OF_RACING < year && year <= CURRENT_YEAR)) {
            throw new IllegalArgumentException("Rok nesedí: 1949 < zadaný rok ≤ " + CURRENT_YEAR);
        }
        return year;
    }

    /**
     * Function returns boolean if season year is between correct interval
     * 
     * @param year
     * @return
     */
    public static boolean checkSeasonYearBoolean(int year) {
        return (START_OF_RACING < year && year <= CURRENT_YEAR);
    }
}
