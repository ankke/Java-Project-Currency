package pl.parser.nbp;

import java.text.ParseException;

class Executor {

    /**Class to execute desired methods. If necessary can by extended by adding different FileProviders f.ex. for JSON with corresponding Parsers.
     * @param cur
     * @param start
     * @param stop
     * @throws IllegalArgumentException when @cur name ins invalid or dates are from future
     * @throws ParseException when date's format is incompatible with yyyy-MM-dd
     */
    static void execute(String cur, String start, String stop) throws IllegalArgumentException, ParseException{
        IFileProvider fileProvider = new XMLFileProvider("c"); // allows adding different modes (a, b, c, h) for file searching
        IParser parser = new XMLParser();
        Data data = DataProvider.collectData(Currency.valueOf(cur), start, stop, fileProvider, parser);
        DataAnalyser dataAnalyser = new DataAnalyser(data);
        System.out.printf("%.4f \n", dataAnalyser.getMean());
        System.out.printf("%.4f", dataAnalyser.getStDeviation());
    }

}
