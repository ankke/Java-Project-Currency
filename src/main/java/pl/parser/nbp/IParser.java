package pl.parser.nbp;

/**
 * Interface for different file formats parsers. To assure format compatibility.
 */
public interface IParser {
    Double getValue(Currency cur, String file, String tagName) throws Exception;
}
