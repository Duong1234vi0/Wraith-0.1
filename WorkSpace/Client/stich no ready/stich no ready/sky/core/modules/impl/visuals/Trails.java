package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import sky.core.events.EventRender3D;
import sky.core.events.EventSwapWorld;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.misc.TargetUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.ProjectUtil;

public class Trails extends Module {
   private final MultiBooleanSetting rendering = new MultiBooleanSetting("Отображать", new BooleanSetting[]{new BooleanSetting("Игроков", false), new BooleanSetting("Друзей", true), new BooleanSetting("Себя", true), new BooleanSetting("Невидимых", false)});
   private final SliderSetting size = new SliderSetting("Длина", 2.5F, 2.0F, 4.0F, 0.5F);
   private final Map trailsByEntity = new HashMap();

   public Trails() {
      super("Trails", "Рисует шлейф по последним позициям существ", Category.Visuals);
      this.addSettings(new Setting[]{this.rendering, this.size});
   }

   @EventTarget
   public void onEvent(EventRender3D event) {
      RenderSystem.pushMatrix();
      RenderSystem.shadeModel(7425);
      RenderSystem.enableBlend();
      RenderSystem.disableCull();
      RenderSystem.blendFunc(770, 771);
      RenderSystem.disableAlphaTest();
      RenderSystem.depthMask(false);
      RenderSystem.disableTexture();
      double camX = mc.getRenderManager().info.getProjectedView().x;
      double camY = mc.getRenderManager().info.getProjectedView().y;
      double camZ = mc.getRenderManager().info.getProjectedView().z;
      RenderSystem.translated(-camX, -camY, -camZ);
      float partialTicks = event.getPartialTicks();
      float sizeValue = (Float)this.size.get() * 100.0F;
      BUFFER.begin(7, DefaultVertexFormats.POSITION_COLOR);
      Iterator var10 = mc.world.getAllEntities().iterator();

      while(true) {
         LivingEntity living;
         float heightOffset;
         List list;
         float height;
         do {
            Entity entity;
            do {
               do {
                  if (!var10.hasNext()) {
                     TESSELLATOR.draw();
                     RenderSystem.enableAlphaTest();
                     RenderSystem.enableCull();
                     RenderSystem.depthMask(true);
                     RenderSystem.disableBlend();
                     RenderSystem.enableTexture();
                     RenderSystem.shadeModel(7424);
                     RenderSystem.popMatrix();
                     RenderSystem.clearCurrentColor();
                     return;
                  }

                  entity = (Entity)var10.next();
               } while(!this.isValidTarget(entity));
            } while(!(entity instanceof LivingEntity));

            living = (LivingEntity)entity;
            double posX = living.lastTickPosX + (living.getPosX() - living.lastTickPosX) * (double)partialTicks;
            double posY = living.lastTickPosY + (living.getPosY() - living.lastTickPosY) * (double)partialTicks + 0.05 - (living.isElytraFlying() ? 0.15 : 0.0);
            double posZ = living.lastTickPosZ + (living.getPosZ() - living.lastTickPosZ) * (double)partialTicks;
            Minecraft var10001 = mc;
            boolean isFirstPerson = living == Minecraft.player && mc.gameSettings.getPointOfView() == PointOfView.FIRST_PERSON;
            if (!isFirstPerson) {
               List list = (List)this.trailsByEntity.computeIfAbsent(living.getEntityId(), (id) -> {
                  return new ArrayList();
               });
               list.add(new Trail(posX, posY, posZ));
            }

            height = living.getHeight() * (living.isSneaking() ? 0.8F : 1.0F);
            heightOffset = height * 0.01F;
            list = (List)this.trailsByEntity.get(living.getEntityId());
         } while(list == null);

         Trail prevTrail = null;
         boolean isFirst = true;
         int prevColor = 0;
         Iterator it = list.iterator();

         while(it.hasNext()) {
            Trail trail = (Trail)it.next();
            if ((float)(System.currentTimeMillis() - trail.time) > sizeValue) {
               it.remove();
            } else {
               trail.alpha = MathHelper.clamp((float)(System.currentTimeMillis() - trail.time) / sizeValue, 0.0F, 1.0F) * 255.0F;
               int color = ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL), (int)((float)(255 * (255 - Math.round(trail.alpha))) / 220.0F));
               if (isFirst) {
                  BUFFER.pos(trail.x, trail.y, trail.z).color(color).endVertex();
                  BUFFER.pos(trail.x, trail.y + (double)height, trail.z).color(color).endVertex();
               } else {
                  BUFFER.pos(prevTrail.x, prevTrail.y, prevTrail.z).color(prevColor).endVertex();
                  BUFFER.pos(prevTrail.x, prevTrail.y + (double)height, prevTrail.z).color(prevColor).endVertex();
               }

               BUFFER.pos(trail.x, trail.y + (double)height, trail.z).color(color).endVertex();
               BUFFER.pos(trail.x, trail.y, trail.z).color(color).endVertex();
               if (isFirst) {
                  BUFFER.pos(trail.x, trail.y, trail.z).color(color).endVertex();
                  BUFFER.pos(trail.x, trail.y + (double)heightOffset, trail.z).color(color).endVertex();
               } else {
                  BUFFER.pos(prevTrail.x, prevTrail.y, prevTrail.z).color(prevColor).endVertex();
                  BUFFER.pos(prevTrail.x, prevTrail.y + (double)heightOffset, prevTrail.z).color(prevColor).endVertex();
               }

               BUFFER.pos(trail.x, trail.y + (double)heightOffset, trail.z).color(color).endVertex();
               BUFFER.pos(trail.x, trail.y, trail.z).color(color).endVertex();
               if (isFirst) {
                  BUFFER.pos(trail.x, trail.y + (double)height, trail.z).color(color).endVertex();
                  BUFFER.pos(trail.x, trail.y + (double)height - (double)heightOffset, trail.z).color(color).endVertex();
               } else {
                  BUFFER.pos(prevTrail.x, prevTrail.y + (double)height, prevTrail.z).color(prevColor).endVertex();
                  BUFFER.pos(prevTrail.x, prevTrail.y + (double)height - (double)heightOffset, prevTrail.z).color(prevColor).endVertex();
               }

               BUFFER.pos(trail.x, trail.y + (double)height - (double)heightOffset, trail.z).color(color).endVertex();
               BUFFER.pos(trail.x, trail.y + (double)height, trail.z).color(color).endVertex();
               isFirst = false;
               prevTrail = trail;
               prevColor = color;
            }
         }

         if (list.isEmpty()) {
            this.trailsByEntity.remove(living.getEntityId());
         }
      }
   }

   private boolean isValidTarget(Entity entity) {
      if (entity.isAlive() && ProjectUtil.isInView(entity)) {
         if (entity.isInvisible() && !this.rendering.is("Невидимых")) {
            return false;
         } else if (entity instanceof PlayerEntity) {
            Minecraft var10001 = mc;
            return entity == Minecraft.player ? TargetUtil.isSelfTarget(entity, this.rendering) : TargetUtil.isPlayerTarget(entity, this.rendering, false);
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   @EventTarget
   public void onEvent(EventSwapWorld e) {
      this.trailsByEntity.clear();
   }

   public void onDisable() {
      this.trailsByEntity.clear();
      super.onDisable();
   }

   public static final class Trail {
      final double x;
      final double y;
      final double z;
      float alpha;
      final long time;

      public Trail(double x, double y, double z) {
         this.x = x;
         this.y = y;
         this.z = z;
         this.time = System.currentTimeMillis();
      }
   }
}
