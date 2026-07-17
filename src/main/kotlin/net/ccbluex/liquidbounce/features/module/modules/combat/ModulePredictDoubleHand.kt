/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
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
package net.ccbluex.liquidbounce.features.module.modules.combat

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.events.OverlayRenderEvent
import net.ccbluex.liquidbounce.event.events.PacketEvent
import net.ccbluex.liquidbounce.event.events.TransferOrigin
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.block.raycast
import net.ccbluex.liquidbounce.utils.client.Chronometer
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.armorItems
import net.ccbluex.liquidbounce.utils.entity.mainHandItem
import net.ccbluex.liquidbounce.utils.entity.toCenterPos
import net.ccbluex.liquidbounce.utils.inventory.HotbarItemSlot
import net.ccbluex.liquidbounce.utils.inventory.Slots
import net.ccbluex.liquidbounce.utils.item.getEnchantment
import net.ccbluex.liquidbounce.utils.item.isAxe
import net.ccbluex.liquidbounce.utils.item.isSword
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.player.LocalPlayer
import net.minecraft.core.BlockPos
import net.minecraft.network.protocol.game.ServerboundUseItemPacket
import net.minecraft.world.Difficulty
import net.minecraft.world.damagesource.CombatRules
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.boss.enderdragon.EndCrystal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Items
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.ClipContext
import net.minecraft.world.level.Explosion
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.RespawnAnchorBlock
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.Vec3
import kotlin.math.floor
import kotlin.math.sqrt

/** Strict Prestige PredictDoubleHand port. */
object ModulePredictDoubleHand : ClientModule("PredictDoubleHand", Category.COMBAT) {

    private val delay by intRange("Delay", 30..50, 0..300, "ms")
    private val triggers by multiEnumChoice("Triggers", Trigger.entries)
    private val sensitivity by float("Sensitivity", 1.0f, 0.5f..1.5f)
    private val ignoreWhenShield by boolean("IgnoreWhenShield", true)

    // DragSetting starts at its first value and is only randomized after an elapsed source check.
    private var currentDelay = delay.first
    private val chronometer = Chronometer(System.currentTimeMillis())

    private enum class Trigger(override val choiceName: String) : NamedChoice {
        ANCHORS("Anchors"),
        CRYSTALS("Crystals"),
        OBSIDIAN("Obsidian"),
        SWORD_POP("SwordPop"),
        PEARL("Pearl")
    }

    @Suppress("unused")
    private val overlayHandler = handler<OverlayRenderEvent> {
        if (!chronometer.hasElapsed(currentDelay.toLong())) {
            return@handler
        }

        currentDelay = delay.random()
        chronometer.reset()

        val localPlayer = mc.player ?: return@handler
        val level = mc.level ?: return@handler
        val totemSlot = findFirstHotbarTotem() ?: return@handler
        val selectedTarget = findLastAliveNonSelfPlayer(level, localPlayer) ?: return@handler

        if (localPlayer.distanceTo(selectedTarget) > 10.0f) {
            return@handler
        }

        if (ignoreWhenShield && localPlayer.mainHandItem.item == Items.SHIELD && localPlayer.isUsingItem) {
            return@handler
        }

        if (Trigger.ANCHORS in triggers && hasLethalAnchor(level, localPlayer, selectedTarget)) {
            selectTotem(totemSlot)
            return@handler
        }

        if (Trigger.CRYSTALS in triggers && hasLethalCrystal(level, localPlayer, selectedTarget)) {
            selectTotem(totemSlot)
            return@handler
        }

        if (Trigger.OBSIDIAN in triggers && hasLethalObsidian(level, localPlayer, selectedTarget)) {
            selectTotem(totemSlot)
            return@handler
        }

        if (Trigger.SWORD_POP in triggers && (selectedTarget.mainHandItem.isSword || selectedTarget.mainHandItem.isAxe) &&
            selectedTarget.health + localPlayer.absorptionAmount < 1.0f) {
            selectTotem(totemSlot)
        }
    }

