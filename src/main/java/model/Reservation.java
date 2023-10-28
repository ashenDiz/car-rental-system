package model;

public class Reservation {

	private int user_id;
	private String address;
	private int duration;
	private String rent_date;
	private int add_id;
	private boolean payment_completed;
	private int payment_id;
	private int id;
	
	public Reservation(int user_id, String address, int duration, String rent_date, int add_id,
			boolean payment_completed) {
		super();
		this.user_id = user_id;
		this.address = address;
		this.duration = duration;
		this.rent_date = rent_date;
		this.add_id = add_id;
		this.payment_completed = payment_completed;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getRent_date() {
		return rent_date;
	}
	public void setRent_date(String rent_date) {
		this.rent_date = rent_date;
	}
	public int getAdd_id() {
		return add_id;
	}
	public void setAdd_id(int add_id) {
		this.add_id = add_id;
	}
	public boolean isPayment_completed() {
		return payment_completed;
	}
	public void setPayment_completed(boolean payment_completed) {
		this.payment_completed = payment_completed;
	}
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Reservation [user_id=" + user_id + ", address=" + address + ", duration=" + duration + ", rent_date="
				+ rent_date + ", add_id=" + add_id + ", payment_completed=" + payment_completed + ", payment_id="
				+ payment_id + ", id=" + id + "]";
	}
	
}
