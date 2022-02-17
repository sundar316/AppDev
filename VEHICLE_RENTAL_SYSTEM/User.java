import java.util.ArrayList;
import java.util.List;

public class User
{
    String name;
    String userName;
    String password;
    int amt;
    int borrow[] = new int[2];
    int securityDepositAmtCar;
    int securityDepositAmtBike;

    User (String name, String userName, String password)
    {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.amt = 30000;
        securityDepositAmtCar = 10000;
        securityDepositAmtBike = 3000;
    }

    User () {}

    static List<User> uList = new ArrayList<>();

    static Vehicle ve;
    static Transactions tr;

    static
    {
        uList.add(new User("Sana", "sana002", "sana"));
        uList.add(new User("Chitti", "chitti003", "chitti"));
        uList.add(new User("Pakshi Rajan", "rajan004", "pakshi"));
    }

    public void logged(int pos)
    {
        t:while (true)
        {
            System.out.print("\033[H\033[2J");
            System.out.printf("~~~~~~~~~~~~~~~WELCOME %s~~~~~~~~~~~~~~~\n\n" , uList.get(pos).name.toUpperCase());
            System.out.print("""
                            +======================================+
                            |                                      |
                            | 1.  Extend Tenure of Rented Vehicle  |
                            | 2.  Wallet Amount                    |
                            | 3.  Vehicle List                     |
                            | 4.  Search Vehicle                   |
                            | 5.  Pending Transactions             |
                            | 6.  Transactions                     |
                            | 7.  Logout                           |
                            |                                      |
                            +======================================+

                            Enter the Choice:\u00A0""");
            int choice=Login.sc.nextInt();
            Login.sc.nextLine();
            switch (choice)
            {
                case 1:
                    tr = new Transactions();
                    tr.extend(uList.get(pos));
                    break;
                case 2:
                    walletAmount(pos);
                    break;
                case 3:
                    ve = new Vehicle();
                    ve.vehicleList();
                    break;
                case 4:
                    ve = new Vehicle();
                    ve.searchName();
                    break;
                case 5:
                    pending(uList.get(pos), pos);
                    break;
                case 6:
                    tr = new Transactions();
                    tr.transactions(uList.get(pos));
                    break;
                case 7:
                    System.out.print("\033[H\033[2J");
                    System.out.print("Signing Out");
                    int i = 4;
                    while(i-- > 0)
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
                    break t;
                default:
                    System.out.print("\033[H\033[2J");
                    System.out.println("!!!Invalid Input!!!");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
            }
        }
    }

    public void walletAmount(int pos)
    {
        System.out.print("\033[H\033[2J");
        System.out.print("""
                        +==WALLET=================+
                        |                         |
                        |  1. Add Wallet Amount   |
                        |  2. Show Wallet Amount  |
                        |  3. Exit                |
                        |                         |
                        +=========================+

                        Enter Your Choice:\u00A0""");
        int choice = Login.sc.nextInt();
        Login.sc.nextLine();

        switch (choice)
        {
            case 1:
                System.out.print("\033[H\033[2J");
                System.out.println("~~~~~~~~~~ADD-WALLET-AMOUNT~~~~~~~~~~\n");
                System.out.print("Enter Amount: ");
                uList.get(pos).amt += Login.sc.nextInt();
                Login.sc.nextLine();
                System.out.print("\nAmount Added Successfully\nPress Enter to Continue...");
                Login.sc.nextLine();
                break;
            case 2:
                System.out.print("\033[H\033[2J");
                System.out.println("~~~~~~~~~~WALLET-AMOUNT~~~~~~~~~~\n");
                System.out.println("Wallet Balance: " + uList.get(pos).amt);
                System.out.print("\nPress Enter to Continue...");
                Login.sc.nextLine();
                break;
            case 3:
                break;
            default:
                System.out.print("\033[H\033[2J");
                System.out.print("Invalid Input\nPress Enter to Continue...");
                Login.sc.nextLine();
                break;
        }
    }

