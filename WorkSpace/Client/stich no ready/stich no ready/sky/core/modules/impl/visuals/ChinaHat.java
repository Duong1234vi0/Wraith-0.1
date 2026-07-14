package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.optifine.render.RenderStateManager;
import org.lwjgl.opengl.GL11;
import sky.core.SkyCore;
import sky.core.events.EventLayerHead;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.render.ColorUtil;

public class ChinaHat extends Module {
   private final BooleanSetting viewinfriend = new BooleanSetting("Отображать на друзьях", true);
   private static final float PI;

   public ChinaHat() {
      super("China Hat", "Добавляет китайскую шапку", Category.Visuals);
      this.addSettings(new Setting[]{this.viewinfriend});
   }

   @EventTarget
   public void onEvent(EventLayerHead event) {
      EntityModel var4 = event.getModel();
      if (var4 instanceof IHasHead head) {
         LivingEntity var25 = event.getEntity();
         if (var25 instanceof PlayerEntity player) {
            boolean isFriend = SkyCore.getInstance().getFriendManager().isFriend(player.getGameProfile().getName());
            Minecraft var10001 = mc;
            boolean isLocalPlayer = player == Minecraft.player;
            if (isLocalPlayer || (Boolean)this.viewinfriend.get() && isFriend) {
               MatrixStack matrixStack = event.getMatrix();
               AxisAlignedBB entityBoundingBox = event.getEntity().getBoundingBox();
               double radius = entityBoundingBox.maxX - entityBoundingBox.minX;
               float offset = ((ItemStack)player.inventory.armorInventory.get(3)).isEmpty() ? 0.42F : 0.5F;
               matrixStack.push();
               head.getModelHead().translateRotate(matrixStack);
               RenderStateManager.disableCache();
               RenderSystem.enableBlend();
               RenderSystem.enableDepthTest();
               RenderSystem.disableCull();
               RenderSystem.disableTexture();
               RenderSystem.defaultBlendFunc();
               RenderSystem.disableAlphaTest();
               RenderSystem.shadeModel(7425);
               RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
               RenderSystem.shadeModel(7425);
               RenderSystem.lineWidth(2.0F);
               GL11.glEnable(2848);
               GL11.glHint(3154, 4354);
               matrixStack.translate(0.0, (double)(-offset), 0.0);
               matrixStack.rotate(Vector3f.ZN.rotationDegrees(180.0F));
               matrixStack.rotate(Vector3f.YP.rotationDegrees(90.0F));
               Matrix4f matrix4f = matrixStack.getLast().getMatrix();
               BUFFER.begin(5, DefaultVertexFormats.POSITION_COLOR);
               float y = 0.0F;

               int i;
               for(i = 0; i <= 180; ++i) {
                  float iPi = (float)i * PI;
                  int angle = i * 8;
                  int clientColor = ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL);
                  float x = (float)((double)MathHelper.sin(iPi) * radius);
                  float z = (float)((double)MathHelper.cos(iPi) * radius);
                  int outlineColor = ColorUtil.fade(3, angle, ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL), ColorUtil.darken(ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL), 0.5F));
                  int coneColor = ColorUtil.multAlpha(outlineColor, 0.5F);
                  BUFFER.pos(matrix4f, x, y, z).color(coneColor).endVertex();
                  BUFFER.pos(matrix4f, 0.0F, 0.3F, 0.0F).color(clientColor).endVertex();
               }

               TESSELLATOR.draw();
               RenderSystem.depthMask(false);
               BUFFER.begin(2, DefaultVertexFormats.POSITION_COLOR);

               for(i = 0; i <= 180; ++i) {
                  float iPi2 = (float)i * PI;
                  int angle2 = i * 8;
                  float x2 = (float)((double)MathHelper.sin(iPi2) * radius);
                  float z2 = (float)((double)MathHelper.cos(iPi2) * radius);
                  int outlineColor2 = ColorUtil.fade(3, angle2, ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL), ColorUtil.darken(ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL), 0.5F));
                  BUFFER.pos(matrix4f, x2, y, z2).color(outlineColor2).endVertex();
               }

               TESSELLATOR.draw();
               RenderSystem.depthMask(true);
               RenderSystem.enableCull();
               RenderSystem.enableTexture();
               RenderSystem.shadeModel(7424);
               RenderSystem.enableAlphaTest();
               RenderSystem.shadeModel(7424);
               RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
               GL11.glDisable(2848);
               matrixStack.pop();
            }
         }
      }

   }

   static {
      PI = MathHelper.PI2 / 90.0F;
   }
}
