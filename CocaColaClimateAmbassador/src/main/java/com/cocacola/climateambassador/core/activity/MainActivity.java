package com.cocacola.climateambassador.core.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;

import android.widget.Toast;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.drawer.adapters.MainDrawerListAdapter;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.Section;

import com.cocacola.climateambassador.drawer.model.MainDrawerItem;
import com.cocacola.climateambassador.core.model.SectionModel;
import com.cocacola.climateambassador.core.util.DataChecker;
import com.cocacola.climateambassador.core.util.DataSeeder;
import com.cocacola.climateambassador.module.activity.ModuleActivity;
import com.cocacola.climateambassador.section.activity.ForSuppliersActivity;
import com.cocacola.climateambassador.section.activity.InternalTrainingActivity;
import java.util.LinkedList;
import java.util.List;

import butterknife.OnClick;
import javax.inject.Inject;

public class MainActivity extends CaDrawerActivity implements SearchView.OnQueryTextListener,
    AdapterView.OnItemClickListener {

    @Inject protected DataChecker mDataChecker;
    @Inject protected DataSeeder mDataSeeder;
    @Inject protected DaoMaster mDaoMaster;

    private List<MainDrawerItem> mDrawerItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.main_activity);

        super.onCreate(savedInstanceState);

        // TODO Move this to a landing Activity
        if(!mDataChecker.isDataSeeded()) {

            try {
                mDataSeeder.seed();
            } catch (Exception e) {
                Toast.makeText(this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }

        }

        mMenuAdapter = new MainDrawerListAdapter(this, getNavigationDrawerItems());
        mDrawerList.setAdapter(mMenuAdapter);
        mDrawerList.setOnItemClickListener(this);


    }

    @OnClick(R.id.home_btn_internal)
    public void onClickInternal() {
        launchSectionActivity(InternalTrainingActivity.class);
    }

    @OnClick(R.id.home_btn_suppliers)
    public void onClickSuppliers() {
        launchSectionActivity(ForSuppliersActivity.class);
    }

    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        MainDrawerItem item = (MainDrawerItem) parent.getItemAtPosition(position);

        Intent intent = new Intent(this, ModuleActivity.class);
        intent.putExtra(ModuleActivity.MODULE_ID_BUNDLE_KEY, item.getSection().getModules().get(0).getId());

        startActivity(intent);

    }

    public List<MainDrawerItem> getNavigationDrawerItems() {

        mDrawerItems = new LinkedList<MainDrawerItem>();
        List<Section> sectionList = null;

        try {
            sectionList = SectionModel.getAllSections(mDaoMaster);
        } catch (Exception e) {
            Toast.makeText(this, "Failed to load Sections", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        for(Section section : sectionList) {
            MainDrawerItem item = new MainDrawerItem(section);
            mDrawerItems.add(item);
        }

        return mDrawerItems;

    }

    private void launchSectionActivity(Class<? extends ModuleActivity> activityClz) {
        Intent intent = new Intent(this, activityClz);
        startActivity(intent);
        // TODO Close the drawer
    }

    @Override public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override public boolean onQueryTextChange(String newText) {
        return false;
    }
}
