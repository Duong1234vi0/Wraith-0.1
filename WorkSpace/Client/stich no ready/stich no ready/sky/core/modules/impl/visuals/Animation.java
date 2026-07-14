package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicLong;
import net.minecraft.client.Minecraft;
import sky.core.events.EventRenderChunk;
import sky.core.events.EventRenderChunkContainer;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.utils.animation.Easing;
import sky.core.utils.animation.Easings;

public class Animation extends Module {
   public MultiBooleanSetting mode = new MultiBooleanSetting("Что анимировать", new BooleanSetting[]{new BooleanSetting("Список игроков", false), new BooleanSetting("Инвентарь", false), new BooleanSetting("Приближение камеры", false), new BooleanSetting("Обновление чанков", false), new BooleanSetting("Изменение перспективы", false)});
   public ModeSetting chunkanim = new ModeSetting("Анимация чанков", "Quart", () -> {
      return this.mode.is("Обновление чанков");
   }, new String[]{"Quart", "Circ", "Sine", "Cubic"});
   private final SliderSetting chunkSpeed = new SliderSetting("Скорость", 6.0F, 2.0F, 10.0F, 1.0F, () -> {
      return this.mode.is("Обновление чанков");
   });
   private final WeakHashMap renderChunkMap = new WeakHashMap();

   public Animation() {
      super("Animation", "Анимирует выбранные действия", Category.Visuals);
      this.addSettings(new Setting[]{this.mode, this.chunkanim, this.chunkSpeed});
   }

   private double applySelectedEasing(double t) {
      String modeName = (String)this.chunkanim.get();
      Easing easing;
      if ("Circ".equalsIgnoreCase(modeName)) {
         easing = Easings.CIRC_OUT;
      } else if ("Sine".equalsIgnoreCase(modeName)) {
         easing = Easings.SINE_OUT;
      } else if ("Cubic".equalsIgnoreCase(modeName)) {
         easing = Easings.CUBIC_OUT;
      } else {
         easing = Easings.QUART_OUT;
      }

      return easing.ease(t);
   }

   @EventTarget
   private void onEvent(EventRenderChunk event) {
      if (Boolean.TRUE.equals(this.mode.is("Обновление чанков"))) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null && mc.world != null && !this.renderChunkMap.containsKey(event.getChunkRender())) {
            this.renderChunkMap.put(event.getChunkRender(), new AtomicLong(-1L));
         }

      }
   }

   @EventTarget
   private void onEvent(EventRenderChunkContainer event) {
      if (Boolean.TRUE.equals(this.mode.is("Обновление чанков"))) {
         if (this.renderChunkMap.containsKey(event.getChunkRender())) {
            AtomicLong timeAlive = (AtomicLong)this.renderChunkMap.get(event.getChunkRender());
            long timeClone = timeAlive.get();
            if (timeClone == -1L) {
               timeClone = System.currentTimeMillis();
               timeAlive.set(timeClone);
            }

            long timeDifference = System.currentTimeMillis() - timeClone;
            double durationMs = (double)((Float)this.chunkSpeed.get() * 100.0F);
            if ((double)timeDifference <= durationMs) {
               double chunkY = (double)event.getChunkRender().getPosition().getY();
               double t = (double)timeDifference / durationMs;
               double offsetY = chunkY * this.applySelectedEasing(t);
               RenderSystem.translated(0.0, -chunkY + offsetY, 0.0);
            }
         }

      }
   }
}
