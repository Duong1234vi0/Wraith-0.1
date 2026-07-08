/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828$class_2829
 *  net.minecraft.class_5892
 */
package code.alphadlc.features.impl.player;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.DeathScreenEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.packet.network.Network;
import java.lang.runtime.SwitchBootstraps;
import java.util.Objects;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_5892;

public class AutoRespawn
extends Module {
    private final SelectSetting modeSetting = new SelectSetting("\u0420\u0435\u0436\u0438\u043c", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435, \u0447\u0442\u043e \u0431\u0443\u0434\u0435\u0442 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c\u0441\u044f").value("\u0424\u0430\u043d\u0422\u0430\u0439\u043c \u0432\u043e\u0437\u0432\u0440\u0430\u0442", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439");

    public AutoRespawn() {
        super("AutoRespawn", "AutoRespawn", ModuleCategory.PLAYER);
        this.setup(this.modeSetting);
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        class_2596<?> class_25962 = e.getPacket();
        Objects.requireNonNull(class_25962);
        class_2596<?> class_25963 = class_25962;
        int n = 0;
        block3: while (true) {
            switch (SwitchBootstraps.typeSwitch("typeSwitch", new Object[]{class_5892.class}, class_25963, n)) {
                case 0: {
                    class_5892 message = (class_5892)class_25963;
                    if (!Network.getWorldType().equals("lobby") || !this.modeSetting.isSelected("\u0424\u0430\u043d\u0422\u0430\u0439\u043c \u0432\u043e\u0437\u0432\u0440\u0430\u0442")) {
                        n = 1;
                        continue block3;
                    }
                    AutoRespawn.mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2829(1448.0, 1337.0, 228.0, false, false));
                    AutoRespawn.mc.field_1724.method_7331();
                    AutoRespawn.mc.field_1724.method_3137();
                    break block3;
                }
            }
            break;
        }
    }

    @EventHandler
    public void onDeathScreen(DeathScreenEvent e) {
        if (this.modeSetting.isSelected("\u041e\u0431\u044b\u0447\u043d\u044b\u0439")) {
            AutoRespawn.mc.field_1724.method_7331();
            mc.method_1507(null);
        }
    }
}
