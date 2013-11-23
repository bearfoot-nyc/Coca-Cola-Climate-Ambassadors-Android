package com.cocacola.climateambassador.util;

import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.Section;
import com.cocacola.climateambassador.models.SectionModel;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by realandylawton on 11/19/13.
 */
public class DataChecker {

    public static final int EXPECTED_NUMBER_OF_SECTIONS = 2;

    @Inject protected DataSeeder mSeeder;
    @Inject protected DaoMaster mDaoMaster;

    public boolean isDataSeeded() {

        boolean isDataSeeded = false;

        // Query for sections
        try {
            List<Section> sections = SectionModel.getAllSections(mDaoMaster);

            if(sections == null) {
                isDataSeeded = false;
            }

            if(EXPECTED_NUMBER_OF_SECTIONS == sections.size()) {
                isDataSeeded = true;
            }

        } catch (Exception e) {
            return false;
        }

        // Clear the database just to be safe
        if(isDataSeeded == false) {
            mSeeder.clearDatabase();
            mSeeder.createDatabase();
        }

        return isDataSeeded;

    }



}
