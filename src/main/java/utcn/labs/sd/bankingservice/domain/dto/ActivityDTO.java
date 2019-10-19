package utcn.labs.sd.bankingservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import utcn.labs.sd.bankingservice.domain.data.entity.enums.ActivityType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActivityDTO {
	
	private final int idActivity;
	private final String username;
	private final ActivityType activityName;
	private final String date;
	private final String description;
	
	
	@JsonCreator
	public ActivityDTO(@JsonProperty("idActivity") int idActivity,
                      @JsonProperty("username") String username,
                      @JsonProperty("activityName") ActivityType activityName,
                      @JsonProperty("date") String date,
                      @JsonProperty("description") String description
                      ) {
		System.out.println("ACTIVDTO 1");
		this.idActivity = idActivity;
		System.out.println("ACTIVDTO 2");
		this.username = username;
		System.out.println("ACTIVDTO 3");
		this.activityName = activityName;
		System.out.println("ACTIVDTO 4");
		this.date = date;
		System.out.println("ACTIVDTO 5");
		this.description = description;
		
	}

	@JsonProperty("idActivity")
	public int getIdActivity() {
		return idActivity;
	}

	@JsonProperty("username")
	public String getUsername() {
		return username;
	}


	@JsonProperty("activityName")
	public ActivityType getActivityName() {
		return activityName;
	}


	@JsonProperty("date")
	public String getDate() {
		return date;
	}


	@JsonProperty("description")
	public String getDescription() {
		return description;
	}
	
	
	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ActivityDto{");
        sb.append("idActivity=").append(idActivity);
        sb.append(", username=").append(username);
        sb.append(", activityName='").append(activityName).append('\'');
        sb.append(", date=").append(date).append('\'');
        sb.append(", description=").append(description);
        sb.append('}');
        return sb.toString();
	}
	
	
	
	

}

