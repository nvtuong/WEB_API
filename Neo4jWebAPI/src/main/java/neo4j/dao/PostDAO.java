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
        public ArrayList<Post> GetListPostOfFriend(String userID) throws ClassNotFoundException, SQLException{
            String query = "match (u:User{id : '" + userID + "'})-[:FRIEND]-> (uu : User) - [r : POST|:SHARE]-> (p:Post)"
                    + " Optional match (p) <- [s:SHARE] - (User)"
                    + " Optional match (p) <- [l:LIKE] - (User)"
                    + " Optional match p - [h: HAS_COMMENT] - > (Comment)"
                    + " return p.id , p.content, p.listImage, p.location, p.day, p.feeling, "
                    + "uu.name, uu.avatar, r.name, "
                    + "count (distinct s) as numShared, count (distinct l) as numLiked, count (distinct h) as numComment";
            // Make sure Neo4j Driver is registered
            Class.forName("org.neo4j.jdbc.Driver");
            // Connect
            Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/", "neo4j", "hvngoc");
            // Querying
            System.out.println("DAO USERID: " + userID);
            try {
                final PreparedStatement statement = con.prepareStatement(query);
                
                final ResultSet result = statement.executeQuery();
                ArrayList<Post> listPost = new ArrayList<Post>();
                while(result.next()){
                    Post post = new Post(result.getString("p.id"), result.getString("p.content"), 
                            result.getString("p.listImage"), result.getString("p.day"), result.getString("p.location"), 
                            result.getString("p.feeling"), result.getString("uu.name"),result.getString("uu.avatar"),
                            result.getString("r.name"), Integer.parseInt(result.getString("numLiked")),
                            Integer.parseInt(result.getString("numShared")), Integer.parseInt(result.getString("numComment")));
                    listPost.add(post);
                    
                    System.out.println(post.feeling);
                    
                    }
                return listPost;
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

//    public ArrayList<Post> GetListPostAndSharedOfUser(String userID) throws ClassNotFoundException, SQLException{
//            String query = "match (u:User{id:'" + userID + "'}) - [r :POST|:SHARE]-> (p:Post)"
//                    + " Optional match (p) <- [s:SHARE] - (User) "
//                    + " Optional match (p) <- [l:LIKE] - (User)"
//                    + " Optional match p - [h: HAS_COMMENT] - > (Comment) "
//                    + " return p.id , p.content, p.listImage, p.location, p.day, p.feeling, "
//                    + " u.name, u.avatar, r.name, "
//                    + " count (distinct s) as numShared, count (distinct l) as numLiked, count (distinct h) as numComment  ";
//            // Make sure Neo4j Driver is registered
//            Class.forName("org.neo4j.jdbc.Driver");
//            // Connect
//            Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/", "neo4j", "hvngoc");
//            // Querying
//            try {
//                final PreparedStatement statement = con.prepareStatement(query);
//                System.out.println("Done share of user");
//                final ResultSet result = statement.executeQuery();
//                ArrayList<Post> listPost = new ArrayList<Post>();
//                while(result.next()){
//                    Post post = new Post(result.getString("p.id"), result.getString("p.content"), 
//                            result.getString("p.listImage"), result.getString("p.day"), result.getString("p.location"), 
//                            result.getString("p.feeling"), result.getString("u.name"),result.getString("u.avatar"),
//                            result.getString("r.name"), Integer.parseInt(result.getString("numLiked")),
//                            Integer.parseInt(result.getString("numShared")), Integer.parseInt(result.getString("numComment")));
//                    listPost.add(post);
//                }
//                return listPost;
//            }
//            catch (SQLException e) {
//                    throw new RuntimeException(e);
//            }
//    }
	
}
