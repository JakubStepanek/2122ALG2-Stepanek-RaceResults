package interfaces;

import java.io.File;
import java.io.IOException;

import app.Race;

public interface IFileOperations {
    public String load(File result) throws IOException; // method to load data from file

    public void save(File results, Race race) throws IOException; // method to store data to file

}
