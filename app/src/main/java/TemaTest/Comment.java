package TemaTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment implements Likeable {
    private int nrLike;
    private String text;
    private int id;
    private String commentDate;
    static int auxCommentId = 1;

    public Comment() {}

    public Comment(String text) {
        this.id = auxCommentId++;
        this.text = text;
        this.nrLike = 0;
        this.commentDate = getDateAsString();
    }

    public int getNrLike() {
        return this.nrLike;
    }

    public String getText() {
        return this.text;
    }

    public int getId() {
        return this.id;
    }

    public String getCommentDate() {
        return this.commentDate;
    }

    private String getDateAsString() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String currentDateAsString = dateFormat.format(date);
        return currentDateAsString;
    }

    @Override
    public void like() {
        nrLike++;
    }

    @Override
    public void unlike() {
        nrLike--;
    }

    @Override
    public String toString() {
        return "'comment_id' : '" + this.getId() + "', 'comment_text' : '" +
                this.getText() + "', 'comment_date' : '" +
                this.getCommentDate() + "'";
    }
}
