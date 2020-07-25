package demo.ritwik.endless.ui.viewHolder

import android.view.View

import androidx.recyclerview.widget.RecyclerView

import demo.ritwik.endless.data.Rider

import demo.ritwik.endless.databinding.ItemErrorBinding
import demo.ritwik.endless.databinding.ItemLoadingBinding
import demo.ritwik.endless.databinding.ItemRiderBinding

/**
 * [RecyclerView.ViewHolder] to render a MotoGP Rider.
 *
 * @param binding [androidx.databinding.ViewDataBinding] of this [RecyclerView.ViewHolder].
 * @author Ritwik Jamuar
 */
class RiderVH(private val binding : ItemRiderBinding) : RecyclerView.ViewHolder(binding.root) {

	/*-------------------------------------- Public Methods --------------------------------------*/

	/**
	 * Sets the [Rider] into [ItemRiderBinding].
	 *
	 * @param r Instance of [Rider] which we wish to populate onto this [RecyclerView.ViewHolder].
	 */
	fun setRider(r : Rider) = with(binding) {
		rider = r
	}

}

/**
 * [RecyclerView.ViewHolder] to render the Loading View.
 *
 * @param binding [androidx.databinding.ViewDataBinding] of this [RecyclerView.ViewHolder].
 * @author Ritwik Jamuar
 */
class LoadingVH(private val binding: ItemLoadingBinding): RecyclerView.ViewHolder(binding.root)

/**
 * [RecyclerView.ViewHolder] to render the Error View.
 *
 * @param binding [androidx.databinding.ViewDataBinding] of this [RecyclerView.ViewHolder].
 * @author Ritwik Jamuar
 */
class ErrorVH(private val binding: ItemErrorBinding): RecyclerView.ViewHolder(binding.root) {

	/*-------------------------------------- View Listeners --------------------------------------*/

	/**[View.OnClickListener] that intercepts click on 'Retry' button.*/
	private val retryClickListener = View.OnClickListener {
		// TODO : Propagate the event of Retry.
	}

	/*-------------------------------------- Public Methods --------------------------------------*/

	/**Attaches this [RecyclerView.ViewHolder] in the window.*/
	fun markAttached() = initializeComponents()

	/**Attaches this [RecyclerView.ViewHolder] from the window.*/
	fun markDetached() = cleanUp()

	/**
	 * Sets the Description of Error.
	 *
	 * @param errorDescription [String] describing the Error.
	 */
	fun setDescription(errorDescription: String) = with(binding) {
		description = errorDescription
	}

	/*------------------------------------- Private Methods --------------------------------------*/

	/**Initializes the components in this [RecyclerView.ViewHolder].*/
	private fun initializeComponents() = with(binding) {
		buttonRetry.setOnClickListener(retryClickListener)
	}

	/**De-references any references to avoid a potential Memory Leak in [ErrorVH].*/
	private fun cleanUp() = with(binding) {
		buttonRetry.setOnClickListener(null)
	}

}
