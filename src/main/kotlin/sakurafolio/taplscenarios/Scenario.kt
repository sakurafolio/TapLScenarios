package sakurafolio.taplscenarios

import org.bukkit.event.Listener

/**
 * Base class for all scenarios.
 * Listeners are automatically registered and unregistered as the scenarios and enabled and disabled.
 */
abstract class Scenario(val plugin: TapLScenarios, val name: String) : Listener {
    /**
     * When scenario is enabled (perform setup).
     */
    abstract fun onEnable()

    /**
     * When scenario is disabled (perform teardown).
     */
    abstract fun onDisable()

    /**
     * Creates a key with name prefix for metadata.
     */
    fun key(key: String) = "$name:$key"
}