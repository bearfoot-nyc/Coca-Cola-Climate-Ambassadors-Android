package com.cocacola.climateambassador.core.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.core.util.PreferredAppHelper;

/**
 * Created by realandylawton on 12/1/13.
 */
public class PreferredAppDialogFragment extends DialogFragment {

    public static PreferredAppDialogFragment newInstance() {

        PreferredAppDialogFragment fragment = new PreferredAppDialogFragment();

        return fragment;

    }

    @Override public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new AlertDialog.Builder(getActivity())
            .setTitle(R.string.preferred_app_dialog_title)
            .setMessage(R.string.preferred_app_dialog_message)
            .setPositiveButton(R.string.preferred_app_dialog_download, new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialog, int which) {
                    onDownloadClick();
                }
            })
            .setNegativeButton(R.string.preferred_app_dialog_cancel, new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialog, int which) {
                    onCancelClick();
                }
            })
            .create();

    }

    private void onCancelClick() {
        dismiss();
    }

    private void onDownloadClick() {

        PreferredAppHelper.launchPreferredAppInPlayStore(getActivity());

    }
}
