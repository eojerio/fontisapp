package APPDET.com;

public class HomeOBJ {
    private String price;
    private String prodName;
    private String prodDesc;
    private String imgURL;
    private int fk_id;

    //home objects
    public HomeOBJ(int fk_id, String price, String prodName, String prodDesc, String imgURL){
        this.fk_id = fk_id;
        this.price = price;
        this.prodName = prodName;
        this.prodDesc = prodDesc;
        this.imgURL = imgURL;
    }

    public int getfk_id() {
        return fk_id;
    }

    public String getPrice() {
        return price;
    }

    public String getProdName() {
        return prodName;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public String getImgURL() {
        return imgURL;
    }
}
