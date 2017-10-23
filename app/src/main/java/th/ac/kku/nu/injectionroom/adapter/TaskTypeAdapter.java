package th.ac.kku.nu.injectionroom.adapter;

import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import th.ac.kku.nu.injectionroom.R;
import th.ac.kku.nu.injectionroom.Storage;
import th.ac.kku.nu.injectionroom.adapter.binder.TaskTypeBinder;

/**
 * Created by nopph on 10/22/2017.
 */

public class TaskTypeAdapter extends RecyclerView.Adapter<TaskTypeAdapter.TaskTypeViewHolder>{

    private View view;

    public TaskTypeAdapter(){
    }

    ViewGroup currentViewGroup;

    @Override
    public TaskTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_type , parent , false);
        currentViewGroup = parent;
        return new TaskTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskTypeViewHolder holder, int position) {
        holder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return Storage.Task.taskTypeKeyList.size();
    }



    class TaskTypeViewHolder extends RecyclerView.ViewHolder implements TaskTypeBinder.OnClickShowMore{
        TaskTypeBinder taskTypeBinder;
        int currentPosition = 0;
        private TaskTypeViewHolder(View itemView)
        {
            super(itemView);
            taskTypeBinder = new TaskTypeBinder(currentViewGroup.getContext() , itemView);
        }

        private void setPosition(int pos){
            currentPosition = pos;
            taskTypeBinder.setView(currentPosition);
            taskTypeBinder.setOnClickShowMore(this);
        }

        @Override
        public void startAnimation(int position) {
            TransitionManager.beginDelayedTransition(currentViewGroup);
            notifyDataSetChanged();
        }
    }
}
