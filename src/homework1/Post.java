package homework1;

public class Post{

    private String content;

    int postId;

    String accountid;

    private BasicArray<Like> likes = new BasicArray<Like>();

    private BasicArray<Comment> comments = new BasicArray<Comment>();

    public Post() {}

    public Post(int postId,String accountid,String content){
        this.postId = postId;
        this.accountid = accountid;
        this.content = content;
    }


    /*Setters and Getters*/

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public void setLikes(BasicArray<Like> likes) {
        this.likes = likes;
    }

    public void setComments(BasicArray<Comment> comments) {
        this.comments = comments;
    }

    public String getContent(){ return content; }
    public void setContent(String postString) {
        this.content = postString;
    }

    public BasicArray<Like> getLikes() {
        return likes;
    }
    public BasicArray<Comment> getComments() {
        return comments;
    }

    public void addLike(int postId , Account account){
        likes.add(new Like(1,postId,account.getAccountid(),account.getUsername()));
    }

    public void addComment(int postId, String comment,Account account){
        comments.add(new Comment(1,postId,comment,account));
    }

    public String printLikes(){
        String str = "";
        for(int i=0 ; i < likes.getSize() ; i++){
            str += likes.get(i).getUsername();
            str += ", ";
        }
        return str;
    }

    public String printComments(){
        String str = "";
        for(int i=0 ; i < comments.getSize() ; i++){
            str += comments.get(i).getComment();
            str += ", ";
        }
        return str;
    }

    @Override
    public String toString() {
        return content;
    }
}
