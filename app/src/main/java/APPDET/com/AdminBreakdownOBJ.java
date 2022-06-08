package APPDET.com;

public class AdminBreakdownOBJ {
    private String price;
    private String amount;
    private String product_name;
    private String desc;
    private String imgURL;

    public AdminBreakdownOBJ(String price, String amount, String product_name, String desc, String imgURL){
        this.price = price;
        this.amount = amount;
        this.product_name = product_name;
        this.desc = desc;
        this.imgURL = imgURL;
    }

    public String getPrice() {
        return price;
    }

    public String getAmount() {
        return amount;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getDesc() {
        return desc;
    }

    public String getProduct_name() {
        return product_name;
    }
}
