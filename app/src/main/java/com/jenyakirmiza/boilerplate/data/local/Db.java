package com.jenyakirmiza.boilerplate.data.local;

import android.content.ContentValues;
import android.database.Cursor;

import com.jenyakirmiza.boilerplate.data.model.Author;

public class Db {

    public Db() { }

    public abstract static class RibotProfileTable {
        public static final String TABLE_NAME = "AUTHOR";
        public static final String COLUMN_NAME = "NAME";
        public static final String COLUMN_URL = "URL";
        public static final String COLUMN_IMAGE_URL = "image_url";
        public static final String COLUMN_AUTHOR_DESCRIPTION = "author_description";

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_NAME + " TEXT TEXT NOT NULL, " +
                        COLUMN_URL + " TEXT NOT NULL "+
                " ); ";

        public static ContentValues toContentValues(Author author) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, author.name());
            values.put(COLUMN_URL, author.url());
            //values.put(COLUMN_IMAGE_URL, author.imageUrl());
            //values.put(COLUMN_AUTHOR_DESCRIPTION, author.authorDescription());
            return values;
        }

        public static Author parseCursor(Cursor cursor) {
            Author author = Author.create(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_URL))
                    //cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE_URL)),
                     /*cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AUTHOR_DESCRIPTION)*/);

            return author;
        }
    }
}
