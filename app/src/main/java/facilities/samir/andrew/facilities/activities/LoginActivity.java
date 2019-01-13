package facilities.samir.andrew.facilities.activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import facilities.samir.andrew.facilities.R;
import facilities.samir.andrew.facilities.models.RegestrationData;

public class LoginActivity extends Activity implements Validator.ValidationListener {

    //region fields
    Validator validator;
    FirebaseAuth mAuth;
    //endregion

    //region views

    @NotEmpty
    @BindView(R.id.edtUserNameLogin)
    EditText edtUserNameLogin;

    @Password
    @BindView(R.id.edtPasswordLogin)
    EditText edtPasswordLogin;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getActionBar().hide();
        ButterKnife.bind(this);

        validator = new Validator(this);
        validator.setValidationListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    //region clicks

    @OnClick(R.id.btnSignIn)
    public void onClickbtnSignIn() {
        validator.validate();
    }

    @OnClick(R.id.tvRegisterLogin)
    public void onClick() {
        startActivity(new Intent(this, ForgetPasswordActivity.class));
        finish();
    }
    //endregion

    //region validation
    public void onValidationSucceeded() {
        callLogin();
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

    private void callLogin() {
        mAuth.signInWithEmailAndPassword(edtUserNameLogin.getText().toString(), edtPasswordLogin.getText().toString())
                .addOnSuccessListener(LoginActivity.this, new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
//                        FirebaseMessaging.getInstance().subscribeToTopic(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        TastyToast.makeText(getApplicationContext(), "your logged in successfully", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);

                        Intent goToHome = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(goToHome);
                        finish();
                    }
                }).addOnFailureListener(LoginActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("errorLog", e.toString());
                TastyToast.makeText(getApplicationContext(), "Email or Password is Incorrect", TastyToast.LENGTH_LONG, TastyToast.CONFUSING);

            }
        });
    }
    //endregion
}
