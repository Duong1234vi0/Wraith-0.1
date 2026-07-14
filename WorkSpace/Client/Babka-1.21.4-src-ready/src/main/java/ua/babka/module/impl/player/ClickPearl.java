package ua.babka.module.impl.player;

import com.google.common.eventbus.Subscribe;
import net.minecraft.item.Items;
import ua.babka.event.EventKeyInput;
import ua.babka.event.EventPlayerSync;
import ua.babka.event.EventPlayerUpdate;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BindSetting;
import ua.babka.module.setting.ModeSetting;
import ua.babka.util.player.InventoryUtil;

@ModuleInformation(moduleName="Click Pearl", moduleDesc="Клик перл через настройки", moduleCategory=ModuleCategory.PLAYER)
public class ClickPearl
extends Module {
    private final ModeSetting mode = new ModeSetting("Мод", "Обычный", "Обычный", "Легитный");
    private final BindSetting key = new BindSetting("Клавиша броска", -98);
    private boolean pearlUsed;
    private int ticksExisted;

    @Subscribe
    private void onKey(EventKeyInput e2) {
        if (e2.getAction() == 0) {
            return;
        }
        if (e2.getKey() == this.key.getValue().intValue()) {
            if (this.mode.is("Обычный")) {
                InventoryUtil.swapAndUseHvH(Items.ENDER_PEARL);
            } else {
                this.pearlUsed = true;
            }
        }
    }

    @Subscribe
    private void onPlayerTick(EventPlayerUpdate ignored) {
        if (this.mc.player == null) {
            return;
        }
        if (!this.pearlUsed && this.ticksExisted > 0) {
            --this.ticksExisted;
        }
        if (this.pearlUsed || this.ticksExisted > 0) {
            this.mc.player.setSprinting(false);
        }
    }

    @Subscribe
    private void onPlayerSync(EventPlayerSync ignored) {
        if (this.mc.player == null || !this.pearlUsed) {
            return;
        }
        int slotHotbar = InventoryUtil.searchItem(Items.ENDER_PEARL, 0, 9);
        if (slotHotbar != -1) {
            InventoryUtil.swapAndUseLegit(Items.ENDER_PEARL);
        } else {
            if (this.ticksExisted == 0) {
                ++this.ticksExisted;
                return;
            }
            InventoryUtil.swapAndUseLegit(Items.ENDER_PEARL);
            this.ticksExisted = 2;
        }
        this.pearlUsed = false;
    }
}

