package model;

public class Vehicle {

	private String vehicle_type;
	private String m_year;
	private String reg_year;
	private String fuel_type;
	private String capacity;
	private Double mileage;
	private Double price;
	private String img_path;
	private int id;
	private String model;

	public Vehicle(String vehicle_type, String m_year, String reg_year, String fuel_type, String capacity,
			Double milage, Double price, String img_path, String model) {
		super();
		this.vehicle_type = vehicle_type;
		this.m_year = m_year;
		this.reg_year = reg_year;
		this.fuel_type = fuel_type;
		this.capacity = capacity;
		this.mileage = milage;
		this.price = price;
		this.img_path = img_path;
		this.model = model;
	}

	public String getVehicle_type() {
		return vehicle_type;
	}

	public void setVehicle_type(String vehicle_type) {
		this.vehicle_type = vehicle_type;
	}

	public String getM_year() {
		return m_year;
	}

	public void setM_year(String m_year) {
		this.m_year = m_year;
	}

	public String getReg_year() {
		return reg_year;
	}

	public void setReg_year(String reg_year) {
		this.reg_year = reg_year;
	}

	public String getFuel_type() {
		return fuel_type;
	}

	public void setFuel_type(String fuel_type) {
		this.fuel_type = fuel_type;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	public Double getMileage() {
		return mileage;
	}

	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicle_type=" + vehicle_type + ", m_year=" + m_year + ", reg_year=" + reg_year
				+ ", fuel_type=" + fuel_type + ", capacity=" + capacity + ", mileage=" + mileage + ", price=" + price
				+ ", img_path=" + img_path + ", id=" + id + ", model=" + model + "]";
	}

	
	
	
}
