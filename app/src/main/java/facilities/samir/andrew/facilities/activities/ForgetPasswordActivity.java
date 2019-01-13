package facilities.samir.andrew.facilities.activities;

import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import facilities.samir.andrew.facilities.R;

public class ForgetPasswordActivity extends Activity implements Validator.ValidationListener {

    //region fields
    Validator validator;
    //endregion

    //region views

    @Email
    @BindView(R.id.edtUserNameForgetPassword)
    EditText edtUserNameForgetPassword;

    //endregion

    //region life cycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().hide();
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    //endregion

    //region clicks
    @OnClick(R.id.btnForgetPassword)
    public void onClickbtnForgetPassword() {
        validator.validate();
    }
    //endregion

    //region validation
    public void onValidationSucceeded() {
        callForgetPassword();
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

    private void callForgetPassword() {
        FirebaseAuth.getInstance().sendPasswordResetEmail(edtUserNameForgetPassword.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            TastyToast.makeText(ForgetPasswordActivity.this, "Email sent.", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
                            onBackPressed();
                        }
                    }
                });
    }

    //endregion

}
