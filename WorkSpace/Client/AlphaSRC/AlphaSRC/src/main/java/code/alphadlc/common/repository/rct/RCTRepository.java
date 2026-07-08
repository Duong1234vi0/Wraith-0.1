/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1268
 *  net.minecraft.class_1707
 *  net.minecraft.class_1713
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2246
 *  net.minecraft.class_2596
 *  net.minecraft.class_2886
 *  net.minecraft.class_437
 *  net.minecraft.class_476
 *  net.minecraft.class_7439
 *  net.minecraft.class_8646
 */
package code.alphadlc.common.repository.rct;

import code.alphadlc.display.hud.Notifications;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.managers.event.EventManager;
import code.alphadlc.utils.client.packet.network.Network;
import code.alphadlc.utils.client.sound.SoundManager;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.interfaces.QuickLogger;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.math.time.StopWatch;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1707;
import net.minecraft.class_1713;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2596;
import net.minecraft.class_2886;
import net.minecraft.class_437;
import net.minecraft.class_476;
import net.minecraft.class_7439;
import net.minecraft.class_8646;

public class RCTRepository
implements QuickImports,
QuickLogger {
    private final StopWatch stopWatch = new StopWatch();
    private final StopWatch hubDelayWatch = new StopWatch();
    private boolean lobby;
    private int anarchy;
    private boolean rwReconnecting;
    private boolean hubSent;
    private boolean anSent;
    private int anarchyBeforeHub;
    private boolean rwLeftWorld;
    private long rwStartTime;

    public RCTRepository(EventManager eventManager) {
        EventManager.register(this);
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        class_7439 message;
        String text;
        class_2596<?> class_25962;
        if (this.anarchy != 0 && (class_25962 = e.getPacket()) instanceof class_7439 && !(text = (message = (class_7439)class_25962).comp_763().getString().toLowerCase()).contains("\u0445\u0430\u0431") && text.contains("\u043d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c")) {
            Notifications.getInstance().addList("[RCT] \u041d\u0430 \u0434\u0430\u043d\u043d\u0443\u044e \u0430\u043d\u0430\u0440\u0445\u0438\u044e " + String.valueOf(class_124.field_1061) + "\u043d\u0435\u043b\u044c\u0437\u044f" + String.valueOf(class_124.field_1070) + " \u0437\u0430\u0439\u0442\u0438", 3000);
            this.anarchy = 0;
            this.rwReconnecting = false;
        }
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if (this.anarchy == 0) {
            return;
        }
        if (Network.isReallyWorld()) {
            this.tickReallyWorld();
        } else if (Network.isSpookyTime() || Network.isFunTime()) {
            this.tickCopyTime();
        } else if (this.anarchy > 0 && (this.hubSent || this.rwReconnecting)) {
            this.tickCopyTime();
        } else {
            this.anarchy = 0;
            this.rwReconnecting = false;
            this.hubSent = false;
            this.anSent = false;
            this.anarchyBeforeHub = -1;
            this.hubDelayWatch.reset();
        }
    }

    private void tickReallyWorld() {
        int currentAnarchy = Network.getAnarchy();
        if (!this.rwReconnecting) {
            this.rwReconnecting = true;
            this.hubSent = false;
            this.rwLeftWorld = false;
            this.anarchyBeforeHub = currentAnarchy;
            this.rwStartTime = System.currentTimeMillis();
            this.hubDelayWatch.reset();
        }
        if (System.currentTimeMillis() - this.rwStartTime > 25000L) {
            Notifications.getInstance().addList("[RCT] \u0422\u0430\u0439\u043c\u0430\u0443\u0442 \u2014 \u043d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u043f\u0435\u0440\u0435\u0437\u0430\u0439\u0442\u0438 \u043d\u0430 \u0413\u0420\u0418\u0424 #" + String.valueOf(class_124.field_1078) + this.anarchy + String.valueOf(class_124.field_1070), 3500);
            this.anarchy = 0;
            this.rwReconnecting = false;
            this.hubSent = false;
            this.rwLeftWorld = false;
            this.anarchyBeforeHub = -1;
            return;
        }
        if (!this.hubSent) {
            RCTRepository.mc.field_1724.field_3944.method_45730("hub");
            this.hubSent = true;
            this.hubDelayWatch.reset();
            return;
        }
        int nowAnarchy = Network.getAnarchy();
        if (!this.rwLeftWorld && nowAnarchy != this.anarchyBeforeHub) {
            this.rwLeftWorld = true;
        }
        if (!this.rwLeftWorld) {
            return;
        }
        if (nowAnarchy == this.anarchy) {
            int completed = this.anarchy;
            SoundManager.playSound(SoundManager.ENABLE_MODULE);
            Notifications.getInstance().addList("\u041f\u0435\u0440\u0435\u0437\u0430\u0445\u043e\u0434 \u043d\u0430 \u0413\u0420\u0418\u0424 #" + String.valueOf(class_124.field_1078) + completed + String.valueOf(class_124.field_1070) + " \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d", 3000);
            this.anarchy = 0;
            this.rwReconnecting = false;
            this.hubSent = false;
            this.rwLeftWorld = false;
            this.anarchyBeforeHub = -1;
            return;
        }
        class_437 class_4372 = RCTRepository.mc.field_1755;
        if (class_4372 instanceof class_476) {
            class_476 screen = (class_476)class_4372;
            String title = screen.method_25440().getString();
            if (title.contains("\u0412\u044b\u0431\u043e\u0440 \u0441\u0435\u0440\u0432\u0435\u0440\u0430")) {
                for (int i = 0; i < ((class_1707)screen.method_17577()).method_7629().method_5439(); ++i) {
                    class_1799 stack = ((class_1707)screen.method_17577()).method_7629().method_5438(i);
                    String name = stack.method_7964().getString();
                    if (!name.contains("\u0413\u0420\u0418\u0424\u0415\u0420\u0421\u041a\u041e\u0415 \u0412\u042b\u0416\u0418\u0412\u0410\u041d\u0418\u0415") || stack.method_7909() != class_2246.field_9980.method_8389()) continue;
                    InventoryTask.clickSlot(i, 0, class_1713.field_7790, false);
                    return;
                }
            } else if (title.contains("\u0412\u044b\u0431\u043e\u0440 \u043c\u0438\u0440\u0430 \u0433\u0440\u0438\u0444\u0430")) {
                for (int i = 0; i < ((class_1707)screen.method_17577()).method_7629().method_5439(); ++i) {
                    class_1799 stack = ((class_1707)screen.method_17577()).method_7629().method_5438(i);
                    String name = stack.method_7964().getString();
                    if (!name.contains("\u0413\u0420\u0418\u0424 #" + this.anarchy) || stack.method_7909() != class_1802.field_8575) continue;
                    InventoryTask.clickSlot(i, 0, class_1713.field_7790, false);
                    return;
                }
            }
        } else if (this.stopWatch.every(500.0)) {
            InventoryTask.selectCompass();
            RCTRepository.mc.field_1724.field_3944.method_52787((class_2596)new class_2886(class_1268.field_5808, RCTRepository.mc.field_1724.method_31548().field_7545, RCTRepository.mc.field_1724.method_36454(), RCTRepository.mc.field_1724.method_36455()));
        }
    }

    private void tickCopyTime() {
        String serverTitle;
        long elapsed;
        int currentAnarchy = Network.getAnarchy();
        long l = elapsed = this.hubSent ? this.hubDelayWatch.elapsedTime() : 0L;
        String string = RCTRepository.mc.field_1687 != null && RCTRepository.mc.field_1687.method_8428() != null ? (RCTRepository.mc.field_1687.method_8428().method_1189(class_8646.field_45157) != null ? RCTRepository.mc.field_1687.method_8428().method_1189(class_8646.field_45157).method_1114().getString() : "null") : (serverTitle = "null");
        if (currentAnarchy == this.anarchy && this.anarchy > 0 && this.rwReconnecting && this.anSent && serverTitle.contains("\u0410\u043d\u0430\u0440\u0445\u0438\u044f-")) {
            int completedAnarchy = this.anarchy;
            SoundManager.playSound(SoundManager.ENABLE_MODULE);
            Notifications.getInstance().addList("\u041f\u0435\u0440\u0435\u0437\u0430\u0445\u043e\u0434 \u043d\u0430 \u0430\u043d\u0430\u0440\u0445\u0438\u044e #" + String.valueOf(class_124.field_1078) + completedAnarchy + String.valueOf(class_124.field_1070) + " \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d", 3000);
            this.anarchy = 0;
            this.rwReconnecting = false;
            this.hubSent = false;
            this.anSent = false;
            this.anarchyBeforeHub = -1;
            this.hubDelayWatch.reset();
            return;
        }
        if (currentAnarchy == this.anarchy && this.anarchy > 0 && !this.rwReconnecting) {
            this.rwReconnecting = true;
            this.hubSent = false;
            this.anSent = false;
            this.hubDelayWatch.reset();
        }
        if (this.rwReconnecting) {
            if (!this.hubSent) {
                this.anarchyBeforeHub = currentAnarchy;
                RCTRepository.mc.field_1724.field_3944.method_45729("/hub");
                this.hubSent = true;
                this.anSent = false;
                this.hubDelayWatch.reset();
            } else if (!this.anSent) {
                boolean worldChanged;
                elapsed = this.hubDelayWatch.elapsedTime();
                boolean bl = worldChanged = currentAnarchy != this.anarchyBeforeHub;
                if (elapsed >= 890L && worldChanged) {
                    RCTRepository.mc.field_1724.field_3944.method_45729("/an" + this.anarchy);
                    this.anSent = true;
                }
            }
        }
    }

    public void reconnect(int anarchy) {
        if (Network.isReallyWorld()) {
            if (anarchy > 0) {
                this.anarchy = anarchy;
                this.rwReconnecting = false;
                this.hubSent = false;
                this.rwLeftWorld = false;
                this.anarchyBeforeHub = -1;
                this.rwStartTime = 0L;
            } else {
                Notifications.getInstance().addList("[RCT] \u041d\u0435\u0432\u0435\u0440\u043d\u044b\u0439 " + String.valueOf(class_124.field_1061) + "\u0433\u0440\u0438\u0444", 3000);
            }
        } else if (Network.isSpookyTime() || Network.isFunTime()) {
            if (anarchy > 0) {
                this.anarchy = anarchy;
                this.rwReconnecting = false;
                this.hubSent = false;
                this.hubDelayWatch.reset();
            } else {
                int currentAnarchy = Network.getAnarchy();
                if (currentAnarchy > 0) {
                    this.anarchy = currentAnarchy;
                    this.rwReconnecting = false;
                    this.hubSent = false;
                    this.hubDelayWatch.reset();
                } else {
                    Notifications.getInstance().addList("[RCT] \u041d\u0435\u0432\u0435\u0440\u043d\u044b\u0439 \u043d\u043e\u043c\u0435\u0440 " + String.valueOf(class_124.field_1061) + "\u0430\u043d\u0430\u0440\u0445\u0438\u0438", 3000);
                }
            }
        }
    }
}
