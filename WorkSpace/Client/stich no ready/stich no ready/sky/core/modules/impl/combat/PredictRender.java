package sky.core.modules.impl.combat;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.entity.model.BipedModel.ArmPose;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import sky.core.SkyCore;
import sky.core.events.EventRender2D;
import sky.core.events.EventRender3D;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.utils.math.MathUtil;
import sky.core.utils.render.ColorUtil;

public class PredictRender extends Module {
   public PredictRender() {
      super("Predict Render", Category.Visuals);
   }

   @EventTarget
   public void onEvent(EventRender3D event) {
      this.renderTarget();
   }

   @EventTarget
   public void onEvent(EventRender2D event) {
   }

   private void renderTarget() {
      AttackAura killAura = SkyCore.getInstance().getModuleManager().getAttackAura();
      if (mc.world != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            if (AttackAura.getTarget() == null) {
               return;
            }

            AbstractClientPlayerEntity target = (AbstractClientPlayerEntity)AttackAura.getTarget();
            float pt = mc.getRenderPartialTicks();
            double sx = MathUtil.lerp(target.serverX, target.prevServerX, (double)mc.getRenderPartialTicks());
            double sy = MathUtil.lerp(target.serverY, target.prevServerY, (double)mc.getRenderPartialTicks());
            double sz = MathUtil.lerp(target.serverZ, target.prevServerZ, (double)mc.getRenderPartialTicks());
            Vector3d serverPos = new Vector3d(sx, sy, sz);
            Vector3d cam = mc.getRenderManager().info.getProjectedView();
            boolean slim = "slim".equalsIgnoreCase(target.getSkinType());
            PlayerModel model = new PlayerModel(0.0F, slim);
            this.applyModelVisibilities(target, model);
            model.isChild = target.isChild();
            model.isSneak = target.isCrouching();
            model.isSitting = target.isPassenger();
            model.swingProgress = target.getSwingProgress(pt);
            float bodyYaw = MathHelper.interpolateAngle(pt, target.prevRenderYawOffset, target.renderYawOffset);
            float headYaw = MathHelper.interpolateAngle(pt, target.prevRotationYawHead, target.rotationYawHead);
            float headPitch = MathHelper.lerp(pt, target.prevRotationPitch, target.rotationPitch);
            float netHeadYaw = headYaw - bodyYaw;
            float limbSwingAmount = MathHelper.lerp(pt, target.prevLimbSwingAmount, target.limbSwingAmount);
            float limbSwing = target.limbSwing - target.limbSwingAmount * (1.0F - pt);
            float age = (float)target.ticksExisted + pt;
            model.setLivingAnimations(target, limbSwing, limbSwingAmount, pt);
            model.setRotationAngles(target, limbSwing, limbSwingAmount, age, netHeadYaw, headPitch);
            int color = this.resolveColor();
            float r = (float)(color >> 16 & 255) / 255.0F;
            float g = (float)(color >> 8 & 255) / 255.0F;
            float b = (float)(color & 255) / 255.0F;
            MatrixStack stack = new MatrixStack();
            stack.push();
            double rx = serverPos.x - cam.x;
            double ry = serverPos.y - cam.y;
            double rz = serverPos.z - cam.z;
            stack.translate(rx, ry, rz);
            stack.rotate(Vector3f.YP.rotationDegrees(180.0F - bodyYaw));
            stack.scale(-1.0F, -1.0F, 1.0F);
            stack.translate(0.0, -1.501, 0.0);
            float alpha = MathHelper.clamp(0.39215687F, 0.0F, 0.75F);
            this.renderFlatModel(stack, r, g, b, alpha, model, true);
            stack.pop();
            return;
         }
      }

   }

   private void renderFlatModel(MatrixStack stack, float r, float g, float b, float a, PlayerModel model, boolean chams) {
      RenderSystem.disableTexture();
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableCull();
      if (chams) {
         RenderSystem.disableDepthTest();
      }

      Tessellator tess = Tessellator.getInstance();
      BufferBuilder buf = tess.getBuffer();
      buf.begin(7, DefaultVertexFormats.POSITION_COLOR);
      this.renderFlatPart(stack, buf, model.bipedHead, -5.0F, -6.0F, -5.0F, 8.0F, 8.0F, 8.0F, r, g, b, a);
      this.renderFlatPart(stack, buf, model.bipedBody, -4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, r, g, b, a);
      this.renderFlatPart(stack, buf, model.bipedRightArm, -3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, r, g, b, a);
      this.renderFlatPart(stack, buf, model.bipedLeftArm, -3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, r, g, b, a);
      this.renderFlatPart(stack, buf, model.bipedRightLeg, -2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, r, g, b, a);
      this.renderFlatPart(stack, buf, model.bipedLeftLeg, -2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, r, g, b, a);
      tess.draw();
      RenderSystem.enableCull();
      if (chams) {
         RenderSystem.enableDepthTest();
      }

      RenderSystem.enableTexture();
   }

   private void renderFlatPart(MatrixStack parent, BufferBuilder buf, ModelRenderer part, float minX, float minY, float minZ, float sizeX, float sizeY, float sizeZ, float r, float g, float b, float a) {
      parent.push();
      float unit = 0.0625F;
      parent.translate((double)(part.rotationPointX * unit), (double)(part.rotationPointY * unit), (double)(part.rotationPointZ * unit));
      if (part.rotateAngleZ != 0.0F) {
         parent.rotate(Vector3f.ZP.rotation(part.rotateAngleZ));
      }

      if (part.rotateAngleY != 0.0F) {
         parent.rotate(Vector3f.YP.rotation(part.rotateAngleY));
      }

      if (part.rotateAngleX != 0.0F) {
         parent.rotate(Vector3f.XP.rotation(part.rotateAngleX));
      }

      float x1 = minX * unit;
      float y1 = minY * unit;
      float z1 = minZ * unit;
      float x2 = (minX + sizeX) * unit;
      float y2 = (minY + sizeY) * unit;
      float z2 = (minZ + sizeZ) * unit;
      Matrix4f matrix = parent.getLast().getMatrix();
      buf.pos(matrix, x1, y1, z2).color(r, g, b, a).endVertex();
      buf.pos(matrix, x2, y1, z2).color(r, g, b, a).endVertex();
      buf.pos(matrix, x2, y2, z2).color(r, g, b, a).endVertex();
      buf.pos(matrix, x1, y2, z2).color(r, g, b, a).endVertex();
      buf.pos(matrix, x2, y1, z1).color(r, g, b, a).endVertex();
      buf.pos(matrix, x1, y1, z1).color(r, g, b, a).endVertex();
      buf.pos(matrix, x1, y2, z1).color(r, g, b, a).endVertex();
      buf.pos(matrix, x2, y2, z1).color(r, g, b, a).endVertex();
      buf.pos(matrix, x1, y1, z1).color(r, g, b, a).endVertex();
      buf.pos(matrix, x1, y1, z2).color(r, g, b, a).endVertex();
      buf.pos(matrix, x1, y2, z2).color(r, g, b, a).endVertex();
      buf.pos(matrix, x1, y2, z1).color(r, g, b, a).endVertex();
      buf.pos(matrix, x2, y1, z2).color(r, g, b, a).endVertex();
      buf.pos(matrix, x2, y1, z1).color(r, g, b, a).endVertex();
      buf.pos(matrix, x2, y2, z1).color(r, g, b, a).endVertex();
      buf.pos(matrix, x2, y2, z2).color(r, g, b, a).endVertex();
      buf.pos(matrix, x1, y2, z2).color(r, g, b, a).endVertex();
      buf.pos(matrix, x2, y2, z2).color(r, g, b, a).endVertex();
      buf.pos(matrix, x2, y2, z1).color(r, g, b, a).endVertex();
      buf.pos(matrix, x1, y2, z1).color(r, g, b, a).endVertex();
      buf.pos(matrix, x1, y1, z1).color(r, g, b, a).endVertex();
      buf.pos(matrix, x2, y1, z1).color(r, g, b, a).endVertex();
      buf.pos(matrix, x2, y1, z2).color(r, g, b, a).endVertex();
      buf.pos(matrix, x1, y1, z2).color(r, g, b, a).endVertex();
      parent.pop();
   }

   private void quad(BufferBuilder buf, Matrix4f m, float x1, float y1, float z1, float x2, float y2, float z2, float r, float g, float b, float a) {
      buf.pos(m, x1, y1, z1).color(r, g, b, a).endVertex();
      buf.pos(m, x2, y1, z1).color(r, g, b, a).endVertex();
      buf.pos(m, x2, y2, z2).color(r, g, b, a).endVertex();
      buf.pos(m, x1, y2, z2).color(r, g, b, a).endVertex();
   }

   private int resolveColor() {
      return ColorUtil.getColor(255, 255, 255, 100);
   }

   private void applyModelVisibilities(AbstractClientPlayerEntity player, PlayerModel model) {
      model.setVisible(true);
      model.bipedHeadwear.showModel = player.isWearing(PlayerModelPart.HAT);
      model.bipedBodyWear.showModel = player.isWearing(PlayerModelPart.JACKET);
      model.bipedLeftLegwear.showModel = player.isWearing(PlayerModelPart.LEFT_PANTS_LEG);
      model.bipedRightLegwear.showModel = player.isWearing(PlayerModelPart.RIGHT_PANTS_LEG);
      model.bipedLeftArmwear.showModel = player.isWearing(PlayerModelPart.LEFT_SLEEVE);
      model.bipedRightArmwear.showModel = player.isWearing(PlayerModelPart.RIGHT_SLEEVE);
      BipedModel.ArmPose main = this.getArmPose(player, Hand.MAIN_HAND);
      BipedModel.ArmPose off = this.getArmPose(player, Hand.OFF_HAND);
      if (main.func_241657_a_()) {
         off = player.getHeldItemOffhand().isEmpty() ? ArmPose.EMPTY : ArmPose.ITEM;
      }

      if (player.getPrimaryHand() == HandSide.RIGHT) {
         model.rightArmPose = main;
         model.leftArmPose = off;
      } else {
         model.rightArmPose = off;
         model.leftArmPose = main;
      }

   }

   private BipedModel.ArmPose getArmPose(AbstractClientPlayerEntity player, Hand hand) {
      ItemStack stack = player.getHeldItem(hand);
      if (stack.isEmpty()) {
         return ArmPose.EMPTY;
      } else {
         if (player.getActiveHand() == hand && player.getItemInUseCount() > 0) {
            UseAction action = stack.getUseAction();
            if (action == UseAction.BLOCK) {
               return ArmPose.BLOCK;
            }

            if (action == UseAction.BOW) {
               return ArmPose.BOW_AND_ARROW;
            }

            if (action == UseAction.SPEAR) {
               return ArmPose.THROW_SPEAR;
            }

            if (action == UseAction.CROSSBOW && hand == player.getActiveHand()) {
               return ArmPose.CROSSBOW_CHARGE;
            }
         } else if (stack.getItem() == Items.CROSSBOW && CrossbowItem.isCharged(stack)) {
            return ArmPose.CROSSBOW_HOLD;
         }

         return ArmPose.ITEM;
      }
   }
}
