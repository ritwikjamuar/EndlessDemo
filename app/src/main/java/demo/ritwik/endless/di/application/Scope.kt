package demo.ritwik.endless.di.application

import javax.inject.Scope

/**
 * [Scope] for [dagger] to mark an instance as Application Scoped.
 *
 * @author Ritwik Jamuar
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope
