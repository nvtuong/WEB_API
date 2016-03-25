package neo4j.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import neo4j.bean.Friend;

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
    
    public ArrayList<Friend> getAllFriend(String userId) throws SQLException, ClassNotFoundException {
        String query = "match (me:User{id : '" + userId + "'}) - [FRIEND] -> (u:User) - [r:FRIEND] -> (ff:User) "
                + "optional match (me)- [m:FRIEND]->(ff) return u.id, u.avatar, u.name, "
                + "count(distinct r) as numFriend, count (distinct m) as mutualFriend order by numFriend desc";
	// Make sure Neo4j Driver is registered
        Class.forName("org.neo4j.jdbc.Driver");
	// Connect
        Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/", "neo4j", "hvngoc");
	// Querying
        try {
            final PreparedStatement statement = con.prepareStatement(query);
            System.out.println("Done");
            final ResultSet result = statement.executeQuery();
            ArrayList<Friend> listFriend = new ArrayList<Friend>();
            while(result.next()) {
                User user = new User(result.getString("u.id"), result.getString("u.name"), result.getString("u.avatar"));
                int numFriend = Integer.parseInt(result.getString("numFriend"));
                int mutual = Integer.parseInt(result.getString("mutualFriend"));
                listFriend.add(new Friend(user, numFriend, mutual));
            }
            return listFriend;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public ArrayList<Friend> getSuggestFriend(String userId) throws SQLException, ClassNotFoundException {
        String query = "match (me:User{id : '" + userId + "'}) - [:FRIEND] -> (f:User) - [:FRIEND] -> (u:User) - [r:FRIEND] -> (uu:User) "
                + "where not (me) - [:FRIEND] -> (u) and (me) <> (u) "
                + "return u.id, u.name, u.avatar, count (distinct r) as numFriend, count (distinct f) as mutualFriend order by mutualFriend desc";
	// Make sure Neo4j Driver is registered
        Class.forName("org.neo4j.jdbc.Driver");
	// Connect
        Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/", "neo4j", "hvngoc");
	// Querying
        try {
            final PreparedStatement statement = con.prepareStatement(query);
            System.out.println("Done");
            final ResultSet result = statement.executeQuery();
            ArrayList<Friend> listFriend = new ArrayList<Friend>();
            while(result.next()) {
                User user = new User(result.getString("u.id"), result.getString("u.name"), result.getString("u.avatar"));
                int numFriend = Integer.parseInt(result.getString("numFriend"));
                int mutual = Integer.parseInt(result.getString("mutualFriend"));
                listFriend.add(new Friend(user, numFriend, mutual));
            }
            return listFriend;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
   
//    public void setAvatarForUser(String userId, String avatar) throws SQLException, ClassNotFoundException {
//        String query = "match (user:User{id : '" + userId + "'}) SET u.avatar = '" + avatar + "'";
//	// Make sure Neo4j Driver is registered
//        Class.forName("org.neo4j.jdbc.Driver");
//	// Connect
//        Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/", "neo4j", "hvngoc");
//	// Querying
//        try {
//            final PreparedStatement statement = con.prepareStatement(query);
//            System.out.println("Done");
//            final ResultSet result = statement.executeQuery();
//        }catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }   
}
