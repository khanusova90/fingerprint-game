package cz.hanusova.fingerprintgame.fingerprint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by khanusova on 10.1.2017.
 */
public class Fingerprint implements Serializable {

	private String level;

	private int x;
	private int y;
	private String description;
	private List<WifiScan> wifiScans = new ArrayList<>();
	private List<BleScan> bleScans = new ArrayList<>();
	private List<CellScan> cellScans = new ArrayList<>();

	// other recorded stuff...
	private float accX, accY, accZ, gyroX, gyroY, gyroZ, magX, magY, magZ;
	private String board, bootloader, brand, device, display, fingerprint, hardware, host, osId, manufacturer, model,
			product, serial, tags, type, user;
	private boolean supportsBLE;
	private String deviceID; // IMEI...

	private Float lat, lon;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdDate;

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the lat
	 */
	public Float getLat() {
		return lat;
	}

	/**
	 * @param lat
	 *            the lat to set
	 */
	public void setLat(Float lat) {
		this.lat = lat;
	}

	/**
	 * @return the lon
	 */
	public Float getLon() {
		return lon;
	}

	/**
	 * @param lon
	 *            the lon to set
	 */
	public void setLon(Float lon) {
		this.lon = lon;
	}

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the wifiScans
	 */
	public List<WifiScan> getWifiScans() {
		return wifiScans;
	}

	/**
	 * @return the bleScans
	 */
	public List<BleScan> getBleScans() {
		return bleScans;
	}

	/**
	 * @return the cellScans
	 */
	public List<CellScan> getCellScans() {
		return cellScans;
	}

	/**
	 * @return the accX
	 */
	public float getAccX() {
		return accX;
	}

	/**
	 * @return the accY
	 */
	public float getAccY() {
		return accY;
	}

	/**
	 * @return the accZ
	 */
	public float getAccZ() {
		return accZ;
	}

	/**
	 * @return the gyroX
	 */
	public float getGyroX() {
		return gyroX;
	}

	/**
	 * @return the gyroY
	 */
	public float getGyroY() {
		return gyroY;
	}

	/**
	 * @return the gyroZ
	 */
	public float getGyroZ() {
		return gyroZ;
	}

	/**
	 * @return the magX
	 */
	public float getMagX() {
		return magX;
	}

	/**
	 * @return the magY
	 */
	public float getMagY() {
		return magY;
	}

	/**
	 * @return the magZ
	 */
	public float getMagZ() {
		return magZ;
	}

	/**
	 * @return the board
	 */
	public String getBoard() {
		return board;
	}

	/**
	 * @return the bootloader
	 */
	public String getBootloader() {
		return bootloader;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @return the device
	 */
	public String getDevice() {
		return device;
	}

	/**
	 * @return the display
	 */
	public String getDisplay() {
		return display;
	}

	/**
	 * @return the fingerprint
	 */
	public String getFingerprint() {
		return fingerprint;
	}

	/**
	 * @return the hardware
	 */
	public String getHardware() {
		return hardware;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @return the osId
	 */
	public String getOsId() {
		return osId;
	}

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * @return the serial
	 */
	public String getSerial() {
		return serial;
	}

	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @return the supportsBLE
	 */
	public boolean isSupportsBLE() {
		return supportsBLE;
	}

	/**
	 * @return the deviceID
	 */
	public String getDeviceID() {
		return deviceID;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	public Fingerprint() {
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setAccX(float accX) {
		this.accX = accX;
	}

	public void setAccY(float accY) {
		this.accY = accY;
	}

	public void setAccZ(float accZ) {
		this.accZ = accZ;
	}

	public void setGyroX(float gyroX) {
		this.gyroX = gyroX;
	}

	public void setGyroY(float gyroY) {
		this.gyroY = gyroY;
	}

	public void setGyroZ(float gyroZ) {
		this.gyroZ = gyroZ;
	}

	public void setMagX(float magX) {
		this.magX = magX;
	}

	public void setMagY(float magY) {
		this.magY = magY;
	}

	public void setMagZ(float magZ) {
		this.magZ = magZ;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public void setBootloader(String bootloader) {
		this.bootloader = bootloader;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}

	public void setHardware(String hardware) {
		this.hardware = hardware;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setOsId(String osId) {
		this.osId = osId;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	@Override
	public String toString() {
		return "{" + ",\"level\":" + level + ",\"x\":" + x + ",\"y\":" + y + ",\"description\":" + "\"" + description
				+ "\"" + ",\"wifiScans\":" + wifiScans + ",\"cellScans\":" + cellScans + ",\"supportsBLE\":"
				+ supportsBLE + ",\"bleScans\":" + bleScans + ",\"accX\":" + accX + ",\"accY\":" + accY + ",\"accZ\":"
				+ accZ + ",\"gyroX\":" + gyroX + ",\"gyroY\":" + gyroY + ",\"gyroZ\":" + gyroZ + ",\"magX\":" + magX
				+ ",\"magY\":" + magY + ",\"magZ\":" + magZ + ",\"board\":" + "\"" + board + "\"" + ",\"bootloader\":"
				+ "\"" + bootloader + "\"" + ",\"brand\":" + "\"" + brand + "\"" + ",\"device\":" + "\"" + device + "\""
				+ ",\"display\":" + "\"" + display + "\"" + ",\"cz.hanusova.fingerprint_game.model.fingerprint\":"
				+ "\"" + fingerprint + "\"" + ",\"hardware\":" + "\"" + hardware + "\"" + ",\"host\":" + "\"" + host
				+ "\"" + ",\"osId\":" + "\"" + osId + "\"" + ",\"manufacturer\":" + "\"" + manufacturer + "\""
				+ ",\"model\":" + "\"" + model + "\"" + ",\"product\":" + "\"" + product + "\"" + ",\"serial\":" + "\""
				+ serial + "\"" + ",\"tags\":" + "\"" + tags + "\"" + ",\"type\":" + "\"" + type + "\"" + ",\"user\":"
				+ "\"" + user + "\"" + ",\"deviceID\":" + "\"" + deviceID + "\"" + ",\"lat\":" + lat + ",\"lon\":" + lon
				+ ",\"createdDate\":" + createdDate + "}";
	}

	public void setWifiScans(List<WifiScan> wifiScans) {
		this.wifiScans = wifiScans;
	}

	public void setBleScans(List<BleScan> bleScans) {
		this.bleScans = bleScans;
	}

	public void setSupportsBLE(boolean supportsBLE) {
		this.supportsBLE = supportsBLE;
	}

	public void setCellScans(List<CellScan> cellScans) {
		this.cellScans = cellScans;
	}
}
