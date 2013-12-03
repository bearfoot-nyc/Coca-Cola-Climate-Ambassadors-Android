package com.cocacola.climateambassador.core.controller;

import android.app.Activity;
import android.content.Intent;
import com.cocacola.climateambassador.core.CaApplication;
import com.cocacola.climateambassador.core.activity.CaActivity;
import com.cocacola.climateambassador.core.util.AppPackageFileWriter;
import com.cocacola.climateambassador.core.util.DocumentIntentBuilder;
import com.cocacola.climateambassador.core.util.DocumentUriBuilder;
import com.cocacola.climateambassador.core.util.Toaster;
import com.cocacola.climateambassador.data.Document;
import javax.inject.Inject;

/**
 * Created by realandylawton on 12/1/13.
 */
public class DocumentController {

    @Inject protected DocumentIntentBuilder mDocumentIntentBuilder;

    private Activity mActivity;

    public DocumentController(Activity activity) {
        mActivity = activity;
        CaApplication.getObjectGraph(mActivity).inject(this);
    }

    public void startActivityForDocument(Document doc) {

        try {
            Intent intent = mDocumentIntentBuilder.createViewerIntent(mActivity, doc);
            mActivity.startActivity(intent);
        } catch (DocumentIntentBuilder.PreferredAppNotInstalledException e) {
            Toaster.toast(mActivity, e.getMessage());
        } catch (AppPackageFileWriter.PackageWriteException e) {
            Toaster.toast(mActivity, e.getMessage());
        }
    }
}
