package AppDev.AMAZON;

import java.util.ArrayList;
import java.util.List;

public class Product {
    String productName;
    int quantity;
    int sellerCode;
    String productDescription;
    int productRating;
    int price;
    int warranty;
    String return_;
    int noOfBuy;

    static List<Product> plist=new ArrayList<>();

    Product(String productName, int quantity, int sellerCode, String productDescription, int productRating, int price, int warranty, String return_, int noOfBuy){
        this.productName=productName;
        this.quantity=quantity;
        this.sellerCode=sellerCode;
        this.productDescription=productDescription;
        this.productRating=productRating;
        this.price=price;
        this.warranty=warranty;
        this.return_=return_;
        this.noOfBuy=noOfBuy;
    }

    Product(){}
}