    @Suppress("unused")
    private val pearlPacketHandler = handler<PacketEvent> { event ->
        if (event.origin != TransferOrigin.OUTGOING || Trigger.PEARL !in triggers ||
            event.packet !is ServerboundUseItemPacket) {
            return@handler
        }

        val localPlayer = mc.player ?: return@handler
        if (localPlayer.mainHandItem.item != Items.ENDER_PEARL) {
            return@handler
        }

        val totemSlot = findFirstHotbarTotem() ?: return@handler
        // #Edit By Codex 2026-07-17: do not sync here. The outer Pearl-use packet must leave
        // before normal selected-slot synchronization, otherwise the server can consume the Totem instead.
        localPlayer.inventory.selectedSlot = totemSlot.hotbarSlot
    }

    private fun findFirstHotbarTotem(): HotbarItemSlot? =
        Slots.Hotbar.firstOrNull { it.itemStack.item == Items.TOTEM_OF_UNDYING }

    private fun findLastAliveNonSelfPlayer(level: ClientLevel, localPlayer: LocalPlayer): Player? {
        var selectedTarget: Player? = null
        for (candidate in level.players()) {
            if (candidate.isAlive && candidate != localPlayer) {
                selectedTarget = candidate
            }
        }
        return selectedTarget
    }

