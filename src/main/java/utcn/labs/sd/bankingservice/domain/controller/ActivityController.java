package utcn.labs.sd.bankingservice.domain.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import utcn.labs.sd.bankingservice.core.configuration.SwaggerTags;
import utcn.labs.sd.bankingservice.domain.data.entity.enums.ReportType;
import utcn.labs.sd.bankingservice.domain.dto.AccountDTO;
import utcn.labs.sd.bankingservice.domain.dto.ActivityDTO;
import utcn.labs.sd.bankingservice.domain.service.ActivityService;
import utcn.labs.sd.bankingservice.domain.service.Report;
import utcn.labs.sd.bankingservice.domain.service.ReportFactory;

@Api(tags = {SwaggerTags.BANKING_SERVICE_TAG})
@RestController
@RequestMapping("/bank/admin/activity")
@CrossOrigin
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	@ApiOperation(value = "getAllActivities", tags = SwaggerTags.ACTIVITY_TAG)
    @RequestMapping(method = RequestMethod.GET)
    public List<ActivityDTO> getAllActivities() {
    	System.out.println("one");
        return activityService.getAllActivities();
    }

    @ApiOperation(value = "findActivityById", tags = SwaggerTags.ACTIVITY_TAG)
    @GetMapping(value = "/{activityId}")
    public ResponseEntity<?> findActivityById(@PathVariable("activityId") Integer activityId) {
        try {
            ActivityDTO activityDto = activityService.getActivityById(activityId);
            return new ResponseEntity<>(activityDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "insertActivity", tags = SwaggerTags.ACTIVITY_TAG)
    @PostMapping
    public ResponseEntity<?> insertActivity(@RequestBody ActivityDTO activityDto) {
        ActivityDTO activityDtoToBeInserted;
        try {
        	 System.out.println("3");
            activityDtoToBeInserted = activityService.createActivity(activityDto);
        } catch (Exception e) {
        	 System.out.println("4");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(activityDtoToBeInserted, HttpStatus.CREATED);
    }

    @ApiOperation(value = "updateActivity", tags = SwaggerTags.ACTIVITY_TAG)
    @PutMapping(value = "/{activityId}")
    public ResponseEntity<?> updateAccount(@PathVariable("activityId") Integer activityId, @RequestBody ActivityDTO activityDto) {
        try {
            activityService.updateActivity(activityId, activityDto);
            return new ResponseEntity<>(activityDto, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "deleteActivity", tags = SwaggerTags.ACTIVITY_TAG)
    @DeleteMapping(value = "/{activityId}")
    public ResponseEntity<?> deleteAccount(@PathVariable("activityId") Integer activityId) {
        try {
            activityService.deleteActivity(activityId);
            return new ResponseEntity<Integer>(HttpStatus.OK);
        } catch (NotFoundException ne) {
            return new ResponseEntity<>(ne.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    
    @ApiOperation(value = "generateReport", tags = SwaggerTags.ACTIVITY_TAG)
    @GetMapping(value = "/{date1}/{date2}/{username}/{reportType}")
    public ResponseEntity<?>getActivitiesByDate(@PathVariable("date1") String date1, @PathVariable("date2") String date2,
    		@PathVariable("username") String username, @PathVariable("reportType") ReportType reportType){
    	
    	 try {
    		 System.out.println(date1);
    		 System.out.println(date2);
    		 System.out.println(username);
    	    List<ActivityDTO> activityDtos = activityService.getActivitiesByDates(date1, date2, username);
			ReportFactory rf = new ReportFactory();
    	    Report r = rf.chooseReport(reportType);
    	    for(ActivityDTO a:activityDtos) {
    	    	r.addToReport(a);
    	    }
    	    ByteArrayInputStream input = r.generateReport();
    	    String filename = date1 + "to" + date2 + "." + reportType;
    	    HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition","inline; filename=" + filename);
			MediaType mediaType;
			if(reportType == ReportType.PDF) mediaType = MediaType.APPLICATION_PDF;
			else mediaType = new MediaType("text", "csv");
    	    
    	    return ResponseEntity
    	    		.ok()
    	    		.headers(headers)
    	    		.contentType(mediaType)
    	    		.body(new InputStreamResource(input));
    	 }catch (NotFoundException ne) {
             return new ResponseEntity<>(ne.getMessage(), HttpStatus.NOT_FOUND);
         } catch (Exception ex) {
             return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
         }
		
    }

}
