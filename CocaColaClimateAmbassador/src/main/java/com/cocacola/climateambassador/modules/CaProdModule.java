package com.cocacola.climateambassador.modules;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.cocacola.climateambassador.BuildConfig;
import com.cocacola.climateambassador.CaApplication;
import com.cocacola.climateambassador.DocumentViewerDelegate;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.models.SectionModel;
import com.cocacola.climateambassador.ui.activities.ClimateAmbassadorActivity;
import com.cocacola.climateambassador.ui.activities.DistributionCaseActivity;
import com.cocacola.climateambassador.ui.activities.ForSuppliersActivity;
import com.cocacola.climateambassador.ui.activities.IngredientCaseActivity;
import com.cocacola.climateambassador.ui.activities.InternalTrainingActivity;
import com.cocacola.climateambassador.ui.activities.MainActivity;
import com.cocacola.climateambassador.ui.activities.ManufacturingCaseActivity;
import com.cocacola.climateambassador.ui.activities.ModuleActivity;
import com.cocacola.climateambassador.ui.activities.ModuleFourActivity;
import com.cocacola.climateambassador.ui.activities.ModuleOneActivity;
import com.cocacola.climateambassador.ui.activities.ModuleThreeActivity;
import com.cocacola.climateambassador.ui.activities.ModuleTwoActivity;
import com.cocacola.climateambassador.ui.activities.PackagingCaseActivity;
import com.cocacola.climateambassador.ui.activities.RefrigerationCaseActivity;
import com.cocacola.climateambassador.ui.activities.VisionActivity;
import com.cocacola.climateambassador.ui.fragments.CaseStudiesListFragment;
import com.cocacola.climateambassador.ui.fragments.ClimateAmbassadorFragmentSimple;
import com.cocacola.climateambassador.ui.fragments.FavoritesFragment;
import com.cocacola.climateambassador.ui.fragments.ForSuppliersBodyFragment;
import com.cocacola.climateambassador.ui.fragments.InternalTrainingOverviewFragmentSimple;
import com.cocacola.climateambassador.ui.fragments.SimpleModuleFourFragment;
import com.cocacola.climateambassador.ui.fragments.SimpleModuleFragment;
import com.cocacola.climateambassador.ui.fragments.ModuleFragment;
import com.cocacola.climateambassador.ui.fragments.SimpleModuleOneFragment;
import com.cocacola.climateambassador.ui.fragments.SimpleModuleThreeFragment;
import com.cocacola.climateambassador.ui.fragments.SimpleModuleTwoFragment;
import com.cocacola.climateambassador.ui.views.DocumentView;
import com.cocacola.climateambassador.util.DataChecker;
import com.cocacola.climateambassador.util.DataSeeder;
import com.cocacola.climateambassador.util.JsonAssetsLoader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

/**
 * Created by andrewlawton on 8/21/13.
 */

@Module(injects = {
        CaApplication.class,
        MainActivity.class,
        ModuleFragment.class,
        FavoritesFragment.class,
        InternalTrainingActivity.class,
        ForSuppliersActivity.class,
        ForSuppliersBodyFragment.class,
        DocumentViewerDelegate.class,
        JsonAssetsLoader.class,
        CaseStudiesListFragment.class,
        InternalTrainingOverviewFragmentSimple.class,
        PackagingCaseActivity.class,
        ManufacturingCaseActivity.class,
        DistributionCaseActivity.class,
        RefrigerationCaseActivity.class,
        IngredientCaseActivity.class,
        ClimateAmbassadorFragmentSimple.class,
        SimpleModuleOneFragment.class,
        SimpleModuleTwoFragment.class,
        SimpleModuleThreeFragment.class,
        SimpleModuleFourFragment.class,
        ClimateAmbassadorActivity.class,
        ModuleOneActivity.class,
        ModuleTwoActivity.class,
        ModuleThreeActivity.class,
        ModuleFourActivity.class,
        VisionActivity.class,
        DocumentView.class,
        DataSeeder.class, DataChecker.class, SectionModel.class, ModuleActivity.class,
        SimpleModuleFragment.class
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
        return new DaoMaster.DevOpenHelper(context, "titty-sprinkles", null);
    }

    @Provides @Singleton DaoMaster provideDaoMaster(SQLiteOpenHelper openHelper) {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster;
    }

}
