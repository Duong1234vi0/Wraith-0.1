/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2596
 *  net.minecraft.class_2670
 *  net.minecraft.class_2827
 */
package code.alphadlc.features.impl.player;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.math.time.TimerUtil;
import net.minecraft.class_2596;
import net.minecraft.class_2670;
import net.minecraft.class_2827;

public class FakePing
extends Module {
    SliderSettings slid = new SliderSettings("\u0417\u043d\u0430\u0447\u0435\u043d\u0438\u0435 \u043f\u0438\u043d\u0433\u0430", "\u0423\u0441\u0442\u0430\u043d\u0430\u0432\u043b\u0438\u0432\u0430\u0435\u0442 \u0437\u0430\u0434\u0435\u0440\u0436\u043a\u0443 \u043d\u0430 \u0441\u0435\u0440\u0432\u0435\u0440").setValue(1000.0f).range(0.0f, 10000.0f);
    long id = -1L;
    TimerUtil time = new TimerUtil();

    public FakePing() {
        super("FakePing", ModuleCategory.PLAYER);
        this.setup(this.slid);
    }

    @EventHandler
    public void onPacket(PacketEvent event) {
        class_2596<?> class_25962;
        if (!event.isSend() && (class_25962 = event.getPacket()) instanceof class_2670) {
            class_2670 p = (class_2670)class_25962;
            this.id = p.method_11517();
            this.time.resetCounter();
            event.cancel();
        }
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (this.time.hasTimeElapsed((long)this.slid.getValue()) && this.id != -1L) {
            FakePing.mc.field_1724.field_3944.method_48296().method_10743((class_2596)new class_2827(this.id));
            this.id = -1L;
        }
    }
}
