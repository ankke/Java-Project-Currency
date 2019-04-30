package pl.parser.nbp;

import java.text.ParseException;

class Executor {

    static void execute(String cur, String start, String stop) throws IllegalArgumentException, ParseException {
        IFileProvider fileProvider = new XMLFileProvider("c"); //możliwość ze skorzystania z innego pliku xml (a, b, c, h)
        // możliwosc skorzysania z innego formatu pliku
        IParser parser = new XMLParser(); // oraz innego parsera w zależności od formatu
        Data data = DataProvider.collectData(Currency.valueOf(cur), start, stop, fileProvider, parser);
        DataAnalyser dataAnalyser = new DataAnalyser(data);
        System.out.printf("%.4f \n", dataAnalyser.getMean());
        System.out.printf("%.4f", dataAnalyser.getStDeviation());
    }

}
