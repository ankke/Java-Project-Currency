package pl.parser.nbp;

import org.xml.sax.InputSource;
import javax.xml.xpath.*;
import java.io.*;

public class XMLParser implements IParser {

    public Double getValue(Currency cur, String XMLFile, String tagName) throws XPathExpressionException{

        InputSource inputXML = new InputSource( new StringReader( XMLFile));
        XPath xPath = XPathFactory.newInstance().newXPath();
        String result =
                (String) xPath.evaluate("tabela_kursow/pozycja[nazwa_waluty = '" + cur.getFullName()+"' ]/" + tagName , inputXML, XPathConstants.STRING);
        result = result.replaceAll(",", ".");

        return Double.parseDouble(result);
    }

}
