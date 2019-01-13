package facilities.samir.andrew.facilities.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import developer.mokadim.projectmate.dialog.IndicatorStyle;
import developer.mokadim.projectmate.dialog.ProgressDialog;
import facilities.samir.andrew.facilities.FirebaseHandler.HandleAddDataToFirebase;
import facilities.samir.andrew.facilities.R;
import facilities.samir.andrew.facilities.interfaces.InterfaceAddDataToFirebase;
import facilities.samir.andrew.facilities.models.ProfileData;
import facilities.samir.andrew.facilities.models.RegestrationData;

public class RegisterActivity extends Activity implements Validator.ValidationListener, InterfaceAddDataToFirebase {

    //region fields
    private FirebaseAuth mAuth;
    Validator validator;

    //endregion

    //region views

    @NotEmpty
    @BindView(R.id.edtNameRegister)
    EditText edtNameRegister;

    @NotEmpty
    @BindView(R.id.edtLastNameRegister)
    EditText edtLastNameRegister;

    @Email
    @BindView(R.id.edtMailRegister)
    EditText edtMailRegister;

    @Length(min = 10, max = 10)
    @BindView(R.id.edtPhoneRegister)
    EditText edtPhoneRegister;


    @Password
    @BindView(R.id.edtPasswordRegister)
    EditText edtPasswordRegister;


    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getActionBar().hide();

        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();

        validator = new Validator(this);
        validator.setValidationListener(this);

        HandleAddDataToFirebase.getInstance(this).setClickDialogListener(this);
    }

    //region clicks

    @OnClick(R.id.buttonRegister)
    public void onClickButtonRegister() {
        validator.validate();
    }



    //endregion

    //region validation
    public void onValidationSucceeded() {
        createAccount();
//        callRegister();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                ((TextView) view).setError("Required Field");
            }
        }

    }

    //endregion

    //region calls

    private void createAccount() {


        //  showProgressDialog();

        // [START create_user_with_email]
        Dialog progressDialog = new ProgressDialog(this, IndicatorStyle.BallBeat).show();
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(edtMailRegister.getText().toString(), edtPasswordRegister.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //set data to database

                        RegestrationData regestrationData = new RegestrationData();

                        ProfileData profileData = new ProfileData();
                        profileData.setDisplayName(edtNameRegister.getText().toString() + " " + edtLastNameRegister.getText().toString());
                        profileData.setMail(edtMailRegister.getText().toString());
                        profileData.setMobile(edtPhoneRegister.getText().toString());

                        regestrationData.setProfileData(profileData);
                        HandleAddDataToFirebase.getInstance(RegisterActivity.this).callAddProfileData("callAddProfileData", regestrationData);

                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
        progressDialog.dismiss();
        // [END create_user_with_email]
    }

    @Override
    public void onDataAddedSuccess(String flag) {

        Intent goToHome = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(goToHome);
        finish();
    }

    @Override
    public void onDataAddedFailed(String flag) {

    }

    @Override
    public void onDataAddedRepeated(String flag) {

    }

    //endregion
}
