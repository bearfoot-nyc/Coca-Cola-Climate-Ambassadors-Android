package com.cocacola.climateambassador.util;

import android.content.Context;
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

    @Inject protected DaoMaster mDaoMaster;

    private Context mContext;

    @Inject
    public DataChecker(Context context) {
        mContext = context;
    }

    public boolean isDataSeeded() {

        boolean isDataSeeded = false;

        // Query for sections
        try {
            List<Section> sections = SectionModel.getAllSections(mDaoMaster);
            if(sections != null && sections.size() == EXPECTED_NUMBER_OF_SECTIONS) {
                isDataSeeded = true;
            }

        } catch (Exception e) {
            return false;
        }

        return isDataSeeded;

    }

}
