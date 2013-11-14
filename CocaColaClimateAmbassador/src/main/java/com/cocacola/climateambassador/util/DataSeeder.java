package com.cocacola.climateambassador.util;

import android.content.Context;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.DaoSession;
import com.cocacola.climateambassador.data.Module;
import com.cocacola.climateambassador.data.ModuleDao;
import com.cocacola.climateambassador.models.ModuleJson;
import java.io.IOException;
import javax.inject.Inject;

/**
 * Created by realandylawton on 11/14/13.
 */
public class DataSeeder {

    @Inject JsonAssetsLoader mJsonLoader;
    @Inject DaoMaster mDaoMaster;

    private Context mContext;

    @Inject
    public DataSeeder(Context context) {
        mContext = context;
    }

    public Long seedModule(String fileName) throws IOException {

        ModuleJson json = mJsonLoader.parseModuleFromJsonFile(fileName);

        DaoSession session = mDaoMaster.newSession();
        ModuleDao dao = session.getModuleDao();

        Module moduleModel = new Module();
        moduleModel.setTitle(json.getTitle());
        moduleModel.setBodyText(json.getBodyText());

        long id = dao.insert(moduleModel);

        return id;
    }
}
