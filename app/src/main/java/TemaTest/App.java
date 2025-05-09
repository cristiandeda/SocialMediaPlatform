/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package TemaTest;

import java.util.ArrayList;
import java.util.Comparator;

public class App {
    private static ArrayList<User> userArray = new ArrayList<>();
    private static ArrayList<Post> allPostsArray = new ArrayList<>();
    private static ArrayList<Comment> allCommentsArray = new ArrayList<>();
    public App() {/* compiled code */}

    String getUsernameFromArgs(String[] strings) {
        int beginIndex = strings[1].indexOf("'");
        int endIndex = strings[1].indexOf("'", beginIndex + 1);
        return strings[1].substring(beginIndex + 1, endIndex);
    }

    String getPasswordFromArgs(String[] strings) {
        int beginIndex = strings[2].indexOf("'");
        int endIndex = strings[2].indexOf("'", beginIndex + 1);
        return strings[2].substring(beginIndex + 1, endIndex);
    }

    String getFourthParamFromArgs(String[] strings) {
        int beginIndex = strings[3].indexOf("'");
        int endIndex = strings[3].indexOf("'", beginIndex + 1);
        return strings[3].substring(beginIndex + 1, endIndex);
    }

    String getFifthParamFromArgs(String[] strings) {
        int beginIndex = strings[4].indexOf("'");
        int endIndex = strings[4].indexOf("'", beginIndex + 1);
        return strings[4].substring(beginIndex + 1, endIndex);
    }

    boolean checkAuth(String[] strings) {
        if (strings.length < 2 || strings.length < 3) {
            System.out.println("{'status' : 'error', 'message' : 'You need to be authenticated'}");
            return false;
        }
        return true;
    }

    boolean checkLogin(String username, String password) {
        for (User utilizator : userArray) {
            if (utilizator.getUsername().equals(username)) {
                if (!utilizator.getPassword().equals(password)) {
                    System.out.println("{'status' : 'error', 'message' : 'Login failed'}");
                    return false;
                }
                return true;
            }
        }
        System.out.println("{'status' : 'error', 'message' : 'Login failed'}");
        return false;
    }

    User getUserFromUsername(String username) {
        for (User utilizator : userArray) {
            if (utilizator.getUsername().equals(username)) {
                return utilizator;
            }
        }
        return null;
    }

    Post getPostById(String id) {
        for (Post postare : allPostsArray) {
            if (postare.getId() == Integer.parseInt(id)) {
                return postare;
            }
        }
        return null;
    }

    Comment getCommentById(String id) {
        for (Comment comentariu : allCommentsArray) {
            if (comentariu.getId() == Integer.parseInt(id)) {
                return comentariu;
            }
        }
        return null;
    }

    String getUserOfPost(String id) {
        for (User utilizator : userArray) {
            for (Post postare : utilizator.getPostArray()) {
                if (postare.getId() == Integer.parseInt(id)) {
                    return utilizator.getUsername();
                }
            }
        }
        return null;
    }

    String getUserOfComment(int id) {
        for (User utilizator : userArray) {
            for (Comment comentariu : utilizator.getUserCommentArray()) {
                if (comentariu.getId() == id) {
                    return utilizator.getUsername();
                }
            }
        }
        return null;
    }

    void printFollowingArray(User utilizator) {
        System.out.print("{'status' : 'ok', 'message' : [ '");
        int i = 0;
        for (i = 0; i < utilizator.getFollowingArray().size() - 1; i++) {
            System.out.print(utilizator.getFollowingArray().get(i).getUsername() + "', '");
        }
        System.out.print(utilizator.getFollowingArray().get(i).getUsername() + "'");
        System.out.println("]}");
    }

    void printFollowersArray(User utilizator) {
        System.out.print("{'status' : 'ok', 'message' : [ '");
        int i = 0;
        for (i = 0; i < utilizator.getFollowersArray().size() - 1; i++) {
            System.out.print(utilizator.getFollowersArray().get(i).getUsername() + "', '");
        }
        System.out.print(utilizator.getFollowersArray().get(i).getUsername() + "'");
        System.out.println("]}");
    }

    void createUser(String username, String password) {
        User user = new User(username, password);
        for (User utilizator : userArray) {
            if (utilizator.getUsername().equals(username)) {
                System.out.println("{'status' : 'error', 'message' : 'User already exists'}");
                return;
            }
        }
        userArray.add(user);
        System.out.println("{'status' : 'ok', 'message' : 'User created successfully'}");
    }

