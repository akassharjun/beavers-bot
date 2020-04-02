import com.jagrosh.jdautilities.command.CommandClientBuilder
import com.jagrosh.jdautilities.commons.waiter.EventWaiter
import command.Print
import command.SaveTag
import command.TradeAdd
import command.TradeProfile
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.OnlineStatus
import net.dv8tion.jda.core.entities.Game
import net.dv8tion.jda.core.hooks.ListenerAdapter
import util.Constants
import javax.security.auth.login.LoginException

class Bot : ListenerAdapter() {
	object Bot {
		@JvmStatic
		@Throws(LoginException::class, IllegalArgumentException::class, InterruptedException::class)
		fun main(args : Array<String>) {

			/* BUILDING THE COMMAND CLIENT */

			val waiter = EventWaiter()

			// initialising the command client builder
			val client = CommandClientBuilder()

			client.useDefaultGame()
			client.setOwnerId(Constants.OWNER_ID)
			client.setEmojis("\uD83D\uDE03", "\uD83D\uDE2E", "\uD83D\uDE26")
			client.setPrefix(Constants.PREFIX)

			// adding the commands
			client.addCommands(SaveTag(), TradeAdd(), Print(), TradeProfile())


			/* STARTING A BOT ACCOUNT  */
			JDABuilder(AccountType.BOT)
					// setting the bot token
					.setToken(Constants.BOT_TOKEN)

					// setting the bot to online status
					.setStatus(OnlineStatus.ONLINE)
					.setGame(Game.playing("loading..."))

					// adding the listeners
					.addEventListener(Bot())
					.addEventListener(client.build())
					.addEventListener(waiter)

					.setGame(Game.playing("with you!"))
					.build()
		}
	}
}



