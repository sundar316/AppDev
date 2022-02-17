import java.util.ArrayList;
import java.util.List;

public class Transactions {
    int bookId;
    int userId;
    int adminId;
    boolean borrow;
    boolean lost;
    int returnDate;
    int fineAmt;
    String type;
    boolean pay;

    static List<Transactions> tList=new ArrayList<>();
    static List<Transactions> fList=new ArrayList<>();

    Transactions(int userId, int fineAmt, String type, boolean pay){
        this.userId=userId;
        this.fineAmt=fineAmt;
        this.type=type;
        this.pay=pay;
    }

    Transactions(int bookId, int userId, int adminId, boolean borrow, int returnDate){
        this.bookId=bookId;
        this.userId=userId;
        this.adminId=adminId;
        this.borrow=borrow;
        this.lost=true;
        this.returnDate=returnDate;
    }

    Transactions(){}

    public void issue(int adminId) {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~ISSUE-BOOK~~~~~~~~~~\n");
        System.out.print("Enter User Name: ");
        String uname=Login.sc.nextLine();
        for(User i:User.uList)
        {
            if(i.userName.equalsIgnoreCase(uname))
            {
                if(i.amt<500)
                {
                    System.out.println("\nMinimum Wallet Amount is not maintained by user");
                    System.out.print("Press Enter to Continue...");
                    Login.sc.nextLine();
                    return;
                }
                System.out.print("\nBook Name / ISBN of Book: ");
                String book=Login.sc.nextLine();
                for(Book j:Book.bList)
                {
                    if((j.bookName.equalsIgnoreCase(book) || book.equals(j.bookId+"")) && j.cquantity>0)
                    {
                        boolean flag=true;
                        for(Transactions k:tList)
                        {
                            if((k.userId==i.id && k.bookId==j.bookId))
                            {
                                flag=false;
                                if(!k.borrow)
                                    break;
                                System.out.print("\nEnter No. of Days for Return: ");
                                int returnDate=Login.sc.nextInt();
                                Login.sc.nextLine();
                                j.cquantity--;
                                tList.add(new Transactions(j.bookId, i.id, adminId, false, returnDate));
                                Admin.log.add("issueBook"+","+j.bookId+","+i.id+","+adminId+","+returnDate);
                                System.out.println("\nBook has been issued to the user");
                                System.out.print("Press Enter to Continue...");
                                Login.sc.nextLine();
                                return;
                            }
                        }
                        if(flag)
                        {
                            System.out.print("\nEnter No. of Days for Return: ");
                            int returnDate=Login.sc.nextInt();
                            Login.sc.nextLine();
                            j.cquantity--;
                            tList.add(new Transactions(j.bookId, i.id, adminId, false, returnDate));
                            System.out.println("\nBook has been issued to the user");
                            System.out.print("Press Enter to Continue...");
                            Login.sc.nextLine();
                            return;
                        }
                        System.out.println("\nBook were Already issued to the user");
                        System.out.print("Press Enter to Continue...");
                        Login.sc.nextLine();
                        return;
                    }
                }
                System.out.println("\nEntered Book is Not Available");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
                return;
            }
        }
        System.out.println("\nUser Name Doesn't Exits");
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
    }

    public void returns(int adminId) {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~RETURN-BOOK~~~~~~~~~~\n");
        System.out.print("Enter User Name: ");
        String uname=Login.sc.nextLine();
        int p=0;
        for(User i:User.uList)
        {
            if(i.userName.equalsIgnoreCase(uname))
            {
                uname=i.id+"";
                break;
            }
            p++;
        }
        for(Transactions i:Transactions.tList)
        {
            if(uname.equals(i.userId+""))
            {
                System.out.print("\nBook Name / ISBN of Book: ");
                String book=Login.sc.nextLine();
                int amt1=0;
                int pos=0;
                for(Book j:Book.bList)
                {
                    if(j.bookName.equalsIgnoreCase(book) || book.equals(j.bookId+""))
                    {
                        book=j.bookId+"";
                        amt1=j.cost;
                        break;
                    }
                    pos++;
                }
                for(Transactions j:Transactions.tList)
                {
                    if(book.equals(j.bookId+"") && !j.borrow && j.lost)
                    {
                        if(j.returnDate>0)
                        {
                            j.borrow=true;
                            Book.bList.get(pos).cquantity++;
                            System.out.println("\nEntered Book has been Returned");
                            System.out.print("Press Enter to Continue...");
                            Login.sc.nextLine();
                            return;
                        }
                        else
                        {
                            j.borrow=true;
                            int amt=Math.abs(j.returnDate);
                            if(amt<10)
                            {
                                amt=2;
                                System.out.println("\nFine Amount: "+amt);
                                User.uList.get(p).amt-=amt;
                                fList.add(new Transactions(i.userId, amt, "Book Return Day Exceeds", true));
                                Book.bList.get(pos).cquantity++;
                            }
                            else if(amt<(amt1*80)/100)
                            {
                                amt=(amt1*80)/100;
                                System.out.println("\nFine Amount: "+amt);
                                User.uList.get(p).amt-=amt;
                                fList.add(new Transactions(i.userId, amt, "Book Return Day Exceeds", true));
                                Book.bList.get(pos).cquantity++;
                            }
                            else
                            {
                                System.out.println("\nFine Amount: "+amt);
                                User.uList.get(p).amt-=amt;
                                fList.add(new Transactions(i.userId, amt, "Book Return Day Exceeds", true));
                                Book.bList.get(pos).cquantity++;
                            }
                            System.out.println("\nEntered Book has been Returned");
                            System.out.print("Press Enter to Continue...");
                            Login.sc.nextLine();
                            return;
                        }
                    }
                }
                System.out.println("\nEntered Book is Not Issued under the User");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
                return;
            }
        }
        System.out.println("\nNo Book have been Issued under the User");
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
    }

