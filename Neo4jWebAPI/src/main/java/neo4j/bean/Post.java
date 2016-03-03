package neo4j.bean;


public class Post {
	private String postID;
	private String content;
	private String listImages;
	private String numLike;
	private String postDate;
	private String location; 
	public String userName, userAvatar;
        
	public Post (String postID, String content, String listImages, String numLike,
			String postDate, String location, String userName, String userAvatar) {
		this.setPostID(postID);
		this.setContent(content);
		this.setListImages(listImages);
		this.setNumLike(numLike);
		this.setPostDate(postDate);
		this.setLocation(location);
                this.userName = userName;
                this.userAvatar = userAvatar;
	}

	public String getPostID() {
		return postID;
	}

	public void setPostID(String postID) {
		this.postID = postID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getListImages() {
		return listImages;
	}

	public void setListImages(String listImages) {
		this.listImages = listImages;
	}

	public String getNumLike() {
		return numLike;
	}

	public void setNumLike(String numLike) {
		this.numLike = numLike;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
