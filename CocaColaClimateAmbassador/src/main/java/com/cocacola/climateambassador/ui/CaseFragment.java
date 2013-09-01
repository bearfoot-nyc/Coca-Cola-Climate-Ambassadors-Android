package com.cocacola.climateambassador.ui;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.models.BulletPointFrame;
import com.cocacola.climateambassador.models.Case;
import com.cocacola.climateambassador.models.Document;
import com.cocacola.climateambassador.models.SubtitleTextPair;
import com.cocacola.climateambassador.models.TextFrame;

/**
 * Created by Vinnie Vendemia on 8/29/13.
 */

public class CaseFragment extends CaFragment {

    private Case mCase;

    public static CaseFragment newInstance(Case aCase) {
        CaseFragment fragment = new CaseFragment();
        fragment.mCase = aCase;
        return fragment;
      }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Context context = getActivity();
        View view = inflater.inflate(R.layout.case_fragment, container, false);
        ((TextView)view.findViewById(R.id.case_title)).setText(mCase.getTitle());

        if(mCase.getBodyText() != null) {
               ((TextView)view.findViewById(R.id.body_text)).setText(mCase.getBodyText());
        }

        LinearLayout caseFrames = (LinearLayout)view.findViewById(R.id.case_frames);

        if(mCase.getBulletPointFrame() != null) {
            BulletPointFrame caseBulletPointFrame = mCase.getBulletPointFrame();
            View bulletPointFrame = inflater.inflate(R.layout.case_frame, null);
            ((TextView)bulletPointFrame.findViewById(R.id.case_frame_title)).setText(caseBulletPointFrame.getTitle());
            ((TextView)bulletPointFrame.findViewById(R.id.case_frame_subtitle)).setText(caseBulletPointFrame.getSubtitle());
            ((TextView)bulletPointFrame.findViewById(R.id.case_frame_subtitle)).setVisibility(View.VISIBLE);
            //Bullet List Base Layout
            LinearLayout bulletList = (LinearLayout)(bulletPointFrame.findViewById(R.id.bullet_list_content));

            //Add Bullet Points to Layout
            for(String bulletPoint: caseBulletPointFrame.getBulletPoints() ) {
                View bulletPointLayout = inflater.inflate(R.layout.case_frame_bullet_point, null);
                ((TextView)bulletPointLayout.findViewById(R.id.bullet_text)).setText(bulletPoint);
                bulletList.addView(bulletPointLayout);
            }

            bulletList.setVisibility(View.VISIBLE);
            caseFrames.addView(bulletPointFrame);
        }

        if(mCase.getTextFrames() != null) {

            for( TextFrame currTextFrame : mCase.getTextFrames() ) {
                View textFrame = inflater.inflate(R.layout.case_frame, null);
                ((TextView)textFrame.findViewById(R.id.case_frame_title)).setText(currTextFrame.getTitle());

                if(!TextUtils.isEmpty(currTextFrame.getBodyText())) {
                    ((TextView)textFrame.findViewById(R.id.case_frame_body_text)).setText(currTextFrame.getBodyText());
                    ((TextView)textFrame.findViewById(R.id.case_frame_body_text)).setVisibility(View.VISIBLE);
                }
                //TextFrame list base layout
                LinearLayout textFrames = (LinearLayout)textFrame.findViewById(R.id.case_frame_body);

                for(SubtitleTextPair subTextPair : currTextFrame.getSubtitleTextPairs()){
                    View subtitleTextPairView = inflater.inflate(R.layout.case_text_frame, null);
                    ((TextView)subtitleTextPairView.findViewById(R.id.title)).setText(subTextPair.getTitle());
//                    addBodyText(((TextView)subtitleTextPairView.findViewById(R.id.body_text)), subTextPair.getText
                   ((TextView)subtitleTextPairView.findViewById(R.id.body_text)).setText(subTextPair.getText());
                    textFrames.addView(subtitleTextPairView);
                }

                textFrames.setVisibility(View.VISIBLE);
                caseFrames.addView(textFrame);
            }
        }


