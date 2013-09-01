package com.cocacola.climateambassador.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.models.NavigationDrawerItem;

import java.util.List;

/**
 * Created by Vinnie Vendemia on 8/27/13.
 */
public class MenuListAdapter extends BaseAdapter {

    private List<NavigationDrawerItem> mNavigationItems;

    private Context mContext;
    private LayoutInflater mInflater;

    public MenuListAdapter(Context context, List<NavigationDrawerItem> navigationItems) {
        mNavigationItems = navigationItems;
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mNavigationItems.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public NavigationDrawerItem getItem(int position) {
        return mNavigationItems.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        TextView txtTitle;
        ImageView imgIcon;

        View v = mInflater.inflate(R.layout.drawer_list_item, parent, false);

        // Locate the TextViews in drawer_list_item.xml
        txtTitle = (TextView) v.findViewById(R.id.title);
        imgIcon = (ImageView) v.findViewById(R.id.icon);

        NavigationDrawerItem item = mNavigationItems.get(position);

        txtTitle.setText(item.getTitle());
        imgIcon.setImageResource(item.getIconId());

        return v;
    }

}
