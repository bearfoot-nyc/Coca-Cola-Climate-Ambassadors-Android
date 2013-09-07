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

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.ui.activities.DistributionCaseActivity;
import com.cocacola.climateambassador.ui.activities.IngredientCaseActivity;
import com.cocacola.climateambassador.ui.activities.ManufacturingCaseActivity;
import com.cocacola.climateambassador.ui.activities.PackagingCaseActivity;
import com.cocacola.climateambassador.ui.activities.RefrigerationCaseActivity;

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

    Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.frag_for_suppliers, container, false);
        Views.inject(this, view);

        mContext = getActivity();
        //Set up Short Intro button
        View introButtonLayout = inflater.inflate(R.layout.depr_favorite_divider_button, null);
        //TODO: change to listen Image
        ((ImageView)introButtonLayout.findViewById(R.id.doc_type)).setImageResource(R.drawable.ic_doc_pdf);
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
        View videoButtonLayout = inflater.inflate(R.layout.depr_favorite_divider_button, null);
        ((TextView) videoButtonLayout.findViewById(R.id.doc_title)).setText(VIDEO_BUTTON_TITLE);
        //TODO: Change to MOV Image
        ((ImageView)introButtonLayout.findViewById(R.id.doc_type)).setImageResource(R.drawable.ic_doc_doc);
        ((LinearLayout) videoButtonLayout.findViewById(R.id.document_opener_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Open Document
                Toast.makeText(getActivity(), "Touched " + VIDEO_BUTTON_TITLE, Toast.LENGTH_SHORT).show();
            }
        });
        videoButton.addView(videoButtonLayout);

        //Set up supplier guide button
        View supplierButtonLayout = inflater.inflate(R.layout.depr_favorite_divider_button, null);
        ((ImageView)introButtonLayout.findViewById(R.id.doc_type)).setImageResource(R.drawable.ic_doc_pdf);
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
                Intent intent = new Intent(mContext, IngredientCaseActivity.class);
                mContext.startActivity(intent);
            }
        });

        packagingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PackagingCaseActivity.class);
                mContext.startActivity(intent);
            }
        });

        manufactureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ManufacturingCaseActivity.class);
                mContext.startActivity(intent);
            }
        });

        distributionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DistributionCaseActivity.class);
                mContext.startActivity(intent);
            }
        });

        refrigerationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RefrigerationCaseActivity.class);
                mContext.startActivity(intent);
            }
        });

        return view;
    }
}
