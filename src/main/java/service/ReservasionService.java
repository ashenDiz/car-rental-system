package service;

import java.util.List;

import model.Reservation;


public interface ReservasionService {
	public boolean addReservation(Reservation r);
	public boolean updateReservation(Reservation r);
	public boolean deleteReservation(int id);
	public List<Reservation> getAllReservation();
	public Reservation getReservationById(int id);
	public boolean updatePaymentStatus(int id);
}
