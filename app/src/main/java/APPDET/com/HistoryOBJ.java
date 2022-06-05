package APPDET.com;

public class HistoryOBJ {
    private String date;
    private String amount;
    private String total;
    private String imgURL;

    public HistoryOBJ(String date, String amount, String total, String imgURL){
        this.date = date;
        this.amount = amount;
        this.total = total;
        this.imgURL = imgURL;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}

