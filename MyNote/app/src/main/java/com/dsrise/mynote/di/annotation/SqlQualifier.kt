package com.dsrise.mynote.di.annotation

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier

@Qualifier // to declare this annotation as qualifer
@Documented // to let get documented when java document is generated using jdk tool
@Retention(RetentionPolicy.RUNTIME)
/**
Remark : Three type of retention policy are there
1.) Class : When we used this then this qualifier tag is lost/removed/expires when the class loading
is completed.
2.) Runtime : Survive uptil the application is running.
3.) Source : Survive only in time of compilation after that behaves as normal object, tag is removed
 **/
annotation class SqlQualifier()
