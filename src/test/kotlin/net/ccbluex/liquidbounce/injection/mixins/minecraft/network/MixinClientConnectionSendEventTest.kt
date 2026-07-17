package net.ccbluex.liquidbounce.injection.mixins.minecraft.network

import java.nio.file.Files
import java.nio.file.Path
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MixinClientConnectionSendEventTest {

    @Test
    fun preObservationStaysAtHeadAndPostObservationHasOneTailDispatch() {
        val source = Files.readString(
            Path.of("src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/network/MixinClientConnection.java")
        )

        val head = source.indexOf("at = @At(\"HEAD\"), cancellable = true")
        val pre = source.indexOf("new OutgoingPacketSentEvent(packet)")
        val tail = source.indexOf("at = @At(\"TAIL\")")
        val post = source.indexOf("new OutgoingPacketPostSendEvent(packet)")

        assertTrue(head >= 0 && head < pre)
        assertTrue(pre < tail && tail < post)
        assertEquals(1, Regex("new OutgoingPacketPostSendEvent\\(packet\\)").findAll(source).count())
    }
}
