package cz.hanusova.fingerprintgame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "C_ACTIVITY")
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ACTIVITY")
	private Long idActivity;
	private String name;
	private Material material;

	public Long getIdActivity() {
		return idActivity;
	}

	public void setIdActivity(Long idActivity) {
		this.idActivity = idActivity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

}
