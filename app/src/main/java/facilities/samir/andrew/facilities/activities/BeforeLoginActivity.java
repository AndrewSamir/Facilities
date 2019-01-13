package facilities.samir.andrew.facilities.activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import facilities.samir.andrew.facilities.R;

public class BeforeLoginActivity extends Activity {

    //region life cycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_login);
        getActionBar().hide();

        ButterKnife.bind(this);

    }

    //endregion

    //region clicks

    @OnClick(R.id.btnBeforeLoginRegistration)
    public void onClickbtnBeforeLoginRegistration() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @OnClick(R.id.btnBeforeLoginLogin)
    public void onClickbtnBeforeLoginLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    //endregion
}
