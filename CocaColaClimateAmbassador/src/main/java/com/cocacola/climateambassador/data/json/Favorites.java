package com.cocacola.climateambassador.data.json;

import java.util.List;

/**
 * Created by Vinnie Vendemia on 9/3/13.
 */
public class Favorites {


    private List<DocumentJson> courseMaterials;
    private List<DocumentJson> caseStudies;

    public Favorites(List<DocumentJson> courseMaterials, List<DocumentJson> caseStudies) {
        this.courseMaterials = courseMaterials;
        this.caseStudies = caseStudies;
    }

    public List<DocumentJson> getCourseMaterials() {
        return courseMaterials;
    }

    public void setCourseMaterials(List<DocumentJson> courseMaterials) {
        this.courseMaterials = courseMaterials;
    }

    public List<DocumentJson> getCaseStudies() {
        return caseStudies;
    }

    public void setCaseStudies(List<DocumentJson> caseStudies) {
        this.caseStudies = caseStudies;
    }

}
