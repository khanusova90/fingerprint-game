package cz.hanusova.fingerprintgame.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ACTIVITY")
public class UserActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USER_ACTIVITY")
	private Long idUserActivity;
	// private AppUser appUser;

	// TODO: foreign
	@ManyToOne
	@JoinColumn(name = "ID_ACTIVITY")
	private Activity activity;

	@Column(name = "START_TIME")
	private Date startTime;

	@Column(name = "MATERIAL_USED")
	private String materialUsed;

	@Column(name = "MATERIAL_AMOUNT")
	private Float materialAmount;

	public Long getIdUserActivity() {
		return idUserActivity;
	}

	public void setIdUserActivity(Long idUserActivity) {
		this.idUserActivity = idUserActivity;
	}

	// public AppUser getAppUser() {
	// return appUser;
	// }
	//
	// public void setAppUser(AppUser appUser) {
	// this.appUser = appUser;
	// }

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getMaterialUsed() {
		return materialUsed;
	}

	public void setMaterialUsed(String materialUsed) {
		this.materialUsed = materialUsed;
	}

	public Float getMaterialAmount() {
		return materialAmount;
	}

	public void setMaterialAmount(Float materialAmount) {
		this.materialAmount = materialAmount;
	}

}
