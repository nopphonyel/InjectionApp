package th.ac.kku.nu.injectionroom.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import th.ac.kku.nu.injectionroom.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Subcutaneous extends AppCompatActivity {

    TextView purpose,procedure,position;
    String[] purpose_contents,procedure_contents,position_contents;
    ImageView sc_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcutaneous);

        purpose_contents = getResources().getStringArray(R.array.subcutaneous_purpose);
        procedure_contents = getResources().getStringArray(R.array.subcutaneous_step);
        position_contents = getResources().getStringArray(R.array.subcutaneous_position);
        purpose = (TextView)findViewById(R.id.subcutaneous_purpose);
        procedure = (TextView)findViewById(R.id.subcutaneous_procedure);
        position = (TextView)findViewById(R.id.subcutaneous_position);

        for (String purpose_content : purpose_contents) {
            purpose.append(purpose_content + "\n");
        }
        for (String procedure_contents : procedure_contents) {
            procedure.append(procedure_contents + "\n");
        }
        for (String position_contents : position_contents) {
            position.append(position_contents + "\n");
        }

        sc_position = (ImageView)findViewById(R.id.sc_position);
        Glide.with(this).load(R.drawable.sc_position).into(sc_position);

    }

    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }
}
