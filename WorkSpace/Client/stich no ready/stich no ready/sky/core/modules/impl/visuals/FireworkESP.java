package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlaySoundEffectPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import org.joml.Vector2f;
import sky.core.events.EventPacket;
import sky.core.events.EventRender2D;
import sky.core.events.EventSwapWorld;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.animation.Easings;
import sky.core.utils.math.TimeUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.ProjectUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class FireworkESP extends Module {
   private final BooleanSetting time = new BooleanSetting("Отображать время", true);
   private final List fireworks = new CopyOnWriteArrayList();

   public FireworkESP() {
      super("Firework ESP", "Отображает где был использован фейерверк", Category.Visuals);
      this.addSettings(new Setting[]{this.time});
   }

   @EventTarget
   public void onPacket(EventPacket eventPacket) {
      IPacket var3 = eventPacket.getPacket();
      if (var3 instanceof SPlaySoundEffectPacket packet) {
         if (packet.getCategory() == SoundCategory.AMBIENT && packet.getSound() == SoundEvents.ENTITY_FIREWORK_ROCKET_LAUNCH) {
            Vector3d newPosition = new Vector3d(packet.getX(), packet.getY(), packet.getZ());
            if (!this.hasActiveFireworkInRadius(newPosition, 5.0)) {
               this.fireworks.add(new Firework(newPosition));
            }
         }
      }

   }

   @EventTarget
   public void onEvent(EventRender2D event) {
      if (!this.fireworks.isEmpty()) {
         this.updateAndCleanFireworks();
         Iterator var2 = this.fireworks.iterator();

         while(var2.hasNext()) {
            Firework firework = (Firework)var2.next();
            firework.updateAlpha();
            Vector3d pos = firework.getPosition();
            Vector2f screenPos = ProjectUtil.project2D(pos.x, pos.y, pos.z);
            if (screenPos.x != Float.MAX_VALUE && screenPos.y != Float.MAX_VALUE) {
               this.renderFirework(event, firework, screenPos, (Boolean)this.time.get());
            }
         }

      }
   }

   private void renderFirework(EventRender2D event, Firework firework, Vector2f screenPos, boolean showTime) {
      float posX = screenPos.x - 20.0F;
      float posY = screenPos.y;
      RenderUtil.drawMinecraftRectangle(event.getStack(), posX, posY, showTime ? Fonts.sf_medium[14].getWidth(String.format("%.1f сек.", Math.max(0.0, (double)(firework.getLifetimeMs() - firework.timer.getTimePassed()) / 1000.0))) + 14.0F : 10.0F, 11.0F, ColorUtil.getColor(0, 0, 0, (int)(120.0F * firework.getAlpha())));
      RenderUtil.drawImage2D(new ResourceLocation("textures/item/firework_rocket.png"), posX + 0.5F, posY + 1.5F, 8.0F, 8.0F, ColorUtil.getColor(255, 255, 255, (int)(255.0F * firework.getAlpha())));
      if (showTime) {
         Fonts.sf_medium[14].drawString(event.getStack(), String.format("%.1f сек.", Math.max(0.0, (double)(firework.getLifetimeMs() - firework.timer.getTimePassed()) / 1000.0)), (double)(posX + 11.0F), (double)(posY + 4.0F), ColorUtil.getColor(255, 255, 255, (int)(255.0F * firework.getAlpha())));
      }

   }

   private boolean hasActiveFireworkInRadius(Vector3d position, double radius) {
      double radiusSquared = radius * radius;
      return this.fireworks.stream().filter(Firework::isActive).anyMatch((firework) -> {
         double distanceSquared = position.squareDistanceTo(firework.getPosition());
         return distanceSquared <= radiusSquared;
      });
   }

   private void updateAndCleanFireworks() {
      this.fireworks.removeIf((firework) -> {
         if (firework.timer.getTimePassed() >= firework.getLifetimeMs()) {
            if (!firework.isFading()) {
               firework.startFade();
            }

            return firework.shouldRemove();
         } else {
            return false;
         }
      });
   }

   @EventTarget
   public void onEvent(EventSwapWorld event) {
      this.fireworks.clear();
   }

   public void onDisable() {
      this.fireworks.clear();
      super.onDisable();
   }

   public static class Firework {
      private final Vector3d position;
      private final TimeUtil timer = new TimeUtil();
      private final long lifetimeMs = 5000L;
      private boolean fading = false;
      private final AnimationUtil alpha;

      public Firework(Vector3d position) {
         this.alpha = new AnimationUtil(1.0F, 6.0F, Easings.LINEAR);
         this.position = position;
      }

      public void startFade() {
         if (!this.fading) {
            this.fading = true;
            this.alpha.update(0.0F);
         }

      }

      public boolean shouldRemove() {
         return this.fading && this.alpha.isDone();
      }

      public float getAlpha() {
         return this.alpha.getValue();
      }

      public void updateAlpha() {
         if (this.fading) {
            this.alpha.update(0.0F);
         }

      }

      public boolean isActive() {
         return !this.fading || this.getAlpha() > 0.1F;
      }

      public Vector3d getPosition() {
         return this.position;
      }

      public TimeUtil getTimer() {
         return this.timer;
      }

      public long getLifetimeMs() {
         Objects.requireNonNull(this);
         return 5000L;
      }

      public boolean isFading() {
         return this.fading;
      }
   }
}
