package homework1;

public class TestClass3 {
    public static void main(String[] args) {

        Account account1 = new Account("1","gizemsungu","10.03.1995","Istanbul");
        Account account2 = new Account("2","sibelgulmez","31.01.1990","Kocaeli");
        Account account3 = new Account("3","gokhankaya","15.07.1993","Kýrklareli");

        AccountManager accountManager = new AccountManager();
        accountManager.addMultipleAccounts(new Account[] {account1, account2, account3});

        /*TEST 1*/
        account2.login(accountManager);
        account2.sharePost(new String[]{"I like Java","Java the coffee"});
        account2.addMultipleFollower(new Account[] {account1,account3});
        //account2.follow(account1);
        account2.logout(accountManager);
        account3.login(accountManager);
        account3.viewProfile(account2);
        account3.viewPost(account2);
        account3.likePost(1,account2);
        account3.commentPost(1,account2,"me too");
        account3.addMultipleFollower(new Account[] {account1,account2});
        account3.sendMessage(account1,"This homework is too easy");
        account3.logout(accountManager);
        account1.login(accountManager);
        account1.sendMessage(account3,"I don't think so");
        account1.checkOutboxMessages();
        account1.checkInboxMessages();
        account1.viewInboxMessages(accountManager);
        account1.viewOutboxMessages(accountManager);
        account1.viewProfile(account2);
        account1.viewPost(account2);
        account1.viewInteraction(account2);
        account1.likePost(1,account2);
        account1.likePost(2,account2);
        account1.viewInteraction(account2);
        account1.logout(accountManager);
        //********************************

        account1.login(accountManager);
        account1.blockAccount(account2);
        account1.logout(accountManager);

        account2.login(accountManager);
        account2.viewProfile(account1);
        account2.sendMessage(account1,"Are you here?");
    }
}
