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
package net.ccbluex.liquidbounce.utils.entity
import net.ccbluex.liquidbounce.utils.entity.*

import net.minecraft.client.player.ClientInput
import net.minecraft.world.entity.player.Input

val Input.any: Boolean
    get() = forward || backward || left || right

val Input.sneak: Boolean
    get() = shift

var ClientInput.playerInput: Input
    get() = keyPresses
    set(value) {
        keyPresses = value
    }

val ClientInput.movementForward: Float
    get() = getMoveVector().y

val ClientInput.movementSideways: Float
    get() = getMoveVector().x

@Suppress("LongParameterList")
fun Input.copy(
    forward: Boolean = this.forward,
    backward: Boolean = this.backward,
    left: Boolean = this.left,
    right: Boolean = this.right,
    jump: Boolean = this.jump,
    sneak: Boolean = this.shift,
    sprint: Boolean = this.sprint
): Input {
    return Input(
        forward,
        backward,
        left,
        right,
        jump,
        sneak,
        sprint
    )
}

@Suppress("LongParameterList")
fun ClientInput.set(
    forward: Boolean = playerInput.forward,
    backward: Boolean = playerInput.backward,
    left: Boolean = playerInput.left,
    right: Boolean = playerInput.right,
    jump: Boolean = playerInput.jump,
    sneak: Boolean = playerInput.sneak,
    sprint: Boolean = playerInput.sprint
) {
    this.playerInput = Input(
        forward,
        backward,
        left,
        right,
        jump,
        sneak,
        sprint
    )
}
