package com.cocacola.climateambassador.ui.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.models.BulletPointFrame;
import com.cocacola.climateambassador.models.Case;
import com.cocacola.climateambassador.models.SubtitleTextPair;
import com.cocacola.climateambassador.models.TextFrame;

/**
 * Created by Vinnie on 9/4/13.
 */
public class IngredientCaseActivity extends CaCaseActivity {

    @Override
    public String getJsonAssetFilename() {
        return "case_ingredients.json";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_case);

        Case aCase = getModel();

        findViewById(R.id.scroll_view).setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_case_ingredients));
        ((ImageView)findViewById(R.id.case_logo)).setImageResource(R.drawable.ic_case_detail_ingredients);

        LayoutInflater inflater = getLayoutInflater();

        ((TextView) findViewById(R.id.case_title)).setText(aCase.getTitle());

        if (!TextUtils.isEmpty(aCase.getBodyText())) {
            ((TextView) findViewById(R.id.body_text)).setText(aCase.getBodyText());
            ((TextView) findViewById(R.id.body_text)).setVisibility(View.VISIBLE);
        }

        LinearLayout caseFrames = (LinearLayout) findViewById(R.id.case_frames);

        if (aCase.getBulletPointFrame() != null) {
            BulletPointFrame caseBulletPointFrame = aCase.getBulletPointFrame();
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

        if (aCase.getTextFrames() != null) {

            for (TextFrame currTextFrame : aCase.getTextFrames()) {
                View textFrame = inflater.inflate(R.layout.case_frame, null);

                if (!TextUtils.isEmpty(currTextFrame.getTitle())) {
                    ((TextView) textFrame.findViewById(R.id.case_frame_title)).setText(currTextFrame.getTitle());
                    ((TextView) textFrame.findViewById(R.id.case_frame_title)).setVisibility(View.VISIBLE);
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
                    ((TextView) subtitleTextPairView.findViewById(R.id.body_text)).setText(subTextPair.getText());
                    textFrames.addView(subtitleTextPairView);
                }

                textFrames.setVisibility(View.VISIBLE);
                caseFrames.addView(textFrame);
            }
        }

        showDocumentLayouts(aCase);


//        LinearLayout courseMaterialFrame = (LinearLayout) findViewById(R.id.course_materials);
//        if (aCase.getCourseMaterials() != null) {
//
//            for (Document courseMaterial : aCase.getCourseMaterials()) {
//
//                View materialOption = inflater.inflate(R.layout.depr_favorite_divider_button, null);
//                setupButtonAccordingToDocument(courseMaterial, materialOption, inflater);
//                courseMaterialFrame.addView(materialOption);
//            }
//
//            courseMaterialFrame.setVisibility(View.VISIBLE);
//        }
//
//        LinearLayout caseStudyFrame = (LinearLayout) findViewById(R.id.case_studies);
//        if (aCase.getCaseStudies() != null) {
//
//            for (Document caseStudy : aCase.getCaseStudies()) {
//                View studyOption = inflater.inflate(R.layout.depr_favorite_divider_button, null);
//                setupButtonAccordingToDocument(caseStudy, studyOption, inflater);
//                caseStudyFrame.addView(studyOption);
//            }
//
//            caseStudyFrame.setVisibility(View.VISIBLE);
//        }

    }

}
