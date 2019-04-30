package pl.parser.nbp;

import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

class DataProvider {

    static List<Double> sellingRates = new LinkedList<>();
    static List<Double> buyingRates = new LinkedList<>();
    static int counter;

    private static void collectRates(Currency cur, Date date, Parser parser, FileProvider fp){
        try{
            String file = fp.getFile(date);
            sellingRates.add(parser.getValue(cur, file, "kurs_sprzedazy"));
            buyingRates.add(parser.getValue(cur, file, "kurs_kupna"));
        }catch (XPathExpressionException | FileNotFoundException | IOException e){
            counter--;
            System.out.println(e.getMessage());
        }

    }

    static void collectData(Currency cur, String start, String end, FileProvider fp, Parser parser) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse(start);
        Date endDate = format.parse(end);
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Calendar current = Calendar.getInstance();
        cal1.setTime(startDate);
        cal2.setTime(endDate);

        if(cal1.after(current) || cal2.after(current)) throw new IllegalArgumentException("Dates cannot be from the future.");

        do{
            collectRates(cur, cal1.getTime(), parser, fp);
            cal1.add(Calendar.DATE, 1);
            counter++;

        } while(cal1.before(cal2));
    }

}
