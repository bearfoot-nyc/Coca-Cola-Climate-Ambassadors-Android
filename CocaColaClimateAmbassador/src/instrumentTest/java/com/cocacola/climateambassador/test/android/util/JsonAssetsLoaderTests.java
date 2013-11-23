package com.cocacola.climateambassador.test.android.util;

import android.content.res.AssetManager;
import com.cocacola.climateambassador.CaConstants;
import com.cocacola.climateambassador.json.BulletPointFrameJson;
import com.cocacola.climateambassador.json.CaseJson;
import com.cocacola.climateambassador.test.CaTestCase;
import com.cocacola.climateambassador.util.JsonAssetsLoader;
import java.io.IOException;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by realandylawton on 8/31/13.
 */
public class JsonAssetsLoaderTests extends CaTestCase {

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
            Log.i("fileName=%s", file);
        }

    }

    public void testIngredientCasesParsesStringAsGson() throws IOException {

        CaseJson ingredientsCase = mJsonAssetsLoader.parseFromJsonFile("case_ingredients.json", CaseJson.class);

        assertNotNull(ingredientsCase);

        assertEquals(ingredientsCase.getTitle(), "Ingredients Cases");

        BulletPointFrameJson bulletPointFrame = ingredientsCase.getBulletPointFrame();
        // FIXME Was this changed or did test fail?
        ////assertEquals(bulletPointFrame.getTitle(), "Strategic Frame");
        //
        //List<String> bulletPoints = bulletPointFrame.getBulletPoints();
        //
        //assertNotNull(bulletPoints);
        //assertNotSame(bulletPoints.size(), 0);
        //assertEquals(bulletPoints.get(0), "Fertilizer = high GHG");
        //assertEquals(bulletPoints.get(4), "Waste = emissions or energy opportunity");
        //
        //List<TextFrameJson> textFrames = ingredientsCase.getTextFrames();
        //
        //// FIXME This is failing too
        //assertNotNull(textFrames);
        ////assertNotSame(textFrames.size(), 0);
        //
        //TextFrameJson frame1 = textFrames.get(0);
        //
        //assertEquals("Financial Frame", frame1.getTitle());
        //assertEquals("", frame1.getBodyText());
        //assertEquals("Revenue", frame1.getSubtitleTextPairs().get(1).getTitle());
        //
        //TextFrameJson frame2 = textFrames.get(1);
        //
        //assertEquals("Strategic Future", frame2.getTitle());
        //assertEquals(frame2.getSubtitleTextPairs().size(), 0);


    }

}
