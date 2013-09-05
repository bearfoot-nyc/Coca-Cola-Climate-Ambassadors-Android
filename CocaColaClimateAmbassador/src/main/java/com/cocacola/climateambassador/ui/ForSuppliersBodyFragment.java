package com.cocacola.climateambassador.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cocacola.climateambassador.R;

import butterknife.InjectView;
import butterknife.Views;

/**
 * Created by Vinnie Vendemia on 8/28/13.
 */

public class ForSuppliersBodyFragment extends CaFragment {

    //Constants
    public static final String TAG = "ForSuppliersBodyFragment";
    public static final String INTRO_BUTTON_TITLE = "Listen to Short Introduction";
    public static final String VIDEO_BUTTON_TITLE = "View The Video";
    public static final String SUPPLIER_GUIDE_TITLE = "Flip through the Supplier Guide";

    @InjectView(R.id.introduction_button)
    FrameLayout introductionButton;
    @InjectView(R.id.video_button)
    FrameLayout videoButton;
    @InjectView(R.id.supplier_guide_button)
    FrameLayout supplierGuideButton;
    @InjectView(R.id.ingredient_button)
    Button ingredientButton;
    @InjectView(R.id.packaging_button)
    Button packagingButton;
    @InjectView(R.id.manufacturing_button)
    Button manufactureButton;
    @InjectView(R.id.distribution_button)
    Button distributionButton;
    @InjectView(R.id.refrigeration_button)
    Button refrigerationButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.frag_for_suppliers, container, false);
        Views.inject(this, view);

        //Set up Short Intro button
        View introButtonLayout = inflater.inflate(R.layout.favorite_divider_button, null);
        //            ((ImageView)viewWithButton.findViewById(R.id.doc_type)).setImageResource();
        ((TextView) introButtonLayout.findViewById(R.id.doc_title)).setText(INTRO_BUTTON_TITLE);
        ((LinearLayout) introButtonLayout.findViewById(R.id.document_opener_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Open Document
                Toast.makeText(getActivity(), "Touched " + INTRO_BUTTON_TITLE, Toast.LENGTH_SHORT).show();
            }
        });
        introductionButton.addView(introButtonLayout);

        //Set up video button
        View videoButtonLayout = inflater.inflate(R.layout.favorite_divider_button, null);
        //            ((ImageView)viewWithButton.findViewById(R.id.doc_type)).setImageResource();
        ((TextView) videoButtonLayout.findViewById(R.id.doc_title)).setText(VIDEO_BUTTON_TITLE);
        ((LinearLayout) videoButtonLayout.findViewById(R.id.document_opener_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Open Document
                Toast.makeText(getActivity(), "Touched " + VIDEO_BUTTON_TITLE, Toast.LENGTH_SHORT).show();
            }
        });
        videoButton.addView(videoButtonLayout);

        //Set up supplier guide button
        View supplierButtonLayout = inflater.inflate(R.layout.favorite_divider_button, null);
        //            ((ImageView)viewWithButton.findViewById(R.id.doc_type)).setImageResource();
        ((TextView) supplierButtonLayout.findViewById(R.id.doc_title)).setText(SUPPLIER_GUIDE_TITLE);
        ((LinearLayout) supplierButtonLayout.findViewById(R.id.document_opener_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Open Document
                Toast.makeText(getActivity(), "Touched " + VIDEO_BUTTON_TITLE, Toast.LENGTH_SHORT).show();
            }
        });
        supplierGuideButton.addView(supplierButtonLayout);


        ingredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Go to Case Study
                Log.d(TAG, "Clicked Ingredient Button");
            }
        });

        packagingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Go to Case Study
                Log.d(TAG, "Clicked Packaging Button");
            }
        });

        manufactureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Go to Case Study
                Log.d(TAG, "Clicked Manufacturing Button");
            }
        });

        distributionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Go to Case Study
                Log.d(TAG, "Clicked Distribution Button");
            }
        });

        refrigerationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Go to Case Study
                Log.d(TAG, "Clicked Refrigeration Button");
            }
        });

        return view;
    }
}
