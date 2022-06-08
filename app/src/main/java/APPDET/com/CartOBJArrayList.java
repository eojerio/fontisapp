package APPDET.com;

import java.util.ArrayList;

public class CartOBJArrayList {

    private ArrayList<String> cart_idAdmin;
    private ArrayList<String> cart_userIDAdmin;

    public void setCart_idAdmin(ArrayList<String> cart_idAdmin) {
        this.cart_idAdmin = cart_idAdmin;
    }

    public ArrayList<String> getCartID(){
        return cart_idAdmin;
    }

    public void setCart_userIDAdmin(ArrayList<String> cart_userIDAdmin) {
        this.cart_userIDAdmin = cart_userIDAdmin;
    }

    public ArrayList<String> getCart_userIDAdmin() {
        return cart_userIDAdmin;
    }

}
