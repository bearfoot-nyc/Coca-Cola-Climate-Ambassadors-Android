package com.cocacola.climateambassador.test.android.ui;

import android.test.ActivityInstrumentationTestCase2;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.test.CaTestModule;
import com.cocacola.climateambassador.core.activity.MainActivity;
import com.cocacola.climateambassador.core.util.DataChecker;
import dagger.ObjectGraph;
import javax.inject.Inject;

/**
 * Created by realandylawton on 11/19/13.
 */
public abstract class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    @Inject protected DaoMaster mDaoMaster;
    @Inject protected DataChecker mDataChecker;

    private MainActivity mMainActivity;

    public MainActivityTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        mMainActivity = getActivity();

        ObjectGraph graph = ObjectGraph.create(new CaTestModule(getActivity().getApplicationContext()));
        graph.inject(this);

        DaoMaster.createAllTables(mDaoMaster.getDatabase(), true);

    }

    @Override protected void tearDown() throws Exception {
        super.tearDown();
        DaoMaster.dropAllTables(mDaoMaster.getDatabase(), true);
    }

    public void testSeedsDataIfNotAvailable() {

        DaoMaster.dropAllTables(mDaoMaster.getDatabase(), true);
        DaoMaster.createAllTables(mDaoMaster.getDatabase(), true);

        mMainActivity.seedDataIfNotAvailable();


        mMainActivity.runOnUiThread(new Runnable() {
            @Override public void run() {
                try {
                    Thread.sleep(3000);

                    assertTrue("Data was not seeded", mDataChecker.isDataSeeded());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });



    }

}