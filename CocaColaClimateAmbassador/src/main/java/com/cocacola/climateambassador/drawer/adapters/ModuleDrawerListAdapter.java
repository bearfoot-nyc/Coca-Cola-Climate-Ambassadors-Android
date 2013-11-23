package com.cocacola.climateambassador.drawer.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.drawer.model.DrawerItem;
import com.cocacola.climateambassador.drawer.model.ModuleDrawerItem;
import java.util.List;

/**
 * Created by realandylawton on 11/23/13.
 */
public class ModuleDrawerListAdapter extends DrawerListAdapter {

    private List<ModuleDrawerItem> mNavigationItems;

    public ModuleDrawerListAdapter(Context context, List<ModuleDrawerItem> navigationItems) {
        super(context);
        mNavigationItems = navigationItems;
    }

    @Override public int getCount() {
        return mNavigationItems.size();
    }

    @Override public ModuleDrawerItem getItem(int position) {
        return mNavigationItems.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).isHeader() ? 0 : 1;
    }

    @Override
    public boolean isEnabled(int position) {
        return !getItem(position).isHeader();
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ModuleDrawerItem item = getItem(position);

        View v = (item.isHeader()) ? getHeaderView(item) : getNavigationDrawerItemView(item);

        return v;
    }

    private View getHeaderView(DrawerItem item) {

        View v = mInflater.inflate(R.layout.drawer_header, null);

        TextView text = (TextView) v.findViewById(R.id.drawer_header_text);
        //text.setText(item.getTitle());

        // FIXME Actually add a header
        text.setText("TITLE");

        return v;

    }

    private View getNavigationDrawerItemView(ModuleDrawerItem item) {

        View v = mInflater.inflate(R.layout.drawer_list_item, null);

        TextView txtTitle = (TextView) v.findViewById(R.id.drawer_item_title);
        ImageView imgIcon = (ImageView) v.findViewById(R.id.drawer_item_icon);

        txtTitle.setText(item.getTitle());


        return v;

    }

}
