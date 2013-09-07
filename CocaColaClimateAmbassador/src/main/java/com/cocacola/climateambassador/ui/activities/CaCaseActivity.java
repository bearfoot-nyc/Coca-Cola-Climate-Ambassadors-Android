package com.cocacola.climateambassador.ui.activities;

import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.Toast;

import com.cocacola.climateambassador.HasModel;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.models.Case;
import com.cocacola.climateambassador.ui.views.DocumentsLayout;
import com.cocacola.climateambassador.util.JsonAssetsLoader;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by Vinnie on 9/4/13.
 */
public abstract class CaCaseActivity extends CaActivity implements HasModel<Case> {

    @Inject JsonAssetsLoader mJsonAssetsLoader;

    private Case mCase;

    public Case getModel() {

        // Lazily create Model object from JSON file
        if(mCase == null) {
            try {
                mCase = mJsonAssetsLoader.parseCaseFromJsonFile(getJsonAssetFilename());
            } catch (IOException e) {
                onAssetLoadError();
            } catch (JsonSyntaxException e) {
                onAssetLoadError();
            }
        }

        return mCase;
    }

    protected void showDocumentLayouts(Case aCase) {

        DocumentsLayout courseMaterialsLayout = (DocumentsLayout) findViewById(R.id.course_materials);
        courseMaterialsLayout.setDocuments(aCase.getCourseMaterials());

        DocumentsLayout caseStudiesLayout = (DocumentsLayout) findViewById(R.id.case_studies);
        caseStudiesLayout.setDocuments(aCase.getCourseMaterials());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
                // Just finish()
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onAssetLoadError() {
        Log.e("Failed loading %s", getJsonAssetFilename());
        Toast.makeText(this, "Failed To Load: " + getJsonAssetFilename(), Toast.LENGTH_SHORT);
    }
}
