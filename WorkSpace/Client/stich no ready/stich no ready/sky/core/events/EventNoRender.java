package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventNoRender extends EventCancellable implements Event {
   public final NorenderType norenderType;

   public EventNoRender(NorenderType norenderType) {
      this.norenderType = norenderType;
   }

   public NorenderType getNorenderType() {
      return this.norenderType;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventNoRender)) {
         return false;
      } else {
         EventNoRender other = (EventNoRender)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$norenderType = this.getNorenderType();
            Object other$norenderType = other.getNorenderType();
            if (this$norenderType == null) {
               if (other$norenderType != null) {
                  return false;
               }
            } else if (!this$norenderType.equals(other$norenderType)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventNoRender;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      Object $norenderType = this.getNorenderType();
      result = result * 59 + ($norenderType == null ? 43 : $norenderType.hashCode());
      return result;
   }

   public String toString() {
      return "EventNoRender(norenderType=" + String.valueOf(this.getNorenderType()) + ")";
   }

   public static enum NorenderType {
      fire,
      bossbar,
      scoreboard,
      title,
      totem,
      hurttime,
      fishingrod,
      destroyparticles,
      rain,
      shadows,
      smoke,
      vignette,
      arrows,
      holograms,
      grass,
      health,
      players,
      bad_effects,
      underwater_blur,
      container_background,
      lava,
      tab,
      cameraclip,
      glowing;

      // $FF: synthetic method
      private static NorenderType[] $values() {
         return new NorenderType[]{fire, bossbar, scoreboard, title, totem, hurttime, fishingrod, destroyparticles, rain, shadows, smoke, vignette, arrows, holograms, grass, health, players, bad_effects, underwater_blur, container_background, lava, tab, cameraclip, glowing};
      }
   }
}
