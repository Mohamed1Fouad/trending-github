package com.xapo.trendinggithub.RepositoryDetails;

import android.support.annotation.NonNull;

import com.xapo.trendinggithub.data.model.Repository;

public class RepoDetailsPresenter implements RepoDetailsContract.Presenter {


    private RepoDetailsContract.View view;
 Repository repository;
    public RepoDetailsPresenter(@NonNull RepoDetailsContract.View view,Repository repository) {
        this.view = view;
        this.repository=repository;
    }


    @Override
    public void loadRepository() {
        getRepos();
    }

    //show repository
    public void getRepos() {
        if(repository!=null) {
             view.showRepos(repository);
        }
        else
            view.showLoadingError();

    }
}
