package com.cocacola.climateambassador.ui;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.adapters.MenuListAdapter;
import com.cocacola.climateambassador.models.Case;
import com.cocacola.climateambassador.util.JsonAssetsLoader;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.OnClick;

public class MainActivity extends CaDrawerActivity implements SearchView.OnQueryTextListener {

    private final int INGREDIENTS_CASE_POS = 0;
    private final int BUSINESS_CASE_POS = 1;
    private final int KEY_INTERVENTIONS_POS = 2;
    private final int ENGAGING_SUPPLIERS_POS = 3;
    private final int SUPPLIERS_OVERVIEW_POS = 4;
    private final int SUPPLIER_GUIDE_POS = 5;

    private SearchView mSearchView;

    @Inject
    JsonAssetsLoader mJsonAssetsLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setupNavigationDrawer();
        setUpHomeScreen();

    }

    private void setUpHomeScreen() {
        MainFragment fragment = new MainFragment();
        getFragmentManager().beginTransaction().replace(R.id.main_content, fragment).commit();
    }

    @OnClick(R.id.home_btn_internal)
    public void startSectionActivity() {
        // TODO Actually launch a section
        Log.i("Launch a section");
    }

    @Override
    int getNavigationTitleArrayId() {
        return R.array.nav_sections_titles;
    }

    @Override
    int getNavigationIconArrayId() {
        return R.array.nav_sections_icons;
    }

    @Override
    void onDrawerItemClick(int position) {

        // TODO Use switch instead of if/elses
        if (position == INGREDIENTS_CASE_POS) {

            Case ingredientsCase = null;

            try {
                ingredientsCase = mJsonAssetsLoader.parseCaseFromJsonFile("ingredients.json");
            } catch (IOException e) {
                Toast.makeText(this, "Failed to Get Ingredients Case", Toast.LENGTH_SHORT);
            }

            CaseFragment fragment = CaseFragment.newInstance(ingredientsCase);

            getFragmentManager().beginTransaction().replace(R.id.main_content, fragment).commit();

            getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
            getActionBar().setDisplayShowTitleEnabled(true);
            getActionBar().setTitle(getResources().getString(R.string.for_suppliers));


        } else if (position == BUSINESS_CASE_POS) {

            ValueChainModule fragment = ValueChainModule.newInstance();
            getFragmentManager().beginTransaction().replace(R.id.main_content, fragment).commit();

            mDrawerLayout.closeDrawer(mDrawerList);

            getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
            getActionBar().setDisplayShowTitleEnabled(true);
            getActionBar().setTitle(getResources().getString(R.string.for_suppliers));


        } else if (position == KEY_INTERVENTIONS_POS) {

            SupplierOverview fragment = SupplierOverview.newInstance();
            getFragmentManager().beginTransaction().replace(R.id.main_content, fragment).commit();

            mDrawerLayout.closeDrawer(mDrawerList);

            getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
            getActionBar().setDisplayShowTitleEnabled(true);
            getActionBar().setTitle(getResources().getString(R.string.for_suppliers));


        } else if (position == ENGAGING_SUPPLIERS_POS) {


        } else if (position == SUPPLIERS_OVERVIEW_POS) {


        } else if (position == SUPPLIER_GUIDE_POS) {


        } else {
            //do nothing
            mDrawerLayout.closeDrawer(mDrawerList);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
        mSearchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setQueryHint("Search");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        //TODO: Handle Search View TextSubmit
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
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


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


}
