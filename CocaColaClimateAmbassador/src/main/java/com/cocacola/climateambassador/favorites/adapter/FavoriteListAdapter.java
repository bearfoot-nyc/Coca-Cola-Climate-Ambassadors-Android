package com.cocacola.climateambassador.favorites.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.cocacola.climateambassador.data.Document;
import com.cocacola.climateambassador.favorites.model.FavoritesModel;
import com.cocacola.climateambassador.favorites.view.FavoriteRowView;
import java.util.List;

/**
 * Created by realandylawton on 12/1/13.
 */
public class FavoriteListAdapter extends BaseAdapter implements
    FavoriteRowView.OnFavoriteChangedListener {

    private Context mContext;
    private List<Document> mFavoriteList;
    private LayoutInflater mInflater;

    public FavoriteListAdapter(Context context, List<Document> favoriteList) {
        mContext = context;
        mFavoriteList = favoriteList;
        mInflater = LayoutInflater.from(context);
    }

    @Override public int getCount() {
        return mFavoriteList.size();
    }

    @Override public Document getItem(int position) {
        return mFavoriteList.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {

        Document document = getItem(position);

        FavoriteRowView view = FavoriteRowView.inflate(mInflater, document);
        view.setOnFavoriteChangedListener(this);

        return view;

    }

    @Override public void onFavoriteChanged(boolean favorited) {
        // Won't implement.  Activity that hosts this will override
    }

    public void setFavoriteList(List<Document> favoritesList) {

        mFavoriteList = favoritesList;
        notifyDataSetChanged();

    }
}
