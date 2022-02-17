package AppDev.LMS;

import java.util.ArrayList;
import java.util.List;

public class Book {
    String bookName;
    String bookAuthor;
    String category;
    int bookId;
    int adminId;
    int quantity;
    int cost;
    int cquantity;

    Book(String bookName, String bookAuthor, String category, int bookId, int authorId, int cost, int quantity)
    {
        this.bookName=bookName;
        this.bookAuthor=bookAuthor;
        this.category=category;
        this.bookId=bookId;
        this.adminId=authorId;
        this.quantity=quantity;
        this.cost=cost;
        this.cquantity=quantity;
    }

    Book(){}

    static int id=100;
    static List<Book> bList=new ArrayList<>();

    public void books(int aid) {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~NEW-BOOK~~~~~~~~~~\n");
        System.out.print("Enter Book Name: ");
        String bookName=Login.sc.nextLine();
        for(Book i:bList)
        {
            if(i.bookName.equalsIgnoreCase(bookName))
            {
                System.out.print("\033[H\033[2J");
                System.out.println("Entered Book is Already Exists");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
                return;
            }
        }
        System.out.print("\nEnter Author Name: ");
        String bookAuthor=Login.sc.nextLine();
        System.out.print("\nEnter Category of Book: ");
        String category=Login.sc.nextLine();
        System.out.print("\nEnter No. of Books: ");
        int quantity=Login.sc.nextInt();
        System.out.print("\nEnter Cost of Book: ");
        int cost=Login.sc.nextInt();
        Login.sc.nextLine();
        bList.add(new Book(bookName, bookAuthor, category, ++id, aid, cost, quantity));
        System.out.print("\033[H\033[2J");
        System.out.println("ISBN of Book: "+id);
        System.out.println("\nBook has been Added");
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
        Admin.log.add("addBook,"+bookName+","+id+","+bookAuthor+","+category+","+quantity+","+quantity+","+aid);
    }

    public void edit(int aid) {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~EDIT-BOOK~~~~~~~~~~\n");
        System.out.print("Enter Book Name / ISBN of Book: ");
        String bookName=Login.sc.nextLine();
        int j=0;
        for(Book i:bList)
        {
            if(i.bookName.equalsIgnoreCase(bookName) || bookName.equals(i.adminId+""))
            {
                boolean flag=true;
                while(flag)
                {
                    System.out.print("""
                                    \n1. Add
                                    2. Remove

                                    Enter the Choice: """);
                    int choice=Login.sc.nextInt();
                    Login.sc.nextLine();
                    switch(choice)
                    {
                        case 1:
                            System.out.print("\nEnter No. of Books to be Add: ");
                            int a=Login.sc.nextInt();
                            bList.get(j).quantity+=a;
                            bList.get(j).cquantity+=a;
                            Admin.log.add("editBookAdd"+","+bList.get(j).bookName+","+bList.get(j).bookId+","+bList.get(j).quantity+","+bList.get(j).cquantity+","+a+","+aid);
                            break;
                        case 2:
                            System.out.print("\nEnter No. of Books to be Remove: ");
                            int b=Login.sc.nextInt();
                            if(b>bList.get(j).quantity)
                            {
                                System.out.println("No. of Books must be Greater than No. of Books Remove");
                                System.out.print("Press Enter to Continue...");
                                Login.sc.nextLine();
                                Login.sc.nextLine();
                                return;
                            }
                            bList.get(j).quantity-=b;
                            bList.get(j).cquantity-=b;
                            Admin.log.add("editBookRemove"+","+bList.get(j).bookName+","+bList.get(j).bookId+","+bList.get(j).quantity+","+bList.get(j).cquantity+","+b+","+aid);
                            break;
                        default:
                            System.out.println("Invalid Input");
                            System.out.print("Press Enter to Continue...");
                            Login.sc.nextLine();
                    }
                    System.out.print("\033[H\033[2J");
                    System.out.println("Book has been Edited Successfully");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    Login.sc.nextLine();
                    return;
                }
            }
            j++;
        }
        System.out.print("\033[H\033[2J");
        System.out.println("Entered Book is Not Available");
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
    }

