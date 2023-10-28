package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Payment;
import model.Vehicle;
import utill.DbConnectionUtill;


public class PaymentServiceImpl implements PaymentService {
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	private static PreparedStatement preparedStatement = null;
	private static Payment payment = null;
	private static List<Payment> paymentList = null;
	@Override
	public boolean addPayment(Payment p) {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		String sql = "INSERT INTO `payment` (`id`, `card_no`, `card_name`, `cvv`, `exp_date`,`res_id`) VALUES (NULL, '"+p.getCard_no()+"', '"+p.getCard_name()+"', "+p.getCvv()+", '"+p.getExp_date()+"',"+p.getRes_id()+")";

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
	public boolean updatePayment(Payment p) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String sql="UPDATE `payment` SET `card_no` = '"+p.getCard_no()+"', `card_name` = '"+p.getCard_name()+"', `cvv` = "+p.getCvv()+", `res_id` = "+p.getRes_id()+", `exp_date` = '"+p.getExp_date()+"' WHERE `payment`.`id` = "+p.getId();
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
	public boolean deletePayment(int id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String sql = "DELETE FROM payment WHERE id ="+id;
		
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
	public List<Payment> getAllPayment() {
		// TODO Auto-generated method stub
		paymentList = new ArrayList<>();
		String sql = "SELECT * FROM payment";

		try {
			connection = DbConnectionUtill.createConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while(resultSet.next()) {
				int id  = Integer.parseInt(resultSet.getString("id"));
				String card_no = resultSet.getString("card_no");
				String card_name = resultSet.getString("card_name");
				int cvv = Integer.parseInt(resultSet.getString("cvv"));
				String exp_date = resultSet.getString("exp_date");
				int res_id = resultSet.getInt("res_id");
				payment = new Payment(card_no, card_name, cvv, exp_date, res_id);
				payment.setId(id);
				paymentList.add(payment);
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
		
		return paymentList;
	}

	@Override
	public Payment getPaymentById(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM payment WHERE id = "+id;
		try {
			connection = DbConnectionUtill.createConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				String card_no = resultSet.getString("card_no");
				String card_name = resultSet.getString("card_name");
				int cvv = Integer.parseInt(resultSet.getString("cvv"));
				String exp_date = resultSet.getString("exp_date");
				int res_id = resultSet.getInt("res_id");
				payment = new Payment(card_no, card_name, cvv, exp_date, res_id);
				payment.setId(id);
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
		return payment;
	}

}
