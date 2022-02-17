import java.util.ArrayList;
import java.util.List;

public class User {
    String userName;
    int pin;
    int id;
    int aid;
    boolean card;
    int amt;
    List<Integer> borrow=new ArrayList<>();

    static int ids=9000;
    static Book bo;
    static List<User> uList=new ArrayList<>();
    static {
        uList.add(new User("Senthil", 1234, 5678, 609));
        uList.add(new User("Guna", 1234, 5679, 609));
        uList.add(new User("Velu", 1234, 5680, 609));
    }

    User(String userName, int pin, int id, int aid){
        this.userName=userName;
        this.pin=pin;
        this.id=id;
        this.aid=aid;
        this.amt=1500;
        this.card=true;
    }

    User(){}

    public void user() {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~USER-LOGIN~~~~~~~~~~\n");
        System.out.print("Enter User Name: ");
        String userName=Login.sc.nextLine();
        int j=0;
        for(User i:uList)
        {
            if(i.userName.equalsIgnoreCase(userName))
            {
                System.out.print("\nEnter Pin: ");
                int pin=Login.sc.nextInt();
                Login.sc.nextLine();
                if(i.pin==pin)
                {
                    login(i.id, j);
                    return;
                }
                System.out.println("Wrong Pin");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
                return;
            }
            j++;
        }
        System.out.print("\033[H\033[2J");
        System.out.println("User Name Doesn't Exists");
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
    }

    protected void users(int aid) {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~NEW-USER~~~~~~~~~~\n");
        System.out.print("Enter User Name: ");
        String userName=Login.sc.nextLine();
        for(User i:uList)
        {
            if(i.userName.equalsIgnoreCase(userName))
            {
                System.out.print("\033[H\033[2J");
                System.out.println("User Name Already Exists");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
                return;
            }
        }
        System.out.print("\nEnter Pin: ");
        int pin=Login.sc.nextInt();
        Login.sc.nextLine();
        uList.add(new User(userName, pin, ++ids, aid));
        System.out.print("\033[H\033[2J");
        System.out.println("New User has been Created");
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
        Admin.log.add("addUser,"+userName+","+aid);
    }

    public void login(int id, int pos) {
        t:while(true)
        {
            System.out.print("\033[H\033[2J");
            System.out.print("""
                            +==WELCOME-USER============+
                            |                          |
                            | 1. Search Book           |
                            | 2. Transactions          |
                            | 3. Fine Amounts          |
                            | 4. Wallet Amount         |
                            | 5. Membership Card Lost  |
                            | 6. Exit                  |
                            |                          |
                            +==========================+

                            Enter the Choice: """);
            int choice=Login.sc.nextInt();
            Login.sc.nextLine();
            System.out.print("\033[H\033[2J");
            switch(choice)
            {
                case 1:
                    bo=new Book();
                    bo.search();
                    break;
                case 2:
                    transaction(pos);
                    break;
                case 3:
                    fineAmounts(id);
                    break;
                case 4:
                    walletAmount(pos);
                    break;
                case 5:
                    System.out.print("\033[H\033[2J");
                    System.out.println("~~~~~~~~~~MEMBERSHIP-CARD-LOST~~~~~~~~~~\n");
                    System.out.print("Card Lost (1) / Cancel (0): ");
                    int a=Login.sc.nextInt();
                    Login.sc.nextLine();
                    if(a==0)
                        continue;
                    if(!uList.get(pos).card)
                    {
                        System.out.println("Already Applied for New Card");
                        System.out.print("Press Enter to Continue...");
                        Login.sc.nextLine();
                        continue;
                    }
                    uList.get(pos).card=false;
                    if(uList.get(pos).amt<10)
                    {
                        System.out.println("\nLower Wallet Amount");
                        System.out.print("Press Enter to Continue...");
                        Login.sc.nextLine();
                        continue;
                    }
                    uList.get(pos).amt-=10;
                    Transactions.fList.add(new Transactions(id, 10, "Membership Card Lost",true));
                    System.out.println("\nFine Amount Rs.10 is Collected");
                    System.out.println("Collect the Card from Admin");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    break;
                case 6:
                    System.out.println("Signing Out");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    break t;
                default:
                    System.out.println("Invalid Input");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
            }
        }
    }

    public void transaction(int pos) {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~TRANSACTIONS~~~~~~~~~~\n");
        boolean flag=true;
        for(Transactions i:Transactions.tList)
        {
            if(i.userId==uList.get(pos).id)
            {
                flag=false;
                if(!i.borrow)
                    System.out.println("Book Issued\nBook ID: "+i.bookId+"\nAdmin ID: "+i.adminId+"\nNo. of Days: "+i.returnDate);
                else if(i.borrow)
                    System.out.println("Book Returned\nBook ID: "+i.bookId+"\nAdmin ID: "+i.adminId);
                System.out.println();
            }
        }
        if(flag)
        {
            System.out.println("No Transaction has been done");
        }
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
    }

    public void fineAmounts(int id) {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~FINE-AMOUNTS~~~~~~~~~~\n");
        boolean flag=true;
        for(Transactions i:Transactions.fList)
        {
            if(i.userId==id)
            {
                flag=false;
                String res=i.pay ? "Completed" : "Not Completed";
                System.out.println("Reason: "+i.type+"\nFine Amount: "+i.fineAmt+"\nPayment: "+res);
                System.out.println();
            }
        }
        if(flag)
        {
            System.out.println("\nNo Fine Amount has been Charged");
        }
        System.out.print("\nPress Enter to Continue...");
        Login.sc.nextLine();
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
            int choice=Login.sc.nextInt();
            Login.sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("\033[H\033[2J");
                    System.out.println("~~~~~~~~~~ADD-WALLET-AMOUNT~~~~~~~~~~\n");
                    System.out.print("Enter Amount: ");
                    uList.get(pos).amt+=Login.sc.nextInt();
                    Login.sc.nextLine();
                    System.out.print("\nAmount Added Successfully\nPress Enter to Continue...");
                    Login.sc.nextLine();
                    break;
                case 2:
                    System.out.print("\033[H\033[2J");
                    System.out.println("~~~~~~~~~~WALLET-AMOUNT~~~~~~~~~~\n");
                    System.out.println("Wallet Balance: "+uList.get(pos).amt);
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
