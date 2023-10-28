package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Reservation;
import model.User;
import utill.DbConnectionUtill;

public class LoginServiceImpl implements LoginService {
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	private static User user = null;
	@Override
	public User validate(String user_name, String password,String user_type) {

		// TODO Auto-generated method stub
		boolean flag = false;
		String sql = "SELECT * FROM `user` WHERE type = '"+user_type+"' and name = '"+user_name+"' and password = '"+password+"'";
		try {
			connection = DbConnectionUtill.createConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			if(resultSet.next()) {
				String user_no = resultSet.getString("id_no");
				int id = resultSet.getInt("id");
				user = new User(user_type, user_name, "", user_no);
				user.setId(id);
				flag = true;
				user.setLogged(flag);
			}else {
				user = new User(user_type, user_name, password, "");
				user.setLogged(flag);
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
		
		System.out.println(user.toString());
		return user;

	}
	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM user WHERE id = "+id;
		try {
			connection = DbConnectionUtill.createConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				String type = resultSet.getString("type");
				String namee = resultSet.getString("name");
				String id_no =String.valueOf(resultSet.getInt("id_no"));
				
				user = new User(type, namee, "", id_no);
				user.setId(id);
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
		return user;
	}

}
