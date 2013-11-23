package com.cocacola.climateambassador.drawer.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Views;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.drawer.model.MainDrawerItem;
import java.util.List;

/**
 * Created by realandylawton on 11/23/13.
 */
public class MainDrawerListAdapter extends DrawerListAdapter {

    private List<MainDrawerItem> mDrawerItems;

    public MainDrawerListAdapter(Context context, List<MainDrawerItem> mainDrawerItems) {
        super(context);
        mDrawerItems = mainDrawerItems;
    }

    @Override public int getCount() {
        return mDrawerItems.size();
    }

    @Override public MainDrawerItem getItem(int position) {
        return mDrawerItems.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {

        View v = mInflater.inflate(R.layout.drawer_list_item, null);

        TextView txtTitle = Views.findById(v, R.id.drawer_item_title);
        ImageView imgIcon =  Views.findById(v, R.id.drawer_item_icon);

        MainDrawerItem item = getItem(position);

        txtTitle.setText(item.getTitle());
        imgIcon.setImageResource(item.getIconId());

        return v;

    }
}
