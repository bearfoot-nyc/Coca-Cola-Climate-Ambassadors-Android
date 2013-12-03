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
import com.cocacola.climateambassador.data.Module;
import com.cocacola.climateambassador.data.json.DocumentJson;
import com.cocacola.climateambassador.data.json.ModuleJson;
import com.cocacola.climateambassador.core.views.DocumentView;
import com.cocacola.climateambassador.core.util.JsonAssetsLoader;
import com.cocacola.climateambassador.module.fragment.ModuleFragment;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.Views;
import timber.log.Timber;

public class ForSupplierOverviewFragment extends ModuleFragment {

    public static ForSupplierOverviewFragment newInstance(Long moduleId) {

        ForSupplierOverviewFragment fragment = new ForSupplierOverviewFragment();
        fragment.setArguments(createBundleWithModuleId(moduleId));

        return fragment;

    }

    @Inject protected Timber Log;
    @InjectView(R.id.suppliers_document) protected DocumentView mDocumentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.suppliers_frag_overview, container, false);
        Views.inject(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // FIXME Content is hardcoded in XML layout....lame
        mDocumentView.setDocument(getSupplierDocument());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Views.reset(this);
    }

    private Document getSupplierDocument() {

        Module module = getModule();
        if(module == null || module.getDocuments() == null || module.getDocuments().size() < 1) {
            return null;
        }

        return module.getDocuments().get(0);

    }

}
