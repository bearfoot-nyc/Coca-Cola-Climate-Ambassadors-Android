package com.cocacola.climateambassador.core.activity;

import android.os.Bundle;
import android.view.View;
import butterknife.OnClick;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.core.CaConstants;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.Navigable;
import javax.inject.Inject;

public class MainActivity extends RootDrawerActivity {

    @Inject protected DaoMaster mDaoMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

    }

    @OnClick({ R.id.home_btn_internal, R.id.home_btn_suppliers })
    public void onClickInternal(View view) {

        switch (view.getId()) {
            case R.id.home_btn_internal:
                onSectionItemClick(new Navigable() {
                    @Override public Long getId() {
                        return CaConstants.SECTION_ID_INTERNAL;
                    }

                    @Override public String getTitle() {
                        return "Internal Training Materials";
                    }

                    @Override public String getShortTitle() {
                        return "Internal Training Materials";
                    }
                });
                break;
            case R.id.home_btn_suppliers:
                onSectionItemClick(new Navigable() {
                    @Override public Long getId() {
                        return CaConstants.SECTION_ID_SUPPLIERS;
                    }

                    @Override public String getTitle() {
                        return "For Suppliers";
                    }

                    @Override public String getShortTitle() {
                        return "For Suppliers";
                    }
                });
        }

    }
}
