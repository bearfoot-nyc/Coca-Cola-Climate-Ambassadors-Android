package com.cocacola.climateambassador.core.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.core.HasController;
import com.cocacola.climateambassador.core.controller.DocumentController;
import com.cocacola.climateambassador.core.util.HasModel;
import com.cocacola.climateambassador.data.Document;
import java.util.List;

/**
 * Created by andrewlawton on 9/7/13.
 */
public class DocumentsLayout extends LinearLayout implements HasController<DocumentController>,
    HasModel<List<Document>> {

    private DocumentController mController;

    public DocumentsLayout(Context context) {
        super(context);
    }

    public DocumentsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DocumentsLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setController(DocumentController controller) {
        mController = controller;
    }

    @Override public void setModel(List<Document> documents) {

        if(documents == null) {
            setVisibility(View.GONE);
            return;
        }

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for(Document doc : documents) {

            DocumentView docView = (DocumentView) inflater.inflate(R.layout.view_document, this, false);
            docView.setDocument(doc);
            docView.setController(mController);
            addView(docView);

        }

        invalidate();
        requestLayout();


    }
}
