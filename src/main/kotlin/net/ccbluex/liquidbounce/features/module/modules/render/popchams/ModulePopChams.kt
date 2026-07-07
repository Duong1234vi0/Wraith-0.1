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
import net.ccbluex.liquidbounce.config.types.NamedChoice
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
import net.ccbluex.liquidbounce.utils.client.world
import net.ccbluex.liquidbounce.utils.entity.skinTextures
import net.minecraft.client.model.EntityModel
import net.minecraft.client.model.player.PlayerModel
import net.minecraft.client.player.AbstractClientPlayer
import net.minecraft.client.player.RemotePlayer
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.LivingEntityRenderer
import net.minecraft.client.renderer.entity.state.HumanoidRenderState
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.phys.Vec3
import java.util.concurrent.CopyOnWriteArrayList

/**
 * PopChams — ported 1:1 from VCore `vcore.features.modules.render.PopChams`
 * onto the fork's 1.26.2 / Mojang mappings.
 *
 * #Edit By Claude 2026-06-14.
 *
 * Behaviour (faithful to VCore): when a non-self player pops a totem, clone
 * them into a frozen [RemotePlayer] and render that clone's player model
 * rising into the air, slowly rotating and fading out, in the shared client theme colour.
 *
 * VCore → fork mapping notes:
 *  - Totem-pop detection: VCore's `TotemPopEvent` (fired from its `CombatManager`
 *    on an `EntityStatusS2CPacket` status 35) is ported faithfully — the fork's
 *    [TotemPopEvent] is fired by `CombatManager` on a real status-35 packet and
 *    by `ModuleFakePlayer` for a client-side fake pop, and PopChams subscribes to
 *    it. This is VCore's exact decoupling (no raw-packet parsing duplicated here).
 *  - Model render: VCore drew through `Tessellator` + `model.renderToBuffer` +
 *    `setShaderColor`. On 1.26.2 the immediate `setAngles(entity, ...)` API is
 *    gone, so the model is posed from a render-state extracted via the entity's
 *    own renderer ([LivingEntityRenderer.extractRenderState]) and drawn with
 *    [EntityModel.renderToBuffer] through a vanilla entity [RenderType].
 *  - Helpers: `Render2DEngine.interpolateFloat` → [lerp]; `MathUtility.rad` →
 *    [Math.toRadians]; `MathUtility.clamp` → [coerceIn]; `Render3DEngine.getTickDelta`
 *    → `deltaTracker…PartialTick`; `IEntity.setPos` → `clone.setPos`.
 *
 * Shader logic (mirrors [net.ccbluex.liquidbounce.features.module.modules.render.targetesp.ModuleTargetESP]):
 * when an Iris shaderpack is active the chams is drawn through
 * `entityTranslucentEmissive` during [renderShaderWorldPass] (called from
 * `MixinWorldRenderer` before Iris' final composite) so Complementary's bloom
 * lights it up. Otherwise it falls back to the normal [WorldRenderEvent] path.
 *
 * DEVIATION (noted): VCore disabled the depth test so the chams shows through
 * walls. The vanilla entity render types used here depth-test; since the pop
 * model rises into open air above the player this is rarely occluded. The
 * additive `SrcAlpha/One` blend of VCore's Textured mode maps to the emissive
 * (full-bright translucent) entity type, which reads as a bright glow.
 */
@Suppress("MagicNumber", "TooManyFunctions")
object ModulePopChams : ClientModule("PopChams", Category.RENDER) {

    private val mode by enumChoice("Mode", Mode.SIMPLE)
    private val secondLayer by boolean("SecondLayer", false)
    private val ySpeed by int("YSpeed", 1, -10..10)
    private val aSpeed by int("AlphaSpeed", 35, 1..100)
    private val rotSpeed by float("RotationSpeed", 0.0f, 0.0f..6.0f)

    private val popList = CopyOnWriteArrayList<Person>()

    // Flat-colour silhouette texture for Simple mode (Textured mode uses the real skin).
    private val whiteTexture = lazyTexture("popchams-white", "popchams/white.png")

    private const val VCORE_MODEL_LIGHT = 10
    private const val SHADER_FULL_BRIGHT = 0xF000F0
    private const val VANILLA_AVATAR_SCALE = 0.9375f
    private const val INITIAL_ALPHA = 255

    private var shaderPassRendered = false
    private var shaderPassDisabled = false

    // ----- totem-pop detection -----

