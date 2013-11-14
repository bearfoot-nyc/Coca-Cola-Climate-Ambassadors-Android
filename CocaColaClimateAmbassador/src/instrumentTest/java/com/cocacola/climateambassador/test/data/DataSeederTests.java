package com.cocacola.climateambassador.test.data;

import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.DaoSession;
import com.cocacola.climateambassador.data.Module;
import com.cocacola.climateambassador.data.ModuleDao;
import com.cocacola.climateambassador.test.CaTestCase;
import com.cocacola.climateambassador.util.DataSeeder;
import com.cocacola.climateambassador.util.JsonAssetsLoader;
import com.cocacola.climateambassador.util.JsonFilenameDictionary;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by realandylawton on 11/13/13.
 */
public class DataSeederTests extends CaTestCase {

    @Inject JsonAssetsLoader mJsonLoader;
    @Inject DataSeeder mSeeder;
    @Inject DaoMaster mDaoMaster;

    @Override protected void tearDown() throws Exception {
        super.tearDown();
        mJsonLoader = null;
        mSeeder = null;
    }

    public void testModuleOneIsSeeded() throws IOException {

        String jsonFileName = JsonFilenameDictionary.MODULE_ONE;

        Long id = mSeeder.seedModule(jsonFileName);
        assertNotNull("Module ID was null", id);
        assertTrue("Module ID was zero", id != 0);

        Module module = getModule(id);

        assertNotNull("Module was null", module);
        assertNotNull("Module name was null", module.getTitle());

    }

    private Module getModule(long id) {

        DaoSession session = mDaoMaster.newSession();
        ModuleDao dao = session.getModuleDao();

        List<Module> modules = dao.queryBuilder().where(ModuleDao.Properties.Id.eq(id))
            .list();

        assertNotNull("Module List was null", modules);
        assertNotNull("No Modules in List", modules.get(0));

        return modules.get(0);

    }

}
