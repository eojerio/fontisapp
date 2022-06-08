package APPDET.com;

import java.util.ArrayList;

public class AdminOBJArrayList {

    private ArrayList<String> breakdown_idCartAdmin;
    private ArrayList<String> breakdown_historyIDAdmin;
    private ArrayList<String> breakdown_userIDAdmin;
    private Integer position;


    public void setBreakdown_historyIDAdmin(ArrayList<String> breakdown_historyIDAdmin) {
        this.breakdown_historyIDAdmin = breakdown_historyIDAdmin;
    }

    public void setBreakdown_idCartAdmin(ArrayList<String> breakdown_idCartAdmin) {
        this.breakdown_idCartAdmin = breakdown_idCartAdmin;
    }

    public void setBreakdown_userIDAdmin(ArrayList<String> breakdown_userIDAdmin) {
        this.breakdown_userIDAdmin = breakdown_userIDAdmin;
    }

    public ArrayList<String> getBreakdown_historyIDAdmin() {
        return breakdown_historyIDAdmin;
    }

    public ArrayList<String> getBreakdown_idCartAdmin() {
        return breakdown_idCartAdmin;
    }

    public ArrayList<String> getBreakdown_userIDAdmin() {
        return breakdown_userIDAdmin;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getPosition() {
        return position;
    }
}
