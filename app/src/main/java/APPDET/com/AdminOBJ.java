package APPDET.com;

public class AdminOBJ {
    private String price;
    private String user;
    private String date;
    private String amountAdmin;
    private String address;

    public AdminOBJ(String price, String user, String date, String amount, String address){
        this.price = price;
        this.user = user;
        this.date = date;
        this.amountAdmin = amount;
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public String getUser() {
        return user;
    }

    public String getDate() {
        return date;
    }

    public String getAmountAdmin() {
        return amountAdmin;
    }

    public String getAddress() {
        return address;
    }
}
