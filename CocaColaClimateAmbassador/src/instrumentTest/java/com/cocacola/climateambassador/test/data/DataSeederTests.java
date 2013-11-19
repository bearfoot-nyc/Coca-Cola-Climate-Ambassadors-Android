package com.cocacola.climateambassador.test.data;

import com.cocacola.climateambassador.data.BulletPoint;
import com.cocacola.climateambassador.data.BulletPointFrame;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.DaoSession;
import com.cocacola.climateambassador.data.Document;
import com.cocacola.climateambassador.data.Module;
import com.cocacola.climateambassador.data.ModuleDao;
import com.cocacola.climateambassador.data.Section;
import com.cocacola.climateambassador.data.SectionDao;
import com.cocacola.climateambassador.models.ModuleJson;
import com.cocacola.climateambassador.models.SectionJson;
import com.cocacola.climateambassador.test.CaTestCase;
import com.cocacola.climateambassador.util.DataSeeder;
import com.cocacola.climateambassador.util.JsonAssetsLoader;
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

    public void testSeedsSections() throws IOException, DataSeeder.SeedFailedException {

        // Seed Sections
        mSeeder.seed();

        // Assert Internal Training Section is valid
        Section internalSection = getSection(DataSeeder.SECTION_INTERNAL_TRAINING);
        assertValidSection(internalSection, DataSeeder.SECTION_INTERNAL_TRAINING);

        // Assert For Suppliers is valid
        Section suppliersSection = getSection(DataSeeder.SECTION_FOR_SUPPLIERS);
        assertValidSection(suppliersSection, DataSeeder.SECTION_FOR_SUPPLIERS);

    }

    public void testSeedsSection() throws IOException, DataSeeder.SeedFailedException {

        // Pick a Section
        Integer sectionRes = DataSeeder.SECTION_INTERNAL_TRAINING;

        // Seed the section
        long sectionId = mSeeder.seedSection(sectionRes);

        // Query for that section
        SectionDao dao = mDaoSession.getSectionDao();
        List<Section> sectionList = dao.queryBuilder().where(SectionDao.Properties.Id.eq(sectionId)).limit(1).list();
        assertNotNull("Sections query was null", sectionList);
        assertEquals("Section size was not one", 1, sectionList.size());

        // Assert Section is valid
        Section section = sectionList.get(0);
        assertValidSection(section, sectionRes);

    }

    public void testModuleIsSeeded() throws IOException, DataSeeder.SeedFailedException {

        // Get a Section
        Integer sectionRes = DataSeeder.SECTION_INTERNAL_TRAINING;
        SectionJson sectionJson = getJsonSection(sectionRes);
        Section section = getSection(sectionRes);

        // Get a module
        Module module = mSeeder.seedModule(sectionJson.getModules().get(2), section.getId());

        assertValidModule(module);

    }

    private void assertValidSection(Section section, Integer sectionTitleRes) {

        // Assert Section is valid
        assertNotNull(section);
        assertEquals("Section titles not the same", mContext.getString(sectionTitleRes), section.getName());

        // Inspect modules
        List<Module> moduleList = section.getModules();
        for(Module module : moduleList) {
            assertValidModule(module);
        }

    }

    private void assertValidModule(Module module) {

        assertNotNull("Module was null", module);
        assertNotNull("Module ID was null", module.getId());
        assertNotNull("Module name was null", module.getTitle());

        assertValidBulletPoints(module.getBulletPointFrame());

        for(Document document : module.getDocuments()) {
            assertValidDocument(document);
        }

    }

    private void assertValidDocument(Document document) {

        assertNotNull(document);
        assertNotNull(document.getFileName());
        assertNotNull(document.getLabel());
        assertNotNull(document.getModuleId());

    }

    private void assertValidBulletPoints(BulletPointFrame bulletPointFrame) {

        assertNotNull(bulletPointFrame);
        assertNotNull(bulletPointFrame.getTitle());
        assertNotNull(bulletPointFrame.getBulletPoints());
        for(BulletPoint bp : bulletPointFrame.getBulletPoints()) {
            assertNotNull(bp.getText());
        }

    }

    private Module getModule(long id) {

        ModuleDao dao = mDaoSession.getModuleDao();

        List<Module> modules = dao.queryBuilder().where(ModuleDao.Properties.Id.eq(id))
            .list();

        assertNotNull("Module List was null", modules);
        assertNotNull("No Modules in List", modules.get(0));

        return modules.get(0);

    }

    private Section getSection(Integer stringRes) {

        SectionDao sectionDao = mDaoSession.getSectionDao();

        Section section = sectionDao.queryBuilder().
            where(SectionDao.Properties.Name.eq(mContext.getString(stringRes)))
            .limit(1).list().get(0);

        return section;


    }

    private SectionJson getJsonSection(Integer sectionRes) throws IOException {
        String fileName = DataSeeder.getJsonForSection(sectionRes);
        return mJsonLoader.parseFromJsonFile(fileName, SectionJson.class);
    }

    private ModuleJson getRandomJsonModule(Integer sectionRes) throws IOException {

        SectionJson sectionJson = getJsonSection(sectionRes);
        String moduleFilename = sectionJson.getModules().get(1);

        return mJsonLoader.parseFromJsonFile(moduleFilename, ModuleJson.class);

    }

}
