
package facilities.samir.andrew.facilities.models.UnittDetails;

import java.util.List;

public class UnitDetailsModel {


    unitDetails unitDetails;
    List<UnitPayment> unitPayment;

    /**
     * No args constructor for use in serialization
     */
    public UnitDetailsModel() {
    }

    public unitDetails getUnitDetails() {
        return unitDetails;
    }

    public void setUnitDetails(unitDetails unitDetails) {
        this.unitDetails = unitDetails;
    }

    public List<UnitPayment> getUnitPayment() {
        return unitPayment;
    }

    public void setUnitPayment(List<UnitPayment> unitPayment) {
        this.unitPayment = unitPayment;
    }
}
