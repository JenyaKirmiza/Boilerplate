package com.jenyakirmiza.boilerplate.test.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import com.jenyakirmiza.boilerplate.injection.component.ApplicationComponent;
import com.jenyakirmiza.boilerplate.test.common.injection.module.ApplicationTestModule;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
