package com.dsrise.mynote.di.services

import android.util.Log
import com.dsrise.mynote.data.model.util.Constant.MainApp.LOGGER_TAG
import javax.inject.Inject

interface AnalyticsService {
    fun trackEvent(eventName: String, eventType: String)
}

class FirebaseAnalytics @Inject constructor() : AnalyticsService {
    override fun trackEvent(eventName: String, eventType: String) {
        Log.d(
            LOGGER_TAG,
            "FirebaseAnalytics : { trackEvent <> eventName : $eventName - eventType : $eventType }"
        )
    }

}

class MixPanelAnalytics : AnalyticsService {
    override fun trackEvent(eventName: String, eventType: String) {
        Log.d(
            LOGGER_TAG,
            "MixPanelAnalytics : { trackEvent <> eventName : $eventName - eventType : $eventType }"
        )
    }

}