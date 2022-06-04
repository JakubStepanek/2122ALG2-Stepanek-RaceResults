package app;

public enum Nationality {
    AU("Austrálie"), DE("Německo"), CZ("Česká republika"), ES("Španělsko"), FR("Francie"), IT("Itálie"),
    PT("Portugalsko"), ZA("Jižní Afrika"), JP("Japonsko"), US("Amerika"), GB("Velká Británie"), FI("Finsko");

    private String nationality;

    private Nationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationalityValue() {
        return nationality;
    }

    public static Nationality of(String nationality) {
        for (Nationality n : Nationality.values()) {
            if (n.getNationalityValue().equals(nationality)) {
                return n;
            }
        }
        throw new IllegalArgumentException("Neplatná zkratka národnosti");
    }
}
