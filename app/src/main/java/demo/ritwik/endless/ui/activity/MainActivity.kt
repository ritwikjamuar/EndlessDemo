package demo.ritwik.endless.ui.activity

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import androidx.databinding.DataBindingUtil

import androidx.lifecycle.ViewModelProvider

import demo.ritwik.endless.R

import demo.ritwik.endless.databinding.ActivityMainBinding

import demo.ritwik.endless.mvvm.repository.MainRepository

import demo.ritwik.endless.mvvm.viewModel.MainVMFactory
import demo.ritwik.endless.mvvm.viewModel.MainViewModel

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
	private fun initializeViews() = Unit

	/**De-references any references to avoid a potential Memory Leak.*/
	private fun cleanUp() = Unit

}
