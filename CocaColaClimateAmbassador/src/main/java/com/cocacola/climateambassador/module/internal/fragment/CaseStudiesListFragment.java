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
import com.cocacola.climateambassador.data.CaCase;
import com.cocacola.climateambassador.data.CaCaseDao;
import com.cocacola.climateambassador.data.DaoMaster;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 * Created by realandylawton on 9/4/13.
 */
public class CaseStudiesListFragment extends CaFragment {

    @Inject DaoMaster mDaoMaster;

    private CaseStudyListAdapter mAdapter;
    private ListView mListView;
    private List<CaCase> mCaseStudyList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new CaseStudyListAdapter(getActivity(), getCaseStudyList());
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

    private List<CaCase> getCaseStudyList() {

        if(mCaseStudyList == null) {
            CaCaseDao dao = mDaoMaster.newSession().getCaCaseDao();
            mCaseStudyList = dao.loadAll();
        }

        return mCaseStudyList;
    }

    private static class CaseStudyListAdapter extends BaseAdapter {

        private static final Map<String, Integer> sCaseIconMap = new HashMap<String, Integer>();
        static {
            sCaseIconMap.put("Ingredients", R.drawable.ic_cases_list_ingredients);
            sCaseIconMap.put("Packaging", R.drawable.ic_cases_list_packaging);
            sCaseIconMap.put("Manufacturing", R.drawable.ic_cases_list_manufacturing);
            sCaseIconMap.put("Distribution", R.drawable.ic_cases_list_distribution);
            sCaseIconMap.put("Refrigeration", R.drawable.ic_cases_list_refrigeration);
        }

        private List<CaCase> mItems;
        private Context mContext;
        private LayoutInflater mInflater;

        private CaseStudyListAdapter(Context context, List<CaCase> items) {
            mItems = items;
            mContext = context;
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public CaCase getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = mInflater.inflate(R.layout.case_studies_list_item, null);

            final CaCase item = getItem(position);

            Button title = (Button) v.findViewById(R.id.case_study_list_item_button);
            title.setText(item.getTitle());
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, CaseActivity.class);
                    intent.putExtra(CaseActivity.EXTRA_CASE_ID, item.getId());
                    mContext.startActivity(intent);
                }
            });

            ImageView icon = (ImageView) v.findViewById(R.id.case_study_list_item_icon);
            Integer iconResId = sCaseIconMap.get(item.getTitle());

            if(iconResId != null) {
                icon.setImageResource(iconResId);
            }

            return v;

        }
    }

}
