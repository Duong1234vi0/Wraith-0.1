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
package net.ccbluex.liquidbounce.features.module.modules.render.popchams

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.TotemPopEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClientTheme
import net.ccbluex.liquidbounce.utils.client.logger
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.player
import net.ccbluex.liquidbounce.utils.client.registerDynamicImageFromResources
import net.minecraft.client.model.geom.ModelLayers
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.player.PlayerModel
import net.minecraft.client.player.AbstractClientPlayer
import net.minecraft.client.player.RemotePlayer
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.player.AvatarRenderer
import net.minecraft.client.renderer.entity.state.AvatarRenderState
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.Pose
import net.minecraft.world.phys.Vec3
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.math.min

/**
 * Source-faithful non-shader PopChams port from VCore's
 * `vcore.features.modules.render.PopChams`.
 *
 * Intentional project deviations:
 * - RGB comes from [ClientTheme.accentA] instead of VCore's standalone ColorSetting.
 * - The existing Iris shader/bloom pass remains in place.
 */
@Suppress("MagicNumber", "TooManyFunctions")
object ModulePopChams : ClientModule("PopChams", Category.RENDER) {

    private val mode by enumChoice("Mode", PopChamsMode.SIMPLE)
    private val secondLayer by boolean("SecondLayer", false)
    private val ySpeed by int("YSpeed", 1, -10..10)
    private val aSpeed by int("AlphaSpeed", 35, 1..100)
    private val rotSpeed by float("RotationSpeed", 0.0f, 0.0f..6.0f)

    private val popList = CopyOnWriteArrayList<Person>()

    private const val INITIAL_ALPHA = 255
    private const val MODEL_ROOT_SCALE_OFFSET = -0.3f

    private var shaderPassRendered = false
    private var shaderPassDisabled = false

    private val shaderSimpleTexture = lazyTexture("popchams-white", "popchams/white.png")

    @Suppress("unused")
    private val totemPopHandler = handler<TotemPopEvent> { event ->
        val popped = event.entity as? AbstractClientPlayer ?: return@handler
        if (popped === player) {
            return@handler
        }

        val level = mc.level ?: return@handler
        val clone = RemotePlayer(level, popped.gameProfile)
        val skin = popped.skin
        val snapshot = PopChamsSkinSnapshot(
            texture = skin.body().texturePath(),
            modelType = skin.model(),
            position = Vec3(popped.x, popped.y, popped.z),
            bodyYaw = popped.yBodyRot,
            headYaw = popped.yHeadRot,
            pitch = popped.xRot,
            crouching = popped.isShiftKeyDown,
            limbPosition = popped.walkAnimation.position(),
            limbSpeed = popped.walkAnimation.speed(),
        )

        clone.copyPosition(popped)
        clone.yRotO = popped.yRotO
        clone.setYRot(popped.yRot)
        clone.xRotO = popped.xRotO
        clone.setXRot(popped.xRot)
        clone.yBodyRotO = popped.yBodyRotO
        clone.setYBodyRot(popped.yBodyRot)
        clone.yHeadRotO = popped.yHeadRotO
        clone.setYHeadRot(popped.yHeadRot)
        clone.isShiftKeyDown = snapshot.crouching
        clone.walkAnimation.setSpeed(snapshot.limbSpeed)
        clone.walkAnimation.position = snapshot.limbPosition

        popList.add(Person(clone, snapshot))
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        popList.forEach { person ->
            if (person.alpha <= 0) {
                popList.remove(person)
                person.discard()
            } else {
                person.alpha -= aSpeed
            }
        }
    }

    override fun disable() {
        popList.forEach { it.discard() }
        popList.clear()
        super.disable()
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        popList.forEach { it.discard() }
        popList.clear()
    }

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        if (shaderPassRendered) {
            shaderPassRendered = false
            return@handler
        }
        if (popList.isEmpty()) {
            return@handler
        }

