package Bank;
class BankAccount{
	private int accountNumber;
	private double balance;
	
	BankAccount(int accountNumber , double balance){
		this.accountNumber = accountNumber;
		this.balance = balance;
		
	}
	void deposit(double amount) {
		balance = balance+amount;
		System.out.println("Amount Deposited"+amount);
	}
	void withdraw(double amount){
		if(balance>=amount) {
		balance= balance-amount;
		System.out.println("Amount Withdrawn Successfull"+amount);
	}else  System.out.println("Insufficient Balance");
	}
	
	double getBalance() {
		return balance;
	}
	int getAccountNumbers() {
		return accountNumber;
	}
}


class SavingAccount extends BankAccount{
	private double interestRate;
	SavingAccount(int accountNumber ,double balance , double interestRate){
		super(accountNumber,balance);
		this.interestRate = interestRate;
	}
	void addInterest() {
		double interest = getBalance() * interestRate/100;
		deposit(interest);
		System.out.println("Interest is Added "+interest);
	}
	
}

class CurrentAccount extends BankAccount {
    private double overdraftLimit;

    CurrentAccount(int accountNumber, double balance, double overdraftLimit) {
        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    void withdraw(double amount) {
        if (getBalance() + overdraftLimit >= amount) {
            super.withdraw(amount);
        } else {
            System.out.println("OverdraftLimit exceeded!");
        }
    }
}

class Customer{
	private String name;
	private BankAccount account;
	
	Customer(String name,BankAccount account){
		this.name = name;
		this.account = account ;
	}
	void showDetails() {
		System.out.println("Customer" +name);
		System.out.println("Account No."+account.getAccountNumbers());
		System.out.println("Balance"+account.getBalance());
	}
	BankAccount getAccount() {
		return account;
	}
}
class Bank{
	private Customer[] customers;
	private int customerCount;
	
	Bank(int size){
		customers = new Customer[size];
		customerCount = 0;
	}
	public void addCustomer(Customer customer) {
		if(customerCount <customers.length) {
			customers[customerCount++] = customer;
			System.out.println("Customer added Successfully");
		}
		else {
			System.out.println("Bank is full");
		}
	}
	void showAllCustomers() {
	for(int i=0;i<customerCount;i++) {
		customers[i].showDetails();
		System.out.println("........................");
	}
	}
}
public class BankManagementSystem {

	public static void main(String[] args) {
	Bank bank = new Bank(100);
	SavingAccount sa = new SavingAccount(1001,50000,5.0);
	CurrentAccount ca = new CurrentAccount(1002,3000,100000);
	
	
	Customer c1 = new Customer("Roshan",sa);
	Customer c2 = new Customer("Amit",ca);
	
	bank.addCustomer(c1);
	bank.addCustomer(c2);
	sa.deposit(200000);
	sa.addInterest();
	ca.withdraw(3500);
	
	bank.showAllCustomers();

	}

}

