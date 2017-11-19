package th.ac.kku.nu.injectionroom.dialog;

import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by nopph on 11/19/2017.
 */

public class TaskDialog extends DialogFragment {
    public static TaskDialog newInstance() {
        Bundle args = new Bundle();

        TaskDialog fragment = new TaskDialog();
        fragment.setArguments(args);
        return fragment;
    }
}
