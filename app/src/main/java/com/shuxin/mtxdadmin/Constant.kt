package com.shuxin.mtxdadmin

object Constant {
    private const val URL_PRODUCTION :String = "http://api.mantianxingdou.com/"
    private const val URL_TEST :String = "http://cs.mantianxingdou.com"

    private const val URL_SHOP_PRODUCTION = "http://apishop.mantianxingdou.com/"
    private const val URL_SHOP_TEST = "http://csshop.mantianxingdou.com/"

    const val BASE_URL = "http://fanq.wy2sf.com/"

    /**
     * 根据环境获取接口请求地址
     * envType 1生产2测试
     */
    fun getInterFaceUrl(): String {
        when (BuildConfig.ENV_TYPE) {
            1 -> return URL_PRODUCTION
            2 -> return URL_TEST
        }
        return ""
    }


    /**
     * 根据环境获取商城接口请求地址
     * envType 1生产2测试
     */
    fun getShopInterfaceUrl():String{
        when (BuildConfig.ENV_TYPE) {
            1 -> return URL_SHOP_PRODUCTION
            2 -> return URL_SHOP_TEST
        }
        return ""
    }

    /**
     * 根据环境获取是否显示log
     * envType 1生产2测试
     */
    fun getIsShowLog(): Boolean {
        when (BuildConfig.ENV_TYPE) {
            1 -> return true
            2 -> return true
        }
        return false
    }
}