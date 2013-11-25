package com.cocacola.climateambassador.module.internal.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.cases.activity.CaseActivity;
import com.cocacola.climateambassador.core.fragment.CaFragment;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by realandylawton on 9/4/13.
 */
public class CaseStudiesListFragment extends CaFragment {

    private CaseStudiesAdapter mAdapter;

    private ListView mListView;
    private List<CaseStudyListItem> mCaseStudyListItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new CaseStudiesAdapter(getActivity(), getCaseStudyListItems());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_case_studies_list, container, true);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = (ListView) view.findViewById(R.id.case_studies_list_view);
        mListView.setAdapter(mAdapter);


    }

    private List<CaseStudyListItem> getCaseStudyListItems() {

        if(mCaseStudyListItems == null) {
            mCaseStudyListItems = new LinkedList<CaseStudyListItem>();
            mCaseStudyListItems.add(new CaseStudyListItem(R.drawable.ic_cases_list_ingredients, "Ingredients"));
            mCaseStudyListItems.add(new CaseStudyListItem(R.drawable.ic_cases_list_packaging, "Packaging"));
            mCaseStudyListItems.add(new CaseStudyListItem(R.drawable.ic_cases_list_manufacturing, "Manufacturing"));
            mCaseStudyListItems.add(new CaseStudyListItem(R.drawable.ic_cases_list_distribution, "Distribution"));
            mCaseStudyListItems.add(new CaseStudyListItem(R.drawable.ic_cases_list_refrigeration, "Refrigeration"));
        }

        return mCaseStudyListItems;
    }

    private class CaseStudiesAdapter extends BaseAdapter {

        private List<CaseStudyListItem> mItems;

        private Context mContext;
        private LayoutInflater mInflater;

        private CaseStudiesAdapter(Context context, List<CaseStudyListItem> items) {
            mItems = items;
            mContext = context;
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public CaseStudyListItem getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = mInflater.inflate(R.layout.case_studies_list_item, null);

            final CaseStudyListItem item = getItem(position);

            Button title = (Button) v.findViewById(R.id.case_study_list_item_button);
            title.setText(item.getTitle());
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, CaseActivity.class);
                    intent.putExtra(CaseActivity.BUNDLE_KEY_CASE_ID, 1l);
                    mContext.startActivity(intent);
                }
            });

            ImageView icon = (ImageView) v.findViewById(R.id.case_study_list_item_icon);
            icon.setImageResource(item.getIconResId());

            return v;

        }
    }

    private class CaseStudyListItem {

        private int iconResId;
        private String title;

        private CaseStudyListItem(int iconResId, String title) {
            this.iconResId = iconResId;
            this.title = title;
        }

        public int getIconResId() {
            return iconResId;
        }

        public void setIconResId(int iconResId) {
            this.iconResId = iconResId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

    }
}
