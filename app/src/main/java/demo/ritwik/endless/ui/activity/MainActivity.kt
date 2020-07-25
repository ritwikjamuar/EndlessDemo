package demo.ritwik.endless.ui.activity

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import androidx.databinding.DataBindingUtil

import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.RecyclerView

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import demo.ritwik.endless.R

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
	private lateinit var adapter: RiderAdapter

	/*-------------------------------------- View Callbacks --------------------------------------*/

	/**[SwipeRefreshLayout.OnRefreshListener] to intercept 'Refresh' event.*/
	private val refreshListener  = SwipeRefreshLayout.OnRefreshListener {
		// TODO : Handle Refresh scenario.
	}

	/**[RecyclerView.OnScrollListener] to intercept scroll.*/
	private val scrollListener = object:RecyclerView.OnScrollListener() {
		override fun onScrollStateChanged(recyclerView : RecyclerView, newState : Int) = Unit
		override fun onScrolled(recyclerView : RecyclerView, dx : Int, dy : Int) {
			super.onScrolled(recyclerView, dx, dy)
			// TODO : Handle the Scroll behavior.
		}
	}

	/*------------------------------------ Activity Callbacks ------------------------------------*/

	override fun onCreate(savedInstanceState : Bundle?) {

		inject()

		super.onCreate(savedInstanceState)

		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

		initializeViews()

	}

	override fun onDestroy() {
		super.onDestroy()
		cleanUp()
	}

	/*-------------------------------------- Private Methods -------------------------------------*/


	/**Fulfill the required dependencies of this [android.app.Activity].*/
	private fun inject() {
		viewModel =
			ViewModelProvider(this, MainVMFactory(MainRepository(this.applicationContext)))
				.get(MainViewModel::class.java)
	}

	/**Initialize the [android.view.View]s under [ActivityMainBinding].*/
	private fun initializeViews() = with(binding) {
		viewSwipe.setOnRefreshListener(refreshListener)
		adapter = RiderAdapter()
		listRiders.adapter = adapter
		listRiders.addOnScrollListener(scrollListener)
	}

	/**De-references any references to avoid a potential Memory Leak.*/
	private fun cleanUp() = Unit

}
