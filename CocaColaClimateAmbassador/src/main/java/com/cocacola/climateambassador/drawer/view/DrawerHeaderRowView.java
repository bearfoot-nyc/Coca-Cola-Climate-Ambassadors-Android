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
public class DrawerHeaderRowView extends LinearLayout implements HasModel<Navigable> {

    private static Map<String, Integer> sIcons = new LinkedHashMap<String, Integer>();
    static {
        sIcons.put("Favorites", android.R.drawable.ic_media_play);
        sIcons.put("Internal Training Materials", R.drawable.ic_drawer_folder);
        sIcons.put("For Suppliers", R.drawable.ic_drawer_wrench);
    }

    private TextView mTitleView;
    private ImageView mIconView;

    public DrawerHeaderRowView(Context context) {
        super(context);
    }

    public DrawerHeaderRowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawerHeaderRowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();

        mTitleView = Views.findById(this, R.id.drawer_row_title);
        mIconView = Views.findById(this, R.id.drawer_row_icon);

    }

    @Override public void setModel(Navigable model) {

        mTitleView.setText(model.getShortTitle());
        mIconView.setImageResource(sIcons.get(model.getTitle()));

    }
}
