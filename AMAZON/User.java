import java.util.ArrayList;
import java.util.List;

public class User {

    String userName;
    int pin;
    int amount;
    boolean review;
    ArrayList<String> cart=new ArrayList<>();
    ArrayList<String> buy=new ArrayList<>();
    ArrayList<String> return_=new ArrayList<>();

    static List<User> uList=new ArrayList<>();
    static {
        uList.add(new User("Tom", 1234, 10000, false, "", "", ""));
        uList.add(new User("Jerry", 1234, 10000, false, "", "", ""));
    }

    User(String userName, int pin, int amount, boolean review, String cart, String buy ,String ret) {
        this.userName=userName;
        this.pin=pin;
        this.amount=amount;
        this.review=review;
        this.cart.add(cart);
        this.buy.add(buy);
        this.return_.add(ret);
    }

    User(){}

    public void user() {
        t:while (true) {
            System.out.print("\033[H\033[2J");
            System.out.print("""
                            ~~~~~~~~~~USER~~~~~~~~~~

                            1. New User
                            2. Existing User
                            3. Exit

                            Enter Your Choice:""");
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
                    System.out.print("Invalid Input\nPress Enter to Continue...");
                    Login.sc.nextLine();
                    break;
            }
        }
    }

    public void newLogin() {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~USER-SIGNING-UP~~~~~~~~~~\n");
        System.out.print("Enter User Name: ");
        String uname=Login.sc.nextLine();
        for(User i:User.uList)
        {
            if(uname.equals(i.userName))
            {
                System.out.print("\033[H\033[2J");
                System.out.println("User Name is Already Exists");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
                return;
            }
        }
        System.out.print("\nEnter New Pin: ");
        int pin=Login.sc.nextInt();
        System.out.print("\nEnter Confirm Pin: ");
        int cpin=Login.sc.nextInt();
        Login.sc.nextLine();
        if(pin==cpin)
        {
            uList.add(new User(uname, pin, 0, false, "", "", ""));
            System.out.print("\033[H\033[2J");
            System.out.println("Sign Up Successfully\n");
            System.out.println("~~~~Thank You for creating an Amazon User Account :) ~~~~\n");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
            return;
        }
        else
        {
            System.out.print("\033[H\033[2J");
            System.out.println("Pin Mismatch");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
            return;
        }
    }

    public void login() {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~USER-SIGNING-IN~~~~~~~~~~\n");
        System.out.print("Enter User Name: ");
        String uname=Login.sc.nextLine();
        int j=0;
        boolean flag=true;
        for(User i:User.uList)
        {
            if(uname.equals(i.userName))
            {
                flag=false;
                break;
            }
            j++;
        }
        if(flag)
        {
            System.out.print("\033[H\033[2J");
            System.out.println("User Name is Doesn't Exists");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
            return;
        }
        System.out.print("\nEnter Pin: ");
        int pin=Login.sc.nextInt();
        Login.sc.nextLine();
        if(pin==User.uList.get(j).pin)
        {
            logged(j);
            return;
        }
        else
        {
            System.out.print("\033[H\033[2J");
            System.out.println("Wrong Pin");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
            return;
        }
    }

    public void logged(int user) {
        t:while (true)
        {
            System.out.print("\033[H\033[2J");
            System.out.print("""
                            ~~~~~~~~~~WELCOME-USER~~~~~~~~~~

                            1. Search / Buy Product
                            2. Cart
                            3. Ordered Products
                            4. Returned Products
                            5. Wallet Amount
                            6. Change Pin
                            7. Exit

                            Enter Your Choice:""");
            int choice=Login.sc.nextInt();
            Login.sc.nextLine();
            switch (choice)
            {
                case 1:
                    search(user);
                    break;
                case 2:
                    cart(user);
                    break;
                case 3:
                    orderedList(user);
                    break;
                case 4:
                    returnedList(user);
                    break;
                case 5:
                    walletAmount(user);
                    break;
                case 6:
                    pinChange(user);
                    break;
                case 7:
                    System.out.print("\033[H\033[2J");
                    break t;
                default:
                    System.out.print("\033[H\033[2J");
                    System.out.print("Invalid Input\nPress Enter to Continue...");
                    Login.sc.nextLine();
                    break;
            }
        }
    }

    public void search(int user) {
        while(true)
        {
            System.out.print("\033[H\033[2J");
            System.out.println("~~~~~~~~~~SEARCH~~~~~~~~~~\n");
            System.out.print("Enter Product Name / Enter (0) to Exit: ");
            String item=Login.sc.nextLine();
            if(item.equals("0"))
                return;
            boolean flag=true;
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %s | %s | %10s | %5s | %5s | %5s | %13s | %20s | %13s | %5s    |\n", "SI.NO.", "Seller ID","Product Name", "Quantity", "Warranty", "Return", "Product-Rating", "Product-Description", "No of Buyers", "Price");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
            int j=0;
            for(Product i:Product.plist)
            {
                if(i.productName.equals(item))
                {
                    flag=false;
                    System.out.printf("| %5s  | %10s  | %10s | %10s | %8s | %6s | %13s | %20s | %10s | %10s |\n\n", ++j, i.sellerCode, i.productName, i.quantity, i.warranty, i.return_, i.productRating, i.productDescription, i.noOfBuy, i.price);
                }
            }
            if(flag)
            {
                System.out.print("\033[H\033[2J");
                System.out.println("\nProduct is not available.");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
            }
            else
            {
                System.out.print("Enter Your Choice / Enter (0) to Exit: ");
                int choice=Login.sc.nextInt();
                if(choice==0)
                    return;
                selected(user, choice, item);
            }
        }
    }

    public void selected(int user, int choice, String item) {
        int k=0;
        for(Product i:Product.plist)
            {
                if(i.productName.equals(item) && ++k==choice)
                {
                    System.out.print("\033[H\033[2J");
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------");
                    System.out.printf("| %s | %10s | %5s | %5s | %5s | %13s | %20s | %13s | %5s    |\n", "Seller ID","Product Name", "Quantity", "Warranty", "Return", "Product-Rating", "Product-Description", "No of Buyers", "Price");
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------");
                    System.out.printf("| %9s   | %10s | %10s | %8s | %6s | %13s | %20s | %10s | %10s |\n\n", i.sellerCode, i.productName, i.quantity, i.warranty, i.return_, i.productRating, i.productDescription, i.noOfBuy, i.price);
                    System.out.print("\nBuy the Product(1) / Add to Cart(2) / Cancel(0): ");
                    int c=Login.sc.nextInt();
                    Login.sc.nextLine();
                    if(c==1)
                    {
                        if(i.price<=uList.get(user).amount)
                        {
                            if(i.quantity>0)
                            {
                                uList.get(user).buy.add(i.sellerCode+","+i.productName+","+i.return_);
                                uList.get(user).amount-=i.price;
                                i.noOfBuy++;
                                i.quantity--;
                                return_.add(i.sellerCode+","+i.productName);
                                System.out.print("\033[H\033[2J");
                                System.out.println("Product was Ordered");
                                System.out.print("Press Enter to Continue...");
                                Login.sc.nextLine();
                                return;
                            }
                            else
                            {
                                System.out.print("\033[H\033[2J");
                                System.out.println("\nProduct is Out of Stock\n");
                                System.out.print("Press Enter to Continue...");
                                Login.sc.nextLine();
                                return;
                            }
                        }
                        else
                        {
                            System.out.print("\033[H\033[2J");
                            System.out.println("Wallet Amount is low");
                            System.out.print("\nAdd Amount to Wallet (1)/ Exit (0): ");
                            if(Login.sc.nextInt()==1)
                            {
                                walletAmount(user);
                            }
                            else
                            {
                                logged(user);
                            }
                        }
                    }
                    else if(c==2)
                    {
                        System.out.print("\033[H\033[2J");
                        uList.get(user).cart.add(i.sellerCode+","+i.productName);
                        System.out.println("\nProduct was Added to Cart\n");
                        System.out.print("Press Enter to Continue...");
                        Login.sc.nextLine();
                        search(user);
                    }
                    else
                    {
                        search(user);
                    }
                }
            }
    }

    public void cart(int user) {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~CART~~~~~~~~~~\n");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %s | %s | %10s | %5s | %5s | %5s | %13s | %20s | %13s | %5s    |\n", "SI.NO.", "Seller ID","Product Name", "Quantity", "Warranty", "Return", "Product-Rating", "Product-Description", "No of Buyers", "Price");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
        String[] arr;
        int K=0;
        for(int i=1;i<uList.get(user).cart.size();i++)
        {
            arr=uList.get(user).cart.get(i).split(",");
            for (Product j : Product.plist)
            {
                if(j.productName.equals(arr[1]) && j.sellerCode==Integer.parseInt(arr[0]))
                {
                    System.out.printf("| %5s     | %5s  | %10s | %10s | %8s | %6s | %13s | %20s | %10s | %10s |\n\n", ++K, j.sellerCode, j.productName, j.quantity, j.warranty, j.return_, j.productRating, j.productDescription, j.noOfBuy, j.price);
                }
            }
        }
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
    }

    public void orderedList(int user) {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~ORDERED-PRODUCTS~~~~~~~~~~\n");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %s | %s | %10s | %5s | %5s | %5s | %13s | %20s | %13s | %5s    |\n", "SI.NO.", "Seller ID","Product Name", "Quantity", "Warranty", "Return", "Product-Rating", "Product-Description", "No of Buyers", "Price");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
        String[] arr;
        ArrayList<Integer> code=new ArrayList<>();
        ArrayList<String> name=new ArrayList<>();
        int K=0;
        for(int i=1;i<uList.get(user).buy.size();i++)
        {
            arr=uList.get(user).buy.get(i).split(",");
            for (Product j : Product.plist)
            {
                if(j.productName.equals(arr[1]) && j.sellerCode==Integer.parseInt(arr[0]))
                {
                    System.out.printf("| %5s     | %5s  | %10s | %10s | %8s | %6s | %13s | %20s | %10s | %10s |\n\n", ++K, j.sellerCode, j.productName, j.quantity, j.warranty, j.return_, j.productRating, j.productDescription, j.noOfBuy, j.price);
                    code.add(j.sellerCode);
                    name.add(j.productName);
                }
            }
        }
        System.out.print("\nSelect the Product / Enter (0) to Exit: ");
        int pro=Login.sc.nextInt();
        if(pro==0)
            return;
        System.out.print("\n\nReturn the Product(1) / Product Rating(2) / Cancel(0): ");
        int c=Login.sc.nextInt();
        Login.sc.nextLine();
        if(c==1)
        {
            for(int l=1;l<uList.get(user).buy.size();l++)
            {
                arr=uList.get(user).buy.get(l).split(",");
                for(int i=0;i<code.size();i++)
                {
                    for(Product j:Product.plist)
                    {
                        if(j.sellerCode==code.get(pro-1) && j.productName.equals(name.get(pro-1)) && j.productName.equals(arr[1]) && j.sellerCode==Integer.parseInt(arr[0]))
                        {
                            if(j.return_.equals("Y"))
                            {
                                System.out.print("\033[H\033[2J");
                                uList.get(user).buy.remove(l);
                                uList.get(user).return_.add(j.productName+","+j.sellerCode);
                                j.quantity++;
                                j.noOfBuy--;
                                uList.get(user).amount+=j.price;
                                System.out.println("\nProduct was Returned to Seller\n");
                                System.out.print("Press Enter to Continue...");
                                Login.sc.nextLine();
                                logged(user);
                            }
                            else
                            {
                                System.out.println("\nEntered Product is not available for Return\n");
                                System.out.print("Press Enter to Continue...");
                                Login.sc.nextLine();
                                logged(user);
                            }
                        }
                    }
                }
            }
        }
        else if(c==2)
        {
            for(int i=0;i<code.size();i++)
            {
                for(Product j:Product.plist)
                {
                    if(j.sellerCode==code.get(pro-1) && j.productName.equals(name.get(pro-1)))
                    {
                        if(!uList.get(user).review)
                        {
                            System.out.print("Enter the Product Rating (1-5): ");
                            int rate=Login.sc.nextInt();
                            Login.sc.nextLine();
                            uList.get(user).review=true;
                            j.productRating=(j.productRating+rate)/j.noOfBuy;
                            System.out.print("\nPress Enter to Continue...");
                            Login.sc.nextLine();
                            return;
                        }
                        else
                        {
                            System.out.println("Product Rating is already given");
                            System.out.print("\nPress Enter to Continue...");
                            Login.sc.nextLine();
                            return;
                        }
                    }
                }
            }
        }
        else
        {
            return;
        }
        Login.sc.nextLine();
    }

    public void returnedList(int user) {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~RETURNED-PRODUCTS~~~~~~~~~~\n");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %s | %10s | %5s | %5s | %5s | %13s | %20s | %13s | %5s    |\n", "Seller ID","Product Name", "Quantity", "Warranty", "Return", "Product-Rating", "Product-Description", "No of Buyers", "Price");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        String[] arr;
        for(int i=1;i<uList.get(user).return_.size();i++)
        {
            arr=uList.get(user).return_.get(i).split(",");
            for (Product j : Product.plist)
            {
                if(j.productName.equals(arr[0]) && j.sellerCode==Integer.parseInt(arr[1]))
                {
                    System.out.printf("| %5s     | %10s | %10s | %8s | %6s | %13s | %20s | %10s | %10s   |\n\n", j.sellerCode, j.productName, j.quantity, j.warranty, j.return_, j.productRating, j.productRating, j.noOfBuy, j.price);
                }
            }
        }
        System.out.print("\nPress Enter to Continue...");
        Login.sc.nextLine();
    }

    public void walletAmount(int user) {
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
                    uList.get(user).amount+=Login.sc.nextInt();
                    Login.sc.nextLine();
                    System.out.print("\nAmount Added Successfully\nPress Enter to Continue...");
                    Login.sc.nextLine();
                    break;
                case 2:
                    System.out.print("\033[H\033[2J");
                    System.out.println("~~~~~~~~~~WALLET-AMOUNT~~~~~~~~~~\n");
                    System.out.println("Wallet Balance: "+uList.get(user).amount);
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

    public void pinChange(int sindex) {
        System.out.print("\033[H\033[2J");
        System.out.print("Enter new Pin: ");
        int pin=Login.sc.nextInt();
        System.out.print("\nEnter Confirm pin: ");
        int pin1=Login.sc.nextInt();
        Login.sc.nextLine();
        if(pin==pin1)
        {
            uList.get(sindex).pin=pin;
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
