package AppDev.SPLIT_WISE;

import java.util.ArrayList;
import java.util.List;

public class Expenses {
    static User us;

    String expenseName;
    int amt;
    String by;
    String date;
    int id;

    Expenses(String expenseName, int amt, String by, String date, int id) {
        this.expenseName = expenseName;
        this.amt = amt;
        this.by = by;
        this.date = date;
        this.id = id;
    }

    Expenses(){}

    static List<Expenses> eList = new ArrayList<>();
    static int ids = 100;

    public void expense(int pos) {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~ADD-EXPENSES~~~~~~~~~~\n");
        System.out.print("Enter Expense Name: ");
        String expenseName = Login.sc.nextLine();
        System.out.print("\nEnter Expense Amount: ");
        int amt = Login.sc.nextInt();
        Login.sc.nextLine();
        System.out.print("\nEnter Date of Expense in (dd/mm/yyyy): ");
        String date = Login.sc.nextLine();
        eList.add(new Expenses(expenseName, amt, User.uList.get(pos).name, date, ++ids));
        System.out.println("\nExpenses has been requested to Other Users");
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
    }

    public void viewExpense(int pos) {
        boolean flag = true;
        for (Expenses i : eList)
        {
            System.out.print("\033[H\033[2J");
            System.out.println("~~~~~~~~~~VIEW/REPAY-EXPENSES~~~~~~~~~~\n");
            if(i.by.equals(User.uList.get(pos).name) || User.uList.get(pos).payments.contains(i.id+""))
                continue;
            System.out.println("Expense Name: " + i.expenseName + "\nExpense Amount: " + i.amt + "\nAmount you to Pay: " + i.amt / User.uList.size() + "\nRequested by: " + i.by+"\nExpense Date: " + i.date + "\n----   ----   ----");
            flag = false;
            System.out.print("\nEnter (1) Pay / (0) Cancel: ");
            String choice = Login.sc.nextLine();
            if(choice.equals("0"))
            {
                System.out.print("\033[H\033[2J");
                continue;
            }
            if(i.amt > User.uList.get(pos).walletAmt)
            {
                System.out.println("\nWallet Amount is Low");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
                us = new User();
                us.walletAmount(pos);
                viewExpense(pos);
                return;
            }
            User.uList.get(pos).payments += i.id + ":" + i.amt / User.uList.size() + ",";
            User.uList.get(pos).walletAmt -= i.amt / User.uList.size();
            for (User j : User.uList)
            {
                if (j.name.equals(i.by))
                {
                    j.walletAmt += i.amt / User.uList.size();
                    j.request.add(i.id + ":" + i.by + ":" + User.uList.get(pos).name + ":" + i.amt / User.uList.size());
                    break;
                }
            }
            System.out.println("\nPayment has been Completed");
            System.out.print("\nPress Enter to Continue...");
            Login.sc.nextLine();
        }
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~VIEW/REPAY-EXPENSES~~~~~~~~~~\n");
        if(flag)
            System.out.print("\nNo User has been Request for Repay");
        else
            System.out.print("\nNo more Request for Repay");
        System.out.print("\nPress Enter to Continue...");
        Login.sc.nextLine();
    }

    public void transactions(int pos) {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~TRANSACTIONS~~~~~~~~~~\n");
        String transact[] = User.uList.get(pos).payments.split(",");
        boolean flag = true;
        for (String i : transact)
        {
            String arr[] = i.split(":");
            for (Expenses j : eList)
            {
                if(arr[0].equals(j.id + ""))
                {
                    flag = false;
                    System.out.println("Expense Name: " + j.expenseName + "\nAmount Payed: " + arr[1] + "\nRequested by: " + j.by+"\nExpense Date: " + j.date + "\n----   ----   ----");
                }
            }
        }
        if(flag)
        {
            System.out.print("\nNo Transaction has been done");
        }
        System.out.print("\nPress Enter to Continue...");
        Login.sc.nextLine();
    }

    public void requestResult(int pos) {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~REQUEST-RESULTS~~~~~~~~~~\n");
        boolean flag = true;
        for (User i : User.uList)
        {
            for (String j : i.request)
            {
                String arr[] = j.split(":");
                if(arr[1].equals(User.uList.get(pos).name))
                {
                    for (Expenses k : eList)
                    {
                        if(arr[0].equals(k.id+""))
                        {
                            System.out.println("Expense Name: " + k.expenseName + "\nAmount Payed: " + arr[3] + "\nPayed by: " + arr[2]+"\nExpense Date: " + k.date + "\n----   ----   ----");
                        }
                    }
                    flag = false;
                }
            }
        }
        if(flag)
        {
            System.out.print("\nNo Repayment has been done by any other Users");
        }
        System.out.print("\nPress Enter to Continue...");
        Login.sc.nextLine();
    }

}
