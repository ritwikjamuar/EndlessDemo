package demo.ritwik.endless.mvvm.repository

import android.content.Context

import com.google.gson.Gson

/**
 * Repository of [demo.ritwik.endless.mvvm.viewModel.MainViewModel].
 *
 * @param context [Context] of Application.
 * @author Ritwik Jamuar
 */
class MainRepository constructor(private val context : Context, private val gson: Gson)
