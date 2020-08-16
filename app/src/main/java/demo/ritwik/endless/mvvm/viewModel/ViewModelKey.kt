package demo.ritwik.endless.mvvm.viewModel

import androidx.lifecycle.ViewModel

import dagger.MapKey

import kotlin.reflect.KClass

/**
 * Dagger Annotation as the [MapKey] for the [ViewModel].
 *
 * @param value [KClass] as our concerned [ViewModel].
 * @author Ritwik Jamuar
 */
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
