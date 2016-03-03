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
	
	public Post GetPostDetail(String postID) throws ClassNotFoundException, SQLException{
		return this.postDAO.GetPostDetail(postID);
	}
	
	public ArrayList<Post> GetListPostOfFriend(String userID) throws ClassNotFoundException, SQLException{
		return this.postDAO.GetListPostOfFriend(userID);
	}
	
	public ArrayList<Post> GetListPostAndSharedOfUser(String userID) throws ClassNotFoundException, SQLException{
		return this.postDAO.GetListPostAndSharedOfUser(userID);
	}
	
	public int GetNumLikeOfPost(String postID) throws ClassNotFoundException, SQLException{
		return this.postDAO.GetNumLikeOfPost(postID);
	}
	
	public int GetNumSharedOfPost(String postID) throws ClassNotFoundException, SQLException{
		return this.postDAO.GetNumSharedOfPost(postID);
	}
	public int GetNumCommentOfPost(String postID) throws ClassNotFoundException, SQLException{
		return this.postDAO.GetNumCommentOfPost(postID);
	}
}
