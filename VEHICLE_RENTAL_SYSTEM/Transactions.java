package AppDev.VEHICLE_RENTAL_SYSTEM;

import java.util.ArrayList;
import java.util.List;

public class Transactions
{
    Vehicle vehicle;
    Admin admin;
    User user;
    String status;
    double a, b, c, d, e, amount;
    String name, vehicleName;

    Transactions (Vehicle vehicle, Admin admin, User user)
    {
        this.vehicle = vehicle;
        this.admin = admin;
        this.user = user;
        this.status = "Rented";
        a = 0;
        b = 0;
        c = 0;
        d = 0;
        e = 0;
        amount = 0;
        name = user.name;
        vehicleName = vehicle.name;
    }

    Transactions () {}

    static List<Transactions> tList = new ArrayList<>();

    public void transact(Admin admin)
    {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~RENT-VEHICLE~~~~~~~~~~\n");
        System.out.print("Enter User's Username: ");
        String userName = Login.sc.nextLine();
        found :
        {
            for (User i : User.uList)
            {
                if (i.userName.equalsIgnoreCase(userName))
                {
                    System.out.print("\nEnter Vehicle's Number Plate: ");
                    String numberPlate = Login.sc.nextLine();
                    found1 :
                    {
                        for (Vehicle j : Vehicle.vList)
                        {
                            if (j.numberPlate.equals(numberPlate))
                            {
                                if (j.isAvailable && j.isServiced)
                                {
                                    if ((i.amt > i.securityDepositAmtBike && j.type.equals("BIKE")) || (i.amt > i.securityDepositAmtCar && j.type.equals("CAR")))
                                    {
                                        if ((i.borrow[0] == 1 && j.type.equals("BIKE")) || (i.borrow[1] == 1 && j.type.equals("CAR")))
                                        {
                                            System.out.println("\nTransaction Failed\nUser can Rent at most 1 Car and 1 Bike");
                                            System.out.print("Press Enter to Continue...");
                                            Login.sc.nextLine();
                                        }
                                        else
                                        {
                                            tList.add(new Transactions(j, admin, i));
                                            if (j.type.equals("Bike"))
                                                i.borrow[0] = 1;
                                            else
                                                i.borrow[1] = 1;
                                            j.isAvailable = false;
                                            j.tenure++;
                                            j.borrowedCount++;
                                            System.out.println("\nRent Per Day: Rs."+j.rentPerDay);
                                            System.out.println("\nVehicle has been Rented to User");
                                            System.out.println("Pay the Amount on Return");
                                            System.out.print("Press Enter to Continue...");
                                            Login.sc.nextLine();
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("\nTransaction Failed\nDue to Low Security Deposit Amount");
                                        System.out.print("Press Enter to Continue...");
                                        Login.sc.nextLine();
                                    }
                                }
                                else
                                {
                                    System.out.println("\nEntered Vehicle is Currently not Available");
                                    System.out.print("Press Enter to Continue...");
                                    Login.sc.nextLine();
                                }
                                break found1;
                            }
                        }
                        System.out.println("\nEntered Vehicle's Number Plate is not Available");
                        System.out.print("Press Enter to Continue...");
                        Login.sc.nextLine();
                    }
                    break found;
                }
            }
            System.out.println("\nEntered Username is not Available");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
    }

    public void extend(User user)
    {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~~EXTEND-TENURE-OF-RENTED-VEHICLE~~~~~~~~~~\n");
        System.out.print("\nEnter Vehicle's Number Plate: ");
        String numberPlate = Login.sc.nextLine();
        found :
        {
            for (Transactions i : tList)
            {
                if (i.user.name.equals(user.name) && i.vehicle.numberPlate.equals(numberPlate))
                {
                    if (i.vehicle.tenure < 3)
                    {
                        i.vehicle.tenure++;
                        System.out.println("\nTenure of the Rented Vehicle Extended for 1 day");
                        System.out.print("Press Enter to Continue...");
                        Login.sc.nextLine();
                    }
                    else
                    {
                        System.out.println("\nTenure of the Rented Vehicle Cannot Exceed more than 2 days,\nReturn a Vehicle");
                        System.out.print("Press Enter to Continue...");
                        Login.sc.nextLine();
                    }
                    break found;
                }
            }
            System.out.println("\nEntered Vehicle was not Rented by You");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
    }

    public void returns(Admin admin)
    {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~RETURN-VEHICLE~~~~~~~~~~\n");
        System.out.print("Enter User's Username: ");
        String userName = Login.sc.nextLine();
        System.out.print("\nEnter Vehicle's Number Plate: ");
        String numberPlate = Login.sc.nextLine();
        found :
        {
            for (Transactions i : tList)
            {
                if (i.user.userName.equals(userName) && i.vehicle.numberPlate.equals(numberPlate) && i.status.equals("Rented"))
                {
                    i.amount = i.vehicle.tenure * i.vehicle.rentPerDay;
                    i.a = i.amount;
                    System.out.print("\nTotal Kilometers Traveled: ");
                    int run = Login.sc.nextInt();
                    i.vehicle.totKms += run;
                    i.vehicle.serviceKms -= run;
                    if (i.vehicle.serviceKms <= 0)
                    {
                        i.vehicle.isServiced = false;
                        i.vehicle.isAvailable = false;
                    }
                    Login.sc.nextLine();
                    if (run / i.vehicle.tenure > 500)
                    {
                        i.b = i.amount * 0.15;
                        i.amount += i.b;
                    }
                    System.out.print("\nCar Damages LOW(a), MEDIUM(b), HIGH(c), NOTHING(d): ");
                    String damage = Login.sc.nextLine();
                    if (damage.equalsIgnoreCase("a"))
                    {
                        i.c = i.amount * 0.2;
                        i.amount += i.c;
                    }
                    else if (damage.equalsIgnoreCase("b"))
                    {
                        i.d = i.amount * 0.5;
                        i.amount += i.d;
                    }
                    else if (damage.equalsIgnoreCase("c"))
                    {
                        i.e = i.amount * 0.75;
                        i.amount += i.e;
                    }
                    i.vehicle.tenure = 0;
                    i.vehicle.isAvailable = true;
                    i.status = "Returned";

                    System.out.print("\033[H\033[2J");
                    System.out.println("\t      >>>Total Bill<<<\n");
                    System.out.printf("%-36s%s\n","Rented By: ", i.name.toUpperCase());
                    System.out.printf("%-36s%s\n\n","Rented Vehicle's Name: ", i.vehicleName.toUpperCase().toUpperCase());
                    System.out.printf("%-36s%s%s\n", "Rented Amount: ", "Rs.", i.a);
                    if (i.b != 0)
                        System.out.printf("%-36s%s%s\n", "Traveled more than 500km per Day: ", "Rs.", i.b);
                    if (damage.equals("a"))
                        System.out.printf("%-36s%s%s\n", "Fine Amount for Small Car Damages: ", "Rs.", i.c);
                    if (damage.equals("b"))
                        System.out.printf("%-36s%s%s\n", "Fine Amount for Partial Car Damages: ", "Rs.", i.d);
                    if (damage.equals("c"))
                        System.out.printf("%-36s%s%s\n", "Fine Amount for Heavy Car Damages: ", "Rs.", i.e);
                    System.out.printf("%-36s%s%s\n", "Total Bill Amount: ", "Rs.", i.amount);
                    System.out.println("\n\t      !!!Thank You!!!\n\n(Pay Bill Amount in User Page)");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    break found;
                }
            }
            System.out.println("\nEntered Username is not Rented the Entered Vehicle");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
    }

    public void report()
    {
        t : while(true)
        {
            System.out.print("\033[H\033[2J");
            System.out.print("""
                            +==REPORTS====================+
                            |                             |
                            | 1. Service List             |
                            | 2. Cars / Bikes List        |
                            | 3. Rented Vehicles          |
                            | 4. Not Been Rented Vehicles |
                            | 5. Transactions             |
                            | 6. Exit                     |
                            |                             |
                            +=============================+

                            Enter the Choice:\u00A0""");
            String choice = Login.sc.nextLine();
            switch (choice)
            {
                case "1":
                    service();
                    break;
                case "2":
                    lists();
                    break;
                case "3":
                    rented();
                    break;
                case "4":
                    notRented();
                    break;
                case "5":
                    transactions();
                    break;
                case "6":
                    break t;
                default:
                    System.out.print("\033[H\033[2J");
                    System.out.println("!!!Invalid Input!!!");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
            }
        }
    }

    public void service()
    {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~SERVICES~~~~~~~~~~\n");
        boolean flag = true;
        for (Vehicle i : Vehicle.vList)
        {
            if (!i.isServiced)
            {
                flag = false;
                System.out.println("Vehicle Name: "+i.name);
                System.out.println("Vehicle Number Plate: "+i.numberPlate);
                System.out.println("\n***************************************************************\n");
            }
        }
        if (flag)
        {
            System.out.println("All Vehicle are Serviced");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
            return;
        }
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
        return;
    }

    public void lists()
    {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~VEHICLES-LIST~~~~~~~~~~\n");
        System.out.println(" 1. Cars");
        System.out.println(" 2. Bikes");
        System.out.println(" 3. Exit\n");
        System.out.print("Enter the Choice: ");
        String choice = Login.sc.nextLine();
        if (choice.equals("3"))
            return;
        if (choice.equals("1"))
        {
            System.out.print("\033[H\033[2J");
            System.out.println("~~~~~~~~~~CAR-LISTS~~~~~~~~~~");
        }
        else
        {
            System.out.print("\033[H\033[2J");
            System.out.println("~~~~~~~~~~BIKE-LISTS~~~~~~~~~~");
        }
        for (Vehicle i : Vehicle.vList)
        {
            if (i.type.equals("CAR") && choice.equals("1"))
            {
                System.out.println("\nVehicle's Name: "+i.name);
                System.out.println("Vehicle's Number Plate: "+i.numberPlate);
                System.out.println("Vehicle's Price: "+i.price);
                System.out.println("Vehicle's Rent Per Day: "+i.rentPerDay);
                System.out.println("****************************************************");
            }
            else if (i.type.equals("BIKE") && choice.equals("2"))
            {
                System.out.println("\nVehicle's Name: "+i.name);
                System.out.println("Vehicle's Number Plate: "+i.numberPlate);
                System.out.println("Vehicle's Price: "+i.price);
                System.out.println("Vehicle's Rent Per Day: "+i.rentPerDay);
                System.out.println("****************************************************");
            }
        }
        System.out.print("\nPress Enter to Continue...");
        Login.sc.nextLine();
    }

    public void rented()
    {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~RENTED-VEHICLES~~~~~~~~~~\n");
        boolean flag = true;
        for (Transactions i : tList)
        {
            if (i.status.equals("Rented"))
            {
                flag = false;
                System.out.println("Vehicle Name: "+i.vehicleName);
                System.out.println("Vehicle Number Plate: "+i.vehicle.numberPlate);
                System.out.println("\n***************************************************************\n");
            }
        }
        if (flag)
        {
            System.out.println("No Vehicles has been Rented Currently");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
            return;
        }
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
        return;
    }

    public void notRented()
    {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~NOT-BEEN-RENTED-VEHICLES~~~~~~~~~~\n");
        boolean flag = true;
        for (Vehicle i : Vehicle.vList)
        {
            if (i.isAvailable)
            {
                flag = false;
                System.out.println("Vehicle Name: "+i.name);
                System.out.println("Vehicle Number Plate: "+i.numberPlate);
                System.out.println("***************************************************************\n");
            }
        }
        if (flag)
        {
            System.out.println("All Vehicles has been Rented");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
            return;
        }
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
        return;
    }

    public void transactions()
    {
        if (tList.size() == 0)
        {
            System.out.print("\033[H\033[2J");
            System.out.println("~~~~~~~~~~TRANSACTIONS~~~~~~~~~~\n");
            System.out.println("No Transaction has been made");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
            return;
        }
        for (Transactions i : Transactions.tList)
        {
            System.out.print("\033[H\033[2J");
            System.out.println("~~~~~~~~~~REPORT~~~~~~~~~~\n");
            System.out.printf("%-25s%s\n", "Vehicle Name: ", i.vehicleName);
            System.out.printf("%-25s%s\n", "Vehicle License Plate: ", i.vehicle.numberPlate);
            System.out.printf("%-25s%s\n", "Vehicle Type: ", i.vehicle.type);
            System.out.printf("%-25s%s\n", "Total KM Travelled: ", i.vehicle.totKms);
            System.out.printf("%-25s%s\n", "Vehicle Rent per Day: ", i.vehicle.rentPerDay);
            System.out.printf("%-25s%s\n", "Total Amount: ", i.amount);
            System.out.printf("%-25s%s\n", "Status: ", i.status);
            System.out.printf("%-25s%s\n", "Buyer's Name: ", i.user.name);
            System.out.printf("%-25s%s\n", "Seller's Name: ", i.admin.name);
            System.out.print("\nPress Enter to Next / (0) to Exit: ");
            if (Login.sc.nextLine().equals("0"))
                break;
        }
        System.out.println("\nNo More Transactions");
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
    }

    public void transactions(User user)
    {
        boolean flag = true;
        for (Transactions i : Transactions.tList)
        {
            System.out.print("\033[H\033[2J");
            System.out.println("~~~~~~~~~~TRANSACTIONS~~~~~~~~~~\n");
            if (i.user.name.equals(user.name))
            {
                flag = false;
                System.out.printf("%-25s%s\n", "Vehicle Name: ", i.vehicleName);
                System.out.printf("%-25s%s\n", "Vehicle License Plate: ", i.vehicle.numberPlate);
                System.out.printf("%-25s%s\n", "Vehicle Type: ", i.vehicle.type);
                System.out.printf("%-25s%s\n", "Total KM Travelled: ", i.vehicle.totKms);
                System.out.printf("%-25s%s\n", "Vehicle Rent per Day: ", i.vehicle.rentPerDay);
                System.out.printf("%-25s%s\n", "Total Amount: ", i.amount);
                System.out.printf("%-25s%s\n", "Status: ", i.status);
                System.out.printf("%-25s%s\n", "Seller Name: ", i.admin.name);
                System.out.print("\nPress Enter to Next / (0) to Exit: ");
                if (Login.sc.nextLine().equals("0"))
                    break;
            }
        }
        if (flag)
        {
            System.out.print("\033[H\033[2J");
            System.out.println("~~~~~~~~~~TRANSACTIONS~~~~~~~~~~\n");
            System.out.println("No Transaction has been made by you");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
            return;
        }
        System.out.println("\nNo More Transactions");
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
    }

}
