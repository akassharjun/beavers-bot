package model.bot

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class PlayerWantList(
		@BsonId
		val id : ObjectId? = null,
		val userID : String,
		var cards : Array<String>
) {
	constructor(userID : String, cards : Array<String>) : this(null, userID, cards)

	override fun equals(other : Any?) : Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as PlayerWantList

		if (id != other.id) return false
		if (userID != other.userID) return false
		if (!cards.contentEquals(other.cards)) return false

		return true
	}

	override fun hashCode() : Int {
		var result = id?.hashCode() ?: 0
		result = 31 * result + userID.hashCode()
		result = 31 * result + cards.contentHashCode()
		return result
	}
}