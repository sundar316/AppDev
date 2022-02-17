package AppDev.AMAZON;

public class Admin {
    public void admin(){
        while(true)
        {
            System.out.print("\033[H\033[2J");
            System.out.print("""
                                ~~~~~~~~~~ADMIN-LOGIN~~~~~~~~~~

                                Enter UserName:\t""");
            String uname=Login.sc.nextLine();
            System.out.print("\nEnter Pin: ");
            int pin=Login.sc.nextInt();
            if(uname.equals("Admin") && pin==1234)
            {
                logged();
                break;
            }
            else
            {
                System.out.print("\033[H\033[2J");
                System.out.println("Wrong UserName/Password");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
                Login.sc.nextLine();
            }
        }
    }

    public void logged(){
        t:while(true)
        {
            System.out.print("\033[H\033[2J");
            System.out.print("""
                                ~~~~~~~~~~ADMIN~~~~~~~~~~

                                1. Add Seller
                                2. Block Seller
                                3. Waiting Seller List
                                4. Exit

                                Enter Your Choice:""");
            int choice=Login.sc.nextInt();
            Login.sc.nextLine();
            switch (choice) {
                case 1:
                        add();
                        break;
                case 2:
                        remove();
                        break;
                case 3:
                        waitSL();
                        break;
                case 4:
                        System.out.print("\033[H\033[2J");
                        break t;
                default:
                        System.out.print("\033[H\033[2J");
                        System.out.println("Invalid Input\nPress Enter to Continue...");
                        Login.sc.nextLine();
                        break;
            }
        }
    }

    public void add(){
        System.out.print("\033[H\033[2J");
        System.out.print("""
                            ~~~~~~~~~~SELLER-ADD~~~~~~~~~~

                            Enter UserName:\t""");
        String userName=Login.sc.nextLine();
        System.out.print("\nEnter Pin: ");
        int pin=Login.sc.nextInt();
        Login.sc.nextLine();
        for(Seller i:Seller.sellerList)
        {
            if(i.userName.equals(userName))
            {
                System.out.print("\033[H\033[2J");
                System.out.println("UserName is already Exits");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
                return;
            }
        }
        Seller.sellerList.add(new Seller(userName, ++Seller.id, pin, "Approved"));
        System.out.print("\033[H\033[2J");
        System.out.println("Seller ID: "+Seller.id);
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
    }

    public void remove(){
        System.out.print("\033[H\033[2J");
        System.out.print("""
                            ~~~~~~~~~~SELLER-BLOCK~~~~~~~~~~

                            Enter UserName:\t""");
        String userName=Login.sc.nextLine();
        for(Seller i:Seller.sellerList)
        {
            if(i.userName.equals(userName))
            {

                System.out.print("\033[H\033[2J");
                i.status="Blocked";
                System.out.println(i.userName+" Seller is Blocked");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
                return;
            }
        }
        System.out.print("\033[H\033[2J");
        System.out.println("Entered User Name is Not Exists");
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
    }

    public void waitSL() {
        int count=0;
        for(Seller i:Seller.sellerList)
        {
            if(i.status.equals("Pending"))
            {
                count++;
            }
        }
        if(count==0)
        {
            System.out.print("\033[H\033[2J");
            System.out.println("No Pending Approvals.");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
            return;
        }
        for(int i=0;i<Seller.sellerList.size();i++)
        {
            if(Seller.sellerList.get(i).status.equals("Pending"))
            {
                System.out.print("\033[H\033[2J");
                System.out.println("~~~~~~~~~~Sellers Waiting List~~~~~~~~~~\n");
                System.out.println("User Name: "+Seller.sellerList.get(i).userName+"\n\nUser ID: "+Seller.sellerList.get(i).userId+"");
                System.out.print("\nApprove(1)/Disapprove(0): ");
                int check=Login.sc.nextInt();
                Login.sc.nextLine();
                if(check==1)
                    Seller.sellerList.get(i).status="Approved";
                else
                    Seller.sellerList.get(i).status="Disapproved";
                count--;
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
            }
        }
        if(count==0)
        {
            System.out.print("\033[H\033[2J");
            System.out.println("No more pending Approvals.");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
    }
}
