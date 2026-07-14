package sky.core.modules.impl.player;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.minecraft.block.ChestBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ChestScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;

public class KitsChest extends Module {
   private static final float SEARCH_RADIUS = 4.0F;
   private static final long PAUSE_TIME = 60000L;
   private final ModeSetting mode = new ModeSetting("Режим", "Всё", new String[]{"Всё", "Определённые"});
   private final BooleanSetting kitUniversal = new BooleanSetting("Universal", true);
   private final BooleanSetting kitDelta = new BooleanSetting("Delta", true);
   private final BooleanSetting kitSponsor = new BooleanSetting("Sponsor", true);
   private final BooleanSetting kitCobra = new BooleanSetting("Cobra", true);
   private final BooleanSetting kitGod = new BooleanSetting("God", true);
   private final BooleanSetting kitSlime = new BooleanSetting("Slime", true);
   private final BooleanSetting kitFame = new BooleanSetting("Fame", true);
   private final BooleanSetting kitSnowman = new BooleanSetting("Snowman", true);
   private final BooleanSetting kitWinner = new BooleanSetting("Winner", true);
   private final BooleanSetting kitRabbit = new BooleanSetting("Rabbit", false);
   private final BooleanSetting kitTiger = new BooleanSetting("Tiger", false);
   private final BooleanSetting kitBunny = new BooleanSetting("Bunny", false);
   private final BooleanSetting filterFeathers = new BooleanSetting("Ливалки", true, () -> {
      return this.mode.is("Определённые");
   });
   private final BooleanSetting filterTotem = new BooleanSetting("Тотем драконида", true, () -> {
      return this.mode.is("Определённые");
   });
   private final BooleanSetting filterBall = new BooleanSetting("Шар", true, () -> {
      return this.mode.is("Определённые");
   });
   private final BooleanSetting filterFireworks = new BooleanSetting("Фейрверки", true, () -> {
      return this.mode.is("Определённые");
   });
   private final BooleanSetting filterPotions = new BooleanSetting("Зелья", true, () -> {
      return this.mode.is("Определённые");
   });
   private final BooleanSetting filterCleric = new BooleanSetting("Клирик", true, () -> {
      return this.mode.is("Определённые");
   });
   private final BooleanSetting filterObsidian = new BooleanSetting("Обсидиан", true, () -> {
      return this.mode.is("Определённые");
   });
   private final BooleanSetting filterGoldenApples = new BooleanSetting("Золотые яблоки", true, () -> {
      return this.mode.is("Определённые");
   });
   private final BooleanSetting filterEnchantedApples = new BooleanSetting("Зачар. яблоко", true, () -> {
      return this.mode.is("Определённые");
   });
   private final BooleanSetting filterArmor = new BooleanSetting("Броня", true, () -> {
      return this.mode.is("Определённые");
   });
   private final BooleanSetting filterWood = new BooleanSetting("Дерево", true, () -> {
      return this.mode.is("Определённые");
   });
   private int delay = 0;
   private State state;
   private BlockPos targetChest;
   private final List usedChests;
   private long pauseEndTime;
   private int kitIndex;
   private List kitList;

   public KitsChest() {
      super("KitsChest", "Берёт киты и складывает в сундуки", Category.Player);
      this.state = KitsChest.State.TAKE_KIT;
      this.targetChest = null;
      this.usedChests = new ArrayList();
      this.pauseEndTime = 0L;
      this.kitIndex = 0;
      this.kitList = new ArrayList();
      this.addSettings(new Setting[]{this.mode, this.kitUniversal, this.kitDelta, this.kitSponsor, this.kitCobra, this.kitGod, this.kitSlime, this.kitFame, this.kitSnowman, this.kitWinner, this.kitRabbit, this.kitTiger, this.kitBunny, this.filterFeathers, this.filterTotem, this.filterBall, this.filterFireworks, this.filterPotions, this.filterCleric, this.filterObsidian, this.filterGoldenApples, this.filterEnchantedApples, this.filterArmor, this.filterWood});
   }

   public void onEnable() {
      super.onEnable();
      this.delay = 0;
      this.state = KitsChest.State.TAKE_KIT;
      this.targetChest = null;
      this.usedChests.clear();
      this.pauseEndTime = 0L;
      this.kitIndex = 0;
      this.updateKitList();
   }

