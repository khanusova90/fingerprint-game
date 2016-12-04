package cz.hanusova.fingerprintgame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PLACE_TYPE")
public class PlaceType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PLACE_TYPE")
	private Long idPlaceType;

	@Column(name = "PLACE_TYPE")
	@NotNull
	private String placeType;

	// @Column(name = "DESCRIPTION")
	// private String description;

	@Column(name = "IMG_URL")
	private String imgUrl;

	@Enumerated(EnumType.STRING)
	private ActivityEnum activity;

	// @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	// @JoinTable(name = "PLACE_TYPE_ACTIVITY", joinColumns = {
	// @JoinColumn(name = "ID_PLACE_TYPE") }, inverseJoinColumns = {
	// @JoinColumn(name = "ID_ACTIVITY") })
	// private List<Activity> activities = new ArrayList<>();

	// @ManyToOne
	// @JoinColumn(name = "ID_ACTIVITY")
	// private Activity activity;

	public Long getIdPlaceType() {
		return idPlaceType;
	}

	public void setIdPlaceType(Long idPlaceType) {
		this.idPlaceType = idPlaceType;
	}

	public String getPlaceType() {
		return placeType;
	}

	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}

	// public List<Activity> getActivities() {
	// return activities;
	// }
	//
	// public void setActivities(List<Activity> activities) {
	// this.activities = activities;
	// }

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	// public Activity getActivity() {
	// return activity;
	// }
	//
	// public void setActivity(Activity activity) {
	// this.activity = activity;
	// }

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

}
