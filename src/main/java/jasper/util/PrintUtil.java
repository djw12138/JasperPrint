package jasper.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;

import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import java.awt.print.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PrintUtil {

    /**
     * 通过本机默认打印机打印pdf文件
     * @param filePath 文件路径
     * @throws Exception
     */
    public static void defaultPrintPDF(String filePath) throws Exception{
        System.out.print("打印工具类入參：filePath==================="+filePath);
        File file = new File(filePath); // 获取选择的文件
        // 构建打印请求属性集
        HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        // 设置打印格式，因为未确定类型，所以选择autosense
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        System.out.print("打印文件类型为：==================="+flavor);
        //pras.add(MediaName.ISO_A4_TRANSPARENT);//A4纸张
        //遍历
//	      PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras); 
//	   
//	      for (PrintService printService2 : printService) {
//	    	  System.out.print("本机可使用打印机列表：==================="+printService2);
//			}
        // 定位默认的打印服务
        PrintService defaultService = PrintServiceLookup
                .lookupDefaultPrintService();
        System.out.print("打印工具选择打印机为：==================="+defaultService);
        try {
            DocPrintJob job = defaultService.createPrintJob(); // 创建打印作业
            FileInputStream fis = new FileInputStream(file); // 构造待打印的文件流
            DocAttributeSet das = new HashDocAttributeSet();
            Doc doc = new SimpleDoc(fis, flavor, das);
            job.print(doc, pras);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("打印异常"+e);
            throw new Exception();
        }
    }

    public static void printPdf(String pdfPath,String printName) throws IOException, PrinterException {
        boolean isChoose = false; //是否有对应打印机
        File file = new File(pdfPath);
        PDDocument document = PDDocument.load(file); // 读取pdf文件
        PrinterJob job = PrinterJob.getPrinterJob();  // 创建打印任务
        // 遍历所有打印机的名称获取到指定的打印机
        for (PrintService ps : PrinterJob.lookupPrintServices()) {
            String psName = ps.toString();
            if (psName.equals(printName)) {
                isChoose = true;
                job.setPrintService(ps);
                break;
            }
        }
        if (isChoose) {
            job.setPageable(new PDFPageable(document));
            Paper paper = new Paper();
            //paper.setSize(227, 142);// 设置打印纸张大小 ：长度(mm) * 72/25.4 我这里是80mm*50mm
            paper.setImageableArea(0, 0, paper.getWidth(), // 设置打印位置/坐标
                    paper.getHeight());
            PageFormat pageFormat = new PageFormat();
            pageFormat.setPaper(paper);
            Book book = new Book();
            // 设置一些属性 是否缩放 打印张数(document.getNumberOfPages()是pdf文件的页数)等，这里选择实际尺寸
            book.append(new PDFPrintable(document, Scaling.ACTUAL_SIZE), pageFormat, document.getNumberOfPages());
            job.setPageable(book);
            job.print();// 开始打印
        }
        //删除文件
        if (file.exists()) {
            file.delete();
        }
    }
}
