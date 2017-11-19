package th.ac.kku.nu.injectionroom.customComponent;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Pikkanet on 19-Nov-17.
 */

public class WaffleRegular extends android.support.v7.widget.AppCompatTextView {
    public WaffleRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"font/THSarabunNew.ttf"));
    }
}
