package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Items;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import org.lwjgl.opengl.GL11;
import sky.core.events.EventRender3D;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;

public class Wings extends Module {
   private static final float DEFAULT_SPREAD = 8.0F;
   private static final int DEFAULT_ALPHA = 220;
   private static final float[][] VECTOR_SHAPE = new float[][]{{0.08F, 0.1F, 0.88F}, {0.28F, 0.34F, 0.78F}, {0.56F, 0.82F, 0.62F}, {0.86F, 0.3F, 0.52F}, {1.14F, 0.46F, 0.4F}, {1.24F, 0.04F, 0.3F}, {1.02F, -0.18F, 0.28F}, {1.18F, -0.64F, 0.22F}, {0.86F, -0.46F, 0.2F}, {0.8F, -0.98F, 0.14F}, {0.54F, -0.74F, 0.16F}, {0.3F, -1.16F, 0.12F}, {0.1F, -0.54F, 0.18F}};
   private final ModeSetting mode = new ModeSetting("Вид", "Обычные", new String[]{"Обычные"});
   private final BooleanSetting self = new BooleanSetting("На себя", true);
   private final BooleanSetting players = new BooleanSetting("На игроков", false);
   private final BooleanSetting fill = new BooleanSetting("Заливка", true);
   private final SliderSetting opacity = new SliderSetting("Прозрачность", 220.0F, 10.0F, 255.0F, 5.0F);
   private final SliderSetting size = new SliderSetting("Размер", 1.0F, 0.5F, 2.0F, 0.05F);
   private float selfBodyYaw;
   private boolean selfBodyYawInitialized;

   public Wings() {
      super("Wings", "Полупрозрачные крылья за спиной", Category.Visuals);
      this.addSettings(new Setting[]{this.mode, this.self, this.players, this.fill, this.opacity, this.size});
   }

