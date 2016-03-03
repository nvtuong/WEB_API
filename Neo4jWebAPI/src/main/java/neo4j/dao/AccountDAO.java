/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4j.dao;

import java.util.Date;
import java.text.SimpleDateFormat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import neo4j.bean.User;

/**
 *
 * @author Hoang Van Ngoc
 */
public class AccountDAO {
    public AccountDAO(){
        
    }
    public boolean checkExistEmail(String email)throws SQLException, ClassNotFoundException {
        String query = "match (a:Account{email: '" + email + "'}) return count(a) as num";
	// Make sure Neo4j Driver is registered
        Class.forName("org.neo4j.jdbc.Driver");
	// Connect
        Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/", "neo4j", "hvngoc");
	// Querying
        try {
            final PreparedStatement statement = con.prepareStatement(query);
            System.out.println("Done");
            final ResultSet result = statement.executeQuery();
            while(result.next()) {
                return true;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    
    public User loginWithEmailAndPassword(String email, String password) throws SQLException, ClassNotFoundException {
        String query = "match (a:Account{email : '" + email + "', password : '" + password +"'}) -[r: USER_ACCOUNT]-> (u:User) "
                + "return u.id, u.name, u.avatar";
	// Make sure Neo4j Driver is registered
        Class.forName("org.neo4j.jdbc.Driver");
	// Connect
        Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/", "neo4j", "hvngoc");
	// Querying
        try {
            final PreparedStatement statement = con.prepareStatement(query);
            System.out.println("Done");
            final ResultSet result = statement.executeQuery();
            User user = null;
            while(result.next()) {
                user = new User(result.getString("u.id"), result.getString("u.name"), result.getString("u.avatar"));
                System.out.println(result.getString("user.name"));
                return user;
            }
            return user;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setNewPasswordAccount(String id, String password)throws SQLException, ClassNotFoundException {
        String query = "match (a:Account{id : '" + id + "'}) SET a.password = '" + password + "'";
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
    public void registerNewAccount(String name, String email, String password)throws SQLException, ClassNotFoundException {
        String id = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
        String query = "CREATE (a:Account {id : '" + id + "', email : '" + email + "', password : '" + password + "'}) "
                + "- [r : USER_ACCOUNT] -> (u:User{id : '" + id + "', name : '" + name + "'})";
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
