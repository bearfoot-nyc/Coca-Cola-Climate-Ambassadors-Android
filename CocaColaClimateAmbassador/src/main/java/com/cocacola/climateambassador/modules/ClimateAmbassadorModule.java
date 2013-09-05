package com.cocacola.climateambassador.modules;

import android.content.Context;

import com.cocacola.climateambassador.BuildConfig;
import com.cocacola.climateambassador.CaApplication;
import com.cocacola.climateambassador.DocumentViewerDelegate;
import com.cocacola.climateambassador.ui.CaseFragment;
import com.cocacola.climateambassador.ui.CaseStudiesListFragment;
import com.cocacola.climateambassador.ui.DistributionCaseActivity;
import com.cocacola.climateambassador.ui.FavoritesFragment;
import com.cocacola.climateambassador.ui.ForSuppliersActivity;
import com.cocacola.climateambassador.ui.ForSuppliersBodyFragment;
import com.cocacola.climateambassador.ui.IngredientCaseActivity;
import com.cocacola.climateambassador.ui.InternalTrainingActivity;
import com.cocacola.climateambassador.ui.InternalTrainingBodyFragment;
import com.cocacola.climateambassador.ui.MainActivity;
import com.cocacola.climateambassador.ui.ManufacturingCaseActivity;
import com.cocacola.climateambassador.ui.ModuleFragment;
import com.cocacola.climateambassador.ui.PackagingCaseActivity;
import com.cocacola.climateambassador.ui.RefrigerationCaseActivity;
import com.cocacola.climateambassador.ui.ValueChainModule;
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
        ValueChainModule.class,
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
