package demo.ritwik.endless.ui.activity

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import androidx.databinding.DataBindingUtil

import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.RecyclerView

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.google.gson.Gson

import demo.ritwik.endless.R

import demo.ritwik.endless.data.Rider

import demo.ritwik.endless.databinding.ActivityMainBinding

import demo.ritwik.endless.mvvm.repository.MainRepository

import demo.ritwik.endless.mvvm.viewModel.MainVMFactory
import demo.ritwik.endless.mvvm.viewModel.MainViewModel

import demo.ritwik.endless.ui.adapter.RiderAdapter

/**
 * [android.app.Activity] to show [List] of MotoGP Riders.
 *
 * @author Ritwik Jamuar
 */
class MainActivity : AppCompatActivity() {

	/*---------------------------------------- Components ----------------------------------------*/

	/**[androidx.databinding.ViewDataBinding] reference for this [android.app.Activity].*/
	private lateinit var binding : ActivityMainBinding

	/**Reference of [MainViewModel].*/
	private lateinit var viewModel : MainViewModel

	/**Adapter of [demo.ritwik.endless.data.Rider].*/
	private lateinit var adapter : RiderAdapter

	/*---------------------------------------- Observers -----------------------------------------*/

	/**[Observer] for show/hide the Loading View using [RiderAdapter].*/
	private val loadingObserver = Observer<Boolean> { show ->
		if (show) {
			adapter.addLoading()
		} else {
			adapter.removeLoading()
		}
	}

	/**[Observer] for populating [List] of [Rider] using [RiderAdapter].*/
	private val listObserver = Observer<List<Rider>> { riders ->
		adapter.addItems(riders)
	}

	/**[Observer] for showing Error View using [RiderAdapter].*/
	private val errorObserver = Observer<String> { description ->
		if (description.isEmpty()) {
			adapter.removeError()
		} else {
			adapter.addError(description)
		}
	}

	/**[Observer] for clearing [List] of [Rider] using [RiderAdapter].*/
	private val clearListObserver = Observer<Unit> {
		adapter.clearItems()
	}

	/*-------------------------------------- View Callbacks --------------------------------------*/

	/**[SwipeRefreshLayout.OnRefreshListener] to intercept 'Refresh' event.*/
	private val refreshListener = SwipeRefreshLayout.OnRefreshListener {
		// TODO : Handle Refresh scenario.
	}

	/**[RecyclerView.OnScrollListener] to intercept scroll.*/
	private val scrollListener = object : RecyclerView.OnScrollListener() {
		override fun onScrollStateChanged(recyclerView : RecyclerView, newState : Int) = Unit
	}

	/*------------------------------------ Activity Callbacks ------------------------------------*/

	override fun onCreate(savedInstanceState : Bundle?) {

		inject()

		super.onCreate(savedInstanceState)

		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

		attachObservers()

		initializeViews()

	}

	override fun onDestroy() {
		super.onDestroy()
		cleanUp()
	}

	/*-------------------------------------- Private Methods -------------------------------------*/


	/**Fulfill the required dependencies of this [android.app.Activity].*/
	private fun inject() {
		val repository = MainRepository(this.applicationContext, Gson())
		viewModel =
			ViewModelProvider(this, MainVMFactory(repository)).get(MainViewModel::class.java)
	}

	/**Initialize the [android.view.View]s under [ActivityMainBinding].*/
	private fun initializeViews() = with(binding) {
		viewSwipe.setOnRefreshListener(refreshListener)
		adapter = RiderAdapter()
		listRiders.adapter = adapter
		listRiders.addOnScrollListener(scrollListener)
	}

	private fun attachObservers() = with(viewModel) {
		val lifecycleOwner = this@MainActivity
		loadingLiveData.observe(lifecycleOwner, loadingObserver)
		listLiveData.observe(lifecycleOwner, listObserver)
		errorLiveData.observe(lifecycleOwner, errorObserver)
		clearListLiveData.observe(lifecycleOwner, clearListObserver)
	}

	/**De-references any references to avoid a potential Memory Leak.*/
	private fun cleanUp() = Unit

}
