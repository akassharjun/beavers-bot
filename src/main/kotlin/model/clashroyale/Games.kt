package model.clashroyale

data class Games(
		val draws : Int,
		val drawsPercent : Double,
		val losses : Int,
		val lossesPercent : Double,
		val total : Int,
		val tournamentGames : Int,
		val warDayWins : Int,
		val wins : Int,
		val winsPercent : Double
)