package qunar.dev.task1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidParameterException;

/**
 * Created by Administrator on 2016/3/30.
 */
public class FileRowsCount {

    public static int getRows(String fileName) throws IOException {
        if (fileName == null) {
            throw new InvalidParameterException("The file to be analyzed is null");
        }
        if (!fileName.endsWith(".java")) {
            throw new InvalidParameterException("[" + fileName + "] is not a java file");
        }
        FileInputStream inputStream = null;
        InputStreamReader streamReader = null;
        BufferedReader br = null;
        try {
            inputStream = new FileInputStream(fileName);
            streamReader = new InputStreamReader(inputStream);
            br = new BufferedReader(streamReader);
            int count = 0;
            String str;
            while ((str = br.readLine()) != null) {
                if (!str.trim().equals("")) {
                    count++;
                }
            }
            return count;
        } finally {
            try {
                br.close();
                streamReader.close();
                inputStream.close();
            } catch (IOException e) {
                throw e;
            }
        }
    }
}
