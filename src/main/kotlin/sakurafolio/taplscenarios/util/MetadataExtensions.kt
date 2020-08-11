package sakurafolio.taplscenarios.util

import org.bukkit.entity.Player
import org.bukkit.metadata.FixedMetadataValue
import sakurafolio.taplscenarios.TapLScenarios

/**
 * These are extensions that improve the Bukkit metadata system so I don't have to keep wrapping FixedMetadataValue.
 *
 * Yes, I could remove the object, however I personally believe that it provides some needed readability when importing.
 */
object MetadataExtensions {
    /**
     * Get fixed metadata value.
     */
    private fun getMetadataValue(value: Any): FixedMetadataValue = FixedMetadataValue(TapLScenarios.instance, value)

    /**
     * Sets metadata.
     */
    fun setMetadata(player: Player, key: String, value: Any) {
        player.setMetadata(key, getMetadataValue(value))
    }

    /**
     * Gets metadata.
     *
     * This method is a bit hacky -- a MetadataValue can't actually be cast directly to T, we have to do the weird
     * `when` block first to "cast" (it's not casting, it's just trying to figure out what T is and then calling the
     * appropriate method). I'll add more to these branches if I need to.
     */
    @Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")
    inline fun <reified T : Any> getMetadata(player: Player, key: String, defaultValue: T): T {
        if (!player.hasMetadata(key)) return defaultValue
        val temp = player.getMetadata(key)[0]
        return when (T::class) {
            String::class -> temp.asString()
            Int::class -> temp.asInt()
            else -> null
        } as T // this is required so that I can actually return these values correctly
    }
}