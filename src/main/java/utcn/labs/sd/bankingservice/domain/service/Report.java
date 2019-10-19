package utcn.labs.sd.bankingservice.domain.service;

import java.io.ByteArrayInputStream;

import utcn.labs.sd.bankingservice.domain.dto.ActivityDTO;

public interface Report {
	
	public void addToReport(ActivityDTO acivDTO);
	public ByteArrayInputStream generateReport();
	
	

}
