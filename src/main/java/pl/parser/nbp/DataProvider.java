package pl.parser.nbp;

import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Class which provides data stored in Data object. Thanks to specified Parser and FileProvider type, this class does not depend on type of used file.
 */
class DataProvider {

    /**Method to fill Data object fields with proper values. Uses Parser for FileProvider, which are passed to the method.
     * If exception in Parser or FileProvider is thrown counter is decremented, because of lack of data from that date.
     * @param cur
     * @param date
     * @param parser
     * @param fp
     * @param data
     */
    private static void collectRates(Currency cur, Date date, IParser parser, IFileProvider fp, Data data){
        try{
            String file = fp.getFile(date);
            data.addToSellingRates(parser.getValue(cur, file, "kurs_sprzedazy"));
            data.addToBuyingRates(parser.getValue(cur, file, "kurs_kupna"));
        }catch (XPathExpressionException | FileNotFoundException | IOException e){
            int tmp = data.getCounter();
            data.setCounter(--tmp);
        }
    }

    /**
     * @param cur
     * @param start
     * @param end
     * @param fp
     * @param parser
     * @return
     * @throws ParseException
     */
    static Data collectData(Currency cur, String start, String end, IFileProvider fp, IParser parser) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse(start);
        Date endDate = format.parse(end);
        Calendar calStart = Calendar.getInstance();
        Calendar calEnd = Calendar.getInstance();
        Calendar current = Calendar.getInstance();
        calStart.setTime(startDate);
        calEnd.setTime(endDate);

        List<Double> sellingRates = new LinkedList<>();
        List<Double> buyingRates = new LinkedList<>();
        int counter = 0;

        Data data = new Data(sellingRates, buyingRates, counter);

        if(calStart.after(current) || calEnd.after(current)) throw new IllegalArgumentException("Dates cannot be from the future.");

        do{
            collectRates(cur, calStart.getTime(), parser, fp, data);
            calStart.add(Calendar.DATE, 1);
            int tmp = data.getCounter();
            data.setCounter(++tmp);

        } while(calStart.before(calEnd));

        return data;

    }

}
