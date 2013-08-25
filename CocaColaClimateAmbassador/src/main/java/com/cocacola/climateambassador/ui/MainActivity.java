package com.cocacola.climateambassador.ui;

import android.content.ActivityNotFoundException;
import android.os.Bundle;

import com.cocacola.climateambassador.AppPackageFileWriter;
import com.cocacola.climateambassador.DocumentViewerDelegate;
import com.cocacola.climateambassador.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.inject.Inject;

import butterknife.OnClick;

public class MainActivity extends CaActivity {

    @Inject DocumentViewerDelegate mDocumentManager;
    @Inject
    AppPackageFileWriter mAppPackageFileWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAppPackageFileWriter.writeAssetsToPackageDir();

        File filesDir = getFilesDir();
        String[] list = filesDir.list();

        Log.i("list.length=%s", list.length);

        for(String filename : list) {
            Log.i("filename=%s", filename);
        }

    }

    @OnClick(R.id.home_btn_internal)
    public void startSectionActivity() {
        try {
            mDocumentManager.startPdfViewerActivity("resume.pdf", this);
        } catch(ActivityNotFoundException e) {
            // TODO Launch an intent to download in Play Store
            Log.e(e, "No Activity for Viewing PDFs");
        }
    }
    
}
