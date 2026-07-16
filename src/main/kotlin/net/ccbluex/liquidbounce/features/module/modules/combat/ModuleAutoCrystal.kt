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

import com.mojang.blaze3d.platform.InputConstants
import net.ccbluex.liquidbounce.config.types.nesting.Configurable
import net.ccbluex.liquidbounce.event.events.AttackInputEvent
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.ItemUseEvent
import net.ccbluex.liquidbounce.event.events.KeyboardKeyEvent
import net.ccbluex.liquidbounce.event.events.MouseButtonEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.attackEntity
import net.ccbluex.liquidbounce.utils.entity.blockPosition
import net.ccbluex.liquidbounce.utils.entity.crosshairTarget
import net.ccbluex.liquidbounce.utils.entity.getStack
import net.ccbluex.liquidbounce.utils.entity.interactBlock
import net.ccbluex.liquidbounce.utils.entity.setPressed
import net.ccbluex.liquidbounce.utils.entity.side
import net.ccbluex.liquidbounce.utils.entity.swingHand
import net.ccbluex.liquidbounce.utils.entity.useKey
import net.ccbluex.liquidbounce.utils.input.InputBind
import net.ccbluex.liquidbounce.utils.item.isAxe
import net.ccbluex.liquidbounce.utils.item.isPickaxe
import net.ccbluex.liquidbounce.utils.item.isShovel
import net.ccbluex.liquidbounce.utils.item.isSword
import net.minecraft.world.InteractionHand
import net.minecraft.core.BlockPos
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.boss.enderdragon.EndCrystal
import net.minecraft.world.entity.monster.Slime
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.RespawnAnchorBlock
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.HitResult
import org.lwjgl.glfw.GLFW

/**
 * Strict V-core 0.8 AutoCrystal port.
 *
 * This is deliberately a crosshair-driven helper, not a position- or damage-based CrystalAura.
 */
object ModuleAutoCrystal : ClientModule("AutoCrystal", Category.COMBAT) {

    private val activateKey by bind(
        "ActivateKey",
        InputBind(InputConstants.UNKNOWN, InputBind.BindAction.HOLD)
    )
    private val placeDelay by int("PlaceDelay", 0, 0..10, "ticks")
    private val breakDelay by int("BreakDelay", 0, 0..10, "ticks")
    private val crystalBase by boolean("CrystalBase", false)
    private val switchDelay by int("SwitchDelay", 0, 0..10, "ticks").visibleWhen { crystalBase }
    private val baseDelay by int("BaseDelay", 0, 0..10, "ticks").visibleWhen { crystalBase }
    private val stopOnKill by boolean("StopOnKill", false)
    private val damageTick by boolean("DamageTick", false)
    private val antiWeakness by boolean("AntiWeakness", false)

    private object BaseSetting : Configurable("BaseSetting") {
        val checkPlace by boolean("CheckPlace", false)
        val workWithTotem by boolean("WorkWithTotem", false)
        val workWithCrystal by boolean("WorkWithCrystal", false)
        val swordSwap by boolean("SwordSwap", true)
    }

    private var placeClock = 0
    private var breakClock = 0
    private var basePlaceClock = 0
    private var baseSwitchClock = 0
    private var lastCrystalTick = -1

    private var baseActive = false
    private var baseCrystalling = false
    private var baseCrystalSelected = false

    var crystalling = false
        private set

    private var observedPlayer: Any? = null
    private var observedLevel: Any? = null
    private var observedActivationKey = InputConstants.UNKNOWN
    private var activationBindEventDown = false

    init {
        BaseSetting.visibleWhen { crystalBase }
        tree(BaseSetting)
    }

    override fun enable() {
        observedPlayer = mc.player
        observedLevel = mc.level
        resetState()
    }

    override fun disable() {
        observedPlayer = null
        observedLevel = null
        activationBindEventDown = false
        resetState()
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        runCrystalTick(ignoreActivationKey = false)
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        observedPlayer = null
        observedLevel = null
        resetState()
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        observedPlayer = null
        observedLevel = null
        resetState()
    }

