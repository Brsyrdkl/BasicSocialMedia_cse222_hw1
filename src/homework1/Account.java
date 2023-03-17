package homework1;

public class Account {

    private String accountid;
    private String username;
    /* XX.YY.ZZZZ */
    private String birthdate;
    private String location;

    private boolean isLoggedIn;
    private BasicArray<Post> posts;

    private BasicArray<Message> messagesInbox;
    private BasicArray<Message> messagesOutbox;
    private BasicArray<Account> following;
    private BasicArray<Account> followers;

    private BasicArray<Account> blockedAccounts = new BasicArray<Account>();

    public Account(){
        //Default Constructor
    }

    public Account(String accountid, String username, String birthdate, String location) {
        this.accountid = accountid;
        this.username = username;
        this.birthdate = birthdate;
        this.location = location;
        this.isLoggedIn = false;

        this.posts = new BasicArray<Post>();
        this.messagesInbox = new BasicArray<Message>();//gelen
        this.messagesOutbox = new BasicArray<Message>();//giden
        this.following = new BasicArray<Account>();
        this.followers = new BasicArray<Account>();
    }

    public void checkLoggedIn(){
        if (!isLoggedIn) {
            throw new IllegalStateException("Account is not currently logged in.");
        }
    }
    public boolean checkBlocked(Account account){
        if (this.blockedAccounts.contains(account)) {
            System.out.println("Account is blocked");
            return true;
        }
        return false;
    }
    public void login(AccountManager accountManager){
        if(this.isLoggedIn() || accountManager.isLoggedIn()) throw new RuntimeException("Account is already logged");
        else {
            if(!accountManager.isLoggedOnce()) {
                System.out.println("...Logging into an account (username: " + this.getUsername() + ")");
            }
            else{
                System.out.println("...Logging into another account (username: " + this.getUsername() + ")");
            }
            this.setLoggedIn(true);
            accountManager.setLoggedIn(true);
            accountManager.setLoggedOnce(true);
        }
    }
    public void logout(AccountManager accountManager){
        if(!this.isLoggedIn() || !accountManager.isLoggedIn()) throw new RuntimeException("Account is not logged! Login first");
        else {
            System.out.println("\n...Logging out from account '" + this.getUsername() + "'");
            this.setLoggedIn(false);
            accountManager.setLoggedIn(false);
        }
    }

    public void blockAccount(Account account){
        checkLoggedIn();
        System.out.println("\n...Blocking account '" + this.getUsername() + "'");
        this.blockedAccounts.add(account);
        account.blockedAccounts.add(this);//other account cant also access this account
    }

    public void follow(Account account) {
        checkLoggedIn();
        if (!isFollowing(account)) {
            account.addFollower(this);
            System.out.println("...You are now following " + account.getUsername());
        }
        else {
            System.out.println("...You are already following " + account.getUsername());
        }
    }

    public void unfollow(Account account) {
        checkLoggedIn();
        if (isFollowing(account)) {
            account.removeFollower(this);
            System.out.println("You have unfollowed " + account.getUsername());
        } else {
            System.out.println("You are not currently following " + account.getUsername());
        }
    }

    private boolean isFollowing(Account account) {
        for (int i = 0 ; i < account.following.getSize() ; i++) {
            if (this.following.get(i) == account) {
                return true;
            }
        }
        return false;
    }

    private void addFollower(Account account) {
            account.followers.add(this);
            this.following.add(account);
    }
    public void addMultipleFollower(Account[] accounts){
        checkLoggedIn();

        //----------------------------CHECKS ACCOUNT ITSELF
        boolean isContain = false;
        for(Account account : accounts){
            if(account.equals(this)){
                isContain = true;
                throw new RuntimeException("Account cannot follow itself");
            }
        }
        //-------------------------------CHECKS FOLLOWS TWICE
        for(int i = 1 ; i < accounts.length ; i++){
            Account tempAccount;
            tempAccount = accounts[i];
            if(tempAccount.equals(accounts[i-1])) throw new RuntimeException("Account cannot follow twice");
        }
        //----------------------------------------FOLLOWING MULTIPLE ACCOUNT
        if(!isContain) {
            System.out.print("...Following ");
            for (int i = 0; i < accounts.length - 1; i++) {
                System.out.print(accounts[i].getUsername() + " ");
            }
            System.out.print("and " + accounts[accounts.length - 1].getUsername());

            for (Account account : accounts) {
                addFollower(account);
            }
        }
    }
    private void removeFollower(Account account) {
            account.followers.remove(this);
            this.following.remove(account);
    }

