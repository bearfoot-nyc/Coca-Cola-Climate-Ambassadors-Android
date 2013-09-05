package com.cocacola.climateambassador.modules;

import android.content.Context;

import com.cocacola.climateambassador.BuildConfig;
import com.cocacola.climateambassador.CaApplication;
import com.cocacola.climateambassador.DocumentViewerDelegate;
import com.cocacola.climateambassador.ui.activities.DistributionCaseActivity;
import com.cocacola.climateambassador.ui.activities.ForSuppliersActivity;
import com.cocacola.climateambassador.ui.activities.IngredientCaseActivity;
import com.cocacola.climateambassador.ui.activities.InternalTrainingActivity;
import com.cocacola.climateambassador.ui.activities.MainActivity;
import com.cocacola.climateambassador.ui.activities.ManufacturingCaseActivity;
import com.cocacola.climateambassador.ui.activities.PackagingCaseActivity;
import com.cocacola.climateambassador.ui.activities.RefrigerationCaseActivity;
import com.cocacola.climateambassador.ui.fragments.CaseFragment;
import com.cocacola.climateambassador.ui.fragments.CaseStudiesListFragment;
import com.cocacola.climateambassador.ui.fragments.FavoritesFragment;
import com.cocacola.climateambassador.ui.fragments.ForSuppliersBodyFragment;
import com.cocacola.climateambassador.ui.fragments.InternalTrainingBodyFragment;
import com.cocacola.climateambassador.ui.fragments.ModuleFragment;
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
        CaseFragment.class,
        ModuleFragment.class,
        FavoritesFragment.class,
        InternalTrainingActivity.class,
        ForSuppliersActivity.class,
        ForSuppliersBodyFragment.class,
        DocumentViewerDelegate.class,
        JsonAssetsLoader.class,
        CaseStudiesListFragment.class,
        InternalTrainingBodyFragment.class,
        PackagingCaseActivity.class,
        ManufacturingCaseActivity.class,
        DistributionCaseActivity.class,
        RefrigerationCaseActivity.class,
        IngredientCaseActivity.class
})
public class ClimateAmbassadorModule {

    private Context mContext;

    public ClimateAmbassadorModule() { }

    public ClimateAmbassadorModule(Context context) {
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

}
