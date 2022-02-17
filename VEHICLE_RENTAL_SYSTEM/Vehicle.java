import java.util.ArrayList;
import java.util.List;

public class Vehicle
{
    String name;
    String numberPlate;
    int seats;
    int totKms;
    Boolean isAvailable = true;
    int borrowedCount;
    int rentPerDay;
    int price;
    int tenure;
    int serviceKms;
    Boolean isServiced = true;
    String type;
    Admin addedBy;

    Vehicle (String name, String numberPlate, int seats, int rentPerDay, int price, String type, Admin addedBy, int serviceKms)
    {
        this.name = name;
        this.numberPlate = numberPlate;
        this.seats = seats;
        this.price = price;
        this.rentPerDay = rentPerDay;
        this.type = type;
        this.addedBy = addedBy;
        this.serviceKms = serviceKms;
    }

    Vehicle () {}

    static List<Vehicle> vList = new ArrayList<>();

    static
    {
        vList.add(new Vehicle("Audi", "001", 4, 30000, 25900000, "CAR", Admin.aList.get(0), 3000));
        vList.add(new Vehicle("Honda", "002", 4, 19000, 6410000, "CAR", Admin.aList.get(0), 3000));
        vList.add(new Vehicle("Toyota", "003", 5, 15500, 1730000, "CAR", Admin.aList.get(0), 3000));
        vList.add(new Vehicle("Hyundai", "004", 6, 13500, 1022000, "CAR", Admin.aList.get(0), 3000));

        vList.add(new Vehicle("Honda Shine", "005", 2, 900, 78800, "BIKE", Admin.aList.get(0), 1500));
        vList.add(new Vehicle("TVS Apache", "006", 2, 1000, 122000, "BIKE", Admin.aList.get(0), 1500));
        vList.add(new Vehicle("TVS Jupiter", "007", 2, 1000, 82700, "BIKE", Admin.aList.get(0), 1500));
        vList.add(new Vehicle("Bajaj Pulsar", "008", 2, 1500, 150000, "BIKE", Admin.aList.get(0), 1500));
    }

    public void addVehicle(Admin admin)
    {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~ADD-VEHICLE~~~~~~~~~~\n");
        System.out.print("Enter Vehicle's Name: ");
        String name = Login.sc.nextLine();
        System.out.print("\nEnter Vehicle's Type (Car / Bike): ");
        String type = Login.sc.nextLine().toUpperCase();
        if (type.equals("BIKE"))
        {
            System.out.print("\nEnter Vehicle's Number Plate: ");
            String numberPlate = Login.sc.nextLine();
            for (Vehicle i : vList)
            {
                if(i.numberPlate.equals(numberPlate))
                {
                    System.out.println("\nVehicle with this Number Plate is Already Exists");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    return;
                }
            }
            System.out.print("\nEnter Vehicle's Price: ");
            int priceOfCar = Login.sc.nextInt();
            System.out.print("\nEnter Vehicle's Rent per Day: ");
            int rentPerDay = Login.sc.nextInt();
            vList.add(new Vehicle(name, numberPlate, 2, rentPerDay, priceOfCar, "BIKE", admin, 1500));
            System.out.println("\nVehicle has been Added Successfully");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
            Login.sc.nextLine();
        }
        else
        {
            System.out.print("\nEnter Vehicle's Number Plate No.: ");
            String numberPlate = Login.sc.nextLine();
            for (Vehicle i : vList)
            {
                if(i.numberPlate.equals(numberPlate))
                {
                    System.out.println("\nVehicle with this Number Plate is Already Exists");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    return;
                }
            }
            System.out.print("\nEnter Vehicle's No. of Seats: ");
            int seats = Login.sc.nextInt();
            System.out.print("\nEnter Vehicle's Price: ");
            int price = Login.sc.nextInt();
            System.out.print("\nEnter Vehicle's Rent per Day: ");
            int rentPerDay = Login.sc.nextInt();
            vList.add(new Vehicle(name, numberPlate, seats, rentPerDay, price, "CAR", admin, 3000));
            System.out.println("\nVehicle has been Added Successfully");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
            Login.sc.nextLine();
        }
    }

