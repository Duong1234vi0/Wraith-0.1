/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1294
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828$class_2829
 *  net.minecraft.class_2828$class_2830
 *  net.minecraft.class_3532
 *  net.minecraft.class_9069
 */
package code.alphadlc.features.impl.combat;

import code.alphadlc.events.player.AttackEvent;
import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.features.impl.combat.TriggerBot;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_3532;
import net.minecraft.class_9069;

public class PacketCriticals
extends Module {
    private static final double OLD_HOLYWORLD_OFFSET = 1.0E-10;
    private static final double KRYSTAL_OFFSET = 0.0625;
    private static final float GRIM_FALL_MIN = 0.08f;
    private static final float GRIM_FALL_RANGE = 0.25f;
    private final SelectSetting mode = new SelectSetting("\u0420\u0435\u0436\u0438\u043c", "\u0420\u0435\u0436\u0438\u043c \u043f\u0430\u043a\u0435\u0442\u043d\u044b\u0445 \u043a\u0440\u0438\u0442\u043e\u0432").value("Post", "Old Holyworld", "KrystalMC", "Grim").selected("Post");

    public PacketCriticals() {
        super("PacketCriticals", "Packet Criticals", ModuleCategory.COMBAT);
        this.setup(this.mode);
    }

    public static PacketCriticals getInstance() {
        return Instance.get(PacketCriticals.class);
    }

    public boolean isPostMode() {
        return this.mode.isSelected("Post");
    }

    public boolean isOldHolyworldMode() {
        return this.mode.isSelected("Old Holyworld");
    }

    public boolean isKrystalMode() {
        return this.mode.isSelected("KrystalMC");
    }

    public boolean isGrimMode() {
        return this.mode.isSelected("Grim");
    }

    public boolean allowsPacketCritState() {
        return this.isState() && PacketCriticals.mc.field_1724 != null && (PacketCriticals.mc.field_1724.method_6059(class_1294.field_5906) || this.isInCobweb());
    }

    public boolean shouldDeferMainTickAttack() {
        return this.isState() && this.isPostMode() && PacketCriticals.mc.field_1724 != null && PacketCriticals.mc.field_1724.method_6059(class_1294.field_5906) && !PacketCriticals.mc.field_1724.method_5869();
    }

    public boolean shouldAttackOnPostTick() {
        return this.isState() && this.isGrimMode() && PacketCriticals.mc.field_1724 != null && PacketCriticals.mc.field_1724.method_6059(class_1294.field_5906) && !PacketCriticals.mc.field_1724.method_5869() && PacketCriticals.mc.field_1724.field_6017 > 0.0f && PacketCriticals.mc.field_1724.field_6017 < 1.0f;
    }

    @EventHandler
    public void onAttack(AttackEvent event) {
        if (!this.isState() || PacketCriticals.mc.field_1724 == null || mc.method_1562() == null) {
            return;
        }
        if (this.isOldHolyworldMode()) {
            this.sendOffsetPacket(1.0E-10);
            return;
        }
        if (this.isKrystalMode()) {
            if (this.isInCobweb() || PacketCriticals.mc.field_1724.method_6059(class_1294.field_5909)) {
                this.sendOffsetPacket(0.0625);
            }
            return;
        }
        if (this.isGrimMode()) {
            class_1297 target = event.getEntity();
            if (target == null || target instanceof class_9069) {
                return;
            }
            this.sendGrimCrit();
        }
    }

    private void sendOffsetPacket(double offset) {
        mc.method_1562().method_52787((class_2596)new class_2828.class_2829(PacketCriticals.mc.field_1724.method_23317(), PacketCriticals.mc.field_1724.method_23318() - offset, PacketCriticals.mc.field_1724.method_23321(), false, false));
    }

    private void sendGrimCrit() {
        float fall;
        class_1309 triggerTarget;
        class_1309 auraTarget = AttackAura.getInstance() != null && AttackAura.getInstance().isState() ? AttackAura.getInstance().getTarget() : null;
        class_1309 class_13092 = triggerTarget = TriggerBot.getInstance() != null && TriggerBot.getInstance().isState() ? TriggerBot.getInstance().getTarget() : null;
        if (auraTarget == null && triggerTarget == null) {
            return;
        }
        if (PacketCriticals.mc.field_1724.method_24828()) {
            return;
        }
        double y = PacketCriticals.mc.field_1724.method_23318();
        if (y == (double)((int)y)) {
            return;
        }
        if (!this.isInCobweb() && !PacketCriticals.mc.field_1724.method_5799()) {
            return;
        }
        PacketCriticals.mc.field_1724.field_6017 = fall = ThreadLocalRandom.current().nextFloat() * 0.25f + 0.08f;
        mc.method_1562().method_52787((class_2596)new class_2828.class_2830(PacketCriticals.mc.field_1724.method_23317(), PacketCriticals.mc.field_1724.method_23318() - (double)fall, PacketCriticals.mc.field_1724.method_23321(), PacketCriticals.mc.field_1724.method_36454(), PacketCriticals.mc.field_1724.method_36455(), false, false));
    }

    private boolean isInCobweb() {
        if (PacketCriticals.mc.field_1724 == null || PacketCriticals.mc.field_1687 == null) {
            return false;
        }
        class_238 box = PacketCriticals.mc.field_1724.method_5829();
        for (class_2338 pos : class_2338.method_10094((int)class_3532.method_15357((double)box.field_1323), (int)class_3532.method_15357((double)box.field_1322), (int)class_3532.method_15357((double)box.field_1321), (int)class_3532.method_15357((double)box.field_1320), (int)class_3532.method_15357((double)box.field_1325), (int)class_3532.method_15357((double)box.field_1324))) {
            if (!PacketCriticals.mc.field_1687.method_8320(pos).method_27852(class_2246.field_10343)) continue;
            return true;
        }
        return false;
    }
}
