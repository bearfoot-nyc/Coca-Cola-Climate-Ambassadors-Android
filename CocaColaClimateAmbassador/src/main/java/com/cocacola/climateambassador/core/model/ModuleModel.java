package com.cocacola.climateambassador.core.model;

import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.Module;
import com.cocacola.climateambassador.data.ModuleDao;

/**
 * Created by realandylawton on 11/23/13.
 */
public class ModuleModel extends Module {

    public static Module getModule(DaoMaster daoMaster, Long moduleId) {

        ModuleDao dao = daoMaster.newSession().getModuleDao();

        Module module = dao.queryBuilder().where(ModuleDao.Properties.Id.eq(moduleId)).limit(1).unique();

        return module;

    }

}
