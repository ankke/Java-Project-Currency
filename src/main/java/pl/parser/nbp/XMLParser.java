package pl.parser.nbp;

import org.xml.sax.InputSource;
import javax.xml.xpath.*;
import java.io.*;

/**
 * Class used for extracting values from XMLFile with XPath.
 */
public class XMLParser implements IParser {

    /**
     * Method to extract Double values from XMLFile depending on tagName value.
     * @param cur Enum currency
     * @param file String form of file
     * @param tagName kurs_sprzedazy lub kurs_kupna
     * @return Double value of specified in tagName rate
     * @throws XPathExpressionException when searching for value in XMLFile is not successful (XPath cannot evaluate searching expression).
     */
    public Double getValue(Currency cur, String file, String tagName) throws Exception{

        InputSource inputXML = new InputSource( new StringReader(file));
        XPath xPath = XPathFactory.newInstance().newXPath();

        String result = (String) xPath.evaluate("tabela_kursow/pozycja[nazwa_waluty = '" + cur.getFullName()+"' ]/" + tagName,
                        inputXML, XPathConstants.STRING);

        result = result.replaceAll(",", "."); // necessary for parsing a Double from String

        return Double.parseDouble(result);
    }

}
