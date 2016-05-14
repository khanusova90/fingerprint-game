package cz.hanusova.fingerprintgame.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "C_PLACE_TYPE")
public class PlaceType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PLACE_TYPE")
	private Long idPlaceType;

	@Column(name = "PLACE_TYPE")
	private String placeType;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "IMG_URL")
	private String imgUrl;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "PLACE_TYPE_ACTIVITY", joinColumns = {
			@JoinColumn(name = "ID_PLACE_TYPE") }, inverseJoinColumns = { @JoinColumn(name = "ID_ACTIVITY") })
	private List<Activity> activities = new ArrayList<>();

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
