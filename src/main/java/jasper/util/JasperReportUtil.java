package jasper.util;

import java.io.File;

public class JasperReportUtil {

    final static String jasperDir = "jrxml";
    final static String pdfDir = "F:/JasperPrint/pdf";

    /**
     * 获取jasper文件地址
     *
     * @param fileName
     * @return
     */
    public static String getJasperFileDir(String fileName) {
        return ClassLoader.getSystemClassLoader().getResource(jasperDir).getPath() + File.separator + fileName + ".jasper";
    }

    /**
     * 获取pdf文件地址
     *
     * @param fileName
     * @return
     */
    public static String getPdfFileDir(String fileName) {
        return pdfDir +File.separator+ fileName + ".pdf";
    }
}
