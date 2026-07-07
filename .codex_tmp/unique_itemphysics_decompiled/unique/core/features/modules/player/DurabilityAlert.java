package unique.core.features.modules.player;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_1657;
import net.minecraft.class_1738;
import net.minecraft.class_1799;
import net.minecraft.class_332;
import unique.core.core.Managers;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.ClientSettings;
import unique.core.gui.font.FontRenderers;
import unique.core.setting.Setting;
import unique.core.utility.Timer;
import unique.core.utility.render.TextureStorage;

public class DurabilityAlert extends Module {
   private final Setting<Boolean> friends = new Setting<>("Friend message", true);
   private final Setting<Integer> percent = new Setting<>("Percent", 20, 1, 100);
   private boolean need_alert = false;
   private final Timer timer = new Timer();

   public DurabilityAlert() {
      super("DurabilityAlert", Module.Category.PLAYER);
   }

   @Override
   public void onUpdate() {
      if (this.friends.getValue()) {
         for (class_1657 player : mc.field_1687.method_18456()) {
            if (Managers.FRIEND.isFriend(player) && player != mc.field_1724) {
               for (class_1799 stack : player.method_31548().field_7548) {
                  if (!stack.method_7960()
                     && stack.method_7909() instanceof class_1738
                     && getDurability(stack) < this.percent.getValue()
                     && this.timer.passedMs(30000L)) {
                     mc.field_1724
                        .field_3944
                        .method_45730(
                           "msg " + player.method_5477().getString() + (ClientSettings.isRu() ? " Срочно чини броню!" : " Fix your armor right now!")
                        );
                     this.timer.reset();
                  }
               }
            }
         }
      }

      boolean flag = false;

      for (class_1799 stackx : mc.field_1724.method_31548().field_7548) {
         if (!stackx.method_7960() && stackx.method_7909() instanceof class_1738 && getDurability(stackx) < this.percent.getValue()) {
            this.need_alert = true;
            flag = true;
         }
      }

      if (!flag && this.need_alert) {
         this.need_alert = false;
      }
   }

   @Override
   public void onRender2D(class_332 context) {
      if (this.need_alert) {
         FontRenderers.sf_bold
            .drawCenteredString(
               context.method_51448(),
               ClientSettings.isRu() ? "Срочно чини броню!" : "Fix your armor right now!",
               mc.method_22683().method_4486() / 2.0F,
               mc.method_22683().method_4502() / 3.0F,
               new Color(16768768).getRGB()
            );
         Color c1 = new Color(16768768);
         RenderSystem.setShaderColor(c1.getRed() / 255.0F, c1.getGreen() / 255.0F, c1.getBlue() / 255.0F, 1.0F);
         context.method_25293(
            TextureStorage.brokenShield,
            (int)(mc.method_22683().method_4486() / 2.0F - 40.0F),
            (int)(mc.method_22683().method_4502() / 3.0F - 120.0F),
            80,
            80,
            0.0F,
            0.0F,
            80,
            80,
            80,
            80
         );
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      }
   }

   public static int getDurability(class_1799 stack) {
      return (int)((stack.method_7936() - stack.method_7919()) / Math.max(0.1, (double)stack.method_7936()) * 100.0);
   }
}
