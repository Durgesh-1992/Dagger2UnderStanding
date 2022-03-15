package com.dsrise.mynote.di.module

import com.dsrise.mynote.di.annotation.ActivityScope
import com.dsrise.mynote.di.annotation.ApplicationScope
import com.dsrise.mynote.di.annotation.FirebaseAnalyticsQualifier
import com.dsrise.mynote.di.annotation.SqlQualifier
import com.dsrise.mynote.di.repository.FirebaseRepository
import com.dsrise.mynote.di.repository.NoteRepository
import com.dsrise.mynote.di.repository.SqlRepository
import com.dsrise.mynote.di.services.AnalyticsService
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
/**
 * Remark :
 * 1.) Approach :"class NoteServiceModule(private val retryCount : Int)" here argument pass belongs to this module
 * will not be available through the component as it is assocaited with this module,
 * so whenever we need to create dagger component we need to tell dagger that we need to manually create
 * this module with passing dynamic value in our case "retryCount". If incase you forgot to create this in
 * component creation then it will crash at runtime.
 * 2.) Approach :(Factory) So to compensate or we have another approach that will let you know that to create
 * object of component you need to provide the dynamic value as argument to factory method
 * using Factory
 */
//Approach 1
// class NoteServiceModule(private val retryCount : Int) {

//@Approach 2
class NoteServiceModule() {
    /**
     * Remark : Since dagger can create object of SqlRepository, so we dont need to
     * again define method with @Provide annotation to create object of it, we can directly
     * bind the argument(sql_repository) created by dagger and that get associated by NoteRepository
     * So it means if dagger can create object of particular argument provide, we can directly
     * bind/associate it with return type of method.
     */
//    @Named("sql")
//    @Binds
//    abstract fun bindSQLRepository(sqlRepository: SqlRepository): NoteRepository

    @SqlQualifier
    @Provides
    @ActivityScope
    fun getSQLRepository(sqlRepository: SqlRepository): NoteRepository {
        return sqlRepository
    }

    /**
     * Remark : When dagger has to return object of sometype but is confused since that object can be
     * created in multiple ways but the type(extended class) is different, then in that case we
     * use @Named/@Qualifier Annotation to let dagger know that we need to object of type based @Named Tag
     * or our defined @Qualifier. Means differentiate between object of same type to create different instance
     */
    @Named("firebase")
    @Provides
    @ActivityScope
    fun getFirebaseRepository(retryCount:Int,@FirebaseAnalyticsQualifier analyticsService: AnalyticsService): NoteRepository {
        return FirebaseRepository(retryCount,analyticsService)
    }
}