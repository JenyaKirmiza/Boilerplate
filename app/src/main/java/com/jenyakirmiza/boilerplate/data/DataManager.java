package com.jenyakirmiza.boilerplate.data;

import com.jenyakirmiza.boilerplate.data.local.DatabaseHelper;
import com.jenyakirmiza.boilerplate.data.local.PreferencesHelper;
import com.jenyakirmiza.boilerplate.data.model.Author;
import com.jenyakirmiza.boilerplate.data.remote.RibotsService;
import com.jenyakirmiza.boilerplate.util.DatabaseFileManager;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class DataManager {

    private final RibotsService mRibotsService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final DatabaseFileManager mDatabaseFileManager;

    @Inject
    public DataManager(RibotsService ribotsService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper, DatabaseFileManager databaseFileManager) {
        mRibotsService = ribotsService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
        mDatabaseFileManager=databaseFileManager;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public void syncRibots() {
        mDatabaseHelper.syncRibots();
    }

    public void copyDatabaseFromAssets(){
        mDatabaseFileManager.copyAssets();

       /*return  Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                mDatabaseFileManager.copyAssets();
            }
        });*/

    }


    public Observable<List<Author>> getRibots() {
        return mDatabaseHelper.getRibots().distinct();
    }

}
