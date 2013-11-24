package com.cocacola.climateambassador.cases.activity;

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.cases.activity.CaCaseActivity;

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
