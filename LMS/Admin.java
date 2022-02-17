package AppDev.LMS;

import java.util.ArrayList;
import java.util.List;

public class Admin {
    static User us;
    static Admin ad;
    static Book bo;
    static Transactions tr;

    String userName;
    int pin;
    int id;

    static int ids=501;

    Admin(String userName, int pin, int id){
        this.userName=userName;
        this.pin=pin;
        this.id=id;
    }

    static List<Admin> aList=new ArrayList<>();
    static {
        aList.add(new Admin("Rajan", 1234, 609));
    }
    static List<String> log=new ArrayList<>();

    Admin(){}

    public void admin() {
        aList.add(new Admin("Admin", 1234, 101));
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~ADMIN~~~~~~~~~~\n");
        System.out.print("Enter User Name: ");
        String uname=Login.sc.nextLine();
        System.out.print("\nEnter Pin: ");
        int pin_=Login.sc.nextInt();
        Login.sc.nextLine();
        boolean flag=true;
        for(Admin i:aList)
        {
            if(i.userName.equals(uname) && pin_==i.pin)
            {
                flag=false;
                logged(i.id);
                break;
            }
        }
        if(flag)
        {
            System.out.println("\nWrong Username / Pin");
            System.out.print("Press Enter to Continue...");
            Login.sc.nextLine();
        }
    }

    public void logged(int id) {
        t:while (true)
        {
            System.out.print("\033[H\033[2J");
            System.out.print("""
                            +==WELCOME-ADMIN============+
                            |                           |
                            | 1.  Issue Book            |
                            | 2.  Return Book           |
                            | 3.  Add User              |
                            | 4.  Add Admin             |
                            | 5.  Add Book              |
                            | 6.  Edit Book             |
                            | 7.  Remove Book           |
                            | 8.  Search Book           |
                            | 9.  Book List             |
                            | 10. Report                |
                            | 11. Book Lost             |
                            | 12. Membership Card Lost  |
                            | 13. Log                   |
                            | 14. Exit                  |
                            |                           |
                            +===========================+

                            Enter the Choice: """);
            int choice=Login.sc.nextInt();
            Login.sc.nextLine();
            switch (choice)
            {
                case 1:
                    tr=new Transactions();
                    tr.issue(id);
                    break;
                case 2:
                    tr=new Transactions();
                    tr.returns(id);
                    break;
                case 3:
                    us=new User();
                    us.users(id);
                    break;
                case 4:
                    admins(id);
                    break;
                case 5:
                    bo=new Book();
                    bo.books(id);
                    break;
                case 6:
                    bo=new Book();
                    bo.edit(id);
                    break;
                case 7:
                    bo=new Book();
                    bo.remove(id);
                    break;
                case 8:
                    bo=new Book();
                    bo.search();
                    break;
                case 9:
                    bo=new Book();
                    bo.bookList();
                    break;
                case 10:
                    tr=new Transactions();
                    tr.report();
                    break;
                case 11:
                    tr=new Transactions();
                    tr.lost();
                    break;
                case 12:
                    System.out.print("\033[H\033[2J");
                    System.out.println("~~~~~~~~~~MEMBERSHIP-CARD-LOST~~~~~~~~~~\n");
                    System.out.print("Enter User Name: ");
                    String uname=Login.sc.nextLine();
                    boolean flag=true;
                    for(User i:User.uList)
                    {
                        if(i.userName.equals(uname))
                        {
                            flag=false;
                            if(!i.card)
                            {
                                System.out.println("\nNew Card has been dispatched");
                                System.out.print("Press Enter to Continue...");
                                Login.sc.nextLine();
                                i.card=true;
                                break;
                            }
                            System.out.println("\nUser was not Applied for New Card");
                            System.out.print("Press Enter to Continue...");
                            Login.sc.nextLine();
                            i.card=true;
                            break;
                        }
                    }
                    if(flag)
                    {
                        System.out.println("\nUser Name is not Exist");
                        System.out.print("Press Enter to Continue...");
                        Login.sc.nextLine();
                    }
                    break;
                case 13:
                    log();
                    break;
                case 14:
                    System.out.print("\033[H\033[2J");
                    System.out.println("Signing Out");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    break t;
                default:
                    System.out.print("\033[H\033[2J");
                    System.out.println("Invalid Input");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
            }
        }
    }

    private void admins(int aid) {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~NEW-ADMIN~~~~~~~~~~\n");
        System.out.print("Enter Admin Name: ");
        String userName=Login.sc.nextLine();
        for(Admin i:aList)
        {
            if(i.userName.equalsIgnoreCase(userName))
            {
                System.out.print("\033[H\033[2J");
                System.out.println("Admin Name Already Exists");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
                return;
            }
        }
        System.out.print("\nEnter Pin: ");
        int pin=Login.sc.nextInt();
        Login.sc.nextLine();
        aList.add(new Admin(userName, pin, ++ids));
        System.out.print("\033[H\033[2J");
        System.out.println("New Admin has been Created");
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
        log.add("addAdmin,"+userName+","+aid);
    }

    public void log() {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~LOG~~~~~~~~~~\n");
        for(String i:log)
        {
            String arr[]=i.split(",");
            if(arr[0].equals("addAdmin"))
                System.out.println("Added new Admin\nAdmin Name: "+arr[1]+"\nAdded By: "+arr[2]+"\n");
            if(arr[0].equals("addUser"))
                System.out.println("Added new User\nUser Name: "+arr[1]+"\nAdded By: "+arr[2]+"\n");
            if(arr[0].equals("addBook"))
                System.out.println("Added new Book\nBook Name: "+arr[1]+"\nISBN of Book: "+arr[2]+"\nAuthor Name: "+arr[3]+"\nCategory: "+arr[4]+"\nQuantity: "+arr[5]+"\nCurrent Quantity: "+arr[6]+"\nAdded By: "+arr[7]+"\n");
            if(arr[0].equals("editBookAdd"))
                System.out.println("Edited Book\nBook Name: "+arr[1]+"\nISBN of Book: "+arr[2]+"\nQuantity: "+arr[3]+"\nCurrent Quantity: "+arr[4]+"\nNo. of Books Added: "+arr[5]+"\nEdited By: "+arr[6]+"\n");
            if(arr[0].equals("editBookRemove"))
                System.out.println("Edited Book\nBook Name: "+arr[1]+"\nISBN of Book: "+arr[2]+"\nQuantity: "+arr[3]+"\nCurrent Quantity: "+arr[4]+"\nNo. of Books Removed: "+arr[5]+"\nEdited By: "+arr[6]+"\n");
            if(arr[0].equals("removeBook"))
                System.out.println("Removed Book\nBook Name: "+arr[1]+"\nRemoved By: "+arr[2]+"\n");
            if(arr[0].equals("issueBook"))
                System.out.println("Issued Book\nUser ID: "+arr[2]+"\nISBN of Book: "+arr[1]+"\nIssued By: "+arr[3]+"\nReturn Days: "+arr[4]);
        }
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
    }

}
