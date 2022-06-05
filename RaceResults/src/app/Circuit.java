package app;

public enum Circuit {
    SPA("Jerez"), CZE("Brno"), AUT("Spielberg"), STY("Spielberg"), RSM("Misano"), CAT("Catalunya"), FRA("Le Mans"),
    ARA("Aragón"), TER("Aragón"), EUR("Ricardo Tormo"), VAL("Ricardo Tormo"), POR("Algarve"), QAT("Losail"),
    DOH("Losail"), ITA("Mugello"), GER("Sachsenring"), NED("Assen"), GBR("Silverstone"), AME("Texas"), EMI("Misano"),
    SEP("Sepang");

    private String name;

    private Circuit(String circuit) {
        this.name = circuit;
    }

    public String getCircuitValue() {
        return name;
    }

    public static Circuit of(String circuit) {
        for (Circuit c : Circuit.values()) {
            if (c.getCircuitValue().equals(circuit)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Zadaný okruh neexistuje");

    }

}