    public void modifyVehicle(Admin admin)
    {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~MODIFY-VEHICLE~~~~~~~~~~\n");
        System.out.print("Enter Vehicle's Number Plate No.: ");
        String numberPlate = Login.sc.nextLine();
        for (Vehicle i : vList)
        {
            if(i.numberPlate.equals(numberPlate))
            {
                if(i.isAvailable)
                {
                    System.out.println("\nVehicle's Name: "+i.name);
                    modify(i);
                    return;
                }
                else
                {
                    System.out.println("\nEntered Vehicle is Currently Issued");
                    System.out.println("Vehicle will not Able to Modify");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    return;
                }
            }
        }
        System.out.println("\nEntered Vehicle is Not Available");
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
    }

    public void modify(Vehicle i)
    {
        System.out.print("""
                        \n1. Change Price of Vehicle
                        2. Change Rent per Day
                        3. Exit

                        Enter Choice:\u00A0""");
        String choice = Login.sc.nextLine();
        switch (choice)
        {
            case "1":
                System.out.print("\nEnter Price of the Vehicle: ");
                int price = Integer.parseInt(Login.sc.nextLine());
                i.price = price;
                System.out.println("\nVehicle has been Modified Successfully");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
                break;
            case "2":
                System.out.print("\nEnter Rent per Day of the Vehicle: ");
                int rentPerDay = Integer.parseInt(Login.sc.nextLine());
                i.rentPerDay = rentPerDay;
                System.out.println("\nVehicle has been Modified Successfully");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
                break;
            case "3":
                return;
            default:
                System.out.println("\n!!!Invalid Input!!!");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
                break;
        }
    }