    void printPostDetails(App app, Post postToGetDetails, String postToGetDetailsId) {
        String usernameOfPostUser = app.getUserOfPost(postToGetDetailsId);
        System.out.print("{'status' : 'ok', 'message' : [{");
        System.out.print("'post_text' : '" + postToGetDetails.getText() +
                "', 'post_date' : '" + postToGetDetails.getPostDate() +
                "', 'username': '" + usernameOfPostUser +
                "', 'number_of_likes' : '" + postToGetDetails.getNrLike() +
                "', 'comments' : [");
        for (int i = postToGetDetails.getPostCommentArray().size() - 1; i >= 0; i--) {
            Comment comentariu = postToGetDetails.getPostCommentArray().get(i);
            String usernameOfCommentUser = app.getUserOfComment(comentariu.getId());
            System.out.print("{" + comentariu + ", 'username' : '" +
                    usernameOfCommentUser + "', 'number_of_likes' : '" +
                    comentariu.getNrLike() + "'}");
            if (i != 0) {
                System.out.print(",");
            }
        }
        System.out.println("]}]}");
    }
    void printFollowingPosts(User userGetFollowingsPosts) {
        System.out.print("{'status' : 'ok', 'message' : [");
        for (int i = userGetFollowingsPosts.getFollowingArray().size() - 1; i >= 0; i--) {
            User utilizator = userGetFollowingsPosts.getFollowingArray().get(i);
            for (int j = utilizator.getPostArray().size() - 1; j >= 0; j--) {
                Post postFromFollowings = utilizator.getPostArray().get(j);
                System.out.print("{" + postFromFollowings.toString() + ",");
                System.out.print("'username' : '" + utilizator.getUsername() + "'}");
                if (j != 0) {
                    System.out.print(",");
                }
            }
            if (i != 0) {
                System.out.print(",");
            }
        }
        System.out.println("]}");
    }
    void printUserPosts(User userToListPost) {
        System.out.print("{'status' : 'ok', 'message' : [");
        for (int i = userToListPost.getPostArray().size() - 1; i >= 0; i--) {
            Post postToList = userToListPost.getPostArray().get(i);
            System.out.print("{" + postToList.toString() + "}");
            if (i != 0) {
                System.out.print(",");
            }
        }
        System.out.println("]}");
    }

    void printMostLikedPosts(App app) {
        System.out.print("{'status' : 'ok', 'message' : [");
        for (int i = 0; i < allPostsArray.size(); i++) {
            Post postMostLiked = allPostsArray.get(i);
            String usernameMostLikedPost = app.getUserOfPost(String.valueOf(postMostLiked.getId()));
            System.out.print("{" + postMostLiked +
                    ", 'username' : '" + usernameMostLikedPost +
                    "', 'number_of_likes': '" + postMostLiked.getNrLike() + "'}");
            if (i < 4 && i < allPostsArray.size() - 1) {
                System.out.print(",");
            } else if (i == 4) {
                break;
            }
        }
        System.out.println("]}");
    }

    void printMostCommentedPosts(App app) {
        System.out.print("{'status' : 'ok', 'message' : [");
        for (int i = 0; i < allPostsArray.size(); i++) {
            Post postMostCommented = allPostsArray.get(i);
            String usernameMostCommentedPost = app.getUserOfPost(String.valueOf(postMostCommented.getId()));
            System.out.print("{" + postMostCommented +
                    ", 'username' : '" + usernameMostCommentedPost +
                    "', 'number_of_comments': '" +
                    postMostCommented.getNrComments() + "'}");
            if (i < 4 && i < allPostsArray.size() - 1) {
                System.out.print(",");
            } else if (i == 4) {
                break;
            }
        }
        System.out.println("]}");
    }

    void printMostFollowedUsers() {
        System.out.print("{'status' : 'ok', 'message' : [");
        for (int i = 0; i < userArray.size(); i++) {
            User userMostFollowed = userArray.get(i);
            System.out.print("{'username' : '" +
                    userMostFollowed.getUsername() +
                    "', 'number_of_followers': '" +
                    userMostFollowed.getNrFollowers() + "'}");
            if (i < 4 && i < userArray.size() - 1) {
                System.out.print(",");
            } else if (i == 4) {
                break;
            }
        }
        System.out.println("]}");
    }

