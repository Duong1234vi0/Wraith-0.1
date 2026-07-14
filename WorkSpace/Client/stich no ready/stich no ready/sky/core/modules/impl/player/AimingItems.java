package sky.core.modules.impl.player;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.SkullItem;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.events.EventUpdate;
import sky.core.handlers.impl.LookHandler;
import sky.core.handlers.impl.Rotation;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.modules.impl.combat.AttackAura;
import sky.core.utils.component.impl.RotationComponent;

public class AimingItems extends Module {
   private final MultiBooleanSetting aimAt = new MultiBooleanSetting("Наводиться на", new BooleanSetting[]{new BooleanSetting("Голову игрока", true), new BooleanSetting("Элитру", true), new BooleanSetting("Осколки", true), new BooleanSetting("Ауры", false)});
   private Rotation targetRotation;
   private ItemEntity targetItem;

   public AimingItems() {
      super("AutoPilot", "Притягивает к выбранным предметам", Category.Player);
      this.addSettings(new Setting[]{this.aimAt});
   }

   @EventTarget
   private void onUpdate(EventUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         this.targetItem = null;
         this.targetRotation = null;
         Iterator var2 = mc.world.getAllEntities().iterator();

         while(var2.hasNext()) {
            Entity entity = (Entity)var2.next();
            if (entity instanceof ItemEntity) {
               ItemEntity itemEntity = (ItemEntity)entity;
               if (this.isItemOfInterest(itemEntity)) {
                  this.targetItem = itemEntity;
                  this.targetRotation = this.calculateRotation(itemEntity.getPositionVec().add(itemEntity.getMotion().scale(1.100000023841858)));
                  break;
               }
            }
         }

         if (this.targetItem != null && this.targetRotation != null) {
            if (AttackAura.target == null) {
               LookHandler.setActive(false);
            }

            RotationComponent.update(this.targetRotation, 360.0F, 360.0F, 360.0F, 360.0F, 1, 5, true);
         }
      }
   }

   private Rotation calculateRotation(Vector3d target) {
      Minecraft var10000 = mc;
      Vector3d eyes = Minecraft.player.getEyePosition(1.0F);
      double diffX = target.x - eyes.x;
      double diffY = target.y - eyes.y;
      double diffZ = target.z - eyes.z;
      double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
      float yaw = (float)(MathHelper.atan2(diffZ, diffX) * 57.29577951308232) - 90.0F;
      float pitch = (float)(-(MathHelper.atan2(diffY, diffXZ) * 57.29577951308232));
      return new Rotation(yaw, pitch);
   }

   private boolean isItemOfInterest(ItemEntity item) {
      Item itemType = item.getItem().getItem();
      return itemType instanceof SkullItem && Boolean.TRUE.equals(this.aimAt.is("Голову игрока")) || itemType == Items.GHAST_TEAR && Boolean.TRUE.equals(this.aimAt.is("Осколки")) || itemType == Items.CLAY_BALL && Boolean.TRUE.equals(this.aimAt.is("Ауры")) || itemType == Items.WHITE_DYE && Boolean.TRUE.equals(this.aimAt.is("Ауры")) || itemType == Items.POPPED_CHORUS_FRUIT && Boolean.TRUE.equals(this.aimAt.is("Ауры")) || itemType == Items.GHAST_TEAR && Boolean.TRUE.equals(this.aimAt.is("Ауры")) || itemType == Items.SUNFLOWER && Boolean.TRUE.equals(this.aimAt.is("Ауры")) || itemType == Items.GOLD_NUGGET && Boolean.TRUE.equals(this.aimAt.is("Ауры")) || itemType instanceof ElytraItem && Boolean.TRUE.equals(this.aimAt.is("Элитру"));
   }

   public void onDisable() {
      this.targetRotation = null;
      this.targetItem = null;
      super.onDisable();
   }
}
