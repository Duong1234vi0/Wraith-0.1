/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1291
 *  net.minecraft.class_1293
 *  net.minecraft.class_1294
 *  net.minecraft.class_1657
 *  net.minecraft.class_1713
 *  net.minecraft.class_1735
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1844
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828$class_2830
 *  net.minecraft.class_2868
 *  net.minecraft.class_2886
 *  net.minecraft.class_4081
 *  net.minecraft.class_6880
 *  net.minecraft.class_9334
 */
package code.alphadlc.features.impl.combat;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.packet.network.Network;
import code.alphadlc.utils.features.aura.warp.Turns;
import code.alphadlc.utils.features.rotate.RotationUtils;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.interactions.inv.InventoryToolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1268;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1844;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_2868;
import net.minecraft.class_2886;
import net.minecraft.class_4081;
import net.minecraft.class_6880;
import net.minecraft.class_9334;

public class AutoPotion
extends Module {
    private static final int THROW_DELAY_TICKS = 20;
    private final MultiSelectSetting throwSetting = new MultiSelectSetting("\u0411\u0440\u043e\u0441\u0430\u0442\u044c", "\u041a\u0430\u043a\u0438\u0435 \u0437\u0435\u043b\u044c\u044f \u0431\u0440\u043e\u0441\u0430\u0442\u044c \u043f\u043e\u0434 \u0441\u0435\u0431\u044f").value("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", "\u0421\u0438\u043b\u0430", "\u041e\u0433\u043d\u0435\u0441\u0442\u043e\u0439\u043a\u043e\u0441\u0442\u044c").selected("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", "\u0421\u0438\u043b\u0430", "\u041e\u0433\u043d\u0435\u0441\u0442\u043e\u0439\u043a\u043e\u0441\u0442\u044c");
    private final BooleanSetting onlyPvP = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u043f\u0440\u0438 \u043f\u0432\u043f", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c \u0442\u043e\u043b\u044c\u043a\u043e \u0432 PVP").setValue(true);
    private final BooleanSetting autoDisable = new BooleanSetting("\u041e\u0442\u043a\u043b\u044e\u0447\u0430\u0442\u044c \u043f\u043e\u0441\u043b\u0435 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u044f", "\u0412\u044b\u043a\u043b\u044e\u0447\u0438\u0442\u044c \u043c\u043e\u0434\u0443\u043b\u044c \u043f\u043e\u0441\u043b\u0435 \u0431\u0440\u043e\u0441\u043a\u0430").setValue(false);
    private final BooleanSetting hotbarOnly = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u0441 \u0445\u043e\u0442\u0431\u0430\u0440\u0430", "\u0411\u0440\u0430\u0442\u044c \u0437\u0435\u043b\u044c\u044f \u0442\u043e\u043b\u044c\u043a\u043e \u0438\u0437 \u0445\u043e\u0442\u0431\u0430\u0440\u0430").setValue(false);
    private final BooleanSetting strengthPriority = new BooleanSetting("\u041f\u0440\u0438\u043e\u0440\u0438\u0442\u0435\u0442 \u0441\u0438\u043b\u044b", "\u0421\u043d\u0430\u0447\u0430\u043b\u0430 \u0441\u0438\u043b\u0430, \u0437\u0430\u0442\u0435\u043c \u0441\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0438 \u043e\u0433\u043d\u0435\u0441\u0442\u043e\u0439\u043a\u043e\u0441\u0442\u044c").setValue(true);
    private int ticksSinceThrow = 20;
    private boolean usedThisCycle;

    public AutoPotion() {
        super("AutoPotion", "AutoPotion", ModuleCategory.COMBAT);
        this.setup(this.throwSetting, this.onlyPvP, this.autoDisable, this.hotbarOnly, this.strengthPriority);
    }

    @Override
    public void activate() {
        this.ticksSinceThrow = 20;
        this.usedThisCycle = false;
        super.activate();
    }

    @Override
    public void deactivate() {
        this.ticksSinceThrow = 20;
        this.usedThisCycle = false;
        super.deactivate();
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (AutoPotion.mc.field_1724 == null || AutoPotion.mc.field_1687 == null || mc.method_1562() == null) {
            return;
        }
        if (this.autoDisable.isValue() && this.usedThisCycle) {
            return;
        }
        if (this.onlyPvP.isValue() && !Network.isPvp()) {
            return;
        }
        if (AutoPotion.mc.field_1724.method_6128() || AutoPotion.mc.field_1724.method_6115() && !AutoPotion.mc.field_1724.method_6039()) {
            return;
        }
        ++this.ticksSinceThrow;
        if (this.ticksSinceThrow < 20) {
            return;
        }
        List<Integer> slots = this.collectSlots();
        if (slots.isEmpty()) {
            return;
        }
        if (this.hotbarOnly.isValue() && slots.stream().anyMatch(slot -> slot >= 9) && (slots = slots.stream().filter(slot -> slot < 9).toList()).isEmpty()) {
            return;
        }
        Turns angle = this.computeDownAngle();
        this.syncRotation(angle);
        this.throwPotions(slots, angle);
        this.ticksSinceThrow = 0;
        this.usedThisCycle = true;
        if (this.autoDisable.isValue()) {
            this.setState(false);
        }
    }

    private List<Integer> collectSlots() {
        ArrayList<Integer> slots = new ArrayList<Integer>();
        if (this.strengthPriority.isValue()) {
            this.addIfNeeded(slots, "\u0421\u0438\u043b\u0430", (class_6880<class_1291>)class_1294.field_5910);
            this.addIfNeeded(slots, "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", (class_6880<class_1291>)class_1294.field_5904);
            this.addIfNeeded(slots, "\u041e\u0433\u043d\u0435\u0441\u0442\u043e\u0439\u043a\u043e\u0441\u0442\u044c", (class_6880<class_1291>)class_1294.field_5918);
        } else {
            this.addIfNeeded(slots, "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", (class_6880<class_1291>)class_1294.field_5904);
            this.addIfNeeded(slots, "\u0421\u0438\u043b\u0430", (class_6880<class_1291>)class_1294.field_5910);
            this.addIfNeeded(slots, "\u041e\u0433\u043d\u0435\u0441\u0442\u043e\u0439\u043a\u043e\u0441\u0442\u044c", (class_6880<class_1291>)class_1294.field_5918);
        }
        return slots;
    }

    private void addIfNeeded(List<Integer> slots, String label, class_6880<class_1291> effect) {
        if (!this.throwSetting.isSelected(label) || AutoPotion.mc.field_1724.method_6059(effect)) {
            return;
        }
        class_1735 slot = this.findSplash(effect);
        if (slot != null) {
            slots.add(slot.field_7874);
        }
    }

    private class_1735 findSplash(class_6880<class_1291> effect) {
        class_1735 hotbar = this.findInRange(effect, 36, 45);
        if (hotbar != null) {
            return hotbar;
        }
        if (this.hotbarOnly.isValue()) {
            return null;
        }
        return this.findInRange(effect, 9, 36);
    }

    private class_1735 findInRange(class_6880<class_1291> effect, int fromInclusive, int toExclusive) {
        for (class_1735 slot : InventoryTask.slots().toList()) {
            if (slot.field_7874 < fromInclusive || slot.field_7874 >= toExclusive || !this.isSplashWithEffect(slot.method_7677(), effect)) continue;
            return slot;
        }
        return null;
    }

    private boolean isSplashWithEffect(class_1799 stack, class_6880<class_1291> effect) {
        if (stack == null || !stack.method_31574(class_1802.field_8436)) {
            return false;
        }
        class_1844 contents = (class_1844)stack.method_57824(class_9334.field_49651);
        if (contents == null) {
            return false;
        }
        boolean hasEffect = false;
        for (class_1293 instance : contents.method_57397()) {
            if (((class_1291)instance.method_5579().comp_349()).method_18792() == class_4081.field_18272) {
                return false;
            }
            if (!instance.method_5579().equals(effect)) continue;
            hasEffect = true;
        }
        return hasEffect;
    }

    private Turns computeDownAngle() {
        float[] rotation = RotationUtils.correctRotation(new float[]{AutoPotion.mc.field_1724.method_36454(), 90.0f + ThreadLocalRandom.current().nextFloat(-1.5f, 1.5f)});
        return new Turns(rotation[0], rotation[1]).adjustSensitivity();
    }

    private void syncRotation(Turns angle) {
        mc.method_1562().method_52787((class_2596)new class_2828.class_2830(AutoPotion.mc.field_1724.method_23317(), AutoPotion.mc.field_1724.method_23318(), AutoPotion.mc.field_1724.method_23321(), angle.getYaw(), angle.getPitch(), AutoPotion.mc.field_1724.method_24828(), AutoPotion.mc.field_1724.field_5976));
    }

    private void throwPotions(List<Integer> screenSlots, Turns angle) {
        int originalHotbar = AutoPotion.mc.field_1724.method_31548().field_7545;
        int syncId = AutoPotion.mc.field_1724.field_7512.field_7763;
        boolean changedSlot = false;
        for (int screenSlot : screenSlots) {
            if (screenSlot >= 36 && screenSlot <= 44) {
                int hotbar = screenSlot - 36;
                if (hotbar != originalHotbar) {
                    mc.method_1562().method_52787((class_2596)new class_2868(hotbar));
                    changedSlot = true;
                }
                this.sendThrow(angle);
                continue;
            }
            AutoPotion.mc.field_1761.method_2906(syncId, screenSlot, originalHotbar, class_1713.field_7791, (class_1657)AutoPotion.mc.field_1724);
            mc.method_1562().method_52787((class_2596)new class_2868(originalHotbar));
            this.sendThrow(angle);
            AutoPotion.mc.field_1761.method_2906(syncId, screenSlot, originalHotbar, class_1713.field_7791, (class_1657)AutoPotion.mc.field_1724);
            changedSlot = true;
        }
        if (changedSlot) {
            mc.method_1562().method_52787((class_2596)new class_2868(originalHotbar));
        }
    }

    private void sendThrow(Turns angle) {
        InventoryToolkit.sendSequencedPacket(seq -> new class_2886(class_1268.field_5808, seq, angle.getYaw(), angle.getPitch()));
    }
}
