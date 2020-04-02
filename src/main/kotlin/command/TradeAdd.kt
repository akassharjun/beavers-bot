package command

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import model.bot.PlayerWantList
import net.dv8tion.jda.core.EmbedBuilder
import sun.plugin.javascript.navig.JSType.Embed
import util.CardHandler
import util.DatabaseManager
import util.Text

class TradeAdd : Command() {

	init {
		name = "TradeAdd"
		help = "Adds card(s) to your want list!"
	}

	private var wantList : MutableList<String> = mutableListOf()
	private var addedCardEmotes = StringBuilder()

	override fun execute(event : CommandEvent) {

		val input = event.args.split(" ")

		val user = event.author
		val playerWantList = DatabaseManager.getPlayerWantList(user.id)

		if (playerWantList != null) {
			wantList = playerWantList.cards.toList().toMutableList()
		}

		getCards(input)

		run {
			DatabaseManager.savePlayerWantList(PlayerWantList(user.id, wantList.toTypedArray()))
		}

		event.reply("Added cards! Want List : $addedCardEmotes")

		val wantListEmotes = StringBuilder()

		wantList.forEach{
			wantListEmotes.append(Text.toEmote(it))
		}

		val tradeAdd = EmbedBuilder()
				.setTitle("${event.author.name}'s Want List")
				.setDescription(wantList.toString())
				.addField("Added", addedCardEmotes.toString(), false)
				.setThumbnail(event.author.avatarUrl)

		event.reply(tradeAdd.build())

		addedCardEmotes.clear()
		wantList.clear()
	}

	private inline fun getCards(inputCards : List<String>) {
		val cardHandler = CardHandler()
		val cardObject = CardHandler::class.members

		inputCards.forEach {
			for (kCallable in cardObject) {
				if (kCallable.returnType.classifier.toString() == "class kotlin.collections.List") {
					val alias = kCallable.call(cardHandler) as List<*>
					if (alias.contains(it)) {
						wantList.add(kCallable.name)
						addedCardEmotes.append(Text.toEmote(kCallable.name))
					}
				}
			}
		}
	}

}