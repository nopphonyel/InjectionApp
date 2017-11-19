package th.ac.kku.nu.injectionroom.dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import th.ac.kku.nu.injectionroom.R;
import th.ac.kku.nu.injectionroom.Storage;

/**
 * Created by nopph on 11/19/2017.
 */

public class TaskDialog extends DialogFragment {

    static String taskKey;
    static Integer taskNum;

    public static TaskDialog newInstance(String taskKey , Integer taskNum) {

        TaskDialog.taskKey = taskKey;
        TaskDialog.taskNum = taskNum;

        Bundle args = new Bundle();
        TaskDialog fragment = new TaskDialog();
        fragment.setArguments(args);
        return fragment;
    }

    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity().getApplicationContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        try {
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }catch (NullPointerException e){
            Log.e("Confirm Remove Dialog" , "set no title FAILED!");
        }
        return inflater.inflate(R.layout.dialog_task , container, false);
    }

    Button okBtn;
    TextView taskContent;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        okBtn = (Button) view.findViewById(R.id.btn_ok);
        taskContent = (TextView) view.findViewById(R.id.dialog_content);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        taskContent.setText(Storage.Task.taskListResource.get(TaskDialog.taskKey).get(TaskDialog.taskNum));
    }
}
