package com.xapo.trendinggithub.data.source;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepoService {

    private Retrofit retrofit = null;


    //retrofit setup
    public RepoApi getAPI() {
        String BASE_URL = "https://github-trending-api.now.sh/";

        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(RepoApi.class);
    }
}
