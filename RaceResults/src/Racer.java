/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kubin
 */
public class Racer {

    private int racingNumber;
    private String name;
    private String surname;
    private TimeTools dateOfBirth;
    private Gender gender;

    public Racer(String name, String surname, TimeTools dateOfBirth, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.format("[%s] %10s %10s %10s", gender.getGenderValue(), name, surname, dateOfBirth.toString());
    }

    public static void main(String[] args) {
        //Racer r = new Racer("Jakub", "Štěpánek", "AKJDKA", Gender.M);
        //System.out.println(r);
    }

}
