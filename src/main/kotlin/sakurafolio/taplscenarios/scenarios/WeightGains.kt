package sakurafolio.taplscenarios.scenarios

import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import sakurafolio.taplscenarios.Scenario
import sakurafolio.taplscenarios.TapLScenarios
import sakurafolio.taplscenarios.util.MetadataExtensions.setMetadata
import sakurafolio.taplscenarios.util.MetadataExtensions.getMetadata

class WeightGains(plugin: TapLScenarios) : Scenario(plugin, "WeightGains") {
    private val consumedKey = key("consumed")
    private val multiplierKey = key("amplifier")

    /**
     * Unneeded for this.
     */
    override fun onEnable() {}
    override fun onDisable() {}

    @EventHandler
    fun onEat(e: PlayerItemConsumeEvent) {
        val consumedFood = getMetadata(e.player, consumedKey, 0) + 1
        if (consumedFood >= 5) { // just in the edge case that for some reason it's gone above 5
            // reset consumed value
            setMetadata(e.player, consumedKey, 0)

            // get next multiplier and store it
            val amplifier = getMetadata(e.player, multiplierKey, -1) + 1
            setMetadata(e.player, multiplierKey, amplifier)

            // update potion effect
            e.player.addPotionEffect(
                PotionEffect(PotionEffectType.SLOW, Int.MAX_VALUE, amplifier, false, false),
                true
            )
        } else {
            setMetadata(e.player, consumedKey, consumedFood)
        }
    }
}