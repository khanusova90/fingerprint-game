package cz.hanusova.fingerprintgame.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Deprecated
// @Entity
// @Table(name = "C_ACTIVITY")
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ACTIVITY")
	private Long idActivity;
	private String name;
	private MaterialEnum material;

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

	public MaterialEnum getMaterial() {
		return material;
	}

	public void setMaterial(MaterialEnum material) {
		this.material = material;
	}

}
