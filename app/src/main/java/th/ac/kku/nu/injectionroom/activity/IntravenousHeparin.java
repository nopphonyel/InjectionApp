package th.ac.kku.nu.injectionroom.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import th.ac.kku.nu.injectionroom.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class IntravenousHeparin extends AppCompatActivity {

    TextView purpose,procedure;
    String[] purpose_contents,procedure_contents;
    ImageView position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intravenous_heparin);

        purpose_contents = getResources().getStringArray(R.array.intravenous_purpose);
        procedure_contents = getResources().getStringArray(R.array.intravenous_heparinLock_step);
        purpose = (TextView)findViewById(R.id.intravenous_heparinLock_purpose);
        procedure = (TextView)findViewById(R.id.intravenous_heparinLock_procedure);


        for (String purpose_content : purpose_contents) {
            purpose.append(purpose_content + "\n");
        }
        for (String procedure_contents : procedure_contents) {
            procedure.append(procedure_contents + "\n");
        }

        position = (ImageView)findViewById(R.id.intravenous_heparinLock_position);
        Glide.with(this).load(R.drawable.heparin_lock).into(position);
    }

    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }
}
