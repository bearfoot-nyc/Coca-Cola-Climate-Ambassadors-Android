package com.cocacola.climateambassador.core.model;

import com.cocacola.climateambassador.data.Document;
import com.cocacola.climateambassador.data.json.DocumentJson;

/**
 * Created by realandylawton on 11/27/13.
 */
public class DocumentModel {
    public static Document fromJson(DocumentJson documentJson) {

        Document document = new Document();
        document.setLabel(documentJson.getLabel());
        document.setFileName(documentJson.getFileName());
        document.setIsFavorite(false);

        return document;

    }

    public static Document forModulefromJson(DocumentJson documentJson, Long moduleId) {

        Document document = fromJson(documentJson);
        document.setModuleId(moduleId);

        return document;

    }

    public static Document forCasefromJson(DocumentJson documentJson, Long caseId) {

        Document document = fromJson(documentJson);
        document.setCaseId(caseId);

        return document;

    }

}
