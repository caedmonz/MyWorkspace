package qunar.dev.task1;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by Administrator on 2016/4/6.
 */
public class FileRowsCountTest {

    @Test
    public void testRowCount() throws IOException {
        String fileName = "D:\\编程开发\\IDE\\IntelliJ IDEA\\workspace\\DevTask\\src\\FileAnalysis.java";
        int count = FileRowsCount.getRows(fileName);
        System.out.println("[" + fileName + "] Lines Number is " + count);
    }

}
