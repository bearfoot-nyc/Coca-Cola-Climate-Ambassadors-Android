package com.cocacola.climateambassador.ui.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cocacola.climateambassador.CaConstants;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.models.BulletPointFrame;
import com.cocacola.climateambassador.models.Document;
import com.cocacola.climateambassador.models.Module;
import com.cocacola.climateambassador.util.JsonAssetsLoader;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by Vinnie on 9/5/13.
 */
public class ModuleTwoActivity extends CaModuleActivity {

    private JsonAssetsLoader mJsonAssetsLoader;
    private Module mModule;
    LayoutInflater inflater;
    @Inject
    Gson gson;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_module);
        getAssetLoader();
        getModule();
        inflater = getLayoutInflater();

        //TODO: Very sloppy way to achieve this, will change later

        if(!TextUtils.isEmpty(mModule.getTitle())) {
            ((TextView)findViewById(R.id.title)).setText(mModule.getTitle());
        }

        if(!TextUtils.isEmpty(mModule.getBodyText())) {
            ((TextView)findViewById(R.id.description)).setText(mModule.getBodyText());
        }

        LinearLayout caseFrames = (LinearLayout) findViewById(R.id.case_frames);
        if (mModule.getBulletPointFrame() != null) {
            BulletPointFrame caseBulletPointFrame = mModule.getBulletPointFrame();
            View bulletPointFrame = inflater.inflate(R.layout.case_frame, null);

            if (!TextUtils.isEmpty(caseBulletPointFrame.getTitle())) {
                ((TextView) bulletPointFrame.findViewById(R.id.case_frame_title)).setText(caseBulletPointFrame.getTitle());
                ((TextView) bulletPointFrame.findViewById(R.id.case_frame_title)).setTextColor(getResources().getColor(R.color.black));
                ((TextView) bulletPointFrame.findViewById(R.id.case_frame_title)).setVisibility(View.VISIBLE);
            }

            if (!TextUtils.isEmpty(caseBulletPointFrame.getSubtitle())) {
                ((TextView) bulletPointFrame.findViewById(R.id.case_frame_subtitle)).setText(caseBulletPointFrame.getSubtitle());
                ((TextView) bulletPointFrame.findViewById(R.id.case_frame_subtitle)).setVisibility(View.VISIBLE);
            }

            //Bullet List Base Layout
            LinearLayout bulletList = (LinearLayout) (bulletPointFrame.findViewById(R.id.bullet_list_content));

            //Add Bullet Points to Layout
            for (String bulletPoint : caseBulletPointFrame.getBulletPoints()) {
                View bulletPointLayout = inflater.inflate(R.layout.case_frame_bullet_point, null);
                ((TextView) bulletPointLayout.findViewById(R.id.bullet_text)).setText(bulletPoint);
                //Must set text color, base color is white (for cases)
                ((TextView) bulletPointLayout.findViewById(R.id.bullet_text)).setTextColor(getResources().getColor(R.color.black));
                bulletList.addView(bulletPointLayout);
            }

            bulletList.setVisibility(View.VISIBLE);
            caseFrames.addView(bulletPointFrame);
        }


        LinearLayout courseMaterialFrame = (LinearLayout) findViewById(R.id.course_materials);
        if (mModule.getCourseMaterials() != null && courseMaterialFrame != null) {

            for (Document courseMaterial : mModule.getCourseMaterials()) {
                View materialOption = inflater.inflate(R.layout.favorite_divider_button, null);
                setupButtonAccordingToDocument(courseMaterial, materialOption, inflater);
                courseMaterialFrame.addView(materialOption);
            }

            courseMaterialFrame.setVisibility(View.VISIBLE);
        }
    }

    public void getModule() {
        try {
            mModule = mJsonAssetsLoader.parseModuleFromJsonFile("module_two.json");
        } catch (IOException e) {
            Toast.makeText(this, "Failed to Get Module Two", Toast.LENGTH_SHORT);
        }
    }

    @Override
    public JsonAssetsLoader getAssetLoader() {
        mJsonAssetsLoader = new JsonAssetsLoader(this, gson );
        return mJsonAssetsLoader;
    }

    @Override
    public void setupButtonAccordingToDocument(final Document doc, View viewWithButton, LayoutInflater inflater) {
        //TODO: Set viewWithButton background according to doc type and title
        if (CaConstants.PDF.equals(getFileType(doc.getFileName()))) {
            //TODO: Set image to resource once assets are added
//            ((ImageView)viewWithButton.findViewById(R.id.doc_type)).setImageResource();
        } else if (CaConstants.PPT.equals(getFileType(doc.getFileName()))) {
            //TODO: Set image to resource once assets are added
//            ((ImageView)viewWithButton.findViewById(R.id.doc_type)).setImageResource();
        } else if (CaConstants.DOC.equals(getFileType(doc.getFileName()))) {
            //TODO: Set image to resource once assets are added
//            ((ImageView)viewWithButton.findViewById(R.id.doc_type)).setImageResource();
        } else {
            //Do nothing
        }

        ((TextView) viewWithButton.findViewById(R.id.doc_title)).setText(doc.getLabel());
        ((LinearLayout) viewWithButton.findViewById(R.id.document_opener_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Open Document
            }
        });


    }

    @Override
    public String getFileType(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }

        return extension;
    }
}
