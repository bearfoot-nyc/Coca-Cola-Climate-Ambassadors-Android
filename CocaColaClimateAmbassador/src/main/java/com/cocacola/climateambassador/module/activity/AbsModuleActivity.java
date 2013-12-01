package com.cocacola.climateambassador.module.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.widget.AdapterView;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.core.activity.CaDrawerSearchableActivity;
import com.cocacola.climateambassador.core.fragment.CaFragment;
import com.cocacola.climateambassador.data.DaoMaster;
import javax.inject.Inject;

/**
 * Created by realandylawton on 11/24/13.
 */
public class AbsModuleActivity extends CaDrawerSearchableActivity implements AdapterView.OnItemClickListener {

    public static final int MODULE_TYPE_INTERVENTION = 1;
    public static final int MODULE_TYPE_SUSTAINABLE = 2;
    public static final int MODULE_TYPE_VISION = 3;
    public static final String EXTRA_MODULE_ID = "moduleId";
    public static final String EXTRA_MODULE_TYPE = "fragmentClazz";

    @Inject protected DaoMaster mDaoMaster;

    protected void setContentFragment(CaFragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.module_frag_container, fragment);

        transaction.commit();
    }

    protected Long getModuleIdFromIntent(Intent intent) {

        if(!intent.hasExtra(EXTRA_MODULE_ID)) {
            return null;
        }

        Long id = intent.getLongExtra(EXTRA_MODULE_ID, 0);

        return id;

    }

    protected Integer getModuleTypeFromIntent(Intent intent) {

        if(!intent.hasExtra(EXTRA_MODULE_TYPE)) {
            return 0;
        }

        return intent.getIntExtra(EXTRA_MODULE_TYPE, 0);

    }

}
