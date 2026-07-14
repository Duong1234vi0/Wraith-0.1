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
package net.ccbluex.liquidbounce.features.module.modules.misc.fakeplayer

import com.mojang.authlib.GameProfile
import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.EventManager
import net.ccbluex.liquidbounce.event.events.AttackEntityEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.PacketEvent
import net.ccbluex.liquidbounce.event.events.TotemPopEvent
import net.ccbluex.liquidbounce.event.events.TransferOrigin
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.command.commands.ingame.fakeplayer.FakePlayer
import net.ccbluex.liquidbounce.features.command.commands.ingame.fakeplayer.fromPlayer
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.criticals.ModuleCriticals
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleTotemParticles
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.player
import net.ccbluex.liquidbounce.utils.client.world
import net.ccbluex.liquidbounce.utils.combat.CombatManager
import net.ccbluex.liquidbounce.utils.entity.addCritParticles
import net.ccbluex.liquidbounce.utils.entity.getDamageFromExplosion
import net.ccbluex.liquidbounce.utils.entity.getAttackCooldownProgress
import net.ccbluex.liquidbounce.utils.entity.getStatusEffect
import net.ccbluex.liquidbounce.utils.entity.resetLastAttackedTicks
import net.ccbluex.liquidbounce.utils.entity.soundCategory
import net.ccbluex.liquidbounce.utils.entity.swingHand
import net.ccbluex.liquidbounce.utils.item.isAxe
import net.ccbluex.liquidbounce.utils.item.isSword
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.network.protocol.game.ClientboundExplodePacket
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.InteractionHand
import net.minecraft.world.damagesource.CombatRules
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.phys.Vec3
import java.util.UUID
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

/**
 * FakePlayer — ported from VCore `vcore.features.modules.misc.FakePlayer` onto the
 * fork's 1.26.2 / Mojang mappings.
 *
 * #Edit By Claude 2026-06-14.
 *
 * Spawns a single client-side dummy player for testing PvP modules (AutoTotem,
 * combat, PopChams, …). It copies the local player's position/inventory, keeps a
 * totem in the off-hand, and estimates damage from incoming crystal explosions and
 * from your own melee attacks. When it would die with a totem available it "pops":
 * it heals back up and fires a [TotemPopEvent] so PopChams / totem counters react,
 * exactly as a real totem pop would (VCore did the same via its `TotemPopEvent`).
 *
 * VCore → fork mapping notes:
 *  - `OtherClientPlayerEntity` → the fork's existing [FakePlayer] (`RemotePlayer`)
 *    entity, spawned through `world.addEntity` (same path as `CommandFakePlayer`).
 *  - explosion damage estimation → [getDamageFromExplosion];
 *    `InventoryUtility.getHitDamage` → a local VCore-style sword/axe/default
 *    damage estimate.
 *  - `EventAttack` → [AttackEntityEvent]; `EventSync` → [GameTickEvent].
 *  - `Managers.COMBAT.popList` → [CombatManager.popList] (shared pop counter).
 *  - `EntityStatusS2CPacket(fakePlayer, 35)` "apply" hack → we fire [TotemPopEvent]
 *    directly (the fork decouples pop detection through that event, see CombatManager).
 *  - Damage absorption math → [applyEstimatedDamage], the same helper the fork's
 *    `CommandFakePlayer` uses, so behaviour matches the existing fake-player handling.
 */
@Suppress("MagicNumber", "TooManyFunctions")
object ModuleFakePlayer : ClientModule("FakePlayer", Category.MISC) {

    private val copyInventory by boolean("CopyInventory", true)
    private val autoTotem by boolean("AutoTotem", true)
    private val nameValue by text("Name", "Fake Player")
    private val elytraMode by boolean("ElytraMode", false)
    private val elytraTakeoffHeight by float("ElytraTakeoffHeight", 12.0f, 3.0f..60.0f)
    private val elytraSpeed by float("ElytraSpeed", 1.2f, 0.1f..5.0f)
    private val elytraDirection by enumChoice("ElytraDirection", ElytraDirection.SPAWN_YAW)
    private val elytraFireworkVisual by boolean("ElytraFireworkVisual", true)

    private var fakePlayer: FakePlayer? = null
    private var deathTime = 0
    private var fakePlayerId = -1
    private var elytraSpawnPos: Vec3? = null
    private var elytraFlightDirection = Vec3.ZERO
    private var elytraFlightPhase = ElytraFlightPhase.NONE
    private var elytraVisualPlayed = false

