package neo4j.service;

import java.sql.SQLException;
import java.util.ArrayList;

import neo4j.bean.Comment;
import neo4j.dao.CommentDAO;

public class CommentService {
	private CommentDAO commentDAO;
	
	public CommentService(){
		this.commentDAO = new CommentDAO();
	}
	public ArrayList<Comment> GetALLCommentOfPost(String postID) throws ClassNotFoundException, SQLException{
		return this.commentDAO.GetALLCommentOfPost(postID);
	}
}
