package sky.core.modules.impl.player;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.text.ITextComponent.Serializer;
import sky.core.events.EventChat;
import sky.core.events.EventContainerTick;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ItemSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.ui.gui.autobuy.ItemList;
import sky.core.ui.gui.autobuy.PurchaseHistory;
import sky.core.utils.math.NumberUtil;
import sky.core.utils.misc.ChatUtil;

public class AutoBuy extends Module {
   public static AutoBuy instance;
   public final ModeSetting mode = new ModeSetting("Режим", "Спукитайм", new String[]{"Спукитайм", "Холиворлд"});
   private final BooleanSetting autosetup = new BooleanSetting("Авто сетап", false);
   private final SliderSetting procent = new SliderSetting("На сколько процентов меньше", 20.0F, 1.0F, 100.0F, 1.0F);
   private ItemSetting lastMatchedSetting = null;
   private boolean pendingSell = false;
   private long pendingSellPrice = 0L;
   private boolean moveInitiated = false;
   private int lastPurchasedCount = 1;
   private final List sellQueue = new ArrayList();
   private long nextActionTime = 0L;
   private long nextRefreshTime = 0L;
   private long reopenTime = 0L;
   private boolean autosetupRunning = false;
   private final List autosetupQueue = new ArrayList();
   private int autosetupIndex = 0;
   private int setupState = 0;
   private int stateTimer = 0;
   private long currentItemMinPrice = Long.MAX_VALUE;
   private ItemStack pendingPurchaseItem;
   private long pendingPurchasePrice;
   private ItemSetting pendingPurchaseSetting;
   private static final Pattern PURCHASE_PATTERN = Pattern.compile("Вы успешно купили (.+) за \\$([\\d,]+)");
   private long chatBlockTime;

   public AutoBuy() {
      super("Auto Buy", "Упрощает покупку предметов", Category.Player);
      this.pendingPurchaseItem = ItemStack.EMPTY;
      this.pendingPurchasePrice = 0L;
      this.pendingPurchaseSetting = null;
      this.chatBlockTime = 0L;
      instance = this;
      this.addSettings(new Setting[]{this.mode, this.autosetup, this.procent});
   }

   public void onEnable() {
      super.onEnable();
      this.resetAll();
   }

   public void onDisable() {
      super.onDisable();
      this.resetAll();
   }

