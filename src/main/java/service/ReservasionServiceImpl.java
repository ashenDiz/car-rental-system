package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Reservation;
import utill.DbConnectionUtill;


public class ReservasionServiceImpl implements ReservasionService {
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	private static PreparedStatement preparedStatement = null;
	private static Reservation reservation = null;
	private static List<Reservation> resvList = null;
	@Override
	public boolean addReservation(Reservation r) {
		// TODO Auto-generated method stub
		boolean flag = false;
		System.out.println(r.toString());
		String sql = "INSERT INTO `reservation` (`id`, `user_id`, `address`, `duration`, `rent_date`, `add_id`, `payment_complete`) VALUES ( NULL,"+r.getUser_id()+", '"+r.getAddress()+"', "+r.getDuration()+", '"+r.getRent_date()+"', "+r.getAdd_id()+", "+r.isPayment_completed()+")";		
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
	public boolean updateReservation(Reservation r) {
		// TODO Auto-generated method stub
		boolean flag = false;
		System.out.println("update data :"+r.toString());
		String sql = "UPDATE `reservation` SET `address` = '"+r.getAddress()+"', `duration` = "+r.getDuration()+", `rent_date` = '"+r.getRent_date()+"', `payment_complete` = "+r.isPayment_completed()+" WHERE `id` = "+r.getId();
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
	public boolean deleteReservation(int id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String sql = "DELETE FROM reservation WHERE id ="+id;
		
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
	public List<Reservation> getAllReservation() {
		// TODO Auto-generated method stub
		resvList = new ArrayList<>();
		String sql = "SELECT * FROM reservation";
		
		try {
			connection = DbConnectionUtill.createConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				int id = Integer.parseInt(resultSet.getString("id"));
				int user_id = Integer.parseInt(resultSet.getString("user_id"));
				int add_id = Integer.parseInt(resultSet.getString("add_id"));
				String address = resultSet.getString("address");
				int duration = Integer.parseInt(resultSet.getString("duration"));
				String rent_date = resultSet.getString("rent_date");
				boolean payment_complete = resultSet.getBoolean("payment_complete");
				
				reservation = new Reservation(user_id, address, duration, rent_date, add_id, payment_complete);
				reservation.setId(id);
				resvList.add(reservation);
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
		
		return resvList;
	}

	@Override
	public Reservation getReservationById(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM reservation WHERE id = "+id;
		try {
			connection = DbConnectionUtill.createConnection();
			statement = connection.createStatement();
			System.out.println("came to query area ");
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				int user_id = Integer.parseInt(resultSet.getString("user_id"));
				int add_id = Integer.parseInt(resultSet.getString("add_id"));
				String address = resultSet.getString("address");
				int duration = Integer.parseInt(resultSet.getString("duration"));
				String rent_date = resultSet.getString("rent_date");
				boolean payment_complete = resultSet.getBoolean("payment_complete");
				
				
				reservation = new Reservation(user_id, address, duration, rent_date, add_id, payment_complete);
				reservation.setId(id);
				System.out.println(reservation.toString());
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
		return reservation;
	}

	@Override
	public boolean updatePaymentStatus(int id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String sql = "UPDATE `reservation` SET `payment_complete` = 1 WHERE `id` = "+id;
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

}
