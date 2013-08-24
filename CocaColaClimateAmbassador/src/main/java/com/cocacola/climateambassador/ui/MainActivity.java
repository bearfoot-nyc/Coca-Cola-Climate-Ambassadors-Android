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

    }

    public void writeFile() {

        String path = getApplicationContext().getFilesDir() + "/testDir/";
        File file = new File(path);
        file.mkdirs();
        path += "testlab.txt";
        OutputStream myOutput;
        try {
            myOutput = new BufferedOutputStream(new FileOutputStream(path,true));
//            write(myOutput, new String("TEST").getBytes());
            myOutput.flush();
            myOutput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.home_btn_internal)
    public void startSectionActivity() {
        try {
            mDocumentManager.startPdfViewerActivity("pdf/resume.pdf", this);
        } catch(ActivityNotFoundException e) {
            // TODO Launch an intent to download in Play Store
            Log.e(e, "No Activity for Viewing PDFs");
        }
    }
    
}
