package cz.hanusova.fingerprintgame.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USER")
	private AppUser user;
	private Material material;
	private BigDecimal amount;

	public Inventory(AppUser user, Material material, BigDecimal amount) {
		this.user = user;
		this.material = material;
		this.amount = amount;
	}

	public Inventory() {
	}

	public Long getIdInventory() {
		return idInventory;
	}

	public void setIdInventory(Long idInventory) {
		this.idInventory = idInventory;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
