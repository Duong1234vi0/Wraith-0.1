package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import sky.core.events.EventAttack;
import sky.core.events.EventRender3D;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.render.RenderUtil;

public class HitEffect extends Module {
   private final SliderSetting size = new SliderSetting("Размер", 2.0F, 0.5F, 5.0F, 0.1F);
   private final SliderSetting lifeTime = new SliderSetting("Время жизни", 1000.0F, 200.0F, 3000.0F, 100.0F);
   private final SliderSetting speed = new SliderSetting("Скорость", 0.05F, 0.01F, 0.2F, 0.01F);
   private final ArrayList effects = new ArrayList();
   private final Random rnd = new Random();
   private static final ResourceLocation TEX = new ResourceLocation("SkyCore/icons/world_render/cross.png");

   public HitEffect() {
      super("HitEffect", "Снежинки при ударе", Category.Visuals);
      this.addSettings(new Setting[]{this.size, this.lifeTime, this.speed});
   }

   public void onDisable() {
      this.effects.clear();
      super.onDisable();
   }

   @EventTarget
   public void onEvent(EventAttack e) {
      Entity t = e.getTarget();
      if (t != null) {
         Vector3d tp = t.getPositionVec().add(0.0, (double)(t.getHeight() / 2.0F), 0.0);
         Minecraft var10000 = mc;
         Vector3d var7 = Minecraft.player.getPositionVec();
         Minecraft var10002 = mc;
         Vector3d pp = var7.add(0.0, (double)Minecraft.player.getEyeHeight(), 0.0);
         Vector3d dir = pp.subtract(tp).normalize().add((this.rnd.nextDouble() - 0.5) * 1.5, (this.rnd.nextDouble() - 0.5) * 1.5, (this.rnd.nextDouble() - 0.5) * 1.5).normalize();
         float s = (Float)this.speed.get();
         this.effects.add(new float[]{(float)tp.x, (float)tp.y, (float)tp.z, (float)(dir.x * (double)s), (float)(dir.y * (double)s), (float)(dir.z * (double)s), 0.0F, (Float)this.lifeTime.get()});
      }
   }

   @EventTarget
   public void onEvent(EventRender3D e) {
      this.effects.removeIf((px) -> {
         return px[6] >= px[7];
      });
      if (!this.effects.isEmpty()) {
         RenderSystem.disableDepthTest();
         RenderSystem.depthMask(false);
         Quaternion rot = new Quaternion(mc.getRenderManager().info.getRotation());
         rot.multiply(Vector3f.ZP.rotationDegrees(180.0F));
         Vector3f r = new Vector3f(1.0F, 0.0F, 0.0F);
         Vector3f u = new Vector3f(0.0F, 1.0F, 0.0F);
         r.transform(rot);
         u.transform(rot);
         Vector3d cam = mc.getRenderManager().info.getProjectedView();
         Iterator var6 = this.effects.iterator();

         while(var6.hasNext()) {
            float[] p = (float[])var6.next();
            float prog = p[6] / p[7];
            float factor = 1.0F - prog;
            p[0] += p[3] * factor;
            p[1] += p[4] * factor;
            p[2] += p[5] * factor;
            p[3] *= 0.95F;
            p[4] *= 0.95F;
            p[5] *= 0.95F;
            p[6] += 16.0F;
            float sz = (float)Math.sqrt(1.0 - Math.pow((double)(prog - 1.0F), 2.0)) * (Float)this.size.get() * 0.5F;
            float alpha = prog > 0.6F ? 1.0F - (prog - 0.6F) / 0.4F : 1.0F;
            Vector3d c = new Vector3d((double)p[0] - cam.x, (double)p[1] - cam.y, (double)p[2] - cam.z);
            Vector3d hr = (new Vector3d((double)r.getX(), (double)r.getY(), (double)r.getZ())).scale((double)sz);
            Vector3d hu = (new Vector3d((double)u.getX(), (double)u.getY(), (double)u.getZ())).scale((double)sz);
            int col = ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL) & 16777215 | (int)(alpha * 255.0F) << 24;
            RenderUtil.drawImage3DQuad(TEX, true, (float)(c.x - hr.x - hu.x), (float)(c.y - hr.y - hu.y), (float)(c.z - hr.z - hu.z), (float)(c.x + hr.x - hu.x), (float)(c.y + hr.y - hu.y), (float)(c.z + hr.z - hu.z), (float)(c.x + hr.x + hu.x), (float)(c.y + hr.y + hu.y), (float)(c.z + hr.z + hu.z), (float)(c.x - hr.x + hu.x), (float)(c.y - hr.y + hu.y), (float)(c.z - hr.z + hu.z), col);
            RenderUtil.drawImage3DQuad(TEX, true, (float)(c.x - hr.x + hu.x), (float)(c.y - hr.y + hu.y), (float)(c.z - hr.z + hu.z), (float)(c.x + hr.x + hu.x), (float)(c.y + hr.y + hu.y), (float)(c.z + hr.z + hu.z), (float)(c.x + hr.x - hu.x), (float)(c.y + hr.y - hu.y), (float)(c.z + hr.z - hu.z), (float)(c.x - hr.x - hu.x), (float)(c.y - hr.y - hu.y), (float)(c.z - hr.z - hu.z), col);
         }

         RenderUtil.flushImage3DBatch();
         RenderSystem.depthMask(true);
         RenderSystem.enableDepthTest();
      }
   }
}
