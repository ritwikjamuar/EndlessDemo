package demo.ritwik.endless.mvvm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.RuntimeException

import javax.inject.Inject
import javax.inject.Provider

/**
 * Generic [ViewModel]'s [ViewModelProvider.Factory] which is supported by Dagger Injection.
 *
 * @param creators [Map] of [Class] as [ViewModel] and [Provider] of [ViewModel].
 * @author Ritwik Jamuar
 */
class VMFactory @Inject constructor(
	private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
): ViewModelProvider.Factory {

	override fun <T : ViewModel?> create(modelClass : Class<T>) : T {
		return try {

			/*
			There are two ways of obtaining the provider of the concerned ViewModel from the Map of
			ViewModel and it's Provider:
			a) Provide the Model Class as the Key for the Map of ViewModel and it's Provider.
			b) If the Key does not exist, then set the key of the first item of the Map as the
			   value of isAssignableFrom. The first item in the Map represents the instance of
			   latest concerned ViewModel.
			c) If above 2 didn't worked, then throw an IllegalArgumentException.
			 */

			val creators = creators[modelClass] ?: creators.entries.firstOrNull { map ->
				modelClass.isAssignableFrom(map.key)
			}?.value ?: throw IllegalArgumentException("unknown Model Class :$modelClass")

			// If the provider is obtained, then get the current instance of the ViewModel.
			@Suppress("UNCHECKED_CAST")
			creators.get() as T

		} catch (e: Exception) {
			throw RuntimeException(e)
		}
	}

}
