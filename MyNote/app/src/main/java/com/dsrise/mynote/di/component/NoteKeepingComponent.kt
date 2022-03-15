package com.dsrise.mynote.di.component

import com.dsrise.mynote.MainActivity
import com.dsrise.mynote.di.annotation.ActivityScope
import com.dsrise.mynote.di.annotation.ApplicationScope
import com.dsrise.mynote.di.module.AnalyticsServiceModule
import com.dsrise.mynote.di.module.NoteServiceModule
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [NoteServiceModule::class])
interface NoteKeepingComponent {

    /**
     * Approach 1
     * Remark : For Dagger to know what all object are required by the consumer,
     * dagger need to produce/create instance using constructor and provide it to the consumer(in
     * our case it may be activity/fragment etc. So for that we need to define method to get
     * object of that type to be injected by the dagger.
     * But the problem with this approach is that if we require 100 more dependencies for a particular
     * class(Activity/fragment) then in that case we need to define 100 methods for reteriving those object
     * that is not correct approach. #Check next approach(Approach : 2)
     */
//    fun getNoteSavingService(): NoteSavingService
//    fun getNoteRepository(): NoteRepository

    /**
     * Approach 2
     * Remark : In this approach we define a method and add object/type argument of that class(consumer)
     * which need to consume/use the dependency
     *
     */
    fun inject(mainActivity: MainActivity)

    /**
     * Remark : For creating the object of this component(NoteKeepingComponent) we tell
     * dagger to use Factory , with which we can pass dynamic value that will be associated with
     * this component instance and will be available through this component
     * 2.) When we have some classes that are associated with application class component then we need
     * to pass object from application component to activity level component so that activity level
     * can be created and these are activity level dependencies on application level component(AppComponent)
     * (AnalyticsService is application level dependencies but activity level component(Activity#NoteKeepingCompinet)
     * depend on it to build its component so it is pass as dependencies
     */
//    @Component.Factory
//    interface Factory {
//        fun create(@BindsInstance retryCount: Int, appComponent: AppComponent): NoteKeepingComponent
//    }

    /**
     * Remark : Since declaring a component as SubComponent will automatically have access to all
     * dependencies declare at application level we dont need to explicitly pass them as argument and
     * add application level component(#AppComponent) as dependencies array
     * to build subcomponent @Subcomponent(
    dependencies = [AppComponent::class],
    modules = [NoteServiceModule::class]
    )
     */
//    @Subcomponent.Factory
//    interface Factory {
//        fun create(@BindsInstance retryCount: Int): NoteKeepingComponent
//    }

    /**
     * Remark : Similar to Factory Pattern we have builder pattern for creating object/instance
     * subcomponent(#NoteKeepingComponet)/component(#AppComponet)
     * Three Steps to create Build pattern
     * a.) Annotate the interface with @Component.Builder/@SubComponent.Builder
     * b.) Define Build method that return Component/SubComponent(#NoteKeepingComponent)
     * c.) Define method for all the argument with return type as Builder
     */
    @Subcomponent.Builder
    interface Builder {
        fun build(): NoteKeepingComponent
        fun retryCount(@BindsInstance retryCount: Int): Builder
    }
}