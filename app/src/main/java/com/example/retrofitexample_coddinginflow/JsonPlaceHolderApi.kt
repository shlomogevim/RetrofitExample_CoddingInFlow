package com.example.retrofitexample_coddinginflow

import retrofit2.Call
import retrofit2.http.*

interface JsonPlaceHolderApi {


    @GET("posts") //          /postsa
    fun getPosts1():Call<List<Post>>

    @GET("posts/{userId}") //          /posts/1
    fun getPosts2(@Path("userId") userId:Int):Call<Post>


    @GET("posts")                   // /post?userId=1
    fun getPosts3(@Query("userId") userId: Int): Call<List<Post>>

    @GET("posts")                   // /post?userId=1&7
    fun getPosts30(@Query("userId") userId1: Int,
                   @Query("userId") userId2: Int): Call<List<Post>>

    @GET("posts")
    fun getPosts4(
        @Query("userId") userId: Int?=null,
        @Query("_sort") sort: String? = null,
        @Query("_order") order: String? = null
    ): Call<List<Post>>? = null

    @GET("posts")
    fun getPosts5(
        @Query("userId") userId: List<Int>,
        @Query("_sort") sort: String? = null,
        @Query("_order") order: String? = null
    ): Call<List<Post>>? = null


    @GET("posts")
    fun getPosts6(@QueryMap parameters: Map<String, String>
    ): Call<List<Post>>? = null

    @Headers("Static-Header1: 123")
    @GET("posts")
    fun getPosts60(
        @Header("Dynamic-Header") hrader :String,
        @QueryMap parameters: Map<String, String>
    ): Call<List<Post>>? = null


    @Headers("Static-Header1: 123")
    @GET("posts")
    fun getPosts61(
        @HeaderMap  header :Map<String,String>,
        @QueryMap parameters: Map<String, String>
    ): Call<List<Post>>? = null

    @GET("posts")
    fun getPosts62(
        @HeaderMap  header: Map<String,String>,
        @QueryMap parameters: Map<String, String>
    ): Call<List<Post>>? = null

    @GET
    fun getPosts7(@Url url:String):Call<Post>


    @POST("posts")
    fun creatPost1(@Body post: Post
    ): Call<Post>? = null


    @Headers("Static-Header1: 123")
    @POST("posts")
    fun creatPost10(
        @Header("Dynamic-Header") hrader :String,
        @Body post: Post
    ): Call<Post>? = null

    @FormUrlEncoded                 //Just another way to post
    @POST("posts")
    fun creatPost2(
        @Field ("userId") userId:Int,
        @Field("title") title:String,
        @Field("body") text:String
    ): Call<Post>? = null

    @FormUrlEncoded                 //Just another way to post
    @POST("posts")
    fun creatPost3(
        @FieldMap filds:Map<String,String>
    ): Call<Post>? = null
}

