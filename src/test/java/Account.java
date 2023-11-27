public class Account {

    private int accountId;
    private int customerId;
    private double balance;
    private AccountType accountType;

    private static int sayacAccount = 9000;

    public Account() {
    }

    public Account(int customerId, AccountType accountType) {
        setAccountId(sayacAccount++);
        setCustomerId(customerId);
        setAccountType(accountType);
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }


    public void depositToAmount(double amount) {


    }

    public void withdrawToAmount(double amount) {


    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", customerId=" + customerId +
                ", balance=" + balance +
                ", accountType=" + accountType +
                '}';
    }
}
