package AppDev.SPLIT_WISE;

import java.util.Scanner;

public class Login {
    static Login lo;
    static User us;

    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        t:while (true)
        {
            System.out.print("\033[H\033[2J");
            System.out.print("""
                            +==WELCOME=================+
                            |                          |
                            | 1. User Login            |
                            | 2. Exit                  |
                            |                          |
                            +==========================+

                            Enter the Choice: """);
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice)
            {
                case 1:
                    lo = new Login();
                    lo.login();
                    break;
                case 2:
                    System.out.print("\033[H\033[2J");
                    System.out.println("!!! Thank You !!!");
                    break t;
                default:
                    System.out.print("\033[H\033[2J");
                    System.out.println("Invalid Input");
                    System.out.println("Press Enter to Continue...");
                    sc.nextLine();
            }
        }
    }

    public void login() {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~LOGIN~~~~~~~~~~\n");
        System.out.print("Enter Username: ");
        String userName = sc.nextLine();
        int pos = 0;
        for (User i : User.uList) {
            if(i.userName.equals(userName))
            {
                System.out.print("\nEnter Password: ");
                String password = sc.nextLine();
                if(i.password.equals(password))
                {
                    us = new User();
                    us.logged(pos);
                    return;
                }
                System.out.println("\nIncorrect Password !!!");
                System.out.print("Press Enter to Continue...");
                sc.nextLine();
                return;
            }
            pos++;
        }
        System.out.println("\nUsername Doesn't Exists");
        System.out.print("Press Enter to Continue...");
        sc.nextLine();
    }

}
