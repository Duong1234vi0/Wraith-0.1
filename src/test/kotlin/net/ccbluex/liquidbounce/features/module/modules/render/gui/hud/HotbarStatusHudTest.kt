package net.ccbluex.liquidbounce.features.module.modules.render.gui.hud

import java.nio.file.Files
import java.nio.file.Path
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class HotbarStatusHudTest {

    @Test
    fun noTotemsReturnsZero() {
        assertEquals(0, HotbarStatusHudLogic.totalTotems(emptyList(), stack(false, 0)))
    }

    @Test
    fun oneTotemInHotbarReturnsOne() {
        assertEquals(
            1,
            HotbarStatusHudLogic.totalTotems(listOf(stack(true, 1)), stack(false, 0)),
        )
    }

    @Test
    fun totemsInMainInventoryAreCounted() {
        assertEquals(
            3,
            HotbarStatusHudLogic.totalTotems(
                listOf(stack(false, 1), stack(true, 3)),
                stack(false, 0),
            ),
        )
    }

    @Test
    fun offhandTotemIsCounted() {
        assertEquals(
            1,
            HotbarStatusHudLogic.totalTotems(emptyList(), stack(true, 1)),
        )
    }

    @Test
    fun multipleStackCountsAreSummed() {
        assertEquals(
            129,
            HotbarStatusHudLogic.totalTotems(
                listOf(stack(true, 64), stack(true, 64)),
                stack(true, 1),
            ),
        )
    }

    @Test
    fun armorAndUnrelatedItemsAreNotCounted() {
        assertEquals(
            0,
            HotbarStatusHudLogic.totalTotems(
                listOf(stack(false, 1), stack(false, 1)),
                stack(false, 1),
            ),
        )
    }

    @Test
    fun emptyInventoryWithOffhandTotemReturnsOne() {
        assertEquals(
            1,
            HotbarStatusHudLogic.totalTotems(emptyList(), stack(true, 1)),
        )
    }

    @Test
    fun fullAirAboveWaterReturnsZeroAdjustment() {
        assertEquals(0, HotbarStatusHudLogic.airAdjustment(false, 300, 300))
    }

    @Test
    fun underwaterReturnsTenPixelAdjustment() {
        assertEquals(10, HotbarStatusHudLogic.airAdjustment(true, 300, 300))
    }

    @Test
    fun reducedAirReturnsTenPixelAdjustment() {
        assertEquals(10, HotbarStatusHudLogic.airAdjustment(false, 299, 300))
    }

    @Test
    fun airGreaterThanMaxIsSafelyBounded() {
        assertEquals(0, HotbarStatusHudLogic.airAdjustment(false, 999, 300))
    }

    @Test
    fun nonpositiveMaxAirIsHandledSafely() {
        assertEquals(0, HotbarStatusHudLogic.airAdjustment(false, 0, 0))
        assertEquals(10, HotbarStatusHudLogic.airAdjustment(true, 0, -1))
    }

    @Test
    fun helmetIsAtCenterPlusTwelve() {
        assertEquals(112, HotbarStatusHudLogic.armorIconX(100, 0))
    }

    @Test
    fun chestIsAtCenterPlusThirtyTwo() {
        assertEquals(132, HotbarStatusHudLogic.armorIconX(100, 1))
    }

    @Test
    fun leggingsAreAtCenterPlusFiftyTwo() {
        assertEquals(152, HotbarStatusHudLogic.armorIconX(100, 2))
    }

    @Test
    fun bootsAreAtCenterPlusSeventyTwo() {
        assertEquals(172, HotbarStatusHudLogic.armorIconX(100, 3))
    }

    @Test
    fun emptyArmorSlotsDoNotCollapseRemainingPositions() {
        val center = 200
        val positionsWithGaps = (0..3).map { HotbarStatusHudLogic.armorIconX(center, it) }
        assertEquals(listOf(212, 232, 252, 272), positionsWithGaps)
    }

    @Test
    fun zeroDamageReturnsOneHundredPercent() {
        assertEquals(100, HotbarStatusHudLogic.durabilityPercent(0, 100))
    }

    @Test
    fun halfDamageReturnsFiftyPercent() {
        assertEquals(50, HotbarStatusHudLogic.durabilityPercent(50, 100))
    }

    @Test
    fun fullyDamagedReturnsZeroPercent() {
        assertEquals(0, HotbarStatusHudLogic.durabilityPercent(100, 100))
    }

    @Test
    fun vcoreTruncationBehaviorIsPreserved() {
        assertEquals(67, HotbarStatusHudLogic.durabilityPercent(1, 3))
    }

    @Test
    fun invalidMaxDamageReturnsNoPercentage() {
        assertNull(HotbarStatusHudLogic.durabilityPercent(1, 0))
        assertNull(HotbarStatusHudLogic.durabilityPercent(1, -1))
    }

    @Test
    fun damageValuesAreSafelyClamped() {
        assertEquals(100, HotbarStatusHudLogic.durabilityPercent(-5, 100))
        assertEquals(0, HotbarStatusHudLogic.durabilityPercent(105, 100))
    }

    @Test
    fun undamagedColorIsGreen() {
        assertEquals(0xFF00FF00.toInt(), HotbarStatusHudLogic.durabilityColor(0, 100))
    }

    @Test
    fun halfDurabilityColorMatchesVcoreTruncation() {
        assertEquals(0xFF7F7F00.toInt(), HotbarStatusHudLogic.durabilityColor(50, 100))
    }

    @Test
    fun brokenColorIsRed() {
        assertEquals(0xFFFF0000.toInt(), HotbarStatusHudLogic.durabilityColor(100, 100))
    }

    @Test
    fun durabilityColorChannelsRemainInRange() {
        val color = HotbarStatusHudLogic.durabilityColor(37, 101)
        assertNotNull(color)
        assertTrue(((color!! ushr 24) and 0xFF) in 0..255)
        assertTrue(((color ushr 16) and 0xFF) in 0..255)
        assertTrue(((color ushr 8) and 0xFF) in 0..255)
        assertTrue((color and 0xFF) in 0..255)
    }

    @Test
    fun localPlayerTotemPopStartsAtFifteenTicks() {
        val local = Any()
        val pulse = HotbarStatusHudPulseState()
        pulse.onTotemPop(local, local)
        assertEquals(15, pulse.ticks)
    }

    @Test
    fun anotherPlayersPopDoesNotStartThePulse() {
        val local = Any()
        val other = Any()
        val pulse = HotbarStatusHudPulseState()
        pulse.tick(local)
        pulse.onTotemPop(other, local)
        assertEquals(0, pulse.ticks)
    }

    @Test
    fun pulseDecrementsToZeroWithoutBecomingNegative() {
        val local = Any()
        val pulse = HotbarStatusHudPulseState()
        pulse.onTotemPop(local, local)
        repeat(20) { pulse.tick(local) }
        assertEquals(0, pulse.ticks)
        assertTrue(pulse.factor >= 0f)
    }

    @Test
    fun pulseLifecycleCleanupResetsState() {
        val local = Any()
        val pulse = HotbarStatusHudPulseState()
        pulse.onTotemPop(local, local)
        pulse.reset()
        assertEquals(0, pulse.ticks)
        assertEquals(0f, pulse.factor)
    }

    @Test
    fun settingsAreIndependentAndEnabledByDefault() {
        val source = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleHUD.kt")
        assertTrue(source.contains("boolean(\"TotemCounter\", true)"))
        assertTrue(source.contains("boolean(\"ArmorDurability\", true)"))
        assertTrue(source.contains("totemCounter,"))
        assertTrue(source.contains("armorDurability,"))
    }

    @Test
    fun bothWidgetsRenderOutsideHudModeSelection() {
        val module = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleHUD.kt")
        val renderer = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/HotbarStatusHud.kt")
        assertTrue(module.contains("HotbarStatusHud.render("))
        assertFalse(renderer.contains("wyvernHudMode"))
        assertFalse(renderer.contains("Hud1"))
        assertFalse(renderer.contains("Hud2"))
    }

    @Test
    fun widgetsDoNotUseHudConfigPositions() {
        val renderer = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/HotbarStatusHud.kt")
        assertFalse(renderer.contains("HudConfig"))
        assertFalse(renderer.contains(": HudElement"))
    }

    @Test
    fun widgetsAreNotAffectedByHudScale() {
        val renderer = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/HotbarStatusHud.kt")
        assertFalse(renderer.contains("hudScale"))
        assertTrue(renderer.contains("guiScaledWidth"))
        assertTrue(renderer.contains("guiScaledHeight"))
    }

    @Test
    fun rendererDoesNotCreateCardsOrPanels() {
        val renderer = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/HotbarStatusHud.kt")
        assertFalse(renderer.contains("HudSurfaceRenderer"))
        assertFalse(renderer.contains("drawPanel"))
        assertFalse(renderer.contains("HudConfig"))
    }

    private fun source(path: String): String = Files.readString(Path.of(path))

    private fun stack(isTotem: Boolean, count: Int) = HotbarStatusHudLogic.CountedStack(isTotem, count)
}
