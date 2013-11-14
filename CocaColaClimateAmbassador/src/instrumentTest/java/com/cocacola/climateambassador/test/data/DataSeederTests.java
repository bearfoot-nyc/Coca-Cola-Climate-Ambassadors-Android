package com.cocacola.climateambassador.test.data;

import com.cocacola.climateambassador.data.Module;
import com.cocacola.climateambassador.test.CaTestCase;
import com.cocacola.climateambassador.util.DataSeeder;
import com.cocacola.climateambassador.util.JsonAssetsLoader;
import com.cocacola.climateambassador.util.JsonFilenameDictionary;
import javax.inject.Inject;

/**
 * Created by realandylawton on 11/13/13.
 */
public class DataSeederTests extends CaTestCase {

    @Inject JsonAssetsLoader mJsonLoader;
    @Inject DataSeeder mSeeder;

    @Override protected void tearDown() throws Exception {
        super.tearDown();
        mJsonLoader = null;
        mSeeder = null;
    }

    public void testModuleOneIsSeeded() {

        String jsonFileName = JsonFilenameDictionary.MODULE_ONE;

        Module module = mSeeder.seedModule(jsonFileName);

        assertNotNull("Module was null", module);
        assertNotNull("Module name was null", module.getTitle());

    }

}
