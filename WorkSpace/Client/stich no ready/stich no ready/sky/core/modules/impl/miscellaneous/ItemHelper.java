package sky.core.modules.impl.miscellaneous;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.CreativeScreen;
import net.minecraft.client.gui.screen.inventory.ShulkerBoxScreen;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.ByteArrayNBT;
import net.minecraft.nbt.ByteNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.DoubleNBT;
import net.minecraft.nbt.FloatNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntArrayNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.LongArrayNBT;
import net.minecraft.nbt.LongNBT;
import net.minecraft.nbt.ShortNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.client.CClickWindowPacket;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.glfw.GLFW;
import sky.core.events.EventContainerRender;
import sky.core.events.EventCooldown;
import sky.core.events.EventHotbarRender;
import sky.core.events.EventPacket;
import sky.core.events.EventRenderTooltip;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BindSetting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ColorSetting;
import sky.core.modules.api.constructors.impl.TextSetting;
import sky.core.modules.impl.visuals.ShulkerPreview;
import sky.core.ui.Interface.elements.impl.HotBarRender;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.misc.ChatUtil;
import sky.core.utils.misc.ServerUtil;
import sky.core.utils.player.InventoryUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;

public class ItemHelper extends Module {
   private final BindSetting chorus = new BindSetting("Хорус");
   private final BindSetting golden_apple = new BindSetting("Золотое яблоко");
   private final BindSetting enchant_golden_apple = new BindSetting("Чарка");
   private final BindSetting trident = new BindSetting("Трезубец");
   private final BindSetting exp = new BindSetting("Пузырек опыта");
   private final BindSetting shield = new BindSetting("Арбалет");
   private final BindSetting instant_health = new BindSetting("Лук");
   private final TextSetting health = new TextSetting("Здоровье");
   private final BooleanSetting show_instant_health = new BooleanSetting("Подсвечивать зелье исцеления", false);
   private final ColorSetting color_instant_health;
   private final BooleanSetting show_enchant_golden_apple;
   private final ColorSetting color_enchant_golden_apple;
   private final BooleanSetting show_golden_apple;
   private final ColorSetting color_golden_apple;
   private final TextSetting other;
   private final BooleanSetting tes1;
   private final BooleanSetting decreaseCooldown;
   private final BooleanSetting show_new_items;
   private final BooleanSetting show_nbt;
   private final AnimationUtil anim;
   private int oldSlot;

   public ItemHelper() {
      super("Item Helper", "э?", Category.Miscellaneous);
      Integer var10005 = ColorUtil.hex("#FF2AB9");
      BooleanSetting var10006 = this.show_instant_health;
      Objects.requireNonNull(var10006);
      this.color_instant_health = new ColorSetting("Цвет подсветки зелья исцеления", true, var10005, var10006::get);
      this.show_enchant_golden_apple = new BooleanSetting("Подсвечивать чарки", false);
      var10005 = ColorUtil.hex("#FFAC93");
      var10006 = this.show_enchant_golden_apple;
      Objects.requireNonNull(var10006);
      this.color_enchant_golden_apple = new ColorSetting("Цвет подсветки чарки", true, var10005, var10006::get);
      this.show_golden_apple = new BooleanSetting("Подсвечивать золотые яблоки", false);
      var10005 = ColorUtil.hex("#E7EB56");
      var10006 = this.show_golden_apple;
      Objects.requireNonNull(var10006);
      this.color_golden_apple = new ColorSetting("Цвет подсветки золотого яблока", true, var10005, var10006::get);
      this.other = new TextSetting("Остальное");
      this.tes1 = new BooleanSetting("Открывать шалкер в кд", false);
      this.decreaseCooldown = new BooleanSetting("Уменьшать задержку на предметы", false);
      this.show_new_items = new BooleanSetting("Подсвечивать только что поднятые предметы", false);
      this.show_nbt = new BooleanSetting("Отображать nbt предметов", false);
      this.anim = new AnimationUtil(0.0F, 4.0F);
      this.oldSlot = -1;
      this.addSettings(new Setting[]{this.shield, this.instant_health, this.health, this.show_instant_health, this.color_instant_health, this.show_enchant_golden_apple, this.color_enchant_golden_apple, this.show_golden_apple, this.color_golden_apple, this.other, this.decreaseCooldown, this.show_nbt, this.tes1});
   }

