package th.ac.kku.nu.injectionroom.activity.game;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import th.ac.kku.nu.injectionroom.R;
import th.ac.kku.nu.injectionroom.Storage;
import th.ac.kku.nu.injectionroom.adapter.TaskTypeAdapter;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SelectTask extends AppCompatActivity {
    RecyclerView taskTypeList;
    TaskTypeAdapter taskTypeAdapter;
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_task);

        if(Storage.Task.taskTypeKeyList.size() == 0) {
            Storage.Task.implementTask();
        }

        backBtn = (ImageView) findViewById(R.id.ic_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        taskTypeList = (RecyclerView) findViewById(R.id.task_type_list);
        taskTypeAdapter = new TaskTypeAdapter();
        taskTypeList.setAdapter(taskTypeAdapter);
        taskTypeList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }
}
