package model.clashroyale

data class Clan(
		val badge : Badge,
		val donations : Int,
		val donationsDelta : Int,
		val donationsReceived : Int,
		val name : String,
		val role : String,
		val tag : String
)