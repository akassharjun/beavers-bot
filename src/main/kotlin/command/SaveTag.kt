package command

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import model.bot.Player
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.User
import org.litote.kmongo.toId
import util.ApiManager
import util.DatabaseManager
import java.awt.Color

class SaveTag : Command() {

	init {
		name = "savetag"
		aliases = arrayOf("save")
		help = "Saves your clash royale player tag."
		arguments = "#PLAYERTAG"
	}

	override fun execute(event : CommandEvent) {
		val inputTag = event.args.split(" ")[0].toUpperCase().replace("#", "")

		if (inputTag.isBlank()) {
			event.reply("You haven't entered a tag!")
			return
		}

		// Check if the user is trying to save someone else's tag, if not, it means they are saving their tag.
		val user : User = if (event.message.mentionedMembers.size != 0) {
			event.message.mentionedMembers[0].user
		} else {
			event.author
		}

//		val player : Player? = DatabaseManager.getPlayerTag(user.id)

		val clashPlayer = ApiManager.getClashPlayer(inputTag)

		if (clashPlayer != null) {
			DatabaseManager.savePlayerTag(Player(user.id, inputTag))
			event.reply(EmbedBuilder()
					.setAuthor("${clashPlayer.name} (#$inputTag) was successfully saved!", null, user.avatarUrl)
					.setColor(Color.GREEN)
					.build()
			)

		} else {
			event.reply(EmbedBuilder()
					.setAuthor("Incorrect Player Tag!", null, user.avatarUrl)
					.setColor(Color.RED)
					.setFooter("Player tags should only contain these characters (0, 2, 8, 9, C, G, J, L, P, Q, R, U, V, Y)", null)
					.build()
			)
		}

	}

}