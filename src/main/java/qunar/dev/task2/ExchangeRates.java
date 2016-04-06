package qunar.dev.task2;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/3/30.
 */
public class ExchangeRates {

    private String indexURL = "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105";

    private Date date;

    private String fileName;

    public ExchangeRates(String fileName) {
        if (fileName == null) {
            throw new InvalidParameterException("File Name is null.");
        }
        if (!fileName.endsWith(".xls")) {
            throw new InvalidParameterException("[" + fileName + "] is not an excel file.");
        }
        this.fileName = fileName;
    }

    public void showRates() throws IOException {
        List<List<Object>> rates = get30DaysRates();
        System.out.println("日期\t\t美元\t欧元\t港元");
        for (int i = 0; i < rates.size(); i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String date = sdf.format(rates.get(i).get(0));
            System.out.println(date + "\t" + rates.get(i).get(1) + "\t" + rates.get(i).get(2) + "\t" + rates.get(i).get(3));
        }
    }

    public void generateXlsForRates() throws IOException {
        setTime();
        //得到Excel工作簿对象
        HSSFWorkbook wb = new HSSFWorkbook();
        //得到Excel工作表对象
        HSSFSheet sheet = wb.createSheet("人民币汇率中间价");
        //得到Excel工作表的行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < 4; i++) {
            sheet.setColumnWidth(i, 3000);
        }

        HSSFCell cell;
        cell = row.createCell(0);
        // 定义单元格为字符串类型
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        // 在单元格中输入一些内容
        cell.setCellValue("日期");

        cell = row.createCell(1);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("美元");

        cell = row.createCell(2);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("欧元");

        cell = row.createCell(3);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("港元");

        List<List<Object>> rates = get30DaysRates();
        for (int i = 0; i < rates.size(); i++) {
            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String date = sdf.format(rates.get(i).get(0));
            cell.setCellValue(date);

            for (int k = 1; k <= 3; k++) {
                cell = row.createCell(k);
                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                cell.setCellValue((Double) rates.get(i).get(k));
            }
        }
        FileOutputStream fOut = new FileOutputStream(fileName);
        // 把相应的Excel 工作簿存盘
        wb.write(fOut);
        fOut.flush();
        fOut.close();
    }

    private void setTime() {
        Calendar nowDay = Calendar.getInstance();
        nowDay.setTime(new Date());
        nowDay.add(nowDay.DATE, -30);
        date = nowDay.getTime();
    }

    private boolean isValidDate(Date rateTime) {
        if (rateTime.after(date)) {
            return true;
        }
        return false;
    }

    private List<List<Object>> get30DaysRates() throws IOException {
        List<List<Object>> rates = new ArrayList<List<Object>>();
        for (int i = 1; i < 10; i++) {
            Document doc = Jsoup.connect(indexURL + "/index" + i + ".html").timeout(10000).get();
            Elements links = doc.select("a[href]"); //带有href属性的a元素
            for (Element link : links) {
                String hrefLink = link.attr("href");
                if (hrefLink.startsWith("/zhengcehuobisi/")) {
                    List<Object> money = getRates("http://www.pbc.gov.cn" + hrefLink);
                    if (money != null) {
                        if (isValidDate((Date) money.get(0))) {
                            rates.add(money);
                        } else {
                            return rates;
                        }
                    }
                }
            }
        }
        return rates;
    }

    private List<Object> getRates(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Element link = doc.getElementById("zoom");
        //判断此url是否包含具体的汇率信息
        if (link == null) {
            return null;
        }
        String context = link.text();
        int time1 = context.lastIndexOf("年");
        int time2 = context.lastIndexOf("月");
        int time3 = context.lastIndexOf("日");
        int year = Integer.valueOf(context.substring(time1 - 4, time1));
        int month = Integer.valueOf(context.substring(time1 + 1, time2));
        int day = Integer.valueOf(context.substring(time2 + 1, time3));
        Calendar rateDay = Calendar.getInstance();
        rateDay.set(year, month - 1, day, 0, 0, 0);
        Date rateTime = rateDay.getTime();

        int index11 = context.indexOf("1美元对人民币");
        int index12 = context.indexOf("元", index11 + 7);
        String money1s = context.substring(index11 + 7, index12);
        double money1 = Double.valueOf(money1s);

        int index21 = context.indexOf("1欧元对人民币");
        int index22 = context.indexOf("元", index21 + 7);
        String money2s = context.substring(index21 + 7, index22);
        double money2 = Double.valueOf(money2s);

        int index31 = context.indexOf("1港元对人民币");
        int index32 = context.indexOf("元", index31 + 7);
        String money3s = context.substring(index31 + 7, index32);
        double money3 = Double.valueOf(money3s);

        List<Object> money = new ArrayList<Object>();
        money.add(rateTime);
        money.add(money1);
        money.add(money2);
        money.add(money3);
        return money;
    }
}
