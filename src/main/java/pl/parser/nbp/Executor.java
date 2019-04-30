package pl.parser.nbp;

import java.text.ParseException;

/**
 * Class to execute desired methods. If necessary can by extended by adding different FileProviders e.g. for JSON with corresponding Parsers.
 */
class Executor {
    /**@param cur currency abbreviation
     * @param start start date
     * @param end end date
     * @throws IllegalArgumentException when cur name or dates are invalid
     * @throws ParseException when date format is incompatible with yyyy-MM-dd
     */
    static void execute(String cur, String start, String end) throws IllegalArgumentException, ParseException{
        IFileProvider fileProvider = new XMLFileProvider("c"); // allows adding different modes (a, b, c, h) for file searching
        IParser parser = new XMLParser();
        Data data = DataProvider.collectData(Currency.valueOf(cur), start, end, fileProvider, parser);
        DataAnalyser dataAnalyser = new DataAnalyser(data);
        System.out.printf("%.4f \n", dataAnalyser.getMean());
        System.out.printf("%.4f", dataAnalyser.getStDeviation());
    }

}
