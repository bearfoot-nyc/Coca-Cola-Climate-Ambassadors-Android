package com.cocacola.climateambassador.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cocacola.climateambassador.AppPackageFileWriter;
import com.cocacola.climateambassador.CaApplication;
import com.cocacola.climateambassador.DocumentViewerDelegate;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.models.Document;
import com.cocacola.climateambassador.models.FileType;

import javax.inject.Inject;

/**
 * Created by andrewlawton on 9/7/13.
 */
public class DocumentView extends LinearLayout {

    private ImageView mImageView;
    private TextView mTitleView;

    @Inject
    DocumentViewerDelegate mDocumentViewerDelegate;

    public DocumentView(Context context) {
        super(context);
        init();
    }

    public DocumentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DocumentView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        CaApplication.getObjectGraph(getContext().getApplicationContext()).inject(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mImageView = (ImageView) findViewById(R.id.doc_image);
        mTitleView = (TextView) findViewById(R.id.doc_title);
    }

    public void setDocument(Document doc) {

        // TODO Choose appropriate icon resource
        mImageView.setImageResource(R.drawable.ic_doc_pdf);

        if(doc.getLabel() != null) {
            mTitleView.setText(doc.getLabel());
        }

        setOnClickListener(new OnDocumentClickListener(doc));

    }

    private class OnDocumentClickListener implements OnClickListener {

        private Document document;

        public OnDocumentClickListener(Document document) {
            this.document = document;
        }

        @Override
        public void onClick(View v) {
            try {
                mDocumentViewerDelegate.startActivityForFileType(getContext(), FileType.PDF, "coca-cola-Business-Case-for-Good-Fertilizer-Use-in-Citrus.pdf");
            } catch (AppPackageFileWriter.FailedToWriteToPackageException e) {
                // TODO Do we really want to Toast?
                Toast.makeText(getContext(), "Failed to write to pacakge: " + e.getFileName(), Toast.LENGTH_LONG);
            }
        }
    };

}
