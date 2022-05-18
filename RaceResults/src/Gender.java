/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kubin
 */
public enum Gender {
    MUŽ("m"), ŽENA("f"), OSTATNÍ("o");

    private String gender;

    private Gender(String gender) {
        this.gender = gender;
    }

    public String getGenderValue() {
        return gender;
    }

    public static Gender of(String gender) {
        for (Gender g : Gender.values()) {
            if (g.getGenderValue().equals(gender)) {
                return g;
            }
        }
        return null;
    }

}