    public void removeMultipleFollower(Account[] accounts){
        checkLoggedIn();

        for (Account account : accounts) {
            removeFollower(account);
        }
    }

    public String printFollower(){
        int size = this.followers.getSize();
        String str = "";
        for(int i=0; i<size; i++) {
            str += this.followers.get(i).getUsername();
            str += ", ";
        }
        return str;
    }
    public String printFollowing(){
        int size = this.following.getSize();
        String str = "";
        for(int i=0; i<size; i++) {
            str += this.following.get(i).getUsername();
            str += ", ";
        }
        return str;
    }

    public void viewProfile(Account account) {
        checkLoggedIn();
        if(!checkBlocked(account)) {
            if (this.getAccountid() == account.getAccountid()) {
                System.out.println("...Viewing your profile...");
            } else {
                System.out.println("...Viewing " + this.getUsername() + "'s profile");
            }
            System.out.println("\nUser ID: " + account.getAccountid()
                    + "\nUsername: " + account.getUsername()
                    + "\nLocation: " + account.getLocation()
                    + "\nBirth Date: " + account.getBirthdate()
                    + "\n" + account.getUsername() + " is following " + account.following.getSize() +
                    " account(s) and has " + account.followers.getSize() + " follower(s).");
            if (account.following.getSize() == 0 && account.followers.getSize() != 0) {
                System.out.println("The followers of " + account.getUsername() + " are: " + account.printFollower());
            }
            if (account.following.getSize() != 0 && account.followers.getSize() == 0) {
                System.out.println(account.getUsername() + " is following: " + account.printFollowing());
            }
            if (account.following.getSize() != 0 && account.followers.getSize() != 0) {
                System.out.println("The followers of " + account.getUsername() + " are: " + account.printFollower());
                System.out.println(account.getUsername() + " is following: " + account.printFollowing());
            }
            System.out.println(account.getUsername() + " has " + account.posts.getSize() + " posts\n");
        }
    }

    public void sharePost(String postcomment){
        checkLoggedIn();
        Post post = new Post(this.posts.getSize()+1,this.getAccountid(),postcomment);
        System.out.println("...Sharing post...");
        this.posts.add(post);
    }
    public void sharePost(String[] stringPosts){
        checkLoggedIn();

        for(int i=0; i < stringPosts.length ; i++){
            Post post = new Post(this.posts.getSize()+i+1,this.getAccountid(),stringPosts[i]);
            this.posts.add(post);
                        }
        System.out.println("...Sharing "+ stringPosts.length + " posts");
    }

    public void viewPost(Account account) {
        checkLoggedIn();
        if(!checkBlocked(account)) {

            System.out.println("...Viewing " + account.getUsername() + "' posts...");
            for (int i = 0; i < account.posts.getSize(); i++) {
                System.out.println("(PostID: " + account.posts.get(i).getPostId() + ")"
                        + " " + account.getUsername() + ": " + account.printPosts(i));
            }
        }
    }
    public String printPosts(int i){
        int size = this.posts.getSize();
        String str = "";
        return str += this.posts.get(i).getContent();
        }

    public void likePost(int postId,Account account){
        checkLoggedIn();
        if(!checkBlocked(account)) {

            System.out.println("...Liking a post of " + account.getUsername());
            if (postId > account.getPosts().getSize()) throw new RuntimeException("There is no post id");
            else {
                Post post = account.getPosts().get(postId - 1);
                post.addLike(postId, this);
                System.out.println("...You liked " + post.getContent());

            }
        }
    }
    public void commentPost(int postId,Account account,String comment){
        checkLoggedIn();
        if(!checkBlocked(account)) {

            System.out.println("...Adding a comment on a post of " + account.getUsername());
            if (postId > account.getPosts().getSize()) throw new RuntimeException("There is no post id");
            else {
                Post post = account.getPosts().get(postId - 1);
                post.addComment(postId, comment, this);
                System.out.println("...You commented on " + comment);
            }
        }
    }

    public void sendMessage(Account receiverAccount, String content){
        checkLoggedIn();
        if(!checkBlocked(receiverAccount)) {
            if(this.following.contains(receiverAccount)) {
                System.out.println("\n...Sending a message to " + receiverAccount.getUsername());
                Message message = new Message(messagesOutbox.getSize(), this.getAccountid(), receiverAccount.getAccountid(), content);
                this.messagesOutbox.add(message);
                receiverAccount.getMessagesInbox().add(message);
            }
            else {
                System.out.println("...You are not following " + receiverAccount.getUsername() + ".You can't send message");
            }
        }
    }

