package app;
import java.util.Scanner;
public class Main 
{
    public static void main(String[] args) 
    {
	Bank bank = new Bank("bank");
        int menu;
        do{
            System.out.println("Enter 1 to Deposite.");
            System.out.println("Enter 2 to Withdraw.");
            System.out.println("Enter 3 to Check Balance.");
            System.out.println("Enter 4 to create new account.");
            System.out.println("Enter 5 to close account.");
            System.out.println("Enter 6 to transfer money to another account.");
            System.out.println("Enter anything else will result in quit.");
            menu = inputI("Enter here");
            System.out.println("---------------------------------------------");
            switch(menu)
            {
                case 1:
                    int accountnumber1 = inputI("Input account number:");
                    int amount1 = inputI("Input amount:");
                    boolean x1 = bank.deposit(accountnumber1, amount1);
                    if(x1)
                    {
                        System.out.println("Deposite success.");
                    }
                    else
                    {
                        System.out.println("Deposite failed.");
                    }
                    System.out.println("---------------------------------------------");
                    break;
                case 2:
                    int accountnumber2 = inputI("Input account number:");
                    int amount2 = inputI("Input amount:");
                    boolean x2 = bank.withdraw(accountnumber2, amount2);
                    if(x2)
                    {
                        System.out.println("Withdraw success.");
                    }
                    else
                    {
                        System.out.println("Withdraw failed.");
                    }
                    System.out.println("---------------------------------------------");
                    break;
                case 3:
                    int accountnumber3 = inputI("Input account number:");
                    int x3 = bank.checkBalance(accountnumber3);
                    if(x3==-1)
                    {
                        System.out.println("Check balance failed.");
                    }
                    else
                    {
                        System.out.println("Account balance: "+x3);
                    }
                    System.out.println("---------------------------------------------");
                    break;
                case 4:
                    String name4 = inputS("Input account name:");
                    int accountnumber4 = bank.createAccount(name4);
                    System.out.println("Account number: "+accountnumber4);
                    System.out.println("---------------------------------------------");
                    break;
                case 5:
                    int accountnumber5 = inputI("Input account number:");
                    boolean x5 = bank.closeAccount(accountnumber5);
                    if(x5)
                    {
                        System.out.println("Account deleted.");
                    }
                    else
                    {
                        System.out.println("Delete account failed.");
                    }
                    System.out.println("---------------------------------------------");
                    break;
                case 6:
                    int accountnumber6x = inputI("Enter account number which the money will be withdraw from:");
                    int accountnumber6y = inputI("Enter account number which the money will be transfer to:");
                    int amount6 = inputI("Enter amount transfer:");
                    boolean x6 = bank.Transfer(accountnumber6x, accountnumber6y, amount6);
                    if(x6)
                    {
                        System.out.println("Money transfered success.");
                    }
                    else
                    {
                        System.out.println("Money transfered failed.");
                    }
                    System.out.println("---------------------------------------------");
                    break;
                default:
                    break;
            }
        }while(menu>=1 && menu<=6);
    }
    public static boolean inputB(String s)
    {
        Scanner input = new Scanner(System.in);
        System.out.println(s);
        return input.nextBoolean();
    }
    public static int inputI(String s)
    {
        Scanner input = new Scanner(System.in);
        System.out.println(s);
        return input.nextInt();
    }
    public static double inputD(String s)
    {
        Scanner input = new Scanner(System.in);
        System.out.println(s);
        return input.nextDouble();
    }
    public static String inputS(String s)
    {
        Scanner input = new Scanner(System.in);
        System.out.println(s);
        return input.nextLine();
    }
}
