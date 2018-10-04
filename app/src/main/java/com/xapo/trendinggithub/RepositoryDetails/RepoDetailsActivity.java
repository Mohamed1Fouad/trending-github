package com.xapo.trendinggithub.RepositoryDetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.xapo.trendinggithub.R;
import com.xapo.trendinggithub.data.model.Repository;

public class RepoDetailsActivity extends AppCompatActivity implements RepoDetailsContract.View {

    private RepoDetailsContract.Presenter presenter;
    Repository repository;
    TextView textViewName, textViewAuthor, textViewUrl, textViewDesc, textViewLang, textViewFork, textViewStars, textViewStarsThisWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() != null) {
            repository = (Repository) getIntent().getSerializableExtra("repository");
        }
        setContentView(R.layout.activity_repo_details);

        initViews();
        initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadRepository();

    }

    // show repo data
    @Override
    public void showRepos(Repository repository) {
        textViewName.setText(repository.getName());
        textViewAuthor.setText(repository.getAuthor());
        textViewDesc.setText(repository.getDescription());
        textViewLang.setText(repository.getLanguage());
        textViewStars.setText(String.valueOf(repository.getStars()));
        textViewStarsThisWeek.setText(String.valueOf(repository.getCurrentPeriodStars()));
        textViewFork.setText(String.valueOf(repository.getForks()));
        textViewUrl.setText(repository.getUrl());

    }


    //show error toast on any loading error
    @Override
    public void showLoadingError() {
        Toast.makeText(this, R.string.error_loading, Toast.LENGTH_SHORT).show();
        finish();
    }

    //init all textviews
    private void initViews() {
        textViewName = findViewById(R.id.textViewName);
        textViewAuthor = findViewById(R.id.textViewAuthor);
        textViewDesc = findViewById(R.id.textViewDesc);
        textViewLang = findViewById(R.id.textViewLang);
        textViewStars = findViewById(R.id.textViewStars);
        textViewStarsThisWeek = findViewById(R.id.textViewStarsThisWeek);
        textViewFork = findViewById(R.id.textViewForks);
        textViewUrl = findViewById(R.id.textViewUrl);

    }

    //init presenter
    private void initPresenter() {
        presenter = new RepoDetailsPresenter(this, repository);
    }
}
