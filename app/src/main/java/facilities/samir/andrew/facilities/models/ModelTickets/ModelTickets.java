package facilities.samir.andrew.facilities.models.ModelTickets;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelTickets {


    @SerializedName("closedTickets")
    private List<Ticket> closedtickets;

    @SerializedName("openTickets")
    private List<Ticket> openTickets;

    public List<Ticket> getClosedtickets() {
        return closedtickets;
    }

    public void setClosedtickets(List<Ticket> closedtickets) {
        this.closedtickets = closedtickets;
    }

    public List<Ticket> getOpenTickets() {
        return openTickets;
    }

    public void setOpenTickets(List<Ticket> openTickets) {
        this.openTickets = openTickets;
    }
}
