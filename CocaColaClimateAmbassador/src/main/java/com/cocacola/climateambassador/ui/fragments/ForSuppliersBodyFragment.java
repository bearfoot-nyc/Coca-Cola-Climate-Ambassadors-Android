package com.cocacola.climateambassador.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cocacola.climateambassador.HasModel;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.json.DocumentJson;
import com.cocacola.climateambassador.json.ModuleJson;
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

public class ForSuppliersBodyFragment extends CaFragment implements HasModel<ModuleJson> {

    @Inject
    JsonAssetsLoader mJsonAssetsLoader;

    @Inject
    Timber Log;

    private ModuleJson mModule;

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

    private DocumentJson getSupplierDocument() {

        DocumentJson doc = new DocumentJson("PartneringToReduceCarboninValueChain.pdf", "Partnering to Reduce Carbon along Our Value Chain");
        return doc;

    }

    @Override
    public ModuleJson getModel() {

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
        return "for_suppliers_overview.json";
    }

    private void onAssetLoadError() {
        Log.e("Failed loading %s", getJsonAssetFilename());
        Toast.makeText(getActivity(), "Failed To Load: " + getJsonAssetFilename(), Toast.LENGTH_SHORT);
    }
}