        LinearLayout courseMaterialFrame = (LinearLayout)view.findViewById(R.id.course_materials);
        if(mCase.getCourseMaterials() != null) {

            for(Document courseMaterial : mCase.getCourseMaterials()){
                View materialOption = inflater.inflate(R.layout.favorite_divider_button, null);
                setupButtonAccordingToDocument(courseMaterial, materialOption , inflater);
                courseMaterialFrame.addView(materialOption);
            }

            courseMaterialFrame.setVisibility(View.VISIBLE);
        }

        LinearLayout caseStudyFrame = (LinearLayout)view.findViewById(R.id.case_studies);
        if(mCase.getCaseStudies() != null) {

            for(Document caseStudy : mCase.getCaseStudies()){
                View studyOption = inflater.inflate(R.layout.favorite_divider_button, null);
                setupButtonAccordingToDocument(caseStudy, studyOption, inflater);
                caseStudyFrame.addView(studyOption);
            }

            caseStudyFrame.setVisibility(View.VISIBLE);
        }

        return view;
    }

//    private void addBodyText(TextView view, String text) {
//        StringBuilder sb = new StringBuilder(text);
//
//        int i = 0;
//        while ((i = sb.indexOf(" ", i + 660)) != -1) {
//            sb.replace(i, i + 1, "\n\n");
//        }
//        view.setText(sb.toString());
//    }

    private void setupButtonAccordingToDocument(Document doc, View viewWithButton, LayoutInflater inflater) {
        //TODO: Set button background according to doc type and title
        if("ppt".equals(getFileType(doc.getFileName()))) {
            View background = inflater.inflate(R.layout.button_background, null);
//            ((ImageView)background.findViewById(R.id.doc_type)).setImageResource();
            ((TextView)background.findViewById(R.id.doc_title)).setText(doc.getLabel());
            background.setDrawingCacheEnabled(true);
            background.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            background.layout(0, 0, background.getMeasuredWidth(), background.getMeasuredHeight());

            background.buildDrawingCache(true);
            Drawable draw = new BitmapDrawable(getActivity().getResources(), background.getDrawingCache());
            background.setDrawingCacheEnabled(false); // clear drawing cache

            ((Button)viewWithButton.findViewById(R.id.button)).setBackgroundDrawable(draw);

        } else if("pdf".equals(getFileType(doc.getFileName()))) {

            View background = inflater.inflate(R.layout.button_background, null);
//            ((ImageView)background.findViewById(R.id.doc_type)).setImageResource();
            String label = doc.getLabel();
            TextView text = ((TextView)background.findViewById(R.id.doc_title));
            ((TextView)background.findViewById(R.id.doc_title)).setText(doc.getLabel());
            background.setDrawingCacheEnabled(true);
            background.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            background.layout(0, 0, background.getMeasuredWidth(), background.getMeasuredHeight());

            background.buildDrawingCache(true);
            Drawable draw = new BitmapDrawable(getActivity().getResources(), background.getDrawingCache());
            background.setDrawingCacheEnabled(false); // clear drawing cache

            ((Button)viewWithButton.findViewById(R.id.button)).setBackgroundDrawable(draw);

        } else if("doc".equals(getFileType(doc.getFileName()))) {

            View background = inflater.inflate(R.layout.button_background, null);
//            ((ImageView)background.findViewById(R.id.doc_type)).setImageResource();
            ((TextView)background.findViewById(R.id.doc_title)).setText(doc.getLabel());
            background.setDrawingCacheEnabled(true);
            background.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            background.layout(0, 0, background.getMeasuredWidth(), background.getMeasuredHeight());

            background.buildDrawingCache(true);
            Drawable draw = new BitmapDrawable(getActivity().getResources(), background.getDrawingCache());
            background.setDrawingCacheEnabled(false); // clear drawing cache

            ((Button)viewWithButton.findViewById(R.id.button)).setBackgroundDrawable(draw);

        } else {
            //Do nothing
        }
    }

    private String getFileType(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i+1);
        }

        return extension;
    }

}
