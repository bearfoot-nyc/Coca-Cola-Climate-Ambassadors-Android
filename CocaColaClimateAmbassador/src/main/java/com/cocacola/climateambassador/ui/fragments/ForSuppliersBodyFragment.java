package com.cocacola.climateambassador.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cocacola.climateambassador.HasModel;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.models.Document;
import com.cocacola.climateambassador.models.FileType;
import com.cocacola.climateambassador.models.Module;
import com.cocacola.climateambassador.ui.activities.DistributionCaseActivity;
import com.cocacola.climateambassador.ui.activities.IngredientCaseActivity;
import com.cocacola.climateambassador.ui.activities.ManufacturingCaseActivity;
import com.cocacola.climateambassador.ui.activities.PackagingCaseActivity;
import com.cocacola.climateambassador.ui.activities.RefrigerationCaseActivity;
import com.cocacola.climateambassador.ui.views.DocumentView;
import com.cocacola.climateambassador.util.JsonAssetsLoader;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.Views;
import timber.log.Timber;

/**
 * Created by Vinnie Vendemia on 8/28/13.
 */

public class ForSuppliersBodyFragment extends CaFragment implements HasModel<Module> {

    @Inject
    JsonAssetsLoader mJsonAssetsLoader;

    @Inject
    Timber Log;

    private Module mModule;

    @InjectView(R.id.suppliers_document)
    DocumentView mDocumentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.frag_for_suppliers, container, false);

        Views.inject(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mDocumentView.setDocument(getSupplierDocument());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Views.reset(this);
    }

    private Document getSupplierDocument() {

        Document doc = new Document("PartneringToReduceCarboninValueChain.pdf", "Partnering to Reduce Carbon along Our Value Chain", FileType.PDF.toString());
        return doc;

    }

    @Override
    public Module getModel() {

        // Lazily create Model object from JSON file
        if(mModule == null) {
            try {
                mModule = mJsonAssetsLoader.parseModuleFromJsonFile(getJsonAssetFilename());
            } catch (IOException e) {
                onAssetLoadError();
            } catch (JsonSyntaxException e) {
                onAssetLoadError();
            }
        }

        return mModule;

    }

    @Override
    public String getJsonAssetFilename() {
        return "supplier.json";
    }

    private void onAssetLoadError() {
        Log.e("Failed loading %s", getJsonAssetFilename());
        Toast.makeText(getActivity(), "Failed To Load: " + getJsonAssetFilename(), Toast.LENGTH_SHORT);
    }
}
