package com.jenyakirmiza.boilerplate.injection.component;

import dagger.Subcomponent;
import com.jenyakirmiza.boilerplate.injection.PerActivity;
import com.jenyakirmiza.boilerplate.injection.module.ActivityModule;
import com.jenyakirmiza.boilerplate.ui.main.MainActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
