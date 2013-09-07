package com.cocacola.climateambassador.ui.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cocacola.climateambassador.CaConstants;
import com.cocacola.climateambassador.DocumentViewerDelegate;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.models.BulletPointFrame;
import com.cocacola.climateambassador.models.Document;
import com.cocacola.climateambassador.models.FileType;
import com.cocacola.climateambassador.models.Module;

import javax.inject.Inject;

/**
 * Created by Vinnie on 9/5/13.
 */
public class ModuleOneActivity extends CaModuleActivity {

    @Inject
    DocumentViewerDelegate mDocumentViewerDelegate;

    @Override
    public String getJsonAssetFilename() {
        return "module_one.json";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        Module module = getModule();
        LayoutInflater inflater = getLayoutInflater();


        if(!TextUtils.isEmpty(module.getTitle())) {
            ((TextView)findViewById(R.id.title)).setText(module.getTitle());
        }

        if(!TextUtils.isEmpty(module.getBodyText())) {
            ((TextView)findViewById(R.id.description)).setText(module.getBodyText());
        }

        LinearLayout caseFrames = (LinearLayout) findViewById(R.id.case_frames);
        if (module.getBulletPointFrame() != null) {
            BulletPointFrame caseBulletPointFrame = module.getBulletPointFrame();
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
        if (module.getCourseMaterials() != null && courseMaterialFrame != null) {

            for (Document courseMaterial : module.getCourseMaterials()) {
                View materialOption = inflater.inflate(R.layout.favorite_divider_button, null);
                setupButtonAccordingToDocument(courseMaterial, materialOption, inflater);
                courseMaterialFrame.addView(materialOption);
            }

            courseMaterialFrame.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setupButtonAccordingToDocument(final Document doc, View viewWithButton, LayoutInflater inflater) {

        if(doc != null) {
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
                    // FIXME Map file name String to a FileType enum type
                    mDocumentViewerDelegate.startActivityForFileType(ModuleOneActivity.this, FileType.PDF, "coca-cola-Business-Case-for-Good-Fertilizer-Use-in-Citrus.pdf");
                }
            });
        }
        else {
            Log.w("Document shouldn't be null");
        }


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
