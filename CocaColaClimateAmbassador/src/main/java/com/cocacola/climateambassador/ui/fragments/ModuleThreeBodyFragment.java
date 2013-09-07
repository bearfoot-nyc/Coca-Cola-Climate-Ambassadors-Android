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
import com.cocacola.climateambassador.ui.activities.CaActivity;
import com.cocacola.climateambassador.ui.views.CourseMaterialsLayout;

import java.util.ArrayList;
import java.util.List;

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

        List<Document> documentList = new ArrayList<Document>();
        documentList.add(new Document("unknown.ppt" , getResources().getString(R.string.value_chain_label), "ppt"));

        CourseMaterialsLayout courseMaterialsLayout = (CourseMaterialsLayout) v.findViewById(R.id.course_materials);
        courseMaterialsLayout.setDocuments(documentList);

        return v;
    }

}
