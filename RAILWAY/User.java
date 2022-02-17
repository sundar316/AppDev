import java.util.ArrayList;
import java.util.List;

public class User {
    String userName;
    int userId;
    int pin;
    int amt;

    static int id=100;
    static List<User> uList=new ArrayList<>();
    static {
        uList.add(new User("Krishnan", 906, 1234, 0));
        uList.add(new User("Meghna", 907, 1234, 0));
    }

    static int ticket[][]=new int[10][6];
    static int waitingList[][]=new int[5][3];

    User(String userName, int userId, int pin, int amt){
        this.userName=userName;
        this.userId=userId;
        this.pin=pin;
        this.amt=amt;
    }

    User(){}

    public void login() {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~SIGNING-IN~~~~~~~~~~\n");
        System.out.print("Enter User Name: ");
        String name=Login.sc.nextLine();
        int pos=0;
        for(User i:User.uList)
        {
            if(i.userName.equals(name))
            {
                System.out.print("Enter Pin: ");
                int pin=Login.sc.nextInt();
                Login.sc.nextLine();
                if(pin==i.pin)
                {
                    System.out.print("\033[H\033[2J");
                    System.out.println("Sign-In Successfully");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    logged(i.userId, pos);
                    return;
                }
                System.out.print("\033[H\033[2J");
                System.out.println("Wrong Pin");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
                return;
            }
            pos++;
        }
        System.out.println("User Name is Not Exists");
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
        return;
    }

    public void logged(int user, int pos){
        t:while (true)
        {
            System.out.print("\033[H\033[2J");
            System.out.print("""
                            +==WELCOME-USER============+
                            |                          |
                            | 1. Book Ticket           |
                            | 2. Cancel Ticket         |
                            | 3. Wallet Amount         |
                            | 4. Status                |
                            | 5. Exit                  |
                            |                          |
                            +==========================+

                            Enter the Choice: """);
            int choice=Login.sc.nextInt();
            Login.sc.nextLine();
            switch (choice)
            {
                case 1:
                    book(user, pos);
                    break;
                case 2:
                    cancel(user, pos);
                    break;
                case 3:
                    walletAmount(pos);
                    break;
                case 4:
                    status(pos, user);
                    break;
                case 5:
                    break t;
                default:
                    System.out.print("\033[H\033[2J");
                    System.out.println("Invalid Input");
                    System.out.println("Press Enter to Continue...");
                    Login.sc.nextLine();
            }
        }
    }

    public void book(int user, int pos) {
        System.out.print("\033[H\033[2J");
        String arr[]={"City1", "City2", "City3", "City4", "City5", "City6"};
        System.out.print("~~~~~~~~~~TICKET~~~~~~~~~~\n\nFrom: ");
        for(int i=0;i<6;i++)
        {
            System.out.print(arr[i]+" ("+(i+1)+") ");
        }
        System.out.print("\n\nEnter the Choice: ");
        int choice=Login.sc.nextInt();
        System.out.print("\nTo: ");
        for(int i=choice;i<6;i++)
        {
            System.out.print(arr[i]+" ("+(i+1)+") ");
        }
        System.out.print("\n\nEnter the Choice: ");
        int toChoice=Login.sc.nextInt();
        Login.sc.nextLine();
        System.out.print("\033[H\033[2J");
        if(choice>=toChoice)
        {
            System.out.println("Wrong Selection");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
            return;
        }
        switch(toChoice-choice)
        {
            case 5:
                System.out.println("Your Fare Amount: RS.240/-");
                if(uList.get(pos).amt>=240)
                {
                    uList.get(pos).amt-=240;
                }
                else
                {
                    System.out.println("\nNot Enough Wallet Balance");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    return;
                }
                break;

            case 4:
                System.out.println("Your Fare Amount: RS.120/-");
                if(uList.get(pos).amt>=120)
                {
                    uList.get(pos).amt-=120;
                }
                else
                {
                    System.out.println("\nNot Enough Wallet Balance");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    return;
                }
                break;

            case 3:
                System.out.println("Your Fare Amount: RS.60/-");
                if(uList.get(pos).amt>=60)
                {
                    uList.get(pos).amt-=60;
                }
                else
                {
                    System.out.println("\nNot Enough Wallet Balance");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    return;
                }
                break;

            case 2:
                System.out.println("Your Fare Amount: RS.40/-");
                if(uList.get(pos).amt>=40)
                {
                    uList.get(pos).amt-=40;
                }
                else
                {
                    System.out.println("\nNot Enough Wallet Balance");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    return;
                }
                break;

            case 1:
                System.out.println("Your Fare Amount: RS.20/-");
                if(uList.get(pos).amt>=20)
                {
                    uList.get(pos).amt-=20;
                }
                else
                {
                    System.out.println("\nNot Enough Wallet Balance");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    return;
                }
                break;
        }
        booked(choice-1, toChoice-2, user);
    }

