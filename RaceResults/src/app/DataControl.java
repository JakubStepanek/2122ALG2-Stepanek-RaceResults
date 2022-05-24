package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataControl extends Race{

    public DataControl(int seasonYear, Circuit circuitName) {
        super(seasonYear, circuitName);
        //TODO Auto-generated constructor stub
    }

    public static void loadStats(File file, Race race) throws FileNotFoundException, IOException {
        String line;
        String[] parts;
        Racer r;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // skip header
            br.readLine();
            while ((line = br.readLine()) != null) {
                parts = line.split(";");
                r = new Racer(parts[6], parts[5], Nationality.valueOf(parts[12]));
                r.setTeam(parts[7]);
                r.setBike(parts[8]);
                r.setRacingNumber(parts[11]);
                if (parts[13].length() != 0) {
                    r.setMaxSpeed(Double.parseDouble(parts[13]));
                }
                r.setPozition(Integer.parseInt(parts[9]));
                race.addRacer(r);
            }
        }
    }

}
