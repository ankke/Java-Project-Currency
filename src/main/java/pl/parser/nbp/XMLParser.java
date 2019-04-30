package pl.parser.nbp;

import org.xml.sax.InputSource;
import javax.xml.xpath.*;
import java.io.*;

public class XMLParser implements IParser {

    /**
     * Method to extract Double values from XMLFile depending on tagName value
     * @param cur
     * @param XMLFile
     * @param tagName kurs_sprzedazy lub kurs_kupna
     * @return Double value of specified in tagName rate
     * @throws XPathExpressionException when searching for value in XMLFile is not successful
     */
    public Double getValue(Currency cur, String XMLFile, String tagName) throws XPathExpressionException{

        InputSource inputXML = new InputSource( new StringReader( XMLFile));
        XPath xPath = XPathFactory.newInstance().newXPath();
        String result =
                (String) xPath.evaluate("tabela_kursow/pozycja[nazwa_waluty = '" + cur.getFullName()+"' ]/" + tagName , inputXML, XPathConstants.STRING);
        result = result.replaceAll(",", "."); // necessary for parsing a Double from String

        return Double.parseDouble(result);
    }

}
