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
@file:OptIn(FlowPreview::class)

package net.ccbluex.liquidbounce.features.module.modules.render
import net.ccbluex.liquidbounce.utils.entity.*

import com.mojang.authlib.GameProfile
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import net.ccbluex.liquidbounce.authlib.utils.generateOfflinePlayerUuid
import net.ccbluex.liquidbounce.authlib.yggdrasil.GameProfileRepository
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.client.withScope
import net.minecraft.client.multiplayer.PlayerInfo
import net.minecraft.world.entity.player.PlayerSkin
import java.util.function.Supplier
import kotlin.time.Duration.Companion.seconds

object ModuleSkinChanger : ClientModule("SkinChanger", Category.RENDER) {

    private val username = text("Username", "LiquidBounce")
        .apply(::tagBy)

    init {
        withScope {
            username.asStateFlow().debounce { 2.seconds }.collectLatest { username ->
                while (mc.player == null) { delay(1.seconds) }
                skinTextures = textureSupplier(username)
            }
        }
    }

    @Volatile
    var skinTextures: Supplier<PlayerSkin>? = null
        private set

    private fun textureSupplier(username: String): Supplier<PlayerSkin> {
        val uuid = GameProfileRepository().fetchUuidByUsername(username)
            ?: generateOfflinePlayerUuid(username)
        val profile = mc.services().sessionService().fetchProfile(uuid, false)?.profile
            ?: GameProfile(uuid, username)

        return PlayerInfo.createSkinLookup(profile)
    }

}
