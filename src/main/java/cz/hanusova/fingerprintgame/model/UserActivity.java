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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Currently running activity
 * 
 * @author khanusova
 *
 */
@Entity
@Table(name = "USER_ACTIVITY")
public class UserActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USER_ACTIVITY")
	private Long idUserActivity;

	@ManyToOne
	@JoinColumn(name = "ID_MATERIAL")
	private Material material;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "ID_PLACE")
	private Place place;

	@Column(name = "MATERIAL_AMOUNT")
	private Float materialAmount;

	@NotNull
	@Column(name = "START_TIME")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;

	@Column(name = "STOP_TIME")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date stopTime;

	/**
	 * Creates new UserActivity started at actual date
	 */
	public UserActivity(Place place, Float materialAmount) {
		this.startTime = new Date();
		this.place = place;
		this.materialAmount = materialAmount;
	}

	public UserActivity() {
	}

	/*
	 * Getters and setters
	 */
	public Long getIdUserActivity() {
		return idUserActivity;
	}

	public void setIdUserActivity(Long idUserActivity) {
		this.idUserActivity = idUserActivity;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * @param material
	 *            the material to set
	 */
	public void setMaterial(Material material) {
		this.material = material;
	}

	/**
	 * @return the place
	 */
	public Place getPlace() {
		return place;
	}

	/**
	 * @param place
	 *            the place to set
	 */
	public void setPlace(Place place) {
		this.place = place;
	}

	public Float getMaterialAmount() {
		return materialAmount;
	}

	public void setMaterialAmount(Float materialAmount) {
		this.materialAmount = materialAmount;
	}

}
