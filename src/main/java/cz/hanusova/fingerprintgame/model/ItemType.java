/**
 * 
 */
package cz.hanusova.fingerprintgame.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

/**
 * Type of item to increase material gaining
 * 
 * @author khanusova
 *
 */
@Entity
@Immutable
@Table(name = "ITEM_TYPE")
public class ItemType implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ITEM_TYPE")
	private Long idItemType;

	@Column(name = "NAME")
	private String name;

	@Enumerated(EnumType.STRING)
	private ActivityEnum activity;

	@ManyToOne
	@JoinColumn(name = "ID_MATERIAL")
	private Material material;

	/**
	 * @return the idItemType
	 */
	public Long getIdItemType() {
		return idItemType;
	}

	/**
	 * @param idItemType
	 *            the idItemType to set
	 */
	public void setIdItemType(Long idItemType) {
		this.idItemType = idItemType;
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

}
