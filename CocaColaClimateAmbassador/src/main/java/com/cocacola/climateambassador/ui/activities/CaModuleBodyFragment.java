package com.cocacola.climateambassador.ui.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.models.BulletPointFrame;
import com.cocacola.climateambassador.models.Module;
import com.cocacola.climateambassador.ui.fragments.CaFragment;
import com.cocacola.climateambassador.ui.views.CourseMaterialsLayout;
import com.cocacola.climateambassador.util.JsonAssetsLoader;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Vinnie on 9/5/13.
 */
public abstract class CaModuleBodyFragment extends CaFragment {

    @Inject
    JsonAssetsLoader mJsonAssetsLoader;

    @Inject
    Timber Log;

    public abstract String getJsonAssetFilename();

    private Module mModule;

    public Module getModule() {

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

        Module module = getModule();

        View v = inflater.inflate(R.layout.frag_module, null);

        if(!TextUtils.isEmpty(module.getTitle())) {
            ((TextView)v.findViewById(R.id.title)).setText(module.getTitle());
        }

        if(!TextUtils.isEmpty(module.getBodyText())) {
            ((TextView)v.findViewById(R.id.description)).setText(module.getBodyText());
        }

        LinearLayout caseFrames = (LinearLayout) v.findViewById(R.id.case_frames);
        if (module.getBulletPointFrame() != null) {
            BulletPointFrame caseBulletPointFrame = module.getBulletPointFrame();
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
                View bulletPointLayout = inflater.inflate(R.layout.case_frame_bullet_point, null);
                ((TextView) bulletPointLayout.findViewById(R.id.bullet_text)).setText(bulletPoint);
                //Must set text color, base color is white (for cases)
                ((TextView) bulletPointLayout.findViewById(R.id.bullet_text)).setTextColor(getResources().getColor(R.color.black));
                bulletList.addView(bulletPointLayout);
            }

            bulletList.setVisibility(View.VISIBLE);
            caseFrames.addView(bulletPointFrame);
        }

        CourseMaterialsLayout courseMaterialsLayout = (CourseMaterialsLayout) v.findViewById(R.id.course_materials);
        courseMaterialsLayout.setDocuments(module.getCourseMaterials());

        return v;

    }

    private void onAssetLoadError() {
        Log.e("Failed loading %s", getJsonAssetFilename());
        Toast.makeText(getActivity(), "Failed To Load: " + getJsonAssetFilename(), Toast.LENGTH_SHORT);
    }

}
