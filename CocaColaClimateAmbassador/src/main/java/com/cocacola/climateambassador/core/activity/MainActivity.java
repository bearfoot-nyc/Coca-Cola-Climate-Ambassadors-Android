package com.cocacola.climateambassador.core.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import butterknife.OnClick;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.Module;
import com.cocacola.climateambassador.data.Navigable;
import com.cocacola.climateambassador.data.Section;
import com.cocacola.climateambassador.favorites.activity.FavoritesActivity;
import com.cocacola.climateambassador.module.activity.AbsModuleActivity;
import com.cocacola.climateambassador.module.activity.ModuleActivity;
import com.cocacola.climateambassador.module.suppliers.activity.ForSuppliersOverviewActivity;
import javax.inject.Inject;

public class MainActivity extends CaDrawerSearchableActivity {

    @Inject protected DaoMaster mDaoMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

    }


    @OnClick({ R.id.home_btn_internal, R.id.home_btn_suppliers })
    public void onClickInternal(View view) {

        Intent intent = null;

        switch (view.getId()) {
            case R.id.home_btn_internal:
                intent = createIntentForSection("Internal Training");
                break;
            case R.id.home_btn_suppliers:
                intent = createIntentForSection("For Suppliers");
                break;
        }

       startActivity(intent);

    }

    private Intent createIntentForSection(String sectionTitle) {

        Long sectionId = sectionTitle.contains("Internal") ?  1l : 2l;

        Section section = mDaoMaster.newSession().getSectionDao().load(sectionId);

        if(section == null) {
            return null;
        }

        return createIntentForSection(section);

    }

    private Intent createIntentForSection(Section section) {

        Module module = section.getModules().get(0);
        if(module == null) {
            Log.w("Null module");
            return null;
        }

        Class<?> fragmentClazz = null;

        if(section.getTitle().contains("Internal")) {
            fragmentClazz = ModuleActivity.class;
        }
        else {
            fragmentClazz = ForSuppliersOverviewActivity.class;
        }

        Intent intent = new Intent(this, fragmentClazz);
        intent.putExtra(AbsModuleActivity.EXTRA_MODULE_ID, module.getId());

        return intent;

    }

}
