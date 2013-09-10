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

import com.cocacola.climateambassador.HasModel;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.models.Module;
import com.cocacola.climateambassador.ui.activities.DistributionCaseActivity;
import com.cocacola.climateambassador.ui.activities.IngredientCaseActivity;
import com.cocacola.climateambassador.ui.activities.ManufacturingCaseActivity;
import com.cocacola.climateambassador.ui.activities.PackagingCaseActivity;
import com.cocacola.climateambassador.ui.activities.RefrigerationCaseActivity;
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

public class ForSuppliersBodyFragment extends CaFragment implements HasModel<Module> {

    @Inject
    JsonAssetsLoader mJsonAssetsLoader;

    @Inject
    Timber Log;

    public static final String VIDEO_BUTTON_TITLE = "View The Video";
    public static final String SUPPLIER_GUIDE_TITLE = "Flip through the Supplier Guide";

    @InjectView(R.id.introduction_button)
    FrameLayout introductionButton;
    @InjectView(R.id.video_button)
    FrameLayout videoButton;
    @InjectView(R.id.supplier_guide_button)
    FrameLayout supplierGuideButton;

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



//        mContext = getActivity();
//        //Set up Short Intro button
//        View introButtonLayout = inflater.inflate(R.layout.depr_favorite_divider_button, null);
//
//        //Set up video button
//        View videoButtonLayout = inflater.inflate(R.layout.depr_favorite_divider_button, null);
//        ((TextView) videoButtonLayout.findViewById(R.id.doc_title)).setText(VIDEO_BUTTON_TITLE);
//        //TODO: Change to MOV Image
//        ((ImageView)introButtonLayout.findViewById(R.id.doc_type)).setImageResource(R.drawable.ic_doc_doc);
//        ((LinearLayout) videoButtonLayout.findViewById(R.id.document_opener_button)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //TODO: Open Document
//                Toast.makeText(getActivity(), "Touched " + VIDEO_BUTTON_TITLE, Toast.LENGTH_SHORT).show();
//            }
//        });
//        videoButton.addView(videoButtonLayout);
//
//        //Set up supplier guide button
//        View supplierButtonLayout = inflater.inflate(R.layout.depr_favorite_divider_button, null);
//        ((ImageView)introButtonLayout.findViewById(R.id.doc_type)).setImageResource(R.drawable.ic_doc_pdf);
//        ((TextView) supplierButtonLayout.findViewById(R.id.doc_title)).setText(SUPPLIER_GUIDE_TITLE);
//        ((LinearLayout) supplierButtonLayout.findViewById(R.id.document_opener_button)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //TODO: Open Document
//                Toast.makeText(getActivity(), "Touched " + VIDEO_BUTTON_TITLE, Toast.LENGTH_SHORT).show();
//            }
//        });
//        supplierGuideButton.addView(supplierButtonLayout);


        return view;
    }

    private Module mModule;

    @Override
    public Module getModel() {

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
        return "supplier.json";
    }

    private void onAssetLoadError() {
        Log.e("Failed loading %s", getJsonAssetFilename());
        Toast.makeText(getActivity(), "Failed To Load: " + getJsonAssetFilename(), Toast.LENGTH_SHORT);
    }
}
