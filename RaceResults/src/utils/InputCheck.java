package utils;

import java.util.zip.CheckedOutputStream;

import app.Nationality;

public class InputCheck {
    public static String nameCheckFormat(String name) {
        // mask ^[A-Z\\s][a-z]+
        return "false";
    }

    public static String surnameCheckFormat(String surname) {
        // mask ^[A-Z\\s][a-z]+
        return "false";
    }

    public static String checkChoiceFormat(String choice) {
        if (choice.equalsIgnoreCase("ano") || choice.equalsIgnoreCase("ne")) {
            return choice;
        } else {
            throw new IllegalArgumentException("Neplatný vstup");
        }
    }
    public static boolean checkChoiceFormatBoolean(String choice) {
        return (choice.equalsIgnoreCase("ano") || choice.equalsIgnoreCase("ne")) ;
    }

    public static Nationality checkNationality(String nationality) {
        return Nationality.valueOf(nationality);
    }

}
