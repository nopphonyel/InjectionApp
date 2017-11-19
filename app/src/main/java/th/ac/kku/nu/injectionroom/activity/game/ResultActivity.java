package th.ac.kku.nu.injectionroom.activity.game;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import th.ac.kku.nu.injectionroom.R;
import th.ac.kku.nu.injectionroom.Storage;
import th.ac.kku.nu.injectionroom.activity.MainPage;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "RESULT ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Log.d(TAG, "Point : " + Storage.InjectionProcess.totalPoint00);
        Log.d(TAG, "Point : " + Storage.InjectionProcess.totalPoint10);
        Log.d(TAG, "Point : " + Storage.InjectionProcess.totalPoint20);
        initComponent();
        updateResult();
    }

    ImageView stEqpSyr, stEqpNeedleNum, stEqpDrug, stDrugVol, stGlove, stCottonAlc, stInjection, stDept, stCottonNoAlc;
    TextView eqpSyr, eqpNeedleNum, eqpDrug, eqpDrugVolumn;
    TextView glove, cottonAlc, injection, dept, cottonNoAlc;
    TextView pts;
    Button returnToMenu , playAgain;
    Intent intent , intentPlayAgain;

    private void initComponent() {
        pts = (TextView) findViewById(R.id.pts);

        eqpSyr = (TextView) findViewById(R.id.eqp_syr);
        eqpNeedleNum = (TextView) findViewById(R.id.eqp_needle_num);
        eqpDrug = (TextView) findViewById(R.id.eqp_drug);
        eqpDrugVolumn = (TextView) findViewById(R.id.eqp_drug_volumn);

        glove = (TextView) findViewById(R.id.glove);
        cottonAlc = (TextView) findViewById(R.id.cotton_alc);
        injection = (TextView) findViewById(R.id.inject);
        dept = (TextView) findViewById(R.id.inject_dept);
        cottonNoAlc = (TextView) findViewById(R.id.cotton);

        stEqpSyr = (ImageView) findViewById(R.id.status_eqp_syr);
        stEqpNeedleNum = (ImageView) findViewById(R.id.status_eqp_needle_num);
        stEqpDrug = (ImageView) findViewById(R.id.status_eqp_drug);
        stDrugVol = (ImageView) findViewById(R.id.status_eqp_drug_volumn);

        stGlove = (ImageView) findViewById(R.id.status_glove);
        stCottonAlc = (ImageView) findViewById(R.id.status_cotton_alc);
        stInjection = (ImageView) findViewById(R.id.status_inject);
        stDept = (ImageView) findViewById(R.id.status_inject_dept);
        stCottonNoAlc = (ImageView) findViewById(R.id.status_cotton);

        intent = new Intent(this , MainPage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intentPlayAgain = new Intent(this, SelectTask.class);
        intentPlayAgain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        playAgain = (Button) findViewById(R.id.play_again);
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage.InjectionProcess.resetProcessMarks();
                startActivity(intentPlayAgain);
            }
        });
        returnToMenu = (Button) findViewById(R.id.return_to_menu);
        returnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage.InjectionProcess.resetProcessMarks();
                startActivity(intent);
                finish();
            }
        });
    }

    private void updateResult() {
        String ptsString = "";
        Log.d("DRUG VOLUMN" ,"VOL:" + Storage.InjectionProcess.drugVolumn );
        if (Storage.currentTaskTypeKey.equalsIgnoreCase(Storage.Task.taskTypeKeyList.get(0))) {
            if (Storage.InjectionProcess.drugVolumn <= Storage.InjectionProcess.DRUG_VOLUMN00_MAX &&
                    Storage.InjectionProcess.drugVolumn >= Storage.InjectionProcess.DRUG_VOLUMN00_MIN) {
                Storage.InjectionProcess.drugVolumeCorrect = true;
                Storage.InjectionProcess.totalPoint00++;
            }
            if (Storage.InjectionProcess.dept <= Storage.InjectionProcess.MAXDEPT00 && Storage.InjectionProcess.dept >= Storage.InjectionProcess.MINDEPT00) {
                Storage.InjectionProcess.deptCorrect = true;
                Storage.InjectionProcess.totalPoint00++;
            }
            ptsString = Storage.InjectionProcess.totalPoint00 + "/" + 9;
        }
        if (Storage.currentTaskTypeKey.equalsIgnoreCase(Storage.Task.taskTypeKeyList.get(1))) {
            if (Storage.InjectionProcess.drugVolumn <= Storage.InjectionProcess.DRUG_VOLUMN10_MAX &&
                    Storage.InjectionProcess.drugVolumn >= Storage.InjectionProcess.DRUG_VOLUMN10_MIN) {
                Storage.InjectionProcess.drugVolumeCorrect = true;
                Storage.InjectionProcess.totalPoint10++;
            }
            if (Storage.InjectionProcess.dept <= Storage.InjectionProcess.MAXDEPT10 && Storage.InjectionProcess.dept >= Storage.InjectionProcess.MINDEPT10) {
                Storage.InjectionProcess.deptCorrect = true;
                Storage.InjectionProcess.totalPoint10++;
            }
            ptsString = Storage.InjectionProcess.totalPoint10 + "/" + 9;
        }
        if (Storage.currentTaskTypeKey.equalsIgnoreCase(Storage.Task.taskTypeKeyList.get(2))) {
            if (Storage.InjectionProcess.drugVolumn <= Storage.InjectionProcess.DRUG_VOLUMN20_MAX &&
                    Storage.InjectionProcess.drugVolumn >= Storage.InjectionProcess.DRUG_VOLUMN20_MIN) {
                Storage.InjectionProcess.drugVolumeCorrect = true;
                Storage.InjectionProcess.totalPoint20++;
            }
            if (Storage.InjectionProcess.dept <= Storage.InjectionProcess.MAXDEPT20 && Storage.InjectionProcess.dept >= Storage.InjectionProcess.MINDEPT20) {
                Storage.InjectionProcess.deptCorrect = true;
                Storage.InjectionProcess.totalPoint20++;
            }
            ptsString = Storage.InjectionProcess.totalPoint20 + "/" + 9;
        }
        pts.setText(ptsString);

        if (!Storage.InjectionProcess.syringe) {
            eqpSyr.setText(getString(R.string.select_result_syringe_x));
            stEqpSyr.setImageDrawable(getDrawable(R.drawable.incorrect));
        }
        if (!Storage.InjectionProcess.needleSize) {
            eqpNeedleNum.setText(getString(R.string.select_result_needle_num_x));
            stEqpNeedleNum.setImageDrawable(getDrawable(R.drawable.incorrect));
        }
        if (!Storage.InjectionProcess.drug) {
            eqpDrug.setText(getString(R.string.select_result_needle_num_x));
            stEqpDrug.setImageDrawable(getDrawable(R.drawable.incorrect));
        }
        if (!Storage.InjectionProcess.drugVolumeCorrect) {
            eqpDrugVolumn.setText(getString(R.string.select_result_drug_volumn_x));
            stDrugVol.setImageDrawable(getDrawable(R.drawable.incorrect));
        }

        if (!Storage.InjectionProcess.glove) {
            glove.setText(getString(R.string.inject_result_glove_x));
            stGlove.setImageDrawable(getDrawable(R.drawable.incorrect));
        }
        if (!Storage.InjectionProcess.cottonAlc) {
            cottonAlc.setText(getString(R.string.inject_result_cotton_alc_x));
            stCottonAlc.setImageDrawable(getDrawable(R.drawable.incorrect));
        }
        if (!Storage.InjectionProcess.injectCorrect) {
            injection.setText(getString(R.string.inject_result_injection_x));
            stInjection.setImageDrawable(getDrawable(R.drawable.incorrect));
        }
        if (!Storage.InjectionProcess.deptCorrect) {
            dept.setText(getString(R.string.inject_result_injection_dept_x));
            stDept.setImageDrawable(getDrawable(R.drawable.incorrect));
        }
        if (!Storage.InjectionProcess.useCotton) {
            cottonNoAlc.setText(getString(R.string.inject_result_injection_cotton_x));
            stCottonNoAlc.setImageDrawable(getDrawable(R.drawable.incorrect));
        }
    }

    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }
}
