package com.cocacola.climateambassador.data.json;

import java.util.List;

/**
 * Created by realandylawton on 8/31/13.
 */
public class TextFrameJson {

    private String title;
    private String bodyText;
    private List<SubtitleTextPairJson> subtitleTextPairs;

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

    public List<SubtitleTextPairJson> getSubtitleTextPairs() {
        return subtitleTextPairs;
    }

    public void setSubtitleTextPairs(List<SubtitleTextPairJson> subtitleTextPairs) {
        this.subtitleTextPairs = subtitleTextPairs;
    }
}
