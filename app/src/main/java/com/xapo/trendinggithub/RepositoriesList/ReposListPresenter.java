package com.xapo.trendinggithub.RepositoriesList;

import android.support.annotation.NonNull;

import com.xapo.trendinggithub.data.model.Repository;
import com.xapo.trendinggithub.data.source.RepoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoriesListPresenter implements RepositoriesListContract.Presenter {


    private RepositoriesListContract.View view;
    private RepoService repoService;

    public RepositoriesListPresenter(@NonNull RepositoriesListContract.View view) {
        this.view = view;
        if (repoService == null)
            repoService = new RepoService();
    }

    public void getRepos( boolean isNetworkAvailable) {
        if(isNetworkAvailable) {
            view.setRefreshing(true);
            repoService
                    .getAPI()
                    .getResults()
                    .enqueue(new Callback<List<Repository>>() {
                        @Override
                        public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                            List<Repository> result = response.body();
                            if (result == null || result.isEmpty())
                                view.showNoData();
                            else
                                view.showRepos(result);

                            view.setRefreshing(false);

                        }

                        @Override
                        public void onFailure(Call<List<Repository>> call, Throwable t) {
                            if (!view.isActive()) {
                                return;
                            }
                            view.showLoadingError();
                        }
                    });
        }
        else
            view.showLoadingError();

    }
    @Override
    public void loadRepos() {
        getRepos(view.isNetworkAvailable());

    }
}
