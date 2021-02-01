package com.shuxin.mtxdadmin.utils.network

import com.shuxin.mtxdadmin.mvvm.model.*
import retrofit2.http.*

/**
 * Created by Buzz on 2019/4/23.
 * Email :lmx2060918@126.com
 */
interface ResApi {
    //首页导航
    @GET("vod/column")
    suspend fun getNavigationColumn(): Navigation


    //分类推荐
    @GET("vod/conf")
    suspend fun getRecommendList(@Query("module") module:String,
                                 @Query("page") page:String,
                                 @Query("limit") limit:String) : Recommend


    //根据id获取电影详情 http://fanq.wy2sf.com/vod/vodDetail?uid=6954&vod_id=34102
    @GET("vod/vodDetail")
    suspend fun getMovieDetail(@Query("uid") uid:String,
                               @Query("vod_id") vodId:String): MovieDetail
}