package util

import model.bot.Player
import model.bot.PlayerWantList
import org.bson.Document
import org.bson.types.ObjectId
import org.litote.kmongo.KMongo
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection
import org.litote.kmongo.updateOne
import java.util.*

object DatabaseManager {

	private val client = KMongo.createClient() //get com.mongodb.MongoClient new instance
	private val database = client.getDatabase("beavers-bot") //normal java driver usage

	fun savePlayerTag(player : Player) {
		val col = database.getCollection<Player>() //KMongo extension method

		val foundPlayer = col.findOne(Document("userID", player.userID))

		if (foundPlayer == null) {
			col.insertOne(Player(ObjectId.createFromLegacyFormat(Date().time.toInt(), 0, 1), player.userID, player.tag))
		} else {
			foundPlayer.tag = player.tag

			col.updateOne(foundPlayer)
		}
	}
//	fun savePlayerTag(player : Player) {
//		val col = database.getCollection<Player>() //KMongo extension method
//
//		val foundPlayer = col.findOne(Document("userID", player.userID))
//
//		if (foundPlayer == null) {
//			col.insertOne(Player(player.userID, player.tag))
//		} else {
//			foundPlayer.tag = player.tag
//			col.updateOne(foundPlayer)
//		}
//	}

	fun getPlayerTag(userID : String) : Player? {
		val col = database.getCollection<Player>() //KMongo extension method

		val foundPlayer = col.findOne(Document("userID", userID))
		return foundPlayer
	}

	fun getPlayerWantList(userID : String) : PlayerWantList? {
		val col = database.getCollection<PlayerWantList>() //KMongo extension method

		val foundPlayer = col.findOne(Document("userID", userID))
		return foundPlayer
	}

	fun savePlayerWantList(playerWantList : PlayerWantList) {
		val col = database.getCollection<PlayerWantList>() //KMongo extension method

		val foundPlayerWantList = col.findOne(Document("userID", playerWantList.userID))

		if (foundPlayerWantList == null) {
			col.insertOne(PlayerWantList(ObjectId.createFromLegacyFormat(Date().time.toInt(), 0, 1), playerWantList.userID, playerWantList.cards))
		} else {
			foundPlayerWantList.cards = playerWantList.cards
			col.updateOne(foundPlayerWantList)
		}
	}

	fun savePlayerWantedCards() {}
}

