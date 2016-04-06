package qunar.dev.task2;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by Administrator on 2016/4/6.
 */
public class ExchangeRatesTest {
    @Test
    public void testRates() throws IOException {
        String xlsFileName = "./rates.xls";
        ExchangeRates rate = new ExchangeRates(xlsFileName);
        rate.generateXlsForRates();
        rate.showRates();
    }
}
