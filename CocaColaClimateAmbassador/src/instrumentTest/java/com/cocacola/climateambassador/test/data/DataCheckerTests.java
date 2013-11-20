package com.cocacola.climateambassador.test.data;

import com.cocacola.climateambassador.util.DataChecker;
import com.cocacola.climateambassador.util.DataSeeder;
import java.io.IOException;
import javax.inject.Inject;

/**
 * Created by realandylawton on 11/19/13.
 */
public class DataCheckerTests extends AbsDataTests {

    @Inject DataChecker mDataChecker;

    public void testDetectsDataIsAvailable() throws IOException, DataSeeder.SeedFailedException {

        // Seed the database
        mSeeder.seed();

        // Check for data
        boolean isDataSeeded = mDataChecker.isDataSeeded();

        assertEquals("Data should be seeded", true, isDataSeeded);

    }

    public void testDetectsDataIsNotAvailable() {

        // Create an empty database
        clearDatabase();
        createDatabase();

        boolean isDataSeeded = mDataChecker.isDataSeeded();

        assertEquals("Data should not be seeded", false, isDataSeeded);

    }

}
