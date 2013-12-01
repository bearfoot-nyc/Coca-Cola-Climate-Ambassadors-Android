package com.cocacola.climateambassador.module.suppliers.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cocacola.climateambassador.core.fragment.CaFragment;
import com.cocacola.climateambassador.core.model.DocumentModel;
import com.cocacola.climateambassador.core.util.HasJsonModel;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.data.Document;
import com.cocacola.climateambassador.data.json.DocumentJson;
import com.cocacola.climateambassador.data.json.ModuleJson;
import com.cocacola.climateambassador.core.views.DocumentView;
import com.cocacola.climateambassador.core.util.JsonAssetsLoader;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.Views;
import timber.log.Timber;

public class ForSupplierOverviewFragment extends CaFragment implements HasJsonModel<ModuleJson> {

    public static ForSupplierOverviewFragment newInstance() {
        return new ForSupplierOverviewFragment();
    }

    @Inject protected JsonAssetsLoader mJsonAssetsLoader;
    @Inject protected Timber Log;
    @InjectView(R.id.suppliers_document) protected DocumentView mDocumentView;

    private ModuleJson mModule;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.suppliers_frag_overview, container, false);

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

        DocumentJson documentJson = new DocumentJson(
            "PartneringToReduceCarboninValueChain.pdf",
            "Partnering to Reduce Carbon along Our Value Chain"
        );

        Document document = DocumentModel.fromJson(documentJson);

        return document;

    }

    @Override
    public ModuleJson getModel() {

        // Lazily create Model object from JSON file
        if(mModule == null) {
            try {
                mModule = mJsonAssetsLoader.parseFromJsonFile(getJsonAssetFilename(), ModuleJson.class);
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
        return "suppliers_overview.json";
    }

    private void onAssetLoadError() {
        Log.e("Failed loading %s", getJsonAssetFilename());
        Toast.makeText(getActivity(), "Failed To Load: " + getJsonAssetFilename(), Toast.LENGTH_SHORT);
    }
}
