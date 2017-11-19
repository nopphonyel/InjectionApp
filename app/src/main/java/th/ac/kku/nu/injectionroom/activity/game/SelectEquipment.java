package th.ac.kku.nu.injectionroom.activity.game;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import th.ac.kku.nu.injectionroom.R;
import th.ac.kku.nu.injectionroom.Storage;
import th.ac.kku.nu.injectionroom.dialog.SyringeAdjustorDialog;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SelectEquipment extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, SyringeAdjustorDialog.OnClickConfirmAdjustDialog {

    private static final String TAG = "SELECT_EQP";
    private Storage.SYRINGE_TYPE currentSelectedSyringe;
    private Storage.NEEDLE_NO currentSelectedNeedle;
    private Storage.DRUG_TYPE currentSelectedDrug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_equipment);
        initializeComponent();
    }

    RadioGroup syringeType, needleSize, drugType;
    Button syringeAdjBtn;
    ImageView backBtn;
    TextView selectNeedleTitle;

    private void initializeComponent() {
        syringeAdjBtn = (Button) findViewById(R.id.adj_syr_btn);
        backBtn = (ImageView) findViewById(R.id.ic_back);

        syringeType = (RadioGroup) findViewById(R.id.syringe_type);
        needleSize = (RadioGroup) findViewById(R.id.needle_size);
        drugType = (RadioGroup) findViewById(R.id.drug_type);
        selectNeedleTitle = (TextView) findViewById(R.id.select_needle_title);
        root = (ScrollView) findViewById(R.id.content_list);
        addListener();
    }

    private void addListener() {
        syringeAdjBtn.setOnClickListener(this);
        syringeType.setOnCheckedChangeListener(this);
        needleSize.setOnCheckedChangeListener(this);
        drugType.setOnCheckedChangeListener(this);
        backBtn.setOnClickListener(this);
    }

    SyringeAdjustorDialog syringeAdjustorDialog;

    @Override
    public void onClick(View v) {
        if (v == syringeAdjBtn) {
            //Should pass some value into here
            if (currentSelectedDrug == null || currentSelectedNeedle == null || currentSelectedSyringe == null) {
                Toast.makeText(this, getString(R.string.complete_eqp), Toast.LENGTH_SHORT).show();
            } else {
                syringeAdjustorDialog = SyringeAdjustorDialog.newInstance(currentSelectedSyringe);
                syringeAdjustorDialog.setOnClickConfirmAdjustDialog(this);
                syringeAdjustorDialog.show(getFragmentManager(), "show");
            }
        }
        if (v == backBtn){
            finish();
        }
    }

    Bundle sendingEqp;

    @Override
    public void onClick() {
        Storage.InjectionProcess.drugVolumn = syringeAdjustorDialog.getVolumn();
        syringeAdjustorDialog.dismiss();
        sendingEqp = new Bundle();
        if (currentSelectedSyringe == Storage.SYRINGE_TYPE.THREE_CC) {
            Log.d(TAG, "3ML packed");
            sendingEqp.putString(Storage.SYRINGE_TYPE_KEY, Storage.THREE_CC_STR);
        } else if(currentSelectedSyringe == Storage.SYRINGE_TYPE.ONE_CC) {
            Log.d(TAG, "1ML packed");
            sendingEqp.putString(Storage.SYRINGE_TYPE_KEY, Storage.ONE_CC_STR);
        } else {
            sendingEqp.putString(Storage.SYRINGE_TYPE_KEY , Storage.INSULIN_SYRINGE_STR);
        }
        Intent startInjectProcess = new Intent(this, GameActivity.class);
        startInjectProcess.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startInjectProcess.putExtras(sendingEqp);
        commitPTS();
        startActivity(startInjectProcess);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (group == syringeType) {
            enableSelectNeedle();
            switch (checkedId) {
                case R.id.syr3ml:
                    Log.d(TAG, "3ML");
                    currentSelectedSyringe = Storage.SYRINGE_TYPE.THREE_CC;
                    break;
                case R.id.syr1ml:
                    Log.d(TAG, "1ML");
                    currentSelectedSyringe = Storage.SYRINGE_TYPE.ONE_CC;
                    break;
                case R.id.syrInsu:
                    currentSelectedSyringe = Storage.SYRINGE_TYPE.INSULIN_SYRINGE;
                    currentSelectedNeedle = Storage.NEEDLE_NO.NO_21;
                    disableNeedleSize();
                    break;
            }
        }
        if (group == needleSize) {
            switch (checkedId) {
                case R.id.no21:
                    currentSelectedNeedle = Storage.NEEDLE_NO.NO_21;
                    break;
                case R.id.no22:
                    currentSelectedNeedle = Storage.NEEDLE_NO.NO_22;
                    break;
                case R.id.no23:
                    currentSelectedNeedle = Storage.NEEDLE_NO.NO_23;
                    break;
                case R.id.no24:
                    currentSelectedNeedle = Storage.NEEDLE_NO.NO_24;
                    break;
                case R.id.no25:
                    currentSelectedNeedle = Storage.NEEDLE_NO.NO_25;
                    break;
                case R.id.no26:
                    currentSelectedNeedle = Storage.NEEDLE_NO.NO_26;
                    break;
                case R.id.no27:
                    currentSelectedNeedle = Storage.NEEDLE_NO.NO_27;
                    break;
            }
        }
        if (group == drugType) {
            switch (checkedId) {
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

    private void commitPTS() {
        if (Storage.currentTaskTypeKey.equalsIgnoreCase(Storage.Task.taskTypeKeyList.get(0))) {
            if (currentSelectedSyringe == Storage.SYRINGE_TYPE.ONE_CC) {
                Storage.InjectionProcess.totalPoint00++;
                Storage.InjectionProcess.syringe = true;
            }
            if (currentSelectedNeedle == Storage.NEEDLE_NO.NO_27 ||
                    currentSelectedNeedle == Storage.NEEDLE_NO.NO_26 ||
                    currentSelectedNeedle == Storage.NEEDLE_NO.NO_25) {
                Storage.InjectionProcess.totalPoint00++;
                Storage.InjectionProcess.needleSize = true;
            }
            if (currentSelectedDrug == Storage.DRUG_TYPE.PVRV) {
                Storage.InjectionProcess.totalPoint00++;
                Storage.InjectionProcess.drug = true;
            }
        }
        if (Storage.currentTaskTypeKey.equalsIgnoreCase(Storage.Task.taskTypeKeyList.get(1))) {
            if (currentSelectedSyringe == Storage.SYRINGE_TYPE.THREE_CC) {
                Storage.InjectionProcess.totalPoint10++;
                Storage.InjectionProcess.syringe = true;
            }
            if (currentSelectedNeedle == Storage.NEEDLE_NO.NO_23 ||
                    currentSelectedNeedle == Storage.NEEDLE_NO.NO_22 ||
                    currentSelectedNeedle == Storage.NEEDLE_NO.NO_21) {
                Storage.InjectionProcess.totalPoint10++;
                Storage.InjectionProcess.needleSize = true;
            }
            if (currentSelectedDrug == Storage.DRUG_TYPE.DMPA) {
                Storage.InjectionProcess.totalPoint10++;
                Storage.InjectionProcess.drug = true;
            }
        }
        if (Storage.currentTaskTypeKey.equalsIgnoreCase(Storage.Task.taskTypeKeyList.get(2))) {
            if (currentSelectedSyringe == Storage.SYRINGE_TYPE.INSULIN_SYRINGE) {
                Storage.InjectionProcess.totalPoint20+=2;
                Storage.InjectionProcess.syringe = true;
            }
            /*
            if (currentSelectedNeedle == Storage.NEEDLE_NO.NO_24 ||
                    currentSelectedNeedle == Storage.NEEDLE_NO.NO_25||
                    currentSelectedNeedle == Storage.NEEDLE_NO.NO_26) {
                Storage.InjectionProcess.totalPoint20++;
                Storage.InjectionProcess.needleSize = true;
            }*/
            if (currentSelectedDrug == Storage.DRUG_TYPE.INSULIN) {
                Storage.InjectionProcess.totalPoint20++;
                Storage.InjectionProcess.drug = true;
            }
        }
    }

    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }

    ScrollView root;

    private void enableSelectNeedle(){
        selectNeedleTitle.setText(getString(R.string.select_needle_size));
        needleSize.setVisibility(View.VISIBLE);
        TransitionManager.beginDelayedTransition(root);
    }

    private void disableNeedleSize(){
        selectNeedleTitle.setText(getString(R.string.select_needle_size_unavi));
        RadioButton eachButton;
        needleSize.setVisibility(View.GONE);
        TransitionManager.beginDelayedTransition(root);
    }
}