        val provider = mc.renderBuffers().bufferSource()
        val cam = event.camera.position()
        runCatching {
            popList.forEach { renderEntity(event.matrixStack, provider, it, cam, event.partialTicks, null) }
        }.onFailure { throwable ->
            logger.error("PopChams render failed; disabling module to avoid a client crash.", throwable)
            enabled = false
        }
        provider.endBatch()
    }

    @JvmStatic
    fun renderShaderWorldPass(cameraPos: Vec3) {
        shaderPassRendered = false
        if (shaderPassDisabled || !running || popList.isEmpty() || !IrisShaderPack.inUse()) {
            return
        }

        val provider = mc.renderBuffers().bufferSource()
        val stack = PoseStack()
        val partial = mc.deltaTracker.getGameTimeDeltaPartialTick(true)
        runCatching {
            try {
                popList.forEach { person ->
                    val renderType = RenderTypes.entityTranslucentEmissive(shaderTextureFor(person), false)
                    renderEntity(stack, provider, person, cameraPos, partial, renderType)
                }
            } finally {
                provider.endBatch()
            }
            shaderPassRendered = true
        }.onFailure { throwable ->
            shaderPassDisabled = true
            logger.error("PopChams shader world pass failed; falling back to the normal renderer.", throwable)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun renderEntity(
        matrices: PoseStack,
        provider: MultiBufferSource,
        person: Person,
        cam: Vec3,
        partialTicks: Float,
        shaderRenderType: RenderType?
    ) {
        val renderAlpha = person.renderAlpha()
        if (renderAlpha <= 0) {
            return
        }

        val clone = person.clone
        val renderer = mc.entityRenderDispatcher.getRenderer(clone) as? AvatarRenderer<RemotePlayer> ?: return

        val renderPosition = clone.position()
        val renderX = renderPosition.x - cam.x
        val renderY = renderPosition.y - cam.y
        val renderZ = renderPosition.z - cam.z
        clone.setPos(PopChamsMotion.nextPosition(renderPosition, ySpeed))

        val state = renderer.createRenderState()
        renderer.extractRenderState(clone, state, partialTicks)
        applyFrozenState(state, person)
        person.model.setupAnim(state)

        matrices.pushPose()
        matrices.translate(renderX, renderY, renderZ)
        matrices.mulPose(
            Axis.YP.rotation(
                Math.toRadians((180.0f - person.snapshot.bodyYaw + yRotation(renderAlpha, partialTicks)).toDouble()).toFloat()
            )
        )
        prepareScale(matrices)

        val renderType = shaderRenderType ?: renderTypeFor(person)
        val consumer = provider.getBuffer(renderType)
        val light = PopChamsRenderDecisions.light(mode, shaderRenderType != null)
        person.model.renderToBuffer(matrices, consumer, light, OverlayTexture.NO_OVERLAY, renderColor(renderAlpha))
        matrices.popPose()
    }

    private fun applyFrozenState(state: AvatarRenderState, person: Person) {
        state.bodyRot = person.snapshot.bodyYaw
        state.yRot = person.snapshot.headYaw - person.snapshot.bodyYaw
        state.xRot = person.snapshot.pitch
        state.walkAnimationPos = person.snapshot.limbPosition
        state.walkAnimationSpeed = min(person.snapshot.limbSpeed, 1.0f)
        state.wornHeadAnimationPos = person.snapshot.limbPosition
        state.isCrouching = person.snapshot.crouching
        state.pose = if (person.snapshot.crouching) Pose.CROUCHING else Pose.STANDING
        state.isFallFlying = false
        state.isVisuallySwimming = false
        state.isPassenger = false
        state.isUsingItem = false
        state.isAutoSpinAttack = false
        state.hasRedOverlay = false
        state.isSpectator = false
        state.showCape = false

        val outerVisible = PopChamsRenderDecisions.overlayVisible(secondLayer)
        state.showHat = outerVisible
        state.showJacket = outerVisible
        state.showLeftSleeve = outerVisible
        state.showRightSleeve = outerVisible
        state.showLeftPants = outerVisible
        state.showRightPants = outerVisible
    }

    private fun yRotation(alpha: Int, partialTicks: Float): Float {
        val yRotYaw = alpha / 255.0f * 360.0f * rotSpeed
        return if (yRotYaw != 0.0f) {
            interpolateFloat(
                yRotYaw,
                yRotYaw - aSpeed / 255.0f * 360.0f * rotSpeed,
                partialTicks
            )
        } else {
            yRotYaw
        }
    }

    private fun prepareScale(matrices: PoseStack) {
        matrices.scale(-1.0f, -1.0f, 1.0f)
        matrices.scale(1.6f, 1.8f, 1.6f)
        matrices.translate(0.0f, -1.501f, 0.0f)
    }

    private fun renderTypeFor(person: Person): RenderType = when (mode) {
        PopChamsMode.SIMPLE -> PopChamsPipelines.simple
        PopChamsMode.TEXTURED -> PopChamsPipelines.texturedAdditive(person.texture)
        PopChamsMode.SKIN -> PopChamsPipelines.skinTranslucent(person.texture)
    }

    private fun shaderTextureFor(person: Person): Identifier = when (mode) {
        PopChamsMode.SIMPLE -> shaderSimpleTexture() ?: person.texture
        PopChamsMode.TEXTURED, PopChamsMode.SKIN -> person.texture
    }

    private fun renderColor(alpha: Int): Int =
        PopChamsRenderDecisions.color(mode, ClientTheme.accentA, alpha)

    private fun interpolateFloat(oldValue: Float, newValue: Float, delta: Float): Float =
        oldValue + (newValue - oldValue) * delta

    private class Person(val clone: RemotePlayer, val snapshot: PopChamsSkinSnapshot) {
        val texture: Identifier get() = snapshot.texture
        val model: PlayerModel = PlayerModel(
            mc.entityModels.bakeLayer(PopChamsRenderDecisions.modelLayer(snapshot.modelType)),
            PopChamsRenderDecisions.isSlim(snapshot.modelType)
        ).apply {
            val root = root()
            val initialPose = root.initialPose
            root.setInitialPose(
                PartPose(
                    initialPose.x(),
                    initialPose.y(),
                    initialPose.z(),
                    initialPose.xRot(),
                    initialPose.yRot(),
                    initialPose.zRot(),
                    initialPose.xScale() + MODEL_ROOT_SCALE_OFFSET,
                    initialPose.yScale() + MODEL_ROOT_SCALE_OFFSET,
                    initialPose.zScale() + MODEL_ROOT_SCALE_OFFSET
                )
            )
            root.resetPose()
        }
        var alpha: Int = INITIAL_ALPHA

        fun renderAlpha(): Int = alpha.coerceIn(0, 255)

        fun discard() {
            clone.remove(Entity.RemovalReason.DISCARDED)
        }
    }

    private fun lazyTexture(idPath: String, resource: String): () -> Identifier? {
        var identifier: Identifier? = null
        var failed = false
        return {
            when {
                failed -> null
                identifier != null -> identifier
                else -> {
                    val texture = Identifier.fromNamespaceAndPath("liquidbounce", idPath)
                    runCatching {
                        texture.apply { registerDynamicImageFromResources(resource) }
                    }.onFailure { throwable ->
                        failed = true
                        logger.error("Failed to load PopChams texture $resource", throwable)
                    }.getOrNull()?.also { identifier = it }
                }
            }
        }
    }

    private object IrisShaderPack {
        private var loaded = false
        private var apiInstance: Any? = null
        private var inUseMethod: java.lang.reflect.Method? = null

        fun inUse(): Boolean {
            if (!loaded) {
                loaded = true
                runCatching {
                    val apiClass = Class.forName("net.irisshaders.iris.api.v0.IrisApi")
                    apiInstance = apiClass.getMethod("getInstance").invoke(null)
                    inUseMethod = apiClass.getMethod("isShaderPackInUse")
                }.onFailure {
                    apiInstance = null
                    inUseMethod = null
                }
            }
            val api = apiInstance ?: return false
            val method = inUseMethod ?: return false
            return runCatching { method.invoke(api) as? Boolean ?: false }.getOrDefault(false)
        }
    }
}
