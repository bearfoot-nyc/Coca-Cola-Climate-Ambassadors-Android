package com.cocacola.climateambassador.models;

import java.util.List;

/**
 * Created by realandylawton on 8/31/13.
 */
public class TextFrame {

    private String title;
    private String bodyText;
    private List<SubtitleTextPair> subtitleTextPairs;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public List<SubtitleTextPair> getSubtitleTextPairs() {
        return subtitleTextPairs;
    }

    public void setSubtitleTextPairs(List<SubtitleTextPair> subtitleTextPairs) {
        this.subtitleTextPairs = subtitleTextPairs;
    }
}
