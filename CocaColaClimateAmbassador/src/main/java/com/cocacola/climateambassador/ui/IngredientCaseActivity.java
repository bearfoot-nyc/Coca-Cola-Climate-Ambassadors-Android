package com.cocacola.climateambassador.ui;

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
import com.cocacola.climateambassador.models.Case;
import com.cocacola.climateambassador.models.Document;
import com.cocacola.climateambassador.models.SubtitleTextPair;
import com.cocacola.climateambassador.models.TextFrame;
import com.cocacola.climateambassador.util.JsonAssetsLoader;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by Vinnie on 9/4/13.
 */
public class IngredientCaseActivity extends CaCaseActivity {

    Case mCase;
    JsonAssetsLoader mJsonAssetsLoader;
    @Inject
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAssetLoader();
        getCase();
        setContentView(R.layout.case_fragment);
        findViewById(R.id.main_content).setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_case_ingredients));

        LayoutInflater inflater = getLayoutInflater();

        ((TextView) findViewById(R.id.case_title)).setText(mCase.getTitle());

        if (!TextUtils.isEmpty(mCase.getBodyText())) {
            ((TextView) findViewById(R.id.body_text)).setText(mCase.getBodyText());
            ((TextView) findViewById(R.id.body_text)).setVisibility(View.VISIBLE);
        }

        LinearLayout caseFrames = (LinearLayout) findViewById(R.id.case_frames);

        if (mCase.getBulletPointFrame() != null) {
            BulletPointFrame caseBulletPointFrame = mCase.getBulletPointFrame();
            View bulletPointFrame = inflater.inflate(R.layout.case_frame, null);

            if (!TextUtils.isEmpty(caseBulletPointFrame.getTitle())) {
                ((TextView) bulletPointFrame.findViewById(R.id.case_frame_title)).setText(caseBulletPointFrame.getTitle());
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
                bulletList.addView(bulletPointLayout);
            }

            bulletList.setVisibility(View.VISIBLE);
            caseFrames.addView(bulletPointFrame);
        }

        if (mCase.getTextFrames() != null) {

            for (TextFrame currTextFrame : mCase.getTextFrames()) {
                View textFrame = inflater.inflate(R.layout.case_frame, null);

                if (!TextUtils.isEmpty(currTextFrame.getTitle())) {
                    ((TextView) textFrame.findViewById(R.id.case_frame_title)).setText(currTextFrame.getTitle());
                }

                if (!TextUtils.isEmpty(currTextFrame.getBodyText())) {
                    ((TextView) textFrame.findViewById(R.id.case_frame_body_text)).setText(currTextFrame.getBodyText());
                    ((TextView) textFrame.findViewById(R.id.case_frame_body_text)).setVisibility(View.VISIBLE);
                }
                //TextFrame list base layout
                LinearLayout textFrames = (LinearLayout) textFrame.findViewById(R.id.case_frame_body);

                for (SubtitleTextPair subTextPair : currTextFrame.getSubtitleTextPairs()) {
                    View subtitleTextPairView = inflater.inflate(R.layout.case_text_frame, null);
                    ((TextView) subtitleTextPairView.findViewById(R.id.title)).setText(subTextPair.getTitle());
//                    addBodyText(((TextView)subtitleTextPairView.findViewById(R.id.body_text)), subTextPair.getText
                    ((TextView) subtitleTextPairView.findViewById(R.id.body_text)).setText(subTextPair.getText());
                    textFrames.addView(subtitleTextPairView);
                }

                textFrames.setVisibility(View.VISIBLE);
                caseFrames.addView(textFrame);
            }
        }


        LinearLayout courseMaterialFrame = (LinearLayout) findViewById(R.id.course_materials);
        if (mCase.getCourseMaterials() != null) {

            for (Document courseMaterial : mCase.getCourseMaterials()) {
                View materialOption = inflater.inflate(R.layout.favorite_divider_button, null);
                setupButtonAccordingToDocument(courseMaterial, materialOption, inflater);
                courseMaterialFrame.addView(materialOption);
            }

            courseMaterialFrame.setVisibility(View.VISIBLE);
        }

        LinearLayout caseStudyFrame = (LinearLayout) findViewById(R.id.case_studies);
        if (mCase.getCaseStudies() != null) {

            for (Document caseStudy : mCase.getCaseStudies()) {
                View studyOption = inflater.inflate(R.layout.favorite_divider_button, null);
                setupButtonAccordingToDocument(caseStudy, studyOption, inflater);
                caseStudyFrame.addView(studyOption);
            }

            caseStudyFrame.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void getCase() {
        try {
            mCase = mJsonAssetsLoader.parseCaseFromJsonFile("ingredients.json");
        } catch (IOException e) {
            Toast.makeText(this, "Failed to Get Packaging Case", Toast.LENGTH_SHORT);
        }
    }

    @Override
    public JsonAssetsLoader getAssetLoader() {
        mJsonAssetsLoader = new JsonAssetsLoader(this, gson );
        return mJsonAssetsLoader;
    }

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

    public String getFileType(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }

        return extension;
    }
}
