package net.ccbluex.liquidbounce.utils.client.error.errors
import net.ccbluex.liquidbounce.utils.entity.*

object JcefIsntCompatible : ClientError(
    message = "JCEF Isn't compatible",
    needToReport = false
) {
    fun readResolve(): Any = JcefIsntCompatible
}
