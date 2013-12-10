package com.cocacola.climateambassador.test.android.ui;

import android.test.ActivityInstrumentationTestCase2;
import com.cocacola.climateambassador.core.util.DataSeeder;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.module.activity.ModuleActivity;
import com.cocacola.climateambassador.module.fragment.ModuleFragment;
import javax.inject.Inject;

/**
 * Created by realandylawton on 11/19/13.
 */
public class ModuleActivityTest extends ActivityInstrumentationTestCase2<ModuleActivity> {

    @Inject protected DataSeeder mDataSeeder;
    @Inject protected DaoMaster mDaoMaster;

    private ModuleActivity mModuleActivity;

    public ModuleActivityTest() {
        super(ModuleActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        //ObjectGraph graph = ObjectGraph.create(new CaTestModule(getActivity().getApplicationContext()));
        //graph.inject(this);
        //
        //DaoMaster.createAllTables(mDaoMaster.getDatabase(), true);
        //
        //mDataSeeder.seed();
        //
        //// Get a Module that has some documents
        //Module module = ModuleModel.getModule(mDaoMaster, 4l);
        //
        //Intent intent = new Intent(getInstrumentation().getContext(), ModuleActivity.class);
        //intent.putExtra(AbsModuleActivity.EXTRA_MODULE_ID, module.getId());
        //
        //setActivityIntent(intent);
        //
        //mModuleActivity = getActivity();

    }

    @Override protected void tearDown() throws Exception {
        super.tearDown();
        //DaoMaster.dropAllTables(mDaoMaster.getDatabase(), true);
    }

    public void testDocumentsExist() {

        // FIXME Implement later

        //ModuleFragment fragment = getModuleFragment();
        //
        //// Loop through the list of Documents
        //DocumentsLayout documentsLayout = Views.findById(fragment.getView(), R.id.module_documents);
        //
        //assertNotNull(documentsLayout);

    }

    //private ModuleFragment getModuleFragment() {
    //
    //    return (ModuleFragment) mModuleActivity.
    //        getFragmentManager().findFragmentById(R.id.module_frag_container);
    //
    //}

}