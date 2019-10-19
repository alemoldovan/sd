package utcn.labs.sd.bankingservice.domain.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import utcn.labs.sd.bankingservice.domain.data.converter.AccountConverter;
import utcn.labs.sd.bankingservice.domain.data.converter.ActivityConverter;
import utcn.labs.sd.bankingservice.domain.data.entity.Account;
import utcn.labs.sd.bankingservice.domain.data.entity.Activity;
import utcn.labs.sd.bankingservice.domain.data.entity.enums.ReportType;
import utcn.labs.sd.bankingservice.domain.data.repository.ActivityRespository;
import utcn.labs.sd.bankingservice.domain.data.repository.UserRepository;
import utcn.labs.sd.bankingservice.domain.dto.AccountDTO;
import utcn.labs.sd.bankingservice.domain.dto.ActivityDTO;


@Service
public class ActivityService {

	@Autowired
	private ActivityRespository activityRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public List<ActivityDTO> getAllActivities() {
    	List<Activity> activities = activityRepository.findAll();
        return ActivityConverter.toDto(activityRepository.findAll());
    }

    public ActivityDTO getActivityById(Integer activityId) throws Exception {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        if (activity == null) throw new NotFoundException("No activity found with that activityId");
        return ActivityConverter.toDto(activity);
    }

    
    public ActivityDTO createActivity(ActivityDTO activityDto) throws Exception {
    	System.out.println("ACTIVITYSERVICE - CreateActivityDto");
        Activity activity = new Activity(activityDto.getIdActivity(), activityDto.getUsername(),  activityDto.getActivityName(), activityDto.getDate(), activityDto.getDescription());
        activityRepository.save(activity);
        System.out.println(activity.toString());
        //System.out.println(newActivity.toString());
        return ActivityConverter.toDto(activity);
    }
    
    public ActivityDTO updateActivity(Integer activityId, ActivityDTO activityDto) throws Exception {
    	Activity activity = activityRepository.findById(activityId).orElse(null);
        if (activity == null) {
            throw new NotFoundException("No activity found with that id");
        }
        
        activity.setDate(activityDto.getDate());
        activity.setActivityName(activityDto.getActivityName());
        activity.setDescription(activityDto.getDescription());
        return ActivityConverter.toDto(activityRepository.save(activity));
    }
    
    public void deleteActivity(Integer activityId) throws Exception {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        if (activity == null) {
            throw new NotFoundException("No activity with that activityId");
        }
        activityRepository.delete(activity);
    }
	
    public List<ActivityDTO> getActivitiesByDates(String date1, String date2, String username) throws Exception{
    	List<ActivityDTO> activitiesDTO = new ArrayList<ActivityDTO>();
    	List<Activity> activities = activityRepository.findAll();
    	
    	/*Pattern dateRegex=Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
    	Matcher regexMatcher = dateRegex.matcher(date1);*/
    	//if(regexMatcher.matches()) {
	    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	    	Date dateD1 =null;
	    	Date dateD2 = null;
			try {
				dateD1 = format.parse(date1);
				dateD2 = format.parse(date2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	for(Activity a:activities) {
	    		if(a.getTheDate().equals(dateD1) || a.getTheDate().equals(dateD2) || (a.getTheDate().after(dateD1) && a.getTheDate().before(dateD2))) {
	    			if(a.getUsername().equals(username) ) {
	    				ActivityDTO activityDto = new ActivityDTO(a.getIdActivity(),a.getUsername(),a.getActivityName(),a.getDate(),a.getDescription());
	    				activitiesDTO.add(activityDto);
	    			}
	    		}
	    	}
    	//}
    	
    	//ReportFactory.chooseReport(reportType,activitiesDTO );
    	
    	//List<String> activs = new ArrayList<String>();
    	
    	/*for(ActivityDTO a: activitiesDTO) {
    		//activs.add(a.toString());
    		Report.addToReport(a.toString());
    	}
    	
    	Report.createPDF();*/
    	
    	
    	return activitiesDTO;
    	
    }
    
	
}
