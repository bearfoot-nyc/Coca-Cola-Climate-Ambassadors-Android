package com.cocacola.climateambassador.data;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table SUBTITLE_TEXT_PAIR.
 */
public class SubtitleTextPair {

    private String title;
    private String text;
    private long textFrameId;

    public SubtitleTextPair() {
    }

    public SubtitleTextPair(String title, String text, long textFrameId) {
        this.title = title;
        this.text = text;
        this.textFrameId = textFrameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTextFrameId() {
        return textFrameId;
    }

    public void setTextFrameId(long textFrameId) {
        this.textFrameId = textFrameId;
    }

}
