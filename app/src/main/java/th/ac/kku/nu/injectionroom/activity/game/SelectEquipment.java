package th.ac.kku.nu.injectionroom.activity.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import th.ac.kku.nu.injectionroom.R;
import th.ac.kku.nu.injectionroom.dialog.SyringeAdjustorDialog;

public class SelectEquipment extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_equipment);
        initializeComponent();
    }

    Button syringeAdjBtn;
    private void initializeComponent(){
        syringeAdjBtn = (Button) findViewById(R.id.adj_syr_btn);
        syringeAdjBtn.setOnClickListener(this);
    }

    SyringeAdjustorDialog syringeAdjustorDialog;
    @Override
    public void onClick(View v) {
        if(v == syringeAdjBtn){
            //Should pass some value into here
            syringeAdjustorDialog = SyringeAdjustorDialog.newInstance();
            syringeAdjustorDialog.show(getFragmentManager() , "show");
        }
    }
}
