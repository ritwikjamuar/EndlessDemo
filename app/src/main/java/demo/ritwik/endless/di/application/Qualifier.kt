package demo.ritwik.endless.di.application

import javax.inject.Qualifier

/**
 * [Qualifier] to differentiate between the [android.content.Context].
 *
 * @author Ritwik Jamuar
 */
@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext
