package cz.hanusova.fingerprintgame.fingerprint;

import java.io.Serializable;

/**
 * Created by khanusova on 10.1.2017.
 */
public class BleScan implements Serializable {

	// FIXME: REFACTOR NEEDED!
	int rssi;
	String uuid = "";
	int major, minor;
	String address = "";
	long time;

	public BleScan() {
	}

	@Override
	public String toString() {
		return "{" + "\"rssi\":" + rssi + ",\"uuid\":" + "\"" + uuid + "\"" + ",\"major\":" + major + ",\"minor\":"
				+ minor + ",\"address\":" + "\"" + address + "\"" + ",\"time\":" + time + "}";
	}

	public void setRssi(int rssi) {
		this.rssi = rssi;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setMajor(int major) {
		this.major = major;
	}

	public void setMinor(int minor) {
		this.minor = minor;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setTime(long time) {
		this.time = time;
	}

	/**
	 * @return the rssi
	 */
	public int getRssi() {
		return rssi;
	}

	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @return the major
	 */
	public int getMajor() {
		return major;
	}

	/**
	 * @return the minor
	 */
	public int getMinor() {
		return minor;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}
}
