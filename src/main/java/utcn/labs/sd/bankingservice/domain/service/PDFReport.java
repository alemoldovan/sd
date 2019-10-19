package utcn.labs.sd.bankingservice.domain.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import utcn.labs.sd.bankingservice.domain.dto.ActivityDTO;

public class PDFReport implements Report{
	
	private static ArrayList<ActivityDTO> activitiesReport;;
	private static boolean isDocumentEmpty;
	
	PDFReport(){
		//this.generateReport();
		activitiesReport = new ArrayList<ActivityDTO>();
		isDocumentEmpty = true;
	}
	
	public void addToReport(ActivityDTO activityInfo) {
		activitiesReport.add(activityInfo);
	}

	public ByteArrayInputStream generateReport() {
		return createPdf();
	}
	
	
	private ByteArrayInputStream createPdf() {
		Document document = new Document();
		
		ByteArrayOutputStream outp = new ByteArrayOutputStream();
		
		List list = new List(List.ORDERED);
		list.setAutoindent(false);
		list.setSymbolIndent(42);
		
		
		for(ActivityDTO a: activitiesReport) {
			list.add(new ListItem(a.toString()));
		}
		try {
			PdfWriter.getInstance(document, outp);
			document.open();
			if(isDocumentEmpty) {
				document.add(new Paragraph("The Report for the Employee"));
				isDocumentEmpty = false;
			}
			document.add(list);
			document.close();
		}catch(DocumentException e) {
			
	}
		return new ByteArrayInputStream(outp.toByteArray());
	
		
	}

}
