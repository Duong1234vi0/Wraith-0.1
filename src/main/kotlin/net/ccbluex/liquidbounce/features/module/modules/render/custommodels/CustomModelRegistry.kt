/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2025 CCBlueX
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
package net.ccbluex.liquidbounce.features.module.modules.render.custommodels

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.features.module.modules.render.custommodels.models.AmogusCustomModel
import net.ccbluex.liquidbounce.features.module.modules.render.custommodels.models.ChinchillaCustomModel
import net.ccbluex.liquidbounce.features.module.modules.render.custommodels.models.CrabCustomModel
import net.ccbluex.liquidbounce.features.module.modules.render.custommodels.models.DemonCustomModel
import net.ccbluex.liquidbounce.features.module.modules.render.custommodels.models.FredBearCustomModel
import net.ccbluex.liquidbounce.features.module.modules.render.custommodels.models.JeffKillerCustomModel
import net.ccbluex.liquidbounce.features.module.modules.render.custommodels.models.RabbitCustomModel
import net.ccbluex.liquidbounce.features.module.modules.render.custommodels.models.SonicCustomModel
import net.minecraft.world.entity.HumanoidArm

enum class CustomModelType(
    override val choiceName: String,
    override val choiceAliases: Array<String> = emptyArray()
) : NamedChoice {
    WHITE_DEMON("White Demon"),
    RED_DEMON("Red Demon"),
    CRAZY_RABBIT("Crazy Rabbit", arrayOf("Rabbit")),
    FREDDY_BEAR("Freddy Bear"),
    SONIC("Sonic"),
    AMOGUS("Amogus"),
    JEFF_KILLER("Jeff Killer"),
    CRAB("Crab"),
    CHINCHILLA("Chinchilla")
}

data class CustomModelTransform(
    val scaleX: Float = 1.0f,
    val scaleY: Float = 1.0f,
    val scaleZ: Float = 1.0f,
    val translateX: Float = 0.0f,
    val translateY: Float = 0.0f,
    val translateZ: Float = 0.0f
)

data class CustomModelSpec(
    val type: CustomModelType,
    val displayName: String,
    val textureResource: String,
    val modelFactory: () -> CustomPlayerModel,
    val transform: CustomModelTransform = CustomModelTransform(),
    val heldItemTransform: ((HumanoidArm) -> FloatArray)? = null
)

object CustomModelRegistry {

    private val specs = mapOf(
        CustomModelType.WHITE_DEMON to CustomModelSpec(
            type = CustomModelType.WHITE_DEMON,
            displayName = "White Demon",
            textureResource = "custommodels/whitedemon.png",
            modelFactory = DemonCustomModel::create
        ),
        CustomModelType.RED_DEMON to CustomModelSpec(
            type = CustomModelType.RED_DEMON,
            displayName = "Red Demon",
            textureResource = "custommodels/demon.png",
            modelFactory = DemonCustomModel::create
        ),
        CustomModelType.CRAZY_RABBIT to CustomModelSpec(
            type = CustomModelType.CRAZY_RABBIT,
            displayName = "Crazy Rabbit",
            textureResource = "custommodels/rabbit.png",
            modelFactory = RabbitCustomModel::create,
            transform = CustomModelTransform(0.825f, 0.825f, 0.825f, 0.0f, 0.3f, 0.0f),
            heldItemTransform = ::rabbitHeldItemTransform
        ),
        CustomModelType.FREDDY_BEAR to CustomModelSpec(
            type = CustomModelType.FREDDY_BEAR,
            displayName = "Freddy Bear",
            textureResource = "custommodels/freddy.png",
            modelFactory = FredBearCustomModel::create,
            transform = CustomModelTransform(0.75f, 0.65f, 0.75f, 0.0f, 0.85f, 0.0f)
        ),
        CustomModelType.SONIC to CustomModelSpec(
            type = CustomModelType.SONIC,
            displayName = "Sonic",
            textureResource = "custommodels/sonic.png",
            modelFactory = SonicCustomModel::create,
            transform = CustomModelTransform(1.3f, 1.3f, 1.3f, 0.0f, -0.35f, 0.0f)
        ),
        CustomModelType.AMOGUS to CustomModelSpec(
            type = CustomModelType.AMOGUS,
            displayName = "Amogus",
            textureResource = "custommodels/amogus.png",
            modelFactory = AmogusCustomModel::create
        ),
        CustomModelType.JEFF_KILLER to CustomModelSpec(
            type = CustomModelType.JEFF_KILLER,
            displayName = "Jeff Killer",
            textureResource = "custommodels/jeff.png",
            modelFactory = JeffKillerCustomModel::create
        ),
        CustomModelType.CRAB to CustomModelSpec(
            type = CustomModelType.CRAB,
            displayName = "Crab",
            textureResource = "custommodels/crab.png",
            modelFactory = CrabCustomModel::create
        ),
        CustomModelType.CHINCHILLA to CustomModelSpec(
            type = CustomModelType.CHINCHILLA,
            displayName = "Chinchilla",
            textureResource = "custommodels/chinchilla.png",
            modelFactory = ChinchillaCustomModel::create,
            transform = CustomModelTransform(1.4f, 1.4f, 1.4f, 0.0f, -0.45f, 0.0f)
        )
    )

    operator fun get(type: CustomModelType): CustomModelSpec = specs.getValue(type)

    private fun rabbitHeldItemTransform(arm: HumanoidArm): FloatArray {
        val left = arm == HumanoidArm.LEFT
        return floatArrayOf(
            ((if (left) -4.0f else 0.9f) / 16.0f) + 0.1f,
            0.125f,
            -0.9f
        )
    }
}
