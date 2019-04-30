package pl.parser.nbp;

import java.util.LinkedList;
import java.util.List;

class Data {

    private List<Double> sellingRates = new LinkedList<>();
    private List<Double> buyingRates = new LinkedList<>();
    private int counter;

    public List<Double> getSellingRates() {
        return sellingRates;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }

    public List<Double> getBuyingRates() {
        return buyingRates;
    }

    void addToSellingRates(Double rate){
        sellingRates.add(rate);
    }

    void addToBuyingRates(Double rate){
        buyingRates.add(rate);
    }

    Data(List<Double> sellingRates, List<Double> buyingRates, int counter){
        this.sellingRates = sellingRates;
        this.buyingRates = buyingRates;
        this.counter = counter;

    }

}
