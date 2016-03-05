package neo4j.bean;

public class Comment {
    private String id;
    private String content;
    private String commentDate;
    private String userID;
    private String userName;
    private String userAvatar;
    
    public Comment(String id, String content, String commentDate, String userId, String userName, String userAvatar){
        this.id = id;
        this.content = content;
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.commentDate = commentDate;
        this.userID = userId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return this.userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getCommentDate() {
        return this.commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }
    
    public void setUserID(String userID){
        this.userID = userID;
    }
    
    public String getUserID(){
        return this.userID;
    }
}	
