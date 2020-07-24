package demo.ritwik.endless.ui.viewHolder

import androidx.recyclerview.widget.RecyclerView

import demo.ritwik.endless.data.Rider

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
