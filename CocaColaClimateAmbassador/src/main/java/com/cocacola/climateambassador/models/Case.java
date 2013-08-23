package com.cocacola.climateambassador.models;

import java.util.List;

/**
 * Created by andrewlawton on 8/23/13.
 */
public class Case {

    public static enum CaseDocumentTypes {
        COURSE_MATERIALS, CASE_STUDIES
    }

    private String bodyText;
    private List<Document> courseMaterials;
    private List<Document> caseStudies;

    public Case(String bodyText, List<Document> courseMaterials, List<Document> caseStudies) {
        this.bodyText = bodyText;
        this.courseMaterials = courseMaterials;
        this.caseStudies = caseStudies;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public List<Document> getCourseMaterials() {
        return courseMaterials;
    }

    public void setCourseMaterials(List<Document> courseMaterials) {
        this.courseMaterials = courseMaterials;
    }

    public List<Document> getCaseStudies() {
        return caseStudies;
    }

    public void setCaseStudies(List<Document> caseStudies) {
        this.caseStudies = caseStudies;
    }
}
