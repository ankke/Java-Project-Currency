package pl.parser.nbp;

import java.io.IOException;
import java.util.Date;

/**
 * Interface for different file formats.
 */
public interface IFileProvider {
    /**
     * @param date date of desired file
     * @return String form of file
     * @throws FileNotFoundException when file with provided date does not exist in dir[year].txt
     * @throws IOException when URLReader cannot read that file
     */
    String getFile(Date date) throws FileNotFoundException, IOException;
}
