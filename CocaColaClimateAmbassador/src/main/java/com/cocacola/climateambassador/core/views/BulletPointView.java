package com.cocacola.climateambassador.core.views;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cocacola.climateambassador.R;

import static butterknife.Views.*;

/**
 * Created by realandylawton on 9/7/13.
 */
public class BulletPointView extends LinearLayout {

    private TextView mBulletPointView;

    public BulletPointView(Context context) {
        super(context);
    }

    public BulletPointView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BulletPointView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mBulletPointView = findById(this, R.id.bullet_text);
    }

    public void setBulletPoint(String point) {

        if(point != null) {
            mBulletPointView.setText(Html.fromHtml(point));
        }

    }
}
