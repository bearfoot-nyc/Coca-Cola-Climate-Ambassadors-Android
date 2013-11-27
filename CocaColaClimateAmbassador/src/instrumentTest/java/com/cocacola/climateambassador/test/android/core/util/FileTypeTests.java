package com.cocacola.climateambassador.test.android.core.util;

import com.cocacola.climateambassador.data.json.FileType;

/**
 * Created by realandylawton on 11/27/13.
 */
public class FileTypeTests extends CaFileTestCase {

    public void testGetsFileTypeForExtension() {

        String extension = "pdf";

        FileType fileType = FileType.getTypeForExtension(extension);

        assertNotNull(fileType);
        assertEquals(extension, fileType.getExtension());

    }

    public void testGetsFileTypeForFilename() {

        String fileName = VALID_PPT_FILENAME;
        FileType expectedType = FileType.PPT;

        FileType type = FileType.getTypeForFilename(fileName);

        assertNotNull(type);
        assertEquals(expectedType, type);

    }

    public void testBadExtensionHasNoFileType() {

        String extension = "badFileType";

        FileType fileType = FileType.getTypeForExtension(extension);

        assertNull(fileType);

    }

}
