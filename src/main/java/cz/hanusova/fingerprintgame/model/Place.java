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
 * Clas representing a place in game <br />
 * There are materials for mining available. Every place has unique code
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
	 * Code of place
	 */
	@NotNull
	@Column(name = "CODE", unique = true)
	private String code;

	/**
	 * Place name
	 */
	private String name;

	/**
	 * Place description
	 */
	private String description;

	/**
	 * Type of place
	 */
	@ManyToOne
	@JoinColumn(name = "ID_PLACE_TYPE")
	@NotNull
	private PlaceType placeType;

	/**
	 * Material available on this place. Could be null
	 */
	@ManyToOne
	@JoinColumn(name = "ID_MATERIAL")
	private Material material;

	/**
	 * Floof that place belongs to
	 */
	private Integer floor;

	/**
	 * X coordinations on original map
	 */
	@Column(name = "X_COORD")
	private Integer xCoord;

	/**
	 * Y coordinations on original map
	 */
	@Column(name = "Y_COORD")
	private Integer yCoord;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public PlaceType getPlaceType() {
		return placeType;
	}

	public void setPlaceType(PlaceType placeType) {
		this.placeType = placeType;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof Place))
			return false;
		Place object = (Place) obj;

		return this.getCode().equals(object.getCode());
	}

}
