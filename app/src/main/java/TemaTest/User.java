package TemaTest;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Post> postArray;
    private ArrayList<User> followingArray;
    private ArrayList<User> followersArray;
    private ArrayList<Post> likedPostsArray;
    private ArrayList<Comment> userCommentArray;
    private ArrayList<Comment> likedCommentsArray;
    private int nrFollowers;
    private int nrTotalLikes;

    public User() {
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.postArray = new ArrayList<>();
        this.followingArray = new ArrayList<>();
        this.followersArray = new ArrayList<>();
        this.likedPostsArray = new ArrayList<>();
        this.userCommentArray = new ArrayList<>();
        this.likedCommentsArray = new ArrayList<>();
        this.nrFollowers = 0;
        this.nrTotalLikes = 0;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public ArrayList<Post> getPostArray() {
        return this.postArray;
    }

    public ArrayList<User> getFollowingArray() {
        return this.followingArray;
    }

    public ArrayList<User> getFollowersArray() {
        return this.followersArray;
    }

    public ArrayList<Post> getLikedPostsArray() {
        return this.likedPostsArray;
    }

    public ArrayList<Comment> getUserCommentArray() {
        return this.userCommentArray;
    }

    public ArrayList<Comment> getLikedCommentsArray() {
        return this.likedCommentsArray;
    }

    public int getNrFollowers() {
        return this.nrFollowers;
    }

    public int getNrTotalLikes() {
        this.nrTotalLikes = getTotalNumberOfLikes();
        return this.nrTotalLikes;
    }

    public void addPost(Post postare) {
        this.postArray.add(postare);
    }
    public void removePost(Post postare) {
        this.postArray.remove(postare);
    }

    public void addFollowing(User utilizator) {
        this.followingArray.add(utilizator);
    }
    public void removeFollowing(User utilizator) {
        this.followingArray.remove(utilizator);
    }

    public void addFollower(User utilizator) {
        this.followersArray.add(utilizator);
        this.nrFollowers++;
    }
    public void removeFollower(User utilizator) {
        this.followersArray.remove(utilizator);
        this.nrFollowers--;
    }

    public void addLikedPost(Post postare) {
        this.likedPostsArray.add(postare);
    }
    public void removeLikedPost(Post postare) {
        this.likedPostsArray.remove(postare);
    }

    public void addCommentToUser(Comment comentariu) {
        this.userCommentArray.add(comentariu);
    }
    public void removeCommentFromUser(Comment comentariu) {
        this.userCommentArray.remove(comentariu);
    }

    public void addLikedCommentToUser(Comment comentariu) {
        this.likedCommentsArray.add(comentariu);
    }
    public void removeLikedCommentFromUser(Comment comentariu) {
        this.likedCommentsArray.remove(comentariu);
    }
    public int getTotalNumberOfLikes() {
        int numberOfLikes = 0;
        for (Post postare : postArray) {
            numberOfLikes += postare.getNrLike();
        }
        for (Comment comentariu : userCommentArray) {
            numberOfLikes += comentariu.getNrLike();
        }
        return numberOfLikes;
    }
}
