package com.cocacola.climateambassador.test;

import android.content.Context;
import android.content.res.AssetManager;
import android.test.InstrumentationTestCase;

import com.cocacola.climateambassador.CaConstants;
import com.cocacola.climateambassador.models.BulletPointFrame;
import com.cocacola.climateambassador.models.Case;
import com.cocacola.climateambassador.models.TextFrame;
import com.cocacola.climateambassador.util.JsonAssetsLoader;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import dagger.ObjectGraph;
import timber.log.Timber;

/**
 * Created by realandylawton on 8/31/13.
 */
public class JsonTextSerializerTests extends InstrumentationTestCase {

    protected Context mContext;

    @Inject
    Timber Log;

    @Inject
    JsonAssetsLoader mJsonAssetsLoader;

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
        mContext = null;
    }

    public void testJsonAssetsExist() throws IOException {

        AssetManager manager = mContext.getAssets();
        String[] files = manager.list(CaConstants.JSON_ASSETS_DIRECTORY);

        assertNotNull(files);

        for(String file : files) {
            Log.i("fileName=%s", file);
        }

    }

    public void testParsesFileAsString() throws IOException {

        String fileName = "case_ingredients.json";

        String json = mJsonAssetsLoader.parseAsString(fileName);

        assertNotNull(json);
        assertNotSame(json, "");

    }

    public void testIngredientCasesParsesStringAsGson() throws IOException {

        Case ingredientsCase = mJsonAssetsLoader.parseCaseFromJsonFile("case_ingredients.json");

        assertNotNull(ingredientsCase);

        assertEquals(ingredientsCase.getTitle(), "Ingredients Cases");

        BulletPointFrame bulletPointFrame = ingredientsCase.getBulletPointFrame();
        assertEquals(bulletPointFrame.getTitle(), "Strategic Frame");

        List<String> bulletPoints = bulletPointFrame.getBulletPoints();

        assertNotNull(bulletPoints);
        assertNotSame(bulletPoints.size(), 0);
        assertEquals(bulletPoints.get(0), "Fertilizer = high GHG");
        assertEquals(bulletPoints.get(4), "Waste = emissions or energy opportunity");

        List<TextFrame> textFrames = ingredientsCase.getTextFrames();

        assertNotNull(textFrames);
        assertNotSame(textFrames.size(), 0);

        TextFrame frame1 = textFrames.get(0);

        assertEquals("Financial Frame", frame1.getTitle());
        assertEquals("", frame1.getBodyText());
        assertEquals("Revenue", frame1.getSubtitleTextPairs().get(1).getTitle());

        TextFrame frame2 = textFrames.get(1);

        assertEquals("Strategic Future", frame2.getTitle());
        assertEquals(frame2.getSubtitleTextPairs().size(), 0);


    }

}
