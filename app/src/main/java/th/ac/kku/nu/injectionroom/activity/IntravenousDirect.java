package th.ac.kku.nu.injectionroom.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import th.ac.kku.nu.injectionroom.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class IntravenousDirect extends AppCompatActivity {

    TextView purpose,procedure;
    String[] purpose_contents,procedure_contents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intravenous_direct);

        purpose_contents = getResources().getStringArray(R.array.intravenous_purpose);
        procedure_contents = getResources().getStringArray(R.array.intravenous_direct_step);
        purpose = (TextView)findViewById(R.id.intravenous_direct_purpose);
        procedure = (TextView)findViewById(R.id.intravenous_direct_procedure);

        for (String purpose_content : purpose_contents) {
            purpose.append(purpose_content + "\n");
        }
        for (String procedure_contents : procedure_contents) {
            procedure.append(procedure_contents + "\n");
        }
    }
    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }
}
