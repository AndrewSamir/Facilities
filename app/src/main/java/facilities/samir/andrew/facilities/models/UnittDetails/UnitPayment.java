
package facilities.samir.andrew.facilities.models.UnittDetails;

public class UnitPayment {

     String unitclientcode;
     String PaymentTypeName;
     String InstallmentAmount;
     String DueDate;
    boolean Paid;
     String PaidAmount;
     String DateOfPayment;

    /**
     * No args constructor for use in serialization
     */
    public UnitPayment() {
    }


    public String getUnitclientcode() {
        return unitclientcode;
    }

    public void setUnitclientcode(String unitclientcode) {
        this.unitclientcode = unitclientcode;
    }

    public String getPaymentTypeName() {
        return PaymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        PaymentTypeName = paymentTypeName;
    }

    public String getInstallmentAmount() {
        return InstallmentAmount;
    }

    public void setInstallmentAmount(String installmentAmount) {
        InstallmentAmount = installmentAmount;
    }

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }


    public boolean isPaid() {
        return Paid;
    }

    public void setPaid(boolean paid) {
        Paid = paid;
    }

    public String getPaidAmount() {
        return PaidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        PaidAmount = paidAmount;
    }

    public String getDateOfPayment() {
        return DateOfPayment;
    }

    public void setDateOfPayment(String dateOfPayment) {
        DateOfPayment = dateOfPayment;
    }
}
