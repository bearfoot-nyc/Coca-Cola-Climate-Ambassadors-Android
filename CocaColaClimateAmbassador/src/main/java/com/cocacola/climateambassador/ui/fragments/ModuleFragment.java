package com.cocacola.climateambassador.ui.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cocacola.climateambassador.CaConstants;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.models.BulletPointFrameJson;
import com.cocacola.climateambassador.models.DocumentJson;
import com.cocacola.climateambassador.models.ModuleJson;

/**
 * Created by Vinnie Vendemia on 9/2/13.
 */
public class ModuleFragment extends CaFragment {

    private ModuleJson mModule;

    public static ModuleFragment newInstance(ModuleJson aModule) {
        ModuleFragment fragment = new ModuleFragment();
        fragment.mModule = aModule;
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        //TODO: Very sloppy way to achieve this, will change later
        View view = null;
        if(mModule.getTitle().equals("Our 2020 Vision")) {
            view = inflater.inflate(R.layout.suppliers_last_page, container, false);
        } else {
            view = inflater.inflate(R.layout.activity_module, container, false);
        }
        if(!TextUtils.isEmpty(mModule.getTitle())) {
            ((TextView)view.findViewById(R.id.title)).setText(mModule.getTitle());
        }

        if(!TextUtils.isEmpty(mModule.getBodyText())) {
            ((TextView)view.findViewById(R.id.description)).setText(mModule.getBodyText());
        }

        LinearLayout caseFrames = (LinearLayout) view.findViewById(R.id.case_frames);
        if (mModule.getBulletPointFrame() != null) {
            BulletPointFrameJson caseBulletPointFrame = mModule.getBulletPointFrame();
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
                View bulletPointLayout = inflater.inflate(R.layout.depr_view_bullet_point, null);
                ((TextView) bulletPointLayout.findViewById(R.id.bullet_text)).setText(bulletPoint);
                bulletList.addView(bulletPointLayout);
            }

            bulletList.setVisibility(View.VISIBLE);
            caseFrames.addView(bulletPointFrame);
        }


        LinearLayout courseMaterialFrame = (LinearLayout) view.findViewById(R.id.course_materials);
        if (mModule.getCourseMaterials() != null && courseMaterialFrame != null) {

            for (DocumentJson courseMaterial : mModule.getCourseMaterials()) {
                View materialOption = inflater.inflate(R.layout.depr_favorite_divider_button, null);
                setupButtonAccordingToDocument(courseMaterial, materialOption, inflater);
                courseMaterialFrame.addView(materialOption);
            }

            courseMaterialFrame.setVisibility(View.VISIBLE);
        }


        return view;
    }


    private void setupButtonAccordingToDocument(final DocumentJson doc, View viewWithButton, LayoutInflater inflater) {
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
                Toast.makeText(getActivity(), "Touched " + doc.getLabel(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private String getFileType(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }

        return extension;
    }
}
