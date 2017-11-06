package th.ac.kku.nu.injectionroom.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import th.ac.kku.nu.injectionroom.R;

public class Intramuscular extends AppCompatActivity {

    TextView purpose,procedure,position,z_purpose;
    String[] purpose_contents,procedure_contents,position_contents,z_purpose_contents;
    ImageView im_1,im_2,im_3,im_4,im_90,im_z;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intramuscular);

        purpose_contents = getResources().getStringArray(R.array.intramuscular_purpose);
        procedure_contents = getResources().getStringArray(R.array.intramuscular_step);
        position_contents = getResources().getStringArray(R.array.intramuscular_position);
        z_purpose_contents = getResources().getStringArray(R.array.z_track_step);
        purpose = (TextView)findViewById(R.id.intramuscular_purpose);
        procedure = (TextView)findViewById(R.id.intramuscular_procedure);
        position = (TextView)findViewById(R.id.intramuscular_position);
        z_purpose = (TextView)findViewById(R.id.z_track_procedure);

        for (String purpose_content : purpose_contents) {
            purpose.append(purpose_content + "\n");
        }
        for (String procedure_contents : procedure_contents) {
            procedure.append(procedure_contents + "\n");
        }
        for (String position_contents : position_contents) {
            position.append(position_contents + "\n");
        }
        for (String z_purpose_contents : z_purpose_contents) {
            z_purpose.append(z_purpose_contents + "\n");
        }

        im_1 = (ImageView)findViewById(R.id.im_1);
        Glide.with(this).load(R.drawable.im_1).into(im_1);

        im_2 = (ImageView)findViewById(R.id.im_2);
        Glide.with(this).load(R.drawable.id_15).into(im_2);

        im_3 = (ImageView)findViewById(R.id.im_3);
        Glide.with(this).load(R.drawable.im_3).into(im_3);

        im_4 = (ImageView)findViewById(R.id.im_4);
        Glide.with(this).load(R.drawable.im_4).into(im_4);

        im_90 = (ImageView)findViewById(R.id.im_90);
        Glide.with(this).load(R.drawable.im_90).into(im_90);

        im_z = (ImageView)findViewById(R.id.im_z);
        Glide.with(this).load(R.drawable.im_z).into(im_z);
    }
}
