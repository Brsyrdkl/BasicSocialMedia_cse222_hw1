package homework1;

public class AccountManager{
    private boolean isLoggedIn;
    private boolean isLoggedOnce;

    public BasicArray<Account> accounts = new BasicArray<>();
    public AccountManager(){
        System.out.println("...Creating Accounts...");
    }

    public void addAccount(Account account) {
        for(int i = 0; i < accounts.getSize(); i++){
            if(accounts.get(i).getUsername().equals(account.getUsername())){
                throw new IllegalStateException("Account with username " + account.getUsername() + " already exists");
            }
        }
        System.out.println(" An account with username " + account.getUsername() + " has been created");
        accounts.add(account);
    }
    public void addMultipleAccounts(Account[] accounts) {
        for (Account account : accounts) {
            addAccount(account);
        }
    }


    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public boolean isLoggedOnce() {
        return isLoggedOnce;
    }

    public void setLoggedOnce(boolean loggedOnce) {
        isLoggedOnce = loggedOnce;
    }

    public BasicArray<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(BasicArray<Account> accounts) {
        this.accounts = accounts;
    }
}
