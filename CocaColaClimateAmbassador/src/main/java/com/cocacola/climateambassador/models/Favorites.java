package com.cocacola.climateambassador.models;

import java.util.List;

/**
 * Created by Vinnie Vendemia on 9/3/13.
 */
public class Favorites {


    private List<Document> courseMaterials;
    private List<Document> caseStudies;

    public Favorites(List<Document> courseMaterials, List<Document> caseStudies) {
        this.courseMaterials = courseMaterials;
        this.caseStudies = caseStudies;
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
