package pl.parser.nbp;

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
            System.out.println("Dates cannot be equal or some file does not exist.");
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

    private void sellingRateStandardDeviation() throws TooLittleDataException{
        double sum = 0.0;
        for(double rate : data.getSellingRates()) sum += rate;
        double mean = sum/data.getCounter();
        double squareSum = 0.0;
        for(double rate : data.getBuyingRates())
            squareSum += Math.pow((rate - mean), 2);
        if(data.getCounter() > 1) stDeviation = Math.sqrt((squareSum)/(data.getCounter() -1));
        else{
            stDeviation = -1;
            throw new TooLittleDataException("Too little data to calculate standard deviation.");
        }
    }

}
