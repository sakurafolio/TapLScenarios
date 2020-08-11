package sakurafolio.taplscenarios

import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.plugin.java.JavaPlugin
import sakurafolio.taplscenarios.util.ScenarioManager

/**
 * Plugin entry point
 */
@Suppress("unused")
class TapLScenarios : JavaPlugin() {
    companion object {
        lateinit var instance: TapLScenarios
    }

    /**
     * Expose instance in companion object for accessibility by other components
     */
    init {
        instance = this
    }

    private lateinit var scenarioManager: ScenarioManager

    /**
     * Setup scenario manager and scenarios.
     */
    override fun onEnable() {
        scenarioManager = ScenarioManager(this)

        // generate configuration file
        config.options().header("TapL Scenarios Configuration")
        scenarioManager.scenarios.forEach {
            config.addDefault(it.name, false)
        }
        config.options().copyDefaults(true)
        saveConfig()

        // read configuration and enable scenarios
        scenarioManager.scenarios.forEach {
            if (config.getBoolean(it.name)) {
                scenarioManager.enableScenario(it)
            }
        }
    }

    /**
     * Tear down scenarios.
     */
    override fun onDisable() {
        // read configuration and disable scenarios
        scenarioManager.scenarios.forEach {
            if (config.getBoolean(it.name)) {
                scenarioManager.disableScenario(it)
            }
        }
    }
}