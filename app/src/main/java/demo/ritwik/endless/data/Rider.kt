package demo.ritwik.endless.data

/**
 * Data Class to encapsulate the details of a MotoGP Rider.
 *
 * @param name [String] denoting the Name of the Rider.
 * @param countryCode [String] denoting 3 character Country Code associated with the Rider.
 * @param podiums [Int] denoting the number of times the Rider has stepped in the Podium.
 * @param championships [Int] denoting the number of times the Rider has won the Championship Title.
 * @param number [Int] denoting the Number the Rider associates himself/herself with.
 * @author Ritwik Jamuar
 */
data class Rider(
	val name : String,
	val countryCode : String,
	val podiums : Int,
	val championships : Int,
	val number : Int
)
