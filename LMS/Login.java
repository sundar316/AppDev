package AppDev.LMS;

import java.util.Scanner;

public class Login {
    static Admin ad;
    static User us;

    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        t:while (true)
        {
            System.out.print("\033[H\033[2J");
            System.out.print("""
                            +==WELCOME=================+
                            |                          |
                            | 1. Admin                 |
                            | 2. User                  |
                            | 3. Exit                  |
                            |                          |
                            +==========================+

                            Enter the Choice: """);
            int choice=sc.nextInt();
            sc.nextLine();
            switch (choice)
            {
                case 1:
                    ad=new Admin();
                    ad.admin();
                    break;
                case 2:
                    us=new User();
                    us.user();
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
}
