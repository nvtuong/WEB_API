package neo4j.controller;

import java.sql.SQLException;
import java.util.List;
import neo4j.bean.Comment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import neo4j.bean.Post;
import neo4j.bean.User;
import neo4j.service.AccountService;
import neo4j.service.CommentService;
import neo4j.service.PostService;


@RestController
public class HomeController {
    
    @RequestMapping(value = "/neo4j/getAllComment", method = RequestMethod.POST,headers="Accept=application/json")
    public ResponseEntity<List<Comment>> GetALLCommentOfPost(@RequestBody String postID) throws ClassNotFoundException, SQLException
    {
    	System.out.println("get all comment");
        CommentService service = new CommentService();
        List<Comment> listComment = null; 
        try {
            listComment = service.GetALLCommentOfPost(postID);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(listComment == null || listComment.size() == 0)
            return new ResponseEntity<List<Comment>>(listComment, HttpStatus.BAD_REQUEST);
        else
            System.out.println("Posts count: " + listComment.size());
        return new ResponseEntity<List<Comment>>(listComment, HttpStatus.OK);
        
    }
    
    @RequestMapping(value = "/neo4j/register", method = RequestMethod.POST,headers="Accept=application/json")
    public ResponseEntity<User> RegisterAccount(@RequestBody String register) throws ClassNotFoundException, SQLException
    {
    	System.out.println("Register");
        AccountService service = new AccountService();
        int index1 = register.indexOf(' ');
        String email = register.substring(0, index1);
        int index2 = register.indexOf(';');
        String password = register.substring(index1 + 1, index2);
        String name = register.substring(index2 + 1, register.length());
        
        Boolean check = service.checkExistEmail(email);
        if(check)
            return new ResponseEntity<User>(new User(), HttpStatus.BAD_REQUEST);
        User user = service.registerNewAccount(name, email, password);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/neo4j/login", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<User> LoginAccount(@RequestBody String account) throws ClassNotFoundException, SQLException
    {
    	System.out.println("Login");
        AccountService service = new AccountService();
        int index = account.indexOf(' ');
        String email = account.substring(0, index);
        String password = account.substring(index+1, account.length());
        User user = service.loginWithEmailAndPassword(email, password);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        if(user == null)
        	return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/neo4j/getAllPost", method = RequestMethod.POST,headers="Accept=application/json")
    public ResponseEntity<List<Post>> getAllPostOfFriends(@RequestBody String userID) {
    	System.out.println("Controller Userid: " + userID);
        PostService service = new PostService();
        List<Post> posts = null;
        try {
            posts = service.GetListPostOfFriend(userID);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(posts == null || posts.size() == 0)
            return new ResponseEntity<List<Post>>(posts, HttpStatus.BAD_REQUEST);
        else
            System.out.println("Posts count: " + posts.size());
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/neo4j/getAllPostOfUser", method = RequestMethod.POST,headers="Accept=application/json")
    public ResponseEntity<List<Post>> GetListPostAndSharedOfUser(@RequestBody String userID) {
    	System.out.println("Controller Userid: " + userID);
        PostService service = new PostService();
        List<Post> posts = null;
        try {
            posts = service.GetListPostAndSharedOfUser(userID);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(posts == null || posts.size() == 0)
            return new ResponseEntity<List<Post>>(posts, HttpStatus.BAD_REQUEST);
        else
            System.out.println("Posts count: " + posts.size());
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }
}
