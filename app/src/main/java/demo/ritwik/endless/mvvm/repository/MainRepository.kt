package demo.ritwik.endless.mvvm.repository

import android.content.Context

import com.google.gson.Gson

import com.google.gson.reflect.TypeToken

import demo.ritwik.endless.data.Rider

import java.io.IOException

/**
 * Repository of [demo.ritwik.endless.mvvm.viewModel.MainViewModel].
 *
 * @param context [Context] of Application.
 * @author Ritwik Jamuar
 */
class MainRepository constructor(private val context : Context, private val gson: Gson) {

	/*---------------------------------------- Components ----------------------------------------*/

	/**[List] of [Rider] extracted from a JSON File in the 'assets' directory.*/
	private val riders: List<Rider> by lazy { getAllRiders() }

	/*------------------------------------- Private Methods --------------------------------------*/

	/**
	 * Provides a [List] of [Rider] using [extractJSONFromAssets].
	 *
	 * @return If the extracted JSON String is empty, then an Empty [List], else a populated
	 *   [List] of [Rider].
	 */
	private fun getAllRiders(): List<Rider> {
		val json = extractJSONFromAssets("response.json")
		return if (json.isEmpty()) {
			emptyList()
		} else {
			val type = object :TypeToken<Rider>(){}.type
			gson.fromJson(json, type)
		}
	}

	/**
	 * Extracts a JSON File as [String] from Assets directory.
	 *
	 * @param fileName [String] denoting the File Name of JSON file which we wish to extract.
	 * @return [String] as JSON if the file name ends with JSON and the supplied [fileName]
	 *   exists, otherwise an Empty [String].
	 */
	private fun extractJSONFromAssets(fileName: String): String = try {

		// Halt the further execution if the File Name does not ends with '.json'.
		if (!fileName.endsWith(".json")) String()

		// Open an InputStream with the fileName supplied.
		val inputStream = context.assets.open(fileName)
		val size: Int = inputStream.available() // Take the size of InputStream.

		// Initialize a ByteArray of buffer as same size as size of InputStream.
		val buffer = ByteArray(size)

		inputStream.use { stream -> // Use the InputStream.
			stream.read(buffer) // Read the InputStream and store it to Buffer.
		}
		String(buffer) // Return the String initialized with ByteArray of Buffer.

	} catch (e: IOException) {
		String() // In the event of exception, just return an empty String.
	}

}
