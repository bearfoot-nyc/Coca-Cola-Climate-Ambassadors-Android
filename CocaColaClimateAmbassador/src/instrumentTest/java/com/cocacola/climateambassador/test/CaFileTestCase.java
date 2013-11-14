package com.cocacola.climateambassador.test;

import com.cocacola.climateambassador.models.FileType;
import java.io.File;

/**
 * Created by realandylawton on 11/13/13.
 */
public class CaFileTestCase extends CaTestCase {

    protected static final String VALID_PDF_FILENAME = "INGREDIENT_The-Case-for-Organic-Sugar-Farming-in-India.pdf";
    protected static final String VALID_PPT_FILENAME = "Climate_Science_Homework_Emissions and Our Business-08-2013.pptx";

    protected File getValidFile() {

        String fileName = VALID_PDF_FILENAME;

        File fileTypeDir = new File(mContext.getFilesDir().getAbsolutePath() + File.separator + FileType
            .PDF.getDirectory());

        File file =  new File(fileTypeDir + File.separator + fileName);

        return file;

    }

}
