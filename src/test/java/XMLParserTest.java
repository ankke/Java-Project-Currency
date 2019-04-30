import pl.parser.nbp.*;
import org.junit.Assert;
import org.junit.Test;

public class XMLParserTest {

    private String file = "<?xml version=\"1.0\" encoding=\"ISO-8859-2\"?>" +
            "<tabela_kursow typ=\"C\" uid=\"13c022\">" +
            "<numer_tabeli>022/C/NBP/2013</numer_tabeli>" +
            "<data_notowania>2013-01-30</data_notowania>" +
            "<data_publikacji>2013-01-31</data_publikacji>" +
            "<pozycja>" +
            "<nazwa_waluty>euro</nazwa_waluty>" +
            "<przelicznik>1</przelicznik>" +
            "<kod_waluty>EUR</kod_waluty>" +
            "<kurs_kupna>4,1530</kurs_kupna>" +
            "<kurs_sprzedazy>4,2370</kurs_sprzedazy>" +
            "</pozycja>" +
            "</tabela_kursow>";

    @Test (expected = Exception.class)
    public void shouldThrowException_WrongTag() throws Exception{
        IParser parser = new XMLParser();
        parser.getValue(Currency.EUR, file, "kurs");
    }

    @Test (expected = Exception.class)
    public void shouldThrowException_WrongFile() throws Exception{
        IParser parser = new XMLParser();
        parser.getValue(Currency.EUR, file.substring(2), "kurs_sprzedazy");
    }

    @Test
    public void shouldReturnDouble() throws Exception{
        IParser parser = new XMLParser();
        Double res = parser.getValue(Currency.EUR, file, "kurs_sprzedazy");
        Assert.assertEquals(res, (Double) 4.2370);
    }


}
