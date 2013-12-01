package com.cocacola.climateambassador.core.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.core.model.SectionModel;
import com.cocacola.climateambassador.core.util.Toaster;
import com.cocacola.climateambassador.data.Module;
import com.cocacola.climateambassador.data.Navigable;
import com.cocacola.climateambassador.data.Section;
import com.cocacola.climateambassador.drawer.adapters.DrawerListAdapter;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by realandylawton on 8/31/13.
 */
public abstract class CaDrawerSearchableActivity extends CaSearchableActivity implements
    AdapterView.OnItemClickListener {

    public DrawerLayout mDrawerLayout;
    public ListView mDrawerList;
    public DrawerListAdapter mMenuAdapter;
    public ActionBarDrawerToggle mDrawerToggle;

    private List<Navigable> mDrawerItems;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        //Set up the Navigation Drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.navigation_drawer_list);
        mDrawerList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Initialize the adapter
        mMenuAdapter = new DrawerListAdapter(this, getNavigationDrawerItems());
        mDrawerList.setAdapter(mMenuAdapter);
        mDrawerList.setOnItemClickListener(this);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
            this,                  /* host Activity */
            mDrawerLayout,         /* DrawerLayout object */
            R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
            R.string.drawer_open,  /* "open drawer" description for accessibility */
            R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        } else {
            // do nothing
        }
        return super.onOptionsItemSelected(item);
    }

    public List<Navigable> getNavigationDrawerItems() {

        if(mDrawerItems == null) {

            mDrawerItems = new LinkedList<Navigable>();

            try {

                // Add Favorites
                mDrawerItems.add(new Navigable() {
                    @Override public Long getId() {
                        return 0l;
                    }

                    @Override public String getTitle() {
                        return "Favorites";
                    }
                });

                // Get the sections
                List<Section> sections = SectionModel.getAllSections(mDaoMaster);
                for(Section section : sections) {

                    mDrawerItems.add(section);

                    for(Module module : section.getModules()) {
                        mDrawerItems.add(module);
                    }

                }

            } catch (Exception e) {
                Toaster.toast(this, "Failed to load Sections: " + e.getMessage());
            }

        }

        return mDrawerItems;

    }

    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //ModuleDrawerItem item = (ModuleDrawerItem) parent.getItemAtPosition(position);
        //if(item.getModule() == null) {
        //    return;
        //}
        //
        //mDrawerLayout.closeDrawer(mDrawerList);
        //
        //// FIXME Use a Fragment with 2 child fragments instead of Activity
        //if(item.getModule().getTitle().contains("Key Interventions")) {
        //
        //    Intent intent = new Intent(this, ModuleCasesActivity.class);
        //    intent.putExtra(AbsModuleActivity.EXTRA_MODULE_ID, item.getModule().getId());
        //
        //    startActivity(intent);
        //
        //}
        //else if(item.getModule().getTitle().contains("Sustainable")) {
        //
        //    ForSupplierOverviewFragment fragment = new ForSupplierOverviewFragment();
        //    setContentFragment(fragment);
        //
        //}
        //else if(item.getModule().getTitle().contains("2020 Vision")) {
        //    ForSuppliersVisionFragment fragment = ForSuppliersVisionFragment.newInstance(item.getModule().getId());
        //    setContentFragment(fragment);
        //}
        //else  {
        //    ModuleFragment fragment = ModuleFragment.newInstance(item.getModule().getId());
        //    setContentFragment(fragment);
        //}

    }

    protected void setTitle(String title) {
        getActionBar().setTitle(title);
    }

}
