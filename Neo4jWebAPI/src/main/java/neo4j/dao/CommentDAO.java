package neo4j.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import neo4j.bean.Comment;

public class CommentDAO {
	public CommentDAO(){
		
	}
	
	public ArrayList<Comment> GetALLCommentOfPost(String postID) throws ClassNotFoundException, SQLException{
                String query = "match (p:Post{id: '" + postID + "'}) - [HAS_COMMENT] -> (c:Comment), (u:User{id : c.userID}) "
                        + "return c.id, c.content, c.day, u.id, u.name, u.avatar";
                
		// Make sure Neo4j Driver is registered
		Class.forName("org.neo4j.jdbc.Driver");
		// Connect
		Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/", "neo4j", "hvngoc");
		// Querying
		try {
			final PreparedStatement statement = con.prepareStatement(query);
			System.out.println("Done");
			final ResultSet result = statement.executeQuery();
			ArrayList<Comment> listComment = new ArrayList<Comment>();
			 while(result.next())
			 {
				 Comment comment= new Comment(result.getString("c.id"), result.getString("c.content"), 
                                         result.getString("c.day"), result.getString("u.id"), 
                                         result.getString("u.name"), result.getString("u.avatar") );
				listComment.add(comment);
			 }
			return listComment;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
