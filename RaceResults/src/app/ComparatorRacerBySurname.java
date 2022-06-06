package app;

import java.util.Comparator;

/**
 * @author Jakub Štěpánek
 */
public class ComparatorRacerBySurname implements Comparator<Racer> {

    /**
     * Method compares two objects of Racer and takes care about Surname
     * 
     * @param Racer
     * @return int
     */
    @Override
    public int compare(Racer o1, Racer o2) {
        return o1.getSurname().compareTo(o2.getSurname());
    }

}