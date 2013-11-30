package com.cocacola.climateambassador.test.android.core.model;

import com.cocacola.climateambassador.core.model.DocumentModel;
import com.cocacola.climateambassador.core.util.DataSeeder;
import com.cocacola.climateambassador.data.Document;
import com.cocacola.climateambassador.test.data.AbsDataTests;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by realandylawton on 11/30/13.
 */
public class DocumentModelTests extends AbsDataTests {

    @Inject DataSeeder mDataSeeder;

    @Override public void setUp() throws Exception {
        super.setUp();
        mDataSeeder.seed();
    }

    public void testSearchesForQuery() {

        String query = "Climate";
        List<Document> results = DocumentModel.search(mDaoMaster, query);

        assertNotNull(results);
        assertTrue("Should have results", results.size() > 0);

        for(Document d : results) {

            assertTrue("Filename should contain query", d.getLabel().contains(query));

        }

    }

}
