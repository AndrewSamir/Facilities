package facilities.samir.andrew.facilities.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (FirebaseAuth.getInstance().getCurrentUser() == null)
            startActivity(new Intent(this, RegisterActivity.class));
        else
            startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
