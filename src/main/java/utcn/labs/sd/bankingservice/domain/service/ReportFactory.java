package utcn.labs.sd.bankingservice.domain.service;

import java.util.List;

import utcn.labs.sd.bankingservice.domain.data.entity.enums.ReportType;
import utcn.labs.sd.bankingservice.domain.dto.ActivityDTO;

public class ReportFactory {

	public ReportFactory(){
		
	}
	public Report chooseReport(ReportType reportType) {
		if(reportType == ReportType.PDF) {
			return new PDFReport();
		}else if(reportType == ReportType.CSV) {
			return new CSVReport();
		}
			
		return null;
	}
}
