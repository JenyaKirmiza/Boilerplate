package com.jenyakirmiza.boilerplate.data.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jenyakirmiza.boilerplate.data.model.Author;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

@Singleton
public class DatabaseHelper {

    private final BriteDatabase mDb;

    @Inject
    public DatabaseHelper(DbOpenHelper dbOpenHelper) {
        SqlBrite.Builder briteBuilder = new SqlBrite.Builder();
        mDb = briteBuilder.build().wrapDatabaseHelper(dbOpenHelper, Schedulers.immediate());
        this.mDbOpenHelper=dbOpenHelper;
    }

    private DbOpenHelper mDbOpenHelper;
    public BriteDatabase getBriteDb() {
        return mDb;
    }

    public void syncRibots(){

        ContentValues values = new ContentValues();
        values.put(Db.RibotProfileTable.COLUMN_NAME, "");
        values.put(Db.RibotProfileTable.COLUMN_URL, "");
        //values.put(Db.RibotProfileTable.COLUMN_IMAGE_URL, "");
        //values.put(Db.RibotProfileTable.COLUMN_AUTHOR_DESCRIPTION, "");

        SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();

        boolean createSuccessful = db.insert(Db.RibotProfileTable.TABLE_NAME, null, values) > 0;
        db.close();

    }

    /*public Observable<Author> setRibots(final Collection<Ribot> newRibots) {
        return Observable.create(new Observable.OnSubscribe<Ribot>() {
            @Override
            public void call(Subscriber<? super Ribot> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                BriteDatabase.Transaction transaction = mDb.newTransaction();
                try {
                    mDb.delete(Db.RibotProfileTable.TABLE_NAME, null);
                    for (Ribot ribot : newRibots) {
                        long result = mDb.insert(Db.RibotProfileTable.TABLE_NAME,
                                Db.RibotProfileTable.toContentValues(ribot.profile()),
                                SQLiteDatabase.CONFLICT_REPLACE);
                        if (result >= 0) subscriber.onNext(ribot);
                    }
                    transaction.markSuccessful();
                    subscriber.onCompleted();
                } finally {
                    transaction.end();
                }
            }
        });
    }*/



    public Observable<List<Author>> getRibots() {
        return mDb.createQuery(Db.RibotProfileTable.TABLE_NAME,
                "SELECT * FROM " + Db.RibotProfileTable.TABLE_NAME)
                .mapToList(new Func1<Cursor, Author>() {
                    @Override
                    public Author call(Cursor cursor) {
                        return Author.create(Db.RibotProfileTable.parseCursor(cursor).name(),
                                Db.RibotProfileTable.parseCursor(cursor).url());
                    }
                });
    }

}