    public void removeVehicle(Admin admin)
    {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~REMOVE-VEHICLE~~~~~~~~~~\n");
        System.out.print("Enter Vehicle's Number Plate No.: ");
        String numberPlate = Login.sc.nextLine();
        int pos = 0;
        boolean flag = true;
        for (Vehicle i : vList)
        {
            if(i.numberPlate.equals(numberPlate))
            {
                System.out.println("Vehicle's Name: "+i.name);
                flag = false;
                if(i.isAvailable)
                {
                    break;
                }
                else
                {
                    System.out.println("\nEntered Vehicle is Currently Issued");
                    System.out.println("Vehicle will not Remove");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    return;
                }
            }
            pos++;
        }
        if (flag)
        {
            System.out.println("\nEntered Vehicle is Not Available");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
        else
        {
            vList.remove(pos);
            System.out.println("\nEntered Vehicle has been Removed Successfully");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
    }

    public void search()
    {
        t:while(true)
        {
            System.out.print("\033[H\033[2J");
            System.out.print("""
                            +==SEARCH-BY===============+
                            |                          |
                            | 1. Vehicle Name          |
                            | 2. Number Plate          |
                            | 3. Exit                  |
                            |                          |
                            +==========================+

                            Enter the Choice: """);
            String choice=Login.sc.nextLine();
            switch (choice)
            {
                case "1":
                    searchName();
                    break;
                case "2":
                    searchNumber();
                    break;
                case "3":
                    break t;
                default:
                    System.out.println("!!!Invalid Input!!!");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    break;
            }
        }
    }

    public void searchName()
    {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~SEARCH-VEHICLE~~~~~~~~~~\n");
        System.out.print("Enter Vehicle's Name: ");
        String name = Login.sc.nextLine();
        found :
        {
            boolean flag = false;
            for (Vehicle i : vList)
            {
                if (i.name.equalsIgnoreCase(name))
                {
                    flag = true;
                    System.out.println("\nVehicle's Number Plate: "+i.numberPlate);
                    System.out.println("Vehicle's Price: "+i.price);
                    System.out.println("Vehicle's Rent Per Day: "+i.rentPerDay);
                    System.out.println("Vehicle's No. of Seats: "+i.seats);
                    System.out.println("Vehicle's Borrowed Count: "+i.borrowedCount);
                    System.out.println("Vehicle's Total Kilometer Traveled: "+i.totKms);
                    System.out.println("Vehicle's Type (CAR / BIKE): "+i.type);
                    System.out.println("Vehicle's Remaining Service Kilometer: "+i.serviceKms);
                    System.out.println("Vehicle Availability: "+ (i.isAvailable ? "Available" : "Not Available"));
                    System.out.println("Vehicle Serviced: "+ (i.isServiced ? "Yes" : "No"));
                    System.out.println("\n****************************************************");
                    System.out.print("\nPress Enter to Continue / (0) to Exit: ");
                    if(Login.sc.nextLine().equals("0"))
                        break found;
                    System.out.print("\033[H\033[2J");
                    System.out.println("~~~~~~~~~~SEARCH-VEHICLE~~~~~~~~~~\n");
                    System.out.print("Enter Vehicle's Name: "+name+"\n");
                }
            }
            if (flag)
            {
                System.out.println("\nNo more Vehicle with the Entered Vehicle Name");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
            }
            else
            {
                System.out.println("\nEntered Vehicle Name is Not Available");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
            }
        }
    }

    public void searchNumber()
    {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~SEARCH-VEHICLE~~~~~~~~~~\n");
        System.out.print("Enter Vehicle's Number Plate: ");
        String numberPlate = Login.sc.nextLine();
        found :
        {
            for (Vehicle i : vList)
            {
                if(numberPlate.equals(i.numberPlate))
                {
                    System.out.println("\nVehicle's Name: "+i.name.toUpperCase());
                    System.out.println("Vehicle's Price: "+i.price);
                    System.out.println("Vehicle's Rent Per Day: "+i.rentPerDay);
                    System.out.println("Vehicle's No. of Seats: "+i.seats);
                    System.out.println("Vehicle's Borrowed Count: "+i.borrowedCount);
                    System.out.println("Vehicle's Total Kilometer Traveled: "+i.totKms);
                    System.out.println("Vehicle's Type (CAR / BIKE): "+i.type);
                    System.out.println("Vehicle's Remaining Service Kilometer: "+i.serviceKms);
                    System.out.println("Vehicle Availability: "+ (i.isAvailable ? "Available" : "Not Available"));
                    System.out.println("Vehicle Serviced: "+ (i.isServiced ? "Yes" : "No"));
                    System.out.println("\n****************************************************");
                    System.out.print("\nPress Enter to Continue...");
                    Login.sc.nextLine();
                    break found;
                }
            }
            System.out.println("\nEntered Vehicle Name is Not Available");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
    }

    public void vehicleList()
    {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~LISTS~~~~~~~~~~\n");
        System.out.println(" 1. Name Vise");
        System.out.println(" 2. Rental Price Vise");
        System.out.println(" 3. Car / Bike Vise");
        System.out.println(" 4. Exit\n");
        System.out.print("Enter the Choice: ");
        String choice = Login.sc.nextLine();
        if (choice.equals("4"))
            return;
        if (choice.equals("1"))
            nameList();
        else if (choice.equals("2"))
            priceList();
        else if (choice.equals("3"))
        {
            Transactions tr = new Transactions();
            tr.lists();
        }
        else
        {
            System.out.print("\033[H\033[2J");
            System.out.println("!!!Invalid Input!!!");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
    }

    public void nameList()
    {
        end :
        {
            int m = 0;
            for (int k = 65; k < 91; k++)
            {
                for (int i = 0; i < vList.size(); i++)
                {
                    if ((char)k == vList.get(i).name.toUpperCase().charAt(0))
                    {
                        System.out.print("\033[H\033[2J");
                        System.out.println("~~~~~~~~~~VEHICLE-LIST~~~~~~~~~~\n");
                        System.out.println("\nVehicle's Name: "+vList.get(i).name.toUpperCase());
                        System.out.println("Vehicle's Number Plate: "+vList.get(i).numberPlate);
                        System.out.println("Vehicle's Price: Rs."+vList.get(i).price);
                        System.out.println("Vehicle's Rent Per Day: Rs."+vList.get(i).rentPerDay);
                        System.out.println("Vehicle's No. of Seats: "+vList.get(i).seats);
                        System.out.println("Vehicle's Borrowed Count: "+vList.get(i).borrowedCount);
                        System.out.println("Vehicle's Total Kilometer Traveled: "+vList.get(i).totKms+"Kms");
                        System.out.println("Vehicle's Type (CAR / BIKE): "+vList.get(i).type);
                        System.out.println("Vehicle's Remaining Service Kilometer: "+vList.get(i).serviceKms+"Kms");
                        System.out.println("Vehicle Availability: "+ (vList.get(i).isAvailable ? "Available" : "Not Available"));
                        System.out.println("Vehicle Serviced: "+ (vList.get(i).isServiced ? "Yes" : "No"));
                        System.out.println("\n****************************************************\n");
                        for (int j = 0; j < vList.size(); j++)
                        {
                            if (m == j)
                                System.out.print("^  ");
                            else
                                System.out.print((j+1)+"  ");
                        }
                        m++;
                        System.out.println();
                        if (m == vList.size())
                        {
                            System.out.print("\nEnter (0) to Exit: ");
                        }
                        else
                        {
                            System.out.print("\nPress Enter to Next / (0) to Exit: ");
                        }
                        if (Login.sc.nextLine().equals("0"))
                            break end;
                    }
                }
            }
        }
    }

    public void priceList()
    {
        List<Vehicle> temps = vList;

        for(int i = 0, j; i < temps.size(); i++)
        {
            Vehicle temp = vList.get(i);
            for(j = i-1; j>-1 && temps.get(j).rentPerDay > temp.rentPerDay; j--)
            {
                temps.set(j+1, temps.get(j));
            }
            temps.set(j+1, temp);
        }
        int m = 0;

        for (Vehicle i : temps)
        {
            System.out.print("\033[H\033[2J");
            System.out.println("~~~~~~~~~~VEHICLE-LIST~~~~~~~~~~\n");
            System.out.println("\nVehicle's Name: "+i.name.toUpperCase());
            System.out.println("Vehicle's Number Plate: "+i.numberPlate);
            System.out.println("Vehicle's Price: Rs."+i.price);
            System.out.println("Vehicle's Rent Per Day: Rs."+i.rentPerDay);
            System.out.println("Vehicle's No. of Seats: "+i.seats);
            System.out.println("Vehicle's Borrowed Count: "+i.borrowedCount);
            System.out.println("Vehicle's Total Kilometer Traveled: "+i.totKms+"Kms");
            System.out.println("Vehicle's Type (CAR / BIKE): "+i.type);
            System.out.println("Vehicle's Remaining Service Kilometer: "+i.serviceKms+"Kms");
            System.out.println("Vehicle Availability: "+ (i.isAvailable ? "Available" : "Not Available"));
            System.out.println("Vehicle Serviced: "+ (i.isServiced ? "Yes" : "No"));
            System.out.println("\n****************************************************\n");
            for (int j = 0; j < vList.size(); j++)
            {
                if (m == j)
                    System.out.print("^  ");
                else
                    System.out.print((j+1)+"  ");
            }
            m++;
            System.out.println();
            if (m == vList.size())
            {
                System.out.print("\nEnter (0) to Exit: ");
            }
            else
            {
                System.out.print("\nPress Enter to Next / (0) to Exit: ");
            }
            if (Login.sc.nextLine().equals("0"))
                break;
        }
        System.out.print("\nPress Enter to Continue...");
        Login.sc.nextLine();
    }

    public void service(Admin admin)
    {
        boolean flag = true;
        for (Vehicle i : vList)
        {
            if (!i.isServiced)
            {
                flag = false;
                System.out.print("\033[H\033[2J");
                System.out.println("~~~~~~~~~~VEHICLE-SERVICE~~~~~~~~~~\n");
                System.out.println("Vehicle's Name:             "+i.name.toUpperCase());
                System.out.println("Vehicle's Number Plate:     "+i.numberPlate);
                System.out.println("Vehicle Type (Car / Bike)): "+i.type);
                System.out.print("\nPress Enter to Continue / (1) to Skip / (0) to Exit: ");
                String choice = Login.sc.nextLine();
                if (choice.equals("0"))
                    return;
                if (choice.equals("1"))
                    continue;
                i.isServiced = true;
                if (i.type.equals("BIKE"))
                    i.serviceKms = 3000;
                else
                    i.serviceKms = 1500;
                System.out.println("\nService has been Completed");
                System.out.print("\nPress Enter to Continue / (0) to Exit: ");
                if (Login.sc.nextLine().equals("0"))
                    return;
            }
        }
        System.out.print("\033[H\033[2J");
        if (flag)
            System.out.println("No Vehicles were waiting for Service");
        else
            System.out.println("No More Vehicles were waiting for Service");
        System.out.print("\nPress Enter to Continue...");
        Login.sc.nextLine();
    }

    public void lost(Admin admin)
    {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~VEHICLE-LOST~~~~~~~~~~\n");
        System.out.print("Enter Vehicle's Number Plate: ");
        String numberPlate = Login.sc.nextLine();
        found :
        {
            for (Transactions i : Transactions.tList)
            {
                if (i.vehicle.numberPlate.equals(numberPlate) && i.status.equals("Rented"))
                {
                    System.out.println("\nRenter's Name: "+i.user.name);
                    System.out.println("\nRequest has been sent to the User");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    i.status = "Lost";
                    i.vehicle.isAvailable = false;
                    break found;
                }
            }
            System.out.println("No User has Been Rent This Vehicle");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
    }

}
