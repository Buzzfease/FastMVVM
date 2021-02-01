package com.shuxin.mtxdadmin.di

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.shuxin.mtxdadmin.MyApplication
import com.shuxin.mtxdadmin.R
import com.shuxin.mtxdadmin.utils.glide.GlideCircleTransform
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by Buzz on 2020/1/8.
 * Email :lmx2060918@126.com
 */
@Module
class GlideModule {

    @Provides
    @Named("basic")
    fun provideRequestOptions(): RequestOptions{
        return RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
    }

    @Provides
    @Named("placeHolder")
    fun providePlaceHolder(@Named("basic") options: RequestOptions): RequestOptions{
        return options.placeholder(R.drawable.shape_placeholder_rectangle)
    }

    @Provides
    @Named("errorRes")
    fun provideErrorRes(@Named("placeHolder") options: RequestOptions): RequestOptions{
        return options.error(R.drawable.shape_placeholder_rectangle)
    }

    @Provides
    @Named("circlePlaceHolder")
    fun provideAvatarPlaceHolder(@Named("basic") options: RequestOptions): RequestOptions{
        return options.placeholder(R.drawable.shape_placeholder_circle)
    }

    @Provides
    @Named("circleErrorRes")
    fun provideAvatarErrorRes(@Named("circlePlaceHolder") options: RequestOptions): RequestOptions{
        return options.error(R.drawable.shape_placeholder_circle)
    }

    @Provides
    @Named("default")
    fun provideRequestBuilder(@Named("basic") options: RequestOptions): RequestBuilder<Drawable>{
        return Glide.with(MyApplication.instance())
            .asDrawable()
            .apply(options)
    }

    @Provides
    @Named("withCrossFade")
    fun provideCrossFade(@Named("errorRes") options: RequestOptions): RequestBuilder<Drawable>{
        return Glide.with(MyApplication.instance())
            .asDrawable()
            .apply(options)
            .transition(DrawableTransitionOptions.withCrossFade())
    }

    @Provides
    @Named("withCircle")
    fun provideCircle(@Named("circleErrorRes") options: RequestOptions): RequestBuilder<Drawable>{
        return Glide.with(MyApplication.instance())
            .asDrawable()
            .apply(options)
            .apply(RequestOptions.bitmapTransform(GlideCircleTransform()))
    }

    @Provides
    @Named("withCircleCrossFade")
    fun provideCircleCrossFade(@Named("circleErrorRes") options: RequestOptions): RequestBuilder<Drawable>{
        return Glide.with(MyApplication.instance())
            .asDrawable()
            .apply(options)
            .apply(RequestOptions.bitmapTransform(GlideCircleTransform()))
            .transition(DrawableTransitionOptions.withCrossFade())
    }

    @Provides
    @Named("withBitmap")
    fun provideBitmap(@Named("errorRes") options: RequestOptions): RequestBuilder<Bitmap>{
        return Glide.with(MyApplication.instance())
            .asBitmap()
            .apply(options)
    }

}