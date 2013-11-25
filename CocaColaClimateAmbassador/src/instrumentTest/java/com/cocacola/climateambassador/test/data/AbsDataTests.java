package com.cocacola.climateambassador.test.data;

import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.DaoSession;
import com.cocacola.climateambassador.test.CaTestCase;
import com.cocacola.climateambassador.core.util.DataSeeder;
import com.cocacola.climateambassador.core.util.JsonAssetsLoader;
import javax.inject.Inject;

/**
 * Created by realandylawton on 11/19/13.
 */
public abstract class AbsDataTests extends CaTestCase {

    @Inject protected JsonAssetsLoader mJsonLoader;
    @Inject protected DataSeeder mSeeder;
    @Inject protected DaoMaster mDaoMaster;
    protected DaoSession mDaoSession;

    @Override public void setUp() throws Exception {
        super.setUp();
        createDatabase();
        mDaoSession = mDaoMaster.newSession();
    }

    @Override protected void tearDown() throws Exception {
        super.tearDown();
        clearDatabase();
        mJsonLoader = null;
        mSeeder = null;
        mDaoSession = null;
    }

    protected void createDatabase() {

       mSeeder.createDatabase();

    }

    protected void clearDatabase() {

        mSeeder.clearDatabase();

    }
}
