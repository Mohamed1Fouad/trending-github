package com.xapo.trendinggithub.RepositoriesList;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.xapo.trendinggithub.R;
import com.xapo.trendinggithub.RepositoryDetails.RepoDetailsActivity;
import com.xapo.trendinggithub.data.model.Repository;

import java.util.ArrayList;
import java.util.List;

public class ReposListActivity extends AppCompatActivity implements ReposListContract.View {

    private ReposListContract.Presenter presenter;
    private ReposViewAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        initSwipeRefresh();
        initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadRepos();

    }

    //show repositories list
    @Override
    public void showRepos(@NonNull List<Repository> repos) {
        if (adapter!=null){
            adapter.addRepos(repos);
        }
    }

    //check if network available
    @Override
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    //on refresh
    @Override
    public void setRefreshing(final boolean refreshing) {
        if (swipeRefreshLayout == null) {
            return;
        }
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(refreshing);
            }
        });
    }


    @Override
    public boolean isActive() {
        return true;
    }

    //show error toast when no data
    @Override
    public void showNoData() {
        setRefreshing(false);
        Toast.makeText(this, R.string.empty_data, Toast.LENGTH_SHORT).show();
    }

    //show error toast on any loading error
    @Override
    public void showLoadingError() {
        setRefreshing(false);
        Toast.makeText(this, R.string.error_loading, Toast.LENGTH_SHORT).show();
    }

    //create RecyclerView
    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        recyclerView.setItemAnimator(itemAnimator);
        adapter = new ReposViewAdapter(new ReposViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Repository repository) {
                startActivity(new Intent(ReposListActivity.this, RepoDetailsActivity.class).putExtra("repository",repository));
             }
        }, new ArrayList<Repository>());
        recyclerView.setAdapter(adapter);
    }



    //create SwipeRefresh layout
    private void initSwipeRefresh() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_to_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadRepos();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    //init presenter
    private void initPresenter() {
        presenter = new ReposListPresenter(this);
    }
}
