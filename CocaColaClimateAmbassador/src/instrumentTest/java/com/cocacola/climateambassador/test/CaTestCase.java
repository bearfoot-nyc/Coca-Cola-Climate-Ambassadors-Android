package com.cocacola.climateambassador.test;

import android.content.Context;
import android.test.InstrumentationTestCase;
import dagger.ObjectGraph;

/**
 * Created by realandylawton on 11/13/13.
 */
public class CaTestCase extends InstrumentationTestCase {

    protected Context mContext;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        mContext = getInstrumentation().getTargetContext();

        ObjectGraph graph = ObjectGraph.create(new CaTestModule(mContext));
        graph.inject(this);
    }

}
