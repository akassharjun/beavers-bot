package util

import util.emote.CREmotes

object Text {

	inline fun toBold(string : String) : String {
		return "**$string**"
	}

	inline fun toItalics(string : String) : String {
		return "*$string*"
	}

	inline fun toEmote(name : String) : String? {
		val emoteHandler = CREmotes()
		val emoteObject = CREmotes::class.members

		for (kCallable in emoteObject) {
			if (kCallable.name == name) {
				println(kCallable.call(emoteHandler))
				return kCallable.call(emoteHandler) as String
			}
		}
		return null
	}

}