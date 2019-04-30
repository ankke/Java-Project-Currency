package pl.parser.nbp;

/**
 * Enum from used currencies.
 */
public enum Currency {
    USD ("dolar amerykański"),
    EUR ("euro"),
    CHF ("frank szwajcarski"),
    GBP ("funt szterling");

    private final String fullName;

    /**
     * @param fullName full name of currency
     */
    Currency(String fullName){
        this.fullName = fullName;
    }

    /**
     * @return full name of currency
     */
    public String getFullName() {
        return fullName;
    }
}
