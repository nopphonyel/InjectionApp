package th.ac.kku.nu.injectionroom.customComponent;

import android.app.Application;

import th.ac.kku.nu.injectionroom.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Pikkanet on 19-Nov-17.
 */

public class ChangeFonts extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        iniFont();
    }

    private void iniFont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/waffleRegular.otf").setFontAttrId(R.attr.fontPath).build());
    }
}
