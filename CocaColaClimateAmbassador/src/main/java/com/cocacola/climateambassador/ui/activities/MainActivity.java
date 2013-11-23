package com.cocacola.climateambassador.ui.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.HandlerThread;
import android.view.Menu;
import android.view.Window;
import android.widget.SearchView;

import android.widget.Toast;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.adapters.MenuListAdapter;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.Section;
import com.cocacola.climateambassador.json.NavigationDrawerItem;

import com.cocacola.climateambassador.models.SectionModel;
import com.cocacola.climateambassador.util.DataChecker;
import com.cocacola.climateambassador.util.DataSeeder;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import butterknife.OnClick;
import butterknife.Views;
import javax.inject.Inject;

public class MainActivity extends CaDrawerActivity implements SearchView.OnQueryTextListener {

    @Inject protected DataChecker mDataChecker;
    @Inject protected DataSeeder mDataSeeder;
    @Inject protected DaoMaster mDaoMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        setContentView(R.layout.activity_main);
        Views.inject(this);

        setupNavigationDrawer();

        if(!mDataChecker.isDataSeeded()) {
            seedDataIfNotAvailable();
        }

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


    public void seedDataIfNotAvailable() {
        new ShitAsyncTask().execute();
    }

    private class ShitAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override protected void onPreExecute() {
            super.onPreExecute();
            // Show loading
            setProgressBarIndeterminateVisibility(true);
        }

        @Override protected Void doInBackground(Void... params) {
            try {
                mDataSeeder.seed();
            } catch (Exception e) {
                return null;
            }

            return null;
        }

        @Override protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setProgressBarIndeterminateVisibility(false);
        }
    }

    @Override public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override public boolean onQueryTextChange(String newText) {
        return false;
    }
}
