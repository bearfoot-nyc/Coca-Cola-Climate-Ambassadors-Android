package com.cocacola.climateambassador.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cocacola.climateambassador.CaApplication;

/**
 * Created by Vinnie Vendemia on 8/28/13.
 */
public class CaFragment extends Fragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CaApplication.getObjectGraph(this.getActivity().getApplicationContext()).inject(this);
    }

}
