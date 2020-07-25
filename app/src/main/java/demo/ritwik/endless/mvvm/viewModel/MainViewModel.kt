package demo.ritwik.endless.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import demo.ritwik.endless.data.Rider

import demo.ritwik.endless.mvvm.repository.MainRepository

/**
 * [ViewModel] of [demo.ritwik.endless.ui.activity.MainActivity].
 *
 * @param repository Repository of [MainViewModel].
 * @author Ritwik Jamuar
 */
class MainViewModel private constructor(private val repository : MainRepository): ViewModel() {

	/*------------------------------------- Mutable LiveData -------------------------------------*/

	private lateinit var _loading : MutableLiveData<Boolean>
	private lateinit var _list : MutableLiveData<List<Rider>>
	private lateinit var _error : MutableLiveData<String>
	private lateinit var _clearList: MutableLiveData<Unit>

	/*------------------------------------ Immutable LiveData ------------------------------------*/

	/**[LiveData] of [Boolean] to notify whether to show Loading or not in the UI.*/
	val loadingLiveData: LiveData<Boolean> get() = _loading

	/**[LiveData] of [List] of [Rider] to populate the values in the UI.*/
	val listLiveData: LiveData<List<Rider>> get() = _list

	/**[LiveData] of [String] to notify error occurred while loading the [Rider]s.*/
	val errorLiveData: LiveData<String> get() = _error

	/**[LiveData] of [Unit] to notify event of clearing the already rendered [List] of [Rider]s
	 * in the UI.*/
	val clearListLiveData: LiveData<Unit> get() = _clearList

	/*------------------------------------ Initializer Block -------------------------------------*/

	init {
		initializeLiveData()
	}

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

	/*-------------------------------------- Public Methods --------------------------------------*/

	/**
	 * Handles the event of UI when the [List] of [Rider] is scrolled.
	 *
	 * @param visibleItemCount [Int] denoting the No. of [Rider] that are visible in the UI.
	 * @param totalItemCount [Int] denoting the total count of [Rider]s.
	 * @param firstVisibleItemPosition [Int] denoting the position of first Visible [Rider].
	 */
	fun onScrolled(visibleItemCount: Int, totalItemCount: Int, firstVisibleItemPosition: Int) {
		if (visibleItemCount + firstVisibleItemPosition >= totalItemCount &&
			firstVisibleItemPosition >= 0) {
			if (!isLoading()!! && !isError()) {
				// TODO: Perform fetch of Riders.
			}
		}
	}

	/**Handles the event of UI when the User asks for refreshing the [List] of [Rider].*/
	fun onRefresh() {
		// TODO: Perform fetch of Riders.
	}

	/*------------------------------------- Private Methods --------------------------------------*/

	/**Initializes all the [MutableLiveData] in this [ViewModel].*/
	private fun initializeLiveData() {
		_loading = MutableLiveData()
		_list = MutableLiveData()
		_error = MutableLiveData()
		_clearList = MutableLiveData()
	}

	/**Shows the Loading in the UI.*/
	private fun showLoading() {
		_loading.value = true
	}

	/**Hides the Loading in the UI.*/
	private fun hideLoading() {
		_loading.value = false
	}

	/**
	 * Determines whether the Loading is being displayed in the UI or not.
	 *
	 * @return true, if the Loading is displayed, else false.
	 */
	private fun isLoading() = if (_loading.value == null) {
		false
	} else {
		_loading.value
	}

	/**
	 * Sends the [List] of [Rider] to UI for displaying.
	 *
	 * @param riders [List] of [Rider] we want to show.
	 */
	private fun populateList(riders: List<Rider>) {
		_list.value = riders
	}

	/**Shows the Error in the UI.*/
	private fun showError(description: String) {
		_error.value = description
	}

	/**Hides the Error in the UI.*/
	private fun hideError() {
		_error.value = ""
	}

	/**
	 * Determines whether the Error is being displayed in the UI or not.
	 *
	 * @return true, if the Error is displayed, else false.
	 */
	private fun isError() = _error.value?.isEmpty() ?: false

	/**Clears the [List] of [Rider] from the UI.*/
	private fun clearList() {
		_clearList.value = Unit
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
