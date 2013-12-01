package com.cocacola.climateambassador.drawer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.data.Navigable;
import com.cocacola.climateambassador.drawer.view.DrawerRowView;
import java.util.List;

/**
 * Created by realandylawton on 12/1/13.
 */
public class DrawerListAdapter extends BaseAdapter {

    protected Context mContext;
    protected List<Navigable> mNavigableList;

    public DrawerListAdapter(Context context, List<Navigable> navigableList) {
        mContext = context;
        mNavigableList = navigableList;
    }

    @Override public int getCount() {
        return mNavigableList.size();
    }

    @Override public Navigable getItem(int position) {
        return mNavigableList.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {

        Navigable item = getItem(position);

        DrawerRowView view =
            (DrawerRowView) LayoutInflater.from(mContext).inflate(R.layout.view_drawer_row, null);
        view.setModel(item);

        return view;

    }
}
