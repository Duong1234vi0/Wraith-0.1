/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2596
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 */
package code.alphadlc.features.impl.player;

import code.alphadlc.events.block.BlockBreakingEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.managers.event.EventHandler;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2596;
import net.minecraft.class_2846;

public class FastBreak
extends Module {
    public FastBreak() {
        super("FastBreak", "FastBreak", ModuleCategory.PLAYER);
    }

    @EventHandler
    public void onBlockBreaking(BlockBreakingEvent e) {
        class_2338 blockPos = e.blockPos();
        class_2350 direction = e.direction();
        if ((double)FastBreak.mc.field_1761.field_3715 >= 0.5) {
            FastBreak.mc.field_1724.field_3944.method_52787((class_2596)new class_2846(class_2846.class_2847.field_12973, blockPos, direction));
            FastBreak.mc.field_1724.field_3944.method_52787((class_2596)new class_2846(class_2846.class_2847.field_12971, blockPos, direction));
        }
    }
}
