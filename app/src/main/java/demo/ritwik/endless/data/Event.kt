package demo.ritwik.endless.data

/**
 * Generic Value Container to emulate a Single Event nature through LiveData.
 *
 * @param T Any Data Class.
 * @param content Instance of [T] denoting it's content.
 * @author Ritwik Jamuar
 */
open class Event<out T>(private val content : T) {

	/**[Boolean] Flag to maintain whether this [Event] has been handled or not.*/
	private var hasBeenHandled: Boolean = false

	/**
	 * Provides the content if the [Event] hasn't been handled previously.
	 *
	 * @return Instance of [T] if it hasn't been handled, else null.
	 */
	fun getContentIfNotHandled(): T? = if (hasBeenHandled) {
		null
	} else {
		this.hasBeenHandled = true
		this.content
	}

	/**
	 * Shows the content of this [Event].
	 * Though, invoking this method would not mark this [Event] as handled.
	 *
	 * @return Content of this container as [T].
	 */
	fun peekContent(): T = this.content

}
