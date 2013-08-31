package com.cocacola.climateambassador.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cocacola.climateambassador.R;

import butterknife.InjectView;
import butterknife.Views;

/**
 * Created by Vinnie Vendemia on 8/28/13.
 */


public class MainFragment extends CaFragment {

    //CaConstants
    //CaConstants
    private final String welcomeString = "WELCOME TO THE COCA COLA CLIMATE AMBASSADOR APP!";
    private final String toolDescriptionString = "This tool connects you with training materials and presentations to engage colleagues and " +
            "suppliers in reducing carbon along your value chain.";
    private final String navString = "Click below to access our Internal Training Content and Tools for " +
            "External Supplier Meetings, or use the side menu to navigate all content";


    @InjectView(R.id.welcome)
    TextView welcomeText;
    @InjectView(R.id.tool_description)
    TextView toolDescriptionText;
    @InjectView(R.id.navigation_text)
    TextView navigationText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        Views.inject(this, view);
        welcomeText.setText(welcomeString);
        toolDescriptionText.setText(toolDescriptionString);
        navigationText.setText(navString);

        return view;
    }
}
