package com.cocacola.climateambassador.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.cocacola.climateambassador.BuildConfig;
import com.cocacola.climateambassador.cases.activity.CaseActivity;
import com.cocacola.climateambassador.core.activity.MainActivity;
import com.cocacola.climateambassador.core.model.SectionModel;
import com.cocacola.climateambassador.core.util.DataChecker;
import com.cocacola.climateambassador.core.util.DataSeeder;
import com.cocacola.climateambassador.core.util.DocumentIntentBuilder;
import com.cocacola.climateambassador.core.util.DocumentUriBuilder;
import com.cocacola.climateambassador.core.util.JsonAssetsLoader;
import com.cocacola.climateambassador.core.views.DocumentView;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.favorites.fragment.FavoritesFragment;
import com.cocacola.climateambassador.module.activity.ModuleActivity;
import com.cocacola.climateambassador.module.fragment.ModuleFragment;
import com.cocacola.climateambassador.module.internal.activity.ModuleCasesActivity;
import com.cocacola.climateambassador.module.internal.activity.RoleOfClimateAmbassadorActivity;
import com.cocacola.climateambassador.module.internal.fragment.CaseStudiesListFragment;
import com.cocacola.climateambassador.module.internal.fragment.InternalTrainingOverviewFragment;
import com.cocacola.climateambassador.module.internal.fragment.RoleOfClimateAmbassadorFragment;
import com.cocacola.climateambassador.module.suppliers.activity.ForSuppliersOverviewActivity;
import com.cocacola.climateambassador.module.suppliers.fragment.ForSuppliersVisionFragment;
import com.cocacola.climateambassador.module.suppliers.fragment.ForSupplierOverviewFragment;
import com.cocacola.climateambassador.section.activity.ForSuppliersActivity;
import com.cocacola.climateambassador.section.activity.InternalTrainingActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import timber.log.Timber;

/**
 * Created by andrewlawton on 8/21/13.
 */

@Module(injects = {
        CaApplication.class,
        MainActivity.class,
        FavoritesFragment.class,
        InternalTrainingActivity.class,
        ForSuppliersActivity.class,
        ForSupplierOverviewFragment.class,
        JsonAssetsLoader.class,
        CaseStudiesListFragment.class,
        InternalTrainingOverviewFragment.class,
        RoleOfClimateAmbassadorFragment.class,
        RoleOfClimateAmbassadorActivity.class,
        ForSuppliersVisionFragment.class,
        DocumentView.class,
        DataSeeder.class, DataChecker.class, SectionModel.class, ModuleActivity.class,
        ModuleFragment.class, CaseActivity.class, CaseStudiesListFragment.class, ForSupplierOverviewFragment.class,
        ForSuppliersOverviewActivity.class, ModuleCasesActivity.class, ForSuppliersVisionFragment.class,
        DocumentUriBuilder.class, DocumentIntentBuilder.class
})
public class CaProdModule {

    private Context mContext;

    public CaProdModule() { }

    public CaProdModule(Context context) {
        mContext = context;
    }

    @Provides @Singleton
    Context provideContext() {
        return mContext;
    }

    @Provides @Singleton
    Timber provideTimber() {
        return BuildConfig.DEBUG ? Timber.DEBUG : Timber.PROD;
    }

    @Provides
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides @Singleton SQLiteOpenHelper provideSQLiteOpenHelper(Context context) {
        return new DaoMaster.DevOpenHelper(context, "climateambassador", null);
    }

    @Provides @Singleton DaoMaster provideDaoMaster(SQLiteOpenHelper openHelper) {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster;
    }

}
