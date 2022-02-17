package AppDev.SPLIT_WISE;

import java.util.ArrayList;
import java.util.List;

public class User {
    static User us;
    static Expenses ep;

    String name;
    String userName;
    String password;
    int walletAmt;
    String payments = "";
    List<String> request = new ArrayList<>();

    User (String name, String userName, String password) {
        this.name = name;
        this.userName =userName;
        this.password = password;
        this.walletAmt = 3500;
    }

    User(){}

    static List<User> uList = new ArrayList<>();

    static {
        uList.add(new User("Dora", "dora001", "dora"));
        uList.add(new User("Bujji", "bujji002", "bujji"));
        uList.add(new User("Kulla Nari", "nari003", "kulla"));
        uList.add(new User("Back Pack", "back004", "pack"));
    }


    public void logged (int pos) {
        t:while (true)
        {
            System.out.print("\033[H\033[2J");
            System.out.printf("~~~~~~~~~~~~~~~WELCOME %s~~~~~~~~~~~~~~~\n\n" , uList.get(pos).name.toUpperCase());
            System.out.print("""
                            \t+==========================+
                            \t|                          |
                            \t| 1. Add Expense           |
                            \t| 2. Add Partner           |
                            \t| 3. View/Repay Expense    |
                            \t| 4. Transactions          |
                            \t| 5. Request Results       |
                            \t| 6. Wallet Amount         |
                            \t| 7. Change Password       |
                            \t| 8. Logout                |
                            \t|                          |
                            \t+==========================+

                            Enter the Choice: """);
            int choice = Login.sc.nextInt();
            Login.sc.nextLine();
            System.out.print("\033[H\033[2J");
            switch (choice)
            {
                case 1:
                    ep = new Expenses();
                    ep.expense(pos);
                    break;
                case 2:
                    us = new User();
                    us.partners("");
                    break;
                case 3:
                    ep = new Expenses();
                    ep.viewExpense(pos);
                    break;
                case 4:
                    ep = new Expenses();
                    ep.transactions(pos);
                    break;
                case 5:
                    ep = new Expenses();
                    ep.requestResult(pos);
                    break;
                case 6:
                    us = new User();
                    us.walletAmount(pos);
                    break;
                case 7:
                    us = new User();
                    us.changePassword(pos);
                    break;
                case 8:
                    System.out.println("---Logging Out---");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    break t;
                default:
                    System.out.println("Invalid Input !!!");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
            }
        }
    }

    public void partners(String name) {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~ADD-PARTNERS~~~~~~~~~~\n");
        System.out.print("Enter Partner's Name: "+name);
        if(name.equals(""))
            name = Login.sc.nextLine();
        else
            System.out.println();
        System.out.print("\nEnter Partner's Username: ");
        String userName = Login.sc.nextLine();
        for (User i : uList) {
            if(i.userName.equalsIgnoreCase(userName))
            {
                System.out.println("\nUsername Already Exists");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
                partners(name);
                return;
            }
        }
        System.out.print("\nEnter Partner's Password: ");
        String password = Login.sc.nextLine();
        uList.add(new User(name, userName, password));
        System.out.println("\nPartner has been added Successfully");
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
    }

    public void changePassword(int pos) {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~CHANGE-PASSWORD~~~~~~~~~~\n");
        System.out.print("Enter New Password/Exist (0): ");
        String password = Login.sc.nextLine();
        if(password.equals("0"))
            return;
        if(password.equals(uList.get(pos).password))
        {
            System.out.println("\nNew Password mustn't be Old Password");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
            changePassword(pos);
            return;
        }
        System.out.print("\nRetype Password: ");
        if(password.equals(Login.sc.nextLine()))
        {
            uList.get(pos).password = password;
            System.out.println("\nPassword Changed Successfully");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
        else
        {
            System.out.println("\nMismatch Password");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
            changePassword(pos);
            return;
        }
    }

    public void walletAmount(int pos) {
        t:while(true)
        {
            System.out.print("\033[H\033[2J");
            System.out.print("""
                            ~~~~~~~~~~AMAZON-WALLET~~~~~~~~~~

                            1. Add Wallet Amount
                            2. Show Wallet Amount
                            3. Exit

                            Enter Your Choice: """);
            int choice = Login.sc.nextInt();
            Login.sc.nextLine();

            switch (choice)
            {
                case 1:
                    System.out.print("\033[H\033[2J");
                    System.out.println("~~~~~~~~~~ADD-WALLET-AMOUNT~~~~~~~~~~\n");
                    System.out.print("Enter Amount: ");
                    uList.get(pos).walletAmt += Login.sc.nextInt();
                    Login.sc.nextLine();
                    System.out.print("\nAmount Added Successfully\nPress Enter to Continue...");
                    Login.sc.nextLine();
                    break;
                case 2:
                    System.out.print("\033[H\033[2J");
                    System.out.println("~~~~~~~~~~WALLET-AMOUNT~~~~~~~~~~\n");
                    System.out.println("Wallet Balance: " + uList.get(pos).walletAmt);
                    System.out.print("\nPress Enter to Continue...");
                    Login.sc.nextLine();
                    break;
                case 3:
                    break t;
                default:
                    System.out.print("\033[H\033[2J");
                    System.out.print("Invalid Input\nPress Enter to Continue...");
                    Login.sc.nextLine();
                    break;
            }
        }
    }

}
