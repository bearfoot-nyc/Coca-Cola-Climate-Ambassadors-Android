package com.cocacola.climateambassador.ui.activities;

import com.cocacola.climateambassador.R;

/**
 * Created by Vinnie on 9/4/13.
 */
public class DistributionCaseActivity extends CaCaseActivity {

    @Override
    public String getJsonAssetFilename() {
        return "case_distribution.json";
    }

    @Override
    int getBackgroundDrawableId() {
        return R.drawable.bg_case_distribution;
    }

    @Override
    int getIconId() {
        return R.drawable.ic_case_detail_distribution;
    }

}
