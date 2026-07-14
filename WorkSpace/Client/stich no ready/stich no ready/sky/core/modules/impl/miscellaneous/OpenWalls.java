package sky.core.modules.impl.miscellaneous;

import java.util.Set;
import net.minecraft.block.AbstractChestBlock;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.BeaconBlock;
import net.minecraft.block.BlastFurnaceBlock;
import net.minecraft.block.BrewingStandBlock;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.CartographyTableBlock;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.GrindstoneBlock;
import net.minecraft.block.LecternBlock;
import net.minecraft.block.LeverBlock;
import net.minecraft.block.LoomBlock;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.SmokerBlock;
import net.minecraft.block.StonecutterBlock;
import sky.core.modules.Category;
import sky.core.modules.Module;

public class OpenWalls extends Module {
   private final Set blockClasses = Set.of(AbstractChestBlock.class, FurnaceBlock.class, CraftingTableBlock.class, ShulkerBoxBlock.class, ContainerBlock.class, AnvilBlock.class, BeaconBlock.class, BlastFurnaceBlock.class, BrewingStandBlock.class, CampfireBlock.class, CartographyTableBlock.class, GrindstoneBlock.class, LecternBlock.class, LoomBlock.class, SmokerBlock.class, StonecutterBlock.class, LeverBlock.class);

   public OpenWalls() {
      super("Open Walls", "Позволяет открывать контейнеры через стены", Category.Miscellaneous);
   }

   public Set getBlockClasses() {
      return this.blockClasses;
   }
}
