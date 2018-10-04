package com.xapo.trendinggithub.data.source;

import com.xapo.trendinggithub.data.model.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RepoApi {

    @GET("repositories?language=java&since=weekly")
    Call<List<Repository>> getResults();

}
