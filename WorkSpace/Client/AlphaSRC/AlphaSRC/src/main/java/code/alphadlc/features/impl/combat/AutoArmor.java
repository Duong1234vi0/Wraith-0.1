/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1304
 *  net.minecraft.class_1304$class_1305
 *  net.minecraft.class_1713
 *  net.minecraft.class_1735
 *  net.minecraft.class_1738
 *  net.minecraft.class_1741
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1890
 *  net.minecraft.class_1893
 *  net.minecraft.class_2378
 *  net.minecraft.class_6880
 *  net.minecraft.class_7924
 */
package code.alphadlc.features.impl.combat;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.impl.render.Hud;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.Setting;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.interfaces.IArmorItem;
import code.alphadlc.utils.interactions.inv.InventoryFlowManager;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import java.util.ArrayList;
import java.util.Comparator;
import net.minecraft.class_1304;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1738;
import net.minecraft.class_1741;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1890;
import net.minecraft.class_1893;
import net.minecraft.class_2378;
import net.minecraft.class_6880;
import net.minecraft.class_7924;

public class AutoArmor
extends Module {
    public AutoArmor() {
        super("AutoArmor", "AutoArmor", ModuleCategory.PLAYER);
        this.setup(new Setting[0]);
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if (InventoryTask.isServerScreen()) {
            return;
        }
        if (!InventoryFlowManager.script.isFinished()) {
            return;
        }
        ArrayList<Runnable> list = new ArrayList<Runnable>();
        for (class_1304 equipment : class_1304.values()) {
            class_1799 equipStack = AutoArmor.mc.field_1724.method_31548().method_7372(equipment.method_5927());
            if (equipment.method_5925() != class_1304.class_1305.field_6178 || equipment.equals((Object)class_1304.field_6174) && equipStack.method_7909().equals(class_1802.field_8833)) continue;
            int armorSlot = 8 - equipment.method_5927();
            class_1735 slot = InventoryTask.getSlot(s -> {
                class_1738 armorItem;
                class_1792 patt0$temp;
                class_1799 stack = s.method_7677();
                return s.field_7874 != armorSlot && !this.isBroken(stack) && !this.hasCurseOfBinding(stack) && (patt0$temp = stack.method_7909()) instanceof class_1738 && ((IArmorItem)(armorItem = (class_1738)patt0$temp)).zov_pidarok$getType().method_48399().equals((Object)equipment);
            }, Comparator.comparingDouble(s -> this.calculateArmorValue(s.method_7677(), (IArmorItem)s.method_7677().method_7909())));
            if (slot != null && this.isBetter(slot.method_7677(), equipStack)) {
                list.add(() -> InventoryTask.moveItem(slot, armorSlot));
                continue;
            }
            if (!this.isBroken(equipStack)) continue;
            Hud hud = Hud.getInstance();
            if (slot != null) {
                list.add(() -> InventoryTask.moveItem(slot, armorSlot));
                continue;
            }
            if (InventoryTask.getSlot(class_1802.field_8162, s -> s.field_7874 >= 9) == null) continue;
            list.add(() -> InventoryTask.clickSlot(armorSlot, 0, class_1713.field_7794, false));
        }
        if (!list.isEmpty()) {
            InventoryFlowManager.addTask(() -> {
                list.forEach(Runnable::run);
                InventoryTask.updateSlots();
            });
        }
    }

    private float calculateArmorValue(class_1799 stack, IArmorItem armorItem) {
        class_2378 enchantments = AutoArmor.mc.field_1687.method_30349().method_30530(class_7924.field_41265);
        class_1741 material = armorItem.zov_pidarok$getMaterial();
        float protection = (float)material.comp_2298().getOrDefault(armorItem.zov_pidarok$getType(), 0).intValue() + material.comp_2303() + (float)class_1890.method_8225((class_6880)((class_6880)enchantments.method_10223(class_1893.field_9111.method_29177()).orElseThrow()), (class_1799)stack);
        float unbreaking = class_1890.method_8225((class_6880)((class_6880)enchantments.method_10223(class_1893.field_9119.method_29177()).orElseThrow()), (class_1799)stack);
        float mending = class_1890.method_8225((class_6880)((class_6880)enchantments.method_10223(class_1893.field_9101.method_29177()).orElseThrow()), (class_1799)stack);
        return protection + unbreaking * 0.1f + mending * 0.2f;
    }

    private boolean hasCurseOfBinding(class_1799 stack) {
        class_6880 curseEntry = AutoArmor.mc.field_1687.method_30349().method_30530(class_7924.field_41265).method_10223(class_1893.field_9113.method_29177()).orElse(null);
        if (curseEntry == null) {
            return false;
        }
        return class_1890.method_8225((class_6880)curseEntry, (class_1799)stack) > 0;
    }

    private boolean isBroken(class_1799 stack) {
        return (double)stack.method_7919() / (double)stack.method_7936() > 0.98;
    }

    private boolean isBetter(class_1799 newArmor, class_1799 currentArmor) {
        class_1738 newItem;
        class_1792 class_17922;
        block5: {
            block4: {
                if (currentArmor.method_7960()) {
                    return true;
                }
                class_17922 = newArmor.method_7909();
                if (!(class_17922 instanceof class_1738)) break block4;
                newItem = (class_1738)class_17922;
                class_17922 = currentArmor.method_7909();
                if (class_17922 instanceof class_1738) break block5;
            }
            return false;
        }
        class_1738 currentItem = (class_1738)class_17922;
        return this.calculateArmorValue(newArmor, (IArmorItem)newItem) > this.calculateArmorValue(currentArmor, (IArmorItem)currentItem);
    }

    private String equipName(class_1304 equipmentSlot) {
        return switch (equipmentSlot) {
            case class_1304.field_6166 -> "\u0411\u043e\u0442\u0438\u043d\u043a\u0438";
            case class_1304.field_6172 -> "\u041f\u043e\u043d\u043e\u0436\u0438";
            case class_1304.field_6174 -> "\u041d\u0430\u0433\u0440\u0443\u0434\u043d\u0438\u043a";
            case class_1304.field_6169 -> "\u0428\u043b\u0435\u043c";
            default -> "\u0414\u0415\u0417\u0417 \u0421\u0412\u0418\u041d\u042c\u042f \u0421\u0423\u041a\u0410\u0410\u0410!";
        };
    }
}
