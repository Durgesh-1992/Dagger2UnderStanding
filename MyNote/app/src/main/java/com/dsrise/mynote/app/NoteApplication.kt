package com.dsrise.mynote.app

import android.app.Application
import com.dsrise.mynote.di.component.AppComponent
import com.dsrise.mynote.di.component.DaggerAppComponent
//import com.dsrise.mynote.di.component.DaggerNoteKeepingComponent
import com.dsrise.mynote.di.component.NoteKeepingComponent

class NoteApplication: Application() {

    /**
     * Remark : We have initialized the NoteKeepingComponent at application because
     * we need to have all the object annotated with @Singleton inside component as single
     * instance.
     * If we initialize our component at activity level then object annotated with @Singleton will
     * have single instance only during that activity life cycle. So if activity is recreated(after rotation) then
     * new instance of same object will be created and used since our @Singleton scope is associated
     * component which getting initialized each time activity is created.
     */
//    lateinit var noteKeepingComponent: NoteKeepingComponent
    /**
     * Remark : This is application level component and NoteKeepingComponent is
     * activity level
     */
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
//        noteKeepingComponent = DaggerNoteKeepingComponent.factory().create(3)
        appComponent = DaggerAppComponent.builder().build()
    }
}