    @Suppress("unused")
    private val activationKeyboardHandler = handler<KeyboardKeyEvent> { event ->
        updateActivationBindState(
            activateKey,
            activateKey.matchesKey(event.keyCode, event.scanCode),
            event.action
        )
    }

    @Suppress("unused")
    private val activationMouseHandler = handler<MouseButtonEvent> { event ->
        updateActivationBindState(activateKey, activateKey.matchesMouse(event.button), event.action)
    }

    @Suppress("unused")
    private val attackInputHandler = handler<AttackInputEvent> { event ->
        val player = mc.player ?: return@handler

        if (crystalBase && player.mainHandItem.item == Items.END_CRYSTAL && !isMouseDown(0)) {
            event.cancelEvent()
        }
    }

    @Suppress("unused")
    private val itemUseHandler = handler<ItemUseEvent> { event ->
        val player = mc.player ?: return@handler
        val mainHandItem = player.mainHandItem.item

        if (crystalBase && (mainHandItem == Items.END_CRYSTAL || mainHandItem == Items.OBSIDIAN) && !isMouseDown(1)) {
            event.cancelEvent()
            return@handler
        }

        if (mainHandItem == Items.END_CRYSTAL) {
            val lookedBlock = getLookedBlock()
            if (lookedBlock != null && isCrystalBase(lookedBlock.blockPosition())) {
                event.cancelEvent()
            }
        }
    }

    /**
     * Runs one V-core AutoCrystal tick. This intentionally remains callable for integrations that
     * need to bypass the activation bind while preserving the same clocks and action order.
     */
    fun runCrystalTick(ignoreActivationKey: Boolean) {
        val player = mc.player
        val level = mc.level

        if (player == null || level == null) {
            if (observedPlayer != null || observedLevel != null) {
                observedPlayer = null
                observedLevel = null
                resetState()
            }
            return
        }

        if (observedPlayer !== player || observedLevel !== level) {
            observedPlayer = player
            observedLevel = level
            resetState()
        }

        if (mc.screen != null) {
            return
        }

        val dontPlace = placeClock > 0
        val dontBreak = breakClock > 0

        if (dontPlace) {
            placeClock--
        }

        if (dontBreak) {
            breakClock--
        }

        if (stopOnKill && isDeadPlayerNearby(player)) {
            return
        }

        if (player.isUsingItem) {
            return
        }

        if (damageTick && shouldWaitDamageTick(player)) {
            return
        }

        if (!ignoreActivationKey && !isActivationDown()) {
            resetState()
            return
        }

        if (crystalBase) {
            handleCrystalBase()
        } else {
            resetCrystalBaseState()
        }

        crystalling = true

        if (!isHolding(Items.END_CRYSTAL)) {
            return
        }

        if (lastCrystalTick != player.tickCount) {
            lastCrystalTick = player.tickCount
            handleEntityTarget(dontBreak)
            handleBlockTarget(dontPlace)
        }
    }

    private fun resetState() {
        placeClock = 0
        breakClock = 0
        lastCrystalTick = -1
        crystalling = false
        resetCrystalBaseState()
    }

    private fun resetCrystalBaseState() {
        basePlaceClock = baseDelay
        baseSwitchClock = switchDelay
        baseActive = false
        baseCrystalling = false
        baseCrystalSelected = false
    }

    private fun handleCrystalBase() {
        val hit = getLookedBlock() ?: return
        val player = mc.player ?: return

        if (baseActive || !BaseSetting.checkPlace || canPlaceAgainst(hit)) {
            if (baseActive || isValidBaseStartItem(player.mainHandItem)) {
                if (!baseActive && BaseSetting.swordSwap && isCrystalBase(hit.blockPosition())) {
                    baseCrystalling = true
                }

                baseActive = true

                if (!baseCrystalling) {
                    handleBasePlacement(hit)
                }

                if (baseCrystalling) {
                    handleBaseCrystalSelection()
                }
            }
        }
    }

