package app;
import java.util.Comparator;

public class ComparatorRacerBySurname implements Comparator<Racer> {

    @Override
    public int compare(Racer o1, Racer o2) {
        return o1.getSurname().compareTo(o2.getSurname());
    }

}