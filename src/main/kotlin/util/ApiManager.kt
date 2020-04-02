package util

import model.clashroyale.ClashPlayer
import com.google.gson.Gson
import io.github.rybalkinsd.kohttp.dsl.httpGet
import okhttp3.Response

object ApiManager {

	private const val baseURL = "api.royaleapi.com"

	fun getClashPlayer(playerTag : String) : ClashPlayer? {
		val response : Response = httpGet {
			host = baseURL
			path = "/player/$playerTag"
			header {
				"auth" to Constants.ROYALE_API_TOKEN
			}
		}

		if (response.code() == 200) {
			val jsonString = response.body()!!.string().toString()
			val clashPlayer = Gson().fromJson<ClashPlayer>(jsonString, ClashPlayer::class.java)
			return clashPlayer
		}
		return null
	}

	fun testGraphQL(){
		val response : Response = httpGet {
			host = "192.168.1.4"
			path = "/graphql?query={allLinks{url}}"
		}

		println(response.body()!!.string())
	}
}
