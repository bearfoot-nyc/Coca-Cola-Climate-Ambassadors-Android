package com.cocacola.climateambassador.core.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.core.CaApplication;
import com.cocacola.climateambassador.core.util.AppPackageFileWriter;
import com.cocacola.climateambassador.core.util.DocumentViewerDelegate;
import com.cocacola.climateambassador.core.util.Toaster;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.Document;
import com.cocacola.climateambassador.data.DocumentDao;
import com.cocacola.climateambassador.data.json.FileType;
import java.util.HashMap;
import javax.inject.Inject;

/**
 * Created by andrewlawton on 9/7/13.
 */
public class DocumentView extends LinearLayout {

    private static HashMap<FileType, Integer> sfileTypeResMap = new HashMap<FileType, Integer>();
    static {
        sfileTypeResMap.put(FileType.PDF, R.drawable.ic_doc_pdf);
        sfileTypeResMap.put(FileType.PPT, R.drawable.ic_doc_ppt);
        sfileTypeResMap.put(FileType.MP4, R.drawable.ic_doc_mov);
        sfileTypeResMap.put(FileType.THREEGP, R.drawable.ic_doc_mov);
    }

    @Inject protected DaoMaster mDaoMaster;
    @Inject protected DocumentViewerDelegate mDocumentViewerDelegate;

    private TextView mTitleView;
    private ImageView mFavoriteBtn;

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
        mTitleView = (TextView) findViewById(R.id.doc_title);
        mFavoriteBtn = (ImageView) findViewById(R.id.doc_favorite_btn);
    }

    public void setDocument(final Document doc) {

        if(doc == null) {
            return;
        }

        boolean isFavorite = (doc.getIsFavorite() != null) ? doc.getIsFavorite() : false;
        Integer iconRes = isFavorite ? R.drawable.favorite_selected : R.drawable.favorite_unselected;

        mFavoriteBtn.setImageResource(iconRes);
        mFavoriteBtn.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View v) {
                onFavoriteClick(doc);
            }
        });

        int iconResId = getResForExtension(doc.getFileName());
        mTitleView.setCompoundDrawables(getResources().getDrawable(iconResId), null, null, null);

        if(doc.getLabel() != null) {
            mTitleView.setText(doc.getLabel());
        }

        setOnClickListener(new OnDocumentClickListener(doc));

    }

    private Integer getResForExtension(String fileName) {

        FileType type = FileType.getTypeForFilename(fileName);

        if(type == null) {
            return 0;
        }

        return sfileTypeResMap.get(type);

    }

    private void onFavoriteClick(Document document) {

        boolean isFavorite = (document.getIsFavorite() != null) ? document.getIsFavorite() : false;

        document.setIsFavorite(!isFavorite);
        DocumentDao dao = mDaoMaster.newSession().getDocumentDao();
        dao.update(document);

        setDocument(document);

    }

    private class OnDocumentClickListener implements OnClickListener {

        private Document document;

        public OnDocumentClickListener(Document document) {
            this.document = document;
        }

        @Override
        public void onClick(View v) {
            try {
                mDocumentViewerDelegate.startActivityForFile(getContext(), document.getFileName());
            } catch (AppPackageFileWriter.PackageWriteException e) {
                // TODO Do we really want to Toast?
                Toaster.toast(v.getContext(), "Failed to write to pacakge: " + e.getFileName());
            }
        }
    };

}
