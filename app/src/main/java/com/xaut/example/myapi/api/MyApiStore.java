package com.xaut.example.myapi.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by pc on 2016/12/15.
 */

public interface MyApiStore {

    String API_SERVER_URL = "http://118.244.215.198/";
    //加载首页

    @GET("http://118.244.215.198/?r=index/index.html")
    Call<ResponseBody> loadTheHome();

    //    @GET("http://118.244.215.198/?r=index/index&menuNum=&pn=page/index.html")
    //    Call<ResponseBody> loadTheHome(@Query("page") int page);
    @GET("http://118.244.215.198/?r=index/index&menuNum=&")
    Call<ResponseBody> loadTheHome(@Query("pn") int page);

    @GET("http://118.244.215.198/?r=index/index&")
    Call<ResponseBody> loadCloth(@Query("menuNum") int num);

    @GET("http://118.244.215.198/?r=index/index&")
    Call<ResponseBody> loadCloth(@Query("menuNum") int num,@Query("pn") int page);

    @GET("http://118.244.215.198/?r=index/index&menuNum=2")
    Call<ResponseBody> loadInfant();

    @GET("http://118.244.215.198/?r=index/index&menuNum=2&")
    Call<ResponseBody> loadInfant(@Query("pn") int page);


}
