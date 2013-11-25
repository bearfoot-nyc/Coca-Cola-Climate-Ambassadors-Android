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

        return dao.load(moduleId);

    }

}
