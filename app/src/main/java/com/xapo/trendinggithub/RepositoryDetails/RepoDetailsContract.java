package com.xapo.trendinggithub.RepositoryDetails;

import com.xapo.trendinggithub.BasePresenter;
import com.xapo.trendinggithub.BaseView;
import com.xapo.trendinggithub.data.model.Repository;

public class RepoDetailsContract {

    //view
    interface View extends BaseView {

        void showRepos(Repository repository);
        void showLoadingError();

    }

    //presenter
    interface Presenter extends BasePresenter {
        void loadRepository();
    }
}
