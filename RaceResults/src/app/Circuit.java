package app;

public enum Circuit {
    SPA("Jerez"), CZE("Brno"), AUT("Spielberg"), STY("Spielberg"), RSM("Misano"), CAT("Catalunya"), FRA("Le Mans"),
    ARA("Aragón"), TER("Aragón"), EUR("Ricardo Tormo"), VAL("Ricardo Tormo"), POR("Algarve"), QAT("Losai"),
    DOH("Losail"), ITA("Mugello"), GER("Sachsenring"), NED("Assen"), GBR("Silverstone"), AME("Texas"), EMI("Misano");

    private String circuit;

    private Circuit(String circuit) {
        this.circuit = circuit;
    }

    public String getCircuitValue() {
        return circuit;
    }

    public static Circuit of(String circuit) {
        for (Circuit c : Circuit.values()) {
            if (c.getCircuitValue().equals(circuit)) {
                return c;
            }
        }
        return null;

    }

}
