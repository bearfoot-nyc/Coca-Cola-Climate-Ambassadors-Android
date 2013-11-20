package com.cocacola.climateambassador.test;

import android.content.Context;

import com.cocacola.climateambassador.modules.CaProdModule;

import com.cocacola.climateambassador.test.android.AppPackageFileWriterTests;
import com.cocacola.climateambassador.test.android.DocumentViewDelegateTests;
import com.cocacola.climateambassador.test.android.JsonAssetsLoaderTests;
import com.cocacola.climateambassador.test.data.DataCheckerTests;
import com.cocacola.climateambassador.test.data.DataSeederTests;
import dagger.Module;
import dagger.Provides;

/**
 * Created by andrewlawton on 8/24/13.
 */

@Module(
        includes = CaProdModule.class,
        overrides = true,
        injects = {
            AppPackageFileWriterTests.class,
            DocumentViewDelegateTests.class,
            JsonAssetsLoaderTests.class,
            DataSeederTests.class,
            DataCheckerTests.class
        }
)
public class CaTestModule {

    private final Context context;

    public CaTestModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext(){
        return this.context;
    }

}
