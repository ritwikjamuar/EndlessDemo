package demo.ritwik.endless.di.activity

import android.content.Context

import com.google.gson.Gson

import dagger.Module
import dagger.Provides

import demo.ritwik.endless.mvvm.repository.MainRepository

@Module
class MainModule {

	@Provides
	fun providesRepository(
		context : Context,
		gson: Gson
	) = MainRepository(context, gson)

}
