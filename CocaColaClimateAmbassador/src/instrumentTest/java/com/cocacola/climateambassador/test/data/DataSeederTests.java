package com.cocacola.climateambassador.test.data;

import com.cocacola.climateambassador.data.BulletPoint;
import com.cocacola.climateambassador.data.BulletPointFrame;
import com.cocacola.climateambassador.data.CaCase;
import com.cocacola.climateambassador.data.Document;
import com.cocacola.climateambassador.data.Module;
import com.cocacola.climateambassador.data.Section;
import com.cocacola.climateambassador.data.SectionDao;
import com.cocacola.climateambassador.data.SubtitleTextPair;
import com.cocacola.climateambassador.data.TextFrame;
import com.cocacola.climateambassador.data.json.SectionJson;
import com.cocacola.climateambassador.core.model.SectionModel;
import com.cocacola.climateambassador.core.util.DataSeeder;
import java.io.IOException;
import java.util.List;

/**
 * Created by realandylawton on 11/13/13.
 */
public class DataSeederTests extends AbsDataTests {

    public void testSeedsSections() throws Exception {

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

        for(CaCase c : module.getCases()) {
            assertValidCase(c);
        }

    }

    private void assertValidCase(CaCase c) {

        assertNotNull(c);
        assertNotNull(c.getTitle());
        assertNotNull(c.getBodyText());

        assertValidBulletPoints(c.getBulletPointFrame());

        for(Document document : c.getCaseStudies()) {
            assertValidDocument(document);
        }

        for(TextFrame frame : c.getTextFrames()) {
            assertNotNull(frame);
            assertNotNull(frame.getTitle());
            assertNotNull(frame.getBodyText());
            assertNotNull(frame.getSubtitleTextPairs());
            for(SubtitleTextPair pair : frame.getSubtitleTextPairs()) {
                assertNotNull(pair);
                assertNotNull(pair.getTitle());
                assertNotNull(pair.getText());
            }
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

    private Section getSection(Integer stringRes) throws Exception {

        String title = mContext.getString(stringRes);

        return SectionModel.getSection(mDaoMaster, title);

    }

    private SectionJson getJsonSection(Integer sectionRes) throws IOException {
        String fileName = DataSeeder.getJsonForSection(sectionRes);
        return mJsonLoader.parseFromJsonFile(fileName, SectionJson.class);
    }

}
