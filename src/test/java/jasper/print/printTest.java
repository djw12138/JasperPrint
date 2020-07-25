package jasper.print;

import jasper.util.PrintUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.exolab.castor.dsml.XML;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class printTest {

    private static final String path= "F:/JasperPrint/";
    private static final String jasperPath= "jasper/";
    private static final String pdfPath= "pdf/";

    @Test
    public void printJasper(){
        String jasperName = "test.jasper";
        String pdfName = "test.pdf";
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("Parameter1","18068161321");
        params.put("Parameter2","1225631395");
        params.put("Parameter3","1466027294");
        params.put("Parameter4","wikipedia@feiliki.com");
        try {
            JRDataSource dataSource =new JREmptyDataSource();
            JasperPrint jasperPrint = JasperFillManager.fillReport(path+jasperPath+jasperName,params,dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+pdfPath+pdfName);
            PrintUtil.printPdf(path+pdfPath+pdfName,"ZDesigner ZT230-200dpi ZPL (副本 1)");
        } catch (JRException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
