/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4j.bean;

/**
 *
 * @author Hoang Van Ngoc
 */
public class Friend {
    private User user;
    private int numFriend;
    private int mutualFriend;

    public  Friend(User user, int numFriend, int mutualFriend){
        this.user = user;
        this.numFriend = numFriend;
        this.mutualFriend = mutualFriend;
    }

    public String getId() {
        return user.getId();
    }

    public String getName() {
        return user.getName();
    }

    public String getAvatar() {
        return user.getAvatar();
    }

    public  int getNumFriend(){
        return  this.numFriend;
    }
    
    public int getMutualFriend(){
        return this.mutualFriend;
    }
}
