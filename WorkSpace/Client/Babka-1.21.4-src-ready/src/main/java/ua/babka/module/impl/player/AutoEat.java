package ua.babka.module.impl.player;

import com.google.common.eventbus.Subscribe;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.ItemStack;
import ua.babka.event.EventPlayerUpdate;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.SliderSetting;

@ModuleInformation(moduleName="Auto Eat", moduleDesc="Автоприём пищи при голоде", moduleCategory=ModuleCategory.PLAYER)
public class AutoEat
extends Module {
    private final SliderSetting hungerThreshold = new SliderSetting("Порог голода", 14.0, 1.0, 20.0, 1.0);
    private boolean isEating = false;
    private int previousSlot = -1;

    @Subscribe
    private void onUpdate(EventPlayerUpdate e2) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        int currentHunger = this.mc.player.getHungerManager().getFoodLevel();
        if ((double)currentHunger <= this.hungerThreshold.getValue()) {
            int foodSlot = this.findFoodSlot();
            if (foodSlot != -1) {
                if (!this.isEating) {
                    this.previousSlot = this.mc.player.getInventory().selectedSlot;
                    this.mc.player.getInventory().selectedSlot = foodSlot;
                    this.isEating = true;
                }
                this.mc.options.useKey.setPressed(true);
            }
        } else if (this.isEating) {
            this.mc.options.useKey.setPressed(false);
            if (this.previousSlot != -1) {
                this.mc.player.getInventory().selectedSlot = this.previousSlot;
                this.previousSlot = -1;
            }
            this.isEating = false;
        }
    }

    private int findFoodSlot() {
        for (int i2 = 0; i2 < 9; ++i2) {
            FoodComponent food;
            ItemStack stack = this.mc.player.getInventory().getStack(i2);
            if (stack.isEmpty() || (food = (FoodComponent)stack.get(DataComponentTypes.FOOD)) == null) continue;
            return i2;
        }
        return -1;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        if (this.isEating) {
            this.mc.options.useKey.setPressed(false);
            if (this.previousSlot != -1 && this.mc.player != null) {
                this.mc.player.getInventory().selectedSlot = this.previousSlot;
            }
        }
        this.isEating = false;
        this.previousSlot = -1;
    }
}

