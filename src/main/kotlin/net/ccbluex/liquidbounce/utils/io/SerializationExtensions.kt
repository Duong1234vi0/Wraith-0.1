package net.ccbluex.liquidbounce.utils.io
import net.ccbluex.liquidbounce.utils.entity.*

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import net.minecraft.world.phys.Vec3

fun Vec3.toJson(): JsonElement {
    return JsonArray(3).apply {
        add(x)
        add(y)
        add(z)
    }
}
