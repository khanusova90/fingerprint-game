package cz.hanusova.fingerprintgame.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Class representing materials available in game
 * 
 * @author khanusova
 *
 */
@Entity
@Table(name = "MATERIAL")
public class Material implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_MATERIAL")
	private Long idMaterial;

	/**
	 * Name of the material
	 */
	@NotNull
	private String name;

	/**
	 * @return the idMaterial
	 */
	public Long getIdMaterial() {
		return idMaterial;
	}

	/**
	 * @param idMaterial
	 *            the idMaterial to set
	 */
	public void setIdMaterial(Long idMaterial) {
		this.idMaterial = idMaterial;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
