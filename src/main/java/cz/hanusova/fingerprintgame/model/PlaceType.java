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

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "PLACE_TYPE")
public class PlaceType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PLACE_TYPE")
	private Long idPlaceType;

	@Column(name = "PLACE_TYPE")
	@NotNull
	private String placeType;

	@Column(name = "IMG_URL")
	private String imgUrl;

	@Enumerated(EnumType.STRING)
	private ActivityEnum activity;

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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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

}
