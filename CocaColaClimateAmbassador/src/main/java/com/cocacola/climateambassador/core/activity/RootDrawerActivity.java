package com.cocacola.climateambassador.core.activity;

import android.content.Intent;
import com.cocacola.climateambassador.core.CaConstants;
import com.cocacola.climateambassador.data.Navigable;
import com.cocacola.climateambassador.module.activity.ModuleActivity;
import com.cocacola.climateambassador.module.activity.SectionModuleActivity;

import static com.cocacola.climateambassador.module.activity.AbsModuleActivity.EXTRA_MODULE_ID;
import static com.cocacola.climateambassador.module.activity.AbsModuleActivity.EXTRA_MODULE_TYPE;
import static com.cocacola.climateambassador.module.activity.AbsModuleActivity.MODULE_TYPE_CASES;
import static com.cocacola.climateambassador.module.activity.AbsModuleActivity.MODULE_TYPE_SUSTAINABLE;
import static com.cocacola.climateambassador.module.activity.AbsModuleActivity.MODULE_TYPE_VISION;

/**
 * Created by realandylawton on 12/1/13.
 */
public class RootDrawerActivity extends CaDrawerSearchableActivity {
    @Override public void onModuleItemClick(Navigable item) {

        Intent intent;

        if(item.getShortTitle().contains("Key Interventions")) {

            intent = new Intent(this, ModuleActivity.class);
            intent.putExtra(EXTRA_MODULE_ID, item.getId());
            intent.putExtra(EXTRA_MODULE_TYPE, MODULE_TYPE_CASES);

        }
        else if(item.getTitle().contains("Sustainable")) {

            intent = new Intent(this, ModuleActivity.class);
            intent.putExtra(EXTRA_MODULE_ID, item.getId());
            intent.putExtra(EXTRA_MODULE_TYPE, MODULE_TYPE_SUSTAINABLE);

        }
        else if(item.getTitle().contains("2020 Vision")) {
            intent = new Intent(this, ModuleActivity.class);
            intent.putExtra(EXTRA_MODULE_ID, item.getId());
            intent.putExtra(EXTRA_MODULE_TYPE, MODULE_TYPE_VISION);
        }
        else  {
            intent = new Intent(this, ModuleActivity.class);
            intent.putExtra(EXTRA_MODULE_ID, item.getId());
        }

        startActivity(intent);

    }

    @Override public void onSectionItemClick(Navigable section) {

        Intent intent = new Intent(this, SectionModuleActivity.class);
        intent.putExtra(SectionModuleActivity.EXTRA_SECTION_ID, section.getId());

        startActivity(intent);

    }
}
