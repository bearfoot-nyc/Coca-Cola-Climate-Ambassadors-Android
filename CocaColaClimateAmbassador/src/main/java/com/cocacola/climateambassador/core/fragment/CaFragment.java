package com.cocacola.climateambassador.core.fragment;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import com.cocacola.climateambassador.core.CaApplication;

import butterknife.Views;
import com.cocacola.climateambassador.core.controller.DocumentController;

public class CaFragment extends Fragment {

    private DocumentController mDocumentController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CaApplication.getObjectGraph(this.getActivity().getApplicationContext()).inject(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Views.reset(this);
    }

}
