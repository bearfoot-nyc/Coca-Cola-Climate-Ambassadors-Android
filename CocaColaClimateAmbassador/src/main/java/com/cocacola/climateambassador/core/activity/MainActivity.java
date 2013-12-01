package com.cocacola.climateambassador.core.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;

import android.widget.Toast;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.core.model.DocumentModel;
import com.cocacola.climateambassador.core.util.AppPackageFileWriter;
import com.cocacola.climateambassador.core.util.DocumentIntentBuilder;
import com.cocacola.climateambassador.core.util.Toaster;
import com.cocacola.climateambassador.data.Document;
import com.cocacola.climateambassador.data.Module;
import com.cocacola.climateambassador.drawer.adapters.MainDrawerListAdapter;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.Section;

import com.cocacola.climateambassador.drawer.model.MainDrawerItem;
import com.cocacola.climateambassador.core.model.SectionModel;
import com.cocacola.climateambassador.core.util.DataChecker;
import com.cocacola.climateambassador.core.util.DataSeeder;
import com.cocacola.climateambassador.module.activity.AbsModuleActivity;
import com.cocacola.climateambassador.module.activity.ModuleActivity;
import com.cocacola.climateambassador.module.suppliers.activity.ForSuppliersOverviewActivity;
import java.util.LinkedList;
import java.util.List;

import butterknife.OnClick;
import javax.inject.Inject;

public class MainActivity extends CaSearchableDrawerActivity implements AdapterView.OnItemClickListener {

    @Inject protected DataChecker mDataChecker;
    @Inject protected DataSeeder mDataSeeder;
    @Inject protected DaoMaster mDaoMaster;

    private List<MainDrawerItem> mDrawerItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        // TODO Move this to a landing Activity
        if(!mDataChecker.isDataSeeded()) {

            try {
                mDataSeeder.seed();
            } catch (Exception e) {
                Toast.makeText(this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mMenuAdapter = new MainDrawerListAdapter(this, getNavigationDrawerItems());
        mDrawerList.setAdapter(mMenuAdapter);
        mDrawerList.setOnItemClickListener(this);

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

    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        MainDrawerItem item = (MainDrawerItem) parent.getItemAtPosition(position);

        Intent intent = createIntentForSection(item.getSection());
        startActivity(intent);

    }

    private Intent createIntentForSection(String sectionName) {

        Long sectionId = sectionName.contains("Internal") ?  1l : 2l;

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

        if(section.getName().contains("Internal")) {
            fragmentClazz = ModuleActivity.class;
        }
        else {
            fragmentClazz = ForSuppliersOverviewActivity.class;
        }

        Intent intent = new Intent(this, fragmentClazz);
        intent.putExtra(AbsModuleActivity.EXTRA_MODULE_ID, module.getId());

        return intent;

    }

    public List<MainDrawerItem> getNavigationDrawerItems() {

        mDrawerItems = new LinkedList<MainDrawerItem>();
        List<Section> sectionList = null;

        try {
            sectionList = SectionModel.getAllSections(mDaoMaster);
        } catch (Exception e) {
            Toast.makeText(this, "Failed to load Sections: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return mDrawerItems;
        }

        for(Section section : sectionList) {
            MainDrawerItem item = new MainDrawerItem(section);
            mDrawerItems.add(item);
        }

        return mDrawerItems;

    }

    @Override public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override public boolean onQueryTextChange(String newText) {

        updateDocumentSuggestions(newText);

        return true;

    }

    @Override public boolean onSuggestionSelect(int position) {
        return false;
    }

    @Override public boolean onSuggestionClick(int position) {

        Document doc = getSuggestedDocument(position);

        try {

            Intent intent = mDocumentIntentBuilder.createViewerIntent(this, doc);

            startActivity(intent);

        } catch (AppPackageFileWriter.PackageWriteException e) {
            Toaster.toast(this, e.getMessage());
        } catch (DocumentIntentBuilder.PreferredAppNotInstalledException e) {
            Toaster.toast(this, e.getMessage());
        } catch (ActivityNotFoundException e) {
            Toaster.toast(this, e.getMessage());
        }

        return true;

    }
}
