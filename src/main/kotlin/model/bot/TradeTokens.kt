package model.bot

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

class TradeTokens(
        @BsonId
        val id: ObjectId? = null,
        val userID: String,
        val legendaryTokens: Int,
        val epicTokens: Int,
        val rareTokens: Int,
        val commonTokens: Int
) {
    constructor(userID: String, legendaryTokens: Int, epicTokens: Int, rareTokens: Int, commonTokens: Int) : this(null, userID, legendaryTokens, epicTokens, rareTokens, commonTokens)
}
