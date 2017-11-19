package th.ac.kku.nu.injectionroom.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.Locale;

import th.ac.kku.nu.injectionroom.R;
import th.ac.kku.nu.injectionroom.Storage;
import th.ac.kku.nu.injectionroom.adapter.TenRAdapter;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TenR extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    Locale myLocale;
    Switch sw;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ten_r);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.ten_r_title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TenRAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);
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
                    final Intent refresh = new Intent(getApplicationContext(), TenR.class);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(refresh);
                        }
                    }, 1000);
                } else {
                    setLocale("th");
                    Log.d("Local", "th");
                    final Intent refresh = new Intent(getApplicationContext(), TenR.class);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(refresh);
                        }
                    }, 1000);
                }
            }
        });
    }

    public void setLocale(String lang) {
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        //Intent refresh = new Intent(this, MainPage.class);
        //startActivity(refresh);
    }


    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent refresh = new Intent(getApplicationContext(), MainPage.class);
        startActivity(refresh);
    }
}
