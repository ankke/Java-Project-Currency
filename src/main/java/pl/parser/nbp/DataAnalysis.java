package pl.parser.nbp;

public class DataAnalysis extends DataProvider{

    public static double buyingRateMean(){
        double sum = 0.0;
        for(double rate : buyingRates) sum += rate;
        return sum/counter;
    }

    public static double sellingRateStandardDeviation(){
        double sum = 0.0;
        for(double rate : sellingRates) sum += rate;
        double mean = sum/counter;
        double squareSum = 0.0;
        for(double rate : buyingRates)
            squareSum += Math.pow((rate - mean), 2);

        return Math.sqrt((squareSum)/(counter -1));
    }

}
