package com.cocacola.climateambassador.core.activity;

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
import com.cocacola.climateambassador.core.util.DocumentIntentBuilder;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.Document;
import javax.inject.Inject;

/**
 * Created by realandylawton on 11/30/13.
 */
public abstract class CaSearchableDrawerActivity extends CaDrawerActivity implements SearchView.OnQueryTextListener,
 SearchView.OnSuggestionListener {

    @Inject protected DaoMaster mDaoMaster;
    @Inject protected DocumentIntentBuilder mDocumentIntentBuilder;

    private SearchView mSearchView;
    private SuggestionsAdapter mSuggestionsAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_searchview, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) searchItem.getActionView();
        mSearchView.setQueryHint(getString(R.string.search_query_hint));
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setOnSuggestionListener(this);
        mSearchView.setSuggestionsAdapter(mSuggestionsAdapter);

        return true;
    }

    public void updateDocumentSuggestions(String query) {

        Cursor cursor = DocumentModel.searchCursor(mDaoMaster, query);

        if(mSuggestionsAdapter == null) {
            mSearchView.setSuggestionsAdapter(new SuggestionsAdapter(this, cursor));
        }
        else {
            mSearchView.getSuggestionsAdapter().swapCursor(cursor);
        }

    }

    public Document getSuggestedDocument(int position) {

        Cursor cursor = (Cursor) mSearchView.getSuggestionsAdapter().getItem(position);

        Long cursorId = cursor.getLong(0);

        Document document = mDaoMaster.newSession().getDocumentDao().load(cursorId);

        return document;



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
            tv.setTextColor(getResources().getColor(R.color.gray));
            tv.setText(cursor.getString(1));
        }
    }

}
