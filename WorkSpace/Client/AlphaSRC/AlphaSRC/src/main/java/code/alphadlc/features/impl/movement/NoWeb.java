/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_2246
 *  net.minecraft.class_238
 *  net.minecraft.class_2596
 *  net.minecraft.class_2879
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.input.MoveUtil;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.interactions.simulate.Simulations;
import net.minecraft.class_1268;
import net.minecraft.class_2246;
import net.minecraft.class_238;
import net.minecraft.class_2596;
import net.minecraft.class_2879;

public class NoWeb
extends Module {
    public final SelectSetting webMode = new SelectSetting("\u0420\u0435\u0436\u0438\u043c", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0440\u0435\u0436\u0438\u043c \u043e\u0431\u0445\u043e\u0434\u0430").value("\u0413\u0440\u0438\u043c", "\u0413\u0440\u0438\u043c \u043d\u043e\u0432\u044b\u0439");
    private SliderSettings speedy = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c Y", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0441\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0434\u043b\u044f \u0441\u0442\u0440\u0435\u0439\u0444\u0430").setValue(0.42f).range(0.0f, 3.0f).step(0.1f).visible(() -> this.webMode.getSelected().equals("\u0413\u0440\u0438\u043c"));
    private SliderSettings speedxz = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c XZ", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0441\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0434\u043b\u044f \u0441\u0442\u0440\u0435\u0439\u0444\u0430").setValue(0.64f).range(0.0f, 1.0f).step(0.01f).visible(() -> this.webMode.getSelected().equals("\u0413\u0440\u0438\u043c"));
    private final SliderSettings grimSpeed = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c XZ", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u044f \u0432 \u043f\u0430\u0443\u0442\u0438\u043d\u0435").range(0.05f, 0.5f).setValue(0.05f).step(0.05f).visible(() -> this.webMode.getSelected().equals("\u0413\u0440\u0438\u043c \u043d\u043e\u0432\u044b\u0439"));
    private final SliderSettings grimSpeedY = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c Y", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u044f \u0432 \u043f\u0430\u0443\u0442\u0438\u043d\u0435").range(0.05f, 0.8f).setValue(0.65f).step(0.05f).visible(() -> this.webMode.getSelected().equals("\u0413\u0440\u0438\u043c \u043d\u043e\u0432\u044b\u0439"));
    private int webTicks = 0;
    private boolean wasInWeb = false;
    private boolean grimFlag = false;
    private int packetDelay = 0;

    public static NoWeb getInstance() {
        return Instance.get(NoWeb.class);
    }

    public NoWeb() {
        super("NoWeb", "NoWeb", ModuleCategory.MOVEMENT);
        this.setup(this.webMode, this.speedy, this.speedxz, this.grimSpeed, this.grimSpeedY);
    }

    @EventHandler
    public void onTick(TickEvent e) {
        String mode = this.webMode.getSelected();
        if (mode.equals("\u0413\u0440\u0438\u043c")) {
            if (PlayerInteractionHelper.isPlayerInBlock(class_2246.field_10343)) {
                double[] speed = Simulations.calculateDirection(this.speedxz.getValue());
                NoWeb.mc.field_1724.method_5762(speed[0], 0.0, speed[1]);
                NoWeb.mc.field_1724.field_18276.field_1351 = NoWeb.mc.field_1690.field_1903.method_1434() ? (double)this.speedy.getValue() : (NoWeb.mc.field_1690.field_1832.method_1434() ? (double)(-this.speedy.getValue()) * 1.3 : 0.0);
            }
        } else if (mode.equals("\u0413\u0440\u0438\u043c \u043d\u043e\u0432\u044b\u0439")) {
            if (NoWeb.mc.field_1724 == null || NoWeb.mc.field_1687 == null) {
                return;
            }
            boolean inWeb = this.isPlayerInWeb();
            if (inWeb) {
                if (!this.wasInWeb) {
                    this.webTicks = 0;
                    this.grimFlag = false;
                    this.packetDelay = 0;
                }
                ++this.webTicks;
                this.wasInWeb = true;
                this.handleWebBypass();
            } else {
                this.webTicks = 0;
                this.wasInWeb = false;
                this.grimFlag = false;
                this.packetDelay = 0;
            }
            if (this.packetDelay > 0) {
                --this.packetDelay;
            }
        }
    }

    private void handleWebBypass() {
        String mode = this.webMode.getSelected();
        if (mode.equals("\u0413\u0440\u0438\u043c \u043d\u043e\u0432\u044b\u0439")) {
            this.handleGrimV2();
        }
    }

    private void handleGrimV2() {
        boolean isSwimming;
        if (NoWeb.mc.field_1724.method_7344().method_7586() < 20) {
            return;
        }
        double baseSpeed = this.grimSpeed.getValue();
        if (this.webTicks == 0 && !this.grimFlag && !(isSwimming = NoWeb.mc.field_1724.method_5681())) {
            NoWeb.mc.field_1724.field_3944.method_52787((class_2596)new class_2879(class_1268.field_5808));
            PlayerInteractionHelper.interactItem(class_1268.field_5810);
            this.grimFlag = true;
            this.packetDelay = 2;
        }
        if (this.webTicks > 0 && this.packetDelay == 0) {
            if (MoveUtil.isMoving()) {
                double[] motion = Simulations.forward(baseSpeed);
                NoWeb.mc.field_1724.method_5762(motion[0], 0.0, motion[1]);
            }
            if (!NoWeb.mc.field_1724.method_24828()) {
                if (NoWeb.mc.field_1690.field_1903.method_1434()) {
                    NoWeb.mc.field_1724.field_18276.field_1351 = this.grimSpeedY.getValue();
                } else if (NoWeb.mc.field_1690.field_1832.method_1434()) {
                    NoWeb.mc.field_1724.field_18276.field_1351 = -this.grimSpeedY.getValue();
                }
            }
        }
    }

    private boolean isPlayerInWeb() {
        if (NoWeb.mc.field_1724 == null || NoWeb.mc.field_1687 == null) {
            return false;
        }
        class_238 box = NoWeb.mc.field_1724.method_5829();
        return PlayerInteractionHelper.isBoxInBlock(box, class_2246.field_10343);
    }

    @Override
    public void deactivate() {
        super.deactivate();
        this.webTicks = 0;
        this.wasInWeb = false;
        this.grimFlag = false;
        this.packetDelay = 0;
    }
}
