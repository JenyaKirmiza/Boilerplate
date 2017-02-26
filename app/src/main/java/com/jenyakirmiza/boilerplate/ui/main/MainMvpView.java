package com.jenyakirmiza.boilerplate.ui.main;

import com.jenyakirmiza.boilerplate.data.model.Author;
import com.jenyakirmiza.boilerplate.ui.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView {

    void showAuthors(List<Author> ribots);

    void showRibotsEmpty();

    void showError();

}
