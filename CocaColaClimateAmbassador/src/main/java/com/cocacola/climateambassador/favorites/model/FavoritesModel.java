package com.cocacola.climateambassador.favorites.model;

import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.Document;
import com.cocacola.climateambassador.data.DocumentDao;
import java.util.List;

/**
 * Created by realandylawton on 12/1/13.
 */
public class FavoritesModel {
    public static List<Document> getFavorites(DaoMaster daoMaster) {

        List<Document> favoriteList = daoMaster.newSession().getDocumentDao().queryBuilder().
            where(DocumentDao.Properties.IsFavorite.eq(1)).
            list();

        return favoriteList;

    }
}
