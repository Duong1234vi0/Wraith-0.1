/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;

public class AutoJump
extends Module {
    public AutoJump() {
        super("AutoJump", "\u041f\u0440\u044b\u0433\u0430\u0435\u0442 \u0430\u0432\u0442\u043e\u043c\u0430\u0442\u0438\u0447\u0435\u0441\u043a\u0438 \u043f\u0440\u0438 \u0430\u0443\u0440\u0435", ModuleCategory.MOVEMENT);
    }

    public static AutoJump getInstance() {
        return Instance.get(AutoJump.class);
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (AutoJump.mc.field_1724 == null || AutoJump.mc.field_1687 == null) {
            return;
        }
        AttackAura aura = AttackAura.getInstance();
        if (aura == null || !aura.isState()) {
            return;
        }
        if (aura.getTarget() != null && AutoJump.mc.field_1724.method_24828()) {
            AutoJump.mc.field_1724.method_6043();
        }
    }
}
