package cz.hanusova.fingerprintgame.fingerprint;

import java.io.Serializable;

/**
 * Wifi scanned for fingerprints
 * 
 * Created by khanusova on 10.1.2017.
 */
public class WifiScan implements Serializable {

	// FIXME: refactor

	private String SSID, MAC, technology;
	private int frequency, channel, strength;
	private long time;

	public void setTime(long time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "{" + "\"SSID\":" + "\"" + SSID + "\"" + ",\"MAC\":" + "\"" + MAC + "\"" + ",\"technology\":"
				+ "\"802.11" + technology + "\"" + ",\"frequency\":" + frequency + ",\"channel\":" + channel
				+ ",\"strength\":" + strength + ",\"time\":" + time + "}";
	}

	/**
	 * @return the sSID
	 */
	public String getSSID() {
		return SSID;
	}

	/**
	 * @param sSID
	 *            the sSID to set
	 */
	public void setSSID(String sSID) {
		SSID = sSID;
	}

	/**
	 * @return the mAC
	 */
	public String getMAC() {
		return MAC;
	}

	/**
	 * @param MAC
	 *            the MAC to set
	 */
	public void setMAC(String MAC) {
		this.MAC = MAC;
	}

	/**
	 * @return the technology
	 */
	public String getTechnology() {
		return technology;
	}

	/**
	 * @param technology
	 *            the technology to set
	 */
	public void setTechnology(String technology) {
		this.technology = technology;
	}

	/**
	 * @return the frequency
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * @param frequency
	 *            the frequency to set
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	 * @return the channel
	 */
	public int getChannel() {
		return channel;
	}

	/**
	 * @param channel
	 *            the channel to set
	 */
	public void setChannel(int channel) {
		this.channel = channel;
	}

	/**
	 * @return the strength
	 */
	public int getStrength() {
		return strength;
	}

	/**
	 * @param strength
	 *            the strength to set
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}

	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}
}
