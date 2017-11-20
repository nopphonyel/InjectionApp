package th.ac.kku.nu.injectionroom.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;

import java.util.Locale;

import th.ac.kku.nu.injectionroom.R;
import th.ac.kku.nu.injectionroom.Storage;
import th.ac.kku.nu.injectionroom.activity.game.SelectTask;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainPage extends AppCompatActivity implements View.OnClickListener {

    ImageView syringe, nurse, iconName, bg;
    CardView tenR, info, game;
    Handler handler = new Handler();
    Locale myLocale;
    Switch sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        syringe = (ImageView) findViewById(R.id.syringe_main);
        Glide.with(this).load(R.drawable.syringe2).into(syringe);

        nurse = (ImageView) findViewById(R.id.nurse_main);
        Glide.with(this).load(R.drawable.nurse_main).into(nurse);

        iconName = (ImageView) findViewById(R.id.icon_name_main);
        Glide.with(this).load(R.drawable.iconname).into(iconName);

        bg = (ImageView) findViewById(R.id.bg);
        Glide.with(this).load(R.drawable.blue2).into(bg);

        tenR = (CardView) findViewById(R.id.ten_R);
        info = (CardView) findViewById(R.id.information);
        game = (CardView) findViewById(R.id.play_game);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tenR.setVisibility(View.VISIBLE);
            }
        }, 1000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                info.setVisibility(View.VISIBLE);
            }
        }, 2000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                game.setVisibility(View.VISIBLE);
            }
        }, 3000);

        tenR.setOnClickListener(this);
        info.setOnClickListener(this);
        game.setOnClickListener(this);

        sw = (Switch) findViewById(R.id.sw);

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b){
                    Storage.checkLanguage++;
                }
                if (Storage.checkLanguage % 2 != 0) {
                    setLocale("en");
                    Log.d("Local", "en");

                } else {
                    setLocale("th");
                    Log.d("Local", "th");

                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v == tenR) {
            Intent intent = new Intent(this, TenR.class);
            startActivity(intent);
        } else if (v == info) {
            Intent intent = new Intent(this, InjectionType.class);
            startActivity(intent);
        } else if (v == game) {
            Intent intent = new Intent(this, SelectTask.class);
            startActivity(intent);
        }
    }



    public void setLocale(String lang) {
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        final Intent refresh = new Intent(this, MainPage.class);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(refresh);
            }
        }, 1000);

    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }
}