    // #Edit By Claude 2026-06-14: subscribe to the fork's TotemPopEvent (fired by CombatManager on a
    // real status-35 packet, and by ModuleFakePlayer for a client-side fake pop) instead of parsing the
    // raw packet here. This is VCore's exact decoupling — PopChams reacts to both real and fake pops,
    // and we don't duplicate packet parsing across every consumer.
    @Suppress("unused")
    private val totemPopHandler = handler<TotemPopEvent> { event ->
        val popped = event.entity as? AbstractClientPlayer ?: return@handler
        if (popped === player) {
            return@handler
        }

        val clone = RemotePlayer(world, popped.gameProfile)
        clone.setPos(popped.x, popped.y, popped.z)
        clone.setYRot(popped.yRot)
        clone.yRotO = popped.yRot
        clone.setXRot(popped.xRot)
        clone.xRotO = popped.xRot
        clone.setYHeadRot(popped.yHeadRot)
        clone.yHeadRotO = popped.yHeadRot
        clone.setYBodyRot(popped.yBodyRot)
        clone.yBodyRotO = popped.yBodyRot
        clone.isShiftKeyDown = popped.isShiftKeyDown
        popList.add(Person(clone, popped.skinTextures.texture, INITIAL_ALPHA))
    }

    // ----- fade / cleanup (VCore Person.update on every tick) -----

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        popList.forEach { person ->
            if (person.alpha <= 0) {
                popList.remove(person)
                person.discard()
            } else {
                // #Edit By Codex 2026-06-15: advance PopChams animation once per tick so shader and
                // fallback render paths obey the same YSpeed/AlphaSpeed settings.
                person.previousYOffset = person.yOffset
                person.yOffset += ySpeed / 50.0
                person.previousAlpha = person.alpha
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

    // ----- render: normal post-world path -----

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        if (shaderPassRendered) {
            // The Iris shader-world pass already drew this frame; skip to avoid a duplicate.
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

    // ----- render: Iris shader-world pass (called from MixinWorldRenderer) -----

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
                    // #Edit By Claude 2026-06-14: emissive entity type → Iris maps it to Complementary's
                    // bloom/emissive program, exactly like TargetESP's shader-world pass.
                    val renderType = RenderTypes.entityTranslucentEmissive(textureFor(person), false)
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
        val clone = person.clone
        val renderer = mc.entityRenderDispatcher.getRenderer(clone)
            as? LivingEntityRenderer<LivingEntity, LivingEntityRenderState, EntityModel<LivingEntityRenderState>>
            ?: return

        val renderAlpha = person.renderAlpha(partialTicks)
        val renderYOffset = person.renderYOffset(partialTicks)

        val state = renderer.createRenderState()
        renderer.extractRenderState(clone, state, partialTicks)
        val model = renderer.model
        model.setupAnim(state)

        matrices.pushPose()
        matrices.translate(clone.x - cam.x, clone.y + renderYOffset - cam.y, clone.z - cam.z)

        val yRotYaw = renderAlpha / 255.0f * 360.0f * rotSpeed
        preparePlayerScale(matrices, state, yRotYaw)

        val renderType = shaderRenderType ?: renderTypeFor(person)
        val consumer = provider.getBuffer(renderType)
        val light = if (shaderRenderType != null) SHADER_FULL_BRIGHT else VCORE_MODEL_LIGHT
        if (model is PlayerModel) {
            renderPlayerModel(model, matrices, consumer, light, colorWithAlpha(renderAlpha))
        } else {
            model.renderToBuffer(matrices, consumer, light, OverlayTexture.NO_OVERLAY, colorWithAlpha(renderAlpha))
        }

        matrices.popPose()
    }

    private fun renderPlayerModel(
        model: PlayerModel,
        matrices: PoseStack,
        consumer: com.mojang.blaze3d.vertex.VertexConsumer,
        light: Int,
        color: Int
    ) {
        val oldHat = model.hat.visible
        val oldJacket = model.jacket.visible
        val oldLeftSleeve = model.leftSleeve.visible
        val oldRightSleeve = model.rightSleeve.visible
        val oldLeftPants = model.leftPants.visible
        val oldRightPants = model.rightPants.visible

        try {
            applySecondLayer(model)
            model.renderToBuffer(matrices, consumer, light, OverlayTexture.NO_OVERLAY, color)
        } finally {
            model.hat.visible = oldHat
            model.jacket.visible = oldJacket
            model.leftSleeve.visible = oldLeftSleeve
            model.rightSleeve.visible = oldRightSleeve
            model.leftPants.visible = oldLeftPants
            model.rightPants.visible = oldRightPants
        }
    }

    private fun applySecondLayer(model: PlayerModel) {
        val visible = secondLayer
        model.hat.visible = visible
        model.jacket.visible = visible
        model.leftSleeve.visible = visible
        model.rightSleeve.visible = visible
        model.leftPants.visible = visible
        model.rightPants.visible = visible
    }

    private fun preparePlayerScale(matrices: PoseStack, state: LivingEntityRenderState, yRotYaw: Float) {
        // #Edit By Codex 2026-06-15: mirror 1.26.2 AvatarRenderer's player transform instead of
        // VCore's old raw-model scale. EntityRenderDispatcher has already selected the WIDE/SLIM
        // AvatarRenderer from PlayerSkin.model(), so this keeps head/body/arm dimensions skin-accurate.
        if (state is HumanoidRenderState && state.isCrouching) {
            matrices.translate(0.0, (state.scale * -2.0f / 16.0f).toDouble(), 0.0)
        }

        matrices.scale(state.scale, state.scale, state.scale)
        matrices.mulPose(Axis.YP.rotation(Math.toRadians((180.0f - state.bodyRot + yRotYaw).toDouble()).toFloat()))
        matrices.scale(-1.0f, -1.0f, 1.0f)
        matrices.scale(VANILLA_AVATAR_SCALE, VANILLA_AVATAR_SCALE, VANILLA_AVATAR_SCALE)
        matrices.translate(0.0f, -1.501f, 0.0f)
    }

    private fun renderTypeFor(person: Person): RenderType =
        // #Edit By Codex 2026-06-15: VCore's fallback PopChams draw used raw unlit model buffers plus
        // setShaderColor. A lit entityTranslucent material with light=10 turns red chams nearly black,
        // so the no-shader fallback uses the unlit eyes material and keeps shader-only rendering separate.
        RenderTypes.eyes(textureFor(person))

    private fun textureFor(person: Person): Identifier = if (mode == Mode.TEXTURED) {
        person.texture
    } else {
        whiteTexture() ?: person.texture
    }

    private fun colorWithAlpha(alpha: Int): Int {
        val a = alpha.coerceIn(0, 255)
        // #Edit By Codex 2026-06-15: PopChams no longer exposes a separate Color setting; it follows
        // the shared client theme, matching TargetESP/HUD theme sync.
        val fadeProgress = 1.0f - a / INITIAL_ALPHA.toFloat()
        return withAlpha(themeColor(fadeProgress), a)
    }

    private fun themeColor(progress: Float): Int =
        lerpColor(ClientTheme.accentA, ClientTheme.accentB, progress.coerceIn(0.0f, 1.0f))

    private fun lerpColor(c1: Int, c2: Int, t: Float): Int {
        val a1 = (c1 shr 24) and 0xFF
        val r1 = (c1 shr 16) and 0xFF
        val g1 = (c1 shr 8) and 0xFF
        val b1 = c1 and 0xFF
        val a2 = (c2 shr 24) and 0xFF
        val r2 = (c2 shr 16) and 0xFF
        val g2 = (c2 shr 8) and 0xFF
        val b2 = c2 and 0xFF
        val a = (a1 + (a2 - a1) * t).toInt()
        val r = (r1 + (r2 - r1) * t).toInt()
        val g = (g1 + (g2 - g1) * t).toInt()
        val b = (b1 + (b2 - b1) * t).toInt()
        return (a shl 24) or (r shl 16) or (g shl 8) or b
    }

    private fun withAlpha(color: Int, alpha: Int): Int =
        (color and 0x00FFFFFF) or (alpha.coerceIn(0, 255) shl 24)

    private fun lerp(oldValue: Float, newValue: Float, delta: Float): Float =
        oldValue + (newValue - oldValue) * delta

    @Suppress("unused")
    private enum class Mode(override val choiceName: String) : NamedChoice {
        SIMPLE("Simple"),
        TEXTURED("Textured")
    }

    private class Person(val clone: RemotePlayer, val texture: Identifier, initialAlpha: Int) {
        var previousAlpha: Int = initialAlpha
        var alpha: Int = initialAlpha
        var previousYOffset: Double = 0.0
        var yOffset: Double = 0.0

        fun renderAlpha(partialTicks: Float): Int =
            lerp(previousAlpha.toFloat(), alpha.toFloat(), partialTicks).toInt().coerceIn(0, 255)

        fun renderYOffset(partialTicks: Float): Double =
            previousYOffset + (yOffset - previousYOffset) * partialTicks.toDouble()

        fun discard() {
            clone.remove(Entity.RemovalReason.DISCARDED)
        }
    }

    // ----- lazy texture (mirrors TargetESP.RichTexture) -----

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

    // ----- Iris detection (reflection only, same approach as TargetESP) -----

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

    private const val TOTEM_USE_STATUS = 35
}
