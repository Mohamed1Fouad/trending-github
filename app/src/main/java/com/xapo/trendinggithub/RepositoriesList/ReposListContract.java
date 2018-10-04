package com.xapo.trendinggithub.RepositoriesList;

import android.support.annotation.NonNull;

import com.xapo.trendinggithub.BasePresenter;
import com.xapo.trendinggithub.BaseView;
import com.xapo.trendinggithub.data.model.Repository;

import java.util.List;

public class RepositoriesListContract {
    interface View extends BaseView {

        void showRepos(@NonNull List<Repository> repos);

        void setRefreshing(boolean refreshing);

        boolean isNetworkAvailable();

        boolean isActive();

        void showNoData();

        void showLoadingError();

    }

    interface Presenter extends BasePresenter {

        void loadRepos();
    }
}
