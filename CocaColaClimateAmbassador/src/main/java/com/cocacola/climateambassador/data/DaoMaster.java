package com.cocacola.climateambassador.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;

import com.cocacola.climateambassador.data.SectionDao;
import com.cocacola.climateambassador.data.ModuleDao;
import com.cocacola.climateambassador.data.CaCaseDao;
import com.cocacola.climateambassador.data.TextFrameDao;
import com.cocacola.climateambassador.data.SubtitleTextPairDao;
import com.cocacola.climateambassador.data.DocumentDao;
import com.cocacola.climateambassador.data.BulletPointFrameDao;
import com.cocacola.climateambassador.data.BulletPointDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * Master of DAO (schema version 5): knows all DAOs.
*/
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 5;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(SQLiteDatabase db, boolean ifNotExists) {
        SectionDao.createTable(db, ifNotExists);
        ModuleDao.createTable(db, ifNotExists);
        CaCaseDao.createTable(db, ifNotExists);
        TextFrameDao.createTable(db, ifNotExists);
        SubtitleTextPairDao.createTable(db, ifNotExists);
        DocumentDao.createTable(db, ifNotExists);
        BulletPointFrameDao.createTable(db, ifNotExists);
        BulletPointDao.createTable(db, ifNotExists);
    }
    
    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(SQLiteDatabase db, boolean ifExists) {
        SectionDao.dropTable(db, ifExists);
        ModuleDao.dropTable(db, ifExists);
        CaCaseDao.dropTable(db, ifExists);
        TextFrameDao.dropTable(db, ifExists);
        SubtitleTextPairDao.dropTable(db, ifExists);
        DocumentDao.dropTable(db, ifExists);
        BulletPointFrameDao.dropTable(db, ifExists);
        BulletPointDao.dropTable(db, ifExists);
    }
    
    public static abstract class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }
    
    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

    public DaoMaster(SQLiteDatabase db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(SectionDao.class);
        registerDaoClass(ModuleDao.class);
        registerDaoClass(CaCaseDao.class);
        registerDaoClass(TextFrameDao.class);
        registerDaoClass(SubtitleTextPairDao.class);
        registerDaoClass(DocumentDao.class);
        registerDaoClass(BulletPointFrameDao.class);
        registerDaoClass(BulletPointDao.class);
    }
    
    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }
    
    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }
    
}