   @EventTarget
   @Native
   public void onChat(EventChat event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && this.isEnabled()) {
         Matcher matcher = PURCHASE_PATTERN.matcher(event.getMessage().replaceAll("§.", "").replaceAll("[☃\\[\\]]", "").trim());
         if (matcher.find()) {
            try {
               long boughtPrice = Long.parseLong(matcher.group(2).replace(",", ""));
               if (!this.pendingPurchaseItem.isEmpty() && this.pendingPurchaseSetting != null) {
                  PurchaseHistory.add(this.pendingPurchaseItem.copy(), boughtPrice, "Аукцион");
                  if (this.pendingPurchaseSetting.isSellEnabled()) {
                     double markup = 1.0 + (double)this.pendingPurchaseSetting.getSellPercent() / 100.0;
                     long totalSellPrice = (long)((double)boughtPrice * markup);
                     this.sellQueue.add(new SellTask(this.pendingPurchaseSetting, this.lastPurchasedCount, totalSellPrice));
                     this.pendingSell = true;
                     this.pendingSellPrice = totalSellPrice;
                  }

                  this.pendingPurchaseItem = ItemStack.EMPTY;
                  this.pendingPurchaseSetting = null;
               }
            } catch (Exception var9) {
            }
         }

      }
   }

   @EventTarget
   @Native
   public void onUpdate(EventUpdate e) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         if ((Boolean)this.autosetup.get()) {
            if (!this.autosetupRunning) {
               this.autosetupQueue.clear();
               Stream var3 = ItemList.getItems().stream().filter(Setting::get);
               List var10001 = this.autosetupQueue;
               Objects.requireNonNull(var10001);
               var3.forEach(var10001::add);
               if (this.autosetupQueue.isEmpty()) {
                  this.autosetup.set(false);
                  return;
               }

               this.autosetupRunning = true;
               this.autosetupIndex = 0;
               this.setupState = 0;
            }

            this.processAutoSetupState();
         } else if (this.autosetupRunning) {
            this.autosetupRunning = false;
         }

         if (this.pendingSell) {
            this.handleAutoSell();
         }

         if (!this.pendingSell && !this.sellQueue.isEmpty()) {
            SellTask next = (SellTask)this.sellQueue.get(0);
            this.lastMatchedSetting = next.setting;
            this.lastPurchasedCount = next.count;
            this.pendingSellPrice = next.price;
            this.pendingSell = true;
            this.moveInitiated = false;
         }

         if (!this.pendingSell && this.reopenTime > 0L && System.currentTimeMillis() > this.reopenTime) {
            if (System.currentTimeMillis() < this.chatBlockTime) {
               return;
            }

            if (mc.currentScreen != null) {
               if (mc.currentScreen instanceof ContainerScreen && mc.currentScreen.getTitle().getString().toLowerCase().contains("аукцион")) {
                  return;
               }

               var10000 = mc;
               Minecraft.player.closeScreen();
               return;
            }

            var10000 = mc;
            Minecraft.player.sendChatMessage("/ah");
            this.chatBlockTime = System.currentTimeMillis() + 1000L;
            this.reopenTime = 0L;
         }

      }
   }

   @Native
   private void processAutoSetupState() {
      Minecraft var10000;
      if (this.autosetupIndex >= this.autosetupQueue.size()) {
         this.autosetupRunning = false;
         this.autosetup.set(false);
         var10000 = mc;
         if (Minecraft.player != null) {
            var10000 = mc;
            Minecraft.player.closeScreen();
         }

      } else {
         ItemSetting cur = (ItemSetting)this.autosetupQueue.get(this.autosetupIndex);
         boolean isHoly = this.mode.is("Холиворлд");
         switch (this.setupState) {
            case 0:
               if (mc.currentScreen != null) {
                  var10000 = mc;
                  Minecraft.player.closeScreen();
               }

               String searchTerm = ItemList.getSearchNameForItem(cur);
               var10000 = mc;
               ClientPlayerEntity var6 = Minecraft.player;
               String var10001 = searchTerm.replaceAll("§.", "").trim();
               var6.sendChatMessage("/ah search " + var10001);
               this.currentItemMinPrice = Long.MAX_VALUE;
               this.stateTimer = isHoly ? 80 : 10;
               this.setupState = 1;
               break;
            case 1:
               if (this.isAuctionOpen()) {
                  this.stateTimer = 15;
                  this.setupState = 2;
               } else if (--this.stateTimer <= 0) {
                  this.setupState = 3;
               }
               break;
            case 2:
               if (--this.stateTimer <= 0 && !this.isAuctionOpen()) {
                  this.setupState = 3;
               }
               break;
            case 3:
               if (this.currentItemMinPrice != Long.MAX_VALUE) {
                  long np = Math.max(1L, (long)((double)this.currentItemMinPrice * (1.0 - (double)(Float)this.procent.get() / 100.0)));
                  cur.setMaxPrice(np);
                  cur.setMaxPriceFromString(NumberUtil.formatThousands(String.valueOf(np)));
                  ChatUtil.addChatMessage(String.format("&f%s: &a%s$ &f-> &b%s$", cur.getName(), NumberUtil.formatThousands(String.valueOf(this.currentItemMinPrice)), NumberUtil.formatThousands(String.valueOf(np))));
               } else {
                  ChatUtil.addChatMessage("&f" + cur.getName() + ": &cПредмет не найден");
               }

               ++this.autosetupIndex;
               var10000 = mc;
               if (Minecraft.player != null) {
                  var10000 = mc;
                  Minecraft.player.closeScreen();
               }

               this.stateTimer = isHoly ? 40 : 2;
               this.setupState = 4;
               break;
            case 4:
               if (--this.stateTimer <= 0) {
                  this.setupState = 0;
               }
         }

      }
   }

   @EventTarget
   public void onEvent(EventContainerTick e) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         if (System.currentTimeMillis() >= this.chatBlockTime) {
            long now = System.currentTimeMillis();
            if (this.mode.is("Холиворлд")) {
               String title = this.getCurrentScreenTitle();
               if (title != null) {
                  String clean = title.replaceAll("§.", "").toLowerCase();
                  if (clean.contains("покупка") || clean.contains("confirm")) {
                     if (now < this.nextActionTime) {
                        return;
                     }

                     List green = new ArrayList();

                     for(int i = 0; i < e.getItems().size(); ++i) {
                        if (((ItemStack)e.getItems().get(i)).getItem() == Items.LIME_STAINED_GLASS_PANE) {
                           green.add(i);
                        }
                     }

                     if (!green.isEmpty()) {
                        this.clickSlot((Integer)green.get(ThreadLocalRandom.current().nextInt(green.size())));
                        this.setRandomDelay();
                     }

                     return;
                  }
               }
            }

            if ((Boolean)this.autosetup.get() && this.autosetupRunning && this.setupState == 2 && this.stateTimer <= 0) {
               this.scanCurrentPage(e);
            } else if (!(Boolean)this.autosetup.get()) {
               this.handleAutoBuy(e);
            }

         }
      }
   }

   private void scanCurrentPage(EventContainerTick e) {
      if (this.autosetupIndex < this.autosetupQueue.size()) {
         ItemSetting current = (ItemSetting)this.autosetupQueue.get(this.autosetupIndex);
         boolean isChunker = current.getName().contains("Прогрузчик чанков");

         for(int i = 0; i < 45; ++i) {
            ItemStack stack = (ItemStack)e.getItems().get(i);
            if (stack != null && !stack.isEmpty() && !this.isDecorativeItem(stack)) {
               long totalPrice = extractPrice(stack);
               if (totalPrice > 0L) {
                  boolean matches = false;
                  if (isChunker) {
                     String displayName = stack.getDisplayName().getString().replaceAll("§.", "");
                     if (current.getName().contains("[1x1]") && displayName.contains("[1x1]")) {
                        matches = true;
                     } else if (current.getName().contains("[3x3]") && displayName.contains("[3x3]")) {
                        matches = true;
                     } else if (current.getName().contains("[5x5]") && displayName.contains("[5x5]")) {
                        matches = true;
                     }
                  } else {
                     matches = this.matchesForAutoSetup(stack, current);
                  }

                  if (matches) {
                     long unitPrice = totalPrice / (long)Math.max(1, stack.getCount());
                     if (unitPrice < this.currentItemMinPrice) {
                        this.currentItemMinPrice = unitPrice;
                     }
                  }
               }
            }
         }

         this.setupState = 3;
      }
   }

   private void handleAutoBuy(EventContainerTick e) {
      Minecraft var10000 = mc;
      if (Minecraft.player.openContainer != null) {
         var10000 = mc;
         Minecraft var10001 = mc;
         if (Minecraft.player.openContainer != Minecraft.player.container && !this.pendingSell) {
            long now = System.currentTimeMillis();
            if (now < this.nextActionTime) {
               return;
            }

            if (this.mode.is("Холиворлд") && ThreadLocalRandom.current().nextFloat() > 0.9F) {
               return;
            }

            String title = this.getCurrentScreenTitle();
            if (title != null && (this.mode.is("Холиворлд") || title.replaceAll("§.", "").toLowerCase().contains("аукцион"))) {
               List settings = ItemList.getItems();
               int startSlot = this.mode.is("Холиворлд") ? ThreadLocalRandom.current().nextInt(5) : 0;

               int i;
               ItemStack stack;
               long price;
               for(i = startSlot; i < 45; ++i) {
                  if (!this.mode.is("Холиворлд") || i < 36) {
                     stack = (ItemStack)e.getItems().get(i);
                     if (stack != null && !stack.isEmpty() && !this.isDecorativeItem(stack)) {
                        price = extractPrice(stack);
                        if (price > 0L) {
                           String fullText = this.getStackFullText(stack);
                           Iterator var12 = settings.iterator();

                           while(var12.hasNext()) {
                              ItemSetting setting = (ItemSetting)var12.next();
                              if ((Boolean)setting.get() && this.matchesRequiredNbt(stack, setting, fullText)) {
                                 long unitPrice = price / (long)Math.max(1, stack.getCount());
                                 if (setting.getMaxPrice() > 0L && unitPrice <= setting.getMaxPrice()) {
                                    this.pendingPurchaseItem = stack.copy();
                                    this.pendingPurchasePrice = price;
                                    this.pendingPurchaseSetting = setting;
                                    this.lastPurchasedCount = stack.getCount();
                                    if (this.mode.is("Холиворлд")) {
                                       this.clickSlot(i);
                                    } else {
                                       this.shiftClickSlot(i);
                                    }

                                    this.lastMatchedSetting = setting;
                                    this.nextActionTime = System.currentTimeMillis() + (long)(this.mode.is("Холиворлд") ? 500 : 0);
                                    return;
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               if (now >= this.nextRefreshTime) {
                  for(i = 45; i < 54; ++i) {
                     var10000 = mc;
                     stack = ((Slot)Minecraft.player.openContainer.inventorySlots.get(i)).getStack();
                     if (!stack.isEmpty() && (stack.getItem() == Items.SUNFLOWER || stack.getDisplayName().getString().toLowerCase().contains("обновить") || this.mode.is("Спукитайм") && stack.getItem() == Items.NETHER_STAR)) {
                        this.clickSlot(i);
                        price = this.mode.is("Холиворлд") ? ThreadLocalRandom.current().nextLong(1200L, 2400L) : ThreadLocalRandom.current().nextLong(20L, 120L);
                        this.nextRefreshTime = System.currentTimeMillis() + price;
                        this.nextActionTime = System.currentTimeMillis() + 200L;
                        break;
                     }
                  }
               }

               return;
            }

            return;
         }
      }

   }

   private void setRandomDelay() {
      this.nextActionTime = System.currentTimeMillis() + (this.mode.is("Холиворлд") ? ThreadLocalRandom.current().nextLong(80L, 300L) : 0L);
   }

   private void handleAutoSell() {
      if (this.sellQueue.isEmpty()) {
         this.pendingSell = false;
      } else if (System.currentTimeMillis() >= this.chatBlockTime) {
         SellTask currentTask = (SellTask)this.sellQueue.get(0);
         Minecraft var10000;
         if (mc.currentScreen != null && !(mc.currentScreen instanceof ContainerScreen)) {
            var10000 = mc;
            Minecraft.player.closeScreen();
         } else if (!this.moveInitiated) {
            int invSlot = this.findInventorySlotForSetting(currentTask.setting);
            if (invSlot >= 0) {
               int targetSlot = invSlot < 9 ? invSlot : this.splitStackToExact(invSlot, currentTask.count);
               if (targetSlot >= 0 && targetSlot < 9) {
                  var10000 = mc;
                  Minecraft.player.inventory.currentItem = targetSlot;
                  this.moveInitiated = true;
                  this.chatBlockTime = System.currentTimeMillis() + 500L;
               }
            }

         } else {
            var10000 = mc;
            Minecraft.player.sendChatMessage("/ah sell " + currentTask.price);
            this.chatBlockTime = System.currentTimeMillis() + 1000L;
            this.sellQueue.remove(0);
            this.moveInitiated = false;
            this.lastMatchedSetting = null;
            if (this.sellQueue.isEmpty()) {
               this.pendingSell = false;
               this.reopenTime = System.currentTimeMillis() + 2000L;
            }

         }
      }
   }

   private void resetAll() {
      this.pendingSell = false;
      this.autosetupRunning = false;
      this.autosetupQueue.clear();
      this.reopenTime = 0L;
      this.nextActionTime = 0L;
      this.setupState = 0;
      this.stateTimer = 0;
      this.currentItemMinPrice = Long.MAX_VALUE;
      this.lastMatchedSetting = null;
      this.pendingPurchaseItem = ItemStack.EMPTY;
      this.pendingPurchasePrice = 0L;
      this.pendingPurchaseSetting = null;
   }

   public static long extractPrice(ItemStack stack) {
      if (!stack.hasTag()) {
         return 0L;
      } else {
         CompoundNBT display = stack.getTag().getCompound("display");
         if (!display.contains("Lore", 9)) {
            return 0L;
         } else {
            ListNBT lore = display.getList("Lore", 8);

            for(int i = 0; i < lore.size(); ++i) {
               String text = "";

               try {
                  text = Serializer.getComponentFromJsonLenient(lore.getString(i)).getString();
               } catch (Exception var9) {
                  text = lore.getString(i);
               }

               String clean = text.replaceAll("§.", "").toLowerCase();
               if (clean.contains("$") || clean.contains("цена") || clean.contains("price") || clean.contains("монет")) {
                  try {
                     long price = Long.parseLong(clean.replaceAll("[^0-9]", ""));
                     if (price > 0L) {
                        return price;
                     }
                  } catch (Exception var8) {
                  }
               }
            }

            return 0L;
         }
      }
   }

   private boolean matchesForAutoSetup(ItemStack stack, ItemSetting setting) {
      if (this.mode.is("Холиворлд")) {
         return this.matchesRequiredNbt(stack, setting, this.getStackFullText(stack));
      } else if (setting.getItemStack() != null && stack.getItem() != setting.getItemStack().getItem()) {
         return false;
      } else {
         String t = (setting.getSearchQuery() == null ? setting.getName() : setting.getSearchQuery()).toLowerCase();
         return stack.getDisplayName().getString().replaceAll("§.", "").toLowerCase().contains(t);
      }
   }

   private boolean matchesRequiredNbt(ItemStack stack, ItemSetting setting, String fullText) {
      List pr = setting.getRequiredNbtParameters();
      if (pr != null && !pr.isEmpty()) {
         Iterator var5 = pr.iterator();

         while(var5.hasNext()) {
            String p = (String)var5.next();
            if (!fullText.contains(p.toLowerCase())) {
               return false;
            }
         }
      }

      if (!this.mode.is("Холиворлд")) {
         if (setting.getItemStack() != null && stack.getItem() != setting.getItemStack().getItem()) {
            return false;
         } else {
            String t = (setting.getSearchQuery() == null ? setting.getName() : setting.getSearchQuery()).toLowerCase();
            if (!fullText.contains(t)) {
               return false;
            } else {
               if (stack.hasTag()) {
                  if (!this.matchesRequiredEnchantments(stack.getTag(), setting)) {
                     return false;
                  }

                  if (setting.isRequireUnbreakable() && !stack.getTag().getBoolean("Unbreakable") && stack.getTag().getByte("Unbreakable") == 0) {
                     return false;
                  }
               } else if (setting.isRequireUnbreakable()) {
                  return false;
               }

               return true;
            }
         }
      } else {
         return pr != null && !pr.isEmpty() || stack.getItem() == setting.getItemStack().getItem();
      }
   }

   private boolean matchesRequiredEnchantments(CompoundNBT tag, ItemSetting setting) {
      Map req = setting.getRequiredEnchantments();
      if (req != null && !req.isEmpty()) {
         Map present = new HashMap();
         if (tag.contains("Enchantments", 9)) {
            this.addEnch(present, tag.getList("Enchantments", 10));
         }

         if (tag.contains("StoredEnchantments", 9)) {
            this.addEnch(present, tag.getList("StoredEnchantments", 10));
         }

         Iterator var5 = req.entrySet().iterator();

         Map.Entry e;
         do {
            if (!var5.hasNext()) {
               return true;
            }

            e = (Map.Entry)var5.next();
         } while((Integer)present.getOrDefault(e.getKey(), 0) >= (Integer)e.getValue());

         return false;
      } else {
         return true;
      }
   }

   private void addEnch(Map map, ListNBT list) {
      for(int i = 0; i < list.size(); ++i) {
         CompoundNBT e = list.getCompound(i);
         map.put(e.getString("id").replace("minecraft:", ""), Math.max((Integer)map.getOrDefault(e.getString("id"), 0), e.getInt("lvl")));
      }

   }

   private void clickSlot(int slot) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         var10000 = mc;
         if (Minecraft.player.openContainer != null) {
            Minecraft var10001 = mc;
            Minecraft var10005 = mc;
            mc.playerController.windowClick(Minecraft.player.openContainer.windowId, slot, 0, ClickType.PICKUP, Minecraft.player);
         }
      }

   }

   private void shiftClickSlot(int slot) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         var10000 = mc;
         if (Minecraft.player.openContainer != null) {
            Minecraft var10001 = mc;
            Minecraft var10005 = mc;
            mc.playerController.windowClick(Minecraft.player.openContainer.windowId, slot, 0, ClickType.QUICK_MOVE, Minecraft.player);
         }
      }

   }

   private String getStackFullText(ItemStack stack) {
      StringBuilder sb = new StringBuilder(stack.getDisplayName().getString());
      if (stack.hasTag() && stack.getTag().getCompound("display").contains("Lore", 9)) {
         ListNBT lore = stack.getTag().getCompound("display").getList("Lore", 8);

         for(int i = 0; i < lore.size(); ++i) {
            try {
               sb.append(" ").append(Serializer.getComponentFromJsonLenient(lore.getString(i)).getString());
            } catch (Exception var6) {
               sb.append(" ").append(lore.getString(i));
            }
         }
      }

      return sb.toString().replaceAll("(?i)§[0-9A-FK-ORX]", "").toLowerCase();
   }

   private String getCurrentScreenTitle() {
      return mc.currentScreen instanceof ContainerScreen ? mc.currentScreen.getTitle().getString() : null;
   }

   private boolean isAuctionOpen() {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         var10000 = mc;
         if (Minecraft.player.openContainer != null) {
            String t = this.getCurrentScreenTitle();
            boolean var2;
            if (t == null || !t.toLowerCase().contains("аукцион") && !t.toLowerCase().contains("поиск")) {
               var10000 = mc;
               if (Minecraft.player.openContainer.inventorySlots.size() <= 53) {
                  var2 = false;
                  return var2;
               }
            }

            var2 = true;
            return var2;
         }
      }

      return false;
   }

   private boolean isDecorativeItem(ItemStack s) {
      return s.getItem() == Items.BLACK_STAINED_GLASS_PANE || s.getItem() == Items.GRAY_STAINED_GLASS_PANE || s.getItem() == Items.WHITE_STAINED_GLASS_PANE || s.getItem() == Items.LIGHT_GRAY_STAINED_GLASS_PANE || s.getDisplayName().getString().trim().isEmpty();
   }

   private int findInventorySlotForSetting(ItemSetting setting) {
      for(int i = 0; i < 45; ++i) {
         Minecraft var10000 = mc;
         ItemStack s = Minecraft.player.inventory.getStackInSlot(i);
         if (!s.isEmpty() && this.matchesRequiredNbt(s, setting, this.getStackFullText(s))) {
            return i;
         }
      }

      return -1;
   }

   private int splitStackToExact(int src, int count) {
      if (count <= 0) {
         return -1;
      } else {
         Minecraft var10000 = mc;
         int winId = Minecraft.player.openContainer.windowId;
         int empty = -1;

         int i;
         for(i = 9; i < 36; ++i) {
            var10000 = mc;
            if (Minecraft.player.inventory.getStackInSlot(i).isEmpty()) {
               empty = i;
               break;
            }
         }

         if (empty == -1) {
            return -1;
         } else {
            int var10002 = src < 9 ? src + 36 : src;
            Minecraft var10005 = mc;
            mc.playerController.windowClick(winId, var10002, 0, ClickType.PICKUP, Minecraft.player);

            for(i = 0; i < count; ++i) {
               var10002 = empty < 9 ? empty + 36 : empty;
               var10005 = mc;
               mc.playerController.windowClick(winId, var10002, 1, ClickType.PICKUP, Minecraft.player);
            }

            var10002 = src < 9 ? src + 36 : src;
            var10005 = mc;
            mc.playerController.windowClick(winId, var10002, 0, ClickType.PICKUP, Minecraft.player);
            return empty;
         }
      }
   }

   private static class SellTask {
      final ItemSetting setting;
      final int count;
      final long price;

      SellTask(ItemSetting s, int c, long p) {
         this.setting = s;
         this.count = c;
         this.price = p;
      }
   }
}