    public void booked(int from, int to, int user) {
        boolean flag=true;
        for(int i=0; i<10; i++)
        {
            int count=0;
            for(int j=from; j<=to; j++)
            {
                if(ticket[i][j]==0)
                {
                    count++;
                }
            }
            if(count==(to-from)+1)
            {
                flag=false;
                for(int j=from; j<=to; j++)
                {
                    ticket[i][j]=user;
                }
                System.out.println("Ticket was Booked Successfully");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
                break;
            }
        }
        if(flag)
        {
            boolean test=true;
            for(int i=0; i<5;i++)
            {
                if(waitingList[i][0]==0 && waitingList[i][1]==0 && waitingList[i][2]==0)
                {
                    test=false;
                    waitingList[i][0]=from;
                    waitingList[i][1]=to;
                    waitingList[i][2]=user;
                    break;
                }
            }
            if(test)
            {
                System.out.println("All seats are reserved");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
                return;
            }
            System.out.println("You have been in the Waiting List");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
    }

    public void cancel(int user, int pos) {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~CANCEL-TICKET~~~~~~~~~~\n(Please Note: Cancellation Fees: Rs.10/-)\n");
        System.out.print("Continue (1)/Cancel (0): ");
        int a=Login.sc.nextInt();
        if(a==0)
        {
            return;
        }
        Login.sc.nextLine();
        boolean flag=false;
        int count=0;
        for(int i=0; i<10; i++)
        {
            for(int j=0; j<6; j++)
            {
                if(ticket[i][j]==user)
                {
                    ++count;
                    flag=true;
                    ticket[i][j]=0;
                }
            }
            if(flag)
            {
                switch (count) {
                    case 6:
                        uList.get(pos).amt+=230;
                        break;
                    case 5:
                        uList.get(pos).amt+=110;
                        break;
                    case 4:
                        uList.get(pos).amt+=50;
                        break;
                    case 3:
                        uList.get(pos).amt+=30;
                        break;
                    case 2:
                        uList.get(pos).amt+=10;
                        break;
                }
                System.out.println("\nTicket has Been Canceled\nAmount has been Refunded");
                System.out.print("\n\nPress Enter to Continue...");
                Login.sc.nextLine();
                waiting();
                return;
            }
        }
        for(int i=0; i<5; i++)
        {
            if(waitingList[i][2]==user)
            {
                int total=waitingList[i][1]-waitingList[i][0];
                waitingList[i][0]=0;
                waitingList[i][1]=0;
                waitingList[i][2]=0;
                switch (total+1) {
                    case 6:
                        uList.get(pos).amt+=230;
                        break;
                    case 5:
                        uList.get(pos).amt+=110;
                        break;
                    case 4:
                        uList.get(pos).amt+=50;
                        break;
                    case 3:
                        uList.get(pos).amt+=30;
                        break;
                    case 2:
                        uList.get(pos).amt+=10;
                        break;
                }
                System.out.println("\nTicket has Been Canceled\nAmount has been Refunded");
                System.out.print("\n\nPress Enter to Continue...");
                Login.sc.nextLine();
                return;
            }
        }
        System.out.println("No Tickets has been Booked");
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
    }

    public void waiting() {
        for(int i=0; i<10; i++)
        {
            for(int j=0; j<5; j++)
            {
                if(waitingList[j][0]==0 && waitingList[j][1]==0 && waitingList[j][2]==0)
                    continue;
                boolean flag=true;
                for(int k=waitingList[j][0]; k<=waitingList[j][1]; k++)
                {
                    if(ticket[i][k]!=0)
                    {
                        flag=false;
                        break;
                    }
                }
                if(flag)
                {
                    int from=waitingList[j][0];
                    int to=waitingList[j][1];
                    waitingList[j][0]=0;
                    waitingList[j][1]=0;
                    for(int k=from; k<=to; k++)
                    {
                        ticket[i][k]=waitingList[j][2];
                    }
                    waitingList[j][2]=0;
                    return;
                }
            }
        }
    }

    public void walletAmount(int user) {
        t:while(true)
        {
            System.out.print("\033[H\033[2J");
            System.out.print("""
                            ~~~~~~~~~~WALLET~~~~~~~~~~

                            1. Add Wallet Amount
                            2. Show Wallet Amount
                            3. Exit

                            Enter Your Choice: """);
            int choice=Login.sc.nextInt();
            Login.sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("\033[H\033[2J");
                    System.out.println("~~~~~~~~~~ADD-WALLET-AMOUNT~~~~~~~~~~\n");
                    System.out.print("Enter Amount: ");
                    uList.get(user).amt+=Login.sc.nextInt();
                    Login.sc.nextLine();
                    System.out.print("\nAmount Added Successfully\nPress Enter to Continue...");
                    Login.sc.nextLine();
                    break;
                case 2:
                    System.out.print("\033[H\033[2J");
                    System.out.println("~~~~~~~~~~WALLET-AMOUNT~~~~~~~~~~\n");
                    System.out.println("Wallet Balance: "+uList.get(user).amt);
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

    public void status(int pos, int user) {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~STATUS~~~~~~~~~~\n\n");
        System.out.println("Booked\n");
        for(int[] i:ticket)
        {
            for(int j:i)
            {
                System.out.print(j+" ");
            }
            System.out.println();
        }
        System.out.println("\nWaiting List\n");
        for(int i[]:waitingList)
        {
            for(int j:i)
            {
                System.out.print(j+" ");
            }
            System.out.println();
        }
        for(int[] i:ticket)
        {
            for(int j:i)
            {
                if(j==user)
                {
                    System.out.println("Ticket has been Reserved");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    return;
                }
            }
        }
        for(int[] i:waitingList)
        {
            if(i[2]==user)
            {
                System.out.println("Ticket has been in waiting list");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
                return;
            }
        }
        System.out.println("No Ticket has been Booked");
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
    }

}
