package AppDev.AMAZON;

import java.util.ArrayList;
import java.util.List;

public class Seller {
    String userName;
    int userId;
    int pin;
    String status;

    static int id=100;

    static List<Seller> sellerList=new ArrayList<>();
    static {
        sellerList.add(new Seller("Annachi01", 609, 1234, "Approved"));
        sellerList.add(new Seller("Annachi02", 609, 1234, "Approved"));
    }

    Seller(String userName, int userId, int pin, String status){
        this.userName=userName;
        this.userId=userId;
        this.pin=pin;
        this.status=status;
    }

    Seller(){}

    public void seller() {
        t:while(true)
        {
            System.out.print("\033[H\033[2J");
            System.out.print("""
                                ~~~~~~~~~~SELLER~~~~~~~~~~

                                1. New User
                                2. Existing User
                                3. Exit

                                Enter Your Choice: """);
            int choice=Login.sc.nextInt();
            Login.sc.nextLine();
            switch (choice) {
                case 1:
                        newLogin();
                        break;
                case 2:
                        login();
                        break;
                case 3:
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

    public void newLogin() {
        System.out.print("\033[H\033[2J");
        System.out.print("""
                            ~~~~~~~~~~SELLER-SIGNING-UP~~~~~~~~~~

                            Enter UserName:\t""");
        String userName=Login.sc.nextLine();
        if(Seller.sellerList.size()!=0)
        {
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
        }
        System.out.print("\nEnter Pin: ");
        int pin=Login.sc.nextInt();
        Login.sc.nextLine();
        if(sellerList.toString().contains(userName))
        {
            System.out.println("\nUserName is already Exits");
            return;
        }
        sellerList.add(new Seller(userName, ++id, pin, "Pending"));
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~Thank You for creating an Amazon Seller Account pls wait for Admins approval~~~~\n");
        System.out.println("Your Seller ID: "+id);
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
    }

    public void login() {
        System.out.print("\033[H\033[2J");
        System.out.print("""
                        ~~~~~~~~~~SELLER-SIGNING-IN~~~~~~~~~~

                        Enter UserName:\t""");
        String uname=Login.sc.nextLine();
        boolean flag=true;
        int j=0;
        for(Seller i:sellerList)
        {
            if(i.userName.equals(uname))
            {
                flag=false;
                if(i.status.equals("Pending"))
                {
                    System.out.print("\033[H\033[2J");
                    System.out.println("Your ID is Waiting for Approval");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    return;
                }
                else if(i.status.equals("Disapproved"))
                {
                    System.out.print("\033[H\033[2J");
                    System.out.println("Your ID is Disapproved by Admin");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    return;
                }
                else if(i.status.equals("Blocked"))
                {
                    System.out.print("\033[H\033[2J");
                    System.out.println("Your ID is Blocked by Admin");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    return;
                }
                else
                {
                    break;
                }
            }
            j++;
        }
        if(flag)
        {
            System.out.println("UserName does not exists");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
            return;
        }
        System.out.print("\nEnter Pin: ");
        int pin=Login.sc.nextInt();
        Login.sc.nextLine();
        if(pin==sellerList.get(j).pin)
        {
            logged(j);
        }
        else
        {
            System.out.println("Wrong Pin");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
    }

    public void logged(int sindex) {
        Login.pr=new Product();
        t:while(true)
        {
            System.out.print("\033[H\033[2J");
            System.out.print("""
                            ~~~~~~~~~~WELCOME SELLER~~~~~~~~~~

                            1. Seller's Product
                            2. Add Product
                            3. Remove Product
                            4. Edit Product
                            5. Compare Product
                            6. Change Pin
                            7. Exit

                            Enter Your Choice: """);
            int choice=Login.sc.nextInt();
            Login.sc.nextLine();

            switch (choice) {
                case 1:
                    sellersProduct(sindex);
                    break;
                case 2:
                    addProduct(sindex);
                    break;
                case 3:
                    removeProduct(sindex);
                    break;
                case 4:
                    editProduct(sindex);
                    break;
                case 5:
                    compareProduct(sindex);
                    break;
                case 6:
                    pinChange(sindex);
                    break;
                case 7:
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

    public void sellersProduct(int sindex) {
        System.out.print("\033[H\033[2J");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %10s | %5s | %5s | %5s | %13s | %20s | %13s | %5s  |\n", "Product Name", "Quantity", "Warranty", "Return", "Product-Rating", "Product-Description", "No of Buyers", "Price");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        for(Product i:Product.plist)
        {
            if(i.sellerCode==Seller.sellerList.get(sindex).userId)
            {
                System.out.printf("| %10s | %10s | %8s | %6s | %13s | %20s | %10s | %10s |\n\n", i.productName, i.quantity, i.warranty, i.return_, i.productRating, i.productDescription, i.noOfBuy, i.price);
            }
        }
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
    }

    public void addProduct(int sindex) {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~ADD-PRODUCT~~~~~~~~~~\n");
        System.out.print("Product Name: ");
        String name=Login.sc.nextLine();
        boolean flag=true;
        for(Product i:Product.plist)
            if(i.productName.equals(name) && i.sellerCode==sellerList.get(sindex).userId)
            {
                flag=false;
                break;
            }
        if(flag)
        {
            System.out.print("Quantity: ");
            int quantity=Login.sc.nextInt();
            System.out.print("Price (per Product): ");
            int price=Login.sc.nextInt();
            Login.sc.nextLine();
            System.out.print("Product Description: ");
            String description=Login.sc.nextLine();
            System.out.print("Warranty (by Year): ");
            int warranty=Login.sc.nextInt();
            Login.sc.nextLine();
            System.out.print("Return (Y/N): ");
            String returnPolicy=Login.sc.nextLine();
            Product.plist.add(new Product(name, quantity, sellerList.get(sindex).userId, description, 0, price, warranty, returnPolicy, 0));
            System.out.print("\033[H\033[2J");
            System.out.println("Product Added Successfully");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
        else
        {
            System.out.println("\nProduct is Already Available");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
    }

    public void removeProduct(int sindex) {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~REMOVE-PRODUCT~~~~~~~~~~\n");
        System.out.print("Product Name: ");
        String name=Login.sc.nextLine();
        boolean flag=true;
        int j=0;
        for(Product i:Product.plist)
        {
            if(i.productName.equals(name) && i.sellerCode==sellerList.get(sindex).userId)
            {
                flag=false;
                Product.plist.remove(j);
                System.out.println("Product Removed Successfully");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
                break;
            }
            j++;
        }
        if(flag)
        {
            System.out.println("Product is Not Available");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
    }

    public void editProduct(int sindex) {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~EDIT-PRODUCT~~~~~~~~~~\n");
        System.out.print("Product Name: ");
        String name=Login.sc.nextLine();
        boolean flag=false;
        int j=0;
        for(Product i:Product.plist)
        {
            if(i.productName.equals(name) && i.sellerCode==sellerList.get(sindex).userId)
            {
                flag=true;
                break;
            }
            j++;
        }
        if(flag)
        {
            while(true)
            {
                System.out.print("\033[H\033[2J");
                System.out.println("~~~~~~~~~~EDIT-PRODUCT~~~~~~~~~~\n");
                System.out.print("Product Name: "+name+"\n");
                System.out.print("""
                                    1. Quantity
                                    2. Price
                                    3. Description
                                    4. Warranty
                                    5. Return

                                    Enter to Choice:""");
                int choice=Login.sc.nextInt();
                switch (choice)
                {
                    case 1:
                        System.out.print("Quantity: ");
                        Product.plist.get(j).quantity=Login.sc.nextInt();
                        Login.sc.nextLine();
                        break;
                    case 2:
                        System.out.print("Price (per Product): ");
                        Product.plist.get(j).price=Login.sc.nextInt();
                        Login.sc.nextLine();
                        break;
                    case 3:
                        System.out.print("Description:");
                        Login.sc.nextLine();
                        Product.plist.get(j).productDescription=Login.sc.nextLine();
                        break;
                    case 4:
                        System.out.print("Warranty (by Year): ");
                        Product.plist.get(j).warranty=Login.sc.nextInt();
                        Login.sc.nextLine();
                        break;
                    case 5:
                        System.out.print("Return (Y/N): ");
                        Product.plist.get(j).return_=Login.sc.nextLine();
                        break;
                    default:
                        System.out.print("Invalid Input (Press Enter to Continue...)");
                        Login.sc.nextLine();
                        break;
                }
                System.out.print("\033[H\033[2J");
                System.out.print("Product Edited Successfully\n\nUpdate Same Product(1) / Exit(0): ");
                int a=Login.sc.nextInt();
                Login.sc.nextLine();
                if(a==0)
                {
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    return;
                }
                else
                {
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                }
            }
        }
        else
        {
            System.out.println("\nProduct is Not Available");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
    }

    public void compareProduct(int sindex) {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~PRODUCT-COMPARE~~~~~~~~~~\n");
        System.out.print("Product Name: ");
        String pname=Login.sc.nextLine();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %s | %10s | %5s | %5s | %5s | %13s | %20s | %13s | %5s    |\n", "Seller ID","Product Name", "Quantity", "Warranty", "Return", "Product-Rating", "Product-Description", "No of Buyers", "Price");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        boolean flag=true;
        for(int i=0;i<Product.plist.size();i++)
        {
            if(Product.plist.get(i).productName.equals(pname))
            {
                if(Product.plist.get(i).sellerCode==sellerList.get(sindex).userId)
                    System.out.print("(YOUR'S)");
                else
                    System.out.print("(OTHERS)");
                System.out.printf("| %s | %10s | %10s | %8s | %6s | %13s | %20s | %10s | %10s |\n\n", Product.plist.get(i).sellerCode, Product.plist.get(i).productName, Product.plist.get(i).quantity, Product.plist.get(i).warranty, Product.plist.get(i).return_, Product.plist.get(i).productRating, Product.plist.get(i).productDescription, Product.plist.get(i).noOfBuy, Product.plist.get(i).price);
                flag=false;
            }
        }
        if(flag)
        {
            System.out.println("Entered Product is not Available");
        }
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
    }

    public void pinChange(int sindex) {
        System.out.print("\033[H\033[2J");
        System.out.print("Enter new Pin: ");
        int pin=Login.sc.nextInt();
        System.out.print("\nEnter Confirm pin: ");
        int pin1=Login.sc.nextInt();
        Login.sc.nextLine();
        if(pin==pin1)
        {
            sellerList.get(sindex).pin=pin;
            System.out.print("\nPin Changed Successfully (Press Enter to Continue...)");
            Login.sc.nextLine();
        }
        else
        {
            System.out.println("\nPin Mismatch");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
    }

}
