package com.cocacola.climateambassador.favorites.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.core.views.DocumentView;
import com.cocacola.climateambassador.data.Document;

/**
 * Created by realandylawton on 12/1/13.
 */
public class FavoriteRowView extends DocumentView {

    public interface OnFavoriteChangedListener {
        public void onFavoriteChanged(boolean favorited);
    }

    public static FavoriteRowView inflate(LayoutInflater inflater, Document document) {

        FavoriteRowView view = (FavoriteRowView) inflater.inflate(R.layout.view_favorite_row, null);
        view.setDocument(document);

        return view;

    }

    private OnFavoriteChangedListener mOnFavoriteChangedListener;

    public FavoriteRowView(Context context) {
        super(context);
    }

    public FavoriteRowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FavoriteRowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public OnFavoriteChangedListener getOnFavoriteChangedListener() {
        return mOnFavoriteChangedListener;
    }

    public void setOnFavoriteChangedListener(OnFavoriteChangedListener onFavoriteChangedListener) {
        mOnFavoriteChangedListener = onFavoriteChangedListener;
    }

    @Override protected void onFavoriteClick(Document document) {
        super.onFavoriteClick(document);

        if(mOnFavoriteChangedListener != null) {
            mOnFavoriteChangedListener.onFavoriteChanged(document.getIsFavorite());
        }

    }
}
