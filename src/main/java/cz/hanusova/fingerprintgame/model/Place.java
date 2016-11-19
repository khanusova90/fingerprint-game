package cz.hanusova.fingerprintgame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Trida reprezentujici nejake misto ve hre. Na miste jsou k dispozici zdroje
 * surovin, ktere mohou hraci tezit. Kazde misto je oznaceno kodem.
 * 
 * @author Katerina Hanusova
 *
 */
@Entity
public class Place {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PLACE")
	private Long idPlace;

	/**
	 * Kod mista
	 */
	@NotNull
	private String code;

	/**
	 * Nazev mista
	 */
	private String name;

	/**
	 * Typ mista
	 */
	@ManyToOne
	@JoinColumn(name = "ID_PLACE_TYPE")
	@NotNull
	private PlaceType placeType;

	/**
	 * Patro, na kterem se misto nachazi
	 */
	private Integer floor;

	/**
	 * Vodorovne souradnice umisteni na mape
	 */
	@Column(name = "X_COORD")
	private Integer xCoord;

	/**
	 * Svisle souradnice umisteni na mape
	 */
	@Column(name = "Y_COORD")
	private Integer yCoord;

	/**
	 * Zdroje, ktere jsou k dispozici na danem miste
	 */
	// @OneToMany(mappedBy = "place")
	// private List<Resource> resources;

	public Long getIdPlace() {
		return idPlace;
	}

	public void setIdPlace(Long idPlace) {
		this.idPlace = idPlace;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	// public List<Resource> getResources() {
	// return resources;
	// }
	//
	// public void setResources(List<Resource> resources) {
	// this.resources = resources;
	// }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PlaceType getPlaceType() {
		return placeType;
	}

	public void setPlaceType(PlaceType placeType) {
		this.placeType = placeType;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public Integer getxCoord() {
		return xCoord;
	}

	public void setxCoord(Integer xCoord) {
		this.xCoord = xCoord;
	}

	public Integer getyCoord() {
		return yCoord;
	}

	public void setyCoord(Integer yCoord) {
		this.yCoord = yCoord;
	}

}
