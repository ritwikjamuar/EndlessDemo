package demo.ritwik.endless.application

import android.app.Activity
import android.app.Application

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector

import demo.ritwik.endless.di.application.DaggerAppComponent

import javax.inject.Inject

/**
 * Represents [Application] Class of this application.
 *
 * @author Ritwik Jamuar
 */
class App : Application(), HasActivityInjector {

	/**[DispatchingAndroidInjector] of type [Activity] acting as Activity Injector.*/
	@Inject
	lateinit var activityInjector: DispatchingAndroidInjector<Activity>

	/*---------------------------------- Application Callbacks -----------------------------------*/

	override fun onCreate() {
		super.onCreate()
		initialize()
	}

	/*------------------------------- HasActivityInjector Callbacks ------------------------------*/

	override fun activityInjector() : AndroidInjector<Activity> = activityInjector

	/*------------------------------------- Private Methods --------------------------------------*/

	/**Takes care of initialization of this [Application].*/
	private fun initialize() {
		initializeComponent()
	}

	/**Initializes the [dagger.Component] of this [Application].*/
	private fun initializeComponent() = DaggerAppComponent.builder()
		.application(this)
		.build()
		.inject(this)

}
