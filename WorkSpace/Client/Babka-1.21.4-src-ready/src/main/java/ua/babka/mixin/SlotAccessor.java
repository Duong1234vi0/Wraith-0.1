package ua.babka.mixin;

import net.minecraft.inventory.Inventory;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={Slot.class})
public interface SlotAccessor {
    @Accessor(value="inventory")
    public Inventory getInventory();

    @Accessor(value="index")
    public int getIndex();
}

