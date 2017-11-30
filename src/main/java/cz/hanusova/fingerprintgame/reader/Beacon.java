package cz.hanusova.fingerprintgame.reader;

public class Beacon {

	private String floor;
	private String mac;
	private Integer major;
	private Integer minor;
	private Integer paper1Number;
	private String UUID;
	private Integer x;
	private Integer y;

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Integer getMajor() {
		return major;
	}

	public void setMajor(Integer major) {
		this.major = major;
	}

	public Integer getMinor() {
		return minor;
	}

	public void setMinor(Integer minor) {
		this.minor = minor;
	}

	public Integer getPaper1Number() {
		return paper1Number;
	}

	public void setPaper1Number(Integer paper1Number) {
		this.paper1Number = paper1Number;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

}
