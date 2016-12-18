package cz.hanusova.fingerprintgame.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Trida reprezentujici inventar uzivatele. Ke kazdemu typu zdroje ma ulozene
 * mnozstvi
 * 
 * @author Katerina Hanusova
 *
 */
@Entity(name = "INVENTORY")
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_INVENTORY")
	private Long idInventory;

	@NotNull
	private BigDecimal amount = BigDecimal.ZERO;

	@ManyToOne
	@JoinColumn(name = "ID_MATERIAL")
	private Material material;

	public Inventory(Material material, BigDecimal amount) {
		this.material = material;
		this.amount = amount;
	}

	/**
	 * Creates Inventory for given {@link Material} with its default value
	 * defined by {@link Material#getDefaultAmount()}
	 * 
	 * @param material
	 */
	public Inventory(Material material) {
		this.material = material;
		this.amount = new BigDecimal(material.getDefaultAmount());
	}

	public Inventory() {
	}

	public Long getIdInventory() {
		return idInventory;
	}

	public void setIdInventory(Long idInventory) {
		this.idInventory = idInventory;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
