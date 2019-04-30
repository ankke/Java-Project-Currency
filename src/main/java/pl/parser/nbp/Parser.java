package pl.parser.nbp;

import javax.xml.xpath.XPathExpressionException;

public interface Parser  {
    Double getValue(Currency cur, String XMLFile, String tagName) throws XPathExpressionException;
}
