package com.cocacola.climateambassador.core.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.cocacola.climateambassador.R;

import com.cocacola.climateambassador.data.BulletPoint;
import java.util.List;

/**
 * Created by realandylawton on 9/7/13.
 */
public class BulletPointLayout extends LinearLayout {

    public BulletPointLayout(Context context) {
        super(context);
    }

    public BulletPointLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BulletPointLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setBulletPoints(List<BulletPoint> bulletPoints) {

        if(bulletPoints == null) {
            return;
        }

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for(BulletPoint point : bulletPoints) {

            BulletPointView bulletPointView = (BulletPointView) inflater.inflate(R.layout.view_bullet_point, null);
            bulletPointView.setBulletPoint(point);

            addView(bulletPointView);

        }

    }

}
