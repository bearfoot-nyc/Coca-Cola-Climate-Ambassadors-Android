package com.cocacola.climateambassador.test;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.test.InstrumentationTestCase;

import com.cocacola.climateambassador.DocumentViewerDelegate;
import com.cocacola.climateambassador.models.FileType;

import java.io.File;

import javax.inject.Inject;

import dagger.ObjectGraph;

/**
 * Created by andrewlawton on 8/24/13.
 */
public class DocumentViewDelegateTests extends InstrumentationTestCase {

    @Inject
    DocumentViewerDelegate mDocumentViewerDelegate;

    protected Context mContext;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        mContext = getInstrumentation().getTargetContext();

        ObjectGraph graph = ObjectGraph.create(new ClimateAmbassadorTestModule(mContext));
        graph.inject(this);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mDocumentViewerDelegate = null;
    }

    public void testCreatesFileForFileType() {

        FileType pdfType = FileType.PDF;
        String fileName = "coca-cola-Business-Case-for-Good-Fertilizer-Use-in-Citrus.pdf";
        String expectedPath = "foo";

        File file = mDocumentViewerDelegate.getFileForFileType(pdfType, fileName);

        assertTrue(file.exists());
        assertEquals(expectedPath, file.getAbsolutePath());

    }

    public void testCreatesUriForFileType() {

        FileType pdfType = FileType.PDF;
        String fileName = "coca-cola-Business-Case-for-Good-Fertilizer-Use-in-Citrus.pdf";

        Uri uri = mDocumentViewerDelegate.createUriFromFileName(pdfType, fileName);

        assertEquals("foo", uri.getEncodedPath());
        assertEquals("bar", uri.getScheme());

    }

    public void testCreatesProperIntentForFileType() {

        FileType videoType = FileType.VIDEO;
        String fileName = "Life-of-a-Bottle video.mp4";

        Uri path = mDocumentViewerDelegate.createUriFromFileName(videoType, fileName);

        Intent intent = mDocumentViewerDelegate.createViewerIntent(mContext, path, videoType);

        assertEquals(path, intent.getData());

    }

}
