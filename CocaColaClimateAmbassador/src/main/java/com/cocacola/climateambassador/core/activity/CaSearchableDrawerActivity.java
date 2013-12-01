package com.cocacola.climateambassador.core.activity;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SearchView;
import android.widget.TextView;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.core.model.DocumentModel;
import com.cocacola.climateambassador.data.DaoMaster;
import javax.inject.Inject;

/**
 * Created by realandylawton on 11/30/13.
 */
public abstract class CaSearchableDrawerActivity extends CaDrawerActivity implements SearchView.OnQueryTextListener,
 SearchView.OnSuggestionListener {

    @Inject protected DaoMaster mDaoMaster;
    protected SearchView mSearchView;

    protected SuggestionsAdapter mSuggestionsAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_searchview, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) searchItem.getActionView();
        mSearchView.setQueryHint("Search for countries…");
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setOnSuggestionListener(this);

        Cursor cursor = DocumentModel.searchCursor(mDaoMaster, "Climate");

        if (mSuggestionsAdapter == null) {
            mSuggestionsAdapter = new SuggestionsAdapter(this, cursor);
        }

        mSearchView.setSuggestionsAdapter(mSuggestionsAdapter);

        return true;
    }

    public SuggestionsAdapter getSuggestionsAdapter() {
        return mSuggestionsAdapter;
    }

    public void setSuggestionsAdapter(SuggestionsAdapter suggestionsAdapter) {
        mSuggestionsAdapter = suggestionsAdapter;
    }

    public void swapCursor(Cursor cursor) {
        mSearchView.getSuggestionsAdapter().swapCursor(cursor);
    }

    private class SuggestionsAdapter extends CursorAdapter {

        public SuggestionsAdapter(Context context, Cursor c) {
            super(context, c, 0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return v;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView tv = (TextView) view;
            tv.setText(cursor.getString(1));
        }
    }

}
