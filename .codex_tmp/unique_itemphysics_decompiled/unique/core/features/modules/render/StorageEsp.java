package unique.core.features.modules.render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1533;
import net.minecraft.class_1694;
import net.minecraft.class_1747;
import net.minecraft.class_238;
import net.minecraft.class_2480;
import net.minecraft.class_2586;
import net.minecraft.class_2595;
import net.minecraft.class_2609;
import net.minecraft.class_2611;
import net.minecraft.class_2614;
import net.minecraft.class_2627;
import net.minecraft.class_2818;
import net.minecraft.class_3719;
import net.minecraft.class_4587;
import org.jetbrains.annotations.Nullable;
import unique.core.core.Managers;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.setting.impl.ColorSetting;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.Render3DEngine;

public class StorageEsp extends Module {
   public final Setting<Boolean> outline = new Setting<>("Outline", true);
   public final Setting<Boolean> fill = new Setting<>("Fill", true);
   public final Setting<Boolean> chest = new Setting<>("Chest", false);
   public final Setting<Boolean> shulker = new Setting<>("Shulker", false);
   public final Setting<Boolean> echest = new Setting<>("Ender Chest", false);
   public final Setting<Boolean> furnace = new Setting<>("Furnace", false);
   public final Setting<Boolean> hopper = new Setting<>("Hopper", false);
   public final Setting<Boolean> barrels = new Setting<>("Barrel", false);
   public final Setting<Boolean> cart = new Setting<>("Minecart", false);
   public final Setting<Boolean> frame = new Setting<>("ItemFrame", false);
   private final Setting<ColorSetting> chestColor = new Setting<>("ChestColor", new ColorSetting(-1), v -> this.chest.getValue());
   private final Setting<ColorSetting> shulkColor = new Setting<>("ShulkerColor", new ColorSetting(-1), v -> this.shulker.getValue());
   private final Setting<ColorSetting> echestColor = new Setting<>("EChestColor", new ColorSetting(-1), v -> this.echest.getValue());
   private final Setting<ColorSetting> frameColor = new Setting<>("FrameColor", new ColorSetting(-1), v -> this.frame.getValue());
   private final Setting<ColorSetting> shulkerframeColor = new Setting<>(
      "ShulkFrameColor", new ColorSetting(-1), v -> this.shulker.getValue() && this.frame.getValue()
   );
   private final Setting<ColorSetting> furnaceColor = new Setting<>("FurnaceColor", new ColorSetting(-1), v -> this.furnace.getValue());
   private final Setting<ColorSetting> hopperColor = new Setting<>("HopperColor", new ColorSetting(-1), v -> this.hopper.getValue());
   private final Setting<ColorSetting> barrelColor = new Setting<>("BarrelColor", new ColorSetting(-1), v -> this.barrels.getValue());
   private final Setting<ColorSetting> minecartColor = new Setting<>("MinecartColor", new ColorSetting(-1), v -> this.cart.getValue());

   public StorageEsp() {
      super("StorageEsp", Module.Category.RENDER);
   }

   @Override
   public void onRender3D(class_4587 stack) {
      if (!mc.field_1690.field_1842) {
         for (class_2586 blockEntity : getBlockEntities()) {
            Color color = this.getColor(blockEntity);
            if (color != null) {
               class_238 chestbox = new class_238(
                  blockEntity.method_11016().method_10263() + 0.06,
                  blockEntity.method_11016().method_10264(),
                  blockEntity.method_11016().method_10260() + 0.06,
                  blockEntity.method_11016().method_10263() + 0.94,
                  blockEntity.method_11016().method_10264() - 0.125 + 1.0,
                  blockEntity.method_11016().method_10260() + 0.94
               );
               if (this.fill.getValue()) {
                  if (!(blockEntity instanceof class_2595) && !(blockEntity instanceof class_2611)) {
                     Render3DEngine.drawFilledBox(stack, new class_238(blockEntity.method_11016()), color);
                  } else {
                     Render3DEngine.drawFilledBox(stack, chestbox, color);
                  }
               }

               if (this.outline.getValue()) {
                  if (!(blockEntity instanceof class_2595) && !(blockEntity instanceof class_2611)) {
                     Render3DEngine.drawBoxOutline(new class_238(blockEntity.method_11016()), Render2DEngine.injectAlpha(color, 255), 1.0F);
                  } else {
                     Render3DEngine.drawBoxOutline(chestbox, Render2DEngine.injectAlpha(color, 255), 1.0F);
                  }
               }
            }
         }

         for (class_1297 ent : Managers.ASYNC.getAsyncEntities()) {
            if (ent instanceof class_1533 iframe && this.frame.getValue()) {
               Color frameColor1 = this.frameColor.getValue().getColorObject();
               if (iframe.method_6940().method_7909() instanceof class_1747 bitem && bitem.method_7711() instanceof class_2480) {
                  frameColor1 = this.shulkerframeColor.getValue().getColorObject();
               }

               if (this.fill.getValue()) {
                  Render3DEngine.drawFilledBox(stack, iframe.method_5829(), frameColor1);
               }

               if (this.outline.getValue()) {
                  Render3DEngine.drawBoxOutline(iframe.method_5829(), Render2DEngine.injectAlpha(frameColor1, 255), 1.0F);
               }
            }

            if (ent instanceof class_1694 mcart && this.cart.getValue()) {
               if (this.fill.getValue()) {
                  Render3DEngine.drawFilledBox(stack, mcart.method_5829(), this.minecartColor.getValue().getColorObject());
               }

               if (this.outline.getValue()) {
                  Render3DEngine.drawBoxOutline(mcart.method_5829(), Render2DEngine.injectAlpha(this.minecartColor.getValue().getColorObject(), 255), 1.0F);
               }
            }
         }
      }
   }

   @Nullable
   private Color getColor(class_2586 bEnt) {
      if (bEnt instanceof class_2595 && this.chest.getValue()) {
         return this.chestColor.getValue().getColorObject();
      } else if (bEnt instanceof class_2611 && this.echest.getValue()) {
         return this.echestColor.getValue().getColorObject();
      } else if (bEnt instanceof class_3719 && this.barrels.getValue()) {
         return this.barrelColor.getValue().getColorObject();
      } else if (bEnt instanceof class_2627 && this.shulker.getValue()) {
         return this.shulkColor.getValue().getColorObject();
      } else if (bEnt instanceof class_2609 && this.furnace.getValue()) {
         return this.furnaceColor.getValue().getColorObject();
      } else {
         return bEnt instanceof class_2614 && this.hopper.getValue() ? this.hopperColor.getValue().getColorObject() : null;
      }
   }

   public static List<class_2586> getBlockEntities() {
      List<class_2586> list = new ArrayList<>();

      for (class_2818 chunk : getLoadedChunks()) {
         list.addAll(chunk.method_12214().values());
      }

      return list;
   }

   public static List<class_2818> getLoadedChunks() {
      List<class_2818> chunks = new ArrayList<>();
      int viewDist = (Integer)mc.field_1690.method_42503().method_41753();

      for (int x = -viewDist; x <= viewDist; x++) {
         for (int z = -viewDist; z <= viewDist; z++) {
            class_2818 chunk = mc.field_1687.method_2935().method_21730((int)mc.field_1724.method_23317() / 16 + x, (int)mc.field_1724.method_23321() / 16 + z);
            if (chunk != null) {
               chunks.add(chunk);
            }
         }
      }

      return chunks;
   }
}
