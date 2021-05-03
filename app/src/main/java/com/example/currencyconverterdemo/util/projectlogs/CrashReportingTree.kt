package com.example.currencyconverterdemo.util.projectlogs

import android.util.Log
import timber.log.Timber

/**
 * Created by Darshan Patel
 * Usage: to report crashes of crashalytics also firebase crashses can integrated
 */
class CrashReportingTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }

        val t = throwable ?: Exception(message)

       /* // Crashlytics
        Crashlytics.setInt(Constants.CRASHLYTICS_KEY_PRIORITY, priority)
        Crashlytics.setString(Constants.CRASHLYTICS_KEY_TAG, tag)
        Crashlytics.setString(Constants.CRASHLYTICS_KEY_MESSAGE, message)
        Crashlytics.logException(t)*/
    }
}