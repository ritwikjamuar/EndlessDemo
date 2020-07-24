package demo.ritwik.endless.mvvm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import demo.ritwik.endless.mvvm.repository.MainRepository

/**
 * [ViewModel] of [demo.ritwik.endless.ui.activity.MainActivity].
 *
 * @param repository Repository of [MainViewModel].
 * @author Ritwik Jamuar
 */
class MainViewModel private constructor(private val repository : MainRepository): ViewModel() {

	/*------------------------------------- Companion Object -------------------------------------*/

	companion object {

		/**
		 * Provides a new instance of [MainViewModel].
		 *
		 * @param repository Repository of this [ViewModel].
		 * @return New Instance of [MainViewModel].
		 */
		fun create(repository : MainRepository) = MainViewModel(repository)

	}

}

/**
 * [ViewModelProvider] of [MainViewModel].
 *
 * @param repository Repository of [MainViewModel].
 * @author Ritwik Jamuar
 */
class MainVMFactory(private val repository : MainRepository) :
	ViewModelProvider.NewInstanceFactory() {

	/*------------------------------- NewInstanceFactory Callbacks -------------------------------*/

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass : Class<T>) : T =
		MainViewModel.create(repository) as T

}
