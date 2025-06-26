package au.com.telstra.simcardactivator.foundation;

public class SimCard {
    private String iccid;

    public SimCard() {}

    public SimCard(String iccid) {
        this.iccid = iccid;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }
}
