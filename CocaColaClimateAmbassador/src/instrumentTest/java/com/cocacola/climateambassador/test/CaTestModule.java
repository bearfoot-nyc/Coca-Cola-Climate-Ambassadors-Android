package com.cocacola.climateambassador.test;

import android.content.Context;

import com.cocacola.climateambassador.core.CaProdModule;

import com.cocacola.climateambassador.test.android.ui.MainActivityTest;
import com.cocacola.climateambassador.test.android.util.AppPackageFileWriterTests;
import com.cocacola.climateambassador.test.android.util.DocumentViewDelegateTests;
import com.cocacola.climateambassador.test.android.util.JsonAssetsLoaderTests;
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
            DataCheckerTests.class, MainActivityTest.class
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
