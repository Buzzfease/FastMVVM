package com.shuxin.mtxdadmin.utils.glide

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.MainThread
import androidx.annotation.Nullable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.shuxin.mtxdadmin.MyApplication
import java.io.File
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Buzz on 2020/1/8.
 * Email :lmx2060918@126.com
 */
class GlideCenter {
    companion object {
        private var mInstance: GlideCenter? = null
        @MainThread
        fun get(): GlideCenter {
            if (mInstance == null) {
                mInstance = GlideCenter()
            }
            return mInstance!!
        }
    }

    init {
        MyApplication.appComponent?.inject(this)
    }

    @Inject
    @field:Named("default")
    lateinit var defaultBuilder: RequestBuilder<Drawable>

    @Inject
    @field:Named("withCircle")
    lateinit var circleBuilder: RequestBuilder<Drawable>

    @Inject
    @field:Named("withCrossFade")
    lateinit var crossFadeBuilder: RequestBuilder<Drawable>

    @Inject
    @field:Named("withCircleCrossFade")
    lateinit var circleCrossFadeBuilder: RequestBuilder<Drawable>


    @Inject
    @field:Named("withBitmap")
    lateinit var bitMapBuilder: RequestBuilder<Bitmap>

    fun showImage(imageView: ImageView?, url:Any?){
        if (imageView == null || url == null) return
        val path = handleUrls(url)
        if (path != null){
            defaultBuilder.load(path).into(imageView)
        }
    }

    fun showCircleImage(imageView: ImageView?, url:Any?){
        if (imageView == null || url == null) return
        val path = handleUrls(url)
        if (path != null){
            circleBuilder.load(path).into(imageView)
        }
    }

    fun showCrossFadeImage(imageView: ImageView?, url:Any?){
        if (imageView == null || url == null) return
        val path = handleUrls(url)
        if (path != null){
            crossFadeBuilder.load(path).into(imageView)
        }
    }

    fun showCircleCrossFadeImage(imageView: ImageView?, url:Any?){
        if (imageView == null || url == null) return
        val path = handleUrls(url)
        if (path != null){
            circleCrossFadeBuilder.load(path).into(imageView)
        }
    }


    fun showImageWithCallback(imageView: ImageView?, url:Any?, @Nullable listener: ILoadingProgress?){
        if (imageView == null || url == null) return
        val path = handleUrls(url)
        if (path != null){
            if (listener != null){
                listener.onStarted()
                defaultBuilder.load(path)
                    .into(object: CustomViewTarget<ImageView, Drawable>(imageView){
                        override fun onLoadFailed(errorDrawable: Drawable?) {
                            listener.onFailed()
                            imageView.setImageDrawable(errorDrawable)
                        }

                        override fun onResourceCleared(placeholder: Drawable?) {
                            /**
                             * Glide会随着Activity的stop会停止执行图片加载，在重新回到Activity的时候，会继续加载之前的请求。
                            新图片还未请求到，而ImageView中的旧图片已经被回收，ImageView绘制过程中崩溃。所以这里必须设置上placeHolder
                             */
                            imageView.setImageDrawable(placeholder)
                        }

                        override fun onResourceReady(drawable: Drawable, transition: Transition<in Drawable>?) {
                            listener.onFinish()
                            imageView.setImageDrawable(drawable)
                        }
                    })
            }else{
                defaultBuilder.load(path).into(imageView)
            }
        }
    }


    private fun handleUrls(url:Any):Any?{
        if (url is String || url is Int || url is File){
            if (url is String) {
                if (url.endsWith("null")) {
                    return null
                }
//                if (!url.startsWith("http")) {
//                    Timber.e("loadPic" + Constant.getInterFaceUrl().plus("/") + url)
//                    return Constant.getInterFaceUrl()/*.plus("/")*/ + url
//                }
            }
        }
        return url
    }


    //获取大图需要的宽高
    fun getInitImageScale(activity: Activity, bitmap: Bitmap): Float {
        val wm = activity.windowManager
        val width = wm.defaultDisplay.width
        val height = wm.defaultDisplay.height
        // 拿到图片的宽和高
        val dw = bitmap.width
        val dh = bitmap.height
        var scale = 1.0f
        //图片宽度大于屏幕，但高度小于屏幕，则缩小图片至填满屏幕宽
        if (dw > width && dh <= height) {
            scale = width * 1.0f / dw
        }
        //图片宽度小于屏幕，但高度大于屏幕，则放大图片至填满屏幕宽
        if (dw <= width && dh > height) {
            scale = width * 1.0f / dw
        }
        //图片高度和宽度都小于屏幕，则放大图片至填满屏幕宽
        if (dw < width && dh < height) {
            scale = width * 1.0f / dw
        }
        //图片高度和宽度都大于屏幕，则缩小图片至填满屏幕宽
        if (dw > width && dh > height) {
            scale = width * 1.0f / dw
        }
        return scale
    }

    interface ILoadingProgress{
        fun onStarted()
        fun onFinish()
        fun onFailed()
    }
}