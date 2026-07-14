package ua.babka.module.impl.player;

import com.google.common.eventbus.Subscribe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import ua.babka.core.Rotation;
import ua.babka.event.EventPlayerUpdate;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeListSetting;
import ua.babka.ui.component.RotationComponent;

@ModuleInformation(moduleName="AutoPilot", moduleDesc="Притягивает к выбранным предметам", moduleCategory=ModuleCategory.PLAYER)
public class AutoPilot
extends Module {
    private final ModeListSetting aimAt = new ModeListSetting("Наводиться на", new BooleanSetting("Голову игрока", true), new BooleanSetting("Элитру", true), new BooleanSetting("Осколки", true), new BooleanSetting("Ауры", false));
    private ItemEntity targetItem;

    @Subscribe
    private void onUpdate(EventPlayerUpdate event) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        this.targetItem = null;
        for (Entity entity : this.mc.world.getEntities()) {
            ItemEntity itemEntity;
            if (!(entity instanceof ItemEntity) || !this.isItemOfInterest(itemEntity = (ItemEntity)entity)) continue;
            this.targetItem = itemEntity;
            break;
        }
        if (this.targetItem == null) {
            return;
        }
        Rotation targetRotation = new Rotation(this.targetItem.getPos().add(this.targetItem.getVelocity().multiply(1.1)));
        RotationComponent.update(targetRotation, 360.0f, 360.0f, 360.0f, 360.0f, 1, 5, true);
    }

    private boolean isItemOfInterest(ItemEntity item) {
        Item itemType = item.getStack().getItem();
        return itemType == Items.PLAYER_HEAD && this.aimAt.isEnabled("Голову игрока") || itemType == Items.GHAST_TEAR && this.aimAt.isEnabled("Осколки") || itemType == Items.CLAY_BALL && this.aimAt.isEnabled("Ауры") || itemType == Items.WHITE_DYE && this.aimAt.isEnabled("Ауры") || itemType == Items.POPPED_CHORUS_FRUIT && this.aimAt.isEnabled("Ауры") || itemType == Items.GHAST_TEAR && this.aimAt.isEnabled("Ауры") || itemType == Items.SUNFLOWER && this.aimAt.isEnabled("Ауры") || itemType == Items.GOLD_NUGGET && this.aimAt.isEnabled("Ауры") || itemType == Items.ELYTRA && this.aimAt.isEnabled("Элитру");
    }

    @Override
    public void onDisable() {
        this.targetItem = null;
        super.onDisable();
    }
}

