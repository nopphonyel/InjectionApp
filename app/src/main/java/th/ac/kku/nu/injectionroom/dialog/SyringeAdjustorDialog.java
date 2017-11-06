package th.ac.kku.nu.injectionroom.dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import th.ac.kku.nu.injectionroom.R;
import th.ac.kku.nu.injectionroom.Storage;

/**
 * Created by nopph on 10/24/2017.
 */

public class SyringeAdjustorDialog extends DialogFragment implements View.OnClickListener {

    private static Storage.SYRINGE_TYPE thisSyringeType;

    public static SyringeAdjustorDialog newInstance(Storage.SYRINGE_TYPE syringeType){
        thisSyringeType = syringeType;
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

    TextView syringeValue;
    Button confirmSyringeVolumn;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        confirmSyringeVolumn = (Button) view.findViewById(R.id.confirm_btn);
        compatSeekBar = (AppCompatSeekBar) view.findViewById(R.id.syringeValue);
        syringeValue = (TextView) view.findViewById(R.id.syringe_value);
        addListener();
    }

    AppCompatSeekBar compatSeekBar;
    private void addListener(){
        if(thisSyringeType == Storage.SYRINGE_TYPE.THREE_CC) {
            compatSeekBar.setMax(300);
        }
        if(thisSyringeType == Storage.SYRINGE_TYPE.INSULIN_SYRINGE) {
            compatSeekBar.setMax(100);
        }
        confirmSyringeVolumn.setOnClickListener(this);
        compatSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Double currentML = progress/100.0;
                String currentProgress = currentML+" ml.";
                syringeValue.setText(currentProgress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public interface OnClickConfirmAdjustDialog{
        void onClick();
    }

    OnClickConfirmAdjustDialog onClickConfirmAdjustDialog;

    public void setOnClickConfirmAdjustDialog(OnClickConfirmAdjustDialog obj){
        onClickConfirmAdjustDialog = obj;
    }

    @Override
    public void onClick(View v) {
        if(v == confirmSyringeVolumn){
            onClickConfirmAdjustDialog.onClick();
        }
    }
}
