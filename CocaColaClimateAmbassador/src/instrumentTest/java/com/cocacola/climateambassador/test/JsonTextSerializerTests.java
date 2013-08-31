package com.cocacola.climateambassador.test;

import android.content.Context;
import android.test.InstrumentationTestCase;

import com.cocacola.climateambassador.util.JsonAssetsHelper;

import java.io.IOException;

import dagger.ObjectGraph;

/**
 * Created by realandylawton on 8/31/13.
 */
public class JsonTextSerializerTests extends InstrumentationTestCase {

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
        mContext = null;
    }

    public void testParsesFileAsString() throws IOException {

        String fileName = "case-ingredients.json";

        String json = JsonAssetsHelper.parseAsString(mContext, fileName);

        assertNotNull(json);
        assertNotSame(json, "");

    }

}
