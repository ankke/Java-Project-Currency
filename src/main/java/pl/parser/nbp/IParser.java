package pl.parser.nbp;

import javax.xml.xpath.XPathExpressionException;

/**
 * Interface for different file formats parsers. To assure format compatibility.
 */
public interface IParser {
    /**
     * @param cur Enum currency
     * @param file String form of file
     * @param tagName search condition
     * @return Double value if provided tag
     * @throws XPathExpressionException when XPath cannot evaluate searching expression
     */
    Double getValue(Currency cur, String file, String tagName) throws XPathExpressionException;
}
