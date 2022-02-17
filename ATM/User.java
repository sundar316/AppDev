import java.util.Scanner;

public class User {
    static String uname[]={"user1", "user2", "user3", "user4"};
    static int pass[]={1234, 4321, 5678, 8765};
    static int bamount[]={6000, 20000, 5000, 2000};
    static String bank[]={"A", "A", "C", "D"};
    static int pattempt[]={0, 0, 0, 0};
    static int with[]={0, 0, 0, 0};
    static String transact[]={"", "", "", ""};

    private static Scanner sc=new Scanner(System.in);
    public void user(){
        System.out.print("\033[H\033[2J");
        t:while(true)
        {
            System.out.println("~~~~~~~~~~USER~~~~~~~~~~\n");
            System.out.print("Enter Username: ");
            String name=sc.nextLine();
            System.out.print("Enter Password: ");
            int pas=sc.nextInt();
            sc.nextLine();
            System.out.println();
            int i=0;
            try{
                boolean flag=false;
                for(i=0;i<=uname.length;i++)
                {
                    if(uname[i].trim().equals(name))
                    {
                        if(pass[i]==pas && pattempt[i]<3)
                        {
                            flag=true;
                            break;
                        }
                        if(pattempt[i]==3)
                        {
                            System.out.print("\033[H\033[2J");
                            System.out.println("Account Blocked for too many attempts.");
                            System.out.print("Press Enter to continue...");
                            sc.nextLine();
                            break t;
                        }
                        else
                        {
                            pattempt[i]++;
                        }
                    }
                }
                if(flag)
                {
                    System.out.print("\033[H\033[2J");
                    logged(i);
                    System.out.print("\033[H\033[2J");
                    break;
                }
            }
            catch(Exception e)
            {
                System.out.print("\033[H\033[2J");
                System.out.println("Enter Correct Username or Password.");
                System.out.println();
            }
        }
    }

    public void logged(int user){
        pattempt[user]=0;
        t:while(true)
        {
            System.out.println("!!! Welcome "+uname[user]+" !!!\n");
            System.out.print("1.Balance\n2.Withdrawal\n3.Pin Change\n4.Transfer\n5.Transaction\n6.Deposit\n7.Exit\nEnter Choice: ");
            int choice=sc.nextInt();
            sc.nextLine();
            System.out.print("\033[H\033[2J");
            switch(choice)
            {
                case 1:
                    System.out.println("Balance: "+bamount[user]);
                    System.out.print("Press Enter to Continue...");
                    sc.nextLine();
                    break;
                case 2:
                    withdrawal(user);
                    break;
                case 3:
                    pinChange(user);
                    break;
                case 4:
                    transfer(user);
                    break;
                case 5:
                    transaction(user);;
                    break;
                case 6:
                    deposit(user);
                    break;
                case 7:
                    break t;
                default:
                    System.out.println("Invalid Input");
            }
            System.out.print("\033[H\033[2J");
        }
    }

    public void pinChange(int user){
        System.out.print("\033[H\033[2J");
        System.out.print("Enter new Pin: ");
        int pin=sc.nextInt();
        System.out.print("Enter Confirm pin: ");
        int pin1=sc.nextInt();
        sc.nextLine();
        if(pin==pin1)
        {
            pass[user]=pin;
            System.out.print("Pin set Successfully (Press Enter to Continue...)");
            sc.nextLine();
            transact[user]+=",Pin Changed";
        }
        else
        {
            System.out.println("Pin Mismatch");
            System.out.print("Press Enter to Continue...");
            sc.nextLine();
        }
    }

    public void deposit(int user){
        System.out.println("Deposit");
        System.out.print("Enter no of 2000 notes: ");
        int a=sc.nextInt();
        Admin.amount[0]+=a;
        System.out.print("Enter no of 500 notes: ");
        int b=sc.nextInt();
        Admin.amount[1]+=b;
        System.out.print("Enter no of 200 notes: ");
        int c=sc.nextInt();
        Admin.amount[2]+=c;
        System.out.print("Enter no of 100 notes: ");
        int d=sc.nextInt();
        Admin.amount[3]+=d;
        bamount[user]+=(2000*a)+(500*b)+(200*c)+(100*d);
        sc.nextLine();
        System.out.println("Amount added Successfully");
        System.out.print("Press Enter to continue...");
        sc.nextLine();
        transact[user]+=",Amount Deposited ->"+((2000*a)+(500*b)+(200*c)+(100*d));
    }

