package demo.ritwik.endless.di.application

import android.app.Application

import android.content.Context

import androidx.lifecycle.ViewModel

import com.google.gson.Gson

import dagger.Binds
import dagger.Module
import dagger.Provides

import dagger.android.ContributesAndroidInjector

import dagger.multibindings.IntoMap

import demo.ritwik.endless.application.App

import demo.ritwik.endless.di.activity.MainModule

import demo.ritwik.endless.mvvm.viewModel.MainViewModel
import demo.ritwik.endless.mvvm.viewModel.VMFactory
import demo.ritwik.endless.mvvm.viewModel.ViewModelKey

import demo.ritwik.endless.ui.activity.MainActivity

import javax.inject.Provider

/*

										Module Graph

										AppComponent
					 ________________________|____________________________
					|						 |							  |
				Component				 AppModule				AndroidInjectionModule
				  Binder					 |
		  ___________________________________|_________________________________________
		 |				   |									|					   |
	Application 	  MVVMModule						  UtilityModule				Context
				  	   	   |									|
				     ViewModelModule						GsonModule
				     	   \									|
				       VMFactory							  Gson
 */

@Module(
	includes = [
		MVVMModule::class,
		UtilityModule::class
	]
)
object AppModule {

	@Provides
	@JvmStatic
	fun providesApp(app: App): Application = app

	@Provides
	@JvmStatic
	fun providesContext(app: App): Context = app.applicationContext

}

@Module(
	includes = [
		ViewModelModule::class
	]
)
object MVVMModule

@Module(
	includes = [
		GsonModule::class
	]
)
object UtilityModule

@Module
object ViewModelModule {

	@Provides
	@JvmStatic
	fun provideVMFactory(
		creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
	): VMFactory = VMFactory(creators)

}

@Module
class GsonModule {

	@AppScope
	@Provides
	fun providesGson(): Gson = Gson()

}

/**
 * Dagger [Module] that binds the components to be used by Android Component
 * (mostly [android.app.Activity]) to inject it's Dependencies.
 *
 * In this project, we are using MVVM Design Pattern with [ViewModel] and [android.app.Activity].
 * The motive of Dependency Injection is also to manage creation/fetching instance of [ViewModel]
 * using [VMFactory] to our concerned [android.app.Activity].
 *
 * Thus we make use of our own [dagger.MapKey] to instruct [dagger] to fulfill the dependency of
 * [android.app.Activity], which is it's concerned [ViewModel] using [ContributesAndroidInjector],
 * which provides the concerned Dependency of [ViewModel], which is Repository.
 *
 * Example Use Case :-
 *
 * 										[MainActivity]
 * 										 	  ^
 * 											  |
 * 										[MainViewModel]
 * 										 	  ^
 * 											  |
 * 										 MainRepository   <-------   [MainModule]
 *
 * Here, MainRepository is provided by [MainModule]. So mention [MainModule] as a module Contributor
 * of Dependencies through [ContributesAndroidInjector].
 * Next, we use [Binds] and [IntoMap] in conjunction with [ViewModelKey] to provide a abstract
 * definition of providing the [MainViewModel].
 *
 * Thus, add this method to contribute the [MainActivity] for Android Injection with
 * using [MainModule]:
 *
 * @ContributesAndroidInjector(modules = [MainModule::class])
 * abstract fun contributesMainActivity(): MainActivity
 *
 * And, to provide the [MainViewModel], make use of [Binds], [IntoMap] and [ViewModelKey] to
 * provide the [MainViewModel] as [ViewModel].
 *
 * @Binds
 * @IntoMap
 * @ViewModelKey(MainViewModel::class)
 * abstract fun provideMainVM(viewModel : MainViewModel): ViewModel
 */
@Module
abstract class ComponentBinder {

	/*--------------------------------- Activity Binding Methods ---------------------------------*/

	@ContributesAndroidInjector(modules = [MainModule::class])
	abstract fun contributesMainActivity(): MainActivity

	/*-------------------------------- ViewModel Binding Methods ---------------------------------*/

	@Binds
	@IntoMap
	@ViewModelKey(MainViewModel::class)
	abstract fun provideMainVM(viewModel : MainViewModel): ViewModel

}
