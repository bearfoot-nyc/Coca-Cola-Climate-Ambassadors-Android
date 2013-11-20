package com.cocacola.climateambassador.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SearchView;

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.adapters.MenuListAdapter;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.Section;
import com.cocacola.climateambassador.json.NavigationDrawerItem;

import com.cocacola.climateambassador.models.SectionModel;
import java.util.LinkedList;
import java.util.List;

import butterknife.OnClick;
import butterknife.Views;
import javax.inject.Inject;

public class MainActivity extends CaDrawerActivity implements SearchView.OnQueryTextListener {

    @Inject protected DaoMaster mDaoMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Views.inject(this);
        setupNavigationDrawer();

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

            try {
                List<Section> sectionList = SectionModel.getAllSections(mDaoMaster);
                for(Section section : sectionList) {
                    mNavigationDrawerItems.add(new NavigationDrawerItem(section.getName(), R.drawable.ic_drawer_wrench, false, InternalTrainingActivity.class));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return mNavigationDrawerItems;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

//        getMenuInflater().inflate(R.menu.main, menu);
//        SearchManager searchManager = (SearchManager) this.getSystemService(SEARCH_SERVICE);
//        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
//        mSearchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
//        mSearchView.setOnQueryTextListener(this);
//        mSearchView.setQueryHint("Search");

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

}
