package com.cocacola.climateambassador.core.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.data.Document;
import com.cocacola.climateambassador.data.json.FileType;
import javax.inject.Inject;

/**
 * Created by realandylawton on 11/28/13.
 */
public class EmailAttachmentIntentBuilder {

    @Inject protected DocumentUriBuilder mDocumentUriBuilder;

    private Context mContext;

    @Inject
    public EmailAttachmentIntentBuilder(Context context) {
        mContext = context;
    }

    public Intent createIntent(Context context, Document document)
        throws AppPackageFileWriter.PackageWriteException {

        return createIntent(context, document.getFileName());

    }

    public Intent createIntent(Context context, String fileName) throws AppPackageFileWriter.PackageWriteException {

        Uri uri = mDocumentUriBuilder.createUriForFilename(context, fileName);
        FileType fileType = FileType.getTypeForFilename(fileName);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType(fileType.getMimeType());
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getSubject());
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
 
        return emailIntent;

    }

    private String getSubject() {
        return mContext.getString(R.string.share_email_subject);
    }

}
