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

import th.ac.kku.nu.injectionroom.R;

/**
 * Created by nopph on 10/24/2017.
 */

public class SyringeAdjustorDialog extends DialogFragment {
    public static SyringeAdjustorDialog newInstance(){
        return  new SyringeAdjustorDialog();
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
        return inflater.inflate(R.layout.dialog_adjust_drug , container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
