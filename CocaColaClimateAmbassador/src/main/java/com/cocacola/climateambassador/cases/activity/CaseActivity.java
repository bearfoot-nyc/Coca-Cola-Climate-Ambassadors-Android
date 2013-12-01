package com.cocacola.climateambassador.cases.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.InjectView;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.core.activity.CaSearchableActivity;
import com.cocacola.climateambassador.core.model.CaseModel;
import com.cocacola.climateambassador.core.views.DocumentsLayout;
import com.cocacola.climateambassador.data.BulletPoint;
import com.cocacola.climateambassador.data.BulletPointFrame;
import com.cocacola.climateambassador.data.CaCase;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.SubtitleTextPair;
import com.cocacola.climateambassador.data.TextFrame;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by Vinnie on 9/4/13.
 */
public class CaseActivity extends CaSearchableActivity {

    public static final String EXTRA_CASE_ID = "caseId";

    public static enum CaseResourceType {

        INGREDIENTS(R.drawable.bg_case_ingredients, R.drawable.ic_case_detail_ingredients),
        PACKAGING(R.drawable.bg_case_packaging, R.drawable.ic_case_detail_packaging),
        MANUFACTURING(R.drawable.bg_case_manufacturing, R.drawable.ic_case_detail_manufacturing),
        DISTRIBUTION(R.drawable.bg_case_distribution, R.drawable.ic_case_detail_distribution),
        REFRIGERATION(R.drawable.bg_case_refrigeration, R.drawable.ic_cases_list_refrigeration);

        CaseResourceType(Integer backgroundId, Integer iconId) {
            mBackgroundId = backgroundId;
            mIconId = iconId;
        }

        private Integer mBackgroundId;
        private Integer mIconId;

        public Integer getBackgroundId() {
            return mBackgroundId;
        }
        public Integer getIconId() {
            return mIconId;
        }

    }

    private static Map<String, CaseResourceType> sCaseResourceMap = new LinkedHashMap<String, CaseResourceType>();
    static {
        sCaseResourceMap.put("Ingredients", CaseResourceType.INGREDIENTS);
        sCaseResourceMap.put("Packaging", CaseResourceType.PACKAGING);
        sCaseResourceMap.put("Manufacturing", CaseResourceType.MANUFACTURING);
        sCaseResourceMap.put("Distribution", CaseResourceType.DISTRIBUTION);
        sCaseResourceMap.put("Refrigeration", CaseResourceType.REFRIGERATION);
    }

    @Inject protected Timber Log;
    @Inject protected DaoMaster mDaoMaster;

    @InjectView(R.id.case_logo) protected ImageView mLogoView;
    @InjectView(R.id.scroll_view) protected ScrollView mScrollLayout;
    @InjectView(R.id.case_studies) protected DocumentsLayout mCaseStudiesLayout;

    private Long mCaseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.case_activity);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        mCaseId = getCaseIdFromIntent(getIntent());

        showLayout();

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

    private Long getCaseIdFromIntent(Intent intent) {

        if(intent == null || !intent.hasExtra(EXTRA_CASE_ID)) {
            return null;
        }

        return intent.getLongExtra(EXTRA_CASE_ID, 0);

    }

    private void showLayout() {

        CaCase _case = CaseModel.getCase(mDaoMaster, mCaseId);
        if(_case == null) {
            Toast.makeText(this, "Unable to load Case Study", Toast.LENGTH_LONG).show();
            return;
        }

        CaseResourceType resourceType = sCaseResourceMap.get(_case.getTitle());
        if(resourceType != null) {
            mScrollLayout.setBackgroundResource(resourceType.getBackgroundId());
            mLogoView.setImageResource(resourceType.getIconId());
        }

        LayoutInflater inflater = getLayoutInflater();

        ((TextView) findViewById(R.id.case_title)).setText(_case.getTitle());

        if (!TextUtils.isEmpty(_case.getBodyText())) {
            ((TextView) findViewById(R.id.body_text)).setText(Html.fromHtml(_case.getBodyText()));
            ((TextView) findViewById(R.id.body_text)).setVisibility(View.VISIBLE);
        }

        LinearLayout caseFrames = (LinearLayout) findViewById(R.id.case_frames);

        if (_case.getBulletPointFrame() != null) {
            BulletPointFrame caseBulletPointFrame = _case.getBulletPointFrame();
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
            for (BulletPoint bulletPoint : caseBulletPointFrame.getBulletPoints()) {
                View bulletPointLayout = inflater.inflate(R.layout.depr_view_bullet_point, null);
                ((TextView) bulletPointLayout.findViewById(R.id.bullet_text)).setText(Html.fromHtml(bulletPoint.getText()));
                bulletList.addView(bulletPointLayout);
            }

            bulletList.setVisibility(View.VISIBLE);
            caseFrames.addView(bulletPointFrame);
        }

        if (_case.getTextFrames() != null) {

            for (TextFrame currTextFrame : _case.getTextFrames()) {
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
        mCaseStudiesLayout.setDocuments(_case.getCaseStudies());

    }



}
