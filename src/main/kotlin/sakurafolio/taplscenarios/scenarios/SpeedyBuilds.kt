package sakurafolio.taplscenarios.scenarios

import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import sakurafolio.taplscenarios.Scenario
import sakurafolio.taplscenarios.TapLScenarios
import sakurafolio.taplscenarios.util.MetadataExtensions.setMetadata
import sakurafolio.taplscenarios.util.MetadataExtensions.getMetadata

/**
 * When you place 100 blocks, you gain 1 level of speed.
 */
class SpeedyBuilds(plugin: TapLScenarios) : Scenario(plugin, "SpeedyBuilds") {
    private val placedKey = key("placed")
    private val multiplierKey = key("amplifier")

    /**
     * Unneeded for this.
     */
    override fun onEnable() {}
    override fun onDisable() {}

    @EventHandler
    fun onEat(e: PlayerItemConsumeEvent) {
        val placedBlocks = getMetadata(e.player, placedKey, 0) + 1
        if (placedBlocks >= 100) { // just in the edge case that for some reason it's gone above 100
            // reset consumed value
            setMetadata(e.player, placedKey, 0)

            // get next multiplier and store it
            val amplifier = getMetadata(e.player, multiplierKey, -1) + 1
            setMetadata(e.player, multiplierKey, amplifier)

            // update potion effect
            e.player.addPotionEffect(
                PotionEffect(PotionEffectType.SPEED, Int.MAX_VALUE, amplifier, false, false),
                true
            )
        } else {
            setMetadata(e.player, placedKey, placedBlocks)
        }
    }
}
