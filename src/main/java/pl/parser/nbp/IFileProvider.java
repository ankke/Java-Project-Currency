package pl.parser.nbp;

import java.io.IOException;
import java.util.Date;

/**
 * Interface for different file formats.
 */
public interface IFileProvider {
    String getFile(Date date) throws FileNotFoundException, IOException;
}
