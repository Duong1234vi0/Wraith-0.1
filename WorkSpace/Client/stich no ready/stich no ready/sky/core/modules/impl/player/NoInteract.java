package sky.core.modules.impl.player;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import sky.core.events.EventClickBlockRight;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;

public class NoInteract extends Module {
   public BooleanSetting noplacesphere = new BooleanSetting("Не ставить шар,сферу", false);
   public BooleanSetting allBlocks = new BooleanSetting("Все блоки", false);
   public MultiBooleanSetting ignoreInteract = new MultiBooleanSetting("Обьекты", () -> {
      return !(Boolean)this.allBlocks.get();
   }, new BooleanSetting[]{new BooleanSetting("Стойки", true), new BooleanSetting("Сундуки", true), new BooleanSetting("Двери", true), new BooleanSetting("Кнопки", true), new BooleanSetting("Воронки", true), new BooleanSetting("Раздатчики", true), new BooleanSetting("Нотные блоки", true), new BooleanSetting("Верстаки", true), new BooleanSetting("Люки", true), new BooleanSetting("Печки", true), new BooleanSetting("Калитки", true), new BooleanSetting("Наковальни", true), new BooleanSetting("Рычаги", true)});

   public NoInteract() {
      super("No Interact", "Отменяет взаимодействие по выбранным сущностям и блокам", Category.Player);
      this.addSettings(new Setting[]{this.noplacesphere, this.allBlocks, this.ignoreInteract});
   }

   public Set getBlocks() {
      Set blocks = new HashSet();
      this.addBlocksForInteractionType(blocks, "Двери", Blocks.ACACIA_DOOR, Blocks.DARK_OAK_DOOR, Blocks.BIRCH_DOOR, Blocks.IRON_DOOR, Blocks.JUNGLE_DOOR, Blocks.OAK_DOOR, Blocks.SPRUCE_DOOR);
      this.addBlocksForInteractionType(blocks, "Кнопки", Blocks.OAK_BUTTON, Blocks.STONE_BUTTON, Blocks.CRIMSON_BUTTON, Blocks.WARPED_BUTTON);
      this.addBlocksForInteractionType(blocks, "Сундуки", Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.ENDER_CHEST);
      this.addBlocksForInteractionType(blocks, "Воронки", Blocks.HOPPER);
      this.addBlocksForInteractionType(blocks, "Раздатчики", Blocks.DISPENSER, Blocks.DROPPER);
      this.addBlocksForInteractionType(blocks, "Нотные блоки", Blocks.NOTE_BLOCK);
      this.addBlocksForInteractionType(blocks, "Верстаки", Blocks.CRAFTING_TABLE, Blocks.CARTOGRAPHY_TABLE, Blocks.SMITHING_TABLE, Blocks.LOOM);
      this.addBlocksForInteractionType(blocks, "Люки", Blocks.OAK_TRAPDOOR, Blocks.IRON_TRAPDOOR);
      this.addBlocksForInteractionType(blocks, "Печки", Blocks.FURNACE, Blocks.BLAST_FURNACE, Blocks.SMOKER);
      this.addBlocksForInteractionType(blocks, "Калитки", Blocks.ACACIA_FENCE_GATE, Blocks.DARK_OAK_FENCE_GATE, Blocks.BIRCH_FENCE_GATE, Blocks.JUNGLE_FENCE_GATE, Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE_GATE);
      this.addBlocksForInteractionType(blocks, "Наковальни", Blocks.ANVIL);
      this.addBlocksForInteractionType(blocks, "Рычаги", Blocks.LEVER);
      return blocks;
   }

   private void addBlocksForInteractionType(Set blocks, String interactionType, Block... blockIds) {
      if (this.ignoreInteract.is(interactionType)) {
         Collections.addAll(blocks, blockIds);
      }

   }

   @EventTarget
   private void onUpdate(EventClickBlockRight event) {
      if ((Boolean)this.noplacesphere.get() && event.getHand() == Hand.OFF_HAND) {
         Minecraft var10000 = mc;
         if (Minecraft.player.getHeldItemOffhand().getItem() == Items.PLAYER_HEAD) {
            event.setCancelled(true);
         }
      }

      if ((Boolean)this.allBlocks.get()) {
         event.setCancelled(true);
      } else if (this.getBlocks().contains(event.getWorld().getBlockState(event.getResult().getPos()).getBlock())) {
         event.setCancelled(true);
      }

   }
}
