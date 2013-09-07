package com.cocacola.climateambassador.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.models.Document;

import java.util.List;

/**
 * Created by andrewlawton on 9/7/13.
 */
public class CourseMaterialsLayout extends LinearLayout {
    public CourseMaterialsLayout(Context context) {
        super(context);
    }

    public CourseMaterialsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CourseMaterialsLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setDocuments(List<Document> documents) {

        if(documents == null) {
            setVisibility(View.GONE);
            return;
        }

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for(Document doc : documents) {

            DocumentView docView = (DocumentView) inflater.inflate(R.layout.view_document, null);
            docView.setDocument(doc);
            addView(docView);

        }

    }


}
