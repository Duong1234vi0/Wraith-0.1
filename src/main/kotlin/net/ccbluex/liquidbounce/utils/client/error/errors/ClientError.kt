package net.ccbluex.liquidbounce.utils.client.error.errors
import net.ccbluex.liquidbounce.utils.entity.*

open class ClientError(
    message: String = "",
    val needToReport: Boolean = true
) : RuntimeException(message)
