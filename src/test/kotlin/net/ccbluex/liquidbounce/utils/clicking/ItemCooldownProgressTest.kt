package net.ccbluex.liquidbounce.utils.clicking

import java.nio.file.Files
import java.nio.file.Path
import kotlin.math.ceil
import net.ccbluex.liquidbounce.utils.kotlin.random
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ItemCooldownProgressTest {

    @Test
    fun fullCooldownUsesTheVanillaHalfTickCombatSample() {
        val period = 12.5f

        assertEquals(0.96f, zeroPartialProgress(lastAttackedTicks = 12, baseTime = 0, period), EPSILON)
        assertEquals(1.0f, calculateItemCooldownProgress(12, 0, period), EPSILON)
        assertFalse(zeroPartialProgress(lastAttackedTicks = 12, baseTime = 0, period) >= 1.0f)
        assertTrue(calculateItemCooldownProgress(12, 0, period) >= 1.0f)

        assertEquals(12, firstReadyTick(period, threshold = 1.0f))
        assertEquals(11, firstReadyTick(period, threshold = 0.85f))
        assertEquals(13, firstReadyTick(period, threshold = 1.05f))
    }

    @Test
    fun baseTimeRemainsIncludedAndThePartialTickIsAppliedOnce() {
        val period = 12.5f

        assertEquals(0.96f, zeroPartialProgress(lastAttackedTicks = 10, baseTime = 2, period), EPSILON)
        assertEquals(1.0f, calculateItemCooldownProgress(10, 2, period), EPSILON)
        assertEquals(1.04f, doublePartialProgress(lastAttackedTicks = 10, baseTime = 2, period), EPSILON)
    }

    @Test
    fun representativeWeaponPeriodsUseTheSameAttributeDerivedFormula() {
        val cases = listOf(
            WeaponPeriodCase("fast weapon", attackSpeed = 4.0f),
            WeaponPeriodCase("sword-like weapon", attackSpeed = 1.6f),
            WeaponPeriodCase("slow axe-like weapon", attackSpeed = 1.0f),
            WeaponPeriodCase("mace-like slow weapon", attackSpeed = 0.6f),
        )

        for (case in cases) {
            val period = periodForAttackSpeed(case.attackSpeed)
            val firstReadyTick = firstReadyTick(period, threshold = 1.0f)
            val expectedReadyTick = ceil(period - ITEM_COOLDOWN_SAMPLE_TICK_DELTA).toInt()

            assertEquals(expectedReadyTick, firstReadyTick, case.name)
            assertFalse(calculateItemCooldownProgress(firstReadyTick - 1, 0, period) >= 1.0f, case.name)
            assertTrue(calculateItemCooldownProgress(firstReadyTick, 0, period) >= 1.0f, case.name)
        }
    }

    @Test
    fun invalidCooldownPeriodsFailOpenWithoutThrowing() {
        val invalidPeriods = listOf(0.0f, -1.0f, Float.NaN, Float.POSITIVE_INFINITY)

        for (period in invalidPeriods) {
            val progress = calculateItemCooldownProgress(0, 0, period)
            assertTrue(progress.isInfinite() && progress > 0.0f, period.toString())
        }
    }

    @Test
    fun fixedAndRandomRangesKeepExistingRangeSemantics() {
        repeat(8) {
            assertEquals(0.85f, (0.85f..0.85f).random(), 0.0f)
            assertEquals(1.0f, (1.0f..1.0f).random(), 0.0f)
        }

        repeat(128) {
            val value = (0.85f..1.0f).random()
            assertTrue(value >= 0.85f && value <= 1.0f, value.toString())
        }

        repeat(128) {
            val value = (1.0f..0.85f).random()
            assertTrue(value >= 0.85f && value <= 1.0f, value.toString())
        }

        assertThrows(IllegalArgumentException::class.java) {
            (Float.NaN..1.0f).random()
        }
    }

    @Test
    fun criticalImpossibleStatesAndAutoJumpOwnershipStayInTheCriticalsHelpers() {
        val criticals = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/criticals/ModuleCriticals.kt")
        val jump = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/criticals/modes/CriticalsJump.kt")

        assertTrue(criticals.contains("player.isInLava"))
        assertTrue(criticals.contains("player.isTouchingWater"))
        assertTrue(criticals.contains("player.hasVehicle"))
        assertTrue(criticals.contains("it is WebBlock"))
        assertTrue(criticals.contains("LEVITATION"))
        assertTrue(criticals.contains("BLINDNESS"))
        assertTrue(criticals.contains("SLOW_FALLING"))
        assertTrue(criticals.contains("player.isClimbing"))
        assertTrue(criticals.contains("player.isRiding"))
        assertTrue(jump.contains("player.isGliding"))
        assertTrue(jump.contains("event.jump = true"))
        assertTrue(jump.contains("allowsCriticalHit(true)"))
    }

    @Test
    fun cooldownSourceStaysUnclampedAndAttackEmissionStillResetsTickerOnce() {
        val cooldown = source("src/main/kotlin/net/ccbluex/liquidbounce/utils/clicking/ItemCooldown.kt")
        val helper = cooldown.substring(
            cooldown.indexOf("internal fun calculateItemCooldownProgress"),
            cooldown.indexOf("class ItemCooldown"),
        )
        val combat = source("src/main/kotlin/net/ccbluex/liquidbounce/utils/combat/CombatExtensions.kt")

        assertEquals(1, helper.countOccurrences("ITEM_COOLDOWN_SAMPLE_TICK_DELTA"))
        assertFalse(helper.contains("coerceIn"))
        assertFalse(helper.contains("clamp"))
        assertFalse(cooldown.contains("0.85f"))
        assertEquals(1, combat.countOccurrences("ServerboundAttackPacket(this@attack.id)"))
        assertEquals(1, combat.countOccurrences("resetLastAttackedTicks()"))
    }

    private data class WeaponPeriodCase(
        val name: String,
        val attackSpeed: Float,
    )

    private fun firstReadyTick(period: Float, threshold: Float, baseTime: Int = 0) = (0..200).first {
        calculateItemCooldownProgress(it, baseTime, period) >= threshold
    }

    private fun periodForAttackSpeed(attackSpeed: Float) = 1.0f / attackSpeed * 20.0f

    private fun zeroPartialProgress(lastAttackedTicks: Int, baseTime: Int, period: Float) =
        (lastAttackedTicks.toFloat() + baseTime.toFloat()) / period

    private fun doublePartialProgress(lastAttackedTicks: Int, baseTime: Int, period: Float) =
        (lastAttackedTicks.toFloat() + baseTime.toFloat() + ITEM_COOLDOWN_SAMPLE_TICK_DELTA * 2.0f) / period

    private fun source(path: String) = Files.readString(Path.of(path))

    private fun String.countOccurrences(needle: String): Int {
        var count = 0
        var index = indexOf(needle)
        while (index >= 0) {
            count++
            index = indexOf(needle, startIndex = index + needle.length)
        }
        return count
    }

    private companion object {
        const val EPSILON = 1.0E-5f
    }
}