   @EventTarget
   public void onPacket(EventPacket e) {
      if ((Boolean)this.tes1.get() && mc.currentScreen instanceof ShulkerBoxScreen && e.isSend()) {
         IPacket var3 = e.getPacket();
         if (var3 instanceof CClickWindowPacket) {
            CClickWindowPacket packet = (CClickWindowPacket)var3;
            if (ServerUtil.isPvP() && packet.getClickType() == ClickType.QUICK_MOVE) {
               Minecraft var10000 = mc;
               ClientPlayNetHandler var4 = Minecraft.player.connection;
               Minecraft var10003 = mc;
               int var5 = Minecraft.player.openContainer.windowId;
               int var10004 = packet.getSlotId();
               int var10005 = InventoryUtil.getSlot(Items.AIR);
               Minecraft var10008 = mc;
               Minecraft var10009 = mc;
               var4.sendPacket(new CClickWindowPacket(var5, var10004, var10005, ClickType.SWAP, ItemStack.EMPTY, Minecraft.player.openContainer.getNextTransactionID(Minecraft.player.inventory)));
               e.setCancelled(true);
            }
         }
      }

   }

   @EventTarget
   public void onUpdate(EventUpdate e) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         int bind = (Integer)this.instant_health.get();
         boolean isBindingPressed = bind >= 0 && GLFW.glfwGetKey(mc.getMainWindow().getHandle(), bind) == 1;
         if (isBindingPressed) {
            int bowSlot = InventoryUtil.getSlot(Items.BOW);
            if (bowSlot == -1) {
               return;
            }

            if (this.oldSlot == -1) {
               Minecraft var10001 = mc;
               this.oldSlot = Minecraft.player.inventory.currentItem;
            }

            var10000 = mc;
            if (Minecraft.player.inventory.currentItem != bowSlot) {
               if (bowSlot < 9) {
                  var10000 = mc;
                  Minecraft.player.inventory.currentItem = bowSlot;
               } else {
                  InventoryUtil.useItemLegit(this, Items.BOW);
               }
            }

            mc.gameSettings.keyBindUseItem.setPressed(true);
            var10000 = mc;
            if (Minecraft.player.getItemInUseMaxCount() >= 20) {
               this.performShot();
            }
         } else if (this.oldSlot != -1) {
            var10000 = mc;
            if (Minecraft.player.getItemInUseMaxCount() > 0) {
               this.performShot();
            } else {
               this.stopDrawing();
            }
         }

      }
   }

   private void performShot() {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         Minecraft var10001 = mc;
         mc.playerController.onStoppedUsingItem(Minecraft.player);
         this.stopDrawing();
      }
   }

   private void stopDrawing() {
      mc.gameSettings.keyBindUseItem.setPressed(false);
      if (this.oldSlot != -1) {
         Minecraft var10000 = mc;
         Minecraft.player.inventory.currentItem = this.oldSlot;
         this.oldSlot = -1;
      }

   }

   @EventTarget
   public void onEvent(EventCooldown event) {
      Item item = event.getItem();
      ItemStack itemStack = new ItemStack(item);
      if ((Boolean)this.decreaseCooldown.get() && itemStack.isFood()) {
         int reduction = item.getFood().isFastEating() ? 16 : 32;
         int originalTicks = event.getTicks();
         if (originalTicks > reduction && itemStack.getItem() != Items.DRIED_KELP) {
            event.setTicks(originalTicks - reduction);
            IFormattableTextComponent message = (new StringTextComponent("Задержка на ")).append(new StringTextComponent(item.getName().getString())).append(new StringTextComponent(" уменьшена на ~" + (double)reduction / 20.0 + " секунды")).setStyle((new StringTextComponent("")).getStyle().applyFormatting(TextFormatting.GRAY));
            ChatUtil.addText(message);
         }
      }

   }

   @EventTarget
   public void onEvent(EventContainerRender.Pre event) {
      if (!(mc.currentScreen instanceof CreativeScreen) && event.getContainer() instanceof PlayerContainer) {
         Iterator var2 = event.getContainer().inventorySlots.iterator();

         while(var2.hasNext()) {
            Slot slot = (Slot)var2.next();
            if (slot != null && slot.getHasStack()) {
               this.renderItemHighlight(event.getStack(), slot.getStack(), (float)(event.getGuiLeft() + slot.xPos), (float)(event.getGuiTop() + slot.yPos));
            }
         }

      }
   }

   @EventTarget
   public void onEvent(EventHotbarRender event) {
      if (!(mc.currentScreen instanceof CreativeScreen)) {
         int screenWidth = mc.getMainWindow().getScaledWidth();
         int screenHeight = mc.getMainWindow().getScaledHeight();
         int baseY = HotBarRender.shouldReplace() ? HotBarRender.getItemY(screenHeight) : screenHeight - 16 - 3;

         for(int i = 0; i < 9; ++i) {
            Minecraft var10000 = mc;
            ItemStack stack = (ItemStack)Minecraft.player.inventory.mainInventory.get(i);
            if (!stack.isEmpty()) {
               int slotX = HotBarRender.shouldReplace() ? HotBarRender.getSlotX(screenWidth, i) : screenWidth / 2 - 90 + i * 20 + 2;
               this.renderItemHighlight(event.getStack(), stack, (float)slotX, (float)baseY);
            }
         }

      }
   }

   private void renderItemHighlight(MatrixStack matrixStack, ItemStack stack, float x, float y) {
      int color = this.getItemHighlightColor(stack);
      if (color != 0) {
         float blinkFactor = (float)(Math.sin((double)(System.currentTimeMillis() / 100L)) * 0.4 + 0.6000000238418579);
         int alpha = (int)(150.0F * blinkFactor);
         RenderUtil.drawMinecraftRectangle(matrixStack, x, y, 16.0F, 16.0F, ColorUtil.getColorWithAlpha(color, (float)alpha));
      }

   }

   private int getItemHighlightColor(ItemStack stack) {
      if ((Boolean)this.show_enchant_golden_apple.get() && stack.getItem() == Items.ENCHANTED_GOLDEN_APPLE) {
         return (Integer)this.color_enchant_golden_apple.get();
      } else if ((Boolean)this.show_golden_apple.get() && stack.getItem() == Items.GOLDEN_APPLE) {
         return (Integer)this.color_golden_apple.get();
      } else {
         return (Boolean)this.show_instant_health.get() && InventoryUtil.stackHasAnyEffect(stack, true, false, false, Effects.INSTANT_HEALTH) ? (Integer)this.color_instant_health.get() : 0;
      }
   }

   @EventTarget
   public void onEvent(EventRenderTooltip event) {
      if (!ShulkerPreview.shouldShowPreview(event.stack)) {
         if ((Boolean)this.show_nbt.get() && event.stack.hasTag()) {
            event.setCancelled(true);
            List tooltip = mc.currentScreen.getTooltipFromItem(event.stack);
            tooltip.add(StringTextComponent.EMPTY);
            this.addTag(tooltip, event.stack.getTag(), 0);
            mc.currentScreen.func_243308_b(event.matrixStack, tooltip, event.mouseX, event.mouseY);
         }
      }
   }

   private void addTag(List tooltip, CompoundNBT tag, int depth) {
      String indent = "  ".repeat(depth);
      Iterator var5 = tag.keySet().iterator();

      while(var5.hasNext()) {
         String key = (String)var5.next();
         INBT base = tag.get(key);
         String type = this.getNbtType(base);
         tooltip.add((new StringTextComponent(indent + "- " + type + ": " + key)).mergeStyle(TextFormatting.DARK_GRAY));
      }

   }

   private String getNbtType(INBT nbt) {
      if (nbt instanceof ByteNBT) {
         return "byte";
      } else if (nbt instanceof ShortNBT) {
         return "short";
      } else if (nbt instanceof IntNBT) {
         return "int";
      } else if (nbt instanceof LongNBT) {
         return "long";
      } else if (nbt instanceof FloatNBT) {
         return "float";
      } else if (nbt instanceof DoubleNBT) {
         return "double";
      } else if (nbt instanceof StringNBT) {
         return "string";
      } else if (nbt instanceof ByteArrayNBT) {
         return "byte[]";
      } else if (nbt instanceof IntArrayNBT) {
         return "int[]";
      } else if (nbt instanceof LongArrayNBT) {
         return "long[]";
      } else if (nbt instanceof ListNBT) {
         return "list";
      } else {
         return nbt instanceof CompoundNBT ? "compound" : "unknown";
      }
   }
}
