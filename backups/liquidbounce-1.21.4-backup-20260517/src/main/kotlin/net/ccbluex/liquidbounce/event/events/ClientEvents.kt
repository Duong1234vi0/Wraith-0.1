/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2025 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LiquidBounce is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LiquidBounce. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package net.ccbluex.liquidbounce.event.events

import net.ccbluex.liquidbounce.config.types.nesting.Configurable
import net.ccbluex.liquidbounce.config.types.Value
import net.ccbluex.liquidbounce.event.CancellableEvent
import net.ccbluex.liquidbounce.event.Event
import net.ccbluex.liquidbounce.utils.client.Nameable
import net.ccbluex.liquidbounce.utils.inventory.InventoryAction
import net.ccbluex.liquidbounce.utils.inventory.InventoryActionChain
import net.ccbluex.liquidbounce.utils.inventory.InventoryConstraints
import net.ccbluex.liquidbounce.utils.kotlin.Priority
import net.minecraft.client.network.ServerInfo
import net.minecraft.world.GameMode

@Deprecated(
    "The `clickGuiScaleChange` event has been deprecated.",
    ReplaceWith("ClickGuiScaleChangeEvent"),
    DeprecationLevel.WARNING
)
@Nameable("clickGuiScaleChange")
class ClickGuiScaleChangeEvent(val value: Float) : Event()

@Nameable("clickGuiValueChange")
class ClickGuiValueChangeEvent(val configurable: Configurable) : Event()

@Nameable("spaceSeperatedNamesChange")
class SpaceSeperatedNamesChangeEvent(val value: Boolean) : Event()

@Nameable("clientStart")
object ClientStartEvent : Event()

@Nameable("clientShutdown")
object ClientShutdownEvent : Event()

@Nameable("clientLanguageChanged")
class ClientLanguageChangedEvent : Event()

@Nameable("valueChanged")
class ValueChangedEvent(val value: Value<*>) : Event()

@Nameable("moduleActivation")
class ModuleActivationEvent(val moduleName: String) : Event()

@Nameable("moduleToggle")
class ModuleToggleEvent(val moduleName: String, val hidden: Boolean, val enabled: Boolean) : Event()

@Nameable("refreshArrayList")
object RefreshArrayListEvent : Event()

@Nameable("notification")
class NotificationEvent(val title: String, val message: String, val severity: Severity) : Event() {
    enum class Severity {
        INFO, SUCCESS, ERROR, ENABLED, DISABLED
    }
}

@Nameable("gameModeChange")
class GameModeChangeEvent(val gameMode: GameMode) : Event()

@Nameable("targetChange")
class TargetChangeEvent(val targetName: String?) : Event()

@Nameable("blockCountChange")
class BlockCountChangeEvent(val count: Int?) : Event()

@Nameable("accountManagerMessage")
class AccountManagerMessageEvent(val message: String) : Event()

@Nameable("accountManagerLogin")
class AccountManagerLoginResultEvent(val username: String? = null, val error: String? = null) : Event()

@Nameable("accountManagerAddition")
class AccountManagerAdditionResultEvent(val username: String? = null, val error: String? = null) : Event()

@Nameable("accountManagerRemoval")
class AccountManagerRemovalResultEvent(val username: String?) : Event()

@Nameable("serverPinged")
class ServerPingedEvent(val server: ServerInfo) : Event()

@Nameable("rotationUpdate")
object RotationUpdateEvent : Event()

@Nameable("resourceReload")
object ResourceReloadEvent : Event()

@Nameable("scaleFactorChange")
class ScaleFactorChangeEvent(val scaleFactor: Double) : Event()

@Nameable("scheduleInventoryAction")
class ScheduleInventoryActionEvent(val schedule: MutableList<InventoryActionChain> = mutableListOf()) : Event() {

    fun schedule(
        constrains: InventoryConstraints,
        action: InventoryAction,
        priority: Priority = Priority.NORMAL
    ) {
        schedule.add(InventoryActionChain(constrains, arrayOf(action), priority))
    }

    fun schedule(
        constrains: InventoryConstraints,
        vararg actions: InventoryAction,
        priority: Priority = Priority.NORMAL
    ) {
        this.schedule.add(InventoryActionChain(constrains, actions, priority))
    }

    fun schedule(
        constrains: InventoryConstraints,
        actions: List<InventoryAction>,
        priority: Priority = Priority.NORMAL
    ) {
        this.schedule.add(InventoryActionChain(constrains, actions.toTypedArray(), priority))
    }
}

@Nameable("selectHotbarSlotSilently")
class SelectHotbarSlotSilentlyEvent(val requester: Any?, val slot: Int): CancellableEvent()
