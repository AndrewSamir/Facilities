package facilities.samir.andrew.facilities.models;


/**
 * Created by andre on 31-Mar-17.
 */


public class RegisterationData {

    public String displayName;
    public String email;
    public String mobileNumber;
    public String birthDate;


    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }


    public RegisterationData() {
    }

    public void setDisplayName(String name) {
        this.displayName = name;
    }

    public void setMail(String mail) {
        this.email = mail;
    }

    public void setMobile(String mobile) {
        this.mobileNumber = mobile;
    }

}
