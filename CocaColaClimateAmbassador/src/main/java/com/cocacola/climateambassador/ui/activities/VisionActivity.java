package com.cocacola.climateambassador.ui.activities;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.cocacola.climateambassador.HasModel;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.models.Module;
import com.cocacola.climateambassador.ui.views.BulletPointLayout;
import com.cocacola.climateambassador.util.JsonAssetsLoader;
import com.cocacola.climateambassador.util.Toaster;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.InjectView;

/**
 * Created by realandylawton on 9/7/13.
 */
public class VisionActivity extends CaActivity implements HasModel<Module> {

    @Inject
    JsonAssetsLoader mJsonAssetsLoader;

    @InjectView(R.id.title)
    TextView mTitleView;

    @InjectView(R.id.body_text)
    TextView mBodyTextView;

    @InjectView(R.id.bullet_points)
    BulletPointLayout mBulletPointLayout;

    private Module mModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_2020_vision);

        Module module = getModel();

        mTitleView.setText(module.getTitle());
        mBodyTextView.setText(Html.fromHtml(module.getBodyText()));

        mBulletPointLayout.setBulletPoints(module.getBulletPointFrame().getBulletPoints());

    }

    @Override
    public Module getModel() {

        if(mModule == null) {
            try {
                mModule = mJsonAssetsLoader.parseModuleFromJsonFile(getJsonAssetFilename());
            }
            catch (IOException e) {
                onAssetLoadError();
            } catch (JsonSyntaxException e) {
                onAssetLoadError();
            }
        }

        return mModule;
    }

    private void onAssetLoadError() {
        Toaster.toast(this, "Failed To Load: " + getJsonAssetFilename());
    }

    @Override
    public String getJsonAssetFilename() {
        return "for_suppliers_2020_vision.json";
    }


}
