package sky.core.utils.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector4f;

public class GhostRenderer3D {
   private Vector3d position;
   private Vector3d motion;
   private float alpha = 1.0F;
   private int color;
   private final float size;
   private final List tail = new ArrayList();

   public GhostRenderer3D(Vector3d position, Vector3d motion, float size, int color) {
      this.position = position;
      this.motion = motion;
      this.size = size;
      this.color = color;
   }

   public void render(MatrixStack ms, ResourceLocation texture) {
      this.position = this.position.add(this.motion);
      float tailLifetime = 55.0F;
      this.tail.add(new Vector4f((float)this.position.x, (float)this.position.y + 0.4F, (float)this.position.z, 55.0F));
      Vector3d camera = Minecraft.getInstance().getRenderManager().info.getProjectedView();
      List expired = new ArrayList();
      Iterator var6 = this.tail.iterator();

      while(var6.hasNext()) {
         Vector4f point = (Vector4f)var6.next();
         if (point.getW() <= 0.0F) {
            expired.add(point);
         } else {
            float miniSize = this.size * point.getW() / 55.0F;
            float alphaMul = point.getW() / 55.0F * this.alpha;
            int pointAlpha = Math.max(0, Math.min(255, (int)(alphaMul * 255.0F)));
            int tinted = ColorUtil.getColor(ColorUtil.red(this.color), ColorUtil.green(this.color), ColorUtil.blue(this.color), pointAlpha);
            ms.push();
            ms.translate((double)point.getX() - camera.x, (double)point.getY() - camera.y, (double)point.getZ() - camera.z);
            ms.rotate(Minecraft.getInstance().getRenderManager().info.getRotation());
            RenderUtil.drawTexture(ms, texture, -miniSize / 2.0F, -miniSize / 2.0F, miniSize, miniSize, tinted);
            ms.pop();
            point.set(point.getX(), point.getY() + 0.01F / Math.max((float)Minecraft.getDebugFPS(), 5.0F) * 300.0F, point.getZ(), point.getW() - 0.3F / Math.max((float)Minecraft.getDebugFPS(), 10.0F) * 450.0F);
            if (point.getW() <= 0.0F) {
               expired.add(point);
            }
         }
      }

      this.tail.removeAll(expired);
   }

   public void tick() {
      this.position = this.position.add(this.motion);
   }

   public Vector3d getPosition() {
      return this.position;
   }

   public void setPosition(Vector3d position) {
      this.position = position;
   }

   public Vector3d getMotion() {
      return this.motion;
   }

   public void setMotion(Vector3d motion) {
      this.motion = motion;
   }

   public float getAlpha() {
      return this.alpha;
   }

   public void setAlpha(float alpha) {
      this.alpha = alpha;
   }

   public void setColor(int color) {
      this.color = color;
   }
}
