package com.cocacola.climateambassador.models;

import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.Section;
import com.cocacola.climateambassador.data.SectionDao;
import java.util.List;

/**
 * Created by realandylawton on 11/19/13.
 */
public class SectionModel extends Section {

    public static List<Section> getAllSections(DaoMaster daoMaster) throws Exception {

        SectionDao dao = daoMaster.newSession().getSectionDao();

        List<Section> sectionList = dao.queryBuilder().list();
        if(sectionList == null) {
            throw new Exception("No sections available");
        }

        return sectionList;

    }

}
