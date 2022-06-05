package interfaces;

import java.io.File;
import java.io.IOException;

import app.Race;

public interface IFileOperations {
    public String load(File result, Race race) throws IOException; // method to load data from file

    public void save(File results, Race race) throws IOException; // method to store data to file

    public void loadToRace(File result, Race race) throws IOException; // method to load data from file and store them
                                                                       // in race

    public void saveRace(Race race, String fileName) throws IOException; // method to store data to file
}
