package com.cocacola.climateambassador.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.models.BulletPointFrame;
import com.cocacola.climateambassador.models.Case;

/**
 * Created by Vinnie Vendemia on 8/29/13.
 */

public class CaseFragment extends CaFragment {

    private Case mCase;

    public static CaseFragment newInstance() {
        CaseFragment fragment = new CaseFragment();
        return fragment;
      }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Context context = getActivity();
        View view = inflater.inflate(R.layout.case_fragment, container, false);
        ((TextView)view.findViewById(R.id.case_title)).setText(mCase.getTitle());

        if(mCase.getBodyText() != null) {
            ((TextView)view.findViewById(R.id.body_text)).setText(mCase.getBodyText());
        }

        LinearLayout caseFrames = (LinearLayout)view.findViewById(R.id.case_frames);
        if(mCase.getBulletPointFrame() != null) {
            BulletPointFrame caseBulletPointFrame = mCase.getBulletPointFrame();
            LinearLayout bulletPointFrame = new LinearLayout(context , null, R.layout.case_frame);
            ((TextView)bulletPointFrame.findViewById(R.id.case_frame_title)).setText(caseBulletPointFrame.getTitle());
            ((TextView)bulletPointFrame.findViewById(R.id.case_frame_subtitle)).setText(caseBulletPointFrame.getSubtitle());

            //Bullet List Base Layout
            LinearLayout bulletList = new LinearLayout(context, null, R.id.bullet_list_content);

            //Add Bullet Points to Layout
            for(String bulletPoint: caseBulletPointFrame.getBulletPoints() ) {
                View bulletPointLayout = inflater.inflate(R.layout.case_frame_bullet_point, null);
                ((TextView)bulletPointLayout.findViewById(R.id.bullet_text)).setText(bulletPoint);
                bulletList.addView(bulletPointLayout);
            }


        }


        return view;
    }
}
