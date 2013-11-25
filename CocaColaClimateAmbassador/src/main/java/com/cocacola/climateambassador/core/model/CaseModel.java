package com.cocacola.climateambassador.core.model;

import com.cocacola.climateambassador.data.CaCase;
import com.cocacola.climateambassador.data.CaCaseDao;
import com.cocacola.climateambassador.data.DaoMaster;

/**
 * Created by realandylawton on 11/24/13.
 */
public class CaseModel {
    public static CaCase getCase(DaoMaster daoMaser, Long caseId) {

        CaCaseDao caseDao = daoMaser.newSession().getCaCaseDao();

        return caseDao.queryBuilder().where(CaCaseDao.Properties.Id.eq(caseId)).limit(1).unique();

    }
}
