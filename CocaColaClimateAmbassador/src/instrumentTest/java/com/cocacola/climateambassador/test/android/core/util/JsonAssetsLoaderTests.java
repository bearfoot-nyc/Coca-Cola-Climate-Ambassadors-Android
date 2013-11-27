package com.cocacola.climateambassador.test.android.core.util;

import android.content.res.AssetManager;
import com.cocacola.climateambassador.core.CaConstants;
import com.cocacola.climateambassador.data.json.BulletPointFrameJson;
import com.cocacola.climateambassador.data.json.CaseJson;
import com.cocacola.climateambassador.test.CaTestCase;
import com.cocacola.climateambassador.core.util.JsonAssetsLoader;
import java.io.IOException;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by realandylawton on 8/31/13.
 */
public class JsonAssetsLoaderTests extends CaTestCase {

    private static final String CASE_FILENAME = "case_ingredients.json";

    @Inject protected Timber Log;
    @Inject protected JsonAssetsLoader mJsonAssetsLoader;

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mJsonAssetsLoader = null;
    }

    public void testJsonAssetsExist() throws IOException {

        AssetManager manager = mContext.getAssets();
        String[] files = manager.list(CaConstants.JSON_ASSETS_DIRECTORY);

        assertNotNull(files);

        for(String file : files) {
            assertNotNull(file);
        }

    }

    public void testParsesCase() throws IOException {

        CaseJson c = mJsonAssetsLoader.parseFromJsonFile(CASE_FILENAME, CaseJson.class);

        assertNotNull(c);
        assertNotNull(c.getTitle());
        assertNotNull(c.getBodyText());
        assertNotNull(c.getCaseStudies());

    }

}
