package AppDev.VEHICLE_RENTAL_SYSTEM;

import java.util.ArrayList;
import java.util.List;

public class Admin
{
    String name;
    String userName;
    String password;

    Admin (String name, String userName, String password)
    {
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    Admin () {}

    static List<Admin> aList = new ArrayList<>();

    static
    {
        aList.add(new Admin("Vashikaran", "vashi001", "vashi"));
    }

    static Vehicle ve;
    static Transactions tr;
    static User us;

    public void logged(int pos)
    {
        t:while (true)
        {
            System.out.print("\033[H\033[2J");
            System.out.printf("~~~~~~~~~~~~~~~WELCOME %s~~~~~~~~~~~~~~~\n\n" , aList.get(pos).name.toUpperCase());
            System.out.print("""
                            +=====================================+
                            |                                     |
                            | 1.  Rent Vehicle                    |
                            | 2.  Return Vehicle                  |
                            | 3.  Add Admin                       |
                            | 4.  Add Vehicle                     |
                            | 5.  Modify Vehicle                  |
                            | 6.  Remove Vehicle                  |
                            | 7.  Search Vehicle                  |
                            | 8.  Vehicle List                    |
                            | 9.  Vehicle Lost                    |
                            | 10. Vehicle Service                 |
                            | 11. Change Security Deposit Amount  |
                            | 12. Generate Report                 |
                            | 13. Change Password                 |
                            | 14. Logout                          |
                            |                                     |
                            +=====================================+

                            Enter the Choice:\u00A0""");
            int choice=Login.sc.nextInt();
            Login.sc.nextLine();
            switch (choice)
            {
                case 1:
                    tr = new Transactions();
                    tr.transact(aList.get(pos));
                    break;
                case 2:
                    tr = new Transactions();
                    tr.returns(aList.get(pos));
                    break;
                case 3:
                    addAdmin();
                    break;
                case 4:
                    ve = new Vehicle();
                    ve.addVehicle(Admin.aList.get(pos));
                    break;
                case 5:
                    ve = new Vehicle();
                    ve.modifyVehicle(Admin.aList.get(pos));
                    break;
                case 6:
                    ve = new Vehicle();
                    ve.removeVehicle(Admin.aList.get(pos));
                    break;
                case 7:
                    ve = new Vehicle();
                    ve.search();
                    break;
                case 8:
                    ve = new Vehicle();
                    ve.vehicleList();
                    break;
                case 9:
                    ve = new Vehicle();
                    ve.lost(Admin.aList.get(pos));
                    break;
                case 10:
                    ve = new Vehicle();
                    ve.service(Admin.aList.get(pos));
                    break;
                case 11:
                    us = new User();
                    us.securityDepositAmt();
                    break;
                case 12:
                    tr = new Transactions();
                    tr.report();
                    break;
                case 13:
                    changePassword(pos);
                    break;
                case 14:
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

    public void addAdmin()
    {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~ADD-ADMIN~~~~~~~~~~\n");
        System.out.print("Enter New Admin's Name: ");
        String name = Login.sc.nextLine();
        System.out.print("\nEnter Admin's Username: ");
        String userName = Login.sc.nextLine();
        boolean flag = true;
        for (Admin i : aList) {
            if (i.userName.equals(userName))
            {
                flag = false;
                break;
            }
        }
        if(flag)
        {
            System.out.print("\nEnter Admin's Password: ");
            String password = Login.sc.nextLine();
            aList.add(new Admin(name, userName, password));
            System.out.println("\nNew Admin has been Added Successfully");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
        else
        {
            System.out.println("\nEntered Username is Already Exists");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
    }

    public void changePassword(int pos)
    {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~CHANGE-PASSWORD~~~~~~~~~~\n");
        System.out.print("Enter Old Password: ");
        String oldPass = Login.sc.nextLine();
        if(Admin.aList.get(pos).password.equals(oldPass))
        {
            System.out.print("\nEnter New Password: ");
            String newPass = Login.sc.nextLine();
            System.out.print("\nRetype Password: ");
            String newPass1 = Login.sc.nextLine();
            if(newPass.equals(newPass1))
            {
                Admin.aList.get(pos).password = newPass;
                System.out.println("\nPassword has been Changed");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
            }
            else
            {
                System.out.println("\nMismatch Password");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
            }
        }
        else
        {
            System.out.println("\nEntered Password is Wrong");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
    }

}
