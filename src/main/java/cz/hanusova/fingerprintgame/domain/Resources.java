package cz.hanusova.fingerprintgame.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Trida reprezentujici zdroje materialu k tezbe
 * 
 * @author Katerina Hanusova
 *
 */
@Entity
public class Resources {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idResources;

	/**
	 * Misto, na kterem se zdroj materialu nachazi
	 */
	@ManyToOne
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