    private fun handleBasePlacement(hit: BlockHitResult) {
        if (!isCrystalBase(hit.blockPosition()) && !isChargedAnchor(hit.blockPosition())) {
            mc.options.useKey.setPressed(false)

            if (!isHolding(Items.OBSIDIAN)) {
                if (!tickBaseSwitchClock()) {
                    return
                }

                baseSwitchClock = switchDelay
                selectHotbarItem(Items.OBSIDIAN)
            }

            if (isHolding(Items.OBSIDIAN) && tickBasePlaceClock()) {
                placeCrystal(hit)
                basePlaceClock = baseDelay
                baseCrystalling = true
            }
        }
    }

    private fun handleBaseCrystalSelection() {
        if (!isHolding(Items.END_CRYSTAL) && !baseCrystalSelected) {
            if (!tickBaseSwitchClock()) {
                return
            }

            baseCrystalSelected = selectHotbarItem(Items.END_CRYSTAL)
            baseSwitchClock = switchDelay
        }
    }

    private fun handleBlockTarget(dontPlace: Boolean) {
        val hit = getLookedBlock() ?: return

        if (!dontPlace && canPlaceCrystal(hit.blockPosition())) {
            placeCrystal(hit)
            placeClock = placeDelay
        }
    }

    private fun handleEntityTarget(dontBreak: Boolean) {
        val entity = getLookedEntity()?.entity ?: return

        if (dontBreak || entity !is EndCrystal && entity !is Slime) {
            return
        }

        if (ModuleCrystalOptimizer.isAttackInhibited(entity)) {
            return
        }

        val player = mc.player ?: return
        val previousSlot = player.inventory.selectedSlot

        if (antiWeakness && cantBreakCrystal(player)) {
            val weaponSlot = findAntiWeaknessTool(player) ?: return
            if (!selectHotbarSlot(weaponSlot)) {
                return
            }
        }

        attackEntity(entity)
        breakClock = breakDelay

        if (antiWeakness) {
            selectHotbarSlot(previousSlot)
        }
    }

    private fun placeCrystal(hit: BlockHitResult) {
        val player = mc.player ?: return
        val interaction = mc.gameMode ?: return

        interaction.interactBlock(player, InteractionHand.MAIN_HAND, hit)
        player.swingHand(InteractionHand.MAIN_HAND)
    }

    private fun attackEntity(entity: Entity) {
        val player = mc.player ?: return
        val interaction = mc.gameMode ?: return

        interaction.attackEntity(player, entity)
        player.swingHand(InteractionHand.MAIN_HAND)
    }

    private fun tickBaseSwitchClock(): Boolean {
        if (baseSwitchClock > 0) {
            baseSwitchClock--
            return false
        }

        return true
    }

    private fun tickBasePlaceClock(): Boolean {
        if (basePlaceClock > 0) {
            basePlaceClock--
            return false
        }

        return true
    }

    private fun updateActivationBindState(bind: InputBind, matches: Boolean, action: Int) {
        if (observedActivationKey != bind.boundKey) {
            observedActivationKey = bind.boundKey
            activationBindEventDown = false
        }

        if (matches) {
            activationBindEventDown = action != GLFW.GLFW_RELEASE
        }
    }

    private fun isActivationDown(): Boolean {
        val bind = activateKey
        if (bind.isUnbound) {
            return true
        }

        val key = bind.boundKey
        if (observedActivationKey != key) {
            observedActivationKey = key
            activationBindEventDown = false
        }

        return when (key.type) {
            InputConstants.Type.MOUSE -> isMouseDown(key.value)
            InputConstants.Type.KEYSYM -> InputConstants.isKeyDown(mc.window, key.value)
            InputConstants.Type.SCANCODE -> activationBindEventDown
        }
    }

    private fun isMouseDown(button: Int): Boolean =
        GLFW.glfwGetMouseButton(mc.window.handle(), button) == GLFW.GLFW_PRESS

    private fun isHolding(item: Item): Boolean = mc.player?.mainHandItem?.item == item

    private fun isDeadPlayerNearby(player: Player): Boolean = mc.level?.players()?.any { otherPlayer ->
        otherPlayer !== player && otherPlayer.distanceToSqr(player) <= 36.0 &&
            (otherPlayer.isDeadOrDying || otherPlayer.health <= 0.0f)
    } == true

