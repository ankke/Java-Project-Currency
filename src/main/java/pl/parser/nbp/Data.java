package pl.parser.nbp;

import java.util.List;

/**
 * Class used for storing data: selling, buying rates.
 */
class Data {

    private List<Double> sellingRates;
    private List<Double> buyingRates;
    private int counter;

    List<Double> getSellingRates() {
        return sellingRates;
    }

    void setCounter(int counter) {
        this.counter = counter;
    }

    int getCounter() {
        return counter;
    }

    List<Double> getBuyingRates() {
        return buyingRates;
    }

    void addToSellingRates(Double rate){
        sellingRates.add(rate);
    }

    void addToBuyingRates(Double rate){
        buyingRates.add(rate);
    }

    /**
     * @param sellingRates List of selling rates values
     * @param buyingRates List of buying rates values
     * @param counter number of stored values
     */
    Data(List<Double> sellingRates, List<Double> buyingRates, int counter){
        this.sellingRates = sellingRates;
        this.buyingRates = buyingRates;
        this.counter = counter;
    }

}
