package demo.ritwik.endless.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView

import demo.ritwik.endless.data.Rider

import demo.ritwik.endless.databinding.ItemErrorBinding
import demo.ritwik.endless.databinding.ItemLoadingBinding
import demo.ritwik.endless.databinding.ItemRiderBinding

import demo.ritwik.endless.ui.viewHolder.ErrorVH
import demo.ritwik.endless.ui.viewHolder.LoadingVH
import demo.ritwik.endless.ui.viewHolder.RiderVH

const val VIEW_HOLDER_CODE_LOADING : String = "LDG"
const val VIEW_HOLDER_CODE_ERROR : String = "ERR"

const val VIEW_TYPE_RIDER : Int = 0
const val VIEW_TYPE_LOADING : Int = 1
const val VIEW_TYPE_ERROR : Int = 2

/**
 * [RecyclerView.Adapter] to render the [List] of [Rider] in the [RecyclerView].
 *
 * @author Ritwik Jamuar
 */
class RiderAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

	/*---------------------------------------- Components ----------------------------------------*/

	/**Mutable [List] of [Rider] which are to be rendered.*/
	private lateinit var list : ArrayList<Rider>

	/*------------------------------------ Initializer Block -------------------------------------*/

	init {
		initialize()
	}

	/*------------------------------ RecyclerView.Adapter Callbacks ------------------------------*/

	override fun getItemCount() : Int = list.size

	override fun getItemViewType(position : Int) : Int {
		if (list.isEmpty()) return VIEW_TYPE_RIDER
		return when (list[position].countryCode) {
			VIEW_HOLDER_CODE_LOADING -> VIEW_TYPE_LOADING
			VIEW_HOLDER_CODE_ERROR -> VIEW_TYPE_ERROR
			else -> VIEW_TYPE_RIDER
		}
	}

	override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : RecyclerView.ViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		return when (viewType) {
			VIEW_TYPE_ERROR -> ErrorVH(ItemErrorBinding.inflate(inflater, parent, false))
			VIEW_TYPE_LOADING -> LoadingVH(ItemLoadingBinding.inflate(inflater, parent, false))
			else -> RiderVH(ItemRiderBinding.inflate(inflater, parent, false))
		}
	}

	override fun onBindViewHolder(holder : RecyclerView.ViewHolder, position : Int) =
		when (holder) {
			is RiderVH -> holder.setRider(list[position])
			is ErrorVH -> holder.setDescription(list[position].name)
			else -> {
			}
		}

	override fun onViewAttachedToWindow(holder : RecyclerView.ViewHolder) {
		super.onViewAttachedToWindow(holder)
		if (holder is ErrorVH) {
			holder.markAttached()
		}
	}

	override fun onViewDetachedFromWindow(holder : RecyclerView.ViewHolder) {
		super.onViewDetachedFromWindow(holder)
		if (holder is ErrorVH) {
			holder.markDetached()
		}
	}

	/*-------------------------------------- Public Methods --------------------------------------*/

	/**
	 * Appends new [List] of [Rider] with existing [List] in this adapter.
	 *
	 * @param newList New [List] of [Rider].
	 */
	fun addItems(newList: List<Rider>) {
		if (newList.isEmpty()) return
		val positionOfInsertion: Int = list.size
		list.addAll(newList)
		notifyItemRangeChanged(positionOfInsertion, list.size)
	}

	/**Clears the existing rendered [List] of [Rider] from this adapter.*/
	fun clearItems() {
		if (list.isEmpty()) return
		val positionOfDeletion: Int = list.size
		list.clear()
		notifyItemRangeRemoved(0, positionOfDeletion)
	}

	/**
	 * Tells whether [LoadingVH] is being rendered or not.
	 *
	 * @return true if [LoadingVH] is rendered, else false.
	 */
	fun isLoading(): Boolean = VIEW_HOLDER_CODE_LOADING == list[list.size - 1].countryCode

	/**Adds the [LoadingVH].*/
	fun addLoading() {
		val positionOfInsertion = list.size
		if (positionOfInsertion != 0 && VIEW_HOLDER_CODE_LOADING == list[positionOfInsertion - 1].countryCode)
			return
		list.add(Rider("",  VIEW_HOLDER_CODE_LOADING, 0, 0, 0))
		notifyItemInserted(positionOfInsertion)
	}

	/**Removes the [LoadingVH].*/
	fun removeLoading() {
		val positionOfDeletion = list.size
		if (VIEW_HOLDER_CODE_LOADING != list[positionOfDeletion - 1].countryCode) return
		list.removeAt(positionOfDeletion - 1)
		notifyItemRemoved(positionOfDeletion)
	}

	/**
	 * Tells whether [LoadingVH] is being rendered or not.
	 *
	 * @return true if [LoadingVH] is rendered, else false.
	 */
	fun isError(): Boolean = VIEW_HOLDER_CODE_ERROR == list[list.size - 1].countryCode

	/**
	 * Adds the [ErrorVH].
	 *
	 * @param description [String] denoting the description of the Error.
	 */
	fun addError(description: String) {
		val positionOfInsertion = list.size
		if (positionOfInsertion != 0 && VIEW_HOLDER_CODE_ERROR == list[positionOfInsertion - 1].countryCode)
			return
		list.add(Rider(description,  VIEW_HOLDER_CODE_ERROR, 0, 0, 0))
		notifyItemInserted(positionOfInsertion)
	}

	/**Removes the [ErrorVH].*/
	fun removeError() {
		val positionOfDeletion = list.size
		if (VIEW_HOLDER_CODE_ERROR != list[positionOfDeletion - 1].countryCode) return
		list.removeAt(positionOfDeletion - 1)
		notifyItemRemoved(positionOfDeletion)
	}

	/*------------------------------------- Private Methods --------------------------------------*/

	/**Initializes the [RiderAdapter].*/
	private fun initialize() {
		list = ArrayList()
	}

}
