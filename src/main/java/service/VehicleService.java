package service;

import java.util.List;

import model.Vehicle;

public interface VehicleService {
	public boolean addVehicle(Vehicle v);
	public boolean updateVehicle(Vehicle v);
	public boolean deleteVehicle(int id);
	public List<Vehicle> getAllVehicle();
	public Vehicle getVehicleById(int id);
}
