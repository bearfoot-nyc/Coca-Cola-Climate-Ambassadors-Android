package com.cocacola.climateambassador.core.activity;

import android.os.Bundle;
import android.view.View;
import butterknife.InjectView;
import butterknife.OnClick;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.core.CaConstants;
import com.cocacola.climateambassador.core.controller.AudioController;
import com.cocacola.climateambassador.core.views.AudioTrackView;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.Document;
import com.cocacola.climateambassador.data.Navigable;
import javax.inject.Inject;

public class MainActivity extends RootDrawerActivity {

    @Inject protected DaoMaster mDaoMaster;
    @InjectView(R.id.home_audio_track) protected AudioTrackView mAudioTrackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        mAudioTrackView.setController(new AudioController(this));
        mAudioTrackView.setModel(getAudioTrack());

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

    @Override protected void onDestroy() {
        if(mAudioTrackView != null) {
            mAudioTrackView.releaseAudio();
        }
        super.onDestroy();
    }

    public Document getAudioTrack() {

        Document document = new Document();
        document.setLabel("Listen to this Track");
        document.setFileName("track1");

        return document;

    }
}
