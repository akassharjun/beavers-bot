package command

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.core.EmbedBuilder
import util.ApiManager
import util.DatabaseManager
import util.Text

class TradeProfile : Command() {

	init {
		name = "tradeprofile"
		help = "Displays your trade profile!"
		aliases = arrayOf("tp")
	}

	override fun execute(event : CommandEvent) {
		val userID = event.author.id

		val playerTag = DatabaseManager.getPlayerTag(userID)

		if (playerTag == null) {
			event.reply("Please save your player tag first!")
			return
		}

		var wantList : MutableList<String> = mutableListOf()
		val playerWantList = DatabaseManager.getPlayerWantList(userID)

		if (playerWantList != null) {
			wantList = playerWantList.cards.toList().toMutableList()
		}

		val clashPlayer = ApiManager.getClashPlayer(playerTag.tag)

		if (clashPlayer == null) {
			event.reply("There was an error with the API!")
			return
		}

		val playerWantsLegendary = StringBuilder()
		val playerHasLegendary = StringBuilder()
		val playerWantsEpic = StringBuilder()
		val playerHasEpic = StringBuilder()
		val playerWantsRare = StringBuilder()
		val playerHasRare = StringBuilder()
		val playerWantsCommon = StringBuilder()
		val playerHasCommon = StringBuilder()

		clashPlayer.cards.forEach {
			val cardName = it.name.replace("-", "").replace(".", "").replace(" ", "")

			when (it.rarity.toUpperCase()) {
				"LEGENDARY" -> {
					if (wantList.contains(cardName)) {
						playerWantsLegendary.append(Text.toEmote(cardName))
					} else {
						if (it.count >= 1) {
							if (!(it.count == 1 && it.level == 1)) {
								playerHasLegendary.append(Text.toEmote(cardName))
							}
						}
					}
				}

				"EPIC" -> {
					if (wantList.contains(cardName)) {
						playerWantsEpic.append(Text.toEmote(cardName))
					} else {
						if (it.count >= 10) {
							playerHasEpic.append(Text.toEmote(cardName))
						}
					}
				}

				"RARE" -> {
					if (wantList.contains(cardName)) {
						playerWantsRare.append(Text.toEmote(cardName))
					} else {
						if (it.count >= 50) {
							playerHasRare.append(Text.toEmote(cardName))
						}
					}
				}

				"COMMON" -> {
					if (wantList.contains(cardName)) {
						playerWantsCommon.append(Text.toEmote(cardName))
					} else {
						if (it.count >= 50) {
							playerHasCommon.append(Text.toEmote(cardName))
						}
					}
				}
			}
		}

		val tradeProfile = EmbedBuilder()

		tradeProfile.setTitle("${event.author.name}'s Trade Profile")
		tradeProfile.addField("Commons", "Wants: $playerWantsCommon \nWants $playerHasCommon", false)
		tradeProfile.addField("Rares", "Wants: $playerWantsRare \nWants $playerHasRare", false)
		tradeProfile.addField("Epics", "Wants: $playerWantsEpic \nWants $playerHasEpic", false)
		tradeProfile.addField("Legendaries", "Wants: $playerWantsLegendary \nWants $playerHasLegendary", false)
	}
}