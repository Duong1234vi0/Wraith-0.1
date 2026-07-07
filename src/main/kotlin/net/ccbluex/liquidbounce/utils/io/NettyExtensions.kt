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
package net.ccbluex.liquidbounce.utils.io
import net.ccbluex.liquidbounce.utils.entity.*

import io.netty.bootstrap.AbstractBootstrap
import io.netty.channel.Channel
import io.netty.channel.EventLoopGroup
import io.netty.channel.epoll.Epoll
import io.netty.channel.epoll.EpollEventLoopGroup
import io.netty.channel.epoll.EpollSocketChannel
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel

private val EPOLL_CLIENT_IO_GROUP: EventLoopGroup by lazy { EpollEventLoopGroup() }
private val CLIENT_IO_GROUP: EventLoopGroup by lazy { NioEventLoopGroup() }

/**
 * Shortcut for Netty client [io.netty.bootstrap.Bootstrap],
 * using shared [io.netty.channel.EventLoopGroup] from [Connection]
 */
fun <B : AbstractBootstrap<B, Channel>> AbstractBootstrap<B, Channel>.clientChannelAndGroup(
    tryToUseEpoll: Boolean = true
): B =
    if (Epoll.isAvailable() && tryToUseEpoll) {
        channelFactory(::EpollSocketChannel)
            .group(EPOLL_CLIENT_IO_GROUP)
    } else {
        channelFactory(::NioSocketChannel)
            .group(CLIENT_IO_GROUP)
    }
