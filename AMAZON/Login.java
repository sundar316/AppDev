public class Login {
       static Scanner sc=new Scanner(System.in);
       static Admin ad;
       static Seller sell;
       static User us;
       static Product pr;
       public static void main(String[] args) {
              while(true)
              {
                     System.out.print("\033[H\033[2J");
                     System.out.print("""
                                          ~~~~~~~~~~AMAZON~~~~~~~~~~

                                          1. Admin
                                          2. Seller
                                          3. User
                                          4. Exit

                                          Enter Your Choice:""");
                     int choice=sc.nextInt();
                     sc.nextLine();
                     switch (choice) {
                            case 1:
                                   ad=new Admin();
                                   ad.admin();
                                   break;
                            case 2:
                                   sell=new Seller();
                                   sell.seller();
                                   break;
                            case 3:
                                   us=new User();
                                   us.user();
                                   break;
                            case 4:
                                   System.out.print("\033[H\033[2J");
                                   System.out.println("!!! Thank You !!!");
                                   System.exit(0);
                            default:
                                   System.out.print("\033[H\033[2J");
                                   System.out.println("Invalid Input\nPress Enter to Continue...");
                                   sc.nextLine();
                                   break;
                     }
              }
       }
}
