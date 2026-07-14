package sky.core.modules.impl.miscellaneous;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import sky.core.events.EventContainerRender;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.ColorSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.modules.impl.player.AutoBuy;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;

public class AuctionHelper extends Module {
   private final ModeSetting mode = new ModeSetting("Режим", "HolyWorld", new String[]{"HolyWorld"});
   private final ColorSetting color = new ColorSetting("Цвет выгодного предмета", true, ColorUtil.hex("#53FF00"));
   private final SliderSetting amount = new SliderSetting("Количество выгодных предметов", 3.0F, 1.0F, 5.0F, 1.0F);
   private final AnimationUtil anim = new AnimationUtil(0.0F, 4.0F);
   private boolean animUp = true;

   public AuctionHelper() {
      super("Auction Helper", "Отображает самый выгодный предмет на аукционе", Category.Miscellaneous);
      this.addSettings(new Setting[]{this.mode, this.color, this.amount});
   }

   @EventTarget
   @Native
   public void onEvent(EventContainerRender.Pre event) {
      if (mc.currentScreen instanceof ContainerScreen && this.mode.is("HolyWorld")) {
         if (mc.currentScreen.getTitle().getString().contains("Аукцион")) {
            float target = this.animUp ? 0.0F : 1.0F;
            this.anim.update(target);
            if (this.anim.isDone()) {
               this.animUp = !this.animUp;
            }

            int alpha = (int)(105.0F + this.anim.getValue() * 120.0F);
            List priced = new ArrayList();
            Iterator var5 = event.getContainer().inventorySlots.iterator();

            while(var5.hasNext()) {
               Slot slot = (Slot)var5.next();
               if (slot != null && slot.getHasStack()) {
                  ItemStack stack = slot.getStack();
                  long price = AutoBuy.extractPrice(stack);
                  if (price > 0L) {
                     priced.add(new PriceSlot(slot, price));
                  }
               }
            }

            if (!priced.isEmpty()) {
               priced.sort(Comparator.comparingLong((ps) -> {
                  return ps.price;
               }));
               int toHighlight = (int)Math.min((float)priced.size(), (Float)this.amount.get());
               int highlightColor = ColorUtil.applyOpacity((Integer)this.color.get(), alpha);

               for(int i = 0; i < toHighlight; ++i) {
                  Slot slot = ((PriceSlot)priced.get(i)).slot;
                  float rx = (float)(event.getGuiLeft() + slot.xPos);
                  float ry = (float)(event.getGuiTop() + slot.yPos);
                  RenderUtil.drawMinecraftRectangle(event.getStack(), rx, ry, 16.0F, 16.0F, highlightColor);
               }

            }
         }
      }
   }

   private static class PriceSlot {
      final Slot slot;
      final long price;

      PriceSlot(Slot slot, long price) {
         this.slot = slot;
         this.price = price;
      }
   }
}
