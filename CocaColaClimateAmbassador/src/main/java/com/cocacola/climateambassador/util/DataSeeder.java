package com.cocacola.climateambassador.util;

import android.content.Context;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.data.BulletPoint;
import com.cocacola.climateambassador.data.BulletPointDao;
import com.cocacola.climateambassador.data.BulletPointFrame;
import com.cocacola.climateambassador.data.BulletPointFrameDao;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.DaoSession;
import com.cocacola.climateambassador.data.Document;
import com.cocacola.climateambassador.data.DocumentDao;
import com.cocacola.climateambassador.data.Module;
import com.cocacola.climateambassador.data.ModuleDao;
import com.cocacola.climateambassador.data.Section;
import com.cocacola.climateambassador.data.SectionDao;
import com.cocacola.climateambassador.json.DocumentJson;
import com.cocacola.climateambassador.json.ModuleJson;
import com.cocacola.climateambassador.json.SectionJson;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by realandylawton on 11/14/13.
 */
public class DataSeeder {

    public static class SeedFailedException extends Exception {
        public SeedFailedException(String detailMessage) {
            super(detailMessage);
        }
    }

    public static final Integer SECTION_INTERNAL_TRAINING = R.string.nav_item_internal_training;
    public static final Integer SECTION_FOR_SUPPLIERS = R.string.nav_item_for_suppliers;

    public static Map<Integer, String> sSectionJsonMap = new LinkedHashMap<Integer, String>();
    static {
        sSectionJsonMap.put(SECTION_INTERNAL_TRAINING, "section_internal_training.json");
        sSectionJsonMap.put(SECTION_FOR_SUPPLIERS, "section_for_suppliers.json");
    }
    public static String getJsonForSection(Integer sectionRes) {
        return sSectionJsonMap.get(sectionRes);
    }

    @Inject protected JsonAssetsLoader mJsonLoader;
    @Inject protected DaoMaster mDaoMaster;
    @Inject protected Timber Log;

    private Context mContext;

    @Inject
    public DataSeeder(Context context) {
        mContext = context;
    }

    public void seed() throws IOException, SeedFailedException {

        for(Integer sectionRes : sSectionJsonMap.keySet()) {
            seedSection(sectionRes);
        }

    }

    public Long seedSection(Integer sectionNameRes) throws IOException, SeedFailedException {

        String sectionJsonFile = sSectionJsonMap.get(sectionNameRes);

        SectionJson sectionJson = mJsonLoader.parseFromJsonFile(sectionJsonFile, SectionJson.class);

        Section section = new Section();
        section.setName(sectionJson.getTitle());

        SectionDao sectionDao = mDaoMaster.newSession().getSectionDao();
        long sectionId = sectionDao.insert(section);

        for(String moduleJson : sectionJson.getModules()) {

            Module module = seedModule(moduleJson, section.getId());
            if(module == null) {
                throw new SeedFailedException("Failed to load module: " + moduleJson);
            }

        }

        return sectionId;

    }

    public Module seedModule(String fileName, Long sectionId)
        throws IOException, SeedFailedException {

        ModuleJson json = mJsonLoader.parseModuleFromJsonFile(fileName);

        DaoSession session = mDaoMaster.newSession();
        ModuleDao dao = session.getModuleDao();

        Module moduleModel = new Module();
        moduleModel.setTitle(json.getTitle());
        moduleModel.setBodyText(json.getBodyText());
        moduleModel.setSectionId(sectionId);

        long bulletPointFrameId = seedBulletPointFrame(json);
        moduleModel.setBulletPointFrameId(bulletPointFrameId);

        Long moduleId = dao.insert(moduleModel);

        if (moduleId == null) {
            return null;
        }

        seedDocuments(json, moduleId);

        return moduleModel;
    }

    private void seedDocuments(ModuleJson json, Long moduleId) throws SeedFailedException {

        List<DocumentJson> documentJsonList = json.getDocuments();

        if(documentJsonList == null || documentJsonList.size() < 1) {
            return;
        }

        DocumentDao documentDao = mDaoMaster.newSession().getDocumentDao();

        for(DocumentJson documentJson : documentJsonList) {

            if(documentJson == null) {
                throw new SeedFailedException("Document JSON null: " + documentJson);
            }

            Document document = new Document();
            document.setFileName(documentJson.getFileName());
            document.setLabel(documentJson.getLabel());
            document.setModuleId(moduleId);

            documentDao.insert(document);


        }

    }

    public long seedBulletPointFrame(ModuleJson json) {

        BulletPointFrame frame = new BulletPointFrame();
        frame.setTitle(json.getBulletPointFrame().getTitle());
        frame.setSubtitle(json.getBulletPointFrame().getSubtitle());

        DaoSession daoSession = mDaoMaster.newSession();

        BulletPointFrameDao frameDao = daoSession.getBulletPointFrameDao();
        Long frameId = frameDao.insert(frame);

        BulletPointDao pointDao = daoSession.getBulletPointDao();
        for(String point : json.getBulletPointFrame().getBulletPoints()) {
            BulletPoint bulletPoint = new BulletPoint();
            bulletPoint.setText(point);
            bulletPoint.setBulletPointFrameId(frameId);
            pointDao.insert(bulletPoint);
        }

        return frameId;

    }

    public final void createDatabase() {

        DaoMaster.createAllTables(mDaoMaster.getDatabase(), true);

    }

    public final void clearDatabase() {

        DaoMaster.dropAllTables(mDaoMaster.getDatabase(), true);

    }

}
