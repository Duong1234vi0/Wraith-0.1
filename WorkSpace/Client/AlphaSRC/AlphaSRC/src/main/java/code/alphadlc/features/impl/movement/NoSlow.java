/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1839
 *  net.minecraft.class_2338
 *  net.minecraft.class_2596
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 *  net.minecraft.class_2886
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.events.player.EventNoSlow;
import code.alphadlc.events.player.MotionEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.interactions.item.ItemUseHelper;
import code.alphadlc.utils.math.script.Script;
import code.alphadlc.utils.math.time.StopWatch;
import net.minecraft.class_1268;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1839;
import net.minecraft.class_2338;
import net.minecraft.class_2596;
import net.minecraft.class_2846;
import net.minecraft.class_2886;

public class NoSlow
extends Module {
    private final StopWatch notifWatch = new StopWatch();
    private final Script script = new Script();
    private boolean finish;
    public final SelectSetting itemMode = new SelectSetting("\u0420\u0435\u0436\u0438\u043c", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0440\u0435\u0436\u0438\u043c \u043e\u0431\u0445\u043e\u0434\u0430").value("\u041d\u043e\u0432\u044b\u0439 \u0413\u0440\u0438\u043c", "\u0421\u0442\u0430\u0440\u044b\u0439 \u0413\u0440\u0438\u043c", "\u0413\u0440\u0438\u043c", "\u0413\u0440\u0438\u043c \u0442\u0438\u043a\u0438", "\u0413\u0440\u0438\u043c2", "LonyGrief");
    public final BooleanSetting NoGepl = new BooleanSetting("\u041d\u0435 \u0440\u0430\u0431\u043e\u0442\u0430\u0442\u044c \u043d\u0430 \u0433\u0435\u043f\u043b\u044b \u0438 \u0447\u0430\u0440\u043a\u0438", "").visible(() -> this.itemMode.isSelected("\u0413\u0440\u0438\u043c"));
    private int ticks = 0;

    public static NoSlow getInstance() {
        return Instance.get(NoSlow.class);
    }

    public NoSlow() {
        super("NoSlow", "NoSlow", ModuleCategory.MOVEMENT);
        this.setup(this.itemMode, this.NoGepl);
    }

    @EventHandler
    public void onUpdate(TickEvent event) {
        if (this.itemMode.isSelected("\u0413\u0440\u0438\u043c")) {
            this.handleGrimLast();
        }
        if (this.itemMode.isSelected("LonyGrief")) {
            this.handleLonyGriefUpdate();
        }
        this.ticks = NoSlow.mc.field_1724.method_6058() == class_1268.field_5808 || NoSlow.mc.field_1724.method_6058() == class_1268.field_5810 ? ++this.ticks : 0;
    }

    @EventHandler
    public void onNoslow(EventNoSlow e) {
        String mode;
        if (NoSlow.mc.field_1724 == null || !NoSlow.mc.field_1724.method_6115()) {
            return;
        }
        class_1799 activeStack = NoSlow.mc.field_1724.method_6030();
        if (ItemUseHelper.isPriorityUseItem(activeStack)) {
            return;
        }
        class_1268 first = NoSlow.mc.field_1724.method_6058();
        class_1268 second = first.equals((Object)class_1268.field_5808) ? class_1268.field_5810 : class_1268.field_5808;
        switch (mode = this.itemMode.getSelected()) {
            case "\u0421\u0442\u0430\u0440\u044b\u0439 \u0413\u0440\u0438\u043c": {
                if (!NoSlow.mc.field_1724.method_6079().method_7976().equals((Object)class_1839.field_8952) && !NoSlow.mc.field_1724.method_6047().method_7976().equals((Object)class_1839.field_8952)) break;
                PlayerInteractionHelper.interactItem(first);
                PlayerInteractionHelper.interactItem(second);
                e.cancel();
                break;
            }
            case "\u041d\u043e\u0432\u044b\u0439 \u0413\u0440\u0438\u043c": {
                if (NoSlow.mc.field_1724.method_6048() < 7) {
                    InventoryTask.updateSlots();
                    InventoryTask.closeScreen(true);
                    break;
                }
                e.cancel();
                break;
            }
            case "\u0413\u0440\u0438\u043c \u0442\u0438\u043a\u0438": {
                if (this.NoGepl.isValue() && (activeStack.method_7909() == class_1802.field_8463 || activeStack.method_7909() == class_1802.field_8367)) {
                    return;
                }
                if (!((float)this.ticks > 1.0f) || NoSlow.mc.field_1724.method_6048() <= 1) break;
                e.cancel();
                this.ticks = 0;
                break;
            }
            case "\u0413\u0440\u0438\u043c": {
                if (this.NoGepl.isValue() && (activeStack.method_7909() == class_1802.field_8463 || activeStack.method_7909() == class_1802.field_8367)) {
                    return;
                }
                if (!this.handleGrimLastNoslow()) break;
                e.cancel();
                break;
            }
            case "\u0413\u0440\u0438\u043c2": {
                e.cancel();
                break;
            }
            case "LonyGrief": {
                this.handleLonyGriefNoSlow(e);
            }
        }
    }

    private boolean handleGrimLastNoslow() {
        if (NoSlow.mc.field_1724 == null) {
            return false;
        }
        int useTime = NoSlow.mc.field_1724.method_6048();
        if (useTime < 4) {
            return false;
        }
        return useTime > 4;
    }

    private void handleGrimLast() {
        if (NoSlow.mc.field_1724 == null || mc.method_1562() == null) {
            return;
        }
        class_1799 activeStack = NoSlow.mc.field_1724.method_6030();
        if (ItemUseHelper.isPriorityUseItem(activeStack)) {
            return;
        }
        int useTime = NoSlow.mc.field_1724.method_6048();
        if (useTime == 4) {
            mc.method_1562().method_52787((class_2596)new class_2846(class_2846.class_2847.field_12970, class_2338.field_10980, NoSlow.mc.field_1724.method_5735()));
        }
    }

    private void handleLonyGriefNoSlow(EventNoSlow event) {
        if (NoSlow.mc.field_1724 == null) {
            return;
        }
        class_1268 activeHand = NoSlow.mc.field_1724.method_6058();
        if (activeHand == class_1268.field_5810) {
            if (this.ticks % 2 == 0 && !NoSlow.mc.field_1724.method_5715()) {
                event.cancel();
            }
            return;
        }
        if (NoSlow.mc.field_1724.method_6048() > 0) {
            event.cancel();
        }
    }

    private void handleLonyGriefUpdate() {
        if (NoSlow.mc.field_1724 == null || NoSlow.mc.field_1724.field_3944 == null) {
            return;
        }
        if (NoSlow.mc.field_1724.method_6115() && NoSlow.mc.field_1724.method_6048() == 0) {
            NoSlow.mc.field_1724.field_3944.method_52787((class_2596)new class_2846(class_2846.class_2847.field_12970, class_2338.field_10980, NoSlow.mc.field_1724.method_5735()));
        }
    }

    @EventHandler
    public void onMotion(MotionEvent event) {
        if (!NoSlow.mc.field_1724.method_6115() || !this.itemMode.isSelected("\u0413\u0440\u0438\u043c2")) {
            return;
        }
        if (ItemUseHelper.isPriorityUseItem(NoSlow.mc.field_1724.method_6030())) {
            return;
        }
        class_1268 hand = NoSlow.mc.field_1724.method_6058();
        if (hand == class_1268.field_5808) {
            NoSlow.mc.field_1724.field_3944.method_52787((class_2596)new class_2886(class_1268.field_5810, 0, TurnsConnection.INSTANCE.getRotation().getYaw(), TurnsConnection.INSTANCE.getRotation().getPitch()));
        } else if (hand == class_1268.field_5810) {
            NoSlow.mc.field_1724.field_3944.method_52787((class_2596)new class_2886(class_1268.field_5808, 0, TurnsConnection.INSTANCE.getRotation().getYaw(), TurnsConnection.INSTANCE.getRotation().getPitch()));
        }
    }
}