    public void remove(int aid) {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~REMOVE-BOOK~~~~~~~~~~\n");
        System.out.print("Enter Book Name / ISBN of Book: ");
        String bookName=Login.sc.nextLine();
        int j=0;
        for(Book i:bList)
        {
            if(i.bookName.equalsIgnoreCase(bookName) || bookName.equals(i.bookId+""))
            {
                System.out.print("\033[H\033[2J");
                System.out.println("Entered Book has been Removed");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
                bList.remove(j);
                Admin.log.add("removeBook,"+bookName+","+i.bookId+","+aid);
                return;
            }
            j++;
        }
        System.out.print("\033[H\033[2J");
        System.out.println("Entered Book is not Available");
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
    }

    public void search() {
        t:while(true)
        {
            System.out.print("\033[H\033[2J");
            System.out.print("""
                            +==SEARCH-BY===============+
                            |                          |
                            | 1. Book Name             |
                            | 2. ISBN of Book          |
                            | 3. Book Author           |
                            | 4. Category              |
                            | 5. Exit                  |
                            |                          |
                            +==========================+

                            Enter the Choice: """);
            int choice=Login.sc.nextInt();
            Login.sc.nextLine();
            boolean flag=true;
            switch (choice)
            {
                case 1:
                    System.out.print("\033[H\033[2J");
                    System.out.print("Enter Book Name: ");
                    String bookName=Login.sc.nextLine();
                    for(Book i:bList)
                    {
                        if(i.bookName.equalsIgnoreCase(bookName))
                        {
                            flag=false;
                            System.out.println("\n\nISBN of Book: "+i.bookId+"\nBook Author: "+i.bookAuthor+"\nCategory: "+i.category+"\nBook Quantity: "+i.quantity+"\nCurrent Book Quantity: "+i.cquantity+"\nCost: "+i.cost);
                        }
                    }
                    if(flag)
                    {
                        System.out.println("\nEntered Book is not Available");
                        System.out.print("Press Enter to Continue...");
                        Login.sc.nextLine();
                        continue;
                    }
                    System.out.print("\nPress Enter to Continue...");
                    Login.sc.nextLine();
                    break;
                case 2:
                    System.out.print("\033[H\033[2J");
                    System.out.print("Enter ISBN of Book: ");
                    String bookId=Login.sc.nextLine();
                    for(Book i:bList)
                    {
                        if(bookId.equals(i.bookId+""))
                        {
                            flag=false;
                            System.out.println("\n\nBook Name: "+i.bookName+"\nBook Author: "+i.bookAuthor+"\nCategory: "+i.category+"\nBook Quantity: "+i.quantity+"\nCurrent Book Quantity: "+i.cquantity+"\nCost: "+i.cost);
                        }
                    }
                    if(flag)
                    {
                        System.out.println("\nEntered Book is not Available");
                        System.out.print("Press Enter to Continue...");
                        Login.sc.nextLine();
                        continue;
                    }
                    System.out.print("\nPress Enter to Continue...");
                    Login.sc.nextLine();
                    break;
                case 3:
                    System.out.print("\033[H\033[2J");
                    System.out.print("Enter Book Author: ");
                    String authorName=Login.sc.nextLine();
                    for(Book i:bList)
                    {
                        if(i.bookAuthor.equalsIgnoreCase(authorName))
                        {
                            flag=false;
                            System.out.println("\n\nISBN of Book: "+i.bookId+"\nBook Name: "+i.bookName+"\nCategory: "+i.category+"\nBook Quantity: "+i.quantity+"\nCurrent Book Quantity: "+i.cquantity+"\nCost: "+i.cost);
                        }
                    }
                    if(flag)
                    {
                        System.out.println("\nEntered Book is not Available");
                        System.out.print("Press Enter to Continue...");
                        Login.sc.nextLine();
                        continue;
                    }
                    System.out.print("\nPress Enter to Continue...");
                    Login.sc.nextLine();
                    break;
                case 4:
                    System.out.print("\033[H\033[2J");
                    System.out.print("Enter Category: ");
                    String category=Login.sc.nextLine();
                    for(Book i:bList)
                    {
                        if(i.category.equalsIgnoreCase(category))
                        {
                            flag=false;
                            System.out.println("\n\nISBN of Book: "+i.bookId+"\nBook Name: "+i.bookName+"\nBook Author: "+i.bookAuthor+"\nBook Quantity: "+i.quantity+"\nCurrent Book Quantity: "+i.cquantity+"\nCost: "+i.cost);
                        }
                    }
                    if(flag)
                    {
                        System.out.println("\nEntered Book is not Available");
                        System.out.print("Press Enter to Continue...");
                        Login.sc.nextLine();
                        continue;
                    }
                    System.out.print("\nPress Enter to Continue...");
                    Login.sc.nextLine();
                    break;
                case 5:
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    break t;
                default:
                    System.out.print("\033[H\033[2J");
                    System.out.println("\nInvalid Input");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
            }
        }
    }

