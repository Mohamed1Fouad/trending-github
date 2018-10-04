package com.xapo.trendinggithub.data.source;

import retrofit2.Retrofit;

public class RetrofitService {

    private Retrofit retrofit = null;



    public CountryAPI getAPI() {
        String BASE_URL = "http://services.groupkt.com/";

        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(CountryAPI.class);
    }
}
