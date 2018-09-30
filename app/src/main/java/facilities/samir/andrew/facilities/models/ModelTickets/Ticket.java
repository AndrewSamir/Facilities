package facilities.samir.andrew.facilities.models.ModelTickets;

import com.google.gson.annotations.SerializedName;

public class Ticket {
    @SerializedName("unit")
    private String unit;
    @SerializedName("ticketNumber")
    private String ticketnumber;
    @SerializedName("status")
    private String status;
    @SerializedName("statusId")
    private int statusid;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTicketnumber() {
        return ticketnumber;
    }

    public void setTicketnumber(String ticketnumber) {
        this.ticketnumber = ticketnumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStatusid() {
        return statusid;
    }

    public void setStatusid(int statusid) {
        this.statusid = statusid;
    }
}
