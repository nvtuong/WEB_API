package neo4j.service;

import java.sql.SQLException;
import java.util.ArrayList;

import neo4j.bean.Post;
import neo4j.dao.PostDAO;

public class PostService {
	
	private PostDAO postDAO;
	
	public PostService(){
		this.postDAO = new PostDAO();
	}
	
	public ArrayList<Post> GetListPostOfFriend(String userID) throws ClassNotFoundException, SQLException{
		return this.postDAO.GetListPostOfFriend(userID);
	}
	
	public ArrayList<Post> GetListPostAndSharedOfUser(String userID) throws ClassNotFoundException, SQLException{
		return this.postDAO.GetListPostAndSharedOfUser(userID);
	}
}
