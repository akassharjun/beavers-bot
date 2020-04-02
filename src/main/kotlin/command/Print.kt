package command

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import util.CardHandler
import kotlin.system.measureTimeMillis

class Print : Command() {

	init {
		name = "print"
		help = "Testing command"
	}

	override fun execute(event : CommandEvent) {
		val cardHandler = CardHandler()
		val cardObject = CardHandler::class.members


		val time = measureTimeMillis {
			for (kCallable in cardObject) {
				try {
					var alias : List<*>
					try {
						alias = kCallable.call(cardHandler) as List<*>
					} catch (e1 : ClassCastException) {
						break
					}
					println("${kCallable.name} : $alias")

				} catch (e : IllegalArgumentException) {
					//ignore
				}
			}
		}

		println("Took : $time")
	}
}