    override fun enable() {
        val localPlayer = mc.player
        val level = mc.level
        if (localPlayer == null || level == null) {
            enabled = false
            return
        }

        val clone = FakePlayer(
            level,
            GameProfile(UUID.fromString(FAKE_UUID), nameValue)
        )
        clone.onRemoval = { fakePlayer = null }
        clone.id = fakePlayerId--
        clone.loadAttributes(fromPlayer(localPlayer))

        if (copyInventory) {
            clone.setItemInHand(InteractionHand.MAIN_HAND, localPlayer.mainHandItem.copy())
            clone.setItemInHand(InteractionHand.OFF_HAND, localPlayer.offhandItem.copy())
            clone.setItemSlot(EquipmentSlot.HEAD, localPlayer.getItemBySlot(EquipmentSlot.HEAD).copy())
            clone.setItemSlot(EquipmentSlot.CHEST, localPlayer.getItemBySlot(EquipmentSlot.CHEST).copy())
            clone.setItemSlot(EquipmentSlot.LEGS, localPlayer.getItemBySlot(EquipmentSlot.LEGS).copy())
            clone.setItemSlot(EquipmentSlot.FEET, localPlayer.getItemBySlot(EquipmentSlot.FEET).copy())
        }
        if (autoTotem) {
            clone.setItemInHand(InteractionHand.OFF_HAND, ItemStack(Items.TOTEM_OF_UNDYING))
        }
        if (elytraMode) {
            setupElytraMode(clone, localPlayer)
        }

        clone.health = clone.maxHealth
        clone.absorptionAmount = 0.0f

        level.addEntity(clone)
        deathTime = 0
        fakePlayer = clone
    }

    override fun disable() {
        clearFakePlayerState(removeEntity = true)
        super.disable()
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        // Entity belongs to the old level; drop our reference (the entity dies with the world).
        clearFakePlayerState(removeEntity = false)
    }

    // ----- crystal/explosion damage (VCore onPacketReceive ExplosionS2CPacket) -----

    @Suppress("unused")
    private val packetHandler = handler<PacketEvent> { event ->
        if (event.origin != TransferOrigin.INCOMING) {
            return@handler
        }
        val clone = fakePlayer ?: return@handler
        val packet = event.packet as? ClientboundExplodePacket ?: return@handler
        if (clone.invulnerableTime != 0) {
            return@handler
        }

        val damage = clone.getDamageFromExplosion(pos = packet.center, power = packet.radius)
        applyEstimatedDamage(clone, damage)
        checkTotemPop(clone)
    }

    // ----- melee damage (VCore onAttack) -----

    @Suppress("unused")
    private val attackHandler = handler<AttackEntityEvent> { event ->
        val clone = fakePlayer ?: return@handler
        if (event.entity.id != clone.id) {
            return@handler
        }
        // #Edit By Codex 2026-06-15: FakePlayer is client-only, so simulate the hit locally and
        // cancel the outbound attack packet for the fake entity id.
        event.cancelEvent()
        player.swingHand(InteractionHand.MAIN_HAND)

        val damageSource = player.damageSources().playerAttack(player)
        val estimate = calculateAttackDamage(clone, damageSource)
        applyEstimatedDamage(
            clone,
            estimate.damage,
            damageSource,
            player.yRot,
            preservePreviousInvulnerability = true
        )
        playMeleeFeedback(clone, estimate)
        player.resetLastAttackedTicks()
        checkTotemPop(clone)
    }

    // ----- sync: keep a totem in off-hand + auto-disable after death (VCore onSync) -----

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        val clone = fakePlayer ?: return@handler

        if (autoTotem && clone.offhandItem.item != Items.TOTEM_OF_UNDYING) {
            clone.setItemInHand(InteractionHand.OFF_HAND, ItemStack(Items.TOTEM_OF_UNDYING))
        }
        if (elytraMode) {
            tickElytraMode(clone)
        }

