package th.ac.kku.nu.injectionroom.activity.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import th.ac.kku.nu.injectionroom.R;
import th.ac.kku.nu.injectionroom.Storage;
import th.ac.kku.nu.injectionroom.dialog.SyringeAdjustorDialog;

public class SelectEquipment extends AppCompatActivity implements View.OnClickListener ,RadioGroup.OnCheckedChangeListener, SyringeAdjustorDialog.OnClickConfirmAdjustDialog{

    private static final String TAG="SELECT_EQP";
    private Storage.SYRINGE_TYPE currentSelectedSyringe;
    private Storage.NEEDLE_NO currentSelectedNeedle;
    private Storage.DRUG_TYPE currentSelectedDrug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_equipment);
        initializeComponent();
    }

    RadioGroup syringeType , needleSize , drugType;
    Button syringeAdjBtn;
    private void initializeComponent(){
        syringeAdjBtn = (Button) findViewById(R.id.adj_syr_btn);

        syringeType = (RadioGroup) findViewById(R.id.syringe_type);
        needleSize = (RadioGroup) findViewById(R.id.needle_size);
        drugType = (RadioGroup) findViewById(R.id.drug_type);
        addListener();
    }

    private void addListener(){
        syringeAdjBtn.setOnClickListener(this);
        syringeType.setOnCheckedChangeListener(this);
        needleSize.setOnCheckedChangeListener(this);
        drugType.setOnCheckedChangeListener(this);
    }

    SyringeAdjustorDialog syringeAdjustorDialog;
    @Override
    public void onClick(View v) {
        if(v == syringeAdjBtn){
            //Should pass some value into here
            if(currentSelectedDrug == null || currentSelectedNeedle == null || currentSelectedSyringe == null) {
                Toast.makeText(this , getString(R.string.complete_eqp) , Toast.LENGTH_SHORT).show();
            }else {
                syringeAdjustorDialog = SyringeAdjustorDialog.newInstance(currentSelectedSyringe);
                syringeAdjustorDialog.setOnClickConfirmAdjustDialog(this);
                syringeAdjustorDialog.show(getFragmentManager(), "show");
            }
        }
    }

    Bundle sendingEqp;

    @Override
    public void onClick() {
        syringeAdjustorDialog.dismiss();
        sendingEqp = new Bundle();
        if(currentSelectedSyringe == Storage.SYRINGE_TYPE.THREE_CC) {
            Log.d(TAG , "3CC packed");
            sendingEqp.putString(Storage.SYRINGE_TYPE_KEY, Storage.THREE_CC_STR);
        }else {
            Log.d(TAG , "INSULIN packed");
            sendingEqp.putString(Storage.SYRINGE_TYPE_KEY, Storage.INSULIN_SYRINGE_STR);
        }
        Intent startInjectProcess = new Intent(this , GameActivity.class);
        startInjectProcess.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startInjectProcess.putExtras(sendingEqp);
        commitPTS();
        startActivity(startInjectProcess);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(group == syringeType){
            switch (checkedId){
                case R.id.syr3cc:
                    Log.d(TAG , "3CC");
                    currentSelectedSyringe = Storage.SYRINGE_TYPE.THREE_CC;
                    break;
                case R.id.syrInsu:
                    Log.d(TAG , "SYRINSU");
                    currentSelectedSyringe = Storage.SYRINGE_TYPE.INSULIN_SYRINGE;
                    break;
            }
        }
        if(group == needleSize){
            switch (checkedId){
                case R.id.no21:
                    currentSelectedNeedle = Storage.NEEDLE_NO.NO_21;
                    break;
                case R.id.no24:
                    currentSelectedNeedle = Storage.NEEDLE_NO.NO_24;
                    break;
                case R.id.no27:
                    currentSelectedNeedle = Storage.NEEDLE_NO.NO_27;
                    break;
            }
        }
        if(group == drugType){
            switch (checkedId){
                case R.id.dmpa:
                    currentSelectedDrug = Storage.DRUG_TYPE.DMPA;
                    break;
                case R.id.insulin:
                    currentSelectedDrug = Storage.DRUG_TYPE.INSULIN;
                    break;
                case R.id.pvrv:
                    currentSelectedDrug = Storage.DRUG_TYPE.PVRV;
                    break;
            }
        }
    }

    private void commitPTS(){
        if(Storage.currentTaskTypeKey == Storage.Task.taskTypeKeyList.get(0)){
            if(currentSelectedSyringe == Storage.SYRINGE_TYPE.INSULIN_SYRINGE){
                Storage.InjectionProcess.totalPoint00++;
                Storage.InjectionProcess.syringe = true;
            }
            if(currentSelectedNeedle == Storage.NEEDLE_NO.NO_27){
                Storage.InjectionProcess.totalPoint00++;
                Storage.InjectionProcess.needleSize = true;
            }
            if(currentSelectedDrug == Storage.DRUG_TYPE.PVRV){
                Storage.InjectionProcess.totalPoint00++;
                Storage.InjectionProcess.drug =true;
            }
        }
        if(Storage.currentTaskTypeKey == Storage.Task.taskTypeKeyList.get(1)){
            if(currentSelectedSyringe == Storage.SYRINGE_TYPE.THREE_CC){
                Storage.InjectionProcess.totalPoint10++;
                Storage.InjectionProcess.syringe = true;
            }
            if(currentSelectedNeedle == Storage.NEEDLE_NO.NO_24){
                Storage.InjectionProcess.totalPoint10++;
                Storage.InjectionProcess.needleSize = true;
            }
            if(currentSelectedDrug == Storage.DRUG_TYPE.DMPA){
                Storage.InjectionProcess.totalPoint10++;
                Storage.InjectionProcess.drug =true;
            }
        }
        if(Storage.currentTaskTypeKey == Storage.Task.taskTypeKeyList.get(2)){
            if(currentSelectedSyringe == Storage.SYRINGE_TYPE.INSULIN_SYRINGE){
                Storage.InjectionProcess.totalPoint20++;
                Storage.InjectionProcess.syringe = true;
            }
            if(currentSelectedNeedle == Storage.NEEDLE_NO.NO_21){
                Storage.InjectionProcess.totalPoint20++;
                Storage.InjectionProcess.needleSize = true;
            }
            if(currentSelectedDrug == Storage.DRUG_TYPE.INSULIN){
                Storage.InjectionProcess.totalPoint20++;
                Storage.InjectionProcess.drug =true;
            }
        }
    }
}
