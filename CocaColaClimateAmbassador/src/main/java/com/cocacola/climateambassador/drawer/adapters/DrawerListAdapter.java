package com.cocacola.climateambassador.drawer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import com.cocacola.climateambassador.drawer.model.DrawerItem;

import java.util.List;

public abstract class DrawerListAdapter extends BaseAdapter {

    protected Context mContext;
    protected LayoutInflater mInflater;

    public DrawerListAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


}
