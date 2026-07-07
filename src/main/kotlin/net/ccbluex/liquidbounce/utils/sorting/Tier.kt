package net.ccbluex.liquidbounce.utils.sorting
import net.ccbluex.liquidbounce.utils.entity.*

enum class Tier: Comparable<Tier> {
    F, E, D, C, B, A, S;

    val score: Int = this.ordinal
}
