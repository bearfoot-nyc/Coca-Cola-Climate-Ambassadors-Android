package com.cocacola.climateambassador.ui.activities;

import com.cocacola.climateambassador.R;

/**
 * Created by Vinnie on 9/4/13.
 */
public class ManufacturingCaseActivity extends CaCaseActivity {

    @Override
    public String getJsonAssetFilename() {
        return "case_manufacturing.json";
    }

    @Override
    int getBackgroundDrawableId() {
        return R.drawable.bg_case_manufacturing;
    }

    @Override
    int getIconId() {
        return R.drawable.ic_case_detail_manufacturing;
    }

}
