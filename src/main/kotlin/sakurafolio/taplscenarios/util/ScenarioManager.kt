package sakurafolio.taplscenarios.util

import org.bukkit.event.HandlerList
import sakurafolio.taplscenarios.Scenario
import sakurafolio.taplscenarios.TapLScenarios
import sakurafolio.taplscenarios.scenarios.*

class ScenarioManager(val plugin: TapLScenarios) {
    val scenarios = listOf(
        WeightGains(plugin)
    )

    fun enableScenario(scenario: Scenario) {
        scenario.onEnable()
        plugin.server.pluginManager.registerEvents(scenario, plugin)
    }

    fun disableScenario(scenario: Scenario) {
        scenario.onDisable()
        HandlerList.unregisterAll(scenario)
    }
}