package pl.parser.nbp;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class XMLFileProvider implements FileProvider {
    private String mode;

    public XMLFileProvider(String mode){
        this.mode = mode;
    }

    public String retrieveFileName(Date date) throws FileNotFoundException, IOException {
        StringBuilder p = new StringBuilder(mode + "[0-9][0-9][0-9]z");
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        p.append(format.format(date)); //yyMMdd
        Pattern pattern = Pattern.compile(p.toString()); // pattern określajacy format nazwy pliku xml

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date);
        int year = cal1.get(Calendar.YEAR);
        String year1 = "";
        if( year != cal2.get(Calendar.YEAR)) year1 = Integer.toString(year);

        Scanner fileName = new Scanner(URLReader.readDir(year1)); // w fileName znajduje się plik tekstowy dir
        String result;
        if((result = fileName.findWithinHorizon(pattern, 0) )!= null ) {
            //System.out.println(result);
            return result; // wyszukiwanie w tym pliku odpowiedniej nazwy pliku
        }
        else throw new FileNotFoundException("No filename found for: " + format.format(date));
    }

    public String getFile(Date date) throws FileNotFoundException, IOException{
        return URLReader.readXMLFile(retrieveFileName(date));
    }
}
