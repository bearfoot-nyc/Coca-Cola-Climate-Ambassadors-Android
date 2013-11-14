package com.cocacola.climateambassador.ui.fragments;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cocacola.climateambassador.HasModel;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.models.BulletPointFrameJson;
import com.cocacola.climateambassador.models.DocumentJson;
import com.cocacola.climateambassador.models.ModuleJson;
import com.cocacola.climateambassador.ui.views.DocumentsLayout;
import com.cocacola.climateambassador.util.JsonAssetsLoader;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.Views;
import timber.log.Timber;

/**
 * Created by Vinnie on 9/5/13.
 */
public abstract class CaModuleBodyFragment extends CaFragment implements HasModel<ModuleJson> {

    @Inject
    JsonAssetsLoader mJsonAssetsLoader;

    @Inject
    Timber Log;

    @InjectView(R.id.course_materials_label)
    TextView courseMaterialsLabelView;

    private ModuleJson mModule;

    public ModuleJson getModel() {

        // Lazily create Module object from JSON file
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ModuleJson module = getModel();

        View v = inflater.inflate(R.layout.frag_module, null);
        Views.inject(this, v);

        if(!TextUtils.isEmpty(module.getTitle())) {
            ((TextView)v.findViewById(R.id.title)).setText(module.getTitle());
        }

        if(!TextUtils.isEmpty(module.getBodyText())) {
            ((TextView)v.findViewById(R.id.description)).setText(Html.fromHtml(module.getBodyText()));
        }

        // Bullet point frames
        LinearLayout caseFrames = (LinearLayout) v.findViewById(R.id.case_frames);
        if (module.getBulletPointFrame() != null) {
            BulletPointFrameJson caseBulletPointFrame = module.getBulletPointFrame();
            View bulletPointFrame = inflater.inflate(R.layout.case_frame, null);

            if (!TextUtils.isEmpty(caseBulletPointFrame.getTitle())) {
                ((TextView) bulletPointFrame.findViewById(R.id.case_frame_title)).setText(caseBulletPointFrame.getTitle());
                ((TextView) bulletPointFrame.findViewById(R.id.case_frame_title)).setTextColor(getResources().getColor(R.color.black));
                ((TextView) bulletPointFrame.findViewById(R.id.case_frame_title)).setVisibility(View.VISIBLE);
            }

            if (!TextUtils.isEmpty(caseBulletPointFrame.getSubtitle())) {
                ((TextView) bulletPointFrame.findViewById(R.id.case_frame_subtitle)).setText(caseBulletPointFrame.getSubtitle());
                ((TextView) bulletPointFrame.findViewById(R.id.case_frame_subtitle)).setVisibility(View.VISIBLE);
            }

            //Bullet List Base Layout
            LinearLayout bulletList = (LinearLayout) (bulletPointFrame.findViewById(R.id.bullet_list_content));

            //Add Bullet Points to Layout
            for (String bulletPoint : caseBulletPointFrame.getBulletPoints()) {
                View bulletPointLayout = inflater.inflate(R.layout.depr_view_bullet_point, null);
                ((TextView) bulletPointLayout.findViewById(R.id.bullet_text)).setText(bulletPoint);
                //Must set text color, base color is white (for cases)
                ((TextView) bulletPointLayout.findViewById(R.id.bullet_text)).setTextColor(getResources().getColor(R.color.black));
                bulletList.addView(bulletPointLayout);
            }

            bulletList.setVisibility(View.VISIBLE);
            caseFrames.addView(bulletPointFrame);
        }

        List<DocumentJson> documentList = module.getCourseMaterials();
        if(documentList.size() > 0) {
            DocumentsLayout documentsLayout = (DocumentsLayout) v.findViewById(R.id.course_materials);
            documentsLayout.setDocuments(module.getCourseMaterials());
        }
        else {
            courseMaterialsLabelView.setVisibility(View.INVISIBLE);
        }

        return v;

    }

    private void onAssetLoadError() {
        Log.e("Failed loading %s", getJsonAssetFilename());
        Toast.makeText(getActivity(), "Failed To Load: " + getJsonAssetFilename(), Toast.LENGTH_SHORT);
    }

}
