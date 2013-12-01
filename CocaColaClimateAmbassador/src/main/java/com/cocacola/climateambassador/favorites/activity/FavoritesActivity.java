package com.cocacola.climateambassador.favorites.activity;

import android.os.Bundle;
import android.widget.ListView;
import butterknife.InjectView;
import butterknife.Views;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.core.activity.CaDrawerSearchableActivity;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.Document;
import com.cocacola.climateambassador.favorites.adapter.FavoriteListAdapter;
import com.cocacola.climateambassador.favorites.model.FavoritesModel;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by realandylawton on 12/1/13.
 */
public class FavoritesActivity extends CaDrawerSearchableActivity {

    @Inject protected DaoMaster mDaoMaster;
    @InjectView(android.R.id.list) protected ListView mListView;

    private FavoriteListAdapter mListAdapter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_activity);

        mListAdapter = new FavoriteListAdapter(this, getFavoritesList()) {
            @Override public void onFavoriteChanged(boolean favorited) {
                FavoritesActivity.this.onFavoriteChanged();
            }
        };
        //mListView.setEmptyView(Views.findById(this, android.R.id.empty));
        mListView.setAdapter(mListAdapter);

    }

    public void onFavoriteChanged() {
        mListAdapter.setFavoriteList(getFavoritesList());
    }

    private List<Document> getFavoritesList() {
        return FavoritesModel.getFavorites(mDaoMaster);
    }
}
