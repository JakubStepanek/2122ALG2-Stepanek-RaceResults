package app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import interfaces.IFileOperations;

/**
 * BinaryFile
 */
public class BinaryFile implements IFileOperations {
    public BinaryFile() {

    }

    @Override
    public String load(File result) throws IOException {
        // počet závodníků
        // registrační_číslo;jméno;příjmení;[int]počet_bodů;[int]pozice;
        StringBuilder sb = new StringBuilder();
        int countOfRacers, racingNumber, countOfLetters, points, position;
        String name = "", surname = "";
        int rank = 1;
        try (DataInputStream in = new DataInputStream(new FileInputStream(result))) {
            boolean end = false;
            while (!end) {
                try {
                    countOfRacers = in.readInt();
                    for (int i = 0; i < countOfRacers; i++) {
                        racingNumber = in.readInt();
                        name = "";
                        surname = "";
                        countOfLetters = in.readInt();
                        for (int j = 0; j < countOfLetters; j++) {
                            name += in.readChar();
                        }
                        surname += in.readUTF();
                        points = in.readInt();
                        position = in.readInt();
                        sb.append(
                                String.format("%2d %10s %10s %3d %3d.", racingNumber, name, surname, points, position));
                        sb.append("\n");
                    }
                } catch (EOFException e) {
                    end = true;
                }

            }

        }
        return sb.toString();

    }

    @Override
    // počet závodníků
    // registrační_číslo;[count]jméno;příjmení;počet_bodů;pozice;
    public void save(File result, Race race) throws IOException {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(result, true))) {
            int countOfLetters;
            // počet závodníků, kteří budou uloženi
            out.writeInt(race.getRacersSize());
            // logika ukládání závodníků
            for (Racer racer : race.getRacers()) {
                out.writeInt(racer.getRacingNumber());
                countOfLetters = racer.getName().length();
                out.writeInt(countOfLetters);
                // zapisujeme jméno písmenko po písmenku
                for (int i = 0; i < countOfLetters; i++) {
                    out.writeChar(racer.getName().charAt(i));
                }
                // ekvivalent pro zápis jména výše
                out.writeUTF(racer.getSurname());
                out.writeInt(racer.getPoints());
                out.writeInt(racer.getPosition());
            }
        }
    }
}
