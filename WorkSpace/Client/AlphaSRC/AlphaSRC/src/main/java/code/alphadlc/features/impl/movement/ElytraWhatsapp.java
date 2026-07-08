/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_243
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.Setting;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_243;

public class ElytraWhatsapp
extends Module {
    private static final double FREEZE_DISTANCE = 3.0;
    private static final double INTERP_Y = 0.5;
    private boolean releasedFreeze;

    public ElytraWhatsapp() {
        super("ElytraWhatsapp", "Elytra Whatsapp", ModuleCategory.MOVEMENT);
        this.setup(new Setting[0]);
    }

    @EventHandler
    public void onTick(TickEvent event) {
        class_1309 target;
        if (!this.state || PlayerInteractionHelper.nullCheck()) {
            return;
        }
        AttackAura aura = Instance.get(AttackAura.class);
        class_1309 class_13092 = target = aura != null ? aura.getTarget() : null;
        if (target == null) {
            if (!this.releasedFreeze) {
                ElytraWhatsapp.mc.field_1724.method_18799(ElytraWhatsapp.mc.field_1724.method_18798().method_18805(1.0, 0.0, 1.0));
                this.releasedFreeze = true;
            }
            return;
        }
        this.releasedFreeze = false;
        boolean auraFar = aura == null || ElytraWhatsapp.mc.field_1724.method_5739((class_1297)target) >= 6.0f || aura.getTarget() != null && ElytraWhatsapp.mc.field_1724.method_5739((class_1297)aura.getTarget()) == 0.0f;
        class_243 targetCenter = target.method_5829().method_1005().method_1031(0.0, (target.method_23318() - target.field_6036) * 0.5, 0.0);
        if (ElytraWhatsapp.mc.field_1724.method_6128() && ElytraWhatsapp.mc.field_1724.method_33571().method_1022(targetCenter) < 3.0 && !auraFar) {
            ElytraWhatsapp.mc.field_1724.method_18799(class_243.field_1353);
            ElytraWhatsapp.mc.field_1724.method_5875(true);
        } else {
            ElytraWhatsapp.mc.field_1724.method_5875(false);
        }
    }

    @Override
    public void deactivate() {
        if (ElytraWhatsapp.mc.field_1724 != null) {
            this.releasedFreeze = false;
            ElytraWhatsapp.mc.field_1724.method_5875(false);
        }
        super.deactivate();
    }
}
