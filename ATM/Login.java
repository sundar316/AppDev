package AppDev.ATM;

import java.util.Scanner;
public class Login {
    private static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        Admin ad;
        User us;
        t:while(true)
        {
            System.out.print("\033[H\033[2J");
            System.out.println("~~~~~~~~~~ATM~~~~~~~~~~\n");
            System.out.println("1.Admin");
            System.out.println("2.User");
            System.out.println("3.Exit");
            System.out.print("Enter Choice: ");
            int choice=sc.nextInt();
            System.out.print("\033[H\033[2J");
            System.out.println();
            switch(choice)
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
                    System.out.println("!!! Thank You !!!");
                    break t;
                default:
                    System.out.println("Invalid Input");
            }
            System.out.println();
        }
    }
}
