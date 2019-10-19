package utcn.labs.sd.bankingservice.domain.data.converter;

import java.util.ArrayList;
import java.util.List;

import utcn.labs.sd.bankingservice.domain.data.entity.Account;
import utcn.labs.sd.bankingservice.domain.data.entity.Activity;
import utcn.labs.sd.bankingservice.domain.dto.AccountDTO;
import utcn.labs.sd.bankingservice.domain.dto.ActivityDTO;

public class ActivityConverter {

	private ActivityConverter() {
		
	}
	
	public static ActivityDTO toDto(Activity model) {
        ActivityDTO dto = null;
        if (model != null) {
        	System.out.println("ACTIVITYCONVERTER ");
            dto = new ActivityDTO(model.getIdActivity(), model.getUsername(), model.getActivityName(), model.getDate(), model.getDescription());
        }
        return dto;
    }

    public static List<ActivityDTO> toDto(List<Activity> models) {
        List<ActivityDTO> activityDtos = new ArrayList<>();
        if ((models != null) && (!models.isEmpty())) {
            for (Activity model : models) {
                activityDtos.add(toDto(model));
                
            }
        }
        return activityDtos;
    }
}
