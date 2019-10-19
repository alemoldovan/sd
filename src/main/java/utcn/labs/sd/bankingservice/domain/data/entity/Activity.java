package utcn.labs.sd.bankingservice.domain.data.entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import utcn.labs.sd.bankingservice.domain.data.entity.enums.ActivityType;

@Entity
@Table(name = "activity_table")
public class Activity {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idactivity")
	private int idActivity;
	
	@Column(name = "username")
	@NotNull(message = "username can not be null")
	private String username;
	
	@Column(name = "activity_name")
    @Enumerated(EnumType.STRING)
	@NotNull(message = "Activity Name can not be null")
	private ActivityType activityName;
	
	@Column(name = "date")
	@NotNull(message = "Date can not be null")
	private String date;
	
	@Column(name = "description")
	@NotNull(message = "Description can not be null")
	@Size(min = 1, max = 200, message = "Description length can not be bigger than 200")
	private String description;
	
	
	public Activity() {
		
	}
	
	public Activity(int idActivity, String username, ActivityType activityName, String date, String description) {
		super();
		System.out.println("activ: 1 "+ idActivity);
		this.idActivity = idActivity;
		System.out.println("activ: 2 "+username );
		this.username = username;
		System.out.println("activ: 3 "+activityName);
		this.activityName = activityName;
		System.out.println("activ: 4 "+date);
		this.date = date;
		System.out.println("activ: 5 "+description);
		this.description = description;
	}

	public int getIdActivity() {
		return idActivity;
	}

	public void setIdActivity(int idActivity) {
		this.idActivity = idActivity;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ActivityType getActivityName() {
		return activityName;
	}

	public void setActivityName(ActivityType activityName) {
		this.activityName = activityName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Date getTheDate() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date = null;
		try {
			 date = format.parse(this.date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	  @Override
	    public String toString() {
	        final StringBuilder sb = new StringBuilder("Activity{");
	        sb.append("idActivity='").append(idActivity).append('\'');
	        sb.append(", username='").append(username).append('\'');
	        sb.append(", activityName='").append(activityName).append('\'');
	        sb.append(", date='").append(date).append('\'');
	        sb.append(", description='").append(description);
	        sb.append('}');
	        return sb.toString();
	    }

}


