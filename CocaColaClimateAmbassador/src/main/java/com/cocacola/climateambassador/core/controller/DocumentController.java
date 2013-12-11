package com.cocacola.climateambassador.core.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import com.cocacola.climateambassador.core.CaApplication;
import com.cocacola.climateambassador.core.activity.CaActivity;
import com.cocacola.climateambassador.core.fragment.PreferredAppDialogFragment;
import com.cocacola.climateambassador.core.util.AppPackageFileWriter;
import com.cocacola.climateambassador.core.util.DocumentIntentBuilder;
import com.cocacola.climateambassador.core.util.DocumentUriBuilder;
import com.cocacola.climateambassador.core.util.Toaster;
import com.cocacola.climateambassador.data.Document;
import javax.inject.Inject;

/**
 * Created by realandylawton on 12/1/13.
 */
public class DocumentController extends CaController {

    @Inject protected DocumentIntentBuilder mDocumentIntentBuilder;

    public DocumentController(Activity activity) {
        super(activity);
    }

    public void startActivityForDocument(Document doc) {

        try {
            Intent intent = mDocumentIntentBuilder.createViewerIntent(mActivity, doc);
            mActivity.startActivity(intent);
        } catch (DocumentIntentBuilder.PreferredAppNotInstalledException e) {
            showPreferredAppDialog();
        } catch (AppPackageFileWriter.PackageWriteException e) {
            Toaster.toast(mActivity, e.getMessage());
        }
    }

    private void showPreferredAppDialog() {

        PreferredAppDialogFragment dialogFragment = PreferredAppDialogFragment.newInstance();
        dialogFragment.show(mActivity.getFragmentManager(), "preferredApp");

    }
}
