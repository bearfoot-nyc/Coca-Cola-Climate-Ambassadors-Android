package com.cocacola.climateambassador.core.views;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Views;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.core.CaApplication;
import com.cocacola.climateambassador.core.util.AppPackageFileWriter;
import com.cocacola.climateambassador.core.util.DocumentIntentBuilder;
import com.cocacola.climateambassador.core.util.DocumentUriBuilder;
import com.cocacola.climateambassador.core.util.EmailAttachmentIntentBuilder;
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
    @Inject protected DocumentUriBuilder mDocumentUriBuilder;
    @Inject protected DocumentIntentBuilder mDocumentIntentBuilder;
    @Inject protected EmailAttachmentIntentBuilder mEmailIntentBuilder;

    private TextView mTitleView;
    private ImageView mFavoriteBtn;
    private ImageView mShareBtn;

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
        mShareBtn = Views.findById(this, R.id.doc_share_btn);
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

        mShareBtn.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View v) {
                onShareClick(doc);
            }
        });

        if(doc.getLabel() != null) {
            mTitleView.setText(doc.getLabel());
        }

        setOnClickListener(new OnClickListener() {
            @Override public void onClick(View v) {
                onDocumentClick(doc);
            }
        });

    }

    private void onShareClick(Document document) {

        try {
            Intent intent = mEmailIntentBuilder.createIntent(document);
            getContext().startActivity(Intent.createChooser(intent, "Email To:"));

        } catch (AppPackageFileWriter.PackageWriteException e) {
            Toaster.toast(getContext(), "Failed to find file");
        }
    }

    private Integer getResForExtension(String fileName) {

        FileType type = FileType.getTypeForFilename(fileName);

        if(type == null) {
            return 0;
        }

        return sfileTypeResMap.get(type);

    }

    protected void onDocumentClick(Document doc) {

        try {

            Uri documentUri = mDocumentUriBuilder.createUriForFilename(doc.getFileName());
            Intent intent = mDocumentIntentBuilder.createViewerIntent(getContext(), documentUri, doc.getFileName());

            getContext().startActivity(intent);

        } catch (AppPackageFileWriter.PackageWriteException e) {
            Toaster.toast(getContext(), e.getMessage());
        } catch (DocumentIntentBuilder.PreferredAppNotInstalledException e) {
            Toaster.toast(getContext(), e.getMessage());
        } catch (ActivityNotFoundException e) {
            Toaster.toast(getContext(), e.getMessage());
        }

    }

    protected void onFavoriteClick(Document document) {

        boolean isFavorite = (document.getIsFavorite() != null) ? document.getIsFavorite() : false;

        document.setIsFavorite(!isFavorite);
        DocumentDao dao = mDaoMaster.newSession().getDocumentDao();
        dao.update(document);

        setDocument(document);

    }

}
