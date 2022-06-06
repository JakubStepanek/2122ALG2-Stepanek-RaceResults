package app;

/**
 * @author Jakub Štěpánek
 */
public enum Circuit {
    SPA("Jerez"), CZE("Brno"), AUT("Spielberg"), STY("Spielberg"), RSM("Misano"), CAT("Catalunya"), FRA("Le Mans"),
    ARA("Aragón"), TER("Aragón"), EUR("Ricardo Tormo"), VAL("Ricardo Tormo"), POR("Algarve"), QAT("Losail"),
    DOH("Losail"), ITA("Mugello"), GER("Sachsenring"), NED("Assen"), GBR("Silverstone"), AME("Texas"), EMI("Misano"),
    SEP("Sepang");

    private String name;

    /**
     * Private constructor
     * 
     * @param circuit
     */
    private Circuit(String circuit) {
        this.name = circuit;
    }

    /**
     * Method returns name of Circuit enum
     * 
     * @return String
     */

    public String getCircuitValue() {
        return name;
    }

    /**
     * Method returns Cirucuit value by name
     * 
     * @param circuit
     * @return String
     */

    public static Circuit of(String circuit) {
        for (Circuit c : Circuit.values()) {
            if (c.getCircuitValue().equals(circuit)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Zadaný okruh neexistuje");

    }

}
