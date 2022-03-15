package com.dsrise.mynote.di.module

import com.dsrise.mynote.di.annotation.ApplicationScope
import com.dsrise.mynote.di.annotation.FirebaseAnalyticsQualifier
import com.dsrise.mynote.di.services.AnalyticsService
import com.dsrise.mynote.di.services.FirebaseAnalytics
import com.dsrise.mynote.di.services.MixPanelAnalytics
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AnalyticsServiceModule {

    @Singleton
    @FirebaseAnalyticsQualifier
    @Provides
    fun getFirebaseAnalytics(firebaseAnalytics: FirebaseAnalytics): AnalyticsService {
        return firebaseAnalytics
    }

    @Singleton
    @Named("mixpanel")
    @Provides
    fun getMixpanelAnalytics(): AnalyticsService {
        return MixPanelAnalytics()
    }
}