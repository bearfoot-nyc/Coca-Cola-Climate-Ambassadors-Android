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
import butterknife.Views;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.core.CaApplication;
import com.cocacola.climateambassador.core.HasController;
import com.cocacola.climateambassador.core.controller.DocumentController;
import com.cocacola.climateambassador.core.model.DocumentModel;
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
public class DocumentView extends LinearLayout implements HasController<DocumentController> {

    private static HashMap<FileType, Integer> sfileTypeResMap = new HashMap<FileType, Integer>();
    static {
        sfileTypeResMap.put(FileType.PDF, R.drawable.ic_doc_pdf);
        sfileTypeResMap.put(FileType.PPT, R.drawable.ic_doc_ppt);
        sfileTypeResMap.put(FileType.MP4, R.drawable.ic_doc_mov);
        sfileTypeResMap.put(FileType.THREEGP, R.drawable.ic_doc_mov);
        sfileTypeResMap.put(FileType.XLS, R.drawable.ic_doc_xls);
    }

    @Inject protected DaoMaster mDaoMaster;
    @Inject protected DocumentUriBuilder mDocumentUriBuilder;
    @Inject protected DocumentIntentBuilder mDocumentIntentBuilder;
    @Inject protected EmailAttachmentIntentBuilder mEmailIntentBuilder;

    private DocumentController mController;
    private TextView mTitleView;
    private ImageView mFavoriteBtn;
    private View mSeparator;
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
        mSeparator = Views.findById(this, R.id.doc_separator);
    }

    @Override public void setController(DocumentController documentController) {
        mController = documentController;
    }

    public void setDocument(final Document doc) {

        if(doc == null) {
            return;
        }

        // Set Title
        if(doc.getLabel() != null) {
            mTitleView.setText(doc.getLabel());
        }

        // Add click listener to body
        setOnClickListener(new OnClickListener() {
            @Override public void onClick(View v) {
                onDocumentClick(doc);
            }
        });

        // Don't add favorites or share if it's a document
        if(DocumentModel.isLink(doc)) {

            mFavoriteBtn.setVisibility(View.GONE);
            mSeparator.setVisibility(View.GONE);
            mShareBtn.setVisibility(View.GONE);

            return;
        }

        // Show document type icon
        int iconResId = getResForExtension(doc.getFileName());
        mTitleView.setCompoundDrawablesWithIntrinsicBounds(iconResId, 0, 0, 0);

        // Set Favorite icon on/off
        boolean isFavorite = (doc.getIsFavorite() != null) ? doc.getIsFavorite() : false;
        Integer favoriteIconRes = isFavorite ? R.drawable.favorite_selected : R.drawable.favorite_unselected;

        mFavoriteBtn.setImageResource(favoriteIconRes);
        mFavoriteBtn.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View v) {
                onFavoriteClick(doc);
            }
        });

        // Set listener for share button
        mShareBtn.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View v) {
                onShareClick(doc);
            }
        });

    }

    private void onShareClick(Document document) {

        try {
            Intent intent = mEmailIntentBuilder.createIntent(getContext(), document);
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

        if(mController != null) {
            mController.startActivityForDocument(doc);
        }
        else {

            try {

                Context context = getContext();

                Uri documentUri = mDocumentUriBuilder.createUriForFilename(context, doc.getFileName());
                Intent intent = mDocumentIntentBuilder.createViewerIntent(context, documentUri, doc.getFileName());

                context.startActivity(intent);

            } catch (AppPackageFileWriter.PackageWriteException e) {
                Toaster.toast(getContext(), e.getMessage());
            } catch (DocumentIntentBuilder.PreferredAppNotInstalledException e) {

            } catch (ActivityNotFoundException e) {
                Toaster.toast(getContext(), e.getMessage());
            }

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
