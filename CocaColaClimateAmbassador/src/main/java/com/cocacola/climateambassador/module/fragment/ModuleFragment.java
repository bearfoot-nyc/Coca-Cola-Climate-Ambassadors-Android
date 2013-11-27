package com.cocacola.climateambassador.module.fragment;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cocacola.climateambassador.core.fragment.CaFragment;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.data.BulletPoint;
import com.cocacola.climateambassador.data.BulletPointFrame;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.Document;
import com.cocacola.climateambassador.data.Module;
import com.cocacola.climateambassador.core.model.ModuleModel;
import com.cocacola.climateambassador.module.activity.AbsModuleActivity;
import com.cocacola.climateambassador.core.views.DocumentsLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.Views;
import timber.log.Timber;

/**
 * Created by Vinnie on 9/5/13.
 */
public class ModuleFragment extends CaFragment{

    public static ModuleFragment newInstance(Long moduleId) {

        ModuleFragment fragment = new ModuleFragment();
        fragment.setArguments(createBundleWithModuleId(moduleId));

        return fragment;

    }

    public static Bundle createBundleWithModuleId(Long moduleId) {

        Bundle bundle = new Bundle();
        bundle.putLong(AbsModuleActivity.EXTRA_MODULE_ID, moduleId);

        return bundle;
    }

    @Inject protected DaoMaster mDaoMaster;
    @Inject protected Timber Log;
    @InjectView(R.id.course_materials_label) protected TextView courseMaterialsLabelView;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getModule();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.module_frag, null);
        Views.inject(this, v);

        return v;

    }

    @Override public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        Module module = getModule();

        LayoutInflater inflater = LayoutInflater.from(v.getContext());

        if(!TextUtils.isEmpty(module.getTitle())) {
            ((TextView)v.findViewById(R.id.title)).setText(module.getTitle());
        }

        if(!TextUtils.isEmpty(module.getBodyText())) {
            ((TextView)v.findViewById(R.id.description)).setText(Html.fromHtml(module.getBodyText()));
        }

        // FIXME Create BulletPointFrame to encapsulate all this garbage

        // Bullet point frames
        LinearLayout caseFrames = (LinearLayout) v.findViewById(R.id.case_frames);
        if (module.getBulletPointFrame() != null) {
            BulletPointFrame caseBulletPointFrame = module.getBulletPointFrame();
            View bulletPointFrame = LayoutInflater.from(v.getContext()).inflate(R.layout.case_frame, null);

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
            for (BulletPoint bulletPoint : caseBulletPointFrame.getBulletPoints()) {
                View bulletPointLayout = inflater.inflate(R.layout.depr_view_bullet_point, null);
                ((TextView) bulletPointLayout.findViewById(R.id.bullet_text)).setText(bulletPoint.getText());
                //Must set text color, base color is white (for cases)
                ((TextView) bulletPointLayout.findViewById(R.id.bullet_text)).setTextColor(getResources().getColor(R.color.black));
                bulletList.addView(bulletPointLayout);
            }

            bulletList.setVisibility(View.VISIBLE);
            caseFrames.addView(bulletPointFrame);
        }

        List<Document> documentList = module.getDocuments();
        if(documentList.size() > 0) {
            DocumentsLayout documentsLayout = (DocumentsLayout) v.findViewById(R.id.module_documents);
            documentsLayout.setDocuments(documentList);
        }
        else {
            courseMaterialsLabelView.setVisibility(View.INVISIBLE);
        }

    }

    protected Module getModule() {
        return ModuleModel.getModule(mDaoMaster, getModuleId());
    }

    protected Long getModuleId() {
        return getArguments().getLong(AbsModuleActivity.EXTRA_MODULE_ID);
    }

}
