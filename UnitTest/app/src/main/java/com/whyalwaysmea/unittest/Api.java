package com.whyalwaysmea.unittest;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Long
 * on 2016/12/16.
 */

public interface Api {

    @GET("helloworld.txt")
    Call<String> get();
}
