package th.ac.kku.nu.injectionroom.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import th.ac.kku.nu.injectionroom.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }
}