    private fun shouldWaitDamageTick(player: Player): Boolean {
        val targetDamageTick = mc.level?.players()?.any { otherPlayer ->
            otherPlayer !== player && otherPlayer.distanceToSqr(player) < 36.0 &&
                otherPlayer.vehicle == null && !otherPlayer.onGround() && otherPlayer.hurtTime >= 2
        } == true

        return targetDamageTick && player.vehicle !is Player
    }

    private fun cantBreakCrystal(player: Player): Boolean {
        val weakness = player.getEffect(MobEffects.WEAKNESS)
        val strength = player.getEffect(MobEffects.STRENGTH)

        return weakness != null && (strength == null || strength.amplifier <= weakness.amplifier) && !isTool(player.mainHandItem)
    }

    private fun isTool(stack: ItemStack): Boolean =
        stack.isSword || stack.isPickaxe || stack.isAxe || stack.isShovel

    private fun findAntiWeaknessTool(player: Player): Int? =
        (0..8).firstOrNull { isTool(player.inventory.getStack(it)) }

    private fun isValidBaseStartItem(stack: ItemStack): Boolean =
        stack.isSword || BaseSetting.workWithTotem && stack.item == Items.TOTEM_OF_UNDYING ||
            BaseSetting.workWithCrystal && stack.item == Items.END_CRYSTAL

    private fun selectHotbarItem(item: Item): Boolean {
        val player = mc.player ?: return false
        val slot = (0..8).firstOrNull { player.inventory.getStack(it).item == item } ?: return false
        return selectHotbarSlot(slot)
    }

    private fun selectHotbarSlot(slot: Int): Boolean {
        val player = mc.player ?: return false
        val interaction = mc.gameMode ?: return false

        player.inventory.selectedSlot = slot
        interaction.ensureHasSentCarriedItem()
        return true
    }

    private fun canPlaceAgainst(hit: BlockHitResult): Boolean =
        mc.level?.getBlockState(hit.blockPosition().relative(hit.side))?.canBeReplaced() == true

    private fun canPlaceCrystal(basePos: BlockPos): Boolean {
        val level = mc.level ?: return false
        if (!isCrystalBase(basePos)) {
            return false
        }

        val crystalPos = basePos.above()
        if (!level.getBlockState(crystalPos).isAir) {
            return false
        }

        val crystalBox = AABB(
            crystalPos.x.toDouble(),
            crystalPos.y.toDouble(),
            crystalPos.z.toDouble(),
            crystalPos.x + 1.0,
            crystalPos.y + 2.0,
            crystalPos.z + 1.0
        )

        return level.getEntities(null, crystalBox) { it.isAlive }.none(::blocksCrystalPlacement)
    }

    private fun blocksCrystalPlacement(entity: Entity): Boolean =
        !ModuleCrystalOptimizer.shouldIgnoreForPlacement(entity)

    private fun isCrystalBase(pos: BlockPos): Boolean {
        val block = mc.level?.getBlockState(pos)?.block ?: return false
        return block == Blocks.OBSIDIAN || block == Blocks.BEDROCK
    }

    private fun isChargedAnchor(pos: BlockPos): Boolean {
        val state = mc.level?.getBlockState(pos) ?: return false
        return state.block == Blocks.RESPAWN_ANCHOR && state.getValue(RespawnAnchorBlock.CHARGE) > 0
    }

    private fun getLookedBlock(): BlockHitResult? {
        val target = mc.crosshairTarget ?: return null

        if (target.type == HitResult.Type.BLOCK) {
            return target as? BlockHitResult
        }

        if (target is EntityHitResult && ModuleCrystalOptimizer.shouldIgnoreForPlacement(target.entity)) {
            val player = mc.player ?: return null
            val raycastResult = player.pick(5.0, 1.0f, false)

            if (raycastResult.type == HitResult.Type.BLOCK) {
                return raycastResult as? BlockHitResult
            }
        }

        return null
    }

    private fun getLookedEntity(): EntityHitResult? {
        val target = mc.crosshairTarget ?: return null
        return if (target.type == HitResult.Type.ENTITY) target as? EntityHitResult else null
    }
}
