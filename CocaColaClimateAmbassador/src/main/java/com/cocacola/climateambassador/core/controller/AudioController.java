package com.cocacola.climateambassador.core.controller;

import android.app.Activity;
import android.media.MediaPlayer;
import com.cocacola.climateambassador.data.Document;

/**
 * Created by realandylawton on 12/10/13.
 */
public class AudioController extends CaController implements MediaPlayer.OnCompletionListener {

    public interface OnAudioStateChangeListener {
        public void onPlay();
        public void onPaused();
        public void onLoading();
    }

    public enum State {
        PAUSED, LOADING, PLAYING
    }

    private MediaPlayer mMediaPlayer;
    private OnAudioStateChangeListener mListener;

    public AudioController(Activity activity) {
        super(activity);
    }

    public void setListener(OnAudioStateChangeListener listener) {
        mListener = listener;
    }

    public void playTrack(Document document) {

        if(mMediaPlayer == null) {

            // Get resource from String value
            int resId = getRawResourceFromString(document.getFileName());

            mMediaPlayer = MediaPlayer.create(mActivity, resId);
            mMediaPlayer.setOnCompletionListener(this);

        }

        mMediaPlayer.start();

        notifyListener(State.PLAYING);

    }

    public void pauseTrack(Document document) {
        if(mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            notifyListener(State.PAUSED);
        }
    }

    public void stop() {
        if(mMediaPlayer != null) {
            mMediaPlayer.stop();;
        }
    }

    private int getRawResourceFromString(String resName)     {

        String packageName = mActivity.getApplicationContext().getPackageName();

        int resId = mActivity.getResources().
            getIdentifier(resName, "raw", packageName);

        return resId;

    }

    private void notifyListener(State state) {

        if(mListener == null) {
            return;
        }

        switch (state) {
            case PLAYING:
                mListener.onPlay();
                break;
            case PAUSED:
                mListener.onPaused();
                break;
            case LOADING:
                mListener.onLoading();
                break;
        }
    }

    @Override public void onCompletion(MediaPlayer mp) {
        notifyListener(State.PAUSED);
    }

}