   @EventTarget
   public void onRender3D(EventRender3D event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         float tickDelta = event.getPartialTicks();
         Vector3d camera = mc.getRenderManager().info.getProjectedView();
         GL11.glPushMatrix();
         GL11.glEnable(3042);
         GL11.glDisable(3553);
         GL11.glDisable(2884);
         GL11.glDisable(3008);
         GL11.glEnable(2929);
         GL11.glDepthMask(false);
         Minecraft var10001;
         if ((Boolean)this.self.get() && mc.gameSettings.getPointOfView() != PointOfView.FIRST_PERSON) {
            var10000 = mc;
            if (Minecraft.player.isAlive()) {
               var10001 = mc;
               if (!this.hasElytra(Minecraft.player)) {
                  try {
                     var10001 = mc;
                     this.renderVectorWings(Minecraft.player, tickDelta, camera);
                  } catch (Exception var9) {
                  }
               }
            }
         }

         if ((Boolean)this.players.get()) {
            Iterator var4 = mc.world.getAllEntities().iterator();

            while(var4.hasNext()) {
               Entity entity = (Entity)var4.next();
               if (entity instanceof PlayerEntity) {
                  PlayerEntity player = (PlayerEntity)entity;
                  var10001 = mc;
                  if (player != Minecraft.player && player.isAlive() && !this.hasElytra(player)) {
                     try {
                        this.renderVectorWings(player, tickDelta, camera);
                     } catch (Exception var8) {
                     }
                  }
               }
            }
         }

         GL11.glDepthMask(true);
         GL11.glEnable(2929);
         GL11.glEnable(3008);
         GL11.glEnable(2884);
         GL11.glEnable(3553);
         GL11.glDisable(3042);
         GL11.glBlendFunc(770, 771);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glPopMatrix();
      }
   }

   private boolean hasElytra(PlayerEntity player) {
      return player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == Items.ELYTRA;
   }

   private void renderVectorWings(PlayerEntity player, float tickDelta, Vector3d camera) {
      double x = MathHelper.lerp((double)tickDelta, player.lastTickPosX, player.getPosX()) - camera.x;
      double y = MathHelper.lerp((double)tickDelta, player.lastTickPosY, player.getPosY()) - camera.y;
      double z = MathHelper.lerp((double)tickDelta, player.lastTickPosZ, player.getPosZ()) - camera.z;
      float bodyYaw = this.resolveBodyYaw(player, tickDelta);
      float move = MathHelper.clamp(player.limbSwingAmount, 0.0F, 1.0F);
      WingPose pose = this.resolveVectorPose(player);
      if (pose != null) {
         float flap = (float)Math.sin((double)(((float)player.ticksExisted + tickDelta) * pose.flapSpeed)) * pose.flapAmplitude;
         float open = (8.0F + flap + move * pose.motionSpreadBoost) * pose.openMultiplier;
         float wingScale = (Float)this.size.get() * pose.scaleMultiplier;
         int baseColor = this.resolveBaseColor();
         int glowColor = this.resolveGlowColor(baseColor);
         int coreColor = this.resolveCoreColor(baseColor);
         GL11.glPushMatrix();
         GL11.glTranslated(x, y, z);
         GL11.glRotatef(180.0F - bodyYaw, 0.0F, 1.0F, 0.0F);
         if (pose.preTranslateY != 0.0F || pose.preTranslateZ != 0.0F) {
            GL11.glTranslatef(0.0F, pose.preTranslateY, pose.preTranslateZ);
         }

         if (pose.pitchRotation != 0.0F) {
            GL11.glRotatef(pose.pitchRotation, 1.0F, 0.0F, 0.0F);
         }

         if (pose.rollRotation != 0.0F) {
            GL11.glRotatef(pose.rollRotation, 0.0F, 0.0F, 1.0F);
         }

         GL11.glTranslatef(0.0F, pose.anchorY, pose.anchorZ);
         GL11.glScalef(wingScale, wingScale, wingScale);
         this.renderVectorWingSide(-1.0F, open, baseColor, glowColor, coreColor, pose);
         this.renderVectorWingSide(1.0F, open, baseColor, glowColor, coreColor, pose);
         GL11.glPopMatrix();
      }
   }

   private void renderVectorWingSide(float side, float open, int baseColor, int glowColor, int coreColor, WingPose pose) {
      GL11.glPushMatrix();
      GL11.glTranslatef(side * pose.sideOffset, pose.sideYOffset, pose.sideZOffset);
      GL11.glRotatef(side * open, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(side * pose.sideRoll, 0.0F, 0.0F, 1.0F);
      GL11.glRotatef(pose.sidePitch, 1.0F, 0.0F, 0.0F);
      GL11.glBlendFunc(770, 771);
      if ((Boolean)this.fill.get()) {
         this.drawWingLayer(side, 1.0F, setAlpha(baseColor, ((Float)this.opacity.get()).intValue()), setAlpha(baseColor, Math.max(20, ((Float)this.opacity.get()).intValue() / 4)));
      }

      GL11.glBlendFunc(770, 1);
      this.drawWingLayer(side, 1.22F, setAlpha(glowColor, 48), setAlpha(glowColor, 0));
      this.drawWingLayer(side, 0.84F, setAlpha(coreColor, 57), setAlpha(coreColor, 0));
      this.drawWingOutline(side, 1.0F, setAlpha(baseColor, 136));
      this.drawWingRibs(side, 0.96F, setAlpha(glowColor, 44));
      GL11.glPopMatrix();
   }

   private void drawWingLayer(float side, float scale, int rootColor, int edgeColor) {
      GL11.glBegin(4);

      for(int i = 0; i < VECTOR_SHAPE.length; ++i) {
         float[] cur = VECTOR_SHAPE[i];
         float[] next = VECTOR_SHAPE[(i + 1) % VECTOR_SHAPE.length];
         this.glColor(rootColor);
         GL11.glVertex3f(0.0F, 0.0F, 0.0F);
         this.glColor(edgeColor);
         GL11.glVertex3f(side * cur[0] * scale, cur[1] * scale, 0.0F);
         this.glColor(edgeColor);
         GL11.glVertex3f(side * next[0] * scale, next[1] * scale, 0.0F);
      }

      GL11.glEnd();
   }

   private void drawWingOutline(float side, float scale, int color) {
      GL11.glLineWidth(1.35F);
      GL11.glEnable(2848);
      GL11.glBegin(3);
      float[][] var4 = VECTOR_SHAPE;
      int var5 = var4.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         float[] point = var4[var6];
         this.glColor(color);
         GL11.glVertex3f(side * point[0] * scale, point[1] * scale, 0.0F);
      }

      this.glColor(color);
      GL11.glVertex3f(side * VECTOR_SHAPE[0][0] * scale, VECTOR_SHAPE[0][1] * scale, 0.0F);
      GL11.glEnd();
      GL11.glDisable(2848);
   }

   private void drawWingRibs(float side, float scale, int color) {
      int[] ribIndices = new int[]{2, 4, 7, 9, 11};
      GL11.glLineWidth(0.9F);
      GL11.glBegin(1);
      int[] var5 = ribIndices;
      int var6 = ribIndices.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         int idx = var5[var7];
         if (idx < VECTOR_SHAPE.length) {
            float[] point = VECTOR_SHAPE[idx];
            this.glColor(setAlpha(color, Math.max(8, (int)((float)alpha(color) * 0.75F))));
            GL11.glVertex3f(0.0F, 0.0F, 0.0F);
            this.glColor(this.applyPointAlpha(color, point[2]));
            GL11.glVertex3f(side * point[0] * scale, point[1] * scale, 0.0F);
         }
      }

      GL11.glEnd();
   }

   private void glColor(int color) {
      GL11.glColor4f((float)red(color) / 255.0F, (float)green(color) / 255.0F, (float)blue(color) / 255.0F, (float)alpha(color) / 255.0F);
   }

   private int applyPointAlpha(int color, float multiplier) {
      return setAlpha(color, Math.max(20, Math.min(255, (int)((float)alpha(color) * multiplier))));
   }

   private static int setAlpha(int color, int a) {
      return MathHelper.clamp(a, 0, 255) << 24 | color & 16777215;
   }

   private static int alpha(int color) {
      return color >> 24 & 255;
   }

   private static int red(int color) {
      return color >> 16 & 255;
   }

   private static int green(int color) {
      return color >> 8 & 255;
   }

   private static int blue(int color) {
      return color & 255;
   }

   private int resolveBaseColor() {
      int color = ThemeEditor.getColor(ThemeSettings.LOGO);
      return color & 16777215 | -16777216;
   }

   private int resolveGlowColor(int base) {
      int r = Math.min(255, red(base) + (int)((float)(255 - red(base)) * 0.28F));
      int g = Math.min(255, green(base) + (int)((float)(255 - green(base)) * 0.28F));
      int b = Math.min(255, blue(base) + (int)((float)(255 - blue(base)) * 0.28F));
      return alpha(base) << 24 | r << 16 | g << 8 | b;
   }

   private int resolveCoreColor(int base) {
      int r = Math.min(255, red(base) + (int)((float)(255 - red(base)) * 0.55F));
      int g = Math.min(255, green(base) + (int)((float)(255 - green(base)) * 0.55F));
      int b = Math.min(255, blue(base) + (int)((float)(255 - blue(base)) * 0.55F));
      return alpha(base) << 24 | r << 16 | g << 8 | b;
   }

   private float resolveBodyYaw(PlayerEntity player, float tickDelta) {
      float target = MathHelper.lerp(tickDelta, player.prevRenderYawOffset, player.renderYawOffset);
      Minecraft var10001 = mc;
      if (player != Minecraft.player) {
         return target;
      } else if (this.selfBodyYawInitialized && player.ticksExisted >= 2) {
         this.selfBodyYaw = approachDegrees(this.selfBodyYaw, target, 14.0F);
         return this.selfBodyYaw;
      } else {
         this.selfBodyYaw = target;
         this.selfBodyYawInitialized = true;
         return this.selfBodyYaw;
      }
   }

   private static float approachDegrees(float current, float target, float maxDelta) {
      float delta = MathHelper.wrapDegrees(target - current);
      delta = MathHelper.clamp(delta, -maxDelta, maxDelta);
      return current + delta;
   }

   private WingPose resolveVectorPose(PlayerEntity player) {
      if (player.isElytraFlying()) {
         return null;
      } else if (player.isSwimming()) {
         return null;
      } else {
         return player.isSneaking() ? new WingPose(0.0F, 0.0F, 0.96F, 0.1F, 18.0F, 0.0F, 1.0F, 1.0F, 0.18F, 4.5F, 0.06F, 0.02F, -11.0F, -4.0F, 0.12F) : new WingPose(0.0F, 0.0F, 1.38F, 0.1F, 0.0F, 0.0F, 1.0F, 1.0F, 0.18F, 4.5F, 0.06F, 0.02F, -11.0F, -4.0F, 0.12F);
      }
   }

   public void onDisable() {
      this.selfBodyYawInitialized = false;
      super.onDisable();
   }

   private static final class WingPose {
      final float preTranslateY;
      final float preTranslateZ;
      final float anchorY;
      final float anchorZ;
      final float pitchRotation;
      final float rollRotation;
      final float openMultiplier;
      final float scaleMultiplier;
      final float motionSpreadBoost;
      final float flapAmplitude;
      final float sideOffset;
      final float sideYOffset;
      final float sideZOffset;
      final float sideRoll;
      final float sidePitch;
      final float flapSpeed;

      WingPose(float preTranslateY, float preTranslateZ, float anchorY, float anchorZ, float pitchRotation, float rollRotation, float openMultiplier, float scaleMultiplier, float motionSpreadBoost, float flapAmplitude, float sideOffset, float sideZOffset, float sideRoll, float sidePitch, float flapSpeed) {
         this.preTranslateY = preTranslateY;
         this.preTranslateZ = preTranslateZ;
         this.anchorY = anchorY;
         this.anchorZ = anchorZ;
         this.pitchRotation = pitchRotation;
         this.rollRotation = rollRotation;
         this.openMultiplier = openMultiplier;
         this.scaleMultiplier = scaleMultiplier;
         this.motionSpreadBoost = motionSpreadBoost;
         this.flapAmplitude = flapAmplitude;
         this.sideOffset = sideOffset;
         this.sideYOffset = 0.0F;
         this.sideZOffset = sideZOffset;
         this.sideRoll = sideRoll;
         this.sidePitch = sidePitch;
         this.flapSpeed = flapSpeed;
      }
   }
}
