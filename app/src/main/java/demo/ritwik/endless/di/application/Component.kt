package demo.ritwik.endless.di.application

import dagger.BindsInstance
import dagger.Component

import dagger.android.AndroidInjectionModule

import demo.ritwik.endless.application.App

/**
 * Dagger [Component] of this [android.app.Application] to fulfill the dependencies at the Global
 * Level.
 *
 * @author Ritwik Jamuar
 */
@AppScope
@Component(
	modules = [
		AppModule::class,
		ComponentBinder::class,
		AndroidInjectionModule::class
	]
)
interface AppComponent {

	/**
	 * Our own implementation of Dagger Component Builder.
	 *
	 * @author Ritwik Jamuar
	 */
	@Component.Builder
	interface Builder {

		/**
		 * Builds a new instance of [AppComponent].
		 *
		 * @return New Instance of [AppComponent].
		 */
		fun build(): AppComponent

		/**
		 * Binds the instance of [android.app.Application] from the [App].
		 *
		 * @param application Global Instance of [App].
		 * @return Modified Instance of [Builder].
		 */
		@BindsInstance
		fun application(application: App): Builder

	}

	/**
	 * Injects the [App] into this Dagger [Component].
	 *
	 * @param application Global Instance of [App].
	 */
	fun inject(application : App)

}
