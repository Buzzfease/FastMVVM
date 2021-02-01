package com.shuxin.mtxdadmin.mvvm.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Created by Buzz on 2020/4/15.
 * Email :lmx2060918@126.com
 */

@Parcelize
@JsonClass(generateAdapter = true)
data class Recommend(
    var code: Int,
    var data: RecommendPage,
    var msg: String
):Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class RecommendPage(
    var limit: Int,
    var list: List<RecommendType>,
    var page: String,
    var total: Int
):Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class RecommendType(
    var color: String,
    var count: Int,
    var create_time: Int,
    var id: Int,
    var list: List<Movie>,
    var module: Int,
    var name: String,
    var ord: Int,
    var total_count: Int,
    var type: Int
):Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Movie(
    var ad_url: String,
    var create_time: Int,
    var id: Int,
    var is_ad: Int,
    var ord: Int,
    var type: Int,
    var vod_desc: String,
    var vod_id: Int,
    var vod_name: String,
    var vod_pic: String,
    var vod_pic_direction: Int,
    var vod_remarks: String,
    var vod_score: Int,
    var vod_status: Int
):Parcelable