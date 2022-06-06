package interfaces;

import java.io.File;
import java.io.IOException;
/**
 * @author JakubStepanek
 */

import app.Race;

public interface IFileOperations {
    /**
     * Function for loading data from file
     * 
     * @param result
     * @param race
     * @return
     * @throws IOException
     */
    public String load(File result, Race race) throws IOException; // method to load data from file

    /**
     * Method to save data into the file
     * 
     * @param results
     * @param race
     * @throws IOException
     */
    public void save(File results, Race race) throws IOException; // method to store data to file

    /**
     * Method to load data from race
     * 
     * @param result
     * @param race
     * @throws IOException
     */

    public void loadToRace(File result, Race race) throws IOException; // method to load data from file and store them
                                                                       // in race

    /**
     * Method to save race data to file
     * 
     * @param race
     * @param fileName
     * @throws IOException
     */
    public void saveRace(Race race, String fileName) throws IOException; // method to store data to file
}
