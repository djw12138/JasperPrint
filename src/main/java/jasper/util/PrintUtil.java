package jasper.util;

import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import java.io.File;
import java.io.FileInputStream;

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
        DocFlavor flavor = DocFlavor.INPUT_STREAM.PDF;
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
}
