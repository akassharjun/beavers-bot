package model.bot

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Player(
		@BsonId
		val id : ObjectId? = null,
		val userID : String,
		var tag : String
) {
	constructor(userID : String, tag : String) : this(null, userID, tag)
}

