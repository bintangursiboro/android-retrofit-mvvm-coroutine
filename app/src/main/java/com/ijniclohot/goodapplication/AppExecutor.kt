package com.ijniclohot.goodapplication

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

class AppExecutor {
    companion object {
        private var instance: AppExecutor? = null
        fun getInstance(): AppExecutor {
            if (instance == null) {
                instance = AppExecutor()
            }
            return instance!!
        }
    }

    private val mNetworkIo: ScheduledExecutorService = Executors.newScheduledThreadPool(3)

    fun networkIo(): ScheduledExecutorService {
        return mNetworkIo
    }
}