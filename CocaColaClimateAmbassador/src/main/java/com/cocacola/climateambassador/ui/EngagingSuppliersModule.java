package com.cocacola.climateambassador.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cocacola.climateambassador.R;

/**
 * Created by Vinnie Vendemia on 8/28/13.
 */

public class EngagingSuppliersModule extends CaFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.engaging_suppliers, container, false);
        return view;
    }


}
