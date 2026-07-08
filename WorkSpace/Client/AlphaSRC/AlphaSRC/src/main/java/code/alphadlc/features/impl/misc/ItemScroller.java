/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_1713
 *  net.minecraft.class_1735
 *  net.minecraft.class_1792
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.events.container.HandledScreenEvent;
import code.alphadlc.events.item.ClickSlotEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.math.time.StopWatch;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1792;

public class ItemScroller
extends Module {
    private final StopWatch stopWatch = new StopWatch();
    private final SliderSettings scrollerSetting = new SliderSettings("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0437\u0430\u0434\u0435\u0440\u0436\u043a\u0443 \u043f\u0440\u043e\u043a\u0440\u0443\u0442\u043a\u0438 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u043e\u0432").setValue(100.0f).range(0, 200);

    public ItemScroller() {
        super("ItemScroller", "ItemScroller", ModuleCategory.MISC);
        this.setup(this.scrollerSetting);
    }

    @EventHandler
    public void onHandledScreen(HandledScreenEvent e) {
        class_1713 actionType;
        class_1735 hoverSlot = e.getSlotHover();
        Object object = PlayerInteractionHelper.isKey(ItemScroller.mc.field_1690.field_1869) ? class_1713.field_7795 : (actionType = PlayerInteractionHelper.isKey(ItemScroller.mc.field_1690.field_1886) ? class_1713.field_7794 : null);
        if (PlayerInteractionHelper.isKey(ItemScroller.mc.field_1690.field_1832) && !PlayerInteractionHelper.isKey(ItemScroller.mc.field_1690.field_1867) && hoverSlot != null && hoverSlot.method_7681() && actionType != null && this.stopWatch.every(this.scrollerSetting.getValue())) {
            ItemScroller.mc.field_1761.method_2906(ItemScroller.mc.field_1724.field_7512.field_7763, hoverSlot.field_7874, actionType.equals((Object)class_1713.field_7795) ? 1 : 0, actionType, (class_1657)ItemScroller.mc.field_1724);
        }
    }

    @EventHandler
    public void onClickSlot(ClickSlotEvent e) {
        int slotId = e.getSlotId();
        if (slotId < 0 || slotId > ItemScroller.mc.field_1724.field_7512.field_7761.size()) {
            return;
        }
        class_1735 slot = ItemScroller.mc.field_1724.field_7512.method_7611(slotId);
        class_1792 item = slot.method_7677().method_7909();
        if (item != null && PlayerInteractionHelper.isKey(ItemScroller.mc.field_1690.field_1832) && PlayerInteractionHelper.isKey(ItemScroller.mc.field_1690.field_1867) && this.stopWatch.every(50.0)) {
            InventoryTask.slots().filter(s -> s.method_7677().method_7909().equals(item) && s.field_7871.equals((Object)slot.field_7871)).forEach(s -> ItemScroller.mc.field_1761.method_2906(ItemScroller.mc.field_1724.field_7512.field_7763, s.field_7874, 1, e.getActionType(), (class_1657)ItemScroller.mc.field_1724));
        }
    }
}
