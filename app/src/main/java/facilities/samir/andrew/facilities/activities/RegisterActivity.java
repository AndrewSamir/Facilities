package facilities.samir.andrew.facilities.activities;

import android.app.Dialog;
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

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import facilities.samir.andrew.facilities.FirebaseHandler.HandleAddDataToFirebase;
import facilities.samir.andrew.facilities.R;
import facilities.samir.andrew.facilities.interfaces.InterfaceAddDataToFirebase;
import facilities.samir.andrew.facilities.models.RegisterationData;

public class RegisterActivity extends Activity implements Validator.ValidationListener, InterfaceAddDataToFirebase {

    //region fields
    private FirebaseAuth mAuth;
    Validator validator;

    //endregion

    //region views

    @NotEmpty
    @BindView(R.id.edtNameRegister)
    EditText edtNameRegister;

    @Email
    @BindView(R.id.edtMailRegister)
    EditText edtMailRegister;

    @Length(min = 10, max = 10)
    @BindView(R.id.edtPhoneRegister)
    EditText edtPhoneRegister;

    @NotEmpty
    @BindView(R.id.edtBirthdate)
    TextView edtBirthDate;

    @Password
    @BindView(R.id.edtPasswordRegister)
    EditText edtPasswordRegister;

    @ConfirmPassword
    @BindView(R.id.edtConfirmPasswordRegister)
    EditText edtConfirmPasswordRegister;

    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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

    @OnClick(R.id.tvHaveAccountRegister)
    public void onClickTvHaveAccountRegister() {
        // TODO submit data to server...
    }

      @OnClick(R.id.edtBirthdate)
          public void onClickedtBirthdate() {
        edtBirthDate.setError(null);
          createDialog();
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
            }else {
                ((TextView)view).setError("Required Field");
            }
        }

    }

    //endregion

    //region calls

    private void createAccount() {


        //  showProgressDialog();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(edtMailRegister.getText().toString(), edtPasswordRegister.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //set data to database
                        final RegisterationData registerationData = new RegisterationData();
                        registerationData.setDisplayName(edtNameRegister.getText().toString());
                        registerationData.setMail(edtMailRegister.getText().toString());
                        registerationData.setMobile(edtPhoneRegister.getText().toString());
                        registerationData.setBirthDate(edtBirthDate.getText().toString());

                        HandleAddDataToFirebase.getInstance(RegisterActivity.this).callAddProfileData("callAddProfileData", registerationData);

                    }
                });
        // [END create_user_with_email]
    }

    @Override
    public void onDataAddedSuccess(String flag) {
        Log.d("ttt","s");
    }

    @Override
    public void onDataAddedFailed(String flag) {

    }

    @Override
    public void onDataAddedRepeated(String flag) {

    }

    private void createDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_date_paker);

        final DatePicker datePicker =  dialog.findViewById(R.id.datePicker2);

        Button cancel =  dialog.findViewById(R.id.cancelAbsent);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        Button continueButton =  dialog.findViewById(R.id.conToAbsent);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day, 0, 0, 0);
                long startTime = calendar.getTimeInMillis();
                String date = Long.toString(startTime);
                date = date.substring(0, date.length() - 3);
                date = date + "000";
                String dateShow = Integer.toString(day) + "-" + Integer.toString(month + 1) + "-" + Integer.toString(year);

                edtBirthDate.setText(dateShow);
                dialog.dismiss();

            }
        });
        dialog.show();
    }

    //endregion
}