    public void bookList() {
        t:while(true)
        {
            System.out.print("\033[H\033[2J");
            System.out.print("""
                            +==BOOK-LIST-BY============+
                            |                          |
                            | 1. Book Name             |
                            | 2. ISBN of Book          |
                            | 3. Exit                  |
                            |                          |
                            +==========================+

                            Enter the Choice: """);
            int choice=Login.sc.nextInt();
            Login.sc.nextLine();
            System.out.print("\033[H\033[2J");
            switch(choice)
            {
                case 1:
                    System.out.println("~~~~~~~~~~BOOK-LIST~~~~~~~~~~\n");
                    System.out.println("+-----------------------------------------------------------------------------------------------------------------------------------+");
                    System.out.printf("| %s %15s %10s %15s %20s %20s %25s %10s |\n","SI. No.","Book Name","ISBN","Author","Category","Quantity","Current Quantity","Cost");
                    System.out.println("+-----------------------------------------------------------------------------------------------------------------------------------+");
                    int m=0;
                    for(int k=65, p=97; k<91 || p<123; k++, p++)
                    {
                        for(Book i:bList)
                        {
                            if(i.bookName.charAt(0)!=(char)k && i.bookName.charAt(0)!=(char)p)
                                continue;
                            System.out.printf("%5s%20s%10s%20s%20s%15s%25s%15s\n",++m,i.bookName,i.bookId,i.bookAuthor,i.category,i.quantity,i.cquantity,i.cost);
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
                        }
                    }
                    System.out.print("\nPress Enter to Continue...");
                    Login.sc.nextLine();
                    break;
                case 2:
                    System.out.println("~~~~~~~~~~BOOK-LIST~~~~~~~~~~\n");
                    System.out.println("+-----------------------------------------------------------------------------------------------------------------------------------+");
                    System.out.printf("| %s %15s %10s %15s %20s %20s %25s %10s |\n","SI. No.","Book Name","ISBN","Author","Category","Quantity","Current Quantity","Cost");
                    System.out.println("+-----------------------------------------------------------------------------------------------------------------------------------+");
                    int j=0;
                    for(Book i:bList)
                    {
                        System.out.printf("%5s%20s%10s%20s%20s%15s%25s%15s\n",++j,i.bookName,i.bookId,i.bookAuthor,i.category,i.quantity,i.cquantity,i.cost);
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
                    }
                    System.out.print("\nPress Enter to Continue...");
                    Login.sc.nextLine();
                    break;
                case 3:
                    System.out.print("\nPress Enter to Continue...");
                    Login.sc.nextLine();
                    break t;
                default:
                    System.out.println("\nInvalid Input");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
            }
        }
    }

}
