package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceContext.BlockMode;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import org.lwjgl.opengl.GL11;
import sky.core.SkyCore;
import sky.core.events.EventRender2D;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ColorSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.utils.misc.OtherUtil;
import sky.core.utils.misc.TargetUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;

public class Arrows extends Module {
   private final MultiBooleanSetting rendering = new MultiBooleanSetting("Отображать", new BooleanSetting[]{new BooleanSetting("Игроков", true), new BooleanSetting("Друзей", true), new BooleanSetting("Голых", false), new BooleanSetting("Предметы", false)});
   private final ColorSetting playerColor = new ColorSetting("Цвет игроков", true, -1, () -> {
      return this.rendering.is("Игроков") || this.rendering.is("Голых");
   });
   private final ColorSetting friendColor = new ColorSetting("Цвет друзей", true, ColorUtil.getColor(0, 255, 0), () -> {
      return this.rendering.is("Друзей");
   });
   private final ColorSetting itemColor = new ColorSetting("Цвет предметов", true, ColorUtil.getColor(255, 0, 0), () -> {
      return this.rendering.is("Предметы");
   });
   private final SliderSetting radius = new SliderSetting("Радиус", 40.0F, 30.0F, 80.0F, 5.0F);
   private final BooleanSetting onlyIfNotVisible = new BooleanSetting("Показывать только если не видишь", false);
   public final ModeSetting type = new ModeSetting("Тип стрелки", "Необычная", new String[]{"Необычная", "Заливка"});
   private static final float ARROW_SIZE = 20.0F;
   private static final ResourceLocation ARROW_UNUSUAL = new ResourceLocation("SkyCore/icons/world_render/triangle.png");
   private static final ResourceLocation ARROW_FILLED = new ResourceLocation("SkyCore/icons/world_render/arrow.png");

   public Arrows() {
      super("Arrows", "Рисует указатели на позиции разных существ", Category.Visuals);
      this.addSettings(new Setting[]{this.rendering, this.playerColor, this.friendColor, this.itemColor, this.onlyIfNotVisible, this.radius, this.type});
   }

   @EventTarget
   private void onRender(EventRender2D eventRender2D) {
      if (!mc.gameSettings.showDebugInfo) {
         double cos = Math.cos(Math.toRadians((double)mc.gameRenderer.getActiveRenderInfo().getYaw()));
         double sin = Math.sin(Math.toRadians((double)mc.gameRenderer.getActiveRenderInfo().getYaw()));
         Vector2f fixed = OtherUtil.getMouse(mc.getMainWindow().getScaledWidth(), mc.getMainWindow().getScaledHeight());
         double baseRadius = (double)(Float)this.radius.get();
         ResourceLocation arrowTexture = this.type.is("Необычная") ? ARROW_UNUSUAL : ARROW_FILLED;
         Iterator var10 = mc.world.getAllEntities().iterator();

         while(var10.hasNext()) {
            Entity entity = (Entity)var10.next();
            if (this.isValidEntity(entity)) {
               double adjustedRadius = entity instanceof ItemEntity ? Math.max(baseRadius - 20.0, 0.0) : baseRadius;
               double xOffset = (double)(fixed.getX() / 2.0F) - adjustedRadius;
               double yOffset = (double)(fixed.getY() / 2.0F) - adjustedRadius;
               Vector3d vector3d = mc.gameRenderer.getActiveRenderInfo().getProjectedView();
               double xWay = (entity.getPosX() + (entity.getPosX() - entity.lastTickPosX) * (double)mc.getRenderPartialTicks() - vector3d.x) * 0.01;
               double zWay = (entity.getPosZ() + (entity.getPosZ() - entity.lastTickPosZ) * (double)mc.getRenderPartialTicks() - vector3d.z) * 0.01;
               double rotationY = -(zWay * cos - xWay * sin);
               double rotationX = -(xWay * cos + zWay * sin);
               double angle = Math.toDegrees(Math.atan2(rotationY, rotationX));
               double x = adjustedRadius * Math.cos(Math.toRadians(angle)) + xOffset + adjustedRadius;
               double y = adjustedRadius * Math.sin(Math.toRadians(angle)) + yOffset + adjustedRadius;
               if (this.isValidRotation(rotationX, rotationY, adjustedRadius)) {
                  GL11.glPushMatrix();
                  GL11.glTranslated(x, y, 0.0);
                  GL11.glRotated(angle, 0.0, 0.0, 1.0);
                  int selectedColor = this.determineEntityColor(entity);
                  RenderUtil.drawImage2D(arrowTexture, -10.0F, -10.0F, 20.0F, 20.0F, selectedColor);
                  GL11.glPopMatrix();
               }
            }
         }

      }
   }

   private boolean isValidEntity(Entity entity) {
      boolean isPlayerOrItem = TargetUtil.isPlayerTarget(entity, this.rendering, true) || TargetUtil.isItemTarget(entity, this.rendering);
      if (isPlayerOrItem && entity.isAlive()) {
         Minecraft var10001 = mc;
         if (entity != Minecraft.player) {
            return !(Boolean)this.onlyIfNotVisible.get() || !this.isEntityVisible(entity);
         }
      }

      return false;
   }

   private boolean isEntityVisible(Entity entity) {
      Minecraft var10000 = mc;
      Vector3d start = Minecraft.player.getEyePosition(mc.getRenderPartialTicks());
      Vector3d end = entity.getPositionVec().add(0.0, (double)(entity.getHeight() / 2.0F), 0.0);
      Minecraft var10006 = mc;
      RayTraceContext context = new RayTraceContext(start, end, BlockMode.COLLIDER, FluidMode.NONE, Minecraft.player);
      RayTraceResult result = mc.world.rayTraceBlocks(context);
      return result.getType() == Type.MISS;
   }

   private int determineEntityColor(Entity entity) {
      if (entity instanceof PlayerEntity player) {
         boolean isFriend = SkyCore.getInstance().getFriendManager().isFriend(player.getGameProfile().getName());
         boolean isNaked = player.getTotalArmorValue() == 0;
         if (isFriend) {
            return (Integer)this.friendColor.get();
         } else {
            return isNaked ? (Integer)this.playerColor.get() : (Integer)this.playerColor.get();
         }
      } else {
         return entity instanceof ItemEntity ? (Integer)this.itemColor.get() : (Integer)this.playerColor.get();
      }
   }

   private boolean isValidRotation(double rotationX, double rotationY, double radius) {
      double mrotY = -rotationY;
      double mrotX = -rotationX;
      return (double)MathHelper.sqrt(mrotX * mrotX + mrotY * mrotY) < radius;
   }
}
