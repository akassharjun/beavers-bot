package command

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import model.bot.PlayerWantList
import net.dv8tion.jda.core.EmbedBuilder
import util.CardHandler
import util.DatabaseManager
import util.Text

class TradeRemove : Command() {

	var wantList : MutableList<String> = mutableListOf()
	var removedCardEmotes = StringBuilder()

	init {
		this.name = "traderemove"
		this.aliases = arrayOf("tr")
		this.help = "Remove cards from your want list."
	}

	override fun execute(event : CommandEvent) {
		val player = DatabaseManager.getPlayerTag(event.author.id)

		val inputCards = event.args.split(" ")

		if (player == null) {
			event.reply("Please save your player tag first!")
		}

		val playerWantList = DatabaseManager.getPlayerWantList(event.author.id)

		if (playerWantList != null) {
			wantList = playerWantList.cards.toList().toMutableList()
		}

		getCards(inputCards)

		DatabaseManager.savePlayerWantList(PlayerWantList(event.author.id, wantList.toTypedArray()))

		val wantListEmotes = StringBuilder()

		wantList.forEach {
			wantListEmotes.append(Text.toEmote(it))
		}

		val tradeRemove = EmbedBuilder()
				.setTitle("${event.author.name}'s Want List")
				.setDescription(wantListEmotes.toString())
				.addField("Removed Cards", removedCardEmotes.toString(), false)
				.setThumbnail(event.author.avatarUrl)

		event.reply(tradeRemove.build())
	}

	private inline fun getCards(inputCards : List<String>) {
		val cardHandler = CardHandler()
		val cardObject = CardHandler::class.members

		inputCards.forEach {
			for (kCallable in cardObject) {
				if (kCallable.returnType.classifier.toString() == "class kotlin.collections.List") {
					val alias = kCallable.call(cardHandler) as List<*>
					if (alias.contains(it)) {
						if (wantList.contains(kCallable.name)) {
							wantList.remove(kCallable.name)
							removedCardEmotes.append(Text.toEmote(kCallable.name))
						}
					}
				}
			}
		}
	}


}