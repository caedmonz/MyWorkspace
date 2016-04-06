package qunar.dev.task3;

import org.junit.Test;
import qunar.dev.task3.classfile.ClassFile;

import java.io.IOException;

/**
 * Created by Administrator on 2016/4/6.
 */
public class ClassFileTest {
    @Test
    public void testClassNames() throws IOException {
        String classFileName = "D:\\编程开发\\IDE\\IntelliJ IDEA\\workspace\\qunarTasks\\target\\classes\\qunar\\dev\\task3\\ClassFileAnalysis.class";
        ClassFileAnalysis classFileAnalysis = new ClassFileAnalysis(classFileName);
        ClassFile classFile = classFileAnalysis.getClassFile();
        System.out.println(classFile);
        System.out.println(classFileAnalysis.getAllClassName());
        System.out.println(classFileAnalysis.getImportClassNames());
    }
}
