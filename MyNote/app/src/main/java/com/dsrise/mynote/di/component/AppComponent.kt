package com.dsrise.mynote.di.component

import com.dsrise.mynote.di.annotation.FirebaseAnalyticsQualifier
import com.dsrise.mynote.di.module.AnalyticsServiceModule
import com.dsrise.mynote.di.services.AnalyticsService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AnalyticsServiceModule::class])
interface AppComponent {

    /**
     * Remark : For component declare at activity to use component dependencies declare
     * at application level we had to explicitly declare/expose the method(like AnalyticsService) that will provide the
     * object needed for component at activity level to build. But if we have other component defined at
     * application level then for those also we have to declare method that expose the way to create
     * object for those dependencies defined at application.
     * Note : These declaration can be removed using @SubComponent Annotation as SubComponent make sure
     * to have access to all dependencies declare at application level with exposing method.
     */
//    fun getAnalyticsService():  AnalyticsService

    /**
     * Remark : Since subcomponent declare is having factory for its object creation, so we need to use
     * its factory rather using directly creating object
     *
     * Using Factory Pattern for NoteKeepingComponet Object Creation
     */
//    fun getNoteKeepingComponentFactory(): NoteKeepingComponent.Factory

    /**
     * Remark : Using Builder Pattern for NoteKeepingComponent Object Creation
     */
    fun getNoteKeepingComponentBuilder(): NoteKeepingComponent.Builder
}