    public void checkInboxMessages(){
        checkLoggedIn();

        System.out.println("...Checking inbox...");
        System.out.println("There is/are " + this.messagesInbox.getSize() + " message(s) in the inbox.");
    }
    public void checkOutboxMessages(){
        checkLoggedIn();

        System.out.println("...Checking outbox...");
        System.out.println("There is/are " + this.messagesOutbox.getSize() + " message(s) in the outbox.");
    }
    public void viewInboxMessages(AccountManager accountManager) {
        checkLoggedIn();

        System.out.println("...Viewing inbox..." + "\n" +
                "---------------------");
        for (int i = 0; i < this.messagesInbox.getSize(); i++) {

            System.out.println("Message ID: " + this.messagesInbox.get(i).getMessageId() + "\n" +
                    "From: " + accountManager.getAccounts().get(this.messagesInbox.get(i).getSenderId()).getUsername() + "\n" +
                    "To: " + accountManager.getAccounts().get(this.messagesInbox.get(i).getReceiverId()).getUsername()+ "\n" +
                    "Message: " + this.messagesInbox.get(i).getContent() + "\n");
        }

    }
    public void viewOutboxMessages(AccountManager accountManager) {
        checkLoggedIn();

        System.out.println("...Viewing outbox..." + "\n" +
                "---------------------");
        for (int i = 0; i < this.messagesOutbox.getSize(); i++) {

            System.out.println("Message ID: " + this.messagesOutbox.get(i).getMessageId() + "\n" +
                    "From: " + accountManager.getAccounts().get(this.messagesOutbox.get(i).getSenderId()).getUsername() + "\n" +
                    "To: " + accountManager.getAccounts().get(this.messagesOutbox.get(i).getReceiverId()).getUsername()+ "\n" +
                    "Message: " + this.messagesOutbox.get(i).getContent() + "\n");
        }
    }
    public void viewInteraction(Account account){
        checkLoggedIn();
        if(!checkBlocked(account)) {

            System.out.println("... Viewing " + account.getUsername() + "'s posts' interactions...");
            for (int i = 0; i < account.posts.getSize(); i++) {

                System.out.println("(PostID: " + account.posts.get(i).getPostId() + ")"
                        + " " + account.getUsername() + ": " + account.printPosts(i));
                if (account.posts.get(i).getLikes().getSize() > 0) {
                    System.out.println("The post was liked by the following account(s):" + account.posts.get(i).printLikes());
                } else {
                    System.out.println("The post has no likes.");
                }
                if (account.posts.get(i).getComments().getSize() > 0) {
                    System.out.println("The post has " + account.posts.get(i).getComments().getSize() + " comment(s)...");
                    for (int j = 0; j < account.posts.get(i).getComments().getSize(); j++) {
                        System.out.println("Comment " + (j + 1) + ": '" + account.posts.get(i).getComments().get(j).getUsername() + "' said '"
                                + account.posts.get(i).getComments().get(j).getComment() + "'");
                    }
                } else {
                    System.out.println("The post has no comments.");
                }
            }
        }
    }

    /*Setters and Getters*/
    public String getAccountid(){ return accountid; }
    public void setAccountid(String accountid){ this.accountid = accountid; }

    public String getUsername(){ return username; }
    public void setUsername(String username){ this.username = username; }

    public String getBirthdate(){ return birthdate; }
    public void setBirthdate(String birthdate){ this.birthdate = birthdate; }

    public String getLocation(){ return location; }
    public void setLocation(String location) { this.location = location; }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public BasicArray<Post> getPosts() {
        return posts;
    }

    public void setPosts(BasicArray<Post> posts) {
        this.posts = posts;
    }

    public BasicArray<Message> getMessagesInbox() {
        return messagesInbox;
    }

    public void setMessagesInbox(BasicArray<Message> messagesInbox) {
        this.messagesInbox = messagesInbox;
    }

    public BasicArray<Message> getMessagesOutbox() {
        return messagesOutbox;
    }

    public void setMessagesOutbox(BasicArray<Message> messagesOutbox) {
        this.messagesOutbox = messagesOutbox;
    }

    public BasicArray<Account> getFollowing() {
        return following;
    }

    public void setFollowing(BasicArray<Account> following) {
        this.following = following;
    }

    public BasicArray<Account> getFollowers() {
        return followers;
    }

    public void setFollowers(BasicArray<Account> followers) {
        this.followers = followers;
    }

    public BasicArray<Account> getBlockedAccounts() {
        return blockedAccounts;
    }

    public void setBlockedAccounts(BasicArray<Account> blockedAccounts) {
        this.blockedAccounts = blockedAccounts;
    }
}


