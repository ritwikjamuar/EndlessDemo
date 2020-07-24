package demo.ritwik.endless.ui.viewHolder

import android.view.View

import androidx.recyclerview.widget.RecyclerView

import demo.ritwik.endless.databinding.ItemErrorBinding

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
