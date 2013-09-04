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
import com.cocacola.climateambassador.models.Module;
import com.cocacola.climateambassador.util.JsonAssetsLoader;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.OnClick;

public class MainActivity extends CaActivity implements SearchView.OnQueryTextListener {




    private final int FAVORITES_CASE_POS = 0;
    private final int MODULE_ONE_POS = 2;
    private final int MODULE_TWO_POS = 3;
    private final int MODULE_THREE_POS = 4;
    private final int INGREDIENTS_CASE_POS = 5;
    private final int PACKAGING_CASE_POS = 6;
    private final int MANUFACTURING_CASE_POS = 7;
    private final int DISTRIBUTION_CASE_POS = 8;
    private final int REFRIGERATION_CASE_POS = 9;
    private final int MODULE_FOUR_POS = 10;
    private final int FOR_SUPPLIERS = 11;
    private final int SUPPLIER_LAST_PAGE = 12;


    public String[] mDrawerOptions = { "FAVORITES" , "INTERNAL TRAINING MATERIALS", "Module 1: Emissions & Our Business", "Module 2: Business Case" , "Module 3: Supply Chain Implementation",
                                            "Ingredient Cases", "Packaging Case" , "Manufacturing Case" , "Distribution Case" , "Refrigeration Case" , "Module 4: Engaging Suppliers" ,
                                                    "FOR SUPPLIERS", "OUR 2020 VISION" };
    public ListView mDrawerList;
    public DrawerLayout mDrawerLayout;
    private MenuListAdapter mMenuAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    int[] icon;

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

    private void setupNavigationDrawer() {

        //Set up the Navigation Drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        // set up the drawer's list view with items and click listener


        //Removed Subtitle
        icon = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        mMenuAdapter = new com.cocacola.climateambassador.adapters.MenuListAdapter(this, mDrawerOptions, icon);

        mDrawerList.setAdapter(mMenuAdapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

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
                // getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                // getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
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

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            onDrawerItemClick(position);
        }
    }

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

                mDrawerLayout.closeDrawer(mDrawerList);

                getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                getActionBar().setDisplayShowTitleEnabled(true);
                getActionBar().setTitle(getResources().getString(R.string.for_suppliers));

                break;
            case REFRIGERATION_CASE_POS:

                Case refrigerationCase = null;

                try {
                    refrigerationCase = mJsonAssetsLoader.parseCaseFromJsonFile("refrigeration.json");
                } catch (IOException e) {
                    Toast.makeText(this, "Failed to Get refrigeration Case", Toast.LENGTH_SHORT);
                }

                fragment = CaseFragment.newInstance(refrigerationCase);

                getFragmentManager().beginTransaction().replace(R.id.main_content, fragment).commit();

                mDrawerLayout.closeDrawer(mDrawerList);

                getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                getActionBar().setDisplayShowTitleEnabled(true);
                getActionBar().setTitle(getResources().getString(R.string.for_suppliers));

                break;
            case MODULE_FOUR_POS:

                Module moduleFour = null;

                try {
                    moduleFour = mJsonAssetsLoader.parseModuleFromJsonFile("module_four.json");
                } catch (IOException e) {
                    Toast.makeText(this, "Failed to Get Module Four", Toast.LENGTH_SHORT);
                }

                fragment = ModuleFragment.newInstance(moduleFour);

                getFragmentManager().beginTransaction().replace(R.id.main_content, fragment).commit();

                mDrawerLayout.closeDrawer(mDrawerList);

                getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                getActionBar().setDisplayShowTitleEnabled(true);
                getActionBar().setTitle(getResources().getString(R.string.for_suppliers));

                break;
            case FOR_SUPPLIERS:

                fragment = SupplierOverview.newInstance();
                getFragmentManager().beginTransaction().replace(R.id.main_content, fragment).commit();

                mDrawerLayout.closeDrawer(mDrawerList);

                getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                getActionBar().setDisplayShowTitleEnabled(true);
                getActionBar().setTitle(getResources().getString(R.string.for_suppliers));

                break;
            case SUPPLIER_LAST_PAGE:

                Module lastPage = null;

                try {
                    lastPage = mJsonAssetsLoader.parseModuleFromJsonFile("supplier_last_page.json");
                } catch (IOException e) {
                    Toast.makeText(this, "Failed to Get Supplier Last Page", Toast.LENGTH_SHORT);
                }

                fragment = ModuleFragment.newInstance(lastPage);

                getFragmentManager().beginTransaction().replace(R.id.main_content, fragment).commit();

                mDrawerLayout.closeDrawer(mDrawerList);

                getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                getActionBar().setDisplayShowTitleEnabled(true);
                getActionBar().setTitle(getResources().getString(R.string.for_suppliers));

                break;
            default:
                //do nothing
                mDrawerLayout.closeDrawer(mDrawerList);
                break;
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
