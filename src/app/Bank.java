package app;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class Bank {
	// Variable for logging/not logging
	private static final boolean LOG = false;

	private static int accountCounter = 1;
	private String name;
	private ArrayList<Account> accounts;

	public Bank() {
		this("Bank Name");
	}

	public Bank(String name) {
		this.name = name;
		accounts = new ArrayList<Account>();
		log("Bank Created");
	}

	public int createAccount(String name) {
		Account newAccount = new Account(name);
		accounts.add(newAccount);

		log("Added account " + newAccount);
		return newAccount.accountNumber;
	}

	public boolean closeAccount(int accountNumber) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			log("Could not close account " + accountNumber);
			return false;
		}
		accounts.remove(account);
		log("Successfully closed " + account);
		return true;
	}

	public boolean deposit(int accountNumber, int amount) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			log("Could not deposit to account " + accountNumber);
			return false;
		}
		account.balance += amount;
		log("Successfully deposited $" + amount + " to " + account);
		return true;
	}

	public boolean withdraw(int accountNumber, int amount) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			log("Could not withdraw from account " + accountNumber);
			return false;
		}
		if (account.balance < amount) {
			log("Insufficient funds in " + account);
			return false;
		}
		account.balance -= amount;
		log("Successfully withdrew $" + amount + " from " + account);
		return true;
	}

	public int checkBalance(int accountNumber) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			log("Could not check balance of account " + accountNumber);
			return -1;
		}
		log("Successfully checked balance of account " + account);
		return account.balance;
	}

        public boolean Transfer(int accountnumber1, int accountnumber2, int amount)
        {
            if(amount>=0)
            {
                Account account1 = findAccount(accountnumber1);
                Account account2 = findAccount(accountnumber2);
                if(account1!=null && account2!=null &&account1.balance>=amount)
                {
                    account1.balance-=amount;
                    account2.balance+=amount;
                    log("Tansfered "+amount+" from "+accountnumber1+" to "+accountnumber2+".");
                    return true;
                }
            }
            log("Failed to tansfered "+amount+" from "+accountnumber1+" to "+accountnumber2+".");
            return false;
        }
        
	public void saveAccounts(String filename) 
        {
            boolean f=false;
            int x = 0;
            for(int i=0; i<accounts.size() && !f;i++)
            {
                if(accounts.get(i).name.equals(filename))
                {
                    f = true;
                    x=i;
                }
            }
            if(f)
            {
                try
                {
                    FileWriter fw = new FileWriter(filename);
                    String message = accounts.get(x).toString();
                    fw.append(message);
                    fw.close();
                    System.out.println("Account saved.");
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                System.out.println("Failed to save account.");
            }
	}

	public void loadAccounts(String filename) 
        {
            try
            {
                Scanner filescan = new Scanner(new File(filename));
                while(filescan.hasNextLine())
                {
                    String line = filescan.nextLine();
                    String[] split = line.split("::");
                    int accountnum  = Integer.parseInt(split[0].substring(1));
                    String accountname = split[1];
                    int amount = Integer.parseInt(split[2].substring(1, split[2].length()-1));
                    Account a = new Account(accountnum,accountname,amount);
                    accounts.add(a);
                }
                filescan.close();
            }catch(IOException e)
            {
                e.printStackTrace();
            }
	}

	private Account findAccount(int accountNumber) {
		for (int i = accounts.size() - 1; i >= 0; i--) {
			if (accounts.get(i).accountNumber == accountNumber)
				return accounts.get(i);
		}
		return null;
	}

	private void log(String message) {
		if (LOG)
			System.out.println(name + " ::: " + message + ".");
	}

	/**
	 * Private Inner Class Account
	 * Deals with Account information
	 */
	private class Account {
		int accountNumber;
		String name;
		int balance;

		private Account(String name) {
			this.name = name;
			balance = 0;
			accountNumber = accountCounter++;
		}
                private Account(int accountNumber,String name,int balance)
                {
                    this.name=name;
                    this.balance=balance;
                    this.accountNumber=accountNumber;
                }
		public String toString() {
			return "{" + accountNumber + "::" + name + "::$" + balance + "}";
		}

	}
}