        if (clone.health <= 0f) {
            deathTime++
            if (deathTime > 10) {
                enabled = false
            }
        }
    }

    // ----- totem pop -----

    private fun checkTotemPop(clone: FakePlayer) {
        if (clone.health <= 0f && clone.offhandItem.item == Items.TOTEM_OF_UNDYING) {
            handleTotemPop(clone)
        }
    }

    private fun handleTotemPop(clone: FakePlayer) {
        // Mimic a totem pop: consume the totem, heal, re-apply the brief regen/absorption a real
        // totem grants, then announce the pop so PopChams / counters react (VCore fired TotemPopEvent).
        clone.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY)
        clone.health = 1.0f
        clone.absorptionAmount = 0f
        clone.addEffect(MobEffectInstance(MobEffects.REGENERATION, 900, 1))
        clone.addEffect(MobEffectInstance(MobEffects.ABSORPTION, 100, 1))
        clone.addEffect(MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0))
        playTotemPopEffects(clone)
        deathTime = 0

        val pops = CombatManager.popList.merge(clone.name.string, 1, Integer::sum) ?: 1
        EventManager.callEvent(TotemPopEvent(clone, pops))
    }

    private fun playTotemPopEffects(clone: FakePlayer) {
        if (!ModuleTotemParticles.shouldSuppressVanillaTotemEmitter(clone)) {
            mc.particleEngine.createTrackingEmitter(clone, ParticleTypes.TOTEM_OF_UNDYING, 30)
        }
        mc.level?.playLocalSound(
            clone.x, clone.y, clone.z,
            SoundEvents.TOTEM_USE,
            clone.soundCategory,
            1.0f, 1.0f, false
        )
    }

    private fun calculateAttackDamage(target: FakePlayer, damageSource: DamageSource): MeleeDamageEstimate {
        val attackCooldown = player.getAttackCooldownProgress(0.5f)
        if (attackCooldown < FULL_DAMAGE_ATTACK_COOLDOWN) {
            return MeleeDamageEstimate(
                damage = LOW_COOLDOWN_DAMAGE,
                critical = isVCoreCritical()
            )
        }

        var baseDamage = vCoreBaseWeaponDamage(player.weaponItem)
        val critical = isVCoreCritical()
        if (critical) {
            baseDamage += baseDamage * VCORE_CRITICAL_BONUS
        }

        player.getStatusEffect(MobEffects.STRENGTH)?.let { strength ->
            baseDamage += STRENGTH_DAMAGE_BONUS * (strength.amplifier + 1)
        }

        // #Edit By Codex 2026-07-01: mirror VCore InventoryUtility.getHitDamage:
        // fixed sword/axe/default damage, simple fallDistance-or-Criticals crit,
        // Strength bonus, then armor/toughness reduction.
        val afterArmor = CombatRules.getDamageAfterAbsorb(
            target,
            baseDamage,
            damageSource,
            target.armorValue.toFloat(),
            target.getAttributeValue(Attributes.ARMOR_TOUGHNESS).toFloat()
        ).coerceAtLeast(0.0f)

        return MeleeDamageEstimate(
            damage = afterArmor,
            critical = critical
        )
    }

    private fun vCoreBaseWeaponDamage(weaponStack: ItemStack): Float {
        return when {
            weaponStack.isSword -> VCORE_SWORD_DAMAGE
            weaponStack.isAxe -> VCORE_AXE_DAMAGE
            else -> PLAYER_BASE_ATTACK_DAMAGE
        }
    }

    private fun isVCoreCritical(): Boolean {
        return player.fallDistance > 0.0f || ModuleCriticals.running
    }

    private fun applyEstimatedDamage(
        clone: FakePlayer,
        damage: Float,
        source: DamageSource = clone.damageSources().generic(),
        hurtYaw: Float = clone.yRot,
        preservePreviousInvulnerability: Boolean = false
    ) {
        if (damage <= 0f) {
            return
        }

        val previousInvulnerableTime = clone.invulnerableTime
        var tookDamage = false
        val absorbed = damage.coerceAtMost(clone.absorptionAmount)
        if (absorbed > 0f) {
            clone.absorptionAmount = (clone.absorptionAmount - absorbed).coerceAtLeast(0f)
            tookDamage = true
        }

        val remaining = damage - absorbed
        if (remaining > 0f) {
            clone.health -= remaining
            tookDamage = true
        }

        if (tookDamage) {
            clone.handleDamageEvent(source)
            clone.animateHurt(hurtYaw)
            if (preservePreviousInvulnerability) {
                // #Edit By Codex 2026-07-01: melee uses the hurt animation for feedback, but this
                // client-only dummy must stay hit-count based instead of inheriting vanilla i-frames.
                clone.invulnerableTime = previousInvulnerableTime
            }
            if (clone.health > 0f) {
                deathTime = 0
            }
        }
    }

    private fun playMeleeFeedback(clone: FakePlayer, estimate: MeleeDamageEstimate) {
        if (estimate.damage > 0f) {
            mc.level?.playLocalSound(
                clone.x, clone.y, clone.z,
                SoundEvents.PLAYER_HURT,
                clone.soundCategory,
                1.0f, 1.0f, false
            )
        }

        if (estimate.critical) {
            mc.level?.playLocalSound(
                clone.x, clone.y, clone.z,
                SoundEvents.PLAYER_ATTACK_CRIT,
                clone.soundCategory,
                1.0f, 1.0f, false
            )
            player.addCritParticles(clone)
        }
    }

    private fun setupElytraMode(clone: FakePlayer, localPlayer: net.minecraft.client.player.LocalPlayer) {
        clone.setItemSlot(EquipmentSlot.CHEST, ItemStack(Items.ELYTRA))
        if (autoTotem) {
            clone.setItemInHand(InteractionHand.MAIN_HAND, ItemStack(Items.FIREWORK_ROCKET, FIREWORK_STACK_SIZE))
        } else {
            clone.setItemInHand(InteractionHand.OFF_HAND, ItemStack(Items.FIREWORK_ROCKET, FIREWORK_STACK_SIZE))
        }

        elytraSpawnPos = clone.position()
        elytraFlightDirection = initialElytraDirection(localPlayer)
        elytraFlightPhase = ElytraFlightPhase.TAKEOFF
        elytraVisualPlayed = false

        orientElytraFake(clone, elytraFlightDirection, TAKEOFF_PITCH)
        ensureElytraPose(clone)
    }

    private fun tickElytraMode(clone: FakePlayer) {
        val spawnPos = elytraSpawnPos ?: run {
            elytraSpawnPos = clone.position()
            clone.position()
        }
        if (elytraFlightDirection.lengthSqr() < MIN_DIRECTION_LENGTH_SQUARED) {
            elytraFlightDirection = Vec3.directionFromRotation(0f, clone.yRot).horizontalNormalized()
        }

        ensureElytraPose(clone)

        val targetY = spawnPos.y + elytraTakeoffHeight
        val movement = when (elytraFlightPhase) {
            ElytraFlightPhase.NONE -> Vec3.ZERO
            ElytraFlightPhase.TAKEOFF -> {
                if (!elytraVisualPlayed && elytraFireworkVisual) {
                    playElytraFireworkVisual(clone)
                    elytraVisualPlayed = true
                }
                if (clone.y >= targetY) {
                    elytraFlightPhase = ElytraFlightPhase.STRAIGHT
                    elytraFlightDirection.scale(elytraSpeed.toDouble())
                } else {
                    Vec3(0.0, TAKEOFF_SPEED.toDouble().coerceAtMost(targetY - clone.y), 0.0)
                }
            }
            ElytraFlightPhase.STRAIGHT -> {
                val horizontal = elytraFlightDirection.scale(elytraSpeed.toDouble())
                Vec3(horizontal.x, GLIDE_DESCENT_PER_TICK, horizontal.z)
            }
        }

        moveFakePlayer(clone, movement)
        orientElytraFake(
            clone,
            if (elytraFlightPhase == ElytraFlightPhase.TAKEOFF) elytraFlightDirection else movement,
            if (elytraFlightPhase == ElytraFlightPhase.TAKEOFF) TAKEOFF_PITCH else GLIDE_PITCH
        )
    }

    private fun moveFakePlayer(clone: FakePlayer, movement: Vec3) {
        if (movement == Vec3.ZERO) {
            clone.deltaMovement = Vec3.ZERO
            return
        }

        clone.deltaMovement = movement
        clone.setPos(clone.x + movement.x, clone.y + movement.y, clone.z + movement.z)
    }

    private fun initialElytraDirection(localPlayer: net.minecraft.client.player.LocalPlayer): Vec3 {
        val direction = when (elytraDirection) {
            ElytraDirection.SPAWN_YAW -> Vec3.directionFromRotation(0f, localPlayer.yRot)
            ElytraDirection.PLAYER_LOOK -> localPlayer.lookAngle
        }

        return direction.horizontalNormalized().takeUnless {
            it.lengthSqr() < MIN_DIRECTION_LENGTH_SQUARED
        } ?: Vec3.directionFromRotation(0f, localPlayer.yRot).horizontalNormalized()
    }

    private fun orientElytraFake(clone: FakePlayer, direction: Vec3, pitch: Float) {
        val horizontal = direction.horizontalNormalized()
        if (horizontal.lengthSqr() < MIN_DIRECTION_LENGTH_SQUARED) {
            return
        }

        val yaw = Math.toDegrees(atan2(-horizontal.x, horizontal.z)).toFloat()
        clone.setYRot(yaw)
        clone.setXRot(pitch)
        clone.yHeadRot = yaw
        clone.yBodyRot = yaw
        clone.yHeadRotO = yaw
        clone.yBodyRotO = yaw
    }

    private fun ensureElytraPose(clone: FakePlayer) {
        if (!clone.isFallFlying) {
            clone.startFallFlying()
        }

        var shouldRefreshDimensions = false
        if (clone.pose != Pose.FALL_FLYING) {
            clone.setPose(Pose.FALL_FLYING)
            shouldRefreshDimensions = true
        }

        if (clone.dimensions != clone.getDimensions(Pose.FALL_FLYING)) {
            shouldRefreshDimensions = true
        }

        if (shouldRefreshDimensions) {
            clone.refreshDimensions()
        }
    }

    private fun playElytraFireworkVisual(clone: FakePlayer) {
        mc.level?.playLocalSound(
            clone.x, clone.y, clone.z,
            SoundEvents.FIREWORK_ROCKET_LAUNCH,
            clone.soundCategory,
            1.0f, 1.0f, false
        )

        repeat(FIREWORK_PARTICLE_COUNT) { index ->
            val angle = index * (Math.PI * 2.0 / FIREWORK_PARTICLE_COUNT)
            spawnParticle(
                ParticleTypes.FIREWORK,
                clone.x - elytraFlightDirection.x * 0.7,
                clone.y + 0.45,
                clone.z - elytraFlightDirection.z * 0.7,
                cos(angle) * FIREWORK_PARTICLE_SPEED,
                0.02,
                sin(angle) * FIREWORK_PARTICLE_SPEED
            )
        }
    }

    private fun spawnParticle(
        particle: ParticleOptions,
        x: Double,
        y: Double,
        z: Double,
        velocityX: Double,
        velocityY: Double,
        velocityZ: Double
    ) {
        mc.level?.addParticle(particle, x, y, z, velocityX, velocityY, velocityZ)
    }

    private fun clearFakePlayerState(removeEntity: Boolean) {
        fakePlayer?.let { clone ->
            CombatManager.popList.remove(clone.name.string)
            if (removeEntity) {
                mc.level?.removeEntity(clone.id, Entity.RemovalReason.KILLED)
            } else if (!clone.isRemoved) {
                clone.remove(Entity.RemovalReason.KILLED)
            }
        }

        fakePlayer = null
        deathTime = 0
        elytraSpawnPos = null
        elytraFlightDirection = Vec3.ZERO
        elytraFlightPhase = ElytraFlightPhase.NONE
        elytraVisualPlayed = false
    }

    private fun Vec3.horizontalNormalized(): Vec3 {
        val horizontal = Vec3(x, 0.0, z)
        return if (horizontal.lengthSqr() > MIN_DIRECTION_LENGTH_SQUARED) {
            horizontal.normalize()
        } else {
            Vec3.ZERO
        }
    }

    private data class MeleeDamageEstimate(
        val damage: Float,
        val critical: Boolean
    )

    private enum class ElytraFlightPhase {
        NONE,
        TAKEOFF,
        STRAIGHT
    }

    private enum class ElytraDirection(override val choiceName: String) : NamedChoice {
        SPAWN_YAW("SpawnYaw"),
        PLAYER_LOOK("PlayerLook")
    }

    private const val PLAYER_BASE_ATTACK_DAMAGE = 1.0f
    private const val VCORE_SWORD_DAMAGE = 7.0f
    private const val VCORE_AXE_DAMAGE = 9.0f
    private const val VCORE_CRITICAL_BONUS = 0.5f
    private const val STRENGTH_DAMAGE_BONUS = 3.0f
    private const val FULL_DAMAGE_ATTACK_COOLDOWN = 0.85f
    private const val LOW_COOLDOWN_DAMAGE = 1.0f
    private const val TAKEOFF_SPEED = 0.72f
    private const val TAKEOFF_PITCH = -35.0f
    private const val GLIDE_PITCH = 6.0f
    private const val GLIDE_DESCENT_PER_TICK = -0.01
    private const val FIREWORK_STACK_SIZE = 64
    private const val FIREWORK_PARTICLE_COUNT = 10
    private const val FIREWORK_PARTICLE_SPEED = 0.04
    private const val MIN_DIRECTION_LENGTH_SQUARED = 1.0E-6
    private const val FAKE_UUID = "66123666-6666-6666-6666-666666666600"
}