    public void withdrawal(int user){
        while(true)
        {
            int amount=0;
            if(bank[user].equals("A"))
            {}
            else
                with[user]++;
            if(with[user]>3)
            {
                transact[user]+=",Fine Amount 100 for more than three transactions";
                bamount[user]-=100;
                Admin.amount[3]++;
            }
            System.out.print("Enter Amount to Withdraw: ");
            amount+=sc.nextInt();
            sc.nextLine();
            int temp=amount;
            int famt=amount;
            int a=0 ,b=0 , c=0, d=0;
            boolean flag=false;
            if(amount%100==0 && amount<=10000 && amount<=bamount[user] && Admin.amt>=amount)
            {
                flag=true;
                int p=Admin.amount[0];
                int q=Admin.amount[1];
                int r=Admin.amount[2];
                int s=Admin.amount[3]-1;
                while(temp>0)
                {
                    if(temp>=2000 && p>0)
                    {
                        a++;
                        temp-=2000;
                        bamount[user]-=2000;
                        Admin.amount[0]--;
                        p--;
                    }
                    else if(temp>=500 && q>0)
                    {
                        b++;
                        temp-=500;
                        bamount[user]-=500;
                        Admin.amount[1]--;
                        q--;
                    }
                    else if(temp>=200 && r>0)
                    {
                        c++;
                        temp-=200;
                        bamount[user]-=200;
                        Admin.amount[2]--;
                        r--;
                    }
                    else if(temp>=100 && s>0)
                    {
                        d++;
                        temp-=100;
                        bamount[user]-=100;
                        Admin.amount[3]--;
                        s--;
                    }
                }
            }
            if(amount>10000 && !flag)
            {
                System.out.print("\033[H\033[2J");
                System.out.println("Maximum Limit is Ten Thousand Rupee Only.");
                System.out.print("Press Enter to continue...");
                sc.nextLine();
                break;
            }
            if(amount>bamount[user] && !flag)
            {
                System.out.print("\033[H\033[2J");
                System.out.println("Insufficient Balance");
                System.out.print("Press Enter to continue...");
                sc.nextLine();
                break;
            }
            if(Admin.amt<amount && !flag)
            {
                System.out.print("\033[H\033[2J");
                System.out.println("Insufficient Fund");
                System.out.print("Press Enter to continue...");
                sc.nextLine();
                break;
            }
            if(amount%100!=0 && !flag)
            {
                System.out.print("\033[H\033[2J");
                System.out.println("Enter Correct Denomination");
                System.out.print("Press Enter to continue...");
                sc.nextLine();
                break;
            }
            if(flag)
            {
                System.out.println("Withdraw Successfully\n\tWithdrawal Amount: "+famt+" in Denominations of 2000->"+a+" ;500->"+b+" ;200->"+c+" ;100->"+d);
                System.out.print("Press Enter to Continue...");
                sc.nextLine();
                transact[user]+=",Amount Withdrew ->"+famt;
                break;
            }
        }
    }

    public void transaction(int user){
        String us[]=transact[user].split(",");
        if(us.length==1)
            System.out.println("No Transaction have been made\n");
        else
            for(int i=1;i<us.length;i++)
                System.out.println(us[i]);
        System.out.print("Press Enter to Continue...");
        sc.nextLine();
    }

    public void transfer(int user){
        System.out.print("Enter User Name: ");
        String u=sc.nextLine();
        int i;
        boolean flag=true;
        l:for(i=0;i<uname.length;i++)
        {
            if(u.equals(uname[i]) && user!=i)
            {
                flag=false;
                break l;
            }
        }
        if(flag)
        {
            System.out.print("Enter valid User Name (Press Enter to Continue...)");
            sc.nextLine();
            System.out.print("\033[H\033[2J");
        }
        else
        {
            System.out.print("Transfer Amount: ");
            int amt=sc.nextInt();
            sc.nextLine();
            if(bamount[user]>=amt)
            {
                System.out.print("Press Enter to Continue...");
                sc.nextLine();
                if(bank[user].equals("A") && bank[i].equals("A"))
                {
                    bamount[user]-=amt;
                    bamount[i]+=amt;
                }
                else
                {
                    bamount[user]-=amt+100;
                    bamount[i]+=amt;
                    transact[user]+=",Charge Amount 100 for other bank money transfer";
                }
                System.out.print("Money Transferred Successfully (Press Enter to Continue...)");
                sc.nextLine();
                transact[user]+=",Transferred Money "+amt+" to "+u;
                System.out.print("\033[H\033[2J");
            }
            else
            {
                System.out.println("Insufficient Balance (Press Enter to Continue...)");
                sc.nextLine();
                System.out.print("\033[H\033[2J");
            }
        }
    }
}
