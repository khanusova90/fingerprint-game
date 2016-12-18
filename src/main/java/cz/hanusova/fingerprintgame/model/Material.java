package cz.hanusova.fingerprintgame.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Immutable;

/**
 * Class representing materials available in game
 * 
 * @author khanusova
 *
 */
@Entity
@Immutable
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
	 * Default amount of material for new users
	 */
	@NotNull
	@Column(name = "DEFAULT_AMOUNT")
	private Integer defaultAmount;

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

	/**
	 * @return the defaultAmount
	 */
	public Integer getDefaultAmount() {
		return defaultAmount;
	}

	/**
	 * @param defaultAmount
	 *            the defaultAmount to set
	 */
	public void setDefaultAmount(Integer defaultAmount) {
		this.defaultAmount = defaultAmount;
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
		if (!(obj instanceof Material))
			return false;
		Material object = (Material) obj;

		return this.getIdMaterial() == object.getIdMaterial();

	}

}
