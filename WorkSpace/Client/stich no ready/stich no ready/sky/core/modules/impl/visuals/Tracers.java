package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.vector.Vector3d;
import org.lwjgl.opengl.GL11;
import sky.core.SkyCore;
import sky.core.events.EventRender3D;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ColorSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.utils.misc.TargetUtil;
import sky.core.utils.render.ColorUtil;

public class Tracers extends Module {
   private final MultiBooleanSetting rendering = new MultiBooleanSetting("Отображать", new BooleanSetting[]{new BooleanSetting("Игроков", true), new BooleanSetting("Монстров", false), new BooleanSetting("Друзей", true), new BooleanSetting("Животных", false), new BooleanSetting("Жителей", false), new BooleanSetting("Голых", false)});
   private final ColorSetting color = new ColorSetting("Цвет", true, -1);
   private final ColorSetting friendColor = new ColorSetting("Цвет друзей", true, ColorUtil.getColor(0, 255, 0), () -> {
      return this.rendering.is("Друзей");
   });
   private final SliderSetting lineWidth = new SliderSetting("Толщина линии", 1.0F, 0.5F, 5.0F, 0.1F);
   private final BooleanSetting onlyNetherite = new BooleanSetting("Онли кто в броне", false, () -> {
      return this.rendering.is("Игроков");
   });

   public Tracers() {
      super("Tracers", "Рисует линии до существ", Category.Visuals);
      this.addSettings(new Setting[]{this.onlyNetherite, this.rendering, this.lineWidth, this.color, this.friendColor});
   }

   @EventTarget
   public void onUpdate(EventRender3D e) {
      GL11.glPushMatrix();
      GL11.glDisable(3553);
      GL11.glDisable(2929);
      GL11.glEnable(3042);
      GL11.glEnable(2848);
      GL11.glLineWidth((Float)this.lineWidth.get());
      Vector3d cam = (new Vector3d(0.0, 0.0, 150.0)).rotatePitch((float)(-Math.toRadians((double)mc.getRenderManager().info.getPitch()))).rotateYaw((float)(-Math.toRadians((double)mc.getRenderManager().info.getYaw())));
      Iterator var3 = mc.world.getAllEntities().iterator();

      while(true) {
         Entity entity;
         do {
            do {
               do {
                  if (!var3.hasNext()) {
                     GL11.glDisable(3042);
                     GL11.glDisable(2848);
                     GL11.glEnable(3553);
                     GL11.glEnable(2929);
                     GL11.glPopMatrix();
                     return;
                  }

                  entity = (Entity)var3.next();
               } while(!this.isValidEntity(entity));
            } while(!(entity instanceof LivingEntity));

            Minecraft var10001 = mc;
         } while(entity == Minecraft.player);

         Vector3d pos = this.getInterpolatedPositionVec(entity).subtract(mc.getRenderManager().info.getProjectedView());
         boolean isFriend = entity instanceof PlayerEntity && SkyCore.getInstance().getFriendManager().isFriend(((PlayerEntity)entity).getGameProfile().getName());
         int selectedColor = isFriend ? (Integer)this.friendColor.get() : (Integer)this.color.get();
         float r = (float)(selectedColor >> 16 & 255) / 255.0F;
         float g = (float)(selectedColor >> 8 & 255) / 255.0F;
         float b = (float)(selectedColor & 255) / 255.0F;
         float a = (float)(selectedColor >> 24 & 255) / 255.0F;
         RenderSystem.color4f(r, g, b, a);
         BUFFER.begin(1, DefaultVertexFormats.POSITION);
         BUFFER.pos(cam.x, cam.y, cam.z).endVertex();
         BUFFER.pos(pos.x, pos.y, pos.z).endVertex();
         TESSELLATOR.draw();
      }
   }

   public Vector3d getPrevPositionVec(Entity entity) {
      return new Vector3d(entity.prevPosX, entity.prevPosY, entity.prevPosZ);
   }

   public Vector3d getInterpolatedPositionVec(Entity entity) {
      Vector3d prev = this.getPrevPositionVec(entity);
      return prev.add(entity.getPositionVec().subtract(prev).scale((double)mc.getRenderPartialTicks()));
   }

   private boolean isValidEntity(Entity entity) {
      if (entity instanceof LivingEntity living) {
         Minecraft var10001 = mc;
         if (entity != Minecraft.player) {
            if (TargetUtil.isPlayerTarget(living, this.rendering, true) && (!(Boolean)this.onlyNetherite.get() || this.hasNetheriteArmor((PlayerEntity)living))) {
               return true;
            }

            if (TargetUtil.isVillagerTarget(living, this.rendering) || TargetUtil.isAnimalTarget(living, this.rendering) || TargetUtil.isMonsterTarget(living, this.rendering)) {
               return true;
            }
         }
      }

      return false;
   }

   private boolean hasNetheriteArmor(PlayerEntity player) {
      Iterator var2 = player.inventory.armorInventory.iterator();

      Item item;
      do {
         ItemStack stack;
         do {
            if (!var2.hasNext()) {
               return false;
            }

            stack = (ItemStack)var2.next();
         } while(stack.isEmpty());

         item = stack.getItem();
      } while(item != Items.NETHERITE_HELMET && item != Items.NETHERITE_CHESTPLATE && item != Items.NETHERITE_LEGGINGS && item != Items.NETHERITE_BOOTS);

      return true;
   }
}
