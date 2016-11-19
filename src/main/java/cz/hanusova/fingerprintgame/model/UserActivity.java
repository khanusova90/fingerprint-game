package cz.hanusova.fingerprintgame.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

	@Enumerated(EnumType.STRING)
	private ActivityEnum activity;

	@ManyToOne
	@JoinColumn(name = "MATERIAL")
	private Material material;

	// @Column(name = "MATERIAL_AMOUNT")
	private Float materialAmount;

	// @ManyToOne
	// @JoinColumn(name = "ID_ACTIVITY")
	// private Activity activity;

	// @Column(name = "START_TIME")
	private Date startTime;

	// @Column(name = "MATERIAL_USED")
	// private MaterialEnum materialUsed;

	/*
	 * Getters and setters
	 */
	public Long getIdUserActivity() {
		return idUserActivity;
	}

	public void setIdUserActivity(Long idUserActivity) {
		this.idUserActivity = idUserActivity;
	}

	/**
	 * @return the activity
	 */
	public ActivityEnum getActivity() {
		return activity;
	}

	/**
	 * @param activity
	 *            the activity to set
	 */
	public void setActivity(ActivityEnum activity) {
		this.activity = activity;
	}
	// public Activity getActivity() {
	// return activity;
	// }
	//
	// public void setActivity(Activity activity) {
	// this.activity = activity;
	// }

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	// public MaterialEnum getMaterialUsed() {
	// return materialUsed;
	// }
	//
	// public void setMaterialUsed(MaterialEnum materialUsed) {
	// this.materialUsed = materialUsed;
	// }

	public Float getMaterialAmount() {
		return materialAmount;
	}

	public void setMaterialAmount(Float materialAmount) {
		this.materialAmount = materialAmount;
	}

}
