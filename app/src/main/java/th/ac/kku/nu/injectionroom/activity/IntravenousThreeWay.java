package th.ac.kku.nu.injectionroom.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import th.ac.kku.nu.injectionroom.R;

public class IntravenousThreeWay extends AppCompatActivity {


    TextView purpose,procedure;
    String[] purpose_contents,procedure_contents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intravenous_three_way);

        purpose_contents = getResources().getStringArray(R.array.intravenous_purpose);
        procedure_contents = getResources().getStringArray(R.array.intravenous_ThreeWay_step);
        purpose = (TextView)findViewById(R.id.intravenous_threeWay_purpose);
        procedure = (TextView)findViewById(R.id.intravenous_threeWay_procedure);


        for (String purpose_content : purpose_contents) {
            purpose.append(purpose_content + "\n");
        }
        for (String procedure_contents : procedure_contents) {
            procedure.append(procedure_contents + "\n");
        }
    }
}