    public void pending(User user, int pos)
    {
        boolean flag = true;
        for (Transactions i : Transactions.tList)
        {
            if (i.user.name.equals(user.name) && i.status.equals("Returned"))
            {
                flag = false;
                System.out.print("\033[H\033[2J");
                System.out.println("\t      >>>Total Bill<<<\n");
                System.out.printf("%-36s%s\n","Rented By: ", i.name.toUpperCase());
                System.out.printf("%-36s%s\n\n","Rented Vehicle's Name: ", i.vehicleName.toUpperCase().toUpperCase());
                System.out.printf("%-36s%s%s\n", "Rented Amount: ", "Rs.", i.a);
                if (i.b != 0)
                    System.out.printf("%-36s%s%s\n", "Traveled more than 500km per Day: ", "Rs.", i.b);
                if (i.c != 0)
                    System.out.printf("%-36s%s%s\n", "Fine Amount for Small Car Damages: ", "Rs.", i.c);
                if (i.d != 0)
                    System.out.printf("%-36s%s%s\n", "Fine Amount for Partial Car Damages: ", "Rs.", i.d);
                if (i.e != 0)
                    System.out.printf("%-36s%s%s\n", "Fine Amount for Heavy Car Damages: ", "Rs.", i.e);
                System.out.printf("%-36s%s%s\n", "Total Bill Amount: ", "Rs.", i.amount);
                System.out.print("\nPress Enter to Pay a Bill / (1) to Skip / (0) to Exit: ");
                String choice = Login.sc.nextLine();
                if (choice.equals("0"))
                    return;
                if (choice.equals("1"))
                    continue;
                System.out.print("\033[H\033[2J");
                System.out.printf("%-10s%s%s\n", "Total Amount: ", "Rs.", i.amount);
                if (i.amount > i.user.amt)
                {
                    System.out.println("\nWallet Amount is Low");
                    System.out.print("\nPress Enter to Add Wallet Amount / (0) to Exit: ");
                    if (Login.sc.nextLine().equals("0"))
                        return;
                    walletAmount(pos);
                    return;
                }
                i.status = "Completed";
                i.user.amt -= i.amount;
                if (i.vehicle.type.equals("CAR"))
                    i.user.borrow[1] = 0;
                else
                    i.user.borrow[0] = 0;
                System.out.println("\nTransaction has been Completed :)");
                System.out.print("\nPress Enter to Continue / (0) to Exit: ");
                if (Login.sc.nextLine().equals("0"))
                    return;
            }

            else if (i.user.name.equals(user.name) && i.status.equals("Lost"))
            {
                flag = false;
                System.out.print("\033[H\033[2J");
                System.out.println("\t      >>>Bill For Vehicle Lost<<<\n");
                System.out.println("Vehicle's Name:            " + i.vehicle.name);
                System.out.println("Vehicle's Number Plate:    " + i.vehicle.numberPlate);
                System.out.println("Vehicle Type (Car / Bike): " + i.vehicle.type);
                System.out.println("Vehicle's Price:           Rs." + i.vehicle.price);
                System.out.println("\nAmount to Pay:             Rs." + (int)(i.vehicle.price * 0.85));
                System.out.println("(85% of the Vehicle's Price)");
                System.out.print("\nPress Enter to Pay / (1) to Skip / (0) to Exit: ");
                String choice = Login.sc.nextLine();
                if (choice.equals("0"))
                    return;
                if (choice.equals("1"))
                    continue;
                if (i.user.amt < (int)(i.vehicle.price * 0.85))
                {
                    System.out.println("\nWallet Amount is Low");
                    System.out.print("\nPress Enter to Add Wallet Amount / (0) to Exit: ");
                    if (Login.sc.nextLine().equals("0"))
                        return;
                    walletAmount(pos);
                    return;
                }
                i.user.amt -= (int)(i.vehicle.price * 0.85);
                System.out.println("\nTransaction has been Completed :)");
                i.status = "Gone";
                System.out.print("\nPress Enter to Continue / (0) to Exit: ");
                if (Login.sc.nextLine().equals("0"))
                    return;
            }
        }
        if (flag)
        {
            System.out.print("\033[H\033[2J");
            System.out.println("\nNo Pending Transactions");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
        else
        {
            System.out.print("\033[H\033[2J");
            System.out.println("\nNo More Pending Transactions");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
    }

    public void securityDepositAmt()
    {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~CHANGE-SECURITY-DEPOSIT-AMOUNT~~~~~~~~~~\n");
        System.out.print("Enter User's Username: ");
        String userName = Login.sc.nextLine();
        for (User i : uList)
        {
            if (i.userName.equals(userName))
            {
                System.out.print("\nEnter New Security Deposit Amount for Car: ");
                i.securityDepositAmtCar = Login.sc.nextInt();;
                System.out.print("\nEnter New Security Deposit Amount for Bike: ");
                i.securityDepositAmtBike = Login.sc.nextInt();
                System.out.println("\nNew Security Deposit Amount is Updated");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
                Login.sc.nextLine();
                return;
            }
        }
        System.out.println("Username is not Available");
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
    }

}
