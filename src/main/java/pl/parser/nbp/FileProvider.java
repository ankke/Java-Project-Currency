package pl.parser.nbp;

import java.io.IOException;
import java.util.Date;

public interface FileProvider {
    String getFile(Date date) throws FileNotFoundException, IOException;
}
