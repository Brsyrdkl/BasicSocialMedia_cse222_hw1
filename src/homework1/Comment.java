package homework1;

public class Comment extends Interaction{

    private String comment;
    private String username;

    public Comment(int interactionid,int postId,String comment,Account account) {
        super(interactionid,postId, account.getAccountid());
        this.comment = comment;
        this.username = account.getUsername();

    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
