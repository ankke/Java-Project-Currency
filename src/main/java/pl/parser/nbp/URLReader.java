package pl.parser.nbp;

import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Class used to reading directly from an URL.
 */
class URLReader {

    /**
     * Cache memory for dir[year].txt files. Year is key, Value is a String representation of a file.
     */
    private static Map<String, String> dirFiles = new HashMap<>();

    /**Reads dir file form URL.
     * @param year year in dir[year].txt
     * @return String representation of dir[year].txt file
     * @throws IOException when unable to read from URL
     */
    static String readDir(String year) throws IOException {
        String result;

        if((result = dirFiles.get(year)) == null) { //checking if the file has not been read already
            StringBuilder path = new StringBuilder("http://www.nbp.pl/kursy/xml/dir");
            if (year != null) path.append(year.trim());
            path.append(".txt");
            URL url = new URL(path.toString());

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder s = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                s.append(inputLine);

            in.close();
            dirFiles.put(year, s.toString());
            return s.toString();
        }
        else return result;
    }

    /**Reads XML file from URL.
     * @param fileName name of file
     * @return String representation of file
     * @throws IOException when unable to read from URL
     */
    static String readXMLFile(String fileName) throws IOException {
        StringBuilder path = new StringBuilder("http://www.nbp.pl/kursy/xml/");
        if(fileName != null) path.append(fileName.trim());
        path.append(".xml");
        URL url = new URL(path.toString());

        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder s = new StringBuilder();
        String inputLine = in.readLine();

        if (inputLine.startsWith("\uFEFF")) { //some files have BOM at the begging, which has to be deleted
            inputLine = inputLine.substring(1);
            s.append(inputLine);
        }
        while ((inputLine = in.readLine()) != null) {
            s.append(inputLine);
        }

        in.close();
        return s.toString();
    }
}
