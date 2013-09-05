package com.cocacola.climateambassador.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SearchView;

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.adapters.MenuListAdapter;
import com.cocacola.climateambassador.models.NavigationDrawerItem;

import java.util.LinkedList;
import java.util.List;

import butterknife.OnClick;

public class MainActivity extends CaDrawerActivity implements SearchView.OnQueryTextListener {

    private SearchView mSearchView;

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
    public void onClickInternal() {
        launchSectionActivity(InternalTrainingActivity.class);
    }

    @OnClick(R.id.home_btn_suppliers)
    public void onClickSuppliers() {
        launchSectionActivity(ForSuppliersActivity.class);
    }

    private void launchSectionActivity(Class<? extends SectionActivity> activityClz) {
        Intent intent = new Intent(this, activityClz);
        startActivity(intent);
        // TODO Close the drawer
    }

    @Override
    MenuListAdapter getMenuListAdapter() {
        return new MenuListAdapter(this, getNavigationDrawerItems());
    }

    @Override
    List<NavigationDrawerItem> getNavigationDrawerItems() {

        if(mNavigationDrawerItems == null) {
            mNavigationDrawerItems = new LinkedList<NavigationDrawerItem>();
            mNavigationDrawerItems.add(new NavigationDrawerItem("Internal Trainings", R.drawable.ic_drawer_wrench, false, InternalTrainingActivity.class));
            mNavigationDrawerItems.add(new NavigationDrawerItem("For Suppliers", R.drawable.ic_drawer_folder, false, ForSuppliersActivity.class));
        }

        return mNavigationDrawerItems;

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

    // FIXME @Vinnie use Activities instead of Fragments for the cases/modules

     /*
    private void onDrawerItemClick(int position) {

        CaFragment fragment;

        switch(position) {
            case FAVORITES_CASE_POS:

                break;
            case MODULE_ONE_POS:

                Module moduleOne = null;

                try {
                    moduleOne = mJsonAssetsLoader.parseModuleFromJsonFile("module_one.json");
                } catch (IOException e) {
                    Toast.makeText(this, "Failed to Get Module One", Toast.LENGTH_SHORT);
                }

                fragment = ModuleFragment.newInstance(moduleOne);

                getFragmentManager().beginTransaction().replace(R.id.main_content, fragment).commit();

                mDrawerLayout.closeDrawer(mDrawerList);

                getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                getActionBar().setDisplayShowTitleEnabled(true);
                getActionBar().setTitle(getResources().getString(R.string.for_suppliers));

                break;
            case MODULE_TWO_POS:

                Module moduleTwo = null;

                try {
                    moduleTwo = mJsonAssetsLoader.parseModuleFromJsonFile("module_two.json");
                } catch (IOException e) {
                    Toast.makeText(this, "Failed to Get Module Two", Toast.LENGTH_SHORT);
                }

                fragment = ModuleFragment.newInstance(moduleTwo);

                getFragmentManager().beginTransaction().replace(R.id.main_content, fragment).commit();

                mDrawerLayout.closeDrawer(mDrawerList);

                getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                getActionBar().setDisplayShowTitleEnabled(true);
                getActionBar().setTitle(getResources().getString(R.string.for_suppliers));

                break;
            case MODULE_THREE_POS:

                fragment = ValueChainModule.newInstance();
                getFragmentManager().beginTransaction().replace(R.id.main_content, fragment).commit();

                mDrawerLayout.closeDrawer(mDrawerList);

                getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                getActionBar().setDisplayShowTitleEnabled(true);
                getActionBar().setTitle(getResources().getString(R.string.for_suppliers));

                break;
            case INGREDIENTS_CASE_POS:

                Case ingredientsCase = null;

                try {
                    ingredientsCase = mJsonAssetsLoader.parseCaseFromJsonFile("ingredients.json");
                } catch (IOException e) {
                    Toast.makeText(this, "Failed to Get Ingredients Case", Toast.LENGTH_SHORT);
                }

                fragment = CaseFragment.newInstance(ingredientsCase);

                getFragmentManager().beginTransaction().replace(R.id.main_content, fragment).commit();

                mDrawerLayout.closeDrawer(mDrawerList);

                getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                getActionBar().setDisplayShowTitleEnabled(true);
                getActionBar().setTitle(getResources().getString(R.string.for_suppliers));


                break;
            case PACKAGING_CASE_POS:

                Case packagingCase = null;

                try {
                    packagingCase = mJsonAssetsLoader.parseCaseFromJsonFile("packaging.json");
                } catch (IOException e) {
                    Toast.makeText(this, "Failed to Get Packaging Case", Toast.LENGTH_SHORT);
                }

                fragment = CaseFragment.newInstance(packagingCase);

                getFragmentManager().beginTransaction().replace(R.id.main_content, fragment).commit();

                mDrawerLayout.closeDrawer(mDrawerList);

                getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                getActionBar().setDisplayShowTitleEnabled(true);
                getActionBar().setTitle(getResources().getString(R.string.for_suppliers));

                break;
            case MANUFACTURING_CASE_POS:

                Case manufacturingCase = null;

                try {
                    manufacturingCase = mJsonAssetsLoader.parseCaseFromJsonFile("manufacturing.json");
                } catch (IOException e) {
                    Toast.makeText(this, "Failed to Get manufacturing Case", Toast.LENGTH_SHORT);
                }

                fragment = CaseFragment.newInstance(manufacturingCase);

                getFragmentManager().beginTransaction().replace(R.id.main_content, fragment).commit();

                mDrawerLayout.closeDrawer(mDrawerList);

                getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                getActionBar().setDisplayShowTitleEnabled(true);
                getActionBar().setTitle(getResources().getString(R.string.for_suppliers));

                break;
            case DISTRIBUTION_CASE_POS:

                Case distributionCase = null;

                try {
                    distributionCase = mJsonAssetsLoader.parseCaseFromJsonFile("distribution.json");
                } catch (IOException e) {
                    Toast.makeText(this, "Failed to Get distributionCase", Toast.LENGTH_SHORT);
                }

                fragment = CaseFragment.newInstance(distributionCase);

                getFragmentManager().beginTransaction().replace(R.id.main_content, fragment).commit();

    */

}
