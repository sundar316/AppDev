package AppDev.ATM;

import java.util.HashMap;
import java.util.Scanner;

public class Admin {
    static int amount[]={10, 10, 10, 10};
    static int denomination[]={2000, 500, 200, 100};
    static int amt;
    static
    {
        amt=0;
        for(int i=0;i<4;i++)
            amt+=denomination[i]*amount[i];
    }

    public void logged(){
        System.out.print("\033[H\033[2J");
        System.out.println("!!! Welcome Admin !!!\n");
        t:while(true)
        {
            amt=0;
            for(int i=0;i<4;i++)
            {
                amt+=denomination[i]*amount[i];
            }
            System.out.println("Total Amount: "+amt+" in (2000-> "+amount[0]+"; 500-> "+amount[1]+"; 200-> "+amount[2]+"; 100-> "+amount[3]+")\n");
            System.out.println("1.Update Amount");
            System.out.println("2.Exit");
            System.out.print("Enter Choice: ");
            int choice=sc.nextInt();
            System.out.print("\033[H\033[2J");
            switch(choice)
            {
                case 1:
                    System.out.print("1.Add\n2.Remove\n3.Exit\nEnter Choice: ");
                    int c=sc.nextInt();
                    System.out.print("\033[H\033[2J");
                    if(c==1)
                    {
                        for(int i=0;i<4;i++)
                        {
                            System.out.print("Enter Amount count of "+denomination[i]+": ");
                            int uamount=sc.nextInt();
                            amount[i]+=uamount;
                            System.out.println();
                        }
                    }
                    else if(c==2)
                    {
                        for(int i=0;i<4;i++)
                        {
                            System.out.print("Enter Amount count of "+denomination[i]+": ");
                            int uamount=sc.nextInt();
                            amount[i]-=uamount;
                            System.out.println();
                        }
                    }
                    break;
                case 2:
                    sc.nextLine();
                    break t;
                default:
                    System.out.println("Invalid Input");
            }
            System.out.print("\033[H\033[2J");
        }
    }

    private static Scanner sc=new Scanner(System.in);
    public void admin(){
        System.out.print("\033[H\033[2J");
        Admin ad;
        HashMap<String,Integer> admins=new HashMap<>();
        admins.put("admin1", 1234);
        admins.put("admin2", 4321);
        while(true)
        {
            System.out.println("~~~~~~~~~~ADMIN~~~~~~~~~~");
            System.out.println();
            System.out.print("Enter Username: ");
            String name=sc.nextLine();
            System.out.print("Enter Password: ");
            int pass=sc.nextInt();
            System.out.println();
            try{
                if(admins.get(name.trim())==pass)
                {
                    ad=new Admin();
                    ad.logged();
                    break;
                }
            }
            catch(Exception e)
            {
                System.out.print("\033[H\033[2J");
                System.out.println("Enter Correct Username or Password.");
                System.out.println();
                sc.nextLine();
            }
        }
    }
}
