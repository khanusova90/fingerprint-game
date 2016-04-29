package cz.hanusova.fingerprintgame.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	private String code;

	/**
	 * Popis mista
	 */
	private String description;

	/**
	 * Zdroje, ktere jsou k dispozici na danem miste
	 */
//	@JsonIgnore
//	@Expose(serialize=false, deserialize=false)
	@OneToMany(mappedBy="place")
	private List<Resource> resources;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

}
