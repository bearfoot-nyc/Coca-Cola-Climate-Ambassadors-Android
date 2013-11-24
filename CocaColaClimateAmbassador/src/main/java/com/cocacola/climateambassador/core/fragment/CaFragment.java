package com.cocacola.climateambassador.core.fragment;

import android.app.Fragment;
import android.os.Bundle;

import com.cocacola.climateambassador.core.CaApplication;

import butterknife.Views;

/**
 * Created by Vinnie Vendemia on 8/28/13.
 */
public class CaFragment extends Fragment{

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
