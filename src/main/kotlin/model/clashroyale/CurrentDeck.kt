package model.clashroyale

data class CurrentDeck(
		val arena : Int,
		val count : Int,
		val description : String,
		val displayLevel : Int,
		val elixir : Int,
		val icon : String,
		val id : Int,
		val key : String,
		val leftToUpgrade : Int,
		val level : Int,
		val maxLevel : Int,
		val minLevel : Int,
		val name : String,
		val rarity : String,
		val requiredForUpgrade : Any,
		val type : String
)