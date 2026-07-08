/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2480
 *  net.minecraft.class_2490
 *  net.minecraft.class_495
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import net.minecraft.class_2338;
import net.minecraft.class_2480;
import net.minecraft.class_2490;
import net.minecraft.class_495;

public class HighJump
extends Module {
    private final SelectSetting modeSetting = new SelectSetting("\u0420\u0435\u0436\u0438\u043c", "\u0420\u0435\u0436\u0438\u043c \u043f\u0440\u044b\u0436\u043a\u0430").value("\u041b\u043e\u0434\u043a\u0430", "\u042d\u043a\u0440\u0430\u043d \u0448\u0430\u043b\u043a\u0435\u0440\u0430", "\u0411\u0443\u0441\u0442 \u0441\u043b\u0438\u0437\u0438", "\u0424\u0430\u043d\u0422\u0430\u0439\u043c \u043f\u0435\u0441\u043e\u043a \u0434\u0443\u0448").selected("\u041b\u043e\u0434\u043a\u0430");
    private final SliderSettings timer2 = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0431\u0443\u0441\u0442\u0430", "").setValue(2.4f).range(1.0f, 6.0f).visible(() -> this.modeSetting.isSelected("\u042d\u043a\u0440\u0430\u043d \u0448\u0430\u043b\u043a\u0435\u0440\u0430"));
    private boolean wasInShulkerScreen = false;
    private boolean wasOnSlimeBlock = false;

    public HighJump() {
        super("HighJump", "HighJump", ModuleCategory.MOVEMENT);
        this.setup(this.modeSetting, this.timer2);
    }

    @EventHandler
    private void tickEvent(TickEvent event) {
        if (this.modeSetting.isSelected("\u042d\u043a\u0440\u0430\u043d \u0448\u0430\u043b\u043a\u0435\u0440\u0430") && HighJump.mc.field_1755 instanceof class_495) {
            HighJump.mc.field_1724.method_5762(0.0, (double)this.timer2.getValue(), 0.0);
        }
        if (this.modeSetting.isSelected("\u0424\u0430\u043d\u0422\u0430\u0439\u043c \u043f\u0435\u0441\u043e\u043a \u0434\u0443\u0448") && HighJump.mc.field_1724.method_5799() && !HighJump.mc.field_1724.method_5869()) {
            HighJump.mc.field_1724.method_5762(0.0, 0.56, 0.0);
        }
        if (this.modeSetting.isSelected("\u041b\u043e\u0434\u043a\u0430")) {
            if (HighJump.mc.field_1755 instanceof class_495) {
                float yaw = (float)Math.toRadians(HighJump.mc.field_1724.method_36454());
                double x = -Math.sin(yaw) * 1.0;
                double z = Math.cos(yaw) * 1.0;
                HighJump.mc.field_1724.method_5762(0.0, 1.0, 0.0);
                HighJump.mc.field_1724.method_23327(HighJump.mc.field_1724.method_23317(), HighJump.mc.field_1724.method_23318() + 0.24, HighJump.mc.field_1724.method_23321());
            }
            if (HighJump.mc.field_1755 instanceof class_495) {
                this.wasInShulkerScreen = true;
            } else if (this.wasInShulkerScreen && HighJump.mc.field_1755 == null && this.isNearShulkerBox()) {
                this.wasInShulkerScreen = false;
            }
        }
        if (this.modeSetting.isSelected("\u0411\u0443\u0441\u0442 \u0441\u043b\u0438\u0437\u0438")) {
            if (HighJump.mc.field_1724.method_24828() && this.isOnSlimeBlock()) {
                this.wasOnSlimeBlock = true;
            } else if (this.wasOnSlimeBlock && !HighJump.mc.field_1724.method_24828() && HighJump.mc.field_1724.method_18798().method_10214() > 0.0) {
                HighJump.mc.field_1724.method_5762(0.0, 1.35, 0.0);
                this.wasOnSlimeBlock = false;
            } else if (!this.isOnSlimeBlock()) {
                this.wasOnSlimeBlock = false;
            }
        }
    }

    private boolean isNearShulkerBox() {
        class_2338 playerPos = HighJump.mc.field_1724.method_24515();
        for (int x = -1; x <= 1; ++x) {
            for (int y = -1; y <= 1; ++y) {
                for (int z = -1; z <= 1; ++z) {
                    class_2338 pos = playerPos.method_10069(x, y, z);
                    if (!(HighJump.mc.field_1687.method_8320(pos).method_26204() instanceof class_2480)) continue;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isOnSlimeBlock() {
        class_2338 playerPos = HighJump.mc.field_1724.method_24515();
        class_2338 belowPos = playerPos.method_10074();
        return HighJump.mc.field_1687.method_8320(belowPos).method_26204() instanceof class_2490;
    }

    public SelectSetting getModeSetting() {
        return this.modeSetting;
    }

    public SliderSettings getTimer2() {
        return this.timer2;
    }

    public boolean isWasInShulkerScreen() {
        return this.wasInShulkerScreen;
    }

    public boolean isWasOnSlimeBlock() {
        return this.wasOnSlimeBlock;
    }
}
