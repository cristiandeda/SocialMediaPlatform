package TemaTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Post implements Likeable {
    private int nrLike;
    private String text;
    private int id;
    private ArrayList<Comment> postCommentArray;
    private String postDate;
    private int nrComments;
    static int auxPostId = 1;

    public Post() {
    }
    public Post(String text) {
        this.id = auxPostId++;
        this.text = text;
        this.postCommentArray = new ArrayList<>();
        this.postDate = getDateAsString();
        this.nrComments = 0;
        this.nrLike = 0;
    }

    public String getText() {
        return this.text;
    }

    public int getId() {
        return this.id;
    }

    public String getPostDate() {
        return this.postDate;
    }

    public int getNrLike() {
        return this.nrLike;
    }

    public int getNrComments() {
        return this.nrComments;
    }

    public ArrayList<Comment> getPostCommentArray() {
        return this.postCommentArray;
    }

    private String getDateAsString() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String currentDateAsString = dateFormat.format(date);
        return currentDateAsString;
    }

    public void addCommentToPost(Comment comentariu) {
        this.postCommentArray.add(comentariu);
        this.nrComments++;
    }
    public void removeCommentFromPost(Comment comentariu) {
        this.postCommentArray.remove(comentariu);
        this.nrComments--;
    }

    @Override
    public void like() {
        this.nrLike++;
    }

    @Override
    public void unlike() {
        this.nrLike--;
    }

    @Override
    public String toString() {
        return "'post_id' : '" + this.getId() + "', 'post_text' : '" +
                this.getText() + "', 'post_date' : '" +
                this.getPostDate() + "'";
    }
}
