
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author kubin
 */
public class Race {

    private String name;
    private String location;
    private ArrayList<Racer> racers = new ArrayList<>(); //ArrayList simulates database
    private TimeTools raceStartTime;

    public Race(String name) {
        this.name = name;
    }

}