   private void updateKitList() {
      this.kitList.clear();
      if ((Boolean)this.kitUniversal.get()) {
         this.kitList.add("universal");
      }

      if ((Boolean)this.kitDelta.get()) {
         this.kitList.add("delta");
      }

      if ((Boolean)this.kitSponsor.get()) {
         this.kitList.add("sponsor");
      }

      if ((Boolean)this.kitCobra.get()) {
         this.kitList.add("cobra");
      }

      if ((Boolean)this.kitGod.get()) {
         this.kitList.add("god");
      }

      if ((Boolean)this.kitSlime.get()) {
         this.kitList.add("slime");
      }

      if ((Boolean)this.kitFame.get()) {
         this.kitList.add("fame");
      }

      if ((Boolean)this.kitSnowman.get()) {
         this.kitList.add("snowman");
      }

      if ((Boolean)this.kitWinner.get()) {
         this.kitList.add("winner");
      }

      if ((Boolean)this.kitRabbit.get()) {
         this.kitList.add("rabbit");
      }

      if ((Boolean)this.kitTiger.get()) {
         this.kitList.add("tiger");
      }

      if ((Boolean)this.kitBunny.get()) {
         this.kitList.add("bunny");
      }

   }

   public void onDisable() {
      super.onDisable();
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         var10000 = mc;
         Minecraft.player.closeScreen();
      }

   }

   @EventTarget
   public void onUpdate(EventUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         if (this.pauseEndTime > 0L) {
            if (System.currentTimeMillis() < this.pauseEndTime) {
               return;
            }

            this.pauseEndTime = 0L;
            this.usedChests.clear();
         }

         if (this.delay > 0) {
            --this.delay;
         } else {
            switch (this.state) {
               case TAKE_KIT:
                  this.takeKit();
                  break;
               case FIND_CHEST:
                  this.findChest();
                  break;
               case ROTATE_TO_CHEST:
                  this.rotateToChest();
                  break;
               case OPEN_CHEST:
                  this.openChest();
                  break;
               case PUT_ITEMS:
                  this.putItems();
                  break;
               case CLOSE_CHEST:
                  this.closeChest();
                  break;
               case CLEAR_INV:
                  this.clearInv();
                  break;
               case OPEN_INVENTORY:
                  this.openInventory();
                  break;
               case MOVE_ARMOR_TO_INV:
                  this.moveArmorToInv();
                  break;
               case MOVE_OFFHAND_TO_INV:
                  this.moveOffhandToInv();
                  break;
               case CLOSE_INVENTORY:
                  this.closeInventory();
                  break;
               case REOPEN_CHEST:
                  this.reopenChest();
                  break;
               case PUT_ARMOR_ITEMS:
                  this.putArmorItems();
                  break;
               case CLEAR_AFTER_ARMOR:
                  this.clearAfterArmor();
                  break;
               case WAIT_CYCLE:
                  this.waitCycle();
            }

         }
      }
   }

   private void takeKit() {
      if (this.kitList.isEmpty()) {
         this.updateKitList();
      }

      if (this.kitList.isEmpty()) {
         this.delay = 20;
      } else if (this.kitIndex >= this.kitList.size()) {
         this.pauseEndTime = System.currentTimeMillis() + 60000L;
         this.kitIndex = 0;
         this.kitList.clear();
         this.usedChests.clear();
      } else {
         String kit = (String)this.kitList.get(this.kitIndex);
         Minecraft var10000 = mc;
         Minecraft.player.sendChatMessage("/kit " + kit);
         ++this.kitIndex;
         this.state = KitsChest.State.FIND_CHEST;
         this.delay = 5;
      }
   }

   private void findChest() {
      this.targetChest = this.findNearestChest();
      if (this.targetChest == null) {
         this.delay = 10;
      } else {
         this.state = KitsChest.State.ROTATE_TO_CHEST;
      }
   }

   private void rotateToChest() {
      if (this.targetChest == null) {
         this.state = KitsChest.State.FIND_CHEST;
      } else {
         double var10000 = (double)this.targetChest.getX() + 0.5;
         Minecraft var10001 = mc;
         double dx = var10000 - Minecraft.player.getPosX();
         var10000 = (double)this.targetChest.getY() + 0.5;
         var10001 = mc;
         double var12 = Minecraft.player.getPosY();
         Minecraft var10002 = mc;
         double dy = var10000 - (var12 + (double)Minecraft.player.getEyeHeight());
         var10000 = (double)this.targetChest.getZ() + 0.5;
         var10001 = mc;
         double dz = var10000 - Minecraft.player.getPosZ();
         double dist = Math.sqrt(dx * dx + dz * dz);
         float yaw = (float)Math.toDegrees(Math.atan2(-dx, dz));
         float pitch = (float)(-Math.toDegrees(Math.atan2(dy, dist)));
         Minecraft var11 = mc;
         Minecraft.player.rotationYaw = yaw;
         var11 = mc;
         Minecraft.player.rotationPitch = pitch;
         this.state = KitsChest.State.OPEN_CHEST;
         this.delay = 1;
      }
   }

   private void openChest() {
      if (this.targetChest == null) {
         this.state = KitsChest.State.FIND_CHEST;
      } else {
         Vector3d vec = new Vector3d((double)this.targetChest.getX() + 0.5, (double)this.targetChest.getY() + 0.5, (double)this.targetChest.getZ() + 0.5);
         BlockRayTraceResult result = new BlockRayTraceResult(vec, Direction.UP, this.targetChest, false);
         Minecraft var10001 = mc;
         mc.playerController.rightClickBlock(Minecraft.player, mc.world, Hand.MAIN_HAND, result);
         this.state = KitsChest.State.PUT_ITEMS;
         this.delay = 4;
      }
   }

   private void putItems() {
      if (!(mc.currentScreen instanceof ChestScreen)) {
         this.delay = 2;
      } else {
         Minecraft var10000 = mc;
         int totalSlots = Minecraft.player.openContainer.inventorySlots.size();
         int chestSlots = totalSlots - 36;
         if (!this.hasEmptySlot(chestSlots)) {
            var10000 = mc;
            Minecraft.player.closeScreen();
            this.usedChests.add(this.targetChest);
            this.targetChest = null;
            this.state = KitsChest.State.FIND_CHEST;
            this.delay = 2;
         } else {
            for(int i = chestSlots; i < totalSlots; ++i) {
               var10000 = mc;
               ItemStack stack = Minecraft.player.openContainer.getSlot(i).getStack();
               if (!stack.isEmpty() && this.isValidItem(stack)) {
                  Minecraft var10001 = mc;
                  Minecraft var10005 = mc;
                  mc.playerController.windowClick(Minecraft.player.openContainer.windowId, i, 0, ClickType.QUICK_MOVE, Minecraft.player);
                  this.delay = this.mode.is("Определённые") ? 2 : 0;
                  return;
               }
            }

            this.state = KitsChest.State.CLOSE_CHEST;
            this.delay = this.mode.is("Определённые") ? 2 : 1;
         }
      }
   }

   private void closeChest() {
      Minecraft var10000 = mc;
      Minecraft.player.closeScreen();
      if (this.mode.is("Определённые")) {
         this.state = KitsChest.State.CLEAR_INV;
         this.delay = 2;
      } else {
         boolean hasArmor = this.hasArmor();
         var10000 = mc;
         boolean hasOffhand = !Minecraft.player.getHeldItemOffhand().isEmpty();
         if (!hasArmor && !hasOffhand) {
            this.state = KitsChest.State.WAIT_CYCLE;
            this.delay = 2;
         } else {
            this.state = KitsChest.State.OPEN_INVENTORY;
            this.delay = 2;
         }
      }

   }

   private void clearInv() {
      Minecraft var10000 = mc;
      Minecraft.player.sendChatMessage("/clear");
      if (this.mode.is("Определённые") && (Boolean)this.filterArmor.get() && this.hasArmor()) {
         this.state = KitsChest.State.OPEN_INVENTORY;
         this.delay = 3;
      } else if (this.mode.is("Определённые") && !(Boolean)this.filterArmor.get() && this.hasArmor()) {
         this.state = KitsChest.State.OPEN_INVENTORY;
         this.delay = 3;
      } else {
         this.state = KitsChest.State.WAIT_CYCLE;
         this.delay = 3;
      }

   }

   private void openInventory() {
      Minecraft var10003 = mc;
      mc.displayGuiScreen(new InventoryScreen(Minecraft.player));
      this.state = KitsChest.State.MOVE_ARMOR_TO_INV;
      this.delay = 2;
   }

   private void moveArmorToInv() {
      if (!(mc.currentScreen instanceof InventoryScreen)) {
         this.state = KitsChest.State.OPEN_INVENTORY;
         this.delay = 2;
      } else {
         for(int i = 5; i <= 8; ++i) {
            Minecraft var10000 = mc;
            ItemStack stack = Minecraft.player.openContainer.getSlot(i).getStack();
            if (!stack.isEmpty()) {
               Minecraft var10001 = mc;
               Minecraft var10005 = mc;
               mc.playerController.windowClick(Minecraft.player.openContainer.windowId, i, 0, ClickType.QUICK_MOVE, Minecraft.player);
               this.delay = 2;
               return;
            }
         }

         this.state = KitsChest.State.MOVE_OFFHAND_TO_INV;
         this.delay = 2;
      }
   }

   private void moveOffhandToInv() {
      if (!(mc.currentScreen instanceof InventoryScreen)) {
         this.state = KitsChest.State.OPEN_INVENTORY;
         this.delay = 2;
      } else {
         Minecraft var10000 = mc;
         ItemStack stack = Minecraft.player.openContainer.getSlot(45).getStack();
         if (!stack.isEmpty()) {
            Minecraft var10001 = mc;
            Minecraft var10005 = mc;
            mc.playerController.windowClick(Minecraft.player.openContainer.windowId, 45, 0, ClickType.QUICK_MOVE, Minecraft.player);
            this.delay = 2;
         } else {
            this.state = KitsChest.State.CLOSE_INVENTORY;
            this.delay = 2;
         }
      }
   }

   private void closeInventory() {
      Minecraft var10000 = mc;
      Minecraft.player.closeScreen();
      if (this.hasItems()) {
         this.state = KitsChest.State.REOPEN_CHEST;
         this.delay = 2;
      } else if (this.mode.is("Определённые")) {
         this.state = KitsChest.State.CLEAR_AFTER_ARMOR;
         this.delay = 2;
      } else {
         this.state = KitsChest.State.WAIT_CYCLE;
         this.delay = 2;
      }

   }

   private void reopenChest() {
      if (this.targetChest == null) {
         this.state = KitsChest.State.FIND_CHEST;
      } else {
         Vector3d vec = new Vector3d((double)this.targetChest.getX() + 0.5, (double)this.targetChest.getY() + 0.5, (double)this.targetChest.getZ() + 0.5);
         BlockRayTraceResult result = new BlockRayTraceResult(vec, Direction.UP, this.targetChest, false);
         Minecraft var10001 = mc;
         mc.playerController.rightClickBlock(Minecraft.player, mc.world, Hand.MAIN_HAND, result);
         this.state = KitsChest.State.PUT_ARMOR_ITEMS;
         this.delay = 4;
      }
   }

   private void putArmorItems() {
      if (!(mc.currentScreen instanceof ChestScreen)) {
         this.delay = 2;
      } else {
         Minecraft var10000 = mc;
         int totalSlots = Minecraft.player.openContainer.inventorySlots.size();
         int chestSlots = totalSlots - 36;
         if (!this.hasEmptySlot(chestSlots)) {
            var10000 = mc;
            Minecraft.player.closeScreen();
            this.usedChests.add(this.targetChest);
            this.targetChest = null;
            this.state = KitsChest.State.FIND_CHEST;
            this.delay = 2;
         } else {
            for(int i = chestSlots; i < totalSlots; ++i) {
               var10000 = mc;
               ItemStack stack = Minecraft.player.openContainer.getSlot(i).getStack();
               if (!stack.isEmpty() && this.isValidItem(stack)) {
                  Minecraft var10001 = mc;
                  Minecraft var10005 = mc;
                  mc.playerController.windowClick(Minecraft.player.openContainer.windowId, i, 0, ClickType.QUICK_MOVE, Minecraft.player);
                  return;
               }
            }

            var10000 = mc;
            Minecraft.player.closeScreen();
            if (this.mode.is("Определённые")) {
               this.state = KitsChest.State.CLEAR_AFTER_ARMOR;
               this.delay = 2;
            } else {
               this.state = KitsChest.State.WAIT_CYCLE;
               this.delay = 2;
            }

         }
      }
   }

   private void clearAfterArmor() {
      Minecraft var10000 = mc;
      Minecraft.player.sendChatMessage("/clear");
      this.state = KitsChest.State.WAIT_CYCLE;
      this.delay = 3;
   }

   private void waitCycle() {
      if (this.kitIndex < this.kitList.size()) {
         this.state = KitsChest.State.TAKE_KIT;
         this.delay = 3;
      } else {
         this.pauseEndTime = System.currentTimeMillis() + 60000L;
         this.kitIndex = 0;
         this.kitList.clear();
         this.usedChests.clear();
         this.state = KitsChest.State.TAKE_KIT;
      }

   }

   private boolean isValidItem(ItemStack stack) {
      if (stack != null && !stack.isEmpty()) {
         if (this.mode.is("Всё")) {
            return true;
         } else {
            String name = stack.getDisplayName().getString().toLowerCase();
            if ((Boolean)this.filterFeathers.get() && stack.getItem() == Items.FEATHER) {
               return true;
            } else {
               if ((Boolean)this.filterTotem.get()) {
                  if (stack.getItem() == Items.TOTEM_OF_UNDYING && stack.isEnchanted()) {
                     return true;
                  }

                  if (name.contains("драконид")) {
                     return true;
                  }
               }

               if ((Boolean)this.filterBall.get()) {
                  Minecraft var10000 = mc;
                  ItemStack offhand = Minecraft.player.getHeldItemOffhand();
                  if (!offhand.isEmpty() && ItemStack.areItemsEqual(stack, offhand)) {
                     return true;
                  }
               }

               if ((Boolean)this.filterFireworks.get() && stack.getItem() == Items.FIREWORK_ROCKET) {
                  return true;
               } else if ((Boolean)this.filterPotions.get() && (stack.getItem() == Items.POTION || stack.getItem() == Items.SPLASH_POTION || stack.getItem() == Items.LINGERING_POTION)) {
                  return true;
               } else if ((Boolean)this.filterCleric.get() && name.contains("клирик")) {
                  return true;
               } else if ((Boolean)this.filterObsidian.get() && stack.getItem() == Items.OBSIDIAN) {
                  return true;
               } else if ((Boolean)this.filterGoldenApples.get() && stack.getItem() == Items.GOLDEN_APPLE) {
                  return true;
               } else if ((Boolean)this.filterEnchantedApples.get() && stack.getItem() == Items.ENCHANTED_GOLDEN_APPLE) {
                  return true;
               } else if (!(Boolean)this.filterArmor.get() || stack.getItem() != Items.DIAMOND_HELMET && stack.getItem() != Items.DIAMOND_CHESTPLATE && stack.getItem() != Items.DIAMOND_LEGGINGS && stack.getItem() != Items.DIAMOND_BOOTS && stack.getItem() != Items.NETHERITE_HELMET && stack.getItem() != Items.NETHERITE_CHESTPLATE && stack.getItem() != Items.NETHERITE_LEGGINGS && stack.getItem() != Items.NETHERITE_BOOTS && stack.getItem() != Items.IRON_HELMET && stack.getItem() != Items.IRON_CHESTPLATE && stack.getItem() != Items.IRON_LEGGINGS && stack.getItem() != Items.IRON_BOOTS && stack.getItem() != Items.GOLDEN_HELMET && stack.getItem() != Items.GOLDEN_CHESTPLATE && stack.getItem() != Items.GOLDEN_LEGGINGS && stack.getItem() != Items.GOLDEN_BOOTS && stack.getItem() != Items.CHAINMAIL_HELMET && stack.getItem() != Items.CHAINMAIL_CHESTPLATE && stack.getItem() != Items.CHAINMAIL_LEGGINGS && stack.getItem() != Items.CHAINMAIL_BOOTS && stack.getItem() != Items.LEATHER_HELMET && stack.getItem() != Items.LEATHER_CHESTPLATE && stack.getItem() != Items.LEATHER_LEGGINGS && stack.getItem() != Items.LEATHER_BOOTS) {
                  return (Boolean)this.filterWood.get() && (stack.getItem() == Items.OAK_LOG || stack.getItem() == Items.SPRUCE_LOG || stack.getItem() == Items.BIRCH_LOG || stack.getItem() == Items.JUNGLE_LOG || stack.getItem() == Items.ACACIA_LOG || stack.getItem() == Items.DARK_OAK_LOG || stack.getItem() == Items.CRIMSON_STEM || stack.getItem() == Items.WARPED_STEM || stack.getItem() == Items.OAK_PLANKS || stack.getItem() == Items.SPRUCE_PLANKS || stack.getItem() == Items.BIRCH_PLANKS || stack.getItem() == Items.JUNGLE_PLANKS || stack.getItem() == Items.ACACIA_PLANKS || stack.getItem() == Items.DARK_OAK_PLANKS || stack.getItem() == Items.CRIMSON_PLANKS || stack.getItem() == Items.WARPED_PLANKS);
               } else {
                  return true;
               }
            }
         }
      } else {
         return false;
      }
   }

   private boolean hasArmor() {
      Minecraft var10000 = mc;
      boolean var1;
      if (((ItemStack)Minecraft.player.inventory.armorInventory.get(0)).isEmpty()) {
         var10000 = mc;
         if (((ItemStack)Minecraft.player.inventory.armorInventory.get(1)).isEmpty()) {
            var10000 = mc;
            if (((ItemStack)Minecraft.player.inventory.armorInventory.get(2)).isEmpty()) {
               var10000 = mc;
               if (((ItemStack)Minecraft.player.inventory.armorInventory.get(3)).isEmpty()) {
                  var1 = false;
                  return var1;
               }
            }
         }
      }

      var1 = true;
      return var1;
   }

   private boolean hasItems() {
      for(int i = 0; i < 36; ++i) {
         Minecraft var10000 = mc;
         ItemStack stack = Minecraft.player.inventory.getStackInSlot(i);
         if (!stack.isEmpty() && this.isValidItem(stack)) {
            return true;
         }
      }

      return false;
   }

   private boolean hasEmptySlot(int chestSlots) {
      for(int i = 0; i < chestSlots; ++i) {
         Minecraft var10000 = mc;
         if (Minecraft.player.openContainer.getSlot(i).getStack().isEmpty()) {
            return true;
         }
      }

      return false;
   }

   private BlockPos findNearestChest() {
      List chests = new ArrayList();
      Minecraft var10000 = mc;
      BlockPos playerPos = Minecraft.player.getPosition();
      int radius = (int)Math.ceil(4.0);

      for(int x = -radius; x <= radius; ++x) {
         for(int y = -radius; y <= radius; ++y) {
            for(int z = -radius; z <= radius; ++z) {
               BlockPos pos = playerPos.add(x, y, z);
               if (mc.world.getBlockState(pos).getBlock() instanceof ChestBlock && !this.usedChests.contains(pos)) {
                  var10000 = mc;
                  double dist = Minecraft.player.getDistanceSq((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5);
                  if (!(dist > 16.0)) {
                     chests.add(pos);
                  }
               }
            }
         }
      }

      if (chests.isEmpty()) {
         return null;
      } else {
         return (BlockPos)chests.stream().min(Comparator.comparingDouble((posx) -> {
            Minecraft var10000 = mc;
            return Minecraft.player.getDistanceSq((double)posx.getX() + 0.5, (double)posx.getY() + 0.5, (double)posx.getZ() + 0.5);
         })).orElse((Object)null);
      }
   }

   private static enum State {
      TAKE_KIT,
      FIND_CHEST,
      ROTATE_TO_CHEST,
      OPEN_CHEST,
      PUT_ITEMS,
      CLOSE_CHEST,
      CLEAR_INV,
      OPEN_INVENTORY,
      MOVE_ARMOR_TO_INV,
      MOVE_OFFHAND_TO_INV,
      CLOSE_INVENTORY,
      REOPEN_CHEST,
      PUT_ARMOR_ITEMS,
      CLEAR_AFTER_ARMOR,
      WAIT_CYCLE;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{TAKE_KIT, FIND_CHEST, ROTATE_TO_CHEST, OPEN_CHEST, PUT_ITEMS, CLOSE_CHEST, CLEAR_INV, OPEN_INVENTORY, MOVE_ARMOR_TO_INV, MOVE_OFFHAND_TO_INV, CLOSE_INVENTORY, REOPEN_CHEST, PUT_ARMOR_ITEMS, CLEAR_AFTER_ARMOR, WAIT_CYCLE};
      }
   }
}
