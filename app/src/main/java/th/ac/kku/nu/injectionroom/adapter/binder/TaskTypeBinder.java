package th.ac.kku.nu.injectionroom.adapter.binder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import th.ac.kku.nu.injectionroom.R;
import th.ac.kku.nu.injectionroom.Storage;
import th.ac.kku.nu.injectionroom.activity.game.TaskDetails;

/**
 * This class is used to bind Task list and their string resource into recycler view on each item
 * Created by nopph on 10/22/2017.
 */

public class TaskTypeBinder implements ViewBinder , View.OnClickListener{

    private View mainView;
    private ArrayList<String> keyList = Storage.Task.taskTypeKeyList;
    private HashMap<String , Integer> typeResource = Storage.Task.taskTypeResource;
    private HashMap<String , ArrayList<Integer>> listResouce = Storage.Task.taskListResource;

    private RelativeLayout taskTypeLayout;
    private RelativeLayout taskListLayout;
    private TextView title;

    private int currentPosition = 0;
    private String currentKey;
    private boolean isShowTaskList = false , isAppended = false;

    private Context mContext;

    public TaskTypeBinder(Context context , View view){
        mainView = view;
        title = view.findViewById(R.id.task_type_label);
        taskTypeLayout = view.findViewById(R.id.task_type_layout);
        taskTypeLayout.setOnClickListener(this);
        taskListLayout = view.findViewById(R.id.task_list);
        taskListLayout.setVisibility(View.GONE);
        mContext = context;
    }

    @Override
    public void setView(int position) {
        currentPosition = position;
        currentKey = keyList.get(position);
        if(!isAppended) updateView(currentKey);
    }

    private void updateView(String currentKey){
        title.setText(mContext.getString(typeResource.get(currentKey)));
        appendList(listResouce.get(currentKey));
    }

    Intent toTaskDetails;
    private void appendList(ArrayList<Integer> taskList){
        int size = taskList.size();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
        RelativeLayout eachTask;
        TextView taskDesc;
        toTaskDetails = new Intent(mContext , TaskDetails.class);
        toTaskDetails.putExtra("KEY" , currentKey);
        for(int i=0; i < size ; i++){
            eachTask = (RelativeLayout) inflater.inflate(R.layout.item_task_list , null);
            eachTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(toTaskDetails);
                }
            });
            taskDesc = (TextView) eachTask.getChildAt(1);
            taskDesc.setText(mContext.getString(taskList.get(i)));
            taskListLayout.addView(eachTask);
        }
        isAppended = true;
    }

    @Override
    public void onClick(View view) {
        if(view == taskTypeLayout){
            if(isShowTaskListContent()){
                isShowTaskList = false;
                taskListLayout.setVisibility(View.GONE);
            }else {
                isShowTaskList = true;
                taskListLayout.setVisibility(View.VISIBLE);
            }
            onClickShowMore.startAnimation(currentPosition);
        }
    }

    private OnClickShowMore onClickShowMore;
    public void setOnClickShowMore(OnClickShowMore onClickShowMore){
        this.onClickShowMore = onClickShowMore;
    }

    public boolean isShowTaskListContent(){
        return isShowTaskList;
    }

    public interface OnClickShowMore{
        void startAnimation(int position);
    }

}
