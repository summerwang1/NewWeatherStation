package com.beestar.jzb.newweathercode.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.beestar.jzb.newweathercode.bean.DeviceBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DEVICE_BEAN".
*/
public class DeviceBeanDao extends AbstractDao<DeviceBean, Long> {

    public static final String TABLENAME = "DEVICE_BEAN";

    /**
     * Properties of entity DeviceBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property SecondName = new Property(2, String.class, "secondName", false, "SECOND_NAME");
        public final static Property Mac = new Property(3, String.class, "mac", false, "MAC");
        public final static Property IsConn = new Property(4, boolean.class, "isConn", false, "IS_CONN");
        public final static Property IsChoose = new Property(5, boolean.class, "isChoose", false, "IS_CHOOSE");
        public final static Property Type = new Property(6, int.class, "type", false, "TYPE");
    };


    public DeviceBeanDao(DaoConfig config) {
        super(config);
    }
    
    public DeviceBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DEVICE_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"SECOND_NAME\" TEXT," + // 2: secondName
                "\"MAC\" TEXT," + // 3: mac
                "\"IS_CONN\" INTEGER NOT NULL ," + // 4: isConn
                "\"IS_CHOOSE\" INTEGER NOT NULL ," + // 5: isChoose
                "\"TYPE\" INTEGER NOT NULL );"); // 6: type
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DEVICE_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DeviceBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String secondName = entity.getSecondName();
        if (secondName != null) {
            stmt.bindString(3, secondName);
        }
 
        String mac = entity.getMac();
        if (mac != null) {
            stmt.bindString(4, mac);
        }
        stmt.bindLong(5, entity.getIsConn() ? 1L: 0L);
        stmt.bindLong(6, entity.getIsChoose() ? 1L: 0L);
        stmt.bindLong(7, entity.getType());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DeviceBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String secondName = entity.getSecondName();
        if (secondName != null) {
            stmt.bindString(3, secondName);
        }
 
        String mac = entity.getMac();
        if (mac != null) {
            stmt.bindString(4, mac);
        }
        stmt.bindLong(5, entity.getIsConn() ? 1L: 0L);
        stmt.bindLong(6, entity.getIsChoose() ? 1L: 0L);
        stmt.bindLong(7, entity.getType());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DeviceBean readEntity(Cursor cursor, int offset) {
        DeviceBean entity = new DeviceBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // secondName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // mac
            cursor.getShort(offset + 4) != 0, // isConn
            cursor.getShort(offset + 5) != 0, // isChoose
            cursor.getInt(offset + 6) // type
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DeviceBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSecondName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMac(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setIsConn(cursor.getShort(offset + 4) != 0);
        entity.setIsChoose(cursor.getShort(offset + 5) != 0);
        entity.setType(cursor.getInt(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DeviceBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DeviceBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
