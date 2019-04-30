package pl.parser.nbp;

import java.text.ParseException;

public class Executor {

    public static void execute(String cur, String start, String stop) throws IllegalArgumentException, ParseException {
        FileProvider fileProvider = new XMLFileProvider("c"); //możliwość ze skorzystania z innego pliku xml (a, b, c, h)
        // możliwosc skorzysania z innego formatu pliku
        Parser parser = new XMLParser(); // oraz innego parsera w zależności od formatu
        DataProvider.collectData(Currency.valueOf(cur), start, stop, fileProvider, parser); // TODO obsługa błedu enuma
        System.out.printf("%.4f \n", DataAnalysis.buyingRateMean());
        System.out.printf("%.4f", DataAnalysis.sellingRateStandardDeviation());
    }

}
