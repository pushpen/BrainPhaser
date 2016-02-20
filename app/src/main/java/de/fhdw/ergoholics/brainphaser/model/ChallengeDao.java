package de.fhdw.ergoholics.brainphaser.model;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import de.fhdw.ergoholics.brainphaser.model.Challenge;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CHALLENGE".
*/
public class ChallengeDao extends AbstractDao<Challenge, Long> {

    public static final String TABLENAME = "CHALLENGE";

    /**
     * Properties of entity Challenge.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ChallengeType = new Property(1, int.class, "challengeType", false, "CHALLENGE_TYPE");
        public final static Property Question = new Property(2, String.class, "question", false, "QUESTION");
        public final static Property CategoryId = new Property(3, long.class, "categoryId", false, "CATEGORY_ID");
    };

    private Query<Challenge> category_ChallengesQuery;

    public ChallengeDao(DaoConfig config) {
        super(config);
    }
    
    public ChallengeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CHALLENGE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"CHALLENGE_TYPE\" INTEGER NOT NULL ," + // 1: challengeType
                "\"QUESTION\" TEXT NOT NULL ," + // 2: question
                "\"CATEGORY_ID\" INTEGER NOT NULL );"); // 3: categoryId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CHALLENGE\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Challenge entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getChallengeType());
        stmt.bindString(3, entity.getQuestion());
        stmt.bindLong(4, entity.getCategoryId());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Challenge readEntity(Cursor cursor, int offset) {
        Challenge entity = new Challenge( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // challengeType
            cursor.getString(offset + 2), // question
            cursor.getLong(offset + 3) // categoryId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Challenge entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setChallengeType(cursor.getInt(offset + 1));
        entity.setQuestion(cursor.getString(offset + 2));
        entity.setCategoryId(cursor.getLong(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Challenge entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Challenge entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "challenges" to-many relationship of Category. */
    public List<Challenge> _queryCategory_Challenges(long categoryId) {
        synchronized (this) {
            if (category_ChallengesQuery == null) {
                QueryBuilder<Challenge> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.CategoryId.eq(null));
                category_ChallengesQuery = queryBuilder.build();
            }
        }
        Query<Challenge> query = category_ChallengesQuery.forCurrentThread();
        query.setParameter(0, categoryId);
        return query.list();
    }

}
