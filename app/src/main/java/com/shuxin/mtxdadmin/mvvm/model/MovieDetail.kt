package com.shuxin.mtxdadmin.mvvm.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Created by Buzz on 2020/4/23.
 * Email :lmx2060918@126.com
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class MovieDetail(
    var code: Int,
    var data: MovieDesc,
    var msg: String
):Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class MovieDesc(
    var group_id: Int,
    var is_collection: Int,
    var is_like: Int,
    var type_id: Int,
    var type_id_1: Int,
    var type_id_1_name: String,
    var vod_actor: String,
    var vod_area: String,
    var vod_author: String,
    var vod_behind: String,
    var vod_blurb: String,
    var vod_class: String,
    var vod_color: String,
    var vod_comment_count: Int,
    var vod_content: String,
    var vod_copyright: Int,
    var vod_director: String,
    var vod_douban_id: Int,
    var vod_douban_score: String,
    var vod_down: Int,
    var vod_down_from: String,
    var vod_down_note: String,
    var vod_down_server: String,
    var vod_down_url: List<VodDownUrl>,
    var vod_duration: String,
    var vod_en: String,
    var vod_hits: Int,
    var vod_hits_day: Int,
    var vod_hits_month: Int,
    var vod_hits_week: Int,
    var vod_id: Int,
    var vod_isend: Int,
    var vod_jumpurl: String,
    var vod_lang: String,
    var vod_letter: String,
    var vod_level: Int,
    var vod_like_count: Int,
    var vod_lock: Int,
    var vod_name: String,
    var vod_pic: String,
    var vod_pic_slide: String,
    var vod_pic_thumb: String,
    var vod_play_from: String,
    var vod_play_note: String,
    var vod_play_server: String,
    var vod_play_url: List<VodPlayUrl>,
    var vod_points: Int,
    var vod_points_down: Int,
    var vod_points_play: Int,
    var vod_pubdate: String,
    var vod_pwd: String,
    var vod_pwd_down: String,
    var vod_pwd_down_url: String,
    var vod_pwd_play: String,
    var vod_pwd_play_url: String,
    var vod_pwd_url: String,
    var vod_rel_art: String,
    var vod_rel_vod: String,
    var vod_remarks: String,
    var vod_reurl: String,
    var vod_score: String,
    var vod_score_all: Int,
    var vod_score_num: Int,
    var vod_serial: String,
    var vod_state: String,
    var vod_status: Int,
    var vod_step_count: Int,
    var vod_sub: String,
    var vod_tag: String,
    var vod_time: Int,
    var vod_time_add: Int,
    var vod_time_hits: Int,
    var vod_time_make: Int,
    var vod_total: Int,
    var vod_tpl: String,
    var vod_tpl_down: String,
    var vod_tpl_play: String,
    var vod_trysee: Int,
    var vod_tv: String,
    var vod_up: Int,
    var vod_version: String,
    var vod_weekday: String,
    var vod_writer: String,
    var vod_year: String
): Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class VodDownUrl(
    var name: String,
    var url: String
): Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class VodPlayUrl(
    var name: String,
    var url: String
): Parcelable