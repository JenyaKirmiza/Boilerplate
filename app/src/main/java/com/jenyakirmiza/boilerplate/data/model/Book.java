package com.jenyakirmiza.boilerplate.data.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;




@AutoValue
public abstract class Book implements Parcelable {
    public abstract String title();

    public abstract int id();

    public static Book create(String title, int id) {
        return new AutoValue_Book(title,id);
    }

    public static TypeAdapter<Book> typeAdapter(Gson gson) {
        return new AutoValue_Book.GsonTypeAdapter(gson);
    }

}
