package th.ac.kku.nu.injectionroom.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import th.ac.kku.nu.injectionroom.R;
import th.ac.kku.nu.injectionroom.activity.game.SelectTask;

public class MainPage extends AppCompatActivity implements View.OnClickListener{

    ImageView syringe,nurse;
    CardView tenR,info,game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        syringe = (ImageView)findViewById(R.id.syringe_main);
        Glide.with(this).load(R.drawable.syringe).into(syringe);

        nurse = (ImageView)findViewById(R.id.nurse_main);
        Glide.with(this).load(R.drawable.nurse_main).into(nurse);

        tenR = (CardView)findViewById(R.id.ten_R);
        info = (CardView)findViewById(R.id.information);
        game = (CardView)findViewById(R.id.play_game);
        tenR.setOnClickListener(this);
        info.setOnClickListener(this);
        game.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==tenR){
            Intent intent = new Intent(this, TenR.class);
            startActivity(intent);
        }else if(v==info){
            Intent intent = new Intent(this, InjectionType.class);
            startActivity(intent);
        }else if(v==game){
            Intent intent = new Intent(this, SelectTask.class);
            startActivity(intent);
        }
    }
}
