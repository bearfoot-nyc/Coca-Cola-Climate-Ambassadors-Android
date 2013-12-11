package com.cocacola.climateambassador.test.android.core.util;

import android.content.Intent;
import android.net.Uri;
import com.cocacola.climateambassador.core.util.AppPackageFileWriter;
import com.cocacola.climateambassador.core.util.DocumentIntentBuilder;
import com.cocacola.climateambassador.core.util.DocumentUriBuilder;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by andrewlawton on 8/24/13.
 */
public class DocumentIntentBuilderTests extends CaFileTestCase {

    @Inject protected DocumentUriBuilder mDocumentUriBuilder;
    @Inject protected DocumentIntentBuilder mDocumentIntentBuilder;
    @Inject protected Timber Log;

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mDocumentIntentBuilder = null;
    }

    public void testCreatesProperIntentForFileType()
        throws AppPackageFileWriter.PackageWriteException,
        DocumentIntentBuilder.PreferredAppNotInstalledException {

        String fileName = VALID_PDF_FILENAME;

        Uri path = mDocumentUriBuilder.createUriForFilename(mContext, fileName);

        Intent intent = mDocumentIntentBuilder.createViewerIntent(mContext, path, fileName);

        assertEquals(path, intent.getData());

    }

    public void testCreatesBrowserIntent() {



    }

}
