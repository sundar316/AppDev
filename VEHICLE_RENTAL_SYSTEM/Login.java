package AppDev.VEHICLE_RENTAL_SYSTEM;

import java.util.Scanner;

public class Login
{
    static Login lo;
    static User us;
    static Admin ad;

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        t:while (true)
        {
            System.out.print("\033[H\033[2J");
            System.out.print("""
                            +==WELCOME=================+
                            |                          |
                            | 1. Admin Login           |
                            | 2. User Login            |
                            | 3. Exit                  |
                            |                          |
                            +==========================+

                            Enter the Choice:\u00A0""");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice)
            {
                case 1:
                    lo = new Login();
                    lo.adminLogin();
                    break;
                case 2:
                    lo = new Login();
                    lo.user();
                    break;
                case 3:
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

    public void adminLogin()
    {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~ADMIN-LOGIN~~~~~~~~~~\n");
        System.out.print("Enter Username: ");
        String userName = sc.nextLine();
        int pos = 0;
        for (Admin i : Admin.aList)
        {
            if(i.userName.equals(userName))
            {
                System.out.print("\nEnter Password: ");
                String password = sc.nextLine();
                if(i.password.equals(password))
                {
                    System.out.print("\033[H\033[2J");
                    System.out.print("Signing In");
                    int j = 4;
                    while(j-- > 0)
                    {
                        try
                        {
                            Thread.sleep(500);
                        }
                        catch(InterruptedException ex)
                        {
                            Thread.currentThread().interrupt();
                        }
                        System.out.print(". ");
                    }
                    ad = new Admin();
                    ad.logged(pos);
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

    public void user()
    {
        t : while (true)
        {
            System.out.print("\033[H\033[2J");
            System.out.print("""
                                +==USER====================+
                                |                          |
                                | 1. New User              |
                                | 2. Existing User         |
                                | 3. Exit                  |
                                |                          |
                                +==========================+

                                Enter the Choice:\u00A0""");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice)
            {
                case 1:
                    userSignup();
                    break;
                case 2:
                    userLogin();
                    return;
                case 3:
                    break t;
                default:
                    System.out.print("\033[H\033[2J");
                    System.out.println("Invalid Input");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    break;
            }
        }
    }

    public void userLogin()
    {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~USER-LOGIN~~~~~~~~~~\n");
        System.out.print("Enter Username: ");
        String userName = sc.nextLine();
        int pos = 0;
        for (User i : User.uList)
        {
            if(i.userName.equals(userName))
            {
                System.out.print("\nEnter Password: ");
                String password = sc.nextLine();
                if(i.password.equals(password))
                {
                    System.out.print("\033[H\033[2J");
                    System.out.print("Signing In");
                    int j = 4;
                    while(j-- > 0)
                    {
                        try
                        {
                            Thread.sleep(500);
                        }
                        catch(InterruptedException ex)
                        {
                            Thread.currentThread().interrupt();
                        }
                        System.out.print(". ");
                    }
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

    public void userSignup()
    {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~USER-SIGNUP~~~~~~~~~~\n");
        System.out.print("Enter Name: ");
        String name = Login.sc.nextLine();
        System.out.print("\nEnter Username: ");
        String userName = Login.sc.nextLine();
        boolean flag = true;
        for (User i : User.uList)
        {
            if(i.userName.equals(userName))
            {
                flag = false;
                break;
            }
        }
        if(flag)
        {
            System.out.print("\nEnter Password: ");
            String password = Login.sc.nextLine();
            User.uList.add(new User(name, userName, password));
            System.out.println("\nSigned-up Successfully");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
        else
        {
            System.out.println("\nEntered Username is Already Exists");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
    }

}
