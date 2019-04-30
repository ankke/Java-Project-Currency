package pl.parser.nbp;

/**
 * Class used for calculating mean and standard deviation.
 */
class DataAnalyser{

    private double mean;
    private double stDeviation;
    private Data data;

    DataAnalyser(Data data){
        this.data = data;
        buyingRateMean();
        try{
            sellingRateStandardDeviation();
        }catch(TooLittleDataException e){
            System.out.println("Some files might not exist.");
            System.out.println(e.getMessage());
        }
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

    /**
     * @throws TooLittleDataException if number of samples is zero (because e.g. xml file from provided date does not exist)
     */
    private void sellingRateStandardDeviation() throws TooLittleDataException{
        double sum = 0.0;
        for(double rate : data.getSellingRates()) sum += rate;
        double mean = sum/data.getCounter();
        double squareSum = 0.0;
        for(double rate : data.getSellingRates())
            squareSum += Math.pow((rate - mean), 2);
        if(data.getCounter() > 0) stDeviation = Math.sqrt((squareSum)/(data.getCounter()));
        else{
            stDeviation = -1;
            throw new TooLittleDataException("Too little data to calculate standard deviation."); //thrown to avoid dividing by 0
        }
    }

}
