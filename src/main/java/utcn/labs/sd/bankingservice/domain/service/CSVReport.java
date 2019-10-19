package utcn.labs.sd.bankingservice.domain.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utcn.labs.sd.bankingservice.domain.dto.ActivityDTO;




public class CSVReport implements Report {

	private static ArrayList<ActivityDTO> activitiesReport;
	private static final char DEFAULT_SEPARATOR = ',';
	
	CSVReport(){
		activitiesReport = new ArrayList<ActivityDTO>();
	
	}
	
	public void addToReport(ActivityDTO report) {
		activitiesReport.add(report);
	}
	
	
	public ByteArrayInputStream generateReport() {
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try(PrintWriter writer = new PrintWriter(output);) {
			
			try {
				this.writeLine(writer, Arrays.asList("ActivityId", "Username", "ActionName", "Date", "Decription"));

			}catch(IOException e) {
				e.getStackTrace();
				
			}
			
			for(ActivityDTO a:activitiesReport) {
				List<String> list = new ArrayList<String>();
				
				String idAc = ""+a.getIdActivity();
				list.add(idAc);
				list.add(a.getUsername());
				String ActivityName = ""+a.getActivityName() ;
				list.add(ActivityName);
				list.add(a.getDate());
				list.add(a.getDescription());
			
				try {
					writeLine(writer,list);
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			writer.flush();
			writer.close();
			
		}
		return new ByteArrayInputStream(output.toByteArray());
	}
	
	
	public static void writeLine(Writer w, List<String> values) throws IOException {
        writeLine(w, values, DEFAULT_SEPARATOR, ' ');
    }

    public static void writeLine(Writer w, List<String> values, char separators) throws IOException {
        writeLine(w, values, separators, ' ');
    }

    //https://tools.ietf.org/html/rfc4180
    private static String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }
    
    public static void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {

        boolean first = true;

        //default customQuote is empty

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(followCVSformat(value));
            } else {
                sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
            }

            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());


    }

}
