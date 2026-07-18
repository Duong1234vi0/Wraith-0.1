package net.ccbluex.liquidbounce.features.module.modules.render.popchams

import java.nio.file.Files
import java.nio.file.Path
import net.minecraft.world.phys.Vec3
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PopChamsMotionTest {

    @Test
    fun positiveYSpeedAccumulatesFromCurrentPosition() {
        var position = Vec3(0.0, 64.0, 0.0)

        position = PopChamsMotion.nextPosition(position, 10)
        assertEquals(64.2, position.y, EPSILON)
        position = PopChamsMotion.nextPosition(position, 10)
        assertEquals(64.4, position.y, EPSILON)
        position = PopChamsMotion.nextPosition(position, 10)
        assertEquals(64.6, position.y, EPSILON)
    }

    @Test
    fun negativeYSpeedAccumulatesDownward() {
        var position = Vec3(0.0, 64.0, 0.0)

        position = PopChamsMotion.nextPosition(position, -10)
        assertEquals(63.8, position.y, EPSILON)
        position = PopChamsMotion.nextPosition(position, -10)
        assertEquals(63.6, position.y, EPSILON)
    }

    @Test
    fun zeroYSpeedLeavesPositionUnchanged() {
        val initial = Vec3(2.0, 64.0, -20.0)
        var position = initial

        repeat(5) {
            position = PopChamsMotion.nextPosition(position, 0)
        }

        assertEquals(initial, position)
    }

    @Test
    fun snapshotPositionRemainsImmutableWhileAnimatedPositionMoves() {
        val snapshot = Vec3(2.0, 64.0, -20.0)
        val animatedPosition = PopChamsMotion.nextPosition(snapshot, 10)

        assertEquals(Vec3(2.0, 64.0, -20.0), snapshot)
        assertEquals(64.2, animatedPosition.y, EPSILON)
    }

    @Test
    fun repeatedUpdatesDoNotResetToSnapshotPlusFixedDelta() {
        val snapshotPosition = Vec3(0.0, 64.0, 0.0)
        var animatedPosition = snapshotPosition

        animatedPosition = PopChamsMotion.nextPosition(animatedPosition, 10)
        val first = animatedPosition
        animatedPosition = PopChamsMotion.nextPosition(animatedPosition, 10)

        assertEquals(64.2, first.y, EPSILON)
        assertEquals(64.4, animatedPosition.y, EPSILON)
        assertFalse(animatedPosition.y == snapshotPosition.y + PopChamsMotion.verticalDelta(10))
    }

    @Test
    fun everyPopChamsModeUsesTheSameAccumulatedMotion() {
        val positions = PopChamsMode.entries.map {
            PopChamsMotion.nextPosition(Vec3.ZERO, 10)
        }

        assertTrue(positions.all { it == positions.first() })
    }

    @Test
    fun renderCapturesCurrentPositionBeforeAdvancingAndIrisSkipsDuplicateNormalPass() {
        val source = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/popchams/ModulePopChams.kt")

        assertTrue(source.contains("val renderPosition = clone.position()"))
        assertTrue(source.contains("val renderX = renderPosition.x - cam.x"))
        assertTrue(source.contains("val renderY = renderPosition.y - cam.y"))
        assertTrue(source.contains("val renderZ = renderPosition.z - cam.z"))
        assertTrue(source.contains("clone.setPos(PopChamsMotion.nextPosition(renderPosition, ySpeed))"))
        assertFalse(source.contains("person.snapshot.position.x"))
        assertFalse(source.contains("person.snapshot.position.y + ySpeed / 50.0"))
        assertTrue(source.contains("if (shaderPassRendered)"))
        assertTrue(source.contains("shaderPassRendered = true"))
    }

    private fun source(path: String): String = Files.readString(Path.of(path))

    private companion object {
        const val EPSILON = 1.0E-9
    }
}
