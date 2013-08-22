package com.cocacola.climateambassador.modules;

import android.content.Context;

import com.cocacola.climateambassador.BuildConfig;
import com.cocacola.climateambassador.CaApplication;
import com.cocacola.climateambassador.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

/**
 * Created by andrewlawton on 8/21/13.
 */

@Module(injects = {
        CaApplication.class,
        MainActivity.class
})
public class ClimateAmbassadorModule {

    private final Context mContext;

    public ClimateAmbassadorModule(Context context) {
        mContext = context;
    }

    @Provides @Singleton
    Timber provideTimber() {
        return BuildConfig.DEBUG ? Timber.DEBUG : Timber.PROD;
    }
}
