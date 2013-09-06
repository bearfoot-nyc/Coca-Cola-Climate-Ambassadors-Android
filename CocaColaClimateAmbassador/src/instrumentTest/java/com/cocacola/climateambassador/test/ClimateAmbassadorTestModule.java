package com.cocacola.climateambassador.test;

import android.content.Context;

import com.cocacola.climateambassador.modules.ClimateAmbassadorModule;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrewlawton on 8/24/13.
 */

@Module(
        includes = ClimateAmbassadorModule.class,
        overrides = true,
        injects = {
                AppPackageFileWriterTests.class,
                DocumentViewDelegateTests.class,
                JsonTextSerializerTests.class
        }
)
public class ClimateAmbassadorTestModule {

    private final Context context;

    public ClimateAmbassadorTestModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext(){
        return this.context;
    }

}
