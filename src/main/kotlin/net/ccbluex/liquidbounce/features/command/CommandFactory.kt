package net.ccbluex.liquidbounce.features.command
import net.ccbluex.liquidbounce.utils.entity.*

/**
 * Provides a [Command] to the [CommandManager].
 */
interface CommandFactory {

    /**
     * Creates the [Command] and is run only once by the [CommandManager].
     */
    fun createCommand(): Command

}
