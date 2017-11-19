package th.ac.kku.nu.injectionroom.activity.game;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import th.ac.kku.nu.injectionroom.R;
import th.ac.kku.nu.injectionroom.Storage;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TaskDetails extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        initializeComponent();
        Storage.InjectionProcess.resetProcessMarks();
        getIntentData();
        setData();
    }

    TextView taskTitle , taskDesc;
    Button startBtn;
    private void initializeComponent(){
        taskTitle = (TextView) findViewById(R.id.task_type_title);
        taskDesc = (TextView) findViewById(R.id.task_desc);
        startBtn = (Button) findViewById(R.id.start_task);
        startBtn.setOnClickListener(this);
    }

    String currentKey;
    Integer currentTaskNumber;
    private void getIntentData(){
        Intent intent = getIntent();
        currentKey = intent.getStringExtra("KEY");
        currentTaskNumber = intent.getIntExtra("TASK_NUM",0);
    }

    private void setData(){
        taskTitle.setText(Storage.Task.taskTypeResource.get(currentKey));
        taskDesc.setText(Storage.Task.taskListResource.get(currentKey).get(currentTaskNumber));
    }

    @Override
    public void onClick(View view) {
        if(view == startBtn){
            setCurrentMode(currentKey , currentTaskNumber);
            startActivity(new Intent(this , SelectEquipment.class));
        }
    }

    private void setCurrentMode(String keys , Integer index){
        Storage.currentTaskTypeKey = keys;
        Storage.currentTaskNumber = index;
    }

    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }
}