    public void report() {
        System.out.print("\033[H\033[2J");
        int max=0, m=0;
        int never=0;
        int min=0, n=Book.bList.get(0).quantity-Book.bList.get(0).cquantity;
        for(Book i:Book.bList)
        {
            if(m<i.quantity-i.cquantity)
            {
                m=i.quantity-i.cquantity;
                max=i.bookId;
            }
            if(n>=i.quantity-i.cquantity && i.quantity-i.cquantity!=0)
            {
                min=i.bookId;
                n=i.quantity-i.cquantity;
            }
            if(i.quantity-i.cquantity==0)
            {
                never=i.bookId;
            }
        }
        for(Book i:Book.bList)
        {
            if(max==i.bookId)
            {
                System.out.println("~~~~~~~~~~HIGHEST-PURCHASED-BOOK~~~~~~~~~~\n");
                System.out.println("Book ID: "+i.bookId+"\nBook Name: "+i.bookName+"\nCategory: "+i.category+"\nQuantity: "+i.quantity+"\nCurrent Quantity: "+i.cquantity);
                break;
            }
        }
        for(Book i:Book.bList)
        {
            if(min==i.bookId)
            {
                System.out.println("\n~~~~~~~~~~LOWEST-PURCHASED-BOOK~~~~~~~~~~\n");
                System.out.println("Book ID: "+i.bookId+"\nBook Name: "+i.bookName+"\nCategory: "+i.category+"\nQuantity: "+i.quantity+"\nCurrent Quantity: "+i.cquantity);
                break;
            }
        }
        for(Book i:Book.bList)
        {
            if(never==i.bookId)
            {
                int pos=0;
                for(Book j:Book.bList)
                {
                    if(j.bookId==i.bookId)
                    {
                        break;
                    }
                    pos++;
                }
                System.out.println("\n~~~~~~~~~~NEVER-PURCHASED-BOOK~~~~~~~~~~\n");
                System.out.println("Book ID: "+i.bookId+"\nBook Name: "+Book.bList.get(pos).bookName+"\nCategory: "+Book.bList.get(pos).category+"\nQuantity: "+Book.bList.get(pos).quantity+"\nCurrent Quantity: "+Book.bList.get(pos).cquantity);
                break;
            }
        }
        System.out.print("\nPress Enter to Continue...");
        Login.sc.nextLine();
    }

    public void lost() {
        System.out.print("\033[H\033[2J");
        System.out.println("~~~~~~~~~~BOOK-LOST~~~~~~~~~~\n");
        System.out.print("Enter User Name: ");
        String uname=Login.sc.nextLine();
        int p=0;
        for(User i:User.uList)
        {
            if(i.userName.equalsIgnoreCase(uname))
            {
                uname=i.id+"";
                break;
            }
            p++;
        }
        for(Transactions i:Transactions.tList)
        {
            if(uname.equals(i.userId+""))
            {
                System.out.print("\nBook Name / ISBN of Book: ");
                String book=Login.sc.nextLine();
                int amt1=0;
                for(Book j:Book.bList)
                {
                    if(j.bookName.equalsIgnoreCase(book) || book.equals(j.bookId+""))
                    {
                        book=j.bookId+"";
                        amt1=j.cost;
                        break;
                    }
                }
                for(Transactions j:Transactions.tList)
                {
                    if(book.equals(j.bookId+"") && !j.borrow && j.lost)
                    {
                        j.lost=false;
                        System.out.println("\nFine Amount: "+amt1/2);
                        User.uList.get(p).amt-=amt1/2;
                        fList.add(new Transactions(User.uList.get(p).id, amt1/2, "Book Lost", true));
                        System.out.print("Press Enter to Continue...");
                        Login.sc.nextLine();
                        return;
                    }
                }
                System.out.println("\nEntered Book is Not Issued under the User");
                System.out.print("Press Enter to Continue...");
                Login.sc.nextLine();
                return;
            }
        }
        System.out.println("\nNo Book have been Issued under the User");
        System.out.print("Press Enter to Continue...");
        Login.sc.nextLine();
    }
}
