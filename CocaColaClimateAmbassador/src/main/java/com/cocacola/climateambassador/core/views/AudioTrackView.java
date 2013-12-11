package com.cocacola.climateambassador.core.views;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Views;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.core.CaApplication;
import com.cocacola.climateambassador.core.HasController;
import com.cocacola.climateambassador.core.controller.AudioController;
import com.cocacola.climateambassador.core.util.AppPackageFileWriter;
import com.cocacola.climateambassador.core.util.EmailAttachmentIntentBuilder;
import com.cocacola.climateambassador.core.util.HasModel;
import com.cocacola.climateambassador.core.util.Toaster;
import com.cocacola.climateambassador.data.Document;
import javax.inject.Inject;

import static com.cocacola.climateambassador.core.controller.AudioController.State.PAUSED;
import static com.cocacola.climateambassador.core.controller.AudioController.State.PLAYING;

/**
 * Created by realandylawton on 12/10/13.
 */
public class AudioTrackView extends LinearLayout implements HasModel<Document>, HasController<AudioController>,
    AudioController.OnAudioStateChangeListener {

    @Inject protected EmailAttachmentIntentBuilder mEmailIntentBuilder;

    private ImageView mActionBtn;
    private TextView mTitleView;
    private AudioController mAudioController;
    private Document mDocument;

    public AudioTrackView(Context context) {
        super(context);
        init(context);
    }

    public AudioTrackView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AudioTrackView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();

        mActionBtn = Views.findById(this, R.id.audio_action_btn);
        mTitleView = Views.findById(this, R.id.audio_title);

    }

    @Override public void setController(AudioController audioController) {
        mAudioController = audioController;
    }

    @Override public void setModel(Document document) {
        mDocument = document;
        updateView();
    }

    private void init(Context context) {
        CaApplication.getObjectGraph(context).inject(this);
    }

    private void updateView() {
        mTitleView.setText(mDocument.getLabel());
        mAudioController.setListener(this);
        mActionBtn.setTag(PAUSED);
        mActionBtn.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View v) {
                onActionClick();
            }
        });
    }

    protected void onActionClick() {

        AudioController.State state = (AudioController.State) mActionBtn.getTag();

        switch (state) {
            case PAUSED:
                mAudioController.playTrack(mDocument);
                //onPlay();
                break;
            case PLAYING:
                mAudioController.pauseTrack(mDocument);
                //onPaused();
                break;
        }

    }

    protected void onShareClick() {

        try {
            Intent intent = mEmailIntentBuilder.createIntent(getContext(), mDocument);
            getContext().startActivity(Intent.createChooser(intent, "Email To:"));

        } catch (AppPackageFileWriter.PackageWriteException e) {
            Toaster.toast(getContext(), "Failed to find file");
        }
    }

    @Override public void onPlay() {
        mActionBtn.setImageResource(android.R.drawable.ic_media_pause);
        mActionBtn.setTag(PLAYING);
    }

    @Override public void onPaused() {
        mActionBtn.setImageResource(android.R.drawable.ic_media_play);
        mActionBtn.setTag(PAUSED);
    }

    @Override public void onLoading() {
        mActionBtn.setImageResource(android.R.drawable.ic_lock_power_off);
        mActionBtn.setTag(PAUSED);
    }

    public void releaseAudio() {
        mAudioController.stop();
        mAudioController = null;
    }
}
