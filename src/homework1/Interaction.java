package homework1;

public class Interaction{

    private int interactionId;

    private String accountId;

    private int postId;


    public Interaction(){
    }
    public Interaction(int interactionId,int postId, String accountId) {
        this.interactionId = interactionId;
        this.accountId = accountId;
        this.postId = postId;
    }


    public int getInteractionId() {
        return interactionId;
    }

    public void setInteractionId(int interactionId) {
        this.interactionId = interactionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
