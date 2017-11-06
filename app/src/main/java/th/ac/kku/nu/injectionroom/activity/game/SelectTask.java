package th.ac.kku.nu.injectionroom.activity.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import th.ac.kku.nu.injectionroom.R;
import th.ac.kku.nu.injectionroom.Storage;
import th.ac.kku.nu.injectionroom.adapter.TaskTypeAdapter;

public class SelectTask extends AppCompatActivity {

    RecyclerView taskTypeList;
    TaskTypeAdapter taskTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_task);

        if(Storage.Task.taskTypeKeyList.size() == 0) {
            Storage.Task.implementTask();
        }
        taskTypeList = (RecyclerView) findViewById(R.id.task_type_list);
        taskTypeAdapter = new TaskTypeAdapter();
        taskTypeList.setAdapter(taskTypeAdapter);
        taskTypeList.setLayoutManager(new LinearLayoutManager(this));
    }
}