    void printMostLikedUsers() {
        System.out.print("{'status' : 'ok', 'message' : [");
        for (int i = 0; i < userArray.size(); i++) {
            User userMostLiked = userArray.get(i);
            System.out.print("{'username' : '" +
                    userMostLiked.getUsername() +
                    "', 'number_of_likes': '" +
                    userMostLiked.getNrTotalLikes() + "'}");
            if (i < 4 && i < userArray.size() - 1) {
                System.out.print(",");
            } else if (i == 4) {
                break;
            }
        }
        System.out.println("]}");
    }

    public static void main(java.lang.String[] strings) {
        if (strings == null) {
            System.out.print("Hello world!");
            return;
        }

        App app = new App();
        String username;
        String password;

        switch (strings[0]) {
            case "-create-user":
                if (strings.length < 2) {
                    System.out.println("{'status' : 'error', 'message' : 'Please provide username'}");
                    break;
                }

                if (strings.length < 3) {
                    System.out.println("{'status' : 'error', 'message' : 'Please provide password'}");
                    break;
                }

                username = app.getUsernameFromArgs(strings);
                password = app.getPasswordFromArgs(strings);
                app.createUser(username, password);
                break;
            case "-create-post":
                if (!app.checkAuth(strings)) {
                    break;
                }
                username = app.getUsernameFromArgs(strings);
                password = app.getPasswordFromArgs(strings);
                if (!app.checkLogin(username, password)) {
                    break;
                }

                if (strings.length < 4) {
                    System.out.println("{'status' : 'error', 'message' : 'No text provided'}");
                    break;
                }

                String text = app.getFourthParamFromArgs(strings);
                if (text.length() > 300) {
                    System.out.println("{'status' : 'error', 'message' : 'Post text length exceeded'}");
                    break;
                }

                Post createdPost = new Post(text);

                // userToCreatePost exists because login worked
                User userToCreatePost = app.getUserFromUsername(username);

                userToCreatePost.addPost(createdPost);

                allPostsArray.add(createdPost);
                System.out.println("{'status' : 'ok', 'message' : 'Post added successfully'}");
                break;
            case "-delete-post-by-id":
                if (!app.checkAuth(strings)) {
                    break;
                }
                username = app.getUsernameFromArgs(strings);
                password = app.getPasswordFromArgs(strings);
                if (!app.checkLogin(username, password)) {
                    break;
                }

                if (strings.length < 4) {
                    System.out.println("{'status' : 'error', 'message' : 'No identifier was provided'}");
                    break;
                }

                boolean postToDeleteExists = false;

                String postToDeleteId = app.getFourthParamFromArgs(strings);

                // userToDeletePost exists because login worked
                User userToDeletePost = app.getUserFromUsername(username);
                Post postToDelete = app.getPostById(postToDeleteId);

                if (postToDelete != null) {
                    postToDeleteExists = true;
                }

                if (!postToDeleteExists) {
                    System.out.println("{'status' : 'error', 'message' : 'The identifier was not valid'}");
                } else {
                    userToDeletePost.removePost(postToDelete);
                    allPostsArray.remove(postToDelete);
                    System.out.println("{'status' : 'ok', 'message' : 'Post deleted successfully'}");
                }
                break;
            case "-follow-user-by-username":
                if (!app.checkAuth(strings)) {
                    break;
                }
                username = app.getUsernameFromArgs(strings);
                password = app.getPasswordFromArgs(strings);
                if (!app.checkLogin(username, password)) {
                    break;
                }

                if (strings.length < 4) {
                    System.out.println("{'status' : 'error', 'message' : 'No username to follow was provided'}");
                    break;
                }

                boolean checkFollowed = false;
                boolean checkUserToFollowExists = false;

                String usernameToFollow = app.getFourthParamFromArgs(strings);
                User userToFollow = app.getUserFromUsername(usernameToFollow);

                if (userToFollow != null) {
                    checkUserToFollowExists = true;
                }

                // currentUserFollow exists because login worked
                User currentUserFollow = app.getUserFromUsername(username);
                for (User utilizator : userArray.get(userArray.indexOf(currentUserFollow)).getFollowingArray()) {
                    if (utilizator.getUsername().equals(usernameToFollow)) {
                        checkFollowed = true;
                        break;
                    }
                }

                if (!checkUserToFollowExists || checkFollowed) {
                    System.out.println("{'status' : 'error', 'message' : 'The username to follow was not valid'}");
                } else {
                    if (currentUserFollow != null) {
                        currentUserFollow.addFollowing(userToFollow);
                    }
                    userToFollow.addFollower(currentUserFollow);
                    System.out.println("{'status' : 'ok', 'message' : 'Operation executed successfully'}");
                }
                break;
            case "-unfollow-user-by-username":
                if (!app.checkAuth(strings)) {
                    break;
                }
                username = app.getUsernameFromArgs(strings);
                password = app.getPasswordFromArgs(strings);
                if (!app.checkLogin(username, password)) {
                    break;
                }

                if (strings.length < 4) {
                    System.out.println("{'status' : 'error', 'message' : 'No username to unfollow was provided'}");
                    break;
                }

                boolean checkUnfollow = false;
                boolean checkUserToUnfollowExists = false;

                String usernameToUnfollow = app.getFourthParamFromArgs(strings);
                User userToUnfollow = app.getUserFromUsername(usernameToUnfollow);

                if (userToUnfollow != null) {
                    checkUserToUnfollowExists = true;
                }

                // currentUserUnfollow exists because login worked
                User currentUserUnfollow = app.getUserFromUsername(username);
                for (User utilizator : userArray.get(userArray.indexOf(currentUserUnfollow)).getFollowingArray()) {
                    if (utilizator.getUsername().equals(usernameToUnfollow)) {
                        checkUnfollow = true;
                        break;
                    }
                }

                if (!checkUserToUnfollowExists || !checkUnfollow) {
                    System.out.println("{'status' : 'error', 'message' : 'The username to unfollow was not valid'}");
                } else {
                    currentUserUnfollow.removeFollowing(userToUnfollow);
                    userToUnfollow.removeFollower(currentUserUnfollow);
                    System.out.println("{'status' : 'ok', 'message' : 'Operation executed successfully'}");
                }
                break;
            case "-like-post":
                if (!app.checkAuth(strings)) {
                    break;
                }
                username = app.getUsernameFromArgs(strings);
                password = app.getPasswordFromArgs(strings);
                if (!app.checkLogin(username, password)) {
                    break;
                }

                if (strings.length < 4) {
                    System.out.println("{'status' : 'error', 'message' : 'No post identifier to like was provided'}");
                    break;
                }

                boolean checkPostToLikeExists = false;
                boolean checkPostIsLiked = false;
                boolean checkOwnPost = false;

                String postToLikeId = app.getFourthParamFromArgs(strings);
                Post postToLike = app.getPostById(postToLikeId);

                if (postToLike != null) {
                    checkPostToLikeExists = true;
                }

                // userToLikePost exists because login worked
                User userToLikePost = app.getUserFromUsername(username);
                for (Post postare : userToLikePost.getPostArray()) {
                    if (postare.getId() == Integer.parseInt(postToLikeId)) {
                        checkOwnPost = true;
                        break;
                    }
                }

                for (Post postare : userToLikePost.getLikedPostsArray()) {
                    if (postare.getId() == Integer.parseInt(postToLikeId)) {
                        checkPostIsLiked = true;
                        break;
                    }
                }

                if (!checkPostToLikeExists || checkPostIsLiked || checkOwnPost) {
                    System.out.println("{'status' : 'error', 'message' : 'The post identifier to like was not valid'}");
                } else {
                    userToLikePost.addLikedPost(postToLike);
                    postToLike.like();
                    System.out.println("{'status' : 'ok', 'message' : 'Operation executed successfully'}");
                }
                break;
            case "-unlike-post":
                if (!app.checkAuth(strings)) {
                    break;
                }
                username = app.getUsernameFromArgs(strings);
                password = app.getPasswordFromArgs(strings);
                if (!app.checkLogin(username, password)) {
                    break;
                }

                if (strings.length < 4) {
                    System.out.println("{'status' : 'error', 'message' : 'No post identifier to unlike was provided'}");
                    break;
                }

                boolean checkPostToUnlikeExists = false;
                boolean checkPostCanBeUnliked = false;

                String postToUnlikeId = app.getFourthParamFromArgs(strings);
                Post postToUnlike = app.getPostById(postToUnlikeId);

                if (postToUnlike != null) {
                    checkPostToUnlikeExists = true;
                }

                // userToUnlikePost exists because login worked
                User userToUnlikePost = app.getUserFromUsername(username);
                for (Post postare : userToUnlikePost.getLikedPostsArray()) {
                    if (postare.getId() == Integer.parseInt(postToUnlikeId)) {
                        checkPostCanBeUnliked = true;
                        break;
                    }
                }

                if (!checkPostToUnlikeExists || !checkPostCanBeUnliked) {
                    System.out.println("{'status' : 'error', 'message' : 'The post identifier to unlike was not valid'}");
                } else {
                    userToUnlikePost.removeLikedPost(postToUnlike);
                    postToUnlike.unlike();
                    System.out.println("{'status' : 'ok', 'message' : 'Operation executed successfully'}");
                }
                break;
            case "-like-comment":
                if (!app.checkAuth(strings)) {
                    break;
                }
                username = app.getUsernameFromArgs(strings);
                password = app.getPasswordFromArgs(strings);
                if (!app.checkLogin(username, password)) {
                    break;
                }

                if (strings.length < 4) {
                    System.out.println("{'status' : 'error', 'message' : 'No comment identifier to like was provided'}");
                    break;
                }

                String commentToLikeId = app.getFourthParamFromArgs(strings);
                Comment commentToLike = app.getCommentById(commentToLikeId);

                boolean checkCommentToLikeExists = false;
                boolean checkCommentIsLiked = false;

                if (commentToLike != null) {
                    checkCommentToLikeExists = true;
                }

                // userToLikeComment exists because login worked
                User userToLikeComment = app.getUserFromUsername(username);

                for (Comment comentariu : userToLikeComment.getLikedCommentsArray()) {
                    if (comentariu.getId() == Integer.parseInt(commentToLikeId)) {
                        checkCommentIsLiked = true;
                        break;
                    }
                }

                if (!checkCommentToLikeExists || checkCommentIsLiked) {
                    System.out.println("{'status' : 'error', 'message' : 'The comment identifier to like was not valid'}");
                } else {
                    userToLikeComment.addLikedCommentToUser(commentToLike);
                    commentToLike.like();
                    System.out.println("{'status' : 'ok', 'message' : 'Operation executed successfully'}");
                }
                break;
            case "-unlike-comment":
                if (!app.checkAuth(strings)) {
                    break;
                }
                username = app.getUsernameFromArgs(strings);
                password = app.getPasswordFromArgs(strings);
                if (!app.checkLogin(username, password)) {
                    break;
                }

                if (strings.length < 4) {
                    System.out.println("{'status' : 'error', 'message' : 'No comment identifier to unlike was provided'}");
                    break;
                }

                boolean checkCommentToUnlikeExists = false;
                boolean checkCommentCanBeUnliked = false;

                String commentToUnlikeId = app.getFourthParamFromArgs(strings);
                Comment commentToUnlike = app.getCommentById(commentToUnlikeId);

                if (commentToUnlike != null) {
                    checkCommentToUnlikeExists = true;
                }

                // userToUnlikeComment exists because login worked
                User userToUnlikeComment = app.getUserFromUsername(username);
                for (Comment comentariu : userToUnlikeComment.getLikedCommentsArray()) {
                    if (comentariu.getId() == Integer.parseInt(commentToUnlikeId)) {
                        checkCommentCanBeUnliked = true;
                        break;
                    }
                }

                if (!checkCommentToUnlikeExists || !checkCommentCanBeUnliked) {
                    System.out.println("{'status' : 'error', 'message' : 'The comment identifier to unlike was not valid'}");
                } else {
                    userToUnlikeComment.removeLikedCommentFromUser(commentToUnlike);
                    commentToUnlike.unlike();
                    System.out.println("{'status' : 'ok', 'message' : 'Operation executed successfully'}");
                }
                break;
            case "-get-followings-posts":
                if (!app.checkAuth(strings)) {
                    break;
                }
                username = app.getUsernameFromArgs(strings);
                password = app.getPasswordFromArgs(strings);
                if (!app.checkLogin(username, password)) {
                    break;
                }

                // userToGetFollowingsPosts exists because login worked
                User userGetFollowingsPosts = app.getUserFromUsername(username);
                app.printFollowingPosts(userGetFollowingsPosts);
                break;
            case "-get-user-posts":
                if (!app.checkAuth(strings)) {
                    break;
                }
                username = app.getUsernameFromArgs(strings);
                password = app.getPasswordFromArgs(strings);
                if (!app.checkLogin(username, password)) {
                    break;
                }

                if (strings.length < 4) {
                    System.out.println("{'status' : 'error', 'message' : 'No username to list posts was provided'}");
                    break;
                }

                boolean checkUserToListPostExists = false;
                boolean checkUserToListPostIsFollowed = false;

                String userToListPostName = app.getFourthParamFromArgs(strings);
                User userToListPost = app.getUserFromUsername(userToListPostName);
                if (userToListPost != null) {
                    checkUserToListPostExists = true;
                }

                // currentUserToGetPost exists because login worked
                User currentUserToGetPost = app.getUserFromUsername(username);
                for (User utilizator : currentUserToGetPost.getFollowingArray()) {
                    if (utilizator.getUsername().equals(userToListPostName)) {
                        checkUserToListPostIsFollowed = true;
                        break;
                    }
                }

                if (!checkUserToListPostExists || !checkUserToListPostIsFollowed) {
                    System.out.println("{'status' : 'error', 'message' : 'The username to list posts was not valid'}");
                } else {
                    app.printUserPosts(userToListPost);
                }
                break;
            case "-get-post-details":
                if (!app.checkAuth(strings)) {
                    break;
                }
                username = app.getUsernameFromArgs(strings);
                password = app.getPasswordFromArgs(strings);
                if (!app.checkLogin(username, password)) {
                    break;
                }

                if (strings.length < 4) {
                    System.out.println("{'status' : 'error', 'message' : 'No post identifier was provided'}");
                    break;
                }

                boolean checkPostToGetDetailsExists = false;

                String postToGetDetailsId = app.getFourthParamFromArgs(strings);
                Post postToGetDetails = app.getPostById(postToGetDetailsId);

                if (postToGetDetails != null) {
                    checkPostToGetDetailsExists = true;
                }

                if (!checkPostToGetDetailsExists) {
                    System.out.println("{'status' : 'error', 'message' : 'The post identifier was not valid'}");
                } else {
                    app.printPostDetails(app, postToGetDetails, postToGetDetailsId);
                }
                break;
            case "-comment-post":
                if (!app.checkAuth(strings)) {
                    break;
                }
                username = app.getUsernameFromArgs(strings);
                password = app.getPasswordFromArgs(strings);
                if (!app.checkLogin(username, password)) {
                    break;
                }

                if (strings.length < 5) {
                    System.out.println("{'status' : 'error', 'message' : 'No text provided'}");
                    break;
                }

                String commentText = app.getFifthParamFromArgs(strings);
                if (commentText.length() > 300) {
                    System.out.println("{'status' : 'error', 'message' : 'Comment text length exceeded'}");
                    break;
                }

                Comment createdComment = new Comment(commentText);
                String postToBeCommentedId = app.getFourthParamFromArgs(strings);
                Post postToBeCommented = app.getPostById(postToBeCommentedId);
                User userToCreateComment = app.getUserFromUsername(username);

                userToCreateComment.addCommentToUser(createdComment);
                postToBeCommented.addCommentToPost(createdComment);
                allCommentsArray.add(createdComment);
                System.out.println("{'status' : 'ok', 'message' : 'Comment added successfully'}");
                break;
            case "-delete-comment-by-id":
                if (!app.checkAuth(strings)) {
                    break;
                }
                username = app.getUsernameFromArgs(strings);
                password = app.getPasswordFromArgs(strings);
                if (!app.checkLogin(username, password)) {
                    break;
                }

                if (strings.length < 4) {
                    System.out.println("{'status' : 'error', 'message' : 'No identifier was provided'}");
                    break;
                }

                String commentToBeDeletedId = app.getFourthParamFromArgs(strings);
                Comment commentToBeDeleted = app.getCommentById(commentToBeDeletedId);

                if (commentToBeDeleted == null) {
                    System.out.println("{'status' : 'error', 'message' : 'The identifier was not valid'}");
                    break;
                }

                // userToDeleteComment exists because login worked
                User userToDeleteComment = app.getUserFromUsername(username);
                boolean checkDeletePermission = false;
                for (Comment comentariu : userToDeleteComment.getUserCommentArray()) {
                    if (comentariu.getId() == Integer.parseInt(commentToBeDeletedId)) {
                        checkDeletePermission = true;
                        break;
                    }
                }

                if (checkDeletePermission) {
                    userToDeleteComment.removeCommentFromUser(commentToBeDeleted);
                    for (Post postare : allPostsArray) {
                        for (Comment comentariu : postare.getPostCommentArray()) {
                            if (comentariu.getId() == Integer.parseInt(commentToBeDeletedId)) {
                                postare.removeCommentFromPost(comentariu);
                                break;
                            }
                        }
                    }
                    allCommentsArray.remove(commentToBeDeleted);
                    System.out.println("{'status' : 'ok', 'message' : 'Operation executed successfully'}");
                } else {
                    System.out.println("{'status' : 'error', 'message' : 'The identifier was not valid'}");
                }
                break;
            case "-get-following":
                if (!app.checkAuth(strings)) {
                    break;
                }
                username = app.getUsernameFromArgs(strings);
                password = app.getPasswordFromArgs(strings);
                if (!app.checkLogin(username, password)) {
                    break;
                }

                // userToListFollowing exists because login worked
                User userToListFollowing = app.getUserFromUsername(username);
                app.printFollowingArray(userToListFollowing);
                break;
            case "-get-followers":
                if (!app.checkAuth(strings)) {
                    break;
                }
                username = app.getUsernameFromArgs(strings);
                password = app.getPasswordFromArgs(strings);
                if (!app.checkLogin(username, password)) {
                    break;
                }

                if (strings.length < 4) {
                    System.out.println("{'status' : 'error', 'message' : 'No username to list followers was provided'}");
                    break;
                }

                String usernameToListFollowers = app.getFourthParamFromArgs(strings);
                User userToListFollowers = app.getUserFromUsername(usernameToListFollowers);

                if (userToListFollowers == null) {
                    System.out.println("{'status' : 'error', 'message' : 'The username to list followers was not valid'}");
                } else {
                    app.printFollowersArray(userToListFollowers);
                }
                break;
            case "-get-most-liked-posts":
                if (!app.checkAuth(strings)) {
                    break;
                }
                username = app.getUsernameFromArgs(strings);
                password = app.getPasswordFromArgs(strings);
                if (!app.checkLogin(username, password)) {
                    break;
                }

                // sort posts by number of likes in descending order
                allPostsArray.sort(Comparator.comparingInt(Post::getNrLike).reversed());
                app.printMostLikedPosts(app);
                break;
            case "-get-most-commented-posts":
                if (!app.checkAuth(strings)) {
                    break;
                }
                username = app.getUsernameFromArgs(strings);
                password = app.getPasswordFromArgs(strings);
                if (!app.checkLogin(username, password)) {
                    break;
                }

                // sort posts by number of comments in descending order
                allPostsArray.sort(Comparator.comparingInt(Post::getNrComments).reversed());
                app.printMostCommentedPosts(app);
                break;
            case "-get-most-followed-users":
                if (!app.checkAuth(strings)) {
                    break;
                }
                username = app.getUsernameFromArgs(strings);
                password = app.getPasswordFromArgs(strings);
                if (!app.checkLogin(username, password)) {
                    break;
                }

                // sort users by number of followers in descending order
                userArray.sort(Comparator.comparingInt(User::getNrFollowers).reversed());
                app.printMostFollowedUsers();
                break;
            case "-get-most-liked-users":
                if (!app.checkAuth(strings)) {
                    break;
                }
                username = app.getUsernameFromArgs(strings);
                password = app.getPasswordFromArgs(strings);
                if (!app.checkLogin(username, password)) {
                    break;
                }

                // sort users by number of likes (posts + comments) in descending order
                userArray.sort(Comparator.comparingInt(User::getTotalNumberOfLikes).reversed());
                app.printMostLikedUsers();
                break;
            case "-cleanup-all":
                // reset everything
                if (userArray != null) {
                    userArray.clear();
                }
                if (allPostsArray != null) {
                    allPostsArray.clear();
                }
                if (allCommentsArray != null) {
                    allCommentsArray.clear();
                }
                Post.auxPostId = 1;
                Comment.auxCommentId = 1;
                System.out.println("{'status' : 'ok', 'message' : 'Cleanup finished successfully'}");
                break;
            default:
        }
    }
}