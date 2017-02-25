package com.jenyakirmiza.boilerplate.ui.main;

import com.jenyakirmiza.boilerplate.data.model.Ribot;
import com.jenyakirmiza.boilerplate.ui.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRibotsEmpty();

    void showError();

}
