package service;

import java.util.List;

import model.Payment;



public interface PaymentService {
	public boolean addPayment(Payment p);
	public boolean updatePayment(Payment p);
	public boolean deletePayment(int id);
	public List<Payment> getAllPayment();
	public Payment getPaymentById(int id);
}
