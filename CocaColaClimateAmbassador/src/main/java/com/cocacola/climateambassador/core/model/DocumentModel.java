package com.cocacola.climateambassador.core.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.DaoSession;
import com.cocacola.climateambassador.data.Document;
import com.cocacola.climateambassador.data.DocumentDao;
import com.cocacola.climateambassador.data.json.DocumentJson;
import java.util.List;

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

    public static Cursor searchCursor(DaoMaster daoMaster, String query) {

        // Build the search query;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(DocumentDao.Properties.Label.columnName);
        sb.append(" FROM ");
        sb.append(DocumentDao.TABLENAME);
        sb.append(" WHERE ");
        sb.append(DocumentDao.Properties.Label.columnName);
        sb.append(" LIKE ");
        sb.append("?;");

        String statement = sb.toString();
        String[] args = new String[] { "%"+query.toLowerCase()+"%" };

        Cursor cursor =  daoMaster.newSession().getDatabase().
            rawQuery(statement, args);

        return cursor;

    }

    public static List<Document> search(DaoMaster daoMaster, String query) {

        DocumentDao documentDao = daoMaster.newSession().getDocumentDao();

        List<Document> results = documentDao.queryBuilder().
            where(DocumentDao.Properties.Label.like("%" + query.toLowerCase() + "%")).
            list();

        return results;

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
