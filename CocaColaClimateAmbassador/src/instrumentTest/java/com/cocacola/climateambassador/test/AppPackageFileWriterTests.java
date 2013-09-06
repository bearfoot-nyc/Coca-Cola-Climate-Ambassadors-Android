package com.cocacola.climateambassador.test;

import android.content.Context;
import android.test.InstrumentationTestCase;

import com.cocacola.climateambassador.AppPackageFileWriter;

import javax.inject.Inject;

import dagger.ObjectGraph;

/**
 * Created by andrewlawton on 8/24/13.
 */
public class AppPackageFileWriterTests extends InstrumentationTestCase {

    @Inject
    AppPackageFileWriter mAppPackageFileWriter;

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
        mAppPackageFileWriter = null;
    }

    public void testDoesWriteFileToPkgDir() {


    }

}
