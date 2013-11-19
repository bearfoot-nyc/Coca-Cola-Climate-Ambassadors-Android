package com.cocacola.climateambassador.test.data;

import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.DaoSession;
import com.cocacola.climateambassador.data.Module;
import com.cocacola.climateambassador.data.ModuleDao;
import com.cocacola.climateambassador.data.Section;
import com.cocacola.climateambassador.data.SectionDao;
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

    @Inject protected JsonAssetsLoader mJsonLoader;
    @Inject protected DataSeeder mSeeder;
    @Inject protected DaoMaster mDaoMaster;

    private DaoSession mDaoSession;

    @Override public void setUp() throws Exception {
        super.setUp();
        mDaoSession = mDaoMaster.newSession();
    }

    @Override protected void tearDown() throws Exception {
        super.tearDown();
        mJsonLoader = null;
        mSeeder = null;
        mDaoSession = null;
    }

    public void testSeedsSections() throws IOException {

        Integer setctionRes = DataSeeder.SECTION_INTERNAL_TRAINING;

        long sectionId = mSeeder.seedSection(setctionRes);

        SectionDao dao = mDaoSession.getSectionDao();

        List<Section> sectionList = dao.queryBuilder().where(SectionDao.Properties.Id.eq(sectionId)).limit(1).list();

        assertNotNull("Sections query was null", sectionList);
        assertEquals("Section size was not one", 1, sectionList.size());

        Section section = sectionList.get(0);

        assertNotNull(section);
        assertEquals("Section titles not the same", mContext.getString(setctionRes), section.getName());


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

        ModuleDao dao = mDaoSession.getModuleDao();

        List<Module> modules = dao.queryBuilder().where(ModuleDao.Properties.Id.eq(id))
            .list();

        assertNotNull("Module List was null", modules);
        assertNotNull("No Modules in List", modules.get(0));

        return modules.get(0);

    }

}
