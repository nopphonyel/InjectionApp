package th.ac.kku.nu.injectionroom.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import th.ac.kku.nu.injectionroom.R;

public class InjectionType extends AppCompatActivity implements View.OnClickListener {

    CardView Intradermal,Subcutaneous,Intramuscular,Intravenous,
            Intravenous_direct,Intravenous_heparinLock,Intravenous_normalSaline,
            Intravenous_IVSet,Intravenous_soluSet,Intravenous_threeWay;
    private int toggle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_injection_type);

        Intradermal = (CardView)findViewById(R.id.intradermal);
        Subcutaneous = (CardView)findViewById(R.id.subcutaneous);
        Intramuscular = (CardView)findViewById(R.id.intramuscular);
        Intravenous = (CardView)findViewById(R.id.intravenous);
        Intravenous_direct = (CardView)findViewById(R.id.intravenous_direct);
        Intravenous_heparinLock = (CardView)findViewById(R.id.intravenous_heparinLock);
        Intravenous_normalSaline = (CardView)findViewById(R.id.intravenous_normalSalineLock);
        Intravenous_IVSet = (CardView)findViewById(R.id.intravenous_IVSet);
        Intravenous_soluSet = (CardView)findViewById(R.id.intravenous_soluSet);
        Intravenous_threeWay = (CardView)findViewById(R.id.intravenous_threeWay);

        Intradermal.setOnClickListener(this);
        Subcutaneous.setOnClickListener(this);
        Intramuscular.setOnClickListener(this);
        Intravenous.setOnClickListener(this);
        Intravenous_direct.setOnClickListener(this);
        Intravenous_heparinLock.setOnClickListener(this);
        Intravenous_normalSaline.setOnClickListener(this);
        Intravenous_IVSet.setOnClickListener(this);
        Intravenous_soluSet.setOnClickListener(this);
        Intravenous_threeWay.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==Intradermal){
            Intent intent = new Intent(this, Intradermal.class);
            startActivity(intent);
        }else if(v==Subcutaneous){
            Intent intent = new Intent(this, Subcutaneous.class);
            startActivity(intent);
        }else if(v==Intramuscular){
            Intent intent = new Intent(this, Intramuscular.class);
            startActivity(intent);
        }else if(v==Intravenous){
            if(toggle==0){
                Intravenous_direct.setVisibility(View.VISIBLE);
                Intravenous_heparinLock.setVisibility(View.VISIBLE);
                Intravenous_normalSaline.setVisibility(View.VISIBLE);
                Intravenous_IVSet.setVisibility(View.VISIBLE);
                Intravenous_soluSet.setVisibility(View.VISIBLE);
                Intravenous_threeWay.setVisibility(View.VISIBLE);
                toggle = 1;
            }else if(toggle==1){
                Intravenous_direct.setVisibility(View.GONE);
                Intravenous_heparinLock.setVisibility(View.GONE);
                Intravenous_normalSaline.setVisibility(View.GONE);
                Intravenous_IVSet.setVisibility(View.GONE);
                Intravenous_soluSet.setVisibility(View.GONE);
                Intravenous_threeWay.setVisibility(View.GONE);
                toggle = 0;
            }
        }else if(v==Intravenous_direct){
            Intent intent = new Intent(this, IntravenousDirect.class);
            startActivity(intent);
        }else if(v==Intravenous_heparinLock){
            Intent intent = new Intent(this, IntravenousHeparin.class);
            startActivity(intent);
        }else if(v==Intravenous_normalSaline){
            Intent intent = new Intent(this, IntravenousNormalSalineLock.class);
            startActivity(intent);
        }else if(v==Intravenous_IVSet){
            Intent intent = new Intent(this, IntravenousIVSet.class);
            startActivity(intent);
        }else if(v==Intravenous_soluSet){
            Intent intent = new Intent(this, IntravenousSoluSet.class);
            startActivity(intent);
        }else if(v==Intravenous_threeWay){
            Intent intent = new Intent(this, IntravenousThreeWay.class);
            startActivity(intent);
        }
    }
}
