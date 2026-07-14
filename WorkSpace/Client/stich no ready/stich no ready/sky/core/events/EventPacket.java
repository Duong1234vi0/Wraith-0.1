package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.network.IPacket;

public class EventPacket extends EventCancellable implements Event {
   private final IPacket packet;
   private final PacketType packetType;

   public boolean isReceive() {
      return this.packetType == EventPacket.PacketType.RECEIVE;
   }

   public boolean isSend() {
      return this.packetType == EventPacket.PacketType.SEND;
   }

   public IPacket getPacket() {
      return this.packet;
   }

   public PacketType getPacketType() {
      return this.packetType;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventPacket)) {
         return false;
      } else {
         EventPacket other = (EventPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$packet = this.getPacket();
            Object other$packet = other.getPacket();
            if (this$packet == null) {
               if (other$packet != null) {
                  return false;
               }
            } else if (!this$packet.equals(other$packet)) {
               return false;
            }

            Object this$packetType = this.getPacketType();
            Object other$packetType = other.getPacketType();
            if (this$packetType == null) {
               if (other$packetType != null) {
                  return false;
               }
            } else if (!this$packetType.equals(other$packetType)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventPacket;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      Object $packet = this.getPacket();
      result = result * 59 + ($packet == null ? 43 : $packet.hashCode());
      Object $packetType = this.getPacketType();
      result = result * 59 + ($packetType == null ? 43 : $packetType.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getPacket());
      return "EventPacket(packet=" + var10000 + ", packetType=" + String.valueOf(this.getPacketType()) + ")";
   }

   public EventPacket(IPacket packet, PacketType packetType) {
      this.packet = packet;
      this.packetType = packetType;
   }

   public static enum PacketType {
      RECEIVE,
      SEND;

      // $FF: synthetic method
      private static PacketType[] $values() {
         return new PacketType[]{RECEIVE, SEND};
      }
   }
}
