package com.cocacola.climateambassador.core.util;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import java.io.File;
import javax.inject.Inject;

/**
 * Created by realandylawton on 11/27/13.
 */
public class DocumentUriBuilder {

    public static final String AUTHORITY = "com.cocacola.climateambassador.files";

    @Inject AppPackageFileWriter mPackageFileWriter;

    @Inject
    public DocumentUriBuilder() {
        // empty constructor
    }


    /**
     * Create a URI pointing to the File representing the filename.
     * If the File doesn't exist yet, will attempt to write file to
     * package folder
     *
     *
     *
     * @param context
     * @param fileName name of file.  Assumed to be in /assets directory.
     * @return URI pointing to File representing the filename
     */
    public Uri createUriForFilename(Context context, String fileName)
        throws AppPackageFileWriter.PackageWriteException {

        File file;

        if(!mPackageFileWriter.fileExists(fileName)) {
            file = mPackageFileWriter.writeToPkgDir(fileName);
        }
        else {
            file = mPackageFileWriter.createFile(fileName);
        }

        return createUri(context, file);

    }

    private Uri createUri(Context context, File file) {

        Uri uri = FileProvider.getUriForFile(context, AUTHORITY, file);
        return uri;

    }

}
