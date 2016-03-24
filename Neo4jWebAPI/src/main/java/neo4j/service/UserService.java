package neo4j.service;


import java.sql.SQLException;
import java.util.ArrayList;
import neo4j.bean.Friend;

import neo4j.bean.User;
import neo4j.dao.UserDAO;

public class UserService {
    private UserDAO userDAO;
	
    public UserService() {
        this.userDAO = new UserDAO();
    }
	
    public User getUserInfor(String userId) throws SQLException, ClassNotFoundException {
        return this.userDAO.getUserInfor(userId);
    }
    
    public ArrayList<Friend> getAllFriend(String userId) throws SQLException, ClassNotFoundException {
        return this.userDAO.getAllFriend(userId);
    }
    
    
//    public void setAvatarForUser(String userId, String avatar) throws SQLException, ClassNotFoundException {
//        this.userDAO.setAvatarForUser(userId, avatar);
//    }
}
