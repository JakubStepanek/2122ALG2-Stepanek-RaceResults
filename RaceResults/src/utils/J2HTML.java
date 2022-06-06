package utils;

import app.Race;
import app.Racer;
import static j2html.TagCreator.*;
import java.util.List;

public final class J2HTML {

    /**
     * private constructor
     */
    private J2HTML() {

    }

    /**
     * Function creates HTML body
     *
     * @param race
     * @return
     */
    public static String saveHTML(Race race) {
        List<Racer> racers = race.getRacers();
        return body(
                h1(String.format("%d %s", race.getSeasonYear(), race.getCircuitName())), h3(String.format("%s\t".repeat(10),
                " Pozice", " Jméno",
                "Příjmení", "Národnost", "Tým", "Motocykl", "Startovní číslo", "Maximální rychlost", "Čas", "Počet bodů")),
                div(each(racers, racer -> div(h5(racer.toString()))))
        ).render();

    }
}
