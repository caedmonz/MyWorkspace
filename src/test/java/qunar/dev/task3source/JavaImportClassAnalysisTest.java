package qunar.dev.task3source;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/4/6.
 */
public class JavaImportClassAnalysisTest {

    @Test
    public void testClassNames() throws IOException {
        List<String> classNames = JavaImportClassAnalysis.getTop10ImportClassName("D:\\编程开发\\IDE\\workspace");
        for (int i = 0; i < classNames.size(); i++) {
            System.out.println("#" + (i + 1) + ": " + classNames.get(i));
        }
    }

}
