package homework1;

public class Like extends Interaction{

    private String username;

    public Like(int interactionid,int postId,String accountid,String username){
        super(interactionid,postId,accountid);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
