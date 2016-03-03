package neo4j.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import neo4j.bean.User;

public class UserDAO {
	
    public UserDAO(){
        
    }
	 
    public User getUserInfor(String userId) throws SQLException, ClassNotFoundException {
        String query = "match (user:User{id : '" + userId + "'}) return user.id, user.name, user.avatar";
	// Make sure Neo4j Driver is registered
        Class.forName("org.neo4j.jdbc.Driver");
	// Connect
        Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/", "neo4j", "hvngoc");
	// Querying
        try {
            final PreparedStatement statement = con.prepareStatement(query);
            System.out.println("Done");
            final ResultSet result = statement.executeQuery();
            User user = new User();
            while(result.next()) {
                user = new User(result.getString("user.id"), result.getString("user.name"), result.getString("user.avatar"));
                System.out.println(result.getString("user.name"));
            }
            return user;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
   
    public void setAvatarForUser(String userId, String avatar) throws SQLException, ClassNotFoundException {
        String query = "match (user:User{id : '" + userId + "'}) SET u.avatar = '" + avatar + "'";
	// Make sure Neo4j Driver is registered
        Class.forName("org.neo4j.jdbc.Driver");
	// Connect
        Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/", "neo4j", "hvngoc");
	// Querying
        try {
            final PreparedStatement statement = con.prepareStatement(query);
            System.out.println("Done");
            final ResultSet result = statement.executeQuery();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }   
}
