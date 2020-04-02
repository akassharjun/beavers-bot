package model.clashroyale

data class Stats(
		val cardsFound : Int,
		val challengeCardsWon : Int,
		val challengeMaxWins : Int,
		val clanCardsCollected : Int,
		val favoriteCard : FavoriteCard,
		val level : Int,
		val maxTrophies : Int,
		val threeCrownWins : Int,
		val totalDonations : Int,
		val tournamentCardsWon : Int
)