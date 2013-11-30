package com.cocacola.climateambassador.test;

import android.content.Context;

import com.cocacola.climateambassador.core.CaProdModule;

import com.cocacola.climateambassador.core.model.DocumentModel;
import com.cocacola.climateambassador.test.android.core.model.DocumentModelTests;
import com.cocacola.climateambassador.test.android.core.util.DocumentIntentBuilderTests;
import com.cocacola.climateambassador.test.android.core.util.DocumentUriBuilderTests;
import com.cocacola.climateambassador.test.android.core.util.FileTypeTests;
import com.cocacola.climateambassador.test.android.ui.ModuleActivityTest;
import com.cocacola.climateambassador.test.android.core.util.AppPackageFileWriterTests;
import com.cocacola.climateambassador.test.android.core.util.JsonAssetsLoaderTests;
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
            DocumentIntentBuilderTests.class,
            JsonAssetsLoaderTests.class,
            DataSeederTests.class,
            DataCheckerTests.class, ModuleActivityTest.class, FileTypeTests.class,
            DocumentUriBuilderTests.class, DocumentModelTests.class
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
