package neo4j.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import neo4j.bean.Post;

public class PostDAO {
	
	public PostDAO(){
		
	}
	
	public Post GetPostDetail(String postID) throws ClassNotFoundException, SQLException{
		String query = "match (n:Post{id : '" + postID + "'}) <-[:POST]- (u:User) "
			+ "return u.name, u.avatar, n.id, n.content, n.listImage, "
                        + "n.numLike, n.location, n.day";
		// Make sure Neo4j Driver is registered
		Class.forName("org.neo4j.jdbc.Driver");
		// Connect
		Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/", "neo4j", "hvngoc");
		// Querying
		try {
                    final PreparedStatement statement = con.prepareStatement(query);
                    System.out.println("Done");
                    final ResultSet result = statement.executeQuery();
                    while(result.next()){
                        Post post = new Post(result.getString("n.id"), result.getString("n.content"), 
                                result.getString("n.listImage"), result.getString("n.numLike"), 
                                result.getString("n.day"), result.getString("n.location"),
                                result.getString("u.name"), result.getString("n.avatar"));
                        return post;
                    }
                    return null;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int GetNumLikeOfPost(String postID) throws ClassNotFoundException, SQLException{
		String query = "match (n:Post{id : '" + postID + "'}) <- [l:LIKE] - (u:User) "
				+ "return count(l) as num";
		// Make sure Neo4j Driver is registered
		Class.forName("org.neo4j.jdbc.Driver");
		// Connect
		Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/", "neo4j", "hvngoc");
		// Querying
		try {
			final PreparedStatement statement = con.prepareStatement(query);
			System.out.println("Done");
			final ResultSet result = statement.executeQuery();
			 while(result.next())
			 {
				 return Integer.parseInt(result.getString("num"));
			 }
			return 0;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int GetNumSharedOfPost(String postID) throws ClassNotFoundException, SQLException{
		String query = "match (n:Post{id : '" + postID + "'}) <- [l:SHARE] - (u:User) "
				+ "return count(l) as num";
		// Make sure Neo4j Driver is registered
		Class.forName("org.neo4j.jdbc.Driver");
		// Connect
		Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/", "neo4j", "hvngoc");
		// Querying
		try {
			final PreparedStatement statement = con.prepareStatement(query);
			System.out.println("Done");
			final ResultSet result = statement.executeQuery();
			 while(result.next())
			 {
				 return Integer.parseInt(result.getString("num"));
			 }
			return 0;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int GetNumCommentOfPost(String postID) throws ClassNotFoundException, SQLException{
		String query = "match (n:Post{id : '" + postID + "'}) - [l:HAS_COMMENT] -> (c:Comment) "
				+ "return count(l) as num";
		// Make sure Neo4j Driver is registered
		Class.forName("org.neo4j.jdbc.Driver");
		// Connect
		Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/", "neo4j", "hvngoc");
		// Querying
		try {
			final PreparedStatement statement = con.prepareStatement(query);
			System.out.println("Done");
			final ResultSet result = statement.executeQuery();
			 while(result.next())
			 {
				 return Integer.parseInt(result.getString("num"));
			 }
			return 0;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public ArrayList<Post> GetListPostOfFriend(String userID) throws ClassNotFoundException, SQLException{
		String query = "match (u:User{id : '" + userID + "'}) - [:FRIEND] -> (uu:User) - [:POST] -> (n:Post) "
				+ "return uu.name, uu.avatar, n.id, n.content, n.listImage, n.numLike, n.location, n.day";
		// Make sure Neo4j Driver is registered
		Class.forName("org.neo4j.jdbc.Driver");
		// Connect
		Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/", "neo4j", "hvngoc");
		// Querying
		try {
                    final PreparedStatement statement = con.prepareStatement(query);
                    System.out.println("Done");
                    final ResultSet result = statement.executeQuery();
                    ArrayList<Post> listPost = new ArrayList<Post>();
                    while(result.next()){
                        Post post = new Post(result.getString("n.id"), result.getString("n.content"), 
                                result.getString("n.listImage"), result.getString("n.numLike"), 
				result.getString("n.day"), result.getString("n.location"),
                                result.getString("uu.name"), result.getString("uu.avatar"));
                        listPost.add(post);
			}
                    return listPost;
		}
		catch (SQLException e) {
                    throw new RuntimeException(e);
		}
	}
	
	public ArrayList<Post> GetListPostAndSharedOfUser(String userID) throws ClassNotFoundException, SQLException{
		String query = "match (u:User{id : '" + userID + "'}) - [:POST|:SHARE] -> (n:Post) "
				+ "return u.name, u.avatar, n.id, n.content, n.listImage, n.numLike, n.location, n.day";
		// Make sure Neo4j Driver is registered
		Class.forName("org.neo4j.jdbc.Driver");
		// Connect
		Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/", "neo4j", "hvngoc");
		// Querying
		try {
                    final PreparedStatement statement = con.prepareStatement(query);
                    System.out.println("Done");
                    final ResultSet result = statement.executeQuery();
                    ArrayList<Post> listPost = new ArrayList<Post>();
                    while(result.next()){
                        Post post = new Post(result.getString("n.id"), result.getString("n.content"), 
                                result.getString("n.listImage"), result.getString("n.numLike"), 
				result.getString("n.day"), result.getString("n.location"),
                                result.getString("u.name"), result.getString("u.avatar"));
                        listPost.add(post);
                    }
                    return listPost;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
