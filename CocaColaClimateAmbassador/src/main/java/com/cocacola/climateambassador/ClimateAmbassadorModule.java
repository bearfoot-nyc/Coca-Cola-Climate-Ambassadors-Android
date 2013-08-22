package com.cocacola.climateambassador;

import android.content.Context;

import dagger.Provides;

/**
 * Created by andrewlawton on 8/21/13.
 */
public class ClimateAmbassadorModule {

    private final Context mContext;

    public ClimateAmbassadorModule(Context context) {
        mContext = context;
    }

    @Provides
    Context provideContext(){
        return mContext;
    }
}
