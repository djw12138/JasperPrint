package jasper.print;

import jasper.util.JasperReportUtil;
import jasper.util.PrintUtil;
import net.sf.jasperreports.engine.*;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class printTest {

    @Test
    public void printJasper(){
        String fileName = "demo1";
        String jasperPath = JasperReportUtil.getJasperFileDir(fileName);
        String pdfPath = JasperReportUtil.getPdfFileDir(fileName);
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("Parameter1","18068161321");
        params.put("Parameter2","1225631395");
        params.put("Parameter3","1466027294");
        params.put("Parameter4","wikipedia@feiliki.com");
        try {
            JRDataSource dataSource =new JREmptyDataSource();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperPath,params,dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint,pdfPath);
            PrintUtil.printPdf(pdfPath,"ZDesigner ZT230-200dpi ZPL (副本 1)");
        } catch (JRException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
