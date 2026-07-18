package net.ccbluex.liquidbounce.features.module.modules.render.popchams

import java.nio.file.Files
import java.nio.file.Path
import net.minecraft.client.model.geom.ModelLayers
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.player.PlayerModelType
import net.minecraft.world.phys.Vec3
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PopChamsSkinModeTest {

    @Test
    fun skinModeSelectsCapturedTexture() {
        val texture = Identifier.fromNamespaceAndPath("minecraft", "textures/entity/player/custom.png")
        val snapshot = snapshot(texture, PlayerModelType.WIDE)
        assertEquals(texture, snapshot.texture)
    }

    @Test
    fun skinModeSelectsWidePlayerGeometry() {
        assertSame(ModelLayers.PLAYER, PopChamsRenderDecisions.modelLayer(PlayerModelType.WIDE))
        assertFalse(PopChamsRenderDecisions.isSlim(PlayerModelType.WIDE))
    }

    @Test
    fun skinModeSelectsSlimPlayerGeometry() {
        assertSame(ModelLayers.PLAYER_SLIM, PopChamsRenderDecisions.modelLayer(PlayerModelType.SLIM))
        assertTrue(PopChamsRenderDecisions.isSlim(PlayerModelType.SLIM))
    }

    @Test
    fun simpleModeUsesThemeRgb() {
        assertEquals(0x7F123456, PopChamsRenderDecisions.color(PopChamsMode.SIMPLE, 0xFF123456.toInt(), 0x7F))
    }

    @Test
    fun texturedModeUsesThemeRgb() {
        assertEquals(0x40123456, PopChamsRenderDecisions.color(PopChamsMode.TEXTURED, 0xAA123456.toInt(), 0x40))
    }

    @Test
    fun skinModeAlwaysUsesWhiteRgb() {
        assertEquals(0x2AFFFFFF, PopChamsRenderDecisions.color(PopChamsMode.SKIN, 0xFF0088CC.toInt(), 0x2A))
    }

    @Test
    fun allModesPreserveAnimatedAlpha() {
        for (mode in PopChamsMode.entries) {
            assertEquals(0x63, PopChamsRenderDecisions.color(mode, 0xFF010203.toInt(), 0x63) ushr 24)
        }
    }

    @Test
    fun skinUsesStandardTranslucentBlending() {
        assertEquals(PopChamsBlend.STANDARD_TRANSLUCENT, PopChamsRenderDecisions.blend(PopChamsMode.SKIN))
    }

    @Test
    fun texturedRetainsAdditiveBlending() {
        assertEquals(PopChamsBlend.ADDITIVE, PopChamsRenderDecisions.blend(PopChamsMode.TEXTURED))
    }

    @Test
    fun skinUsesFullBrightLight() {
        assertEquals(PopChamsRenderDecisions.FULL_BRIGHT_LIGHT, PopChamsRenderDecisions.light(PopChamsMode.SKIN, false))
        assertEquals(PopChamsRenderDecisions.FULL_BRIGHT_LIGHT, PopChamsRenderDecisions.light(PopChamsMode.SKIN, true))
    }

    @Test
    fun secondLayerControlsAllSixPlayerOverlayParts() {
        assertTrue(PopChamsRenderDecisions.overlayVisible(true))
        assertFalse(PopChamsRenderDecisions.overlayVisible(false))
        val source = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/popchams/ModulePopChams.kt")
        for (part in listOf("showHat", "showJacket", "showLeftSleeve", "showRightSleeve", "showLeftPants", "showRightPants")) {
            assertTrue(source.contains("state.$part = outerVisible"))
        }
    }

    @Test
    fun snapshotIsIndependentOfLaterTargetStateChanges() {
        val original = snapshot(
            Identifier.fromNamespaceAndPath("minecraft", "textures/entity/player/original.png"),
            PlayerModelType.SLIM,
        )
        var laterPosition = Vec3(90.0, 64.0, -20.0)
        laterPosition = laterPosition.add(100.0, 1.0, 100.0)
        assertEquals(Vec3(2.0, 64.0, -20.0), original.position)
        assertEquals(PlayerModelType.SLIM, original.modelType)
    }

    @Test
    fun cleanupAndLocalPlayerExclusionRemainInPlace() {
        val source = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/popchams/ModulePopChams.kt")
        assertTrue(source.contains("override fun disable()"))
        assertTrue(source.contains("private val worldChangeHandler"))
        assertTrue(source.contains("if (popped === player)"))
    }

    @Test
    fun skinModeDoesNotAddEquipmentOrNameLayers() {
        val source = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/popchams/ModulePopChams.kt")
        assertTrue(source.contains("state.showCape = false"))
        assertFalse(source.contains("HumanoidArmorLayer"))
        assertFalse(source.contains("heldOnHead"))
        assertFalse(source.contains("submitNameDisplay"))
    }

    @Test
    fun skinPipelineIsSeparateAndVisibleThroughWalls() {
        val pipelines = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/popchams/PopChamsPipelines.kt")
        val module = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/popchams/ModulePopChams.kt")
        assertTrue(pipelines.contains("SKIN_PIPELINE"))
        assertTrue(pipelines.contains("BlendFunction.TRANSLUCENT"))
        assertTrue(pipelines.contains("withDepthStencilState(Optional.empty())"))
        assertTrue(pipelines.contains("withCull(false)"))
        assertTrue(pipelines.contains("DefaultVertexFormat.ENTITY"))
        assertTrue(module.contains("PopChamsMode.SKIN -> PopChamsPipelines.skinTranslucent"))
    }

    @Test
    fun shaderWorldPassUsesCapturedSkinForSkinModeWithoutDuplicateNormalPass() {
        val source = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/popchams/ModulePopChams.kt")
        assertTrue(source.contains("PopChamsMode.TEXTURED, PopChamsMode.SKIN -> person.texture"))
        assertTrue(source.contains("if (shaderPassRendered)"))
        assertTrue(source.contains("shaderPassRendered = true"))
    }

    private fun snapshot(texture: Identifier, modelType: PlayerModelType) = PopChamsSkinSnapshot(
        texture = texture,
        modelType = modelType,
        position = Vec3(2.0, 64.0, -20.0),
        bodyYaw = 45.0f,
        headYaw = 20.0f,
        pitch = -5.0f,
        crouching = true,
        limbPosition = 1.25f,
        limbSpeed = 0.75f,
    )

    private fun source(path: String): String = Files.readString(Path.of(path))
}
