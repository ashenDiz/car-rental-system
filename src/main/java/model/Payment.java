package model;

public class Payment {
	private String card_no;
	private String card_name;
	private int cvv;
	private String exp_date;
	private int id;
	private int res_id;
	
	
	public Payment(String card_no, String card_name, int cvv, String exp_date, int res_id) {
		super();
		this.card_no = card_no;
		this.card_name = card_name;
		this.cvv = cvv;
		this.exp_date = exp_date;
		this.res_id = res_id;
	}
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public String getCard_name() {
		return card_name;
	}
	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public String getExp_date() {
		return exp_date;
	}
	public void setExp_date(String exp_date) {
		this.exp_date = exp_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRes_id() {
		return res_id;
	}
	public void setRes_id(int res_id) {
		this.res_id = res_id;
	}

	
	
}
