package com.cocacola.climateambassador.module.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.core.model.SectionModel;
import com.cocacola.climateambassador.core.util.Toaster;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.Module;
import com.cocacola.climateambassador.data.Section;
import com.cocacola.climateambassador.module.fragment.ModuleFragment;
import javax.inject.Inject;

/**
 * Created by realandylawton on 12/1/13.
 */
public class SectionModuleActivity extends AbsModuleActivity {

    public static final String EXTRA_SECTION_ID = "sectionId";

    @Inject DaoMaster mDaoMaster;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.module_activity);

        Long sectionId = getSectionIdFromIntent(getIntent());
        if(sectionId != null) {

            Section section = SectionModel.getSection(mDaoMaster, sectionId);
            Module module = section.getModules().get(0);

            if(module != null) {
                ModuleFragment fragment = ModuleFragment.newInstance(module.getId());
                setContentFragment(fragment);
            }

        }
        else {
            Toaster.toast(this, "Failed to load Module");
        }

    }

    private Long getSectionIdFromIntent(Intent intent) {

        if(!intent.hasExtra(EXTRA_SECTION_ID)) {
            return null;
        }

        return intent.getLongExtra(EXTRA_SECTION_ID, 0);

    }
}
