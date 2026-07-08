/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.math.projection.Projection;

public class NoFallDamage
extends Module {
    private final SelectSetting mode = new SelectSetting("\u0420\u0435\u0436\u0438\u043c", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0442\u0438\u043f").value("SpookyTime").selected("SpookyTime");

    public NoFallDamage() {
        super("NoFallDamage", "NoFallDamage", ModuleCategory.MOVEMENT);
        this.setup(this.mode);
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        if (NoFallDamage.mc.field_1724 == null || NoFallDamage.mc.field_1687 == null) {
            return;
        }
        if (NoFallDamage.mc.field_1724.field_6017 > 0.0f && Projection.getDistanceToGround() > 4.0) {
            NoFallDamage.mc.field_1724.method_18800(0.0, 0.0, 0.0);
        }
    }

    public SelectSetting getMode() {
        return this.mode;
    }
}
