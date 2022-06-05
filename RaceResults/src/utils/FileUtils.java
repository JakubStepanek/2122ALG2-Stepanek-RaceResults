package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import app.Circuit;
import app.Nationality;
import app.Race;
import app.Racer;
import interfaces.IFileOperations;

public final class FileUtils implements IFileOperations {

    private static Scanner sc = new Scanner(System.in);

    public FileUtils() {
    }

    @Override
    public void loadToRace(File result, Race race) throws IOException {
        String line;
        String[] parts;
        Racer r;
        try (BufferedReader br = new BufferedReader(new FileReader(result))) {
            // skip header
            br.readLine();
            while (((line = br.readLine()) != null)) {
                parts = line.split(";");
                if (InputCheckUtils.checkSeasonYearBoolean(Integer.parseInt(parts[0]))) {
                    race.setSeasonYear(Integer.parseInt(parts[0]));
                }
                if (!(race.isCircuitNameSet())) {
                    race.setCircuitName(Circuit.valueOf(parts[3]));
                }
                r = new Racer(parts[6], parts[5], Nationality.valueOf(parts[12]));
                r.setTeam(parts[7]);
                r.setBike(parts[8]);
                r.setPoints(Integer.parseInt(parts[10]));
                r.setRacingNumber(Integer.parseInt(parts[11]));
                r.setRaceTime(parts[14]);
                if (parts[13].length() != 0) {
                    r.setMaxSpeed(Double.parseDouble(parts[13]));
                }
                r.setPosition(Integer.parseInt(parts[9]));
                race.addRacer(r);

            }
        }
    }

    @Override
    public void saveRace(Race race, String fileName) throws IOException {
        String result = System.getProperty("user.dir") + File.separator + "data" +
                File.separator
                + fileName + ".csv";
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(result, true)))) {
            pw.println(String.format("%-8s %-10s %-10s %-15s %-30s %-10s %-15s %-2s %s", "Umístění",
                    "Jméno",
                    "Příjmení", "Národnost", "Tým", "Motocykl", "Startovní číslo",
                    "Max. rychlost", "Čas"));
            for (Racer racer : race.getRacers()) {
                pw.println(racer);
            }
        }
    }

    @Override
    public String load(File result, Race race) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(File results, Race race) throws IOException {
        // TODO Auto-generated method stub

    }

}
