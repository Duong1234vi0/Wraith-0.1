package sky.core.utils;

import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CCustomPayloadPacket;
import net.minecraft.util.ResourceLocation;

public class OptOutPacket {
   public static final ResourceLocation CHANNEL = new ResourceLocation("marlow", "mco");

   public CCustomPayloadPacket getRawPacket() {
      PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
      return new CCustomPayloadPacket(CHANNEL, buffer);
   }
}
