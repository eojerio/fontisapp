package APPDET.com;

public class CartOBJ {
    private String tag;
    private String price;
    private String amount;
    private String product_name;
    private String desc;
    private String imgURL;
    private String total;


    public CartOBJ(String tag, String price, String amount, String product_name, String desc, String imgURL){
        this.tag = tag;
        this.price = price;
        this.amount = amount;
        this.product_name = product_name;
        this.desc = desc;
        this.imgURL = imgURL;

    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String date) {
        this.price = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProductName() {
        return product_name;
    }

    public void setProductName(String total) {
        this.product_name = total;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }
}
