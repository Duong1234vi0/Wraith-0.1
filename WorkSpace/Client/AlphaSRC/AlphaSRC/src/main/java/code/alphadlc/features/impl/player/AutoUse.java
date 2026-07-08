/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1291
 *  net.minecraft.class_1294
 *  net.minecraft.class_1735
 *  net.minecraft.class_1799
 *  net.minecraft.class_6880
 */
package code.alphadlc.features.impl.player;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.interactions.inv.InventoryFlowManager;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.interactions.item.ItemToolkit;
import code.alphadlc.utils.math.script.Script;
import java.util.Iterator;
import net.minecraft.class_1268;
import net.minecraft.class_1291;
import net.minecraft.class_1294;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_6880;

public class AutoUse
extends Module {
    private final Script script = new Script();
    private final MultiSelectSetting multiSetting = new MultiSelectSetting("\u0410\u0432\u0442\u043e \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0435", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435, \u0447\u0442\u043e \u0431\u0443\u0434\u0435\u0442 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c\u0441\u044f").value("\u0415\u0434\u044b", "\u041d\u0435\u0432\u0438\u0434\u0438\u043c\u043e\u0441\u0442\u0438");

    public AutoUse() {
        super("AutoUse", "AutoUse", ModuleCategory.PLAYER);
        this.setup(this.multiSetting);
    }

    @Override
    public void deactivate() {
        this.script.update();
    }

    @EventHandler
    public void onTick(TickEvent e) {
        Iterator<String> iterator = this.multiSetting.getSelected().iterator();
        block8: while (iterator.hasNext()) {
            String string;
            switch (string = iterator.next()) {
                case "\u0415\u0434\u044b": {
                    class_1735 slot = InventoryTask.getFoodMaxSaturationSlot();
                    if (slot == null || !AutoUse.mc.field_1724.method_7344().method_7587() || !this.swapAndEat(slot)) continue block8;
                    return;
                }
                case "\u041d\u0435\u0432\u0438\u0434\u0438\u043c\u043e\u0441\u0442\u0438": {
                    class_1735 slot = InventoryTask.getPotion((class_6880<class_1291>)class_1294.field_5905);
                    if (slot == null || PlayerInteractionHelper.isPotionActive((class_6880<class_1291>)class_1294.field_5905) || !this.swapAndEat(slot)) break;
                    return;
                }
            }
        }
        this.script.update();
    }

    public boolean swapAndEat(class_1735 slot) {
        class_1799 stack = slot.method_7677();
        if (!AutoUse.mc.field_1724.method_7357().method_7904(stack)) {
            if (!AutoUse.mc.field_1724.method_6079().equals(stack)) {
                if (InventoryFlowManager.script.isFinished()) {
                    InventoryTask.swapHand(slot, class_1268.field_5810, true, true);
                    this.script.cleanup().addTickStep(0, () -> InventoryTask.swapHand(slot, class_1268.field_5810, true, true));
                }
            } else {
                ItemToolkit.INSTANCE.useHand(class_1268.field_5810);
            }
            return true;
        }
        return false;
    }
}
