package pl.parser.nbp;

import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class URLReader {

    private static Map<String, String> dirFiles = new HashMap<>(); // cashowanie pliku dir wg lat

    public static String readDir(String year) throws IOException {
        String result;

        if((result = dirFiles.get(year)) == null) {
            StringBuilder path = new StringBuilder("http://www.nbp.pl/kursy/xml/dir");
            if (year != null) path.append(year.trim());
            path.append(".txt");
            URL files = new URL(path.toString());

            BufferedReader in = new BufferedReader(new InputStreamReader(files.openStream()));
            StringBuilder s = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                s.append(inputLine + '\n');

            in.close();
            dirFiles.put(year, s.toString());
            return s.toString();
        }
        else return result;
    }

    public static String readXMLFile(String fileName) throws IOException {
        StringBuilder path = new StringBuilder("http://www.nbp.pl/kursy/xml/");
        if(fileName != null) path.append(fileName.trim());
        path.append(".xml");
        URL files = new URL(path.toString());

        BufferedReader in = new BufferedReader(new InputStreamReader(files.openStream()));
        StringBuilder s = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            s.append(inputLine);

        in.close();
        return s.toString();
    }


}