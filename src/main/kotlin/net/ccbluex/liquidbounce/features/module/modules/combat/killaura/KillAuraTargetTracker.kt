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
 */
package net.ccbluex.liquidbounce.features.module.modules.combat.killaura
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleAutoWeapon
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleEMaceHelper
import net.ccbluex.liquidbounce.utils.client.isOlderThanOrEqual1_8
import net.ccbluex.liquidbounce.utils.client.player
import net.ccbluex.liquidbounce.utils.client.world
import net.ccbluex.liquidbounce.utils.combat.TargetTracker
import net.ccbluex.liquidbounce.utils.entity.wouldBlockHit
import net.ccbluex.liquidbounce.utils.item.isAxe
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player

object KillAuraTargetTracker : TargetTracker() {

    /**
     * Allows to ignore when the target is holding a shield,
     * which would normally block attacks.
     */
    private val ignoreShield by boolean("IgnoreShield", true)

    /**
     * #Edit By Claude: Port of VCore Aura's LockTarget. When on, KillAura keeps attacking its current
     * target as long as it stays valid/in-range, instead of re-selecting the best target every tick.
     */
    val lockTarget by boolean("LockTarget", true)

    override fun validate(entity: LivingEntity): Boolean {
        return super.validate(entity) && !ModuleEMaceHelper.shouldSkipAuraTarget(entity) && validateShield(entity)
    }

    /**
     * V-Core keeps a shield-blocking target through Aura's ready/post-action path when no axe can
     * break it, so EMaceHelper still receives its post-attack transition. This is scoped to the
     * helper and does not change the ordinary KillAura shield filter.
     */
    fun shouldSkipActualShieldAttack(entity: Entity): Boolean {
        if (!ModuleEMaceHelper.running || ignoreShield || entity !is Player || isOlderThanOrEqual1_8) {
            return false
        }

        if (ModuleKillAura.canDirectShieldBreak(entity) || player.mainHandItem.isAxe || ModuleAutoWeapon.willBreakShield()) {
            return false
        }

        return entity.isBlocking && entity.wouldBlockHit(player)
    }

    /**
     * Check if the entity is holding a shield and if the shield would block the attack.
     */
    private fun validateShield(entity: LivingEntity): Boolean {
        if (ignoreShield || entity !is Player || isOlderThanOrEqual1_8) {
            return true
        }

        if (ModuleKillAura.canDirectShieldBreak(entity) || player.mainHandItem.isAxe || ModuleAutoWeapon.willBreakShield()) {
            return true
        }

        return !entity.isBlocking || !entity.wouldBlockHit(player) ||
            ModuleEMaceHelper.running && !player.mainHandItem.isAxe && !ModuleAutoWeapon.willBreakShield()
    }

}
