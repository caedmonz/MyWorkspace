package qunar.dev.task3source;

import java.io.*;
import java.util.*;

/**
 * Created by Administrator on 2016/4/6.
 */
public class JavaImportClassAnalysis {

    private static Map<String, Integer> importClasses;

    public static List<String> getTop10ImportClassName(String dirName) throws IOException {
        List<String> top10ClassNames = new ArrayList<String>();
        importClasses = new HashMap<String, Integer>();
        File file = new File(dirName);
        dealDir(file);
        if (importClasses.isEmpty()) {
            return top10ClassNames;
        }
        //将从各个文件得到的import class按值排序
        List<Map.Entry<String, Integer>> classNames = new ArrayList<Map.Entry<String, Integer>>(importClasses.entrySet());
        Collections.sort(classNames, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() > o2.getValue()) {
                    return -1;
                }
                if (o1.getValue() < o2.getValue()) {
                    return 1;
                }
                return 0;
            }
        });
        //统计出前10个import类
        int count = 0;
        for (Map.Entry<String, Integer> className : classNames) {
            top10ClassNames.add(className.getKey());
            count++;
            if (count >= 10) {
                break;
            }
        }
        return top10ClassNames;
    }

    //从源文件夹进行处理
    private static void dealDir(File file) throws IOException {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                dealDir(files[i]);
            }
        } else {
            if (!file.getName().endsWith(".java")) {
                return;
            }
            for (String className : getImportClassName(file)) {
                if (importClasses.containsKey(className)) {
                    int count = importClasses.get(className);
                    importClasses.put(className, count + 1);
                } else {
                    importClasses.put(className, 1);
                }
            }
        }
    }

    //获取一个java源文件的import class的name
    private static Set<String> getImportClassName(File file) throws IOException {
        Set<String> classNames = new HashSet<String>();
        Reader reader = new FileReader(file);
        BufferedReader bufReader = new BufferedReader(reader);
        String s = null;
        while ((s = bufReader.readLine()) != null) {
            String line = s.trim();
            if (line.startsWith("public") || line.startsWith("class") || line.startsWith("enum") || line.startsWith
                    ("interface") || line.startsWith("@interface")) {
                return classNames;
            }
            if (line.startsWith("import")) {
                classNames.addAll(processLine(line));
            }
        }
        return classNames;
    }

    //从含有import关键字的一行中提取出相应的class name
    private static Set<String> processLine(String line) {
        Set<String> classNames = new HashSet<String>();
        String[] splitNames = line.split(";");
        for (String splitName : splitNames) {
            if (splitName.trim().startsWith("import")) {
                classNames.add(splitName.trim().substring(7).trim());
            }
        }
        return classNames;
    }
}
