/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 */
package net.ccbluex.liquidbounce.features.module.modules.combat.killaura

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.config.types.nesting.Configurable

object VCoreAuraRotationsConfigurable : Configurable("VCoreRotations") {

    val aimRange by float("AimRange", 2.0f, 0.0f..6.0f)
    val ignoreWalls by boolean("IgnoreWalls", true)
    val rayTrace by boolean("RayTrace", true)
    val wallsBypass by enumChoice("WallsBypass", WallsBypass.OFF)

    val accelerateOnHit by enumChoice("AccelerateOnHit", AccelerateOnHit.OFF)
        .visibleWhen { KillAuraRotationsConfigurable.isVCoreTrack }
    val minYawStep: Int by int("MinYawStep", 65, 1..180)
        .onChange { it.coerceAtMost(maxYawStep) }
        .visibleWhen { KillAuraRotationsConfigurable.isVCoreTrack }
    val maxYawStep: Int by int("MaxYawStep", 75, 1..180)
        .onChange { it.coerceAtLeast(minYawStep) }
        .visibleWhen { KillAuraRotationsConfigurable.isVCoreTrack }
    val aimedPitchStep by float("AimedPitchStep", 1.0f, 0.0f..90.0f)
        .visibleWhen { KillAuraRotationsConfigurable.isVCoreTrack }
    val maxPitchStep by float("MaxPitchStep", 8.0f, 1.0f..90.0f)
        .visibleWhen { KillAuraRotationsConfigurable.isVCoreTrack }
    val pitchAccelerate by float("PitchAccelerate", 1.65f, 1.0f..10.0f)
        .visibleWhen { KillAuraRotationsConfigurable.isVCoreTrack }

    enum class WallsBypass(override val choiceName: String) : NamedChoice {
        OFF("Off"),
        YAW_OFFSET("YawOffset"),
        PEEK_HIGH("PeekHigh")
    }

    enum class AccelerateOnHit(override val choiceName: String) : NamedChoice {
        OFF("Off"),
        YAW("Yaw"),
        PITCH("Pitch"),
        BOTH("Both")
    }
}
