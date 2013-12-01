package com.cocacola.climateambassador.drawer.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Views;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.core.util.HasModel;
import com.cocacola.climateambassador.data.Navigable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by realandylawton on 12/1/13.
 */
public class DrawerRowView extends LinearLayout implements HasModel<Navigable> {

    private TextView mTitleView;

    public DrawerRowView(Context context) {
        super(context);
    }

    public DrawerRowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawerRowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();

        mTitleView = Views.findById(this, R.id.drawer_row_title);

    }

    @Override public void setModel(Navigable model) {

        mTitleView.setText(model.getShortTitle());

    }
}
