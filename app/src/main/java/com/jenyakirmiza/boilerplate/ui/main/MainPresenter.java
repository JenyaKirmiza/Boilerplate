package com.jenyakirmiza.boilerplate.ui.main;

import com.jenyakirmiza.boilerplate.data.DataManager;
import com.jenyakirmiza.boilerplate.data.model.Author;
import com.jenyakirmiza.boilerplate.injection.ConfigPersistent;
import com.jenyakirmiza.boilerplate.ui.base.BasePresenter;
import com.jenyakirmiza.boilerplate.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public MainPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadRibots() {
        checkViewAttached();

        if(!mDataManager.getPreferencesHelper().getFirstLoading()) {
            mDataManager.syncRibots();
            mDataManager.copyDatabaseFromAssets();
            mDataManager.getPreferencesHelper().setFirstLoading();
            observeDb();
        }
        else {
            observeDb();
        }

    }

    private void observeDb(){
        RxUtil.unsubscribe(mSubscription);

        mSubscription = mDataManager.getAuthors()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Author>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the ribots.");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<Author> authors) {
                        if (authors.isEmpty()) {
                            getMvpView().showRibotsEmpty();
                        } else {
                            getMvpView().showAuthors(authors);
                        }
                    }
                });
    }

    public boolean isInitialized() {
        if(!mDataManager.getPreferencesHelper().getFirstLoading()){
            mDataManager.getPreferencesHelper().setFirstLoading();
            return false;
        }

        return true;
    }
}
