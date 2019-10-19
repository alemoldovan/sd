package utcn.labs.sd.bankingservice.domain.service;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;



public class ReportDemo {
	
	private static ArrayList<String> activitiesReport = new ArrayList();
	private static boolean isDocumentEmpty = true;
	
	private ReportDemo() {
		
	}
	
	public static void addToReport(String report) {
		activitiesReport.add(report);
	}
	
	public static void createPDF() {
		Document document = new Document();
		List list = new List(List.ORDERED);
		list.setAutoindent(false);
		list.setSymbolIndent(42);
		
		for(String s: activitiesReport) {
			list.add(new ListItem(s));
		}
		try {
			PdfWriter.getInstance(document, new FileOutputStream("Report.pdf"));
			document.open();
			if(isDocumentEmpty) {
				document.add(new Paragraph("The Report for the Employee"));
				isDocumentEmpty = false;
			}
			document.add(list);
			document.close();
		}catch(DocumentException e) {
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
