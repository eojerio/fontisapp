package APPDET.com;

public class HomeOBJ {
    private String price;
    private String prodName;
    private String prodDesc;
    private String imgURL;

    //home objects
    public HomeOBJ(String price, String prodName, String prodDesc, String imgURL){
        this.price = price;
        this.prodName = prodName;
        this.prodDesc = prodDesc;
        this.imgURL = imgURL;
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
