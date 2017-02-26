package com.jenyakirmiza.boilerplate.data.local;

import android.content.ContentValues;
import android.database.Cursor;

import com.jenyakirmiza.boilerplate.data.model.Author;
import com.jenyakirmiza.boilerplate.data.model.Book;
import com.jenyakirmiza.boilerplate.data.model.Chapter;

public class Db {

    public Db() { }

    public abstract static class RibotProfileTable {
        public static final String TABLE_NAME_AUTHOR = "AUTHOR";
        public static final String TABLE_NAME_BOOK = "BOOK";
        public static final String TABLE_NAME_CHAPTER = "CHAPTER";

        public static final String COLUMN_NAME = "NAME";
        public static final String COLUMN_TITLE = "TITLE";
        public static final String COLUMN_CONTENT = "CONTENT";

        public static final String COLUMN_URL = "URL";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_AUTHOR_ID = "AUTHOR_ID";
        public static final String COLUMN_BOOK_ID = "BOOK_ID";

        public static final String COLUMN_IMAGE_URL = "image_url";
        public static final String COLUMN_AUTHOR_DESCRIPTION = "author_description";

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME_AUTHOR + " (" +
                        COLUMN_NAME + " TEXT TEXT NOT NULL, " +
                        COLUMN_URL + " TEXT NOT NULL "+
                " ); ";

        public static ContentValues toContentValues(Author author) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, author.name());
            //values.put(COLUMN_URL, author.url());
            //values.put(COLUMN_IMAGE_URL, author.imageUrl());
            //values.put(COLUMN_AUTHOR_DESCRIPTION, author.authorDescription());
            return values;
        }

        public static Author parseCursor(Cursor cursor) {
            Author author = Author.create(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
            return author;
        }

        public static Book parseBookCursor(Cursor cursor) {
            Book book = Book.create(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)),cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
            return book;
        }

        public static Chapter parseChapterCursor(Cursor cursor) {
            Chapter chapter = Chapter.create(
            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)), cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT)),cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
            return chapter;
        }
    }
}