    private fun hasLethalAnchor(level: ClientLevel, localPlayer: LocalPlayer, selectedTarget: Player): Boolean {
        val base = localPlayer.blockPosition()
        for (x in -5..5) {
            for (y in -5..5) {
                for (z in -5..5) {
                    val pos = base.offset(x, y, z)
                    val center = pos.toCenterPos()
                    val state = level.getBlockState(pos)
                    if (sqrt(selectedTarget.distanceToSqr(center)) >= 5.0 || state.block != Blocks.RESPAWN_ANCHOR ||
                        state.getValue(RespawnAnchorBlock.CHARGE) != 0) {
                        continue
                    }

                    val damage = getAnchorDamage(level, localPlayer, center) / 2.0 * sensitivity
                    if (wouldPop(selectedTarget, localPlayer, damage)) {
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun hasLethalCrystal(level: ClientLevel, localPlayer: LocalPlayer, selectedTarget: Player): Boolean {
        for (entity in level.entitiesForRendering()) {
            if (entity !is EndCrystal) {
                continue
            }

            val damage = getCrystalDamage(level, localPlayer, entity.blockPosition().toCenterPos()) / 2.0 * sensitivity
            if (wouldPop(selectedTarget, localPlayer, damage)) {
                return true
            }
        }
        return false
    }

    private fun hasLethalObsidian(level: ClientLevel, localPlayer: LocalPlayer, selectedTarget: Player): Boolean {
        val base = localPlayer.blockPosition()
        for (x in -5..5) {
            for (y in -5..5) {
                for (z in -5..5) {
                    val pos = base.offset(x, y, z)
                    val center = pos.toCenterPos()
                    if (pos.y >= localPlayer.y || sqrt(selectedTarget.distanceToSqr(center)) >= 5.0 ||
                        level.getBlockState(pos).block != Blocks.OBSIDIAN || level.getBlockState(pos.above()).block != Blocks.AIR) {
                        continue
                    }

                    val damage = getCrystalDamage(level, localPlayer, pos.above().toCenterPos()) / 2.0 * sensitivity
                    if (wouldPop(selectedTarget, localPlayer, damage)) {
                        return true
                    }
                }
            }
        }
        return false
    }

    // Prestige intentionally combines the selected target's health with the local player's absorption/damage.
    private fun wouldPop(selectedTarget: Player, localPlayer: LocalPlayer, damage: Double): Boolean =
        selectedTarget.health + localPlayer.absorptionAmount - damage < 0.0

    private fun selectTotem(slot: HotbarItemSlot) {
        player.inventory.selectedSlot = slot.hotbarSlot
    }

    private fun getCrystalDamage(level: ClientLevel, player: LocalPlayer, crystal: Vec3): Double {
        if (player.isCreative) {
            return 0.0
        }

        val distance = sqrt(player.distanceToSqr(crystal))
        if (distance > CRYSTAL_RANGE) {
            return 0.0
        }

        val impact = (1.0 - distance / CRYSTAL_RANGE) * getExposure(level, crystal, player)
        var damage = (impact * impact + impact) / 2.0 * 7.0 * (CRYSTAL_POWER * 2.0) + 1.0
        damage = getDamageForDifficulty(level, damage)
        damage = getDamageAfterArmor(level, player, damage)
        damage = getDamageAfterResistance(player, damage)
        return getDamageAfterBlastProtection(player, damage)
    }

    private fun getAnchorDamage(level: ClientLevel, player: LocalPlayer, anchor: Vec3): Double {
        if (player.isCreative) {
            return 0.0
        }

        val distance = sqrt(player.distanceToSqr(anchor))
        if (distance > ANCHOR_RANGE) {
            return 0.0
        }

        val impact = (1.0 - distance / ANCHOR_RANGE) * getExposure(level, anchor, player, BlockPos.containing(anchor))
        var damage = (impact * impact + impact) / 2.0 * 7.0 * (ANCHOR_POWER * 2.0) + 1.0
        damage = getDamageForDifficulty(level, damage)
        // Prestige's bed/anchor path applies Resistance before armor, unlike its crystal path.
        damage = getDamageAfterResistance(player, damage)
        damage = getDamageAfterArmor(level, player, damage)
        return getDamageAfterBlastProtection(player, damage)
    }

    private fun getExposure(level: ClientLevel, source: Vec3, entity: Entity, excludedBlock: BlockPos? = null): Double {
        val box = entity.boundingBox
        val xStep = 1.0 / ((box.maxX - box.minX) * 2.0 + 1.0)
        val yStep = 1.0 / ((box.maxY - box.minY) * 2.0 + 1.0)
        val zStep = 1.0 / ((box.maxZ - box.minZ) * 2.0 + 1.0)
        val xOffset = (1.0 - floor(1.0 / xStep) * xStep) / 2.0
        val zOffset = (1.0 - floor(1.0 / zStep) * zStep) / 2.0

        if (xStep < 0.0 || yStep < 0.0 || zStep < 0.0) {
            return 0.0
        }

        var visibleSamples = 0
        var sampleCount = 0
        var x = 0.0
        while (x <= 1.0) {
            var y = 0.0
            while (y <= 1.0) {
                var z = 0.0
                while (z <= 1.0) {
                    val from = Vec3(
                        lerp(x, box.minX, box.maxX) + xOffset,
                        lerp(y, box.minY, box.maxY),
                        lerp(z, box.minZ, box.maxZ) + zOffset
                    )
                    val hit = level.raycast(
                        ClipContext(from, source, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity),
                        if (excludedBlock == null) null else arrayOf(excludedBlock),
                        null,
                        null
                    )
                    if (hit.type == HitResult.Type.MISS) {
                        visibleSamples++
                    }
                    sampleCount++
                    z += zStep
                }
                y += yStep
            }
            x += xStep
        }

        return visibleSamples.toDouble() / sampleCount
    }

    private fun getDamageForDifficulty(level: ClientLevel, damage: Double): Double = when (level.difficulty) {
        Difficulty.PEACEFUL -> 0.0
        Difficulty.EASY -> minOf(damage / 2.0 + 1.0, damage)
        Difficulty.HARD -> damage * 1.5
        else -> damage
    }

    private fun getDamageAfterArmor(level: ClientLevel, player: LocalPlayer, damage: Double): Double {
        val explosionSource = level.damageSources().explosion(null as Explosion?)
        return CombatRules.getDamageAfterAbsorb(
            player,
            damage.toFloat(),
            explosionSource,
            player.armorValue.toFloat(),
            player.getAttributeValue(Attributes.ARMOR_TOUGHNESS).toFloat()
        ).toDouble()
    }

    private fun getDamageAfterResistance(player: LocalPlayer, damage: Double): Double {
        val resistance = player.getEffect(MobEffects.RESISTANCE) ?: return damage
        return (damage * (1.0 - (resistance.amplifier + 1) * 0.2)).coerceAtLeast(0.0)
    }

    private fun getDamageAfterBlastProtection(player: LocalPlayer, damage: Double): Double {
        val protection = player.armorItems.sumOf {
            it.getEnchantment(Enchantments.PROTECTION) + it.getEnchantment(Enchantments.BLAST_PROTECTION) * 2
        }.coerceAtMost(MAX_BLAST_PROTECTION)
        return (damage * (1.0 - protection / 25.0)).coerceAtLeast(0.0)
    }

    private fun lerp(progress: Double, start: Double, end: Double): Double = start + progress * (end - start)

    private const val CRYSTAL_RANGE = 12.0
    private const val CRYSTAL_POWER = 6.0
    private const val ANCHOR_RANGE = 10.0
    private const val ANCHOR_POWER = 5.0
    private const val MAX_BLAST_PROTECTION = 20
}
