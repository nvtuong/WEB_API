package neo4j.bean;

public class Comment {
    private String id;
    private String content;
    private String userName;
    private String userAvatar;
    private String commentDate;

    public Comment(String id, String content, String userName, String userAvatar, String commentDate){
        this.id = id;
        this.content = content;
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.commentDate = commentDate;
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

    public String getPostDate() {
        return this.commentDate;
    }

    public void setPostDate(String commentDate) {
        this.commentDate = commentDate;
    }
}	
