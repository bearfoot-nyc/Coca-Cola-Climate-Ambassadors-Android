package com.cocacola.climateambassador.core.model;

import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.Section;
import com.cocacola.climateambassador.data.SectionDao;
import java.util.List;

/**
 * Created by realandylawton on 11/19/13.
 */
public class SectionModel extends Section {

    public static List<Section> getAllSections(DaoMaster daoMaster) throws Exception {

        SectionDao sectionDao = daoMaster.newSession().getSectionDao();

        List<Section> sectionList = sectionDao.queryBuilder().list();

        return sectionList;

    }

    public static Section getSection(DaoMaster daoMaster, String title) throws Exception {

        List<Section> sectionList = getAllSections(daoMaster);

        Section section = null;
        for(Section s : sectionList) {
            if(s.getName().equals(title)) {
                section = s;
            }
        }

        return section;


    }

}
