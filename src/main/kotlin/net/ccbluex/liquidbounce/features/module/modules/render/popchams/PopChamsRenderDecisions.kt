package net.ccbluex.liquidbounce.features.module.modules.render.popchams

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.client.model.geom.ModelLayers
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.player.PlayerModelType
import net.minecraft.world.phys.Vec3

internal enum class PopChamsMode(override val choiceName: String) : NamedChoice {
    SIMPLE("Simple"),
    TEXTURED("Textured"),
    SKIN("Skin"),
}

internal enum class PopChamsBlend {
    SOLID,
    ADDITIVE,
    STANDARD_TRANSLUCENT,
}

internal object PopChamsRenderDecisions {
    const val VCORE_MODEL_LIGHT = 10
    const val FULL_BRIGHT_LIGHT = 0xF000F0

    fun color(mode: PopChamsMode, themeColor: Int, alpha: Int): Int {
        val rgb = if (mode == PopChamsMode.SKIN) 0x00FFFFFF else themeColor and 0x00FFFFFF
        return rgb or (alpha.coerceIn(0, 255) shl 24)
    }

    fun blend(mode: PopChamsMode): PopChamsBlend = when (mode) {
        PopChamsMode.SIMPLE -> PopChamsBlend.SOLID
        PopChamsMode.TEXTURED -> PopChamsBlend.ADDITIVE
        PopChamsMode.SKIN -> PopChamsBlend.STANDARD_TRANSLUCENT
    }

    fun light(mode: PopChamsMode, shaderPass: Boolean): Int =
        if (shaderPass || mode == PopChamsMode.SKIN) FULL_BRIGHT_LIGHT else VCORE_MODEL_LIGHT

    fun modelLayer(modelType: PlayerModelType): ModelLayerLocation =
        if (modelType == PlayerModelType.SLIM) ModelLayers.PLAYER_SLIM else ModelLayers.PLAYER

    fun isSlim(modelType: PlayerModelType): Boolean = modelType == PlayerModelType.SLIM

    fun overlayVisible(secondLayer: Boolean): Boolean = secondLayer
}

internal data class PopChamsSkinSnapshot(
    val texture: Identifier,
    val modelType: PlayerModelType,
    val position: Vec3,
    val bodyYaw: Float,
    val headYaw: Float,
    val pitch: Float,
    val crouching: Boolean,
    val limbPosition: Float,
    val limbSpeed: Float,
)
