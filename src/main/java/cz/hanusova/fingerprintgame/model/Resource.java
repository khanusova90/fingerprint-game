package cz.hanusova.fingerprintgame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Trida reprezentujici zdroje materialu k tezbe zobrazene na mape
 * 
 * @author Katerina Hanusova
 *
 */
@Entity
@Table(name="RESOURCES")
public class Resource {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RESOURCES")
	private Long idResources;
	
	/*
	 * Souradnice mista
	 */
	@Column(name="X_COORD")
	private Integer x;
	@Column(name="Y_COORD")
	private Integer y;

	/**
	 * Misto, na kterem se zdroj materialu nachazi
	 */
	@ManyToOne
	@JoinColumn(name="ID_PLACE")
	private Place place;
	
	/**
	 * Druh materialu, ktery je mozne zde tezit
	 */
	private Material material;

	/*
	 * Getters and setters
	 */
	public Long getIdResources() {
		return idResources;
	}

	public void setIdResources(Long idResources) {
		this.idResources = idResources;
	}
	
	public Integer getX() {
		return x;
	}
	
	public void setX(Integer x) {
		this.x = x;
	}
	
	public Integer getY() {
		return y;
	}
	
	public void setY(Integer y) {
		this.y = y;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

}
