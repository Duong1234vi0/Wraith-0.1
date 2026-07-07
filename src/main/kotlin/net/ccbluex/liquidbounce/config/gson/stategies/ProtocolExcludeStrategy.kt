package net.ccbluex.liquidbounce.config.gson.stategies
import net.ccbluex.liquidbounce.utils.entity.*

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class ProtocolExclude

object ProtocolExcludeStrategy : ExclusionStrategy {
    override fun shouldSkipClass(clazz: Class<*>?) = false
    override fun shouldSkipField(field: FieldAttributes) = field.getAnnotation(ProtocolExclude::class.java) != null
}
