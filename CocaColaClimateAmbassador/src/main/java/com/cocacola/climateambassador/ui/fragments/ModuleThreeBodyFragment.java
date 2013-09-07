package com.cocacola.climateambassador.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cocacola.climateambassador.CaConstants;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.models.Document;

/**
 * Created by realandylawton on 9/4/13.
 */
public class ModuleThreeBodyFragment extends CaFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_module_three_body, container, true);

        LinearLayout courseMaterialFrame = (LinearLayout) v.findViewById(R.id.course_materials);
        for (int i = 0; i < 3; i++) {
            View materialOption = inflater.inflate(R.layout.depr_favorite_divider_button, null);
            switch (i) {
                case 0:
                    Document ppt = new Document("unknown.ppt" , getResources().getString(R.string.value_chain_label), "ppt");
                    setupButtonAccordingToDocument(ppt, materialOption, inflater);
                    break;
                case 1:
                    Document mov = new Document("unknown.mov" , "Life of a Bottle", "mov");
                    setupButtonAccordingToDocument(mov, materialOption, inflater);
                    break;
                case 2:
                    Document doc = new Document("unknown.doc" , "PowerPoint Script and Notes", "doc");
                    setupButtonAccordingToDocument(doc, materialOption, inflater);
                    break;
                default:
                    //Do Nothing
                    break;

            }
            courseMaterialFrame.addView(materialOption);

            courseMaterialFrame.setVisibility(View.VISIBLE);
        }

        return v;
    }

    public void setupButtonAccordingToDocument(final Document doc, View viewWithButton, LayoutInflater inflater) {
        if (CaConstants.PDF.equals(getFileType(doc.getFileName()))) {
            ((ImageView)viewWithButton.findViewById(R.id.doc_type)).setImageResource(R.drawable.ic_doc_pdf);
        } else if (CaConstants.PPT.equals(getFileType(doc.getFileName()))) {
            ((ImageView)viewWithButton.findViewById(R.id.doc_type)).setImageResource(R.drawable.ic_doc_ppt);
        } else if (CaConstants.DOC.equals(getFileType(doc.getFileName()))) {
            ((ImageView)viewWithButton.findViewById(R.id.doc_type)).setImageResource(R.drawable.ic_doc_doc);
        } else if (CaConstants.MOV.equals(getFileType(doc.getFileName()))) {
            ((ImageView)viewWithButton.findViewById(R.id.doc_type)).setImageResource(R.drawable.ic_doc_doc);
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


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
