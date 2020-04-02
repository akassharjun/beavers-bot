package model.clashroyale

data class FavoriteCard(
		val arena : Int,
		val description : String,
		val elixir : Int,
		val icon : String,
		val id : Int,
		val key : String,
		val maxLevel : Int,
		val minLevel : Int,
		val name : String,
		val rarity : String,
		val type : String
)