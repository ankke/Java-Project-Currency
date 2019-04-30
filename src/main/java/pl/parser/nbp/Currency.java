package pl.parser.nbp;

public enum Currency {
    USD ("dolar amerykański"),
    EUR ("euro"),
    CHF ("frank szwajcarski"),
    GBP ("funt szterling");

    private final String fullName;

    Currency(String fullName){
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
