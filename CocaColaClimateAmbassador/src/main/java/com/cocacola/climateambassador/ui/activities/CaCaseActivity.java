package com.cocacola.climateambassador.ui.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cocacola.climateambassador.HasModel;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.models.BulletPointFrame;
import com.cocacola.climateambassador.models.Case;
import com.cocacola.climateambassador.models.SubtitleTextPair;
import com.cocacola.climateambassador.models.TextFrame;
import com.cocacola.climateambassador.ui.views.DocumentsLayout;
import com.cocacola.climateambassador.util.JsonAssetsLoader;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.InjectView;

/**
 * Created by Vinnie on 9/4/13.
 */
public abstract class CaCaseActivity extends CaActivity implements HasModel<Case> {

    @Inject JsonAssetsLoader mJsonAssetsLoader;

    @InjectView(R.id.case_studies)
    DocumentsLayout mCaseStudiesLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_case);

        Case aCase = getModel();

        findViewById(R.id.scroll_view).setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_case_ingredients));
        ((ImageView)findViewById(R.id.case_logo)).setImageResource(R.drawable.ic_case_detail_ingredients);

        LayoutInflater inflater = getLayoutInflater();

        ((TextView) findViewById(R.id.case_title)).setText(aCase.getTitle());

        if (!TextUtils.isEmpty(aCase.getBodyText())) {
            ((TextView) findViewById(R.id.body_text)).setText(Html.fromHtml(aCase.getBodyText()));
            ((TextView) findViewById(R.id.body_text)).setVisibility(View.VISIBLE);
        }

        LinearLayout caseFrames = (LinearLayout) findViewById(R.id.case_frames);

        if (aCase.getBulletPointFrame() != null) {
            BulletPointFrame caseBulletPointFrame = aCase.getBulletPointFrame();
            View bulletPointFrame = inflater.inflate(R.layout.case_frame, null);

            if (!TextUtils.isEmpty(caseBulletPointFrame.getTitle())) {
                ((TextView) bulletPointFrame.findViewById(R.id.case_frame_title)).setText(caseBulletPointFrame.getTitle());
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
                bulletList.addView(bulletPointLayout);
            }

            bulletList.setVisibility(View.VISIBLE);
            caseFrames.addView(bulletPointFrame);
        }

        if (aCase.getTextFrames() != null) {

            for (TextFrame currTextFrame : aCase.getTextFrames()) {
                View textFrame = inflater.inflate(R.layout.case_frame, null);

                if (!TextUtils.isEmpty(currTextFrame.getTitle())) {
                    ((TextView) textFrame.findViewById(R.id.case_frame_title)).setText(currTextFrame.getTitle());
                    ((TextView) textFrame.findViewById(R.id.case_frame_title)).setVisibility(View.VISIBLE);
                }

                if (!TextUtils.isEmpty(currTextFrame.getBodyText())) {
                    ((TextView) textFrame.findViewById(R.id.case_frame_body_text)).setText(currTextFrame.getBodyText());
                    ((TextView) textFrame.findViewById(R.id.case_frame_body_text)).setVisibility(View.VISIBLE);
                }
                //TextFrame list base layout
                LinearLayout textFrames = (LinearLayout) textFrame.findViewById(R.id.case_frame_body);

                for (SubtitleTextPair subTextPair : currTextFrame.getSubtitleTextPairs()) {
                    View subtitleTextPairView = inflater.inflate(R.layout.case_text_frame, null);
                    ((TextView) subtitleTextPairView.findViewById(R.id.title)).setText(subTextPair.getTitle());
                    ((TextView) subtitleTextPairView.findViewById(R.id.body_text)).setText(subTextPair.getText());
                    textFrames.addView(subtitleTextPairView);
                }

                textFrames.setVisibility(View.VISIBLE);
                caseFrames.addView(textFrame);
            }
        }

        // Show documents
        mCaseStudiesLayout.setDocuments(aCase.getCaseStudies());


    }

    private Case mCase;

    public Case getModel() {

        // Lazily create Model object from JSON file
        if(mCase == null) {
            try {
                mCase = mJsonAssetsLoader.parseCaseFromJsonFile(getJsonAssetFilename());
            } catch (IOException e) {
                onAssetLoadError();
            } catch (JsonSyntaxException e) {
                onAssetLoadError();
            }
        }

        return mCase;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
                // Just finish()
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onAssetLoadError() {
        Log.e("Failed loading %s", getJsonAssetFilename());
        Toast.makeText(this, "Failed To Load: " + getJsonAssetFilename(), Toast.LENGTH_SHORT);
    }
}
