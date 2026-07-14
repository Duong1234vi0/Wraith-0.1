package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;

public class EventClickBlockRight extends EventCancellable implements Event {
   private final ClientPlayerEntity player;
   private final ClientWorld world;
   private final Hand hand;
   private final BlockRayTraceResult result;

   public ClientPlayerEntity getPlayer() {
      return this.player;
   }

   public ClientWorld getWorld() {
      return this.world;
   }

   public Hand getHand() {
      return this.hand;
   }

   public BlockRayTraceResult getResult() {
      return this.result;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventClickBlockRight)) {
         return false;
      } else {
         EventClickBlockRight other = (EventClickBlockRight)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            label59: {
               Object this$player = this.getPlayer();
               Object other$player = other.getPlayer();
               if (this$player == null) {
                  if (other$player == null) {
                     break label59;
                  }
               } else if (this$player.equals(other$player)) {
                  break label59;
               }

               return false;
            }

            Object this$world = this.getWorld();
            Object other$world = other.getWorld();
            if (this$world == null) {
               if (other$world != null) {
                  return false;
               }
            } else if (!this$world.equals(other$world)) {
               return false;
            }

            Object this$hand = this.getHand();
            Object other$hand = other.getHand();
            if (this$hand == null) {
               if (other$hand != null) {
                  return false;
               }
            } else if (!this$hand.equals(other$hand)) {
               return false;
            }

            Object this$result = this.getResult();
            Object other$result = other.getResult();
            if (this$result == null) {
               if (other$result != null) {
                  return false;
               }
            } else if (!this$result.equals(other$result)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventClickBlockRight;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      Object $player = this.getPlayer();
      result = result * 59 + ($player == null ? 43 : $player.hashCode());
      Object $world = this.getWorld();
      result = result * 59 + ($world == null ? 43 : $world.hashCode());
      Object $hand = this.getHand();
      result = result * 59 + ($hand == null ? 43 : $hand.hashCode());
      Object $result = this.getResult();
      result = result * 59 + ($result == null ? 43 : $result.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getPlayer());
      return "EventClickBlockRight(player=" + var10000 + ", world=" + String.valueOf(this.getWorld()) + ", hand=" + String.valueOf(this.getHand()) + ", result=" + String.valueOf(this.getResult()) + ")";
   }

   public EventClickBlockRight(ClientPlayerEntity player, ClientWorld world, Hand hand, BlockRayTraceResult result) {
      this.player = player;
      this.world = world;
      this.hand = hand;
      this.result = result;
   }
}
