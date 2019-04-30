package pl.parser.nbp;

class DataAnalyser{

    private double mean;
    private double stDeviation;
    private Data data;

    DataAnalyser(Data data){
        this.data = data;
        buyingRateMean();
        sellingRateStandardDeviation();
    }

    double getStDeviation() {
        return stDeviation;
    }

    double getMean() {
        return mean;
    }

    private void buyingRateMean(){
        double sum = 0.0;
        for(double rate :data.getBuyingRates()) sum += rate;
        mean = sum/data.getCounter();
    }

    private void sellingRateStandardDeviation(){
        double sum = 0.0;
        for(double rate : data.getSellingRates()) sum += rate;
        double mean = sum/data.getCounter();
        double squareSum = 0.0;
        for(double rate : data.getBuyingRates())
            squareSum += Math.pow((rate - mean), 2);

        stDeviation = Math.sqrt((squareSum)/(data.getCounter() -1));
    }

}
