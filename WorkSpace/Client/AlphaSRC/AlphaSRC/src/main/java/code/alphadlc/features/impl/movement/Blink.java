/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2678
 *  net.minecraft.class_2724
 *  net.minecraft.class_2799
 *  net.minecraft.class_2799$class_2800
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.geometry.Render3D;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import java.lang.runtime.SwitchBootstraps;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2678;
import net.minecraft.class_2724;
import net.minecraft.class_2799;

public class Blink
extends Module {
    private final BooleanSetting pulse = new BooleanSetting("\u041f\u0443\u043b\u044c\u0441\u0430\u0446\u0438\u044f", "\u041f\u0435\u0440\u0438\u043e\u0434\u0438\u0447\u0435\u0441\u043a\u0438 \u043e\u0442\u043f\u0440\u0430\u0432\u043b\u044f\u0442\u044c \u043d\u0430\u043a\u043e\u043f\u043b\u0435\u043d\u043d\u044b\u0435 \u043f\u0430\u043a\u0435\u0442\u044b").setValue(false);
    private final SliderSettings pulseDelay = new SliderSettings("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430", "\u0418\u043d\u0442\u0435\u0440\u0432\u0430\u043b \u043f\u0443\u043b\u044c\u0441\u0430\u0446\u0438\u0438 \u0432 \u043c\u0441").setValue(500.0f).range(50.0f, 3000.0f).step(10.0f).visible(this.pulse::isValue);
    private final List<class_2596<?>> packets = new CopyOnWriteArrayList();
    private class_238 box;
    private class_243 renderPos;
    private long lastToggleTime;
    private long lastPulseTime;
    private boolean isFrozen = true;

    public Blink() {
        super("Blink", ModuleCategory.PLAYER);
        this.setup(this.pulse, this.pulseDelay);
    }

    @Override
    public void activate() {
        this.start();
    }

    @Override
    public void deactivate() {
        this.end();
    }

    public void start() {
        if (Blink.mc.field_1724 != null) {
            this.box = Blink.mc.field_1724.method_5829();
            this.renderPos = Blink.mc.field_1724.method_19538();
            this.lastPulseTime = System.currentTimeMillis();
        }
    }

    public void end() {
        this.packets.forEach(PlayerInteractionHelper::sendPacketWithOutEvent);
        this.packets.clear();
        this.box = null;
        this.renderPos = null;
        this.lastPulseTime = -1L;
    }

    @EventHandler
    public void onTick(TickEvent e) {
        this.update(this.pulse.isValue(), this.pulseDelay.getValue());
    }

    public void update(boolean pulse, double pulseDelay) {
        if (PlayerInteractionHelper.nullCheck()) {
            return;
        }
        if (!pulse) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - this.lastPulseTime >= (long)pulseDelay) {
            this.packets.forEach(PlayerInteractionHelper::sendPacketWithOutEvent);
            this.packets.clear();
            if (Blink.mc.field_1724 != null) {
                this.box = Blink.mc.field_1724.method_5829();
                this.renderPos = Blink.mc.field_1724.method_19538();
            }
            this.lastPulseTime = now;
        }
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        if (PlayerInteractionHelper.nullCheck()) {
            return;
        }
        class_2596<?> class_25962 = e.getPacket();
        Objects.requireNonNull(class_25962);
        class_2596<?> class_25963 = class_25962;
        int n = 0;
        block5: while (true) {
            switch (SwitchBootstraps.typeSwitch("typeSwitch", new Object[]{class_2724.class, class_2678.class, class_2799.class}, class_25963, n)) {
                case 0: {
                    class_2724 respawn = (class_2724)class_25963;
                    this.setState(false);
                    break block5;
                }
                case 1: {
                    class_2678 join = (class_2678)class_25963;
                    this.setState(false);
                    break block5;
                }
                case 2: {
                    class_2799 status = (class_2799)class_25963;
                    if (!status.method_12119().equals((Object)class_2799.class_2800.field_12774)) {
                        n = 3;
                        continue block5;
                    }
                    this.setState(false);
                    break block5;
                }
                default: {
                    if (!e.isSend()) break block5;
                    this.packets.add(e.getPacket());
                    e.cancel();
                    break block5;
                }
            }
            break;
        }
    }

    @EventHandler
    public void onWorldRender(WorldRenderEvent e) {
        if (this.renderPos != null && Blink.mc.field_1724 != null) {
            class_238 renderBox = Blink.mc.field_1724.method_5829().method_997(this.renderPos.method_1020(Blink.mc.field_1724.method_19538()));
            Render3D.drawBox(renderBox, ColorAssist.getClientColor(), 1.0f);
        }
    }
}
