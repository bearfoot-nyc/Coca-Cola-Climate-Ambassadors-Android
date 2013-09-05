package com.cocacola.climateambassador.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cocacola.climateambassador.CaConstants;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.models.Document;
import com.cocacola.climateambassador.models.Favorites;

/**
 * Created by Vinnie Vendemia on 9/3/13.
 */
public class FavoritesFragment extends CaFragment {


    Favorites mFavorites;

    public static FavoritesFragment newInstance(Favorites favorites) {
        FavoritesFragment fragment = new FavoritesFragment();
        fragment.mFavorites = favorites;
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.frag_favorites, container, false);

        LinearLayout courseMaterialFrame = (LinearLayout) view.findViewById(R.id.course_materials);
        if (mFavorites.getCourseMaterials() != null) {

            for (Document courseMaterial : mFavorites.getCourseMaterials()) {
                View materialOption = inflater.inflate(R.layout.favorite_divider_button, null);
                setupButtonAccordingToDocument(courseMaterial, materialOption, inflater);
                courseMaterialFrame.addView(materialOption);
            }

            courseMaterialFrame.setVisibility(View.VISIBLE);
        }

        LinearLayout caseStudyFrame = (LinearLayout) view.findViewById(R.id.case_studies);
        if (mFavorites.getCaseStudies() != null) {

            for (Document caseStudy : mFavorites.getCaseStudies()) {
                View studyOption = inflater.inflate(R.layout.favorite_divider_button, null);
                setupButtonAccordingToDocument(caseStudy, studyOption, inflater);
                caseStudyFrame.addView(studyOption);
            }

            caseStudyFrame.setVisibility(View.VISIBLE);
        }

        return view;

    }

    private void setupButtonAccordingToDocument(final Document doc, View viewWithButton, LayoutInflater inflater) {
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
