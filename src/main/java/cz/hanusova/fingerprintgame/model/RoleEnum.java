package cz.hanusova.fingerprintgame.model;

/**
 * Uzivatelske role
 * 
 * <p>
 * <b>ROLE_ADMIN</b> - administratorska prava, ma pristup vsude <br/>
 * <b>ROLE_USER</b> - role urcena pro bezne uzivatele aplikace, nema pristup k
 * nekterym strankam
 * </p>
 * 
 * @author hanuska1
 *
 */
@Deprecated
public enum RoleEnum {

	ROLE_ADMIN("ROLE_ADMIN"), ROLE_USER("ROLE_USER");

	private String role;

	private RoleEnum(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return role;
	}
}
