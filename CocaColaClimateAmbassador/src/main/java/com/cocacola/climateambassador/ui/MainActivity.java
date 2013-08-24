package com.cocacola.climateambassador.ui;

import android.content.ActivityNotFoundException;
import android.os.Bundle;

import com.cocacola.climateambassador.DocumentViewerDelegate;
import com.cocacola.climateambassador.R;

import javax.inject.Inject;

import timber.log.Timber;

public class MainActivity extends CaActivity {

    @Inject DocumentViewerDelegate mDocumentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void startDocActivity() {
        try {
//            mDocumentManager.startPdfViewerActivity("wizards-ticket.pdf", this);
        } catch(ActivityNotFoundException e) {
            // TODO Launch an intent to download in Play Store
            Log.e(e, "No Activity for Viewing PDFs");
        }
    }
    
}
