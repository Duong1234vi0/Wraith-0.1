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
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.event.CancellableEvent
import net.ccbluex.liquidbounce.event.Event
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.utils.client.Nameable
import net.ccbluex.liquidbounce.utils.combat.EntityTargetClassification
import net.ccbluex.liquidbounce.utils.combat.EntityTargetingInfo
import net.ccbluex.liquidbounce.utils.kotlin.Priority
import net.ccbluex.liquidbounce.utils.kotlin.PriorityField
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.InteractionHand

@Nameable("attack")
class AttackEntityEvent(
    val entity: Entity,
    val caller: () -> Unit
) : CancellableEvent()

@Nameable("swingHand")
class SwingHandEvent(val hand: InteractionHand) : CancellableEvent()

/**
 * Fired when a player pops a totem of undying.
 *
 * #Edit By Claude 2026-06-14: port of VCore `vcore.events.impl.TotemPopEvent`. VCore fires this
 * from its `CombatManager` (on a real status-35 `EntityStatusS2CPacket`) and from its `FakePlayer`
 * module, decoupling pop detection from consumers like TotemPop / TotemCounter. We keep that
 * decoupling: [net.ccbluex.liquidbounce.utils.combat.CombatManager] fires it for real pops and
 * [net.ccbluex.liquidbounce.features.module.modules.misc.fakeplayer.ModuleFakePlayer] fires it for
 * the client-side fake player (which never produces a network packet).
 */
@Nameable("totemPop")
class TotemPopEvent(val entity: Player, val pops: Int) : Event()

@Nameable("entityMargin")
class EntityMarginEvent(val entity: Entity, var margin: Float) : Event()

@Nameable("tagEntityEvent")
class TagEntityEvent(val entity: Entity, var targetingInfo: EntityTargetingInfo) : Event() {
    val color: PriorityField<Color4b?> = PriorityField(null, Priority.NOT_IMPORTANT)

    /**
     * Don't start combat this target
     */
    fun dontTarget() {
        if (this.targetingInfo.classification == EntityTargetClassification.TARGET) {
            this.targetingInfo = this.targetingInfo.copy(classification = EntityTargetClassification.INTERESTING)
        }
    }

    /**
     * Fully ignore that target
     */
    fun ignore() {
        this.targetingInfo = targetingInfo.copy(classification = EntityTargetClassification.IGNORED)
    }

    fun assumeFriend() {
        this.targetingInfo = targetingInfo.copy(isFriend = true)
    }

    fun color(col: Color4b, priority: Priority) {
        this.color.trySet(col, priority)
    }
}
