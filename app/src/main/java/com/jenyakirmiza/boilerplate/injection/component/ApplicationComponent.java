package com.jenyakirmiza.boilerplate.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import com.jenyakirmiza.boilerplate.data.DataManager;
import com.jenyakirmiza.boilerplate.data.SyncService;
import com.jenyakirmiza.boilerplate.data.local.DatabaseHelper;
import com.jenyakirmiza.boilerplate.data.local.PreferencesHelper;
import com.jenyakirmiza.boilerplate.data.remote.RibotsService;
import com.jenyakirmiza.boilerplate.injection.ApplicationContext;
import com.jenyakirmiza.boilerplate.injection.module.ApplicationModule;
import com.jenyakirmiza.boilerplate.util.RxEventBus;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext Context context();
    Application application();
    RibotsService ribotsService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    RxEventBus eventBus();

}
