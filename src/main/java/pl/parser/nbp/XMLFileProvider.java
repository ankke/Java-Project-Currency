package pl.parser.nbp;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Class used for providing desired XML file in String format.
 */
public class XMLFileProvider implements IFileProvider {

    /**
     * mode =[a,b,c,h]
     */
    private String mode;

    XMLFileProvider(String mode){
        this.mode = mode;
    }

    /**
     * @param date date
     * @return XML filename in String format
     * @throws FileNotFoundException when file does not exist for provided data in dir[year].txt
     * @throws IOException thrown by URLReader method
     */
    private String extractFileName(Date date) throws FileNotFoundException, IOException {
        StringBuilder p = new StringBuilder(mode + "[0-9][0-9][0-9]z");
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        p.append(format.format(date));
        Pattern pattern = Pattern.compile(p.toString());

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date);
        int year = cal1.get(Calendar.YEAR);
        String year1 = "";
        // if year is a current year then txt file is dir.txt, so year1 should stay empty
        if( year != cal2.get(Calendar.YEAR)) year1 = Integer.toString(year);

        Scanner fileName = new Scanner(URLReader.readDir(year1));
        String result;
        if((result = fileName.findWithinHorizon(pattern, 0) )!= null ) return result;
        else throw new FileNotFoundException();
    }

    /**
     * @param date date
     * @return XML file in String format
     * @throws FileNotFoundException thrown by extractFileName
     * @throws IOException thrown by URLReader method
     */
    public String getFile(Date date) throws FileNotFoundException, IOException{
        return URLReader.readXMLFile(extractFileName(date));
    }
}
