package model.clashroyale

data class ClashPlayer(
		val achievements : List<Achievement>,
		val arena : Arena,
		val cards : List<Card>,
		val clan : Clan,
		val currentDeck : List<CurrentDeck>,
		val deckLink : String,
		val games : Games,
		val leagueStatistics : LeagueStatistics,
		val name : String,
		val rank : Any,
		val stats : Stats,
		val tag : String,
		val trophies : Int
)