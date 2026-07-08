/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828$class_2830
 *  net.minecraft.class_2886
 */
package code.alphadlc.features.impl.player;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_2886;

public class NoFall
extends Module {
    public static NoFall getInstance() {
        return Instance.get(NoFall.class);
    }

    public NoFall() {
        super("NoFall", ModuleCategory.PLAYER);
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if ((double)NoFall.mc.field_1724.field_6017 > 2.4) {
            NoFall.mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2830(NoFall.mc.field_1724.method_23317(), NoFall.mc.field_1724.method_23318() + 1.0E-6, NoFall.mc.field_1724.method_23321(), NoFall.mc.field_1724.method_36454(), NoFall.mc.field_1724.method_36455(), false, false));
            NoFall.mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2830(NoFall.mc.field_1724.method_23317(), NoFall.mc.field_1724.method_23318() + 1.0E-6, NoFall.mc.field_1724.method_23321(), NoFall.mc.field_1724.method_36454(), NoFall.mc.field_1724.method_36455(), false, false));
            NoFall.mc.field_1724.field_3944.method_52787((class_2596)new class_2886(class_1268.field_5810, 0, NoFall.mc.field_1724.method_36454(), NoFall.mc.field_1724.method_36455()));
            NoFall.mc.field_1724.field_6017 = 0.0f;
        }
    }
}
