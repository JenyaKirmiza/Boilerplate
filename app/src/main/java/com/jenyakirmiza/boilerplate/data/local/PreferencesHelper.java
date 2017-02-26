package com.jenyakirmiza.boilerplate.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.jenyakirmiza.boilerplate.injection.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

import static android.provider.ContactsContract.Directory.PACKAGE_NAME;

@Singleton
public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "android_boilerplate_pref_file";
    public static final String FIRST_LOADING = "android_boilerplate_pref_first_loading_init";

    private final SharedPreferences mPref;
    private Context mContext;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        mPref = context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
        this.mContext=context;
    }

    public void clear() {
        mPref.edit().clear().apply();
    }

    public void setFirstLoading()
    {

        SharedPreferences prefs = mContext.getSharedPreferences(
                PACKAGE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(FIRST_LOADING, true);
        editor.commit();

        mPref.edit().putBoolean(FIRST_LOADING,true);
        mPref.edit().commit();
    }

    public boolean getFirstLoading(){
        SharedPreferences prefs = mContext.getSharedPreferences(
                PACKAGE_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(FIRST_LOADING,false);
    }



}
