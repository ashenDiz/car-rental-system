package service;

import model.User;

public interface LoginService {
public User validate(String user_name,String password,String user_type);
public User getUserById(int id);

}
