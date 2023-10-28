package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Vehicle;
import utill.DbConnectionUtill;

public class VehicleServiceImpl implements VehicleService {
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	private static PreparedStatement preparedStatement = null;
	private static Vehicle vehicle = null;
	private static List<Vehicle> vehicleList = null;
	
	@Override
	public boolean addVehicle(Vehicle v) {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		String sql = "INSERT INTO `vehicle` (`id`, `vehicle_type`, `m_year`, `reg_year`, `fuel_type`, `capacity`, `mileage`, `price`, `img_path`,`model`) VALUES (NULL, '"+v.getVehicle_type()+"', '"+v.getM_year()+"', '"+v.getReg_year()+"', '"+v.getFuel_type()+"', '"+v.getCapacity()+"', '"+v.getMileage()+"', '"+v.getPrice()+"', '"+v.getImg_path()+"','"+v.getModel()+"')";
		
		try {
			connection = DbConnectionUtill.createConnection();
			
			preparedStatement = connection.prepareStatement(sql);
			System.out.println("came to the service");
			preparedStatement.execute();
			System.out.println("done ");
			flag = true;
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		
		return flag;
	}

	@Override
	public boolean updateVehicle(Vehicle v) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String sql = "UPDATE `vehicle` SET `vehicle_type` = '"+v.getVehicle_type()+"', `reg_year` = '"+v.getReg_year()+"', `m_year` = '"+v.getM_year()+"', `fuel_type` = '"+v.getFuel_type()+"', `capacity` = '"+v.getCapacity()+"', `mileage` = '"+v.getMileage()+"', `price` = '"+v.getPrice()+"', `img_path` = '"+v.getImg_path()+"',`model` = '"+v.getModel()+"' WHERE `vehicle`.`id` = "+v.getId();
		try {
			connection = DbConnectionUtill.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean deleteVehicle(int id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String sql = "DELETE FROM vehicle WHERE id ="+id;
		
		try {
			connection = DbConnectionUtill.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			//close the connection and preparedStatement
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public List<Vehicle> getAllVehicle() {
		// TODO Auto-generated method stub
		vehicleList = new ArrayList<>();
		String sql = "SELECT * FROM vehicle";
		
		try {
			connection = DbConnectionUtill.createConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				String vehicle_type = resultSet.getString("vehicle_type");
				String m_year = resultSet.getString("m_year");
				String reg_year = resultSet.getString("reg_year");
				String fuel_type = resultSet.getString("fuel_type");
				String capacity = resultSet.getString("capacity");
				Double mileage = resultSet.getDouble("mileage");
				Double price = resultSet.getDouble("price");
				String img_path = resultSet.getString("img_path");
				String model = resultSet.getString("model");
				int id = resultSet.getInt("id");
				
				vehicle = new Vehicle(vehicle_type, m_year, reg_year, fuel_type, capacity, mileage, price, img_path,model);
				vehicle.setId(id);
				vehicleList.add(vehicle);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return vehicleList;
	}

	@Override
	public Vehicle getVehicleById(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM vehicle WHERE id = "+id;
		try {
			connection = DbConnectionUtill.createConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				String vehicle_type = resultSet.getString("vehicle_type");
				String m_year = resultSet.getString("m_year");
				String reg_year = resultSet.getString("reg_year");
				String fuel_type = resultSet.getString("fuel_type");
				String capacity = resultSet.getString("capacity");
				Double mileage = resultSet.getDouble("mileage");
				Double price = resultSet.getDouble("price");
				String img_path = resultSet.getString("img_path");
				String model = resultSet.getString("model");
				
				vehicle = new Vehicle(vehicle_type, m_year, reg_year, fuel_type, capacity, mileage, price, img_path,model);
				vehicle.setId(id);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return vehicle;
	}
}
