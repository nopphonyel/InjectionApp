package th.ac.kku.nu.injectionroom.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import th.ac.kku.nu.injectionroom.R;

public class Intradermal extends AppCompatActivity {

    TextView purpose,procedure;
    String[] purpose_contents,procedure_contents;
    ImageView id_15,id_ex,id_position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intradermal);

        purpose_contents = getResources().getStringArray(R.array.intradermal_purpose);
        procedure_contents = getResources().getStringArray(R.array.intradermal_step);
        purpose = (TextView)findViewById(R.id.intradermal_purpose);
        procedure = (TextView)findViewById(R.id.intradermal_procedure);

        for (String purpose_content : purpose_contents) {
            purpose.append(purpose_content + "\n");
        }
        for (String procedure_contents : procedure_contents) {
            procedure.append(procedure_contents + "\n");
        }

        id_position = (ImageView)findViewById(R.id.id_position);
        Glide.with(this).load(R.drawable.id_position).into(id_position);

        id_15 = (ImageView)findViewById(R.id.id_15);
        Glide.with(this).load(R.drawable.id_15).into(id_15);

        id_ex = (ImageView)findViewById(R.id.id_ex);
        Glide.with(this).load(R.drawable.id_ex).into(id_ex);


    }
}
