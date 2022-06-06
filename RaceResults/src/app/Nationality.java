package app;

/**
 * @author Jakub Štěpánek
 */
public enum Nationality {
    AU("Austrálie"), DE("Německo"), CZ("Česká republika"), ES("Španělsko"), FR("Francie"), IT("Itálie"),
    PT("Portugalsko"), ZA("Jižní Afrika"), JP("Japonsko"), US("Amerika"), GB("Velká Británie"), FI("Finsko");

    private String name;

    /**
     * Private constructor
     * 
     * @param circuit
     */
    private Nationality(String nationality) {
        this.name = nationality;
    }

    /**
     * Method returns name of Circuit enum
     * 
     * @return String
     */
    public String getNationalityValue() {
        return name;
    }

    /**
     * Method returns Cirucuit value by name
     * 
     * @param circuit
     * @return String
     */
    public static Nationality of(String nationality) {
        for (Nationality n : Nationality.values()) {
            if (n.getNationalityValue().equals(nationality)) {
                return n;
            }
        }
        throw new IllegalArgumentException("Neplatná zkratka národnosti");
    }
}
