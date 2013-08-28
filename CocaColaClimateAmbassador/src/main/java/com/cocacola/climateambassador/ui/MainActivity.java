package com.cocacola.climateambassador.ui;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.cocacola.climateambassador.Adapters.MenuListAdapter;
import com.cocacola.climateambassador.AppPackageFileWriter;
import com.cocacola.climateambassador.DocumentViewerDelegate;
import com.cocacola.climateambassador.R;

import java.io.File;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Views;

public class MainActivity extends CaActivity implements SearchView.OnQueryTextListener{

    private final String welcomeString = "WELCOME TO THE COCA COLA CLIMATE AMBASSADOR APP!";
    private final String toolDescriptionString = "This tool connects you with training materials and presentations to engage colleagues and " +
            "suppliers in reducing carbon along your value chain.";
    private final String navString = "Click below to access our Internal Training Content and Tools for " +
            "External Supplier Meetings, or use the side menu to navigate all content";


    public String[] mDrawerOptions = {"Add Nav Drawer Options Here"};
    public ListView mDrawerList;
    public DrawerLayout mDrawerLayout;
    static MenuListAdapter mMenuAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    int []icon;

    private SearchView mSearchView;

    @InjectView(R.id.welcome) TextView welcomeText;
    @InjectView(R.id.tool_description) TextView toolDescriptionText;
    @InjectView(R.id.navigation_text) TextView navigationText;
    @Inject DocumentViewerDelegate mDocumentManager;
    @Inject
    AppPackageFileWriter mAppPackageFileWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Views.inject(this);

        welcomeText.setText(welcomeString);
        toolDescriptionText.setText(toolDescriptionString);
        navigationText.setText(navString);


        //Set up the Navigation Drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener


        //Removed Subtitle
        icon = new int[] {0, 0,  0, 0};

        mMenuAdapter = new MenuListAdapter(this, mDrawerOptions, icon);

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


        mAppPackageFileWriter.writeAssetsToPackageDir();

        File filesDir = getFilesDir();
        String[] list = filesDir.list();

        Log.i("list.length=%s", list.length);

        for(String filename : list) {
            Log.i("filename=%s", filename);
        }

    }

    @OnClick(R.id.home_btn_internal)
    public void startSectionActivity() {
        try {
            mDocumentManager.startPdfViewerActivity("resume.pdf", this);
        } catch(ActivityNotFoundException e) {
            // TODO Launch an intent to download in Play Store
            Log.e(e, "No Activity for Viewing PDFs");
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {

        // update the main content by replacing fragments
        if(position == 0) {

            mDrawerLayout.closeDrawer(mDrawerList);
            getActionBar().setDisplayShowTitleEnabled(false);
            getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        }  else if(position == 1) {


            mDrawerLayout.closeDrawer(mDrawerList);

            getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
            getActionBar().setDisplayShowTitleEnabled(true);
            getActionBar().setTitle("Your Watchlist");


        }else {
            //do nothing
            mDrawerLayout.closeDrawer(mDrawerList);
        }




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search_support);
        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
        mSearchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setQueryHint("Search");

        super.onCreateOptionsMenu(menu);



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



}
