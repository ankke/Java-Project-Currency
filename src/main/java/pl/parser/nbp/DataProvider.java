package pl.parser.nbp;

import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

class DataProvider {

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

    static Data collectData(Currency cur, String start, String end, IFileProvider fp, IParser parser) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse(start);
        Date endDate = format.parse(end);
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Calendar current = Calendar.getInstance();
        cal1.setTime(startDate);
        cal2.setTime(endDate);

        List<Double> sellingRates = new LinkedList<>();
        List<Double> buyingRates = new LinkedList<>();
        int counter = 0;

        Data data = new Data(sellingRates, buyingRates, counter);

        if(cal1.after(current) || cal2.after(current)) throw new IllegalArgumentException("Dates cannot be from the future.");

        do{
            collectRates(cur, cal1.getTime(), parser, fp, data);
            cal1.add(Calendar.DATE, 1);
            int tmp = data.getCounter();
            data.setCounter(++tmp);

        } while(cal1.before(cal2));

        return data;

    }

}
