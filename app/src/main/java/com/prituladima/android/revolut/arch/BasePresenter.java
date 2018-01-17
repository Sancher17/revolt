package com.prituladima.android.revolut.arch;

import io.michaelrocks.paranoid.Obfuscate;

@Obfuscate
public class BasePresenter<T extends IView> implements IPresenter<T> {

    private T mMvpView;

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    private boolean isViewAttached() {
        return mMvpView != null;
    }

    protected T getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    private static class MvpViewNotAttachedException extends RuntimeException {